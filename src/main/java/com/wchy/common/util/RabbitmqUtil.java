package com.wchy.common.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class RabbitmqUtil {
	private static final String EXCHANGE_NAME = "hello-logs-push";
	private static Channel channel = null;
	
	private static final String QUEUE_NAME = "sheng-push-test3";
	static{
		
		  ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("10.69.42.84");
		    factory.setPort(5672);
		    factory.setVirtualHost("/bs-arch");
		    factory.setUsername("bsarch");
		    factory.setPassword("bsarch");
		    
			try {
				Connection connection = factory.newConnection();
			    channel = connection.createChannel();
			    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
	
	
	
	public static  void send(byte [] data) throws IOException{
		channel.basicPublish("", QUEUE_NAME, null, data);
	}
	
}
