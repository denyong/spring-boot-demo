package com.duqi.security.common.utils;

import com.duqi.entity.User;
import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class JwtUser implements UserDetails {

  private Integer id;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;


  // 写一个能直接使用user创建jwtUser的构造器
  public JwtUser(User user) {
    id = user.getId();
    username = user.getUsername();
    password = user.getPassword();
    authorities = user.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
