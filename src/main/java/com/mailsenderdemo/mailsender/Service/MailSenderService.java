package com.mailsenderdemo.mailsender.Service;

import com.mailsenderdemo.mailsender.ErrorHandler.MailSenderErrorResponse;
import com.mailsenderdemo.mailsender.Model.ApiRequest;
import com.mailsenderdemo.mailsender.Model.ApiResponse;
import com.mailsenderdemo.mailsender.Model.MailData;
import com.mailsenderdemo.mailsender.Model.MailResponse;
import com.mailsenderdemo.mailsender.Repository.MailSenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class MailSenderService {

    private JavaMailSender javaMailSender;
    private MailSenderRepository mailSenderRepository;
    private ApiResponse apiResponse;
    private MailData mailData;
    private MailSenderErrorResponse mailSenderErrorResponse;
    //private PropertyUtil propertyUtil;

    private static final Logger applicationLogger = LoggerFactory.getLogger(MailSenderService.class);

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender,
                             MailSenderRepository mailSenderRepository,
                             ApiResponse apiResponse,
                             MailData mailData,
                             MailSenderErrorResponse mailSenderErrorResponse) {
        this.javaMailSender = javaMailSender;
        this.mailSenderRepository = mailSenderRepository;
        this.apiResponse = apiResponse;
        this.mailData = mailData;
        this.mailSenderErrorResponse = mailSenderErrorResponse;
    }


    /**
     * This function is used to send mail without attachment.
     *
     * @param mailreq   JSON data which will be parsed to send emails.
     * @throws MailException
     * @throws MessagingException
     */

    public String sendEmail(ApiRequest mailreq) throws MailException, MessagingException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function.
         * It uses Mimemessage and MimeHelper to build the email and send it.
         */
        try {
            apiResponse.setBatchRefid(mailreq.getBatchRefid());

            List<MailResponse> listResponse = new ArrayList<>();
            HashMap<String, List<MailData>> mailBatch = new HashMap<>();
            mailBatch.put(mailreq.getBatchRefid(), mailreq.getMailData());

            mailBatch.forEach((batchid, mailMsg) -> {
                mailMsg.forEach((mailSubBatch) -> {
                    MailResponse mailResponse = new MailResponse();
                    try {
                        mailSubBatch.setBatchrefId(batchid); //set the batch Reference ID
                        String[] toaddr, ccaddr, bccaddr, attachments;

                        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                        MimeMessageHelper mimehelper = new MimeMessageHelper(mimeMessage, true);

                        toaddr = mailSubBatch.getMailToAddr().toArray(new String[0]);
                        ccaddr = mailSubBatch.getMailCCAddr().toArray(new String[0]);
                        bccaddr = mailSubBatch.getMailBccAddr().toArray(new String[0]);
                        attachments = mailSubBatch.getMailAttachment().toArray(new String[0]);

                        mimehelper.setTo(toaddr);
                        if (ccaddr.length > 0) {
                            mimehelper.setCc(ccaddr);
                        }
                        if (bccaddr.length > 0) {
                            mimehelper.setBcc(bccaddr);
                        }
                        mimehelper.setSubject(mailSubBatch.getMailsubject());
                        mimehelper.setText(mailSubBatch.getMailbody(), true);

                        if (attachments.length > 0) {
                            for (String filename : attachments) {
                                ClassPathResource classPathResource = new ClassPathResource(filename);
                                mimehelper.addAttachment(classPathResource.getFilename(), classPathResource);
                            }
                        }
                        javaMailSender.send(mimeMessage);
                        mailSubBatch.setMailDeliveryStatus(1);
                        mailSubBatch.setMailDeliveryTime(Timestamp.valueOf(LocalDateTime.now()));
                        applicationLogger.info("Mail Sent Successfully..");
                    } catch (Exception ex) {
                        applicationLogger.warn("Unable to Connect SMTP Server");
                        mailSubBatch.setMailDeliveryStatus(0);
                    } finally {
                        MailData mailData = mailSenderRepository.save(mailSubBatch);

                        mailResponse.setMaildtl_id(mailData.getMailRefId());
                        mailResponse.setMaildtl_toaddr(mailSubBatch.getMailToAddr());
                        mailResponse.setMaildtl_subject(mailSubBatch.getMailsubject());
                        mailResponse.setMaildtl_deliverysts(mailSubBatch.getMailDeliveryStatus());
                        mailResponse.setMailDeliveryTime(mailSubBatch.getMailDeliveryTime());

                        listResponse.add(mailResponse);

                        apiResponse.setMailResp(listResponse);
                    }

                });
            });
            applicationLogger.info("All Mails sent successfully!");
            return apiResponse.toString();
        }
        catch(Exception ex) {
            applicationLogger.warn("Error in Calling mail Service");
            return mailSenderErrorResponse.errorResponse(
                    "Json Data",
                    "send-mail",
                    "Error in Mail API",
                    ex.getMessage().toString(),
                    Timestamp.valueOf(LocalDateTime.now())
            );

        }
    }

    /**
     * This function is used to fetch batch details
     *
     * @param batchRefId Reference Id associated with each JSON Request
     */
    public String getBatchDetails(String batchRefId) {
        List<MailData> mailResp = new ArrayList();
        try {
            mailResp = mailSenderRepository.findByBatchRefId(batchRefId);
            if (mailResp.isEmpty()) {
                applicationLogger.warn("No Data Found");
                return mailSenderErrorResponse.errorResponse(
                        batchRefId,
                        "getbatchdetails",
                        "No Data found",
                        "Argument Value not present in DB.",
                        Timestamp.valueOf(LocalDateTime.now())
                );            }
            return mailResp.toString();
        } catch (Exception ex) {
            return mailSenderErrorResponse.errorResponse(
                    batchRefId,
                    "getbatchdetails",
                    "No Data found",
                    ex.getMessage().toString(),
                    Timestamp.valueOf(LocalDateTime.now())
            );
        }

    }

    /**
     * This function is used to fetch mail details using mailRef id
     *
     * @param mailRefId Reference Id associated with each mail. This ID is generated by the time the mail is sent
     */
    public String getMailDetails(Integer mailRefId) {
        try {
            mailData = mailSenderRepository.findByMailRefId(mailRefId);
            if (isEmpty(mailData)) {
                applicationLogger.info("Exception: Invalid input data.");
                return mailSenderErrorResponse.errorResponse(
                        mailRefId.toString(),
                        "getmaildetails",
                        "No Data found",
                        "Argument Value not present in DB.",
                        Timestamp.valueOf(LocalDateTime.now())
                );
            }
            return mailData.toString();
        } catch (Exception ex) {
            return mailSenderErrorResponse.errorResponse(
                    mailRefId.toString(),
                    "getmaildetails",
                    "No Data found",
                    ex.getMessage().toString(),
                    Timestamp.valueOf(LocalDateTime.now())
            );
        }
    }
}
