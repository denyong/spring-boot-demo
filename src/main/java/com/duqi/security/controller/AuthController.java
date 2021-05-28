package com.duqi.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.duqi.security.model.LoginRequest;
import com.duqi.security.model.request.UserRegisterRequest;
import com.duqi.security.service.AuthService;
import com.duqi.service.UserService;
import com.duqi.utils.BackBean;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  private final AuthService authService;

  @PostMapping("/auth/login")
  @ApiOperation("用户登录")
  public BackBean<JSONObject> login(@RequestBody LoginRequest loginRequest) {

    try {
      JSONObject jsonObject = new JSONObject();
      String token = authService.getToken(loginRequest);
      jsonObject.put("Authorization", token);
      return BackBean.<JSONObject>builder().status(true).message("success").data(jsonObject)
          .build();
    } catch (Exception e) {
      return BackBean.<JSONObject>builder().status(false).message(e.getMessage())
          .exceptionMessage("error").build();
    }
  }


  @PostMapping("/auth/logout")
  @ApiOperation("退出")
  public ResponseEntity<Void> logout() {
    authService.deleteTokenFromRedis();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/auth/register")
  @ApiOperation("用户注册")
  public BackBean<String> save(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
    userService.save(userRegisterRequest);
    return BackBean.<String>builder().status(true).message("success").data("")
        .build();
  }

}