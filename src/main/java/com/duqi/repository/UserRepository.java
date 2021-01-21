package com.duqi.repository;

import com.duqi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String>,
    JpaSpecificationExecutor<User> {

  // 根据用户名获取用户
  Optional<User> findByUsername(String username);

  // 删除用户
  @Modifying
  @Transactional(rollbackFor = Exception.class)
  void deleteByUsername(String userName);

  boolean existsByUsername(String username);

}
