package com.duqi.security.common.utils;

import com.duqi.security.common.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtTokenUtils {

  /**
   * 生成足够的安全随机密钥，以适合符合规范的签名
   */
  private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(
      SecurityConstants.JWT_SECRET_KEY);
  private static final SecretKey SECRET = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);


  // 创建token
  public static String createToken(String username, String id, List<String> roles,
      boolean isRememberMe) {
    Map map = new HashMap();
    map.put("openId","aaaa");
    long expiration =
        isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
    return Jwts.builder()
        .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
        .signWith(SECRET, SignatureAlgorithm.HS256)
        .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles)) // 添加自定义的claim key-value
        .setId(id)
        .setIssuer(SecurityConstants.ISS)
        .setIssuedAt(new Date())
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
        .compact();
  }

  public static String getId(String token) {
    Claims claims = getClaims(token);
    return claims.getId();
  }

  // 是否已过期
  public static boolean isExpiration(String token) {
    try {
      return getClaims(token).getExpiration().before(new Date());
    } catch (ExpiredJwtException e) {
      return true;
    }
  }

  public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = getClaims(token);
    List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
    String userName = claims.getSubject();
    return new UsernamePasswordAuthenticationToken(userName, token, authorities);
  }

  private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
    String role = (String) claims.get(SecurityConstants.ROLE_CLAIMS);
    return Arrays.stream(role.split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  // 私钥解密token信息
  private static Claims getClaims(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody();
  }
}
