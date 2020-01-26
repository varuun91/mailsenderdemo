package com.mailsenderdemo.mailsender.ErrorHandler;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class MailSenderErrorResponse {

    public String errorResponse(String inputParam, String calledAPI, String errMsg, String rootCause, Timestamp timestamp) {
        return "{" +
                "\"API\":\"mailapp/mailsender/" + calledAPI + "\"" +
                ", \"Input\":\"" + inputParam + "\"" +
                ", \"Error\":\"" + errMsg + "\"" +
                ", \"Cause\":\"" + rootCause + "\"" +
                ", \"Time\":\"" + timestamp + "\"" +
                '}';
    }
}
