package com.duqi.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "user_role")
public class UserRole extends AbstractAuditBase implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  @ManyToOne
  @JoinColumn
  public User user;

  @ManyToOne
  @JoinColumn
  public Role role;

  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
  }
}
