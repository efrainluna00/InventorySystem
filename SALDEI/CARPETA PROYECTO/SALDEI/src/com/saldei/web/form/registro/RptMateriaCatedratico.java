/**
 * 
 */
package com.saldei.web.form.registro;

import org.apache.struts.action.ActionForm;

/**
 * @author Carlos
 *
 */
public class RptMateriaCatedratico extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String estado;
	private String formato;
	private String ciclo;
	private String materia;
	private String catedratico;
	/**
	 * @return the catedratico
	 */
	public String getCatedratico() {
		return catedratico;
	}
	/**
	 * @param catedratico the catedratico to set
	 */
	public void setCatedratico(String catedratico) {
		this.catedratico = catedratico;
	}
	/**
	 * @return the ciclo
	 */
	public String getCiclo() {
		return ciclo;
	}
	/**
	 * @param ciclo the ciclo to set
	 */
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
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
	 * @return the materia
	 */
	public String getMateria() {
		return materia;
	}
	/**
	 * @param materia the materia to set
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}
}
