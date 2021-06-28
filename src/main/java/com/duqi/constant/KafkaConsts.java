package com.duqi.constant;

import lombok.Getter;

/**
 * @Auther: dengyong
 * @Date: 2021/03/20/18:48
 * @Description:
 */
@Getter
public enum KafkaConsts {
  /**
   * 默认分区大小
   */
  PARTITION("DEFAULT_PARTITION_NUM", "3"),

  /**
   * Topic 名称
   */
  TOPICN("TOPIC_NAME", "test");

  private String key;
  private String value;

  KafkaConsts(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
