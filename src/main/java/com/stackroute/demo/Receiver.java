package com.stackroute.demo;

	

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stackroute.domain.Restaurant;


public class Receiver {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Receiver.class);
  @Autowired
  KafkaTest kafkaTest;

  //For testing CountdownLatch is added. Allows a signal when message is received.
  private CountDownLatch latch = new CountDownLatch(1);

//Counter for seeing when message is called
  public CountDownLatch getLatch() {
    return latch;
  }
//Change the topics to partition different or add more methods.
  //@KafkaListener(id = "foo", topics = "#{'${topicOne:annotated1,foo}'.split(',')}")
  
  @KafkaListener(topics = "${kafka.topic.helloworld1}")
  public void receive(String payload) {
    LOGGER.info("received payload='{}'", payload);
    latch.countDown();
    System.out.println("This is " +payload);
  }
 
  @KafkaListener(topics = "${kafka.topic.helloworld}")
  public void receive1(Restaurant payload) {
    LOGGER.info("received payload='{}'", payload);
    //Way to figure out if message it received or not
    latch.countDown();
    System.out.println(payload.getId());
    kafkaTest.Display(payload);
//    CustomeDeserializer customeDeserializer = new CustomeDeserializer();
    //customeDeserializer.deserialize("This", payload);
    
  }
}
