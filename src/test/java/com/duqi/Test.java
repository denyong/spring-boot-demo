package com.duqi;


import java.util.Objects;

public class Test extends Object {

  private static volatile Test test;

  private Test() {
  }

  public static Test getInstance() {
    if (test == null) {
      synchronized (Test.class) {
        if (test == null) {
          test = new Test();
        }
      }
    }
    return test;
  }
}
