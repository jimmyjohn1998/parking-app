package com.parkingapp.mail;


import com.parkingapp.util.AppConstants;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Runnable {

    String toAddress = "";
    String content = "";
    String subject = "";

    public MailSender(String toAddress, String content, String subject) {
        this.toAddress = toAddress;
        this.content = content;
        this.subject = subject;
        new Thread(this).start();
    }

    public void run() {
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtps.host", "smtp.gmail.com");
            properties.put("mail.smtps.auth", "true");
            Session session = Session.getInstance(properties);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(AppConstants.MAIL_ID));
            if (toAddress != null) {
                msg.addRecipients(Message.RecipientType.TO, toAddress);
            }
            msg.setContent(content, "text/html");
            msg.setSubject(subject);
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", AppConstants.MAIL_ID, AppConstants.MAIL_PWD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Successfully Send");
        } catch (MessagingException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
