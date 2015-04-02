package com.bs.messaging.infrastrucutre;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class JsonMessageSerializer {

    @Autowired
    private ObjectMapper objectMapper;

    public String getJson(Serializable obj) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T extends Serializable> T getObject(String json, Class<T> clazz) {
        T result = null;
        try {
            objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
