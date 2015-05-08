package com.jo.mq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldProducer {
	
	public HelloWorldProducer(ActiveMQConnectionFactory connectionFactory, String queueName) {
		super();
		this.connectionFactory = connectionFactory;
		this.queueName = queueName;
	}

	private ActiveMQConnectionFactory connectionFactory;
		public ActiveMQConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}
	
	private String queueName;
		public String getQueueName() {
			return queueName;
		}
		
	public void send(String message) {

		Connection connection = null;
		Session session = null;
		try {
			connection = this.getConnectionFactory().createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(getQueueName());

			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage textMessage = session.createTextMessage(message);
			producer.send(textMessage);

		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				try {
					session.close();
				} catch (Exception e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
				}
		}

	}

}
