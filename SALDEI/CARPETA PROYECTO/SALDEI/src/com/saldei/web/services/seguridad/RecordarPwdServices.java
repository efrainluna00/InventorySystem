/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.seguridad;

import java.util.List;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.mail.ConfigEmail;

public class RecordarPwdServices {
	
	private HibDAO dao = new HibDAOImpl();
	
	public boolean recordarPwd(String usuario) throws Exception{
		Usuario user = this.getPwd(usuario);
		if(user == null)
			return false;
		this.sendEmail(user);
		return true;		
	}
	
	private Usuario getPwd(String usuario) throws Exception{
		String hql = "from Usuario where lower(idUsuario) = ?";
		Object[] params = {usuario.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return null;
		return (Usuario) list.get(0);		
	}
	
	private void sendEmail(Usuario user) throws Exception{
		ConfigEmail email = new ConfigEmail();
		String correo = user.getEmail();
		String bodyString = "Ha sido ingresado al Sistema de Administracion de Laboratorios DEI. Su usario es: " + user.getIdUsuario() + " y su contraseña es: " + user.getPswUsuario();
		String subject = "Ingreso al Sistema de Administracion de Laboratorios SALDEI";
		email.notificarEmail(correo, bodyString, subject);
	}

}
