package com.saldei.web.form.administracion;

import org.apache.struts.action.ActionForm;

public class MedidaForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String abrev;
	private String codigoHidden;
	private String factor;
	private String tipo;
	private String estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getCodigoHidden() {
		return codigoHidden;
	}
	public void setCodigoHidden(String codigoHidden) {
		this.codigoHidden = codigoHidden;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
