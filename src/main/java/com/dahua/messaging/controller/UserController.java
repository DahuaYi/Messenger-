package com.dahua.messaging.controller;

import com.dahua.messaging.request.ActivateUserRequest;
import com.dahua.messaging.request.RegisterUserRequest;
import com.dahua.messaging.request.UserLoginRequest;
import com.dahua.messaging.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

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

    @PostMapping("/users/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        var loginToken = this.userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword()); //Get token
        ResponseCookie responseCookie = ResponseCookie.from("loginToken", loginToken)
                .path("/")
                .maxAge(Duration.ofDays(14))
                .build();
        return ResponseEntity.ok()
                .header(SET_COOKIE, responseCookie.toString())
                .build();
    }

    @PostMapping("/users/logout")
    public ResponseEntity<Void> logout(@CookieValue String loginToken) throws Exception {
        this.userService.logout(loginToken);
        ResponseCookie responseCookie = ResponseCookie.from("loginToken", "")
                .maxAge(Duration.ofDays(0))
                .build();
        return ResponseEntity.ok()
                .header(SET_COOKIE, responseCookie.toString())
                .build();
    }

}
