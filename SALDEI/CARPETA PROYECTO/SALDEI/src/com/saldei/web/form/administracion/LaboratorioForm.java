/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.form.administracion;

import org.apache.struts.action.ActionForm;

public class LaboratorioForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String idLaboratorio;
	private String nombreLaboratorio;
	private String abrevLaboratorio;
	private String numFilas;
	private String numColumnas;
	private String estLaboratorio;
	public String getAbrevLaboratorio() {
		return abrevLaboratorio;
	}
	public void setAbrevLaboratorio(String abrevLaboratorio) {
		this.abrevLaboratorio = abrevLaboratorio;
	}
	public String getEstLaboratorio() {
		return estLaboratorio;
	}
	public void setEstLaboratorio(String estLaboratorio) {
		this.estLaboratorio = estLaboratorio;
	}
	public String getIdLaboratorio() {
		return idLaboratorio;
	}
	public void setIdLaboratorio(String idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}
	public String getNombreLaboratorio() {
		return nombreLaboratorio;
	}
	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}
	public String getNumColumnas() {
		return numColumnas;
	}
	public void setNumColumnas(String numColumnas) {
		this.numColumnas = numColumnas;
	}
	public String getNumFilas() {
		return numFilas;
	}
	public void setNumFilas(String numFilas) {
		this.numFilas = numFilas;
	}
	
}
