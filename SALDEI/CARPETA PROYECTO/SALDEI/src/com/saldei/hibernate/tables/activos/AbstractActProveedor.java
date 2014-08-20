package com.saldei.hibernate.tables.activos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractActProveedor generated by MyEclipse Persistence Tools
 */

public abstract class AbstractActProveedor implements java.io.Serializable {

	// Fields

	private String codProveedor;
	private String nombre;
	private String direccion;
	private String nit;
	private String fax;
	private String telefono;
	private String EMail;
	private String webSite;
	private String regFiscal;
	private String giro;
	private String estado;
	private Date fechaCreacion;
	private String contacto;
	private Set actCotizacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractActProveedor() {
	}

	/** minimal constructor */
	public AbstractActProveedor(String codProveedor, String nombre,
			String estado) {
		this.codProveedor = codProveedor;
		this.nombre = nombre;
		this.estado = estado;
	}

	/** full constructor */
	public AbstractActProveedor(String codProveedor, String nombre,
			String direccion, String nit, String fax, String telefono,
			String EMail, String webSite, String regFiscal, String giro,
			String estado, Date fechaCreacion, String contacto,
			Set actCotizacions) {
		this.codProveedor = codProveedor;
		this.nombre = nombre;
		this.direccion = direccion;
		this.nit = nit;
		this.fax = fax;
		this.telefono = telefono;
		this.EMail = EMail;
		this.webSite = webSite;
		this.regFiscal = regFiscal;
		this.giro = giro;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.contacto = contacto;
		this.actCotizacions = actCotizacions;
	}

	// Property accessors

	public String getCodProveedor() {
		return this.codProveedor;
	}

	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getRegFiscal() {
		return this.regFiscal;
	}

	public void setRegFiscal(String regFiscal) {
		this.regFiscal = regFiscal;
	}

	public String getGiro() {
		return this.giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public Set getActCotizacions() {
		return this.actCotizacions;
	}

	public void setActCotizacions(Set actCotizacions) {
		this.actCotizacions = actCotizacions;
	}

}