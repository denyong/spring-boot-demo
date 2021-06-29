package com.duqi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dengyong
 */
@Data
@NoArgsConstructor
@Entity(name = "role")
public class Role extends AbstractAuditBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  public String name;
  public String description;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  @JsonIgnore
  public List<UserRole> userRoles = new ArrayList<>();

  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
