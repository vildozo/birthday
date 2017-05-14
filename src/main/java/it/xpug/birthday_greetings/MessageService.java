package it.xpug.birthday_greetings;

import javax.mail.Session;

public class MessageService {
	Session session ;
	
	MessageService( String smtpHost, int smtpPort, String sender, String subject, String body, String recipient){
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		this.session = Session.getInstance(props, null);
	}
	
	public Session getSessions(){
		return session;
	}
	
	
}
