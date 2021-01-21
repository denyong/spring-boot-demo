package com.duqi.security.model.request;


import com.duqi.entity.User;
import com.duqi.security.common.validator.FullName;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

  @NotBlank // 只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
  @FullName
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String fullName;

  public User toUser() {
    return User.builder()
        .username(this.getUsername())
        .fullName(this.getFullName())
        .enabled(true).build();
  }
}
