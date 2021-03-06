/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfigEmail {
	
	private static String host = "buho.uca.edu.sv";
		
	public boolean notificarEmail(String email, String bodyString, String subject){
		try{
			if (!"".equals(email)) {
				Properties properties = System.getProperties();		
				properties.put("mail.smtp.host", host);
				Authenticator auth = new SMTPAuthenticator("saldei", "3alumnos1tesis");
				javax.mail.Session ses = javax.mail.Session.getDefaultInstance(properties, null);		
				MimeMessage msg = new MimeMessage(ses);
				msg.setText(bodyString);
				msg.setSubject(subject);
			    msg.setFrom(new InternetAddress("saldei"));
			    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				msg.setContent(bodyString,"text/plain");
				Transport.send(msg);
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}//class
