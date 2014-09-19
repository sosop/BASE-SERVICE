package com.sosop.service.redis.rule;

import com.sosop.service.redis.Cluster;

public abstract class ClusterNumRule extends Rule {
	
	@Override
	public Cluster getCluster(Object condition) {
		return clusterInfo.getNumMap().get(getNum(condition));
	}
	
	public abstract int getNum(Object condition);
}
