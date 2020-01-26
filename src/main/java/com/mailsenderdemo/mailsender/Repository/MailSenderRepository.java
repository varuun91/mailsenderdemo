package com.mailsenderdemo.mailsender.Repository;

import com.mailsenderdemo.mailsender.Model.MailData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This Repository handles the CRUD requests for the incoming API requests.
 */

@Repository
public interface MailSenderRepository extends CrudRepository<MailData, Long> {
    List<MailData> findByBatchRefId(String batchRefId);
    MailData findByMailRefId(Integer mailRefId);
}