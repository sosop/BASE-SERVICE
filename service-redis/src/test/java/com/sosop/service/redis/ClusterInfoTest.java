package com.sosop.service.redis;

import org.junit.Before;
import org.junit.Test;


public class ClusterInfoTest {
	
	private ClusterInfo info;
	
	@Before
	public void init() {
		info = new ClusterInfo();
	}
	
	@Test
	public void testConfig() {
		info.config();
	}
}
