package com.dahua.messaging.controller;

import com.dahua.messaging.request.SendMessageRequest;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @PostMapping("/send")
    public void sendMessage(@CookieValue String LoginToken, @RequestBody SendMessageRequest sendMessageRequest) {
        
    }
}
