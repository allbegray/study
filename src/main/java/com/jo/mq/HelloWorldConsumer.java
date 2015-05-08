package com.jo.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldConsumer implements ExceptionListener {
	
	public HelloWorldConsumer(ActiveMQConnectionFactory connectionFactory, String queueName) {
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
	
	public String receive() {
		
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			connection = this.getConnectionFactory().createConnection();
			connection.start();
			
			connection.setExceptionListener(this);
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(getQueueName());
			
			consumer = session.createConsumer(destination);
			
			Message message = consumer.receive(1 * 1000);
			if (message instanceof TextMessage) {
				return ((TextMessage) message).getText();
			}
			
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			if (consumer != null) try { consumer.close(); } catch (Exception e) {}
			if (session != null) try { session.close(); } catch (Exception e) {}
			if (connection != null) try { connection.close(); } catch (Exception e) {}
		}
		
		return null;
	}

	@Override
	public void onException(JMSException exception) {
		System.out.println("JMS Exception occured.  Shutting down client.");		
	}

}
