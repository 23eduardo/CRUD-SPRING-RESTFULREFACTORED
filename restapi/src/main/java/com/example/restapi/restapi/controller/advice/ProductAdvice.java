package com.example.restapi.restapi.controller.advice;

import com.example.restapi.restapi.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//this annotation is used to return exceptions in case webservers mal functions
@RestControllerAdvice
public class ProductAdvice {

    //Provide the posible exceptions
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNullPointerException(NullPointerException ex){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Errors found in request, try later");
        messageDTO.setType("ERROR");
        return messageDTO;
    }

}
