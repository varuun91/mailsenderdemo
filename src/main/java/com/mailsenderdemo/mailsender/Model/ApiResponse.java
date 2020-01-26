package com.mailsenderdemo.mailsender.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This model is the structure of API response received after calling the API - "send-mail"
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refid", "response"})
public class ApiResponse {

    @JsonProperty("refid")
    @JsonPropertyDescription("reference id for the request")
    @NotNull
    private String BatchRefid;

    @Valid
    @JsonProperty("response")
    @JsonPropertyDescription("Response to JSON input")
    @NotNull
    private List<MailResponse> mailResp;

    public ApiResponse() {
    }

    @JsonProperty("refid")
    public String getBatchRefid() {
        return BatchRefid;
    }

    @JsonProperty("refid")
    public void setBatchRefid(String batchRefid) {
        this.BatchRefid = batchRefid;
    }

    @JsonProperty("response")
    public List<MailResponse> getMailResp() {
        return mailResp;
    }

    @JsonProperty("response")
    public void setMailResp(List<MailResponse> mailResp) {
        this.mailResp = mailResp;
    }

    @Override
    public String toString() {
        return "{" +
                "\"BatchRefId\":\"" + BatchRefid + '\"' +
                ", \"Response\":" + mailResp +
                '}';
    }
}

