package com.teamkph.kph.responseRole;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CommonResult {
//    @ApiModelProperty(value = "응답 성공여부 : true/false")
//    private boolean success;
//
//    @ApiModelProperty(value = "응답 코드 번호 : >= 0 정상, < 0 비정상")
//    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;

    @ApiModelProperty(value = "응답 데이터")
    private Object data;

    public CommonResult(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
