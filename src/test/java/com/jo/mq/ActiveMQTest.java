package com.jo.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class ActiveMQTest {
	
	@Test
	public void textMessageTest() {
		
		JmsConnectionFactory factory = new JmsConnectionFactory() {

			@Override
			public ActiveMQConnectionFactory getConnectionFactory() {
				return super.getConnectionFactory();
			}
			
		};
		
		ActiveMQConnectionFactory connectionFactory = factory.getConnectionFactory();
		String queueName = "TEST.FOO";
		
		String testMessage = "테스트 메시지";
		
		HelloWorldProducer producer = new HelloWorldProducer(connectionFactory, queueName);
		HelloWorldConsumer consumer = new HelloWorldConsumer(connectionFactory, queueName);
		
		producer.send(testMessage);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String message = consumer.receive();
		
		Assert.assertTrue(testMessage.equals(message));
		
	}

}
