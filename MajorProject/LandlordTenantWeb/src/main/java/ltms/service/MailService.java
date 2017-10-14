package ltms.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public static void main(String[] args) {
		MailService mailService = new MailService();
		MailServiceRequest mailServiceRequest = new MailServiceRequest();
		mailServiceRequest.setToAddress("mansijoshi36@gmail.com");
		mailServiceRequest.setEmailSubject("Testing Subject");
		mailServiceRequest.setEmailMessage(
				"<p style='color:black;'>Dear <b>Landlord</b>,<br /><br />Your user id <i>activated</i> !<br /><br />Web Administrator,<br />Conveyance Inc, 2016</p>");
		mailService.sendEmail(mailServiceRequest);
	}

	public void sendEmail(MailServiceRequest mailServiceRequest) throws RuntimeException {

		final String username = "ltmsadm2016@gmail.com";
		final String password = "ltms@123";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ltmsadm2016@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailServiceRequest.getToAddress()));
			message.setSubject(mailServiceRequest.getEmailSubject());
			message.setContent(mailServiceRequest.getEmailMessage(), "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}