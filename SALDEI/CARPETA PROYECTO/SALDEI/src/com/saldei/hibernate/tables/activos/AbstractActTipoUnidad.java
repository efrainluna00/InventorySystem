package com.saldei.hibernate.tables.activos;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractActTipoUnidad generated by MyEclipse Persistence Tools
 */

public abstract class AbstractActTipoUnidad implements java.io.Serializable {

	// Fields

	private Integer codTipoUnidad;
	private String descripcion;
	private Set actUnidads = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractActTipoUnidad() {
	}

	/** full constructor */
	public AbstractActTipoUnidad(String descripcion, Set actUnidads) {
		this.descripcion = descripcion;
		this.actUnidads = actUnidads;
	}

	// Property accessors

	public Integer getCodTipoUnidad() {
		return this.codTipoUnidad;
	}

	public void setCodTipoUnidad(Integer codTipoUnidad) {
		this.codTipoUnidad = codTipoUnidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getActUnidads() {
		return this.actUnidads;
	}

	public void setActUnidads(Set actUnidads) {
		this.actUnidads = actUnidads;
	}

}