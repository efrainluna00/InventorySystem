/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.mail;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator{
	private String d_password;
	private String d_email;
	
	public SMTPAuthenticator(String email, String password){
		this.d_email = email;
		this.d_password = password;
	}
	
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(d_email, d_password);
    }

}
