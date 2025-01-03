package com.dahua.messaging.controller;

import com.dahua.messaging.request.RegisterUserRequest;
import com.dahua.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//-----------------------------------------ENTRY POINT------------------------------
@RestController
public class UserController { //This is where all request will be entering and will be response

    @Autowired UserService userService;

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
}
