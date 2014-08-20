package com.saldei.web.services.seguridad;

import java.util.List;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.form.seguridad.LoginForm;

/**
 * Servicios para el Login de el Sistema
 * @author WiRoCaRo
 * @version 1.0
 */
public class LoginServices {
	
	private HibDAO dao = new HibDAOImpl();
	private Util util  = new Util();
	
	/**
	 * Valida si un Formulario es Valido
	 * @param form Formulario de Pantalla
	 * @return True | False
	 */
	public boolean isNullForm(LoginForm p_form){
		if(p_form.getIdUsuario().trim().equals("") || p_form.getPswUsuario().trim().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Valida si un Usuario y Contraseña exiten en la BD
	 * @param form Formulario 
	 * @return True | False
	 */
	public boolean isUsrExist(LoginForm p_form){
		try {
			String hql = QuerysSeguridad.queryLoginExist(p_form);
			List lstUsr = dao.find(hql);
			if(lstUsr != null && lstUsr.size() >0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** 
	 * Valida que un Usuario tenga los permisos para  entrar al sistema
	 * @param form Formulario
	 * @return True | False
	 */
	public Usuario isUsrValid(LoginForm p_form){
		try {
			String hql = QuerysSeguridad.queryLoginValid(p_form);
			List lstUsr = dao.find(hql); 
			if(lstUsr != null && lstUsr.size() >0){
			 return (Usuario) lstUsr.get(0);	
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	/**
	 * Obtiene los Perfiles de un usuario en formato de String separados por comas si hay mas de uno
	 * @param iform form de la pantalla
	 * @return 
	 */
	public String getPerfilUsr(LoginForm p_form){
		try {
			String hql  = QuerysSeguridad.getPerfilUsr(p_form.getIdUsuario(), "A");
			List lstPerfilUsr = dao.find(hql);
			String strPerfil = util.getListToString(lstPerfilUsr, ",");
			return strPerfil;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "";
	}
}

