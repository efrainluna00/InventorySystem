/**
 * 
 */
package com.saldei.web.form.administracion;

import org.apache.struts.action.ActionForm;

/**
 * @author Carlos
 *
 */
public class RptLaboratorioForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String estado;
	private String formato;
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
}
