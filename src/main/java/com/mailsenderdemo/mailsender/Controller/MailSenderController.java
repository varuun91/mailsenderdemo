package com.mailsenderdemo.mailsender.Controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mailsenderdemo.mailsender.Model.ApiRequest;
import com.mailsenderdemo.mailsender.Service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/mailsender")
public class MailSenderController {

    private MailSenderService senderService;

    //private PropertyUtil propertyUtil;
    private static final Logger applicationLogger = LoggerFactory.getLogger(MailSenderController.class);

    @Autowired
    public MailSenderController(MailSenderService senderService) {
        this.senderService = senderService;
    }

    /**
     *This API is used to send emails.
     * @param mailreq   this is a JSON request with the necessary details to send an email
     * @return JSON repsonse
     */

    @PostMapping(path = "/send-mail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String send(@Valid @RequestBody ApiRequest mailreq) throws MessagingException, JsonMappingException, JsonParseException, IOException, SQLException {

        applicationLogger.info("API Called. About to Invoke the MailSender Service..");
        return senderService.sendEmail(mailreq);
    }

    /**
     * this API is called to get the details of one batch.
     * @param batchRefId   This is a reference ID associated with each API call
     * @return JSON response
     */
    @GetMapping(path = "/getbatchdetails/{batchRefId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDataByBatchRefId(@Valid @PathVariable String batchRefId) throws MessagingException {
        applicationLogger.info("API Call to fetch Batch Details. About to Invoke the MailSender Service..");
        return senderService.getBatchDetails(batchRefId);
    }

    /**
     * this API is called to get details of individual mail data in the JSON request
     * @param mailRefId  This is a reference id associated with each mail data
     * @return JSON response
     * @throws MessagingException
     */
    @GetMapping(path = "/getmaildetails/{mailRefId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDataBymailRefId(@Valid @PathVariable Integer mailRefId) throws MessagingException {
        applicationLogger.info("API Call to fetch Mail Details. About to Invoke the MailSender Service..");
        return senderService.getMailDetails(mailRefId);
    }
}


