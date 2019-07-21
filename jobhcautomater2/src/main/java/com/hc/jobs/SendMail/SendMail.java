package com.hc.jobs.SendMail;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;



public class SendMail {

	public static void sendMail(String recepient, String obj) throws Exception {
		System.out.println("Preparing to send email");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port", "25");

		String myAccountEmail = "cantact.project2@gmail.com";
		String password = "projectdeux123456789";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}

		});

		Message message = prepareMessage(session, myAccountEmail, recepient, obj);
		Transport.send(message);
		System.out.println("Message sent with successfully");
	}	
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String obj ) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Assignment Email");
			message.setText("I would like to inform you that you have been assigned to this job :"+obj);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SendMail.class.getName()).log(null, Level.SEVERE,ex);
		}
		return null;
	}
}