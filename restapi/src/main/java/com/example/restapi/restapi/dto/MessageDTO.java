package com.example.restapi.restapi.dto;

public class MessageDTO {
    private String message;
    private String type;

   //empty constructor
    public MessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
