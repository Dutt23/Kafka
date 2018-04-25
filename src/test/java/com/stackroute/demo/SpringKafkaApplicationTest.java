package com.stackroute.demo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.demo.KafkaMessageApplication;
import com.stackroute.demo.KafkaTest;
import com.stackroute.demo.Receiver;
import com.stackroute.demo.Sender;
import com.stackroute.domain.Restaurant;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SpringKafkaApplicationTest {

  private static final String HELLOWORLD_TOPIC = "helloworld.t";

//  @ClassRule
//  public static KafkaEmbedded embeddedKafka =
//      new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

  @Autowired
  private Receiver receiver;

 @Autowired
 private Sender sender;

 @Autowired
KafkaTest kafkaTest;
 
 @Autowired
 private Restaurant restaurant;
  
//@Test
//  public void testReceiveString() throws Exception {
//    sender.send(HELLOWORLD_TOPIC, "Hello Spring Kafka! This is from first topic");
//    kafkaTest.sendMessage();
//    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//  }

@Test
public void testReceiveObjects() throws Exception {

  restaurant.setLocation("Florida");
  restaurant.setName("Kedar");
  restaurant.setId(1);
  restaurant.getLocation();
  restaurant.getId();
  restaurant.getName();
  sender.send1(HELLOWORLD_TOPIC, restaurant);
//kafkaTest.Display(restaurant);
  receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
  assertThat(receiver.getLatch().getCount()).isEqualTo(1);
}
}
