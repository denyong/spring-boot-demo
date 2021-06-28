package com.duqi.security.service;

import com.duqi.entity.User;
import com.duqi.security.common.constants.SecurityConstants;
import com.duqi.security.common.utils.CurrentUserUtils;
import com.duqi.security.common.utils.JwtTokenUtils;
import com.duqi.security.common.utils.JwtUser;
import com.duqi.security.model.LoginRequest;
import com.duqi.service.UserService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

  private final UserService userService;

  private final RedisTemplate redisTemplate;

  private final CurrentUserUtils currentUserUtils;

  public String getToken(LoginRequest loginRequest) {
    User user = userService.findByUsername(loginRequest.getUsername());
    if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("The password is not correct.");
    }
    JwtUser jwtUser = new JwtUser(user);
    if (!jwtUser.isEnabled()) {
      throw new BadCredentialsException("User is forbidden to log in");
    }
    List<String> authorities = jwtUser.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
    String token = JwtTokenUtils
        .createToken(user.getUsername(), user.id.toString(), authorities,
            loginRequest.getRememberMe());

    long expiration =
        loginRequest.getRememberMe() ? SecurityConstants.EXPIRATION_REMEMBER
            : SecurityConstants.EXPIRATION;
    redisTemplate.opsForValue().set(user.id.toString(), token, expiration, TimeUnit.SECONDS);
    return token;
  }

  public void deleteTokenFromRedis() {
    redisTemplate.delete(String.valueOf(currentUserUtils.getCurrentUser().getId()));
  }
}
