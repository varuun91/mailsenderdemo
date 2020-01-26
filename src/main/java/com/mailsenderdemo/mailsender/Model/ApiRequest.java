package com.mailsenderdemo.mailsender.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This model is the structure for the incoming API request "send-mail"
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refid","mail"})
public class ApiRequest {

    @JsonProperty("refid")
    @JsonPropertyDescription("reference id for the request")
    @NotNull
    private String BatchRefid;

    @Valid
    @JsonProperty("mail")
    @JsonPropertyDescription("main mail tag in JSON input")
    @NotNull
    private List<MailData> mailData;

    public ApiRequest(){}

    public List<MailData> getMailData() {
        return mailData;
    }

    public void setMailData(List<MailData> mailData) {
        this.mailData = mailData;
    }

    public String getBatchRefid() {
        return BatchRefid;
    }

    public void setBatchRefid(String batchRefid) {
        this.BatchRefid = batchRefid;
    }
}
