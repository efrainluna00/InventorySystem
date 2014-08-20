/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.mail;

import javax.mail.PasswordAuthentication;

public class Autenticacion extends javax.mail.Authenticator{

    public PasswordAuthentication getPasswordAuthentication()
    {

        String username = "saldei@buho.uca.edu.sv";

        String password = "3alumnos1tesis";

        return new PasswordAuthentication(username, password);

    }

}
