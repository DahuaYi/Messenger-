package com.dahua.messaging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// routing
@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello(@RequestParam(required = false) String name) { // null || "null" ?
        return "Hello, " + name + "!";
    }

    @PostMapping("/")
    public User.Detail helloPost(@RequestBody User user) {// Spring: JSON -> object in memory: JSON de-serialization // new User();user.setName("George111111112");
        return user.getDetail();
    } // Spring: object in memory -> JSON string: JSON serialization: getDriverLicenseNo();getDOB(); //reflection
}
