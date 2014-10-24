package com.sosop.service.cluster;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class ClusterClient {
	public static void main(String[] args) {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("localhost", 7000));
		/*nodes.add(new HostAndPort("localhost", 7001));*/
		JedisCluster jc = new JedisCluster(nodes);
		for (Entry<String, JedisPool> entry : jc.getClusterNodes().entrySet()) {
			System.out.println(entry.getKey() + " ==" + entry.getValue());
		}
		System.out.println(jc.get("test"));
/*		jc.set("key1", "test1");
		jc.set("key2", "test2");
		jc.close();
		jc.shutdown();*/
	}
}
