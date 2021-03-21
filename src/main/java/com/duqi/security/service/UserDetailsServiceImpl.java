package com.duqi.security.service;


import com.duqi.security.config.JwtUser;
import com.duqi.entity.User;
import com.duqi.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<User> byUsername = userRepository.findByUsername(s);
    if (byUsername.isPresent())
      return new JwtUser(byUsername.get());
    else
      return null;
  }
}
