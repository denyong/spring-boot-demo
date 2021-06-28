package com.duqi.configuration.lock;

/**
 * @Auther: dengyong
 * @Date: 2021/02/25/15:56
 * @Description:
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="goods_store")
public class GoodsStore implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  private String code;

  @Column
  private int store;

}