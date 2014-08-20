package com.saldei.web.bean.registro;

public class VigenciaDto {

	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String idCarrera;
	private String planEstudio;
	private String fechaIni;
	private String fechaFin;
	
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
	public String getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(String idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPlanEstudio() {
		return planEstudio;
	}
	public void setPlanEstudio(String planEstudio) {
		this.planEstudio = planEstudio;
	}
	
}
