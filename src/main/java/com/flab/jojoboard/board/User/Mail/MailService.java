package com.flab.jojoboard.board.User.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final MailProperties mailProperties;

    private final JavaMailSender mailSender;


    public boolean send(String to, String title, String content) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, mailProperties.getEncoding());

            messageHelper.setSubject(title);
            messageHelper.setText(content, true);
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(to);

            mailSender.send(message); // 메일발송

            return true;
        } catch (MailException es) {
            log.error(es.getMessage());
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
