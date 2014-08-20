package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

/**
 * Fomulario para la Asignacion de Perfil
 * @author WiRoCaRo
 * @version 1.0
 */
public class AsignarPerfilForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String usr;
	private String userPerfil;
	private String perfiles;
	private String idUsuario;
	private String idPerfil;
	
	
	public String getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(String perfiles) {
		this.perfiles = perfiles;
	}
	public String getUserPerfil() {
		return userPerfil;
	}
	public void setUserPerfil(String userPerfil) {
		this.userPerfil = userPerfil;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}	
}
