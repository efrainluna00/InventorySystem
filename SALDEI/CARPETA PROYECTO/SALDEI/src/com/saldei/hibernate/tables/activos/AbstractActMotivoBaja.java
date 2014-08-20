package com.saldei.hibernate.tables.activos;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractActMotivoBaja generated by MyEclipse Persistence Tools
 */

public abstract class AbstractActMotivoBaja implements java.io.Serializable {

	// Fields

	private Integer codBaja;
	private String descripcion;
	private Set actActivos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractActMotivoBaja() {
	}

	/** minimal constructor */
	public AbstractActMotivoBaja(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public AbstractActMotivoBaja(String descripcion, Set actActivos) {
		this.descripcion = descripcion;
		this.actActivos = actActivos;
	}

	// Property accessors

	public Integer getCodBaja() {
		return this.codBaja;
	}

	public void setCodBaja(Integer codBaja) {
		this.codBaja = codBaja;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getActActivos() {
		return this.actActivos;
	}

	public void setActActivos(Set actActivos) {
		this.actActivos = actActivos;
	}

}