package com.sosop.service.redis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import com.sosop.service.common.util.Properties;
import com.sosop.service.common.util.StringUtil;

public class ClusterInfo {

	private final static Logger LOG = Logger.getLogger(Cluster.class);

	private final static String DEFAUL_PATH = "redis-cluster.properties";

	private Properties prop;

	private List<Cluster> clusters;

	public ClusterInfo() {
		this.prop = new Properties();
		prop.load(DEFAUL_PATH);
	}

	public ClusterInfo(String path) {
		this.prop = new Properties();
		prop.load(path);
	}

	public ClusterInfo(Properties prop) {
		this.prop = prop;
	}

	public void config() {
		clusters = new ArrayList<>();

		Map<String, String> info = prop.getContainerMap();

		JedisPoolConfig shareConfig = null;

		// 获得集群数
		int clusterCount = 0;

		for (String key : info.keySet()) {
			if ("[cluster]".equalsIgnoreCase(key)) {
				clusterCount++;
			}
			if ("[share-pool]".equalsIgnoreCase(key)) {
				shareConfig = new JedisPoolConfig();
				poolConfig(shareConfig, "c", info);
			}
		}

		// 获得每个集群的配置
		for (int i = 1; i <= clusterCount; i++) {
			// pool info
			Cluster cluster = new Cluster();
			if (shareConfig != null) {
				cluster.setConfig(shareConfig);
			} else {
				JedisPoolConfig config = new JedisPoolConfig();
				poolConfig(config, StringUtil.append("c", String.valueOf(i)),
						info);
			}
			// 获取 server 数量
			List<JedisShardInfo> servers = new ArrayList<>();
			int serverCount = Integer.parseInt(info.get(StringUtil.append("c",
					String.valueOf(i), ".server.count")));
			for (int j = 1; j <= serverCount; j++) {
				String serverKey = StringUtil.append("c", String.valueOf(i),
						".s", String.valueOf(j), ".");
				JedisShardInfo server = new JedisShardInfo(
						info.get(StringUtil.append(serverKey, "host")), 
						Integer.parseInt(info.get(StringUtil.append(serverKey, "port"))),
						Integer.parseInt(info.get(StringUtil.append(serverKey,"timeout"))), 
						Integer.parseInt(info.get(StringUtil.append(serverKey, "weight"))));
				servers.add(server);
			}
			cluster.setServers(servers);
			
			// 获得编号和名称
			cluster.setName(info.get(StringUtil.append("c", String.valueOf(i), ".name")));
			cluster.setNum(Integer.parseInt(info.get(StringUtil.append("c", String.valueOf(i), ".num"))));
			
			clusters.add(cluster);
		}
	}

	private void poolConfig(JedisPoolConfig config, String prefix,
			Map<String, String> map) {

		Field[] fields1 = GenericObjectPoolConfig.class.getDeclaredFields();
		Field[] fields2 = BaseObjectPoolConfig.class.getDeclaredFields();

		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(fields1));
		fields.addAll(Arrays.asList(fields2));

		for (Field field : fields) {
			String name = field.getName();
			String key = StringUtil.append(prefix, ".pool.", name);
			if (map.containsKey(key)) {
				try {
					BeanUtils.setProperty(config, name, map.get(key));
				} catch (IllegalAccessException | InvocationTargetException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}

	}
}