package com.zbl.code.email.service.impl;

import com.zbl.code.email.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: zbl
 * @Date: Created in 10:10 2019/8/23
 * @Description: 邮件实现类
 * @Version: $
 */
@Component
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${zbl-code.mail.fromMail.addr}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     * @param to 接收者
     * @param subject 主题
     * @param content 发送内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try {
            javaMailSender.send(mailMessage);
            log.info("简单邮件已发送");
        } catch (Exception e) {
            log.error("发送简单邮件出现异常:"+e);
        }
    }

    /**
     * 发送HTML格式邮件
     * @param to 接收者
     * @param subject 主题
     * @param content 发送内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e){
            log.error("发送html邮件时发生异常: " + e);
        }
    }



    /**
     * 发送带附件的邮件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            // 多个附件可使用多条  helper.addAttachment(fileName, file)
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常! " + e);
        }
    }

    /**
     * 发送带静态资源的邮件
     * @param rscPath 图片路径
     * @param rscId 和图片路径绑定 helper.addInline(rscId, res);
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            javaMailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送.");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常! " + e );
        }
    }


}
