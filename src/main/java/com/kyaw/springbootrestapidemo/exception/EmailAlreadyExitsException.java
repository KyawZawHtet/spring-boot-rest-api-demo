/**
 * @Author : Kyaw Zaw Htet
 * @Date : 4/21/2024
 * @Time : 10:32 PM
 * @Project_Name : spring-boot-rest-api-demo
 */
package com.kyaw.springbootrestapidemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExitsException extends RuntimeException{

    private String message;

    public EmailAlreadyExitsException(String message){
        super(message);
    }
}
