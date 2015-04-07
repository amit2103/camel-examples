package com.bs.consumer.config;

import javax.jms.Session;

import org.springframework.jms.connection.JmsResourceHolder;

class LocallyExposedJmsResourceHolder extends JmsResourceHolder {

	public LocallyExposedJmsResourceHolder(Session session) {
		super(session);
	}

}