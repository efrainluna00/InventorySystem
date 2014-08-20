/**
 * 
 */
package com.saldei.web.form.registro;

import org.apache.struts.action.ActionForm;

/**
 * @author Carlos
 *
 */
public class AsignarMateriaEstudianteForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String materia;
	private String carnet;
	private String apellido;
	private String carnetid;
	/**
	 * @return the carnetid
	 */
	public String getCarnetid() {
		return carnetid;
	}
	/**
	 * @param carnetid the carnetid to set
	 */
	public void setCarnetid(String carnetid) {
		this.carnetid = carnetid;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the carnet
	 */
	public String getCarnet() {
		return carnet;
	}
	/**
	 * @param carnet the carnet to set
	 */
	public void setCarnet(String carnet) {
		this.carnet = carnet;
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
