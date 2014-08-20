package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class RptUsuariosForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String estado;
	private String formato;
	private String fechainicial;
	private String fechafinal;
	private String tipousuario;
	
	
	
	/**
	 * @return the tipousuario
	 */
	public String getTipousuario() {
		return tipousuario;
	}
	/**
	 * @param tipousuario the tipousuario to set
	 */
	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}
	/**
	 * @return the fechafinal
	 */
	public String getFechafinal() {
		return fechafinal;
	}
	/**
	 * @param fechafinal the fechafinal to set
	 */
	public void setFechafinal(String fechafinal) {
		this.fechafinal = fechafinal;
	}
	/**
	 * @return the fechainicial
	 */
	public String getFechainicial() {
		return fechainicial;
	}
	/**
	 * @param fechainicial the fechainicial to set
	 */
	public void setFechainicial(String fechainicial) {
		this.fechainicial = fechainicial;
	}
	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}
	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
