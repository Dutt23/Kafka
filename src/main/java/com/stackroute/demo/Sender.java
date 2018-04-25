package com.stackroute.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.stackroute.domain.Restaurant;

public class Sender {
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(Sender.class);

		  @Autowired
		  private KafkaTemplate<String, String> kafkaTemplate;
		  @Autowired
		  private KafkaTemplate<String, Restaurant> kafkaTemplateRestaurant;
		  
		  public void send(String topic, String payload) {
		    LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		    kafkaTemplate.send(topic, payload);
		  }
		  public void send1(String topic, Restaurant payload) {
			    LOGGER.info("sending payload='{}' to topic='{}'", payload.getName(), topic);
		    
			    kafkaTemplateRestaurant.send(topic, payload);
			  }
		  
		}



