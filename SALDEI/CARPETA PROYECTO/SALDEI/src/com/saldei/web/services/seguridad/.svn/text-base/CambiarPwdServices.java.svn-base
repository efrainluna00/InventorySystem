package com.saldei.web.services.seguridad;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.form.seguridad.CambiarPwdForm;


/**
 * Servicios de Cambiar Contraseña   
 * @author WiRoCaRo
 * @version 1.0
 */
public class CambiarPwdServices {
	private HibDAO dao = new HibDAOImpl();
	
		
	/**
	 * Valida si un Formulario es Nulo 
	 * @param form Formulario
	 * @return True | False
	 */	
	public boolean isNullForm(CambiarPwdForm form){
		try{
			if (form.getPwdActual() == null  || form.getPwdActual().equals(""))
				return false;
			if (form.getPwdNew() == null     || form.getPwdNew().equals(""))
				return false;
			if (form.getPwdConfirm() == null || form.getPwdConfirm().equals(""))
				return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Cambia la Contraseña en la BD
	 * @param user Contenedor de Usuario de la BD
	 * @param pwd  Nueva Contraseña
	 * @return True | False
	 */
	public boolean changePwd(Usuario user){
		try {
			String pwd = user.getPswUsuario();
			//	TODO Encriptar Psw
			user.setPswUsuario(pwd);
			dao.update(user);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
