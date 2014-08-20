package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class RptAuditoriaForm extends ActionForm {
	private static final long serialVersionUID = 1L;	
	private String fechainicial;
	private String fechafinal;
	private String usuario;
	private String tabla;
	private String formato;
	
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
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}
	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
