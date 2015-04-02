package com.bs.producer.infrastructure;

import com.bs.messaging.app.JsonMessage;
import com.bs.messaging.infrastrucutre.JsonMessageSerializer;
import com.bs.messaging.infrastrucutre.MessageGateway;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SerializedMessageGateway implements MessageGateway {

    private static final Logger LOG = LoggerFactory.getLogger(SerializedMessageGateway.class);
    private static final String MESSAGE_TYPE = "MessageType";

    @Autowired
    private JsonMessageSerializer messageSerializer;

    @Autowired
    private ProducerTemplate camelProducerTemplate;

    @Override
    public void sendMessage(String destination, JsonMessage jsonMessage) {
        Map<String, Object> headers = new HashMap<String, Object>();
        String json = messageSerializer.getJson(jsonMessage);
        headers.put(MESSAGE_TYPE, jsonMessage.getClass().getName());
        camelProducerTemplate.sendBodyAndHeaders(destination, json, headers);
        LOG.info("Sending message: {}", json);
    }
}
