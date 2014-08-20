package com.saldei.util.mail;
import java.util.*;
import java.io.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.internet.MimeMultipart;

public class Email
{
// private String host = "smtp.gmail.com";
	private String host = "172.28.16.5";
  public Email()
  {}
  
  public void sendEmail(String titulo, String from, String[] to, String[] bcco, String msgText, String att) { 
	  Object [] atts=null;
	  if(att!=null)
		 atts=new Object[]{att};
	  sendEmailAtt(titulo,from,to,bcco,msgText,atts);
  }
  
  public void sendEmailAtt(String titulo, String from, String[] to, String[] bcco, String msgText, Object[] att) { 
    boolean debug = false;
    from = "saldei@ing.uca.edu.sv";
     
    // create some properties and get the default Session
    Properties props = new Properties();    
    props.put("mail.smtp.host", host);
    //props.put("mail.smtp.port", "587");
    /*Esta línea es la que indica al API que debe autenticarse*/
    //props.put("mail.smtp.auth", "true");
    //props.put("mail.smtp.starttls.enable","true");
    Authenticator auth = new SMTPAuthenticator("jd.dario@gmail.com", "darixito");
    Session session = Session.getInstance(props,null);
        
    try {
        // create a message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = new InternetAddress[to.length];
        InternetAddress[] bcc = null;
        if(bcco != null)
           bcc = new InternetAddress[bcco.length];
        
        //creamos la lista de envios
        for(int i = 0; i < to.length; i++) 
        {
          address[i] = new InternetAddress(to[i]);
        }
        
        //seteamos a las personas que le llegaran los correos
        msg.setRecipients(Message.RecipientType.TO, address);
        
        if (bcco != null ) {
            for(int i = 0; i < bcco.length; i++) 
            {
              bcc[i] = new InternetAddress(bcco[i]);
            }
            //seteamos a las personas que le llegara con copia oculta
            msg.setRecipients(Message.RecipientType.CC, bcc);
        }
        //seteamos el tema del mensaje
        msg.setSubject(titulo);
        //seteamos la fecha
        msg.setSentDate(new Date());
        
        MimeBodyPart mbp1 = new MimeBodyPart();
        
        //agregamos el texto al cuerpo del mensaje
        MimeMultipart mpContent = new MimeMultipart("Texto");
        mbp1.setContent(msgText,"text/html" );
        mpContent.addBodyPart(mbp1);
        
        if(att!=null && att.length>0){
          for (int i = 0; i < att.length; i++) {
        	  if(att[i]!=null){
	        	  MimeBodyPart mbp2 = new MimeBodyPart();
	              FileDataSource fds = new FileDataSource((String)att[i]);
	              mbp2.setDataHandler(new DataHandler(fds));
	              mbp2.setFileName(fds.getName());
	              mpContent.addBodyPart(mbp2);
        	  }
          }	
        }      
        //agregamos al mensaje el contenido
        msg.setContent(mpContent);               
        
        //enviamos el mensaje
        Transport.send(msg);
        
      } catch (MessagingException mex) {
        System.out.println("\n--Exception al momento de enviar el mensaje");
        mex.printStackTrace();
        System.out.println("Error: "+mex.getMessage());
        Exception ex = mex;
        do {
          if (ex instanceof SendFailedException) {
              SendFailedException sfex = (SendFailedException)ex;
              Address[] invalid = sfex.getInvalidAddresses();
              if (invalid != null) {
            System.out.println("    ** Invalid Addresses");
            if (invalid != null) {
                for (int i = 0; i < invalid.length; i++) 
              System.out.println("         " + invalid[i]);
            }
              }
              Address[] validUnsent = sfex.getValidUnsentAddresses();
              if (validUnsent != null) {
            System.out.println("    ** ValidUnsent Addresses");
            if (validUnsent != null) {
                for (int i = 0; i < validUnsent.length; i++) 
              System.out.println("         "+validUnsent[i]);
            }
              }
              Address[] validSent = sfex.getValidSentAddresses();
              if (validSent != null) {
            System.out.println("    ** ValidSent Addresses");
            if (validSent != null) {
                for (int i = 0; i < validSent.length; i++) 
              System.out.println("         "+validSent[i]);
            }
              }
          }
          System.out.println();
          if (ex instanceof MessagingException)
              ex = ((MessagingException)ex).getNextException();
          else
              ex = null;
        } while (ex != null);
      }
    }

  public String getHost()
  {
    return host;
  }

  public void setHost(String host)
  {
    this.host = host;
  }
  
}