package com.bs.consumer;

import com.bs.consumer.app.ConsumerMessageListener;
import com.bs.consumer.config.CamelConfig;
import com.bs.consumer.config.PropertiesConfig;

import org.apache.activemq.util.StopWatch;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({CamelConfig.class, PropertiesConfig.class})
public class CalvinKlienConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(CalvinKlienConsumer.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Starting the consumer...");
        ApplicationContext springContext = new SpringApplicationBuilder(CalvinKlienConsumer.class)
                .showBanner(false)
                .run(args);
        LOG.info("Consumer has been started");
        springContext.getBean(Main.class).run();
       final StopWatch stopWatch = new StopWatch();
       stopWatch.restart();
       Runtime.getRuntime().addShutdownHook(new Thread() {
    	   public void run() {
    		   System.out.println("Processed messages " + ConsumerMessageListener.count);
    		   System.out.println(stopWatch.stop());
    	   }
    	   
       });
    }
}