package com.duqi.security.filter;

import com.duqi.security.common.constants.SecurityConstants;
import com.duqi.security.common.utils.JwtTokenUtils;
import com.duqi.security.exception.TokenIsExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 用户鉴权
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  @Autowired
  private RedisTemplate redisTemplate;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
      RedisTemplate redisTemplate) {
    super(authenticationManager);
    this.redisTemplate = redisTemplate;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String tokenHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);
    // 如果请求头中没有Authorization信息则直接放行了
    if (tokenHeader == null || !tokenHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
     SecurityContextHolder.clearContext();
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = null;

    // 如果请求头中有token，则进行解析，并且设置认证信息
    try {
      String token = tokenHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

      boolean expiration = JwtTokenUtils.isExpiration(token);
      if (expiration) {
        throw new TokenIsExpiredException("token超时了");
      }

      String previousToken = (String) redisTemplate.opsForValue().get(JwtTokenUtils.getId(token));
      if (!token.equals(previousToken)) {
         SecurityContextHolder.clearContext(); // 清空用户信息
        chain.doFilter(request, response);
        return;
      }

      authentication = JwtTokenUtils.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e) {
      //返回json形式的错误信息
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json; charset=utf-8");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      String reason = "统一处理，原因：" + e.getMessage();
      response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
      response.getWriter().flush();
      return;
    }
    // chain.doFilter(request, response);
    super.doFilterInternal(request, response, chain);
  }
}
