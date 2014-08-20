/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.form.registro;

import org.apache.struts.action.ActionForm;

public class AsigDirecLabForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String laboratorio;
	private String idUsuario;
	private String idCargo;
	private String estado;
	private String nombre;
	private String desc;
	private String labos;
	private String id;
	private String fechaIni;
	private String fechaFin;
	private String fechaFinHidden;
	private String fechaIniHidden;
	private String idobt;
	
	public String getIdobt() {
		return idobt;
	}
	public void setIdobt(String idobt) {
		this.idobt = idobt;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabos() {
		return labos;
	}
	public void setLabos(String labos) {
		this.labos = labos;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getFechaFinHidden() {
		return fechaFinHidden;
	}
	public void setFechaFinHidden(String fechaFinHidden) {
		this.fechaFinHidden = fechaFinHidden;
	}
	public String getFechaIniHidden() {
		return fechaIniHidden;
	}
	public void setFechaIniHidden(String fechaIniHidden) {
		this.fechaIniHidden = fechaIniHidden;
	}
	

}
