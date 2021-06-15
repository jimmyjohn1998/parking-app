package com.parkingapp.mail;

import com.parkingapp.mail.EmailSender;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email extends Thread {

    private String frommail = null;
    private String password = null;
    private String[] reciepients = null;
    private String subject = null;
    private String fileUrls = null;
    private String outGoingmailServ = "smtp.gmail.com";
    private String message = null;
    Properties property = null;
    EmailSender sender = null;
    public static final String SUCCESS = "success"; 

    public Email() {
    }

    public Email(EmailSender sender) {
        this.sender = sender;
    }

    public Email(String frommail, String password,
            String[] reciepients, String subject, String message, String fileUrls) {
        this.frommail = frommail;
        this.password = password;
        this.reciepients = reciepients;
        this.subject = subject;
        this.fileUrls = fileUrls;
        this.message = message;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    public void setOwner(String mailId, String password) {
        frommail = mailId;
        this.password = password;
    }

    public void setCompose(String[] recipients, String subject, String message, String files) {
        this.reciepients = recipients;
        this.subject = subject;
        this.message = message;
        this.fileUrls = files;
    }

    public void send() {
        start();
    }

    public void send(EmailSender sender) {
        this.sender = sender;
        start();
    }

    public void run() {
        try {
            if (property == null) {
                this.property = System.getProperties();
                this.property.put("mail.smtps.host", "smtp.gmail.com");
                this.property.put("mail.smtps.auth", "true");
            }
            Session instance = Session.getInstance(property);
            MimeMessage mimeMessage = new MimeMessage(instance);
            mimeMessage.setFrom(new InternetAddress(frommail));

            for (String reciepient : reciepients) {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(reciepient));
            }

            mimeMessage.setSubject(subject);
            BodyPart msgbodyPart = new MimeBodyPart();

            msgbodyPart.setText(message);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(msgbodyPart);
            if (fileUrls != null) {
                StringTokenizer token = new StringTokenizer(fileUrls, ",");
                while (token.hasMoreTokens()) {
                    String filename = token.nextToken();
                    msgbodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(filename);
                    msgbodyPart.setDataHandler(new DataHandler(source));
                    msgbodyPart.setFileName(filename.substring(filename.lastIndexOf("\\")));
                    multipart.addBodyPart(msgbodyPart);
                }
            }
            mimeMessage.setContent(multipart);
            Transport trs = instance.getTransport("smtps");
            trs.connect(outGoingmailServ, frommail, password);
            trs.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            trs.close();
            if (sender != null) {
                sender.result("success");
            }
        } catch (MessagingException ex) {
            sender.result(String.valueOf(ex));
        }
    }

    public static void main(String[] args) {
        new Email().test();
    }

    public void test() {
        
    }

  
}
