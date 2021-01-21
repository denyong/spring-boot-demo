package com.duqi.aop;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationTicket {

  private static final ThreadLocal<AuthenticationTicket> holder = new ThreadLocal();

  @Getter
  @Setter
  private String pin;

  public AuthenticationTicket(String pin) {
    this.pin = pin;
  }

  public static void setTicket(AuthenticationTicket ticket) {
    holder.set(ticket);
  }

  public static AuthenticationTicket getTicket() {
    return (AuthenticationTicket) holder.get();
  }

  public static void remove() {
    holder.remove();
  }
}
