package com.jde.demo.service;

import java.util.Map;

public interface HelloWorldService {


    public String helloWorldPost(String requestBody);

    public String helloWorldGet(Map<String, String> m);
    
}
