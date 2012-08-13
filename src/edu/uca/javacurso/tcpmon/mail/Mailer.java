package edu.uca.javacurso.tcpmon.mail;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	
	public static Properties mailServerProps = new Properties();
	public static String fromMail = "asdf@asdf.com";
	
	public Mailer() {
		mailServerProps.put("mail.smtp.host", "localhost");
		mailServerProps.put("mail.smtp.port", "25");
	}
	
	public void send(String from, String to, String subject, String text){
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		Session mailSession = Session.getDefaultInstance(mailServerProps);
		MimeMessage message = new MimeMessage(mailSession);
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try{
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			message.setFrom(fromAddress);
			message.setRecipient(RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setText(text);
			
			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendReportingMail(String email, String address){
		String subject="reporte caida servicio";
		String text="direccion: "+address;
		send(fromMail,email,subject,text);
	}
}
