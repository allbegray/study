package com.jo.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public abstract class JmsConnectionFactory {

	public ActiveMQConnectionFactory getConnectionFactory() {
		return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
	}

}
