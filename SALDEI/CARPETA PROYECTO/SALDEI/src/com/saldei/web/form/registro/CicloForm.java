package com.saldei.web.form.registro;

import org.apache.struts.action.ActionForm;

public class CicloForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String idCiclo;
	private int    numCiclo;
	private String anyoCiclo;
	private String fechaIni;
	private String fechaFin;
	private String cicloActivo;
	private String estCiclo;
	
	public String getAnyoCiclo() {
		return anyoCiclo;
	}
	public void setAnyoCiclo(String anyoCiclo) {
		this.anyoCiclo = anyoCiclo;
	}
	public String getCicloActivo() {
		return cicloActivo;
	}
	public void setCicloActivo(String cicloActivo) {
		this.cicloActivo = cicloActivo;
	}
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
	public int getNumCiclo() {
		return numCiclo;
	}
	public void setNumCiclo(int numCiclo) {
		this.numCiclo = numCiclo;
	}
	public String getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(String idCiclo) {
		this.idCiclo = idCiclo;
	}
	public String getEstCiclo() {
		return estCiclo;
	}
	public void setEstCiclo(String estCiclo) {
		this.estCiclo = estCiclo;
	}
	
}
