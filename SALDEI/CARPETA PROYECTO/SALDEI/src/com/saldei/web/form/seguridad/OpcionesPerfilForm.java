package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class OpcionesPerfilForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String perfil;
	private String idPerfil;
	private String nomPerfil;
	private String idOpcion;
	private String nombreOpcion;
	private String descripcion;
	private String accion;	
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreOpcion() {
		return nombreOpcion;
	}
	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}
	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}
	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNomPerfil() {
		return nomPerfil;
	}
	public void setNomPerfil(String nomPerfil) {
		this.nomPerfil = nomPerfil;
	}
	public String getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(String  idOpcion) {
		this.idOpcion = idOpcion;
	}
}
