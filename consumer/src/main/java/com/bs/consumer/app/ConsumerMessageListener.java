package com.bs.consumer.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bs.messaging.app.SkinEvent;
import com.bs.messaging.app.BlackVenomEvent;

@Component
public class ConsumerMessageListener {
	
	public static int count = 0;

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);
    
    
    public ConsumerMessageListener() {
    	
    }

    public void handleHelloMessage(SkinEvent helloMessage) throws IOException {
    	File file = new File("g:/a.txt");
        LOG.debug("Received HelloMessage: {}", helloMessage.toString());
        System.out.println("Received HelloMessage: {}" + helloMessage.toString());
        PrintWriter out = null; 
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("g:/a.txt", true)));
            count ++;
            out.println(String.valueOf(count) + new Date().toString() + "\n");
            out.flush();
            //Thread.sleep(50);
          }
        catch(Exception e ) {
        	out.close();
        }
    }

    public void handleProductBoughtMessage(BlackVenomEvent pm) throws IOException {
        LOG.debug("Received ProductBoughtEventMessage: {}", pm.toString());
        System.out.println("Received ProductBoughtEventMessage: {}" + pm.toString());
        FileWriter fw = null;
        PrintWriter out = null; 
        try {
        	out = new PrintWriter(new BufferedWriter(new FileWriter("g:/a.txt", true)));
            count ++;
            out.println(String.valueOf(count) + new Date().toString() + "\n");
            out.flush();
           // Thread.sleep(50);
          }
        catch(Exception e ) {
        	out.close();
        }
    }
}
