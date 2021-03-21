//package com.duqi.configuration.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.transaction.KafkaTransactionManager;
//
//
//@Configuration
//@EnableKafka
//public class KafkaProducerConfig {
//
//  @Value("${spring.kafka.producer.bootstrap-servers}")
//  private String bootstrapServers;
//
//  @Value("${spring.kafka.producer.retries}")
//  private Integer retries;
//
//  @Value("${spring.kafka.producer.batch-size}")
//  private Integer batchSize;
//
//  @Value("${spring.kafka.producer.buffer-memory}")
//  private Integer bufferMemory;
//
//  @Value("${spring.kafka.producer.linger}")
//  private Integer linger;
//
//  private Map<String, Object> producerConfigs() {
//    Map<String, Object> props = new HashMap<>();
//    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//    props.put(ProducerConfig.RETRIES_CONFIG, retries);
//    props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
//    props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
//    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
//    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    return props;
//  }
//
//  private ProducerFactory<String, String> producerFactory() {
//    DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(
//        producerConfigs());
//        producerFactory.transactionCapable();
//        producerFactory.setTransactionIdPrefix("hous-");
//    return producerFactory;
//  }
//
//  @Bean
//  public KafkaTransactionManager transactionManager() {
//    KafkaTransactionManager manager = new KafkaTransactionManager(producerFactory());
//    return manager;
//  }
//
//  @Bean
//  public KafkaTemplate<String, String> kafkaTemplate() {
//    return new KafkaTemplate<String, String>(producerFactory());
//  }
//}
