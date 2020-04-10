package com.jmaguina.mail.app.services;

import com.jmaguina.mail.app.model.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    //https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/integration.html#mail
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(MailDto request) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(request.getEmail(), request.getEmail());
        msg.setSubject("Potencial Contacto");
        String emailBody = "Nombre: " + request.getName() + "\nEmail: " +
                request.getEmail() + "\n\n\nMensaje: " + request.getDescription();
        msg.setText(emailBody);

        javaMailSender.send(msg);

        System.out.println("Mail Session has been created successfully..");

    }

}
