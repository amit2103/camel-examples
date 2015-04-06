package com.bs.producer;

import com.bs.messaging.app.SkinEvent;
import com.bs.messaging.app.BlackVenomEvent;
import com.bs.messaging.infrastrucutre.MessageGateway;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Component
public class ProducerApp {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);

    @Autowired
    private MessageGateway gateway;


    public static void main(String[] args) throws Exception {
        LOG.info("Starting the producer...");

        ApplicationContext springContext = new SpringApplicationBuilder(ProducerApp.class).run(args);
        ProducerApp producerApp = springContext.getBean(ProducerApp.class);
        producerApp.schedule();

    }

    private void schedule() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(20);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                gateway.sendMessage("coreActivemq:hello.in", new SkinEvent("Hello World"));
                gateway.sendMessage("coreActivemq:hello.in", new BlackVenomEvent(35D, "Product1"));
            }
        }, 10, 1, TimeUnit.MILLISECONDS);

    }
}