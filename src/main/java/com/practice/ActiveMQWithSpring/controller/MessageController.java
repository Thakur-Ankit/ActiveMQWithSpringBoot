package com.practice.ActiveMQWithSpring.controller;

import com.practice.ActiveMQWithSpring.model.User;
import com.practice.ActiveMQWithSpring.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    MessageProducer messageProducer;

    @PostMapping(value = "/send/message")
    public String sendMessage(@RequestBody String message) {
        boolean status = messageProducer.sendMessage(message);
        if (status) {
            return "Message is successfully sent to AMQ.";
        } else {
            return "Error in sending Message to AMQ.";
        }
    }

    @PostMapping(value = "/send/user")
    public String sendUser(@RequestBody User user) {
        boolean status = messageProducer.sendUser(user);
        if (status) {
            return "User Object is successfully sent to AMQ.";
        } else {
            return "Error in sending User Object to AMQ.";
        }
    }
}
