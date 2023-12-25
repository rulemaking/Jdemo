package com.jde.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jde.demo.model.UserModel;
import com.jde.demo.repository.UserRepository;
import com.jde.demo.service.HelloWorldService;

@RestController
@RequestMapping("")
public class HelloWorldController {
    @Autowired
    HelloWorldService helloWorldService;
    
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/helloworld")
    public ResponseEntity<String> helloWorld(@RequestHeader("Authorization") String token) {
        // Check if the token is valid
        UserModel user = userRepository.findByLoginToken(token);
        if (user != null) {
            return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get")
    public String helloWorldGet(@RequestParam Map<String,String> requestParamMap){
        return helloWorldService.helloWorldGet(requestParamMap);
    }
    @PostMapping("/post")
    public String helloWorldPost(@RequestBody String requestBody){
        return helloWorldService.helloWorldPost(requestBody);
    }
}
