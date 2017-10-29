package com.jmaguina.mail.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jmaguina.mail.app.DTO.MailDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Service to send email
 *
 * Created by Jorge Maguina on 28/10/2017.
 */
@Service
@PropertySource("classpath:application.properties")
public class MailService {

    Properties mailServerProperties;
    Session getMailSession;
    MimeMessage generateMailMessage;

    //To obtain values from application.properties
    @Autowired
    private Environment env;

    public boolean SendContactRequest(MailDTO request) throws MessagingException, UnsupportedEncodingException {

        // Step1 setup Mail Server Properties.
        mailServerProperties();

        // Step2 get Mail Session.
        getMailSession(request);

        // Step3 Get Session and Send mail
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport.send(generateMailMessage);

        return true;
    }

    private void getMailSession(MailDTO request) throws MessagingException {
        System.out.println("\n\n 2nd ===> get Mail Session..");
        /*getMailSession = Session.getDefaultInstance(mailServerProperties, null);*/

        //Create a Session object & authenticate uid and pwd
        getMailSession = Session.getInstance(mailServerProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("mail.username"), env.getProperty("mail.password"));
            }
        });

        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO,
                new InternetAddress(env.getProperty("recipient.email"))); //Repit if need more recipient to send
        generateMailMessage.setSubject("Potencial Contacto");
        String emailBody = "Nombre: " + request.getName() + "<br>Email: " +
                request.getEmail() + "<br><br><br>Mensaje: " + request.getDescription();
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");
    }

    private void mailServerProperties() {
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        mailServerProperties.put("mail.smtp.host", env.getProperty("mail.smtp"));
        System.out.println("Mail Server Properties have been setup successfully..");
    }

}
