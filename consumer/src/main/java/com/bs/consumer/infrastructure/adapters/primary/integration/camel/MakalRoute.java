package com.bs.consumer.infrastructure.adapters.primary.integration.camel;

import com.bs.consumer.app.ConsumerMessageListener;
import com.bs.messaging.app.SkinEvent;
import com.bs.messaging.app.BlackVenomEvent;
import com.bs.messaging.infrastrucutre.JsonMessageSerializer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MakalRoute extends RouteBuilder {

    private static final String IN_QUEUE = "hello.in";
    private static final String MESSAGE_TYPE = "MessageType";

    @Value("coreActivemq")
    private String jmsBroker;

    @Autowired
    private JsonMessageSerializer serializer;

    @Autowired
    private ConsumerMessageListener messageHandlerApplicationService;

    @Override
    public void configure() throws Exception {
        from(jmsBroker + ":" + IN_QUEUE +"?concurrentConsumers=30&messageListenerContainerFactoryRef=listnerFactory&jmsOperations=#customTemplate")
                .to(jmsBroker + ":" + "hello.out" +"?concurrentConsumers=30&jmsOperations=#customTemplate")
                .choice()
                    .when(header(MESSAGE_TYPE).isEqualTo(SkinEvent.class.getName()))
                        .unmarshal().json(JsonLibrary.Gson, SkinEvent.class)
                        .bean(messageHandlerApplicationService, "handleHelloMessage")
                    .when(header(MESSAGE_TYPE).isEqualTo(BlackVenomEvent.class.getName()))
                        .unmarshal().json(JsonLibrary.Gson, BlackVenomEvent.class)
                        .bean(messageHandlerApplicationService, "handleProductBoughtMessage");
    }
}
