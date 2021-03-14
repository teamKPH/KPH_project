package com.teamkph.kph.responseRole;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

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
}
