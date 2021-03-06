package com.saldei.hibernate.tables.inventario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvCategoria generated by MyEclipse Persistence Tools
 */

public abstract class AbstractInvCategoria implements java.io.Serializable {

	// Fields

	private String codCategoria;
	private String nombre;
	private Date fechaCreacion;
	private Set invSubCategorias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvCategoria() {
	}

	/** minimal constructor */
	public AbstractInvCategoria(String codCategoria, String nombre) {
		this.codCategoria = codCategoria;
		this.nombre = nombre;
	}

	/** full constructor */
	public AbstractInvCategoria(String codCategoria, String nombre,
			Date fechaCreacion, Set invSubCategorias) {
		this.codCategoria = codCategoria;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.invSubCategorias = invSubCategorias;
	}

	// Property accessors

	public String getCodCategoria() {
		return this.codCategoria;
	}

	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set getInvSubCategorias() {
		return this.invSubCategorias;
	}

	public void setInvSubCategorias(Set invSubCategorias) {
		this.invSubCategorias = invSubCategorias;
	}

}