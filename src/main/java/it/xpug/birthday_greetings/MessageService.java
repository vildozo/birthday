package it.xpug.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageService {
	Session session ;
	Message msg;
	
	MessageService( String smtpHost, int smtpPort){
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		this.session = Session.getInstance(props, null);
	}
	
	public Session getSessions(){
		return session;
	}
	
	public void composeMessage(String sender, String subject, String body, String recipient) throws AddressException, MessagingException {
		this.msg = new MimeMessage(this.session);
		this.msg.setFrom(new InternetAddress(sender));
		this.msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		this.msg.setSubject(subject);
		this.msg.setText(body);
	}
	
	public void sendMessage()throws AddressException, MessagingException
	{
		Transport.send(this.msg) ;
	}
	
	
	
}
