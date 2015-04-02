package com.bs.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
        props.setLocations(new Resource[]{new ClassPathResource("app.properties")});
        props.setIgnoreUnresolvablePlaceholders(true);
        return props;
    }
}
