package com.bs.messaging.infrastrucutre;

import com.bs.messaging.app.JsonMessage;

public interface MessageGateway {
    void sendMessage(String destination, JsonMessage jsonMessage);
}
