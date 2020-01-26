package com.mailsenderdemo.mailsender.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mailsenderdemo.mailsender.Helper.StringListConverter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * This model is the structure of individual mail data inside the API response which is
 * received for the API call "send-mail"
 */
@Component
@JsonPropertyOrder({"mailrefid", "mailtoaddr", "mailsubject", "maildeliverystatus", "maildeliverytime"})
public class MailResponse {

    @JsonProperty("mailrefid")
    @JsonPropertyDescription("Reference number for each mailset")
    @NotNull
    private Integer maildtl_id;

    @JsonProperty("mailtoaddr")
    @JsonPropertyDescription("To address for sending mails")
    @NotNull
    @Convert(converter = StringListConverter.class)
    private List<String> maildtl_toaddr;

    @JsonProperty("mailsubject")
    @JsonPropertyDescription("Subject of mail")
    @NotNull
    private String maildtl_subject;

    @JsonProperty("maildeliverystatus")
    @JsonPropertyDescription("mail delivery status. 1-success 0-failed")
    private Integer maildtl_deliverysts = 0;

    @JsonProperty("maildeliverytime")
    @JsonPropertyDescription("mail time")
    private Timestamp maildtl_deliverytime;


    public MailResponse() {
    }

    @JsonProperty("mailrefid")
    public Integer getMaildtl_id() {
        return maildtl_id;
    }

    @JsonProperty("mailrefid")
    public void setMaildtl_id(Integer maildtl_id) {
        this.maildtl_id = maildtl_id;
    }

    @JsonProperty("mailtoaddr")
    public List<String> getMaildtl_toaddr() {
        return maildtl_toaddr;
    }

    @JsonProperty("mailtoaddr")
    public void setMaildtl_toaddr(List<String> maildtl_toaddr) {
        this.maildtl_toaddr = maildtl_toaddr;
    }

    @JsonProperty("mailsubject")
    public String getMaildtl_subject() {
        return maildtl_subject;
    }

    @JsonProperty("mailsubject")
    public void setMaildtl_subject(String maildtl_subject) {
        this.maildtl_subject = maildtl_subject;
    }

    @JsonProperty("maildeliverystatus")
    public Integer getMaildtl_deliverysts() {
        return maildtl_deliverysts;
    }

    @JsonProperty("maildeliverystatus")
    public void setMaildtl_deliverysts(Integer maildtl_deliverysts) {
        this.maildtl_deliverysts = maildtl_deliverysts;
    }

    @JsonProperty("maildeliverytime")
    public Timestamp getMailDeliveryTime() {
        return maildtl_deliverytime;
    }

    @JsonProperty("maildeliverytime")
    public void setMailDeliveryTime(Timestamp MailDeliveryTime) {
        this.maildtl_deliverytime = MailDeliveryTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"RefId\":\"" + maildtl_id + "\"" +
                ", \"mailsubject\":\"" + maildtl_subject + "\"" +
                ", \"maildeliverystatus\":\"" + maildtl_deliverysts + "\"" +
                ", \"maildeliverytime\":\"" + maildtl_deliverytime + "\"" +
                "}";
    }
}
