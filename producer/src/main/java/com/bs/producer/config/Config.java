package com.bs.producer.config;


import javax.jms.ConnectionFactory;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.SpringCamelContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bs.messaging.infrastrucutre.JsonMessageSerializer;

@Configuration
@ComponentScan
public class Config {
    @Value("tcp://localhost:61616")
    private String brokerURL;

    @Value("coreActivemq")
    private String brokerName;

    private RoutesBuilder routeBuilder = new RouteBuilder() {
        @Override
        public void configure() throws Exception {

        }
    };

    @Bean
    public JsonMessageSerializer messageSerializer() {
        return new JsonMessageSerializer();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CamelContext createCamelContext(ActiveMQComponent activeMQComponent) throws Exception {
        CamelContext camelContext = new SpringCamelContext();
        camelContext.addRoutes(routeBuilder);
        camelContext.addComponent(brokerName, activeMQComponent);
        return camelContext;
    }

    @Bean
    public ProducerTemplate producerTemplate(CamelContext camelContext) {
        return camelContext.createProducerTemplate();
    }

    @Bean
    public ActiveMQConnectionFactory coreConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerURL);
        connectionFactory.setUseCompression(true);
        return connectionFactory;
    }

    @Bean
    public JmsConfiguration coreJmsConfig(ActiveMQConnectionFactory coreConnectionFactory) {
        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        ConnectionFactory connectionFactory = new PooledConnectionFactory(coreConnectionFactory);
        jmsConfiguration.setConnectionFactory(connectionFactory);
        return jmsConfiguration;
    }
    
    
    @Bean
    public ActiveMQComponent coreActivemq(JmsConfiguration coreJmsConfig) {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setConfiguration(coreJmsConfig);
        return component;
    }

}
