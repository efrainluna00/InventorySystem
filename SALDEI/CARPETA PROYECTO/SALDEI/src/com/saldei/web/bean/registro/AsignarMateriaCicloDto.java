package com.saldei.web.bean.registro;

public class AsignarMateriaCicloDto {
	
	private static final long serialVersionUID = 1L;
	private String ciclo;
	private String año;
	private String seccion;
	private String materia;
	private String accion;
	private String idMateria;
	private String estado;
	private String pkMat;
	
	
	public String getPkMat() {
		return pkMat;
	}

	public void setPkMat(String pkMat) {
		this.pkMat = pkMat;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public void setCiclo(String ciclo){
		this.ciclo = ciclo;
	}

	public String getCiclo(){
		return ciclo;
	}

}
