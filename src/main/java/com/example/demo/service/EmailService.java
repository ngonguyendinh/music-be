package com.example.demo.service;

import com.example.demo.util.MailBody;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail ;

   public void sendSimpleMessage(MailBody body){
       SimpleMailMessage message = new SimpleMailMessage();
       message.setTo(body.to());
       message.setFrom(fromMail);
       message.setSubject(body.subject());
       message.setText(body.text());
       javaMailSender.send(message);
   }

}
