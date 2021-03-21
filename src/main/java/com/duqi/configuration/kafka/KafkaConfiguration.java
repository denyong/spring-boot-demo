package com.duqi.configuration.kafka;

import com.duqi.constant.KafkaConsts;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @Auther: dengyong
 * @Date: 2021/03/20/19:17
 * @Description:
 */
@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
@EnableKafka
@AllArgsConstructor
public class KafkaConfiguration {

  private final KafkaProperties kafkaProperties;

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setConcurrency(Integer.valueOf(KafkaConsts.PARTITION.getValue()));
    factory.setBatchListener(true);
    factory.getContainerProperties().setPollTimeout(3000);
    return factory;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  @Bean("ackContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    factory.setConcurrency(Integer.valueOf(KafkaConsts.PARTITION.getValue()));
    return factory;
  }
}
