package com.duqi.controller;

import com.duqi.entity.User;
import com.duqi.security.model.representation.UserRepresentation;
import com.duqi.security.model.request.UserUpdateRequest;
import com.duqi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户控制层")
//@Scope("prototype") //开启多例模式
public class UserController {

  @Autowired
  UserService userService;
  private static int num = 1;
  @GetMapping("/getAllUsers")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
  @ApiOperation("获取所有用户的信息（分页）")
  public ResponseEntity<Page<UserRepresentation>> getAllUser(
      @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    System.out.println(num++);
    Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);

    return ResponseEntity.ok().body(allUser);
  }

  @PutMapping("/updateUser")
  @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
  @ApiOperation("更新用户")
  public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
    User user = userService.update(userUpdateRequest);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/deleteUser")
  @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
  @ApiOperation("根据用户名删除用户")
  public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
    userService.delete(username);
    return ResponseEntity.ok().build();
  }
}
