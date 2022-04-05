package com.duqi.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dengyong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User extends AbstractAuditBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  @Column(nullable = false)
  @NotBlank
  private String fullName;

  @Column(nullable = false)
  @NotBlank
  public String username;
  @Column(nullable = false)
  @NotBlank
  public String password;
  @Column(columnDefinition = "tinyint(1) default 1")
  public Boolean enabled; // 用户是否被删除/可见

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserRole> userRoles = new ArrayList<>();

}
