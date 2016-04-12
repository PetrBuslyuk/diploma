package server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class emailSending {
    static Session session;
    emailSending(){
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.sosketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
        session = Session.getInstance(props,new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
               return new javax.mail.PasswordAuthentication("skyliner270594@gmail.com", "1062543zz");
            }
        });
    }
    public static void sendEmail(String to, String from, String fromName, String subject, String text) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, fromName));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(text);
            Transport.send(msg);
        } catch (AddressException ex) {
            Logger.getLogger(emailSending.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException | UnsupportedEncodingException ex){
            Logger.getLogger(emailSending.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}