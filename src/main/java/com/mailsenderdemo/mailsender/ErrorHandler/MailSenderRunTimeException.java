package com.mailsenderdemo.mailsender.ErrorHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MailSenderRunTimeException extends RuntimeException {
    private String code;
    private String error;
    private Date timestamp;

    public MailSenderRunTimeException() {

    }

    public MailSenderRunTimeException(String message) {
        super(message);
    }

    public MailSenderRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSenderRunTimeException(String code, String error, String message, Date timestamp) {
        super(message);
        this.code = code;
        this.error = error;
        this.timestamp = timestamp;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorLogMessage() throws JsonProcessingException {
        Map<String, Object> payload = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        payload.put("code", this.code);
        payload.put("error", this.error);
        payload.put("message", this.getMessage());
        payload.put("timestamp", this.timestamp);
        return objectMapper.writeValueAsString(payload);

    }
}
