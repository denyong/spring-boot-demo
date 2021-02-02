package com.duqi.security.config;

import static java.util.Collections.singletonList;

import com.duqi.security.common.constants.SecurityConstants;
import com.duqi.security.exception.JWTAccessDeniedHandler;
import com.duqi.security.exception.JWTAuthenticationEntryPoint;
import com.duqi.security.filter.JWTAuthorizationFilter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private RedisTemplate redisTemplate;

  public SecurityConfiguration(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Autowired
  @Qualifier(value = "userDetailsServiceImpl")
  public UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
        // 禁用 CSRF 因为不使用session
        .csrf().disable()
        // 开始过滤请求
        .authorizeRequests()
        // 设置swagger路径任意访问
        .antMatchers(SecurityConstants.SWAGGER_WHITELIST).permitAll()
        // 设置登录接口任意访问
        .antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_WHITELIST).permitAll()

        .antMatchers(SecurityConstants.FILTER_ALL).authenticated()
        .antMatchers(HttpMethod.DELETE, SecurityConstants.FILTER_ALL).hasRole("ADMIN")
        // 其他都放行了
        .anyRequest().permitAll()
        .and()
//        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(),redisTemplate))
        // 不需要session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        // 没有携带token或者token无效
        .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
        //添加无权限时的处理
        .accessDeniedHandler(new JWTAccessDeniedHandler());
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(singletonList("*"));
    // configuration.setAllowedOriginPatterns(singletonList("*"));
    configuration.setAllowedHeaders(singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
    configuration.setExposedHeaders(singletonList(SecurityConstants.TOKEN_HEADER));
    configuration.setAllowCredentials(false);
    configuration.setMaxAge(3600l);
    configuration.applyPermitDefaultValues();

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
