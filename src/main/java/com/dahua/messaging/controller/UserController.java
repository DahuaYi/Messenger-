package com.dahua.messaging.controller;

import com.dahua.messaging.request.ActivateUserRequest;
import com.dahua.messaging.request.RegisterUserRequest;
import com.dahua.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//-----------------------------------------ENTRY POINT------------------------------
//1)UserController - 一切的起源地
//2)RegisterUserRequest - turn json file to a class (JSON Deserialization)
//3)UserService - Logic逻辑（validate user information when register)
//    -4)UserDTO - （暂时storing）用于即将存储在数据库的用户信息
//5)UserDAO - Action（把用户信息储存到数据库）

//This is where all request will be entering and will be response
@RestController
public class UserController {

    @Autowired UserService userService;// APIs

    @PostMapping("/users/register")
    public void register(@RequestBody RegisterUserRequest registerUserRequest) throws Exception { // request response
        System.out.println(registerUserRequest);
        this.userService.register(registerUserRequest.getUsername(),
                                  registerUserRequest.getPassword(),
                                  registerUserRequest.getRepeatPassword(),
                                  registerUserRequest.getNickname(),
                                  registerUserRequest.getAddress(),
                                  registerUserRequest.getEmail(),
                                  registerUserRequest.getGender());

    }

    @PostMapping("/users/activate")
    public void activate(@RequestBody ActivateUserRequest activateUserRequest) throws Exception {
        this.userService.activate(activateUserRequest.getUsername(), activateUserRequest.getValidationCode());
    }

    @PostMapping("/users/resendValidationCode")
    public void resendValidationCode(@RequestParam String email) throws Exception{
        this.userService.resendValidationCode(email);
    }

}
