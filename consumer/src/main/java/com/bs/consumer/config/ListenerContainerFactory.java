package com.bs.consumer.config;

import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.component.jms.MessageListenerContainerFactory;
import org.springframework.jms.listener.AbstractMessageListenerContainer;

public  class ListenerContainerFactory implements MessageListenerContainerFactory {

	@Override
	public AbstractMessageListenerContainer createMessageListenerContainer(
			JmsEndpoint endpoint) {
		return new TestDMLC();
	}
}
