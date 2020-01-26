package com.mailsenderdemo.mailsender.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mailsenderdemo.mailsender.Helper.StringListConverter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * This model is the structure of individual mail data inside the API request
 * Calling API - "send-mail"
 */
@Component
@Entity
@Table(name = "maildetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"mailrefid", "mailtoaddr", "mailccaddr", "mailbccaddr", "mailsubject", "mailbody", "mailattachment", "maildeliverystatus"})
public class MailData implements Serializable {

    @Id
    @NotNull
    @JsonProperty("mailrefid")
    @Column(name = "maildtl_id", nullable = false)
    private Integer mailRefId;

    @JsonProperty("mailtoaddr")
    @JsonPropertyDescription("To address for sending mails")
    @NotNull
    @Convert(converter = StringListConverter.class)
    private List<String> maildtl_toaddr;

    @JsonProperty("mailccaddr")
    @JsonPropertyDescription("CC address for sending mails")
    @Convert(converter = StringListConverter.class)
    private List<String> maildtl_ccaddr;

    @JsonProperty("mailbccaddr")
    @JsonPropertyDescription("BCC address for sending mails")
    @Convert(converter = StringListConverter.class)
    private List<String> maildtl_bccaddr;

    @JsonProperty("mailsubject")
    @JsonPropertyDescription("Subject of mail")
    @NotNull
    private String maildtl_subject;

    @JsonProperty("mailbody")
    @JsonPropertyDescription("Mail Body")
    private String maildtl_body;

    @JsonProperty("mailattachment")
    @JsonPropertyDescription("Attachments path if any")
    @Convert(converter = StringListConverter.class)
    private List<String> maildtl_attachment;

    @JsonProperty("maildeliverystatus")
    @JsonPropertyDescription("mail delivery status. 1-success 0-failed")
    private Integer maildtl_deliverysts = 0;

    private Timestamp maildtl_deliverytime;

    @Column(name = "maildtl_batchrefid")
    private String batchRefId;

    //Default Constructor
    public MailData() {
    }

    //Getters and Setters
    public Integer getMailRefId() {
        return mailRefId;
    }

    public String getBatchrefId() {
        return batchRefId;
    }

    public void setBatchrefId(String BatchrefId) {
        this.batchRefId = BatchrefId;
    }

    @JsonProperty("mailtoaddr")
    public List<String> getMailToAddr() {
        return maildtl_toaddr;
    }

    @JsonProperty("mailtoaddr")
    public void setMailToAddr(List<String> mailToAddr) {
        maildtl_toaddr = mailToAddr;
    }

    @JsonProperty("mailccaddr")
    public List<String> getMailCCAddr() {
        return maildtl_ccaddr;
    }

    @JsonProperty("mailccaddr")
    public void setMailCCAddr(List<String> mailCCAddr) {
        maildtl_ccaddr = mailCCAddr;
    }

    @JsonProperty("mailsubject")
    public String getMailsubject() {
        return maildtl_subject;
    }

    @JsonProperty("mailsubject")
    public void setMailsubject(String mailsubject) {
        maildtl_subject = mailsubject;
    }

    @JsonProperty("mailbody")
    public String getMailbody() {
        return maildtl_body;
    }

    @JsonProperty("mailbody")
    public void setMailbody(String mailbody) {
        maildtl_body = mailbody;
    }

    @JsonProperty("mailattachment")
    public List<String> getMailAttachment() {
        return maildtl_attachment;
    }

    @JsonProperty("mailattachment")
    public void setMailAttachment(List<String> mailAttachment) {
        maildtl_attachment = mailAttachment;
    }

    @JsonProperty("maildeliverystatus")
    public Integer getMailDeliveryStatus() {
        return maildtl_deliverysts;
    }

    @JsonProperty("maildeliverystatus")
    public void setMailDeliveryStatus(Integer mailDeliveryStatus) {
        maildtl_deliverysts = mailDeliveryStatus;
    }

    @JsonProperty("mailbccaddr")
    public List<String> getMailBccAddr() {
        return maildtl_bccaddr;
    }

    @JsonProperty("mailbccaddr")
    public void setMailBccAddr(List<String> MailBccAddr) {
        this.maildtl_bccaddr = MailBccAddr;
    }

    public Timestamp getMailDeliveryTime() {
        return maildtl_deliverytime;
    }

    public void setMailDeliveryTime(Timestamp MailDeliveryTime) {
        this.maildtl_deliverytime = MailDeliveryTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"BatchRefId\":\"" + batchRefId + '\"' +
                ", \"RefId\":\"" + mailRefId + '\"' +
                ", \"mailsubject\":\"" + maildtl_subject + '\"' +
                ", \"mailbody\":\"" + maildtl_body + '\"' +
                ", \"maildeliverystatus\":\"" + maildtl_deliverysts + '\"' +
                ", \"maildeliverytime\":\"" + maildtl_deliverytime + '\"' +
                '}';
    }
}
