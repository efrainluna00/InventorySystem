package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class OrganizacionDEIForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String usr;
	private String idUsuario;
	private String cargo;
	private String nombreCargo;
	private String descripcion;
	private String accion;
	private String fechaIni;
	private String fechaFin;
	private String idobt;
	
	
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
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
	public String getNombreCargo() {
		return nombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}
	public String getIdobt() {
		return idobt;
	}
	public void setIdobt(String idobt) {
		this.idobt = idobt;
	}
	
	
}
