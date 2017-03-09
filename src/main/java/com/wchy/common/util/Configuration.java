package com.wchy.common.util;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class Configuration<K, V> extends HashMap<K, V> {

	
	public Configuration<K, V> clone() {
		String jsonString = JSON.toJSONString(this);
		return (Configuration<K, V>) JSON.parseObject(jsonString, Configuration.class);
	}
}
