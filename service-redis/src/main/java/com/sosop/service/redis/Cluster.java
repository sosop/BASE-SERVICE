package com.sosop.service.redis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @author sosop
 * @date 2014.9.18 redis集群类， 包含一个redis池， 集群服务器
 * 
 */
public class Cluster {
	
	public final static int DEFAULT_WEIGHT = 10;
	
	private String name;

	private int num;
	
	private int weight;
	

	private ShardedJedisPool pool;
	private JedisPoolConfig config;
	private List<JedisShardInfo> servers;

	public Cluster() {
		this.weight = DEFAULT_WEIGHT;
	}

	public Cluster(String name, JedisPoolConfig config,
			List<JedisShardInfo> servers) {
		this.name = name;
		this.config = config;
		this.servers = servers;
		this.weight = DEFAULT_WEIGHT;
	}

	/**
	 * 配置集群资源
	 */
	public void wire() {
		if (null == pool) {
			synchronized (this) {
				if (null == pool) {
					this.pool = new ShardedJedisPool(config, servers);
				}
			}
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConfig(JedisPoolConfig config) {
		this.config = config;
	}

	public void setServers(List<JedisShardInfo> servers) {
		this.servers = servers;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public ShardedJedis getJedis() {
		ShardedJedis realJedis = pool.getResource();
		ShardedJedis proxy = (ShardedJedis) Proxy.newProxyInstance(realJedis
				.getClass().getClassLoader(), realJedis.getClass()
				.getInterfaces(), new JedisRelease(realJedis));
		return proxy;
	}

	/**
	 * 
	 * @author xiaolong.hou 动态代理，将资源放回池
	 */
	class JedisRelease implements InvocationHandler {

		private ShardedJedis jedis;

		public JedisRelease(ShardedJedis jedis) {
			this.jedis = jedis;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			Object obj = null;
			try {
				obj = method.invoke(this.jedis, args);
			} catch (Exception e) {
				pool.returnBrokenResource(this.jedis);
			} finally {
				pool.returnResource(this.jedis);
			}
			return obj;
		}
	}
}