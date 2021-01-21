package com.duqi.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 定义返回请求响应格式 T为泛型
 *
 * @param <T>
 */
@Getter
@Setter
@Builder
public class BackBean<T> {

  boolean status;
  T data;
  String message;
  String exception;
  String exceptionMessage;
}