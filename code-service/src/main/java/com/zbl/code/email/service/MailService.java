package com.zbl.code.email.service;

/**
 * @Author: zbl
 * @Date: Created in 10:08 2019/8/23
 * @Description: 邮件服务类
 * @Version: $
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
