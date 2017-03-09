package com.wchy.common.util;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;

/**
 * 
 * @Description vshop推送消息 (店铺分销商品上下架，店铺状态以及信息变动)
 * @author guowenbo
 * @date 2017年1月12日 下午3:47:54
 */
@Slf4j
public class MQPushProducer {
	
	private String topic;
	
	private String namesrvAddr;
	
	private String group;
	
	private DefaultMQProducer producer = null;
	
	public void init(){
		/**
		 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ProducerGroupName需要由应用来保证唯一<br>
		 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
		 * 因为服务器会回查这个Group下的任意一个Producer
		 */
		log.info("MQPushProducer start ! topic: {},  namesrvAddr: {}, group: {}" ,
				topic, namesrvAddr, group);
		
		producer = new DefaultMQProducer(group);
		//producer.setInstanceName(instanceName);
		producer.setNamesrvAddr(namesrvAddr);  
		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		try {
			producer.start();
		} catch (MQClientException e) {
			log.error("vshop-MQPushProducer start fail ! {}", e.getMessage());
			e.printStackTrace();
		}
		log.debug("vshop-MQPushProducer start success!");
	}
	
	/**
	 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
	 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法  
	 */
	@PreDestroy
	public void shutdown(){
		producer.shutdown();
	}
	
	public SendResult send(String message,String tag){
		try {
			log.info("MQPushProducer:send mq message parameter message: {},topic: {},  tag: {}, namesrvAddr: {}, groupName: {}", message, topic, tag, namesrvAddr, group);
			Message msg;
			msg = new Message(topic,// topic
					tag,// tag
					message.getBytes("utf-8"));  //增加字符编码校验
			SendResult sendResult = producer.send(msg);
			log.debug("send mq message success!");
			return sendResult;
		} catch (Exception e) {
			log.error("MQPushProducer:send msg error: {}", e);
			return null;
		}
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
