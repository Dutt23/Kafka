package com.stackroute.demo;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.stackroute.domain.Restaurant;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class SenderConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public Map<String, Object> producerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	    return props;
  }
//  public byte[] serialize(String s, Object o) {
//
//      try {
//           ByteArrayOutputStream baos = new ByteArrayOutputStream();
//           ObjectOutputStream oos = new ObjectOutputStream(baos);
//           oos.writeObject(o);
//           oos.close();
//           byte[] b = baos.toByteArray();
//           return b;
//       } catch (IOException e) {
//           return new byte[0];
//       }
//   }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }
  
  

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
  
  @Bean
  public ProducerFactory<String, Restaurant> producerFactory1() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }
  @Bean
  public KafkaTemplate<String, Restaurant> kafkaTemplate1() {
    return new KafkaTemplate<>(producerFactory1());
  }

  @Bean
  public Sender sender() {
    return new Sender();
  }
  @Bean
  public KafkaTest kafkaTest() {
    return new KafkaTest();
  }
  @Bean
  public Restaurant restaurant() {
    return new Restaurant();
  }


}




