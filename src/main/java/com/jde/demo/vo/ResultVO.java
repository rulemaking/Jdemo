package com.jde.demo.vo;

import lombok.Data;

@Data
public class ResultVO {
    private Integer code;
    private String type;
    private String data;

    
    public ResultVO fail() {
        this.code = 999;
        return this;
    }
    public ResultVO success() {
        this.code = 0;
        return this;
    }


}

