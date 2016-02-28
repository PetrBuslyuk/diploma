package server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailSending {
emailSending(){
    sendEmail();
}
//String to, String cc, String from, String subject, String text, String smtpHost
	public static void sendEmail() {
		final String username = "skyliner270594@gmail.com";
		final  String password = "1062543zz";

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "false");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", "aspmx.l.google.com");
		props.setProperty("mail.smtp.port", "25");
                

		Session session = Session.getInstance(props//,
//		  new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
		 // }
);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("skyliner270594@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("skyliner270594@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}