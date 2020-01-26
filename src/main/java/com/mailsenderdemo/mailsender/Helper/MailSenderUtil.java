package com.mailsenderdemo.mailsender.Helper;

import com.mailsenderdemo.mailsender.ErrorHandler.MailSenderRunTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.mailsenderdemo.mailsender.Constants.MailSenderConstants.DEFAULT_ZONE_ID;


public class MailSenderUtil {

    private static final Logger applicationLogger = LoggerFactory.getLogger(MailSenderUtil.class);

    public void throwInternalErro(String code, String error, String message) {
        throw new MailSenderRunTimeException(code, error, message, Timestamp.valueOf(LocalDateTime.now(DEFAULT_ZONE_ID)));
    }
}
