package com.bs.consumer.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bs.messaging.app.SkinEvent;
import com.bs.messaging.app.BlackVenomEvent;

@Component
public class ConsumerMessageListener {
	
	public static AtomicInteger count = new AtomicInteger(0);

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);
    
    
    public ConsumerMessageListener() {
    	
    }

    public void handleHelloMessage(SkinEvent helloMessage) throws IOException {
    	//File file = new File("g:/a.txt");
        LOG.debug("Received HelloMessage: {} + count ", helloMessage.toString()+"  " +count);
        System.out.println("Received HelloMessage: {}" + helloMessage.toString()+"  " +count);
        PrintWriter out = null; 
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("g:/a.txt", true)));
            count.getAndAdd(1);
            out.println(String.valueOf(count.get()) + new Date().toString() + "\n");
            out.flush();

          }
        catch(Exception e ) {
        	out.close();
        }
    }

    public void handleProductBoughtMessage(BlackVenomEvent pm) throws IOException {
        LOG.debug("Received ProductBoughtEventMessage: {}", pm.toString()+"  " +count);
        System.out.println("Received ProductBoughtEventMessage: {}" + pm.toString()+"  " +count);
        FileWriter fw = null;
        PrintWriter out = null; 
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("g:/a.txt", true)));
        	 count.getAndAdd(1);
            out.println(String.valueOf(count.get()) + new Date().toString() + "\n");
            out.flush();
          }
        catch(Exception e ) {
        	out.close();
        }
    }
}
