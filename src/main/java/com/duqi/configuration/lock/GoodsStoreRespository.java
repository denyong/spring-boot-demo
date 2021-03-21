package com.duqi.configuration.lock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存Respository
 * @author user
 *
 */
public interface GoodsStoreRespository extends JpaRepository<GoodsStore,String> {
  /**
   * 更新库存
   * @param code
   * @param store
   * @return
   */
  @Modifying
  @Transactional
  @Query("update GoodsStore gs set gs.store=gs.store-?2 where gs.code=?1")
  int updateStore(@Param("code") String code,@Param("store")Integer store);
}