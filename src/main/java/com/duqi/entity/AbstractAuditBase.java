package com.duqi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * @author dengyong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractAuditBase {

  @CreatedDate
  @Column(updatable = false)
  @JsonIgnore
  private Instant createdAt;

  @LastModifiedDate
  @JsonIgnore
  private Instant updatedAt;

  @CreatedBy
  @Column(updatable = false)
  @JsonIgnore
  private String createdBy;

  @LastModifiedBy
  @JsonIgnore
  private String updatedBy;
}
