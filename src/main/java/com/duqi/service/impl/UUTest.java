package com.duqi.service.impl;

import com.duqi.entity.User;
import com.duqi.entity.UserRole;
import com.duqi.repository.RoleRepository;
import com.duqi.repository.UserRepository;
import com.duqi.repository.UserRoleRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: dengyong
 * @Date: 2021/02/24/15:05
 * @Description:
 */
@Component
public class UUTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRoleRepository userRoleRepository;

  @Transactional(propagation= Propagation.REQUIRED)
  public void test123(String id) {
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
    int i = 1 / 0;
//    if (true){
//      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//    }
  }
}
