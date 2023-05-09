package com.example.demo1;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

<<<<<<< HEAD:demo1/src/main/java/com/example/demo1/EmailSender.java
public class EmailSender extends Exception{
    final String companyemail = "cleaningservicezt2023@gmail.com";
    final String companypassword = "txkjgexkvplmbvgs";
    String reportFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\Reports.txt";
    public EmailSender() throws MyException {

        String fromEmail = companyemail;
=======
public class sendEmail {
    private static final Logger LOGGER = Logger.getLogger(Scene2Controller.class.getName());
    String sentMSG = "Sent message";
    public sendEmail() {
        final String username = "cleaningservicezt2023@gmail.com";
        final String password = "txkjgexkvplmbvgs";
        String fromEmail = "cleaningservicezt2023@gmail.com";
>>>>>>> 58c54a2 (no message):demo1/src/main/java/com/example/demo1/sendEmail.java
        String toEmail = "amerkobari22@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyemail, companypassword);
            }
        });

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Cleaning Service Rerports");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Cleaning Service Rerports for the month of April 2021");


            MimeBodyPart pdfAttachment = new MimeBodyPart();

            pdfAttachment.attachFile(reportFile);

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);


            msg.setContent(emailContent);

            Transport.send(msg);
            LOGGER.info(sentMSG);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            throw new MyException(ex);
        }
    }
    public EmailSender(String to, String subject, String body) {
        String fromEmail = companyemail;
        String toEmail = to;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyemail, companypassword);
            }
        });

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            LOGGER.info(sentMSG);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}