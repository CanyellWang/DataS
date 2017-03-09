package com.wchy.common.impl;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.wchy.common.task.AbstractRunner;
import com.wchy.common.task.IWriter;
import com.wchy.common.util.*;
import com.wchy.vo.VshopDistributionItemVo;

import java.io.IOException;
import java.util.Date;

public class MysqlWriter extends AbstractRunner implements IWriter {
	
	
	private MQPushProducer mqPushProducer = (MQPushProducer) ApplicationContextUtils.getBean("mqPushProducer");
	
	private Kryo kryo = new Kryo();
	private Output output = null;
	public MysqlWriter() {
		kryo.register(VshopDistributionItemVo.class); 
		output = new Output(1, 40960); 
	}
	
	
	@Override
	public void writer() {
		while(true) {
			try {
				Object obj = getArrayBlockingQueue().take();
				if(NullObject.isType(obj)) break;
				Count.atomicinteger.incrementAndGet();
				sendMQ((VshopDistributionItemVo)obj, DateUtil.getCurTime(), 1);
//				sendMQ((VshopDistributionItemVo)obj);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public  void sendMQ(VshopDistributionItemVo vo,long currentDate,int status){
		JSONObject jo = new JSONObject();
		jo.put("id", vo.getId());
		jo.put("mshopId", vo.getVshopId());
		jo.put("itemId", vo.getNewItemId());
		jo.put("status", vo.getStatus());
		jo.put("pshopId", vo.getPshopId());
		jo.put("userId", vo.getNewUserId());
		jo.put("sendTime", new Date().getTime());
		jo.put("currentDate", currentDate);
		SendResult send = mqPushProducer.send(jo.toJSONString(), "TagPlusProduct");
	}
	
	public  void sendMQ(VshopDistributionItemVo vo){
		kryo.writeObject(output, vo); 
		byte[] bb = output.toBytes(); 
		
		

		try {
			RabbitmqUtil.send(bb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		output.clear();
	}
	
	
	
}
