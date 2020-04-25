package com.example.morsecode;

public class MessageInfo {
    public MessageInfo(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        Message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    private String phoneNumber;
    private String Message;
}
