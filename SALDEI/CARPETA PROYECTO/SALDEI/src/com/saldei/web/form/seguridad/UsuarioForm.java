package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class UsuarioForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String carnetEstudiante;
	private String codigoEmpleado;
	private String usr;
	private String pswUsuario;
	private String primerNom;
	private String primerApe;
	private String nombreRestante;
	private String apellidoRestante;
	private String email;
	private String fechaNac;
	private String direccionPart;
	private String telefonoCasa;
	private String telefonoCel;
	private String telefonoTrabajo;	
	private String iniVigencia;
    private String finVigencia;
    private String sexo;
    private String estadoCivil;
	private String tipo;
	private String tipoHidden;
	private String ext;
	private String comentario;
	private String estado;	
	private String oyente;
	private String nit;
	private String dui;
	private String isss;
	private String nup;
	private String solicitadoPor;
	private String cicloIngreso;
    private String anyoIngreso;
    private String autorizadoPor;
    private String idCarrera;
    
    
	public String getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(String idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(String finVigencia) {
		this.finVigencia = finVigencia;
	}
	public String getIniVigencia() {
		return iniVigencia;
	}
	public void setIniVigencia(String iniVigencia) {
		this.iniVigencia = iniVigencia;
	}
	public String getPswUsuario() {
		return pswUsuario;
	}
	public void setPswUsuario(String pswUsuario) {
		this.pswUsuario = pswUsuario;
	}
	public String getNombreRestante() {
		return nombreRestante;
	}
	public void setNombreRestante(String nombreRestante) {
		this.nombreRestante = nombreRestante;
	}
	public String getApellidoRestante() {
		return apellidoRestante;
	}
	public void setApellidoRestante(String apellidoRestante) {
		this.apellidoRestante = apellidoRestante;
	}
	public String getDireccionPart() {
		return direccionPart;
	}
	public void setDireccionPart(String direccionPart) {
		this.direccionPart = direccionPart;
	}
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
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
	public String getTipoHidden() {
		return tipoHidden;
	}
	public void setTipoHidden(String tipoHidden) {
		this.tipoHidden = tipoHidden;
	}
	public String getAnyoIngreso() {
		return anyoIngreso;
	}
	public void setAnyoIngreso(String anyoIngreso) {
		this.anyoIngreso = anyoIngreso;
	}
	public String getAutorizadoPor() {
		return autorizadoPor;
	}
	public void setAutorizadoPor(String autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}
	public String getCicloIngreso() {
		return cicloIngreso;
	}
	public void setCicloIngreso(String cicloIngreso) {
		this.cicloIngreso = cicloIngreso;
	}
	public String getSolicitadoPor() {
		return solicitadoPor;
	}
	public void setSolicitadoPor(String solicitadoPor) {
		this.solicitadoPor = solicitadoPor;
	}
	public String getCarnetEstudiante() {
		return carnetEstudiante;
	}
	public void setCarnetEstudiante(String carnetEstudiante) {
		this.carnetEstudiante = carnetEstudiante;
	}
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	
	
}

