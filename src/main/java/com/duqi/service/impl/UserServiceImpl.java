package com.duqi.service.impl;


import com.duqi.constant.RoleType;
import com.duqi.entity.Role;
import com.duqi.entity.User;
import com.duqi.entity.UserRole;
import com.duqi.exception.RoleNotFoundException;
import com.duqi.exception.UserNameAlreadyExistException;
import com.duqi.exception.UserNameNotFoundException;
import com.duqi.repository.RoleRepository;
import com.duqi.repository.UserRepository;
import com.duqi.repository.UserRoleRepository;
import com.duqi.security.model.representation.UserRepresentation;
import com.duqi.security.model.request.UserRegisterRequest;
import com.duqi.security.model.request.UserUpdateRequest;
import com.duqi.service.UserService;
import com.google.common.collect.ImmutableMap;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRoleRepository userRoleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
  public User save(UserRegisterRequest userRegisterRequest) throws RuntimeException {
    ensureUserNameNotExist(userRegisterRequest.getUsername());
    User user = userRegisterRequest.toUser();
    user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
    User saveUser = userRepository.save(user);
    // 给用户绑定两个角色：用户和管理者
    Role studentRole = roleRepository.findByName(RoleType.USER.getName()).orElseThrow(
        () -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
    Role managerRole = roleRepository.findByName(RoleType.MANAGER.getName()).orElseThrow(
        () -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName())));

    userRoleRepository.save(new UserRole(user, studentRole));
    userRoleRepository.save(new UserRole(user, managerRole));
    return saveUser;
  }

  @Override
  public void delete(String username) {
    if (!userRepository.existsByUsername(username)) {
      throw new UserNameNotFoundException(ImmutableMap.of("username", username));
    }
    userRepository.deleteByUsername(username);
  }

  @Override
  public User update(UserUpdateRequest userUpdateRequest) {
    User user = findByUsername(userUpdateRequest.getUsername());
    if (Objects.nonNull(user)) {
      user.builder().fullName(userUpdateRequest.getFullName())
          .enabled(userUpdateRequest.getEnabled());
      return userRepository.save(user);
    }
    return null;
  }

  @Override
  public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
    return userRepository.findAll(PageRequest.of(pageNum, pageSize))
        .map(User::toUserRepresentation);
  }

  // 校验密码
  public boolean check(String currentPassword, String password) {
    return this.bCryptPasswordEncoder.matches(currentPassword, password);
  }

  // 查询用户
  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("no such user!"));
  }

  // 查询该用户是否存在
  private void ensureUserNameNotExist(String username) {
    boolean exist = userRepository.findByUsername(username).isPresent();
    if (exist) {
      throw new UserNameAlreadyExistException(ImmutableMap.of("username", username));
    }
  }

  // 查询当前请求用户
  public User find(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of("username", username)));
  }

  @Transactional// (rollbackFor=Exception.class)
  public void test(String id) {
    User user = new User();
    user.setEnabled(false);
    user.setPassword("rqwerf");
    user.setFullName("fgfdg");
    user.setUsername("ddfgsdfg");
    user.setId(7);
    ArrayList<UserRole> objects = new ArrayList<>();
    objects.add(new UserRole());
    user.setUserRoles(objects);
    userRepository.save(user);
//    int i = 1 / 0 ;
    if (true){
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }
}
