package com.jde.demo.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jde.demo.service.HelloWorldService;
import com.jde.demo.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelloWorldServiceImpl implements HelloWorldService {


    @Override
    public String helloWorldPost(String requestBody) {
        ResultVO result = new ResultVO().success();
        result.setCode(0);
        result.setType("post");
        result.setData(requestBody);
        String resultString = JSONObject.toJSONString(result);
        log.info("hello world post res {}", resultString);
        return resultString;
    }

    @Override
    public String helloWorldGet(Map<String, String> requestParamMap) {

        ResultVO result = new ResultVO().fail();
        result.setType("get");
        result.setData(JSONObject.toJSONString(requestParamMap));
        String resultString = JSONObject.toJSONString(result);
        log.info("hello world get res {}", resultString);
        return resultString;
    }
    

    
}
