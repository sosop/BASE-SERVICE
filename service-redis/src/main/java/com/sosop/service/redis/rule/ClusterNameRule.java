package com.sosop.service.redis.rule;

import com.sosop.service.redis.Cluster;

public abstract class ClusterNameRule extends Rule {
	@Override
	public Cluster getCluster(Object condition) {
		return clusterInfo.getNameMap().get(getName(condition));
	}
	
	public abstract String getName(Object condition);
}
