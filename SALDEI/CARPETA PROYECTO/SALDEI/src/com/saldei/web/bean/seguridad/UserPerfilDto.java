/**
 * Proyecto: SATC
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.bean.seguridad;

public class UserPerfilDto {
	
	private static final long serialVersionUID = 1L;
	private String accion;
	private String idUsuario;
	private String nombreUsuario;
	private String apellido;
	private String nombrePerfil;
	private String idPerfil;
	
	public String getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	

}
