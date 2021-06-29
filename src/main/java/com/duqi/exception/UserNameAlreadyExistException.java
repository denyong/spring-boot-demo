package com.duqi.exception;

import java.util.Map;


/**
 * @author dengyong
 */
public class UserNameAlreadyExistException extends BaseException {

  public UserNameAlreadyExistException(Map<String, Object> data) {
    super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
  }
}
