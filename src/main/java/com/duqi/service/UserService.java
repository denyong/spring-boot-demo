package com.duqi.service;

import com.duqi.entity.User;
import com.duqi.security.model.representation.UserRepresentation;
import com.duqi.security.model.request.UserRegisterRequest;
import com.duqi.security.model.request.UserUpdateRequest;
import org.springframework.data.domain.Page;

public interface UserService {

  // 添加用户
  User save(UserRegisterRequest userRegisterRequest);

  // 更新用户
  User update(UserUpdateRequest userUpdateRequest);

  // 查询所有用户(分页)
  Page<UserRepresentation> getAll(int pageNum, int pageSize);

  // 删除用户
  void delete(String username);

  // 查询单个用户
  User findByUsername(String username);

  // 检查密码
  boolean check(String currentPassword, String password);

  // 查询当前请求用户
  User find(String userName);
}
