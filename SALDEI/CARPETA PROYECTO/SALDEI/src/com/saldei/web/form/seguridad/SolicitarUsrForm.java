package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class SolicitarUsrForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String carnetEstudiante;
	private String codigoEmpleado;
	private String primerNom;
	private String primerApe;
	private String nombreRestante;
	private String apellidoRestante;
	private String email;
	private String fechaNac;
	private String direccionPart;
	private String telefonoCasa;
	private String telefonoTrabajo;
	private String telefonoCel;
	private String tipo;
	private String tipoHidden;
	private String ext;
	private String comentario;
    private String sexo;
    private String estadoCivil;
    private String idCarrera;
	private String solicitadoPor;
	private String cicloIngreso;
    private String anyoIngreso;
	private String oyente;
	private String nit;
	private String dui;
	private String isss;
	private String nup;
		
	public SolicitarUsrForm() {
		super();
	}
	
	public String getApellidoRestante() {
		return apellidoRestante;
	}
	public void setApellidoRestante(String apellidoRestante) {
		this.apellidoRestante = apellidoRestante;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getDireccionPart() {
		return direccionPart;
	}
	public void setDireccionPart(String direccionPart) {
		this.direccionPart = direccionPart;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
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
	public String getIsss() {
		return isss;
	}
	public void setIsss(String isss) {
		this.isss = isss;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombreRestante() {
		return nombreRestante;
	}
	public void setNombreRestante(String nombreRestante) {
		this.nombreRestante = nombreRestante;
	}
	public String getNup() {
		return nup;
	}
	public void setNup(String nup) {
		this.nup = nup;
	}
	public String getOyente() {
		return oyente;
	}
	public void setOyente(String oyente) {
		this.oyente = oyente;
	}
	public String getPrimerApe() {
		return primerApe;
	}
	public void setPrimerApe(String primerApe) {
		this.primerApe = primerApe;
	}
	public String getPrimerNom() {
		return primerNom;
	}
	public void setPrimerNom(String primerNom) {
		this.primerNom = primerNom;
	}
	public String getAnyoIngreso() {
		return anyoIngreso;
	}

	public void setAnyoIngreso(String anyoIngreso) {
		this.anyoIngreso = anyoIngreso;
	}

	public String getCarnetEstudiante() {
		return carnetEstudiante;
	}

	public void setCarnetEstudiante(String carnetEstudiante) {
		this.carnetEstudiante = carnetEstudiante;
	}

	public String getCicloIngreso() {
		return cicloIngreso;
	}

	public void setCicloIngreso(String cicloIngreso) {
		this.cicloIngreso = cicloIngreso;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getSolicitadoPor() {
		return solicitadoPor;
	}

	public void setSolicitadoPor(String solicitadoPor) {
		this.solicitadoPor = solicitadoPor;
	}

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	public String getTelefonoCel() {
		return telefonoCel;
	}
	public void setTelefonoCel(String telefonoCel) {
		this.telefonoCel = telefonoCel;
	}
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipoHidden() {
		return tipoHidden;
	}
	public void setTipoHidden(String tipoHidden) {
		this.tipoHidden = tipoHidden;
	}

		
	
}
