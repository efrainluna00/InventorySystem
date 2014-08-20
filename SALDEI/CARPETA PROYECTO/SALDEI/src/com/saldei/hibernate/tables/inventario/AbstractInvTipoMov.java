package com.saldei.hibernate.tables.inventario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTipoMov generated by MyEclipse Persistence Tools
 */

public abstract class AbstractInvTipoMov implements java.io.Serializable {

	// Fields

	private Integer codTipoMov;
	private String descripcion;
	private String operacion;
	private Date fechaCreacion;
	private Set invMovimientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTipoMov() {
	}

	/** minimal constructor */
	public AbstractInvTipoMov(Integer codTipoMov) {
		this.codTipoMov = codTipoMov;
	}

	/** full constructor */
	public AbstractInvTipoMov(Integer codTipoMov, String descripcion,
			String operacion, Date fechaCreacion, Set invMovimientos) {
		this.codTipoMov = codTipoMov;
		this.descripcion = descripcion;
		this.operacion = operacion;
		this.fechaCreacion = fechaCreacion;
		this.invMovimientos = invMovimientos;
	}

	// Property accessors

	public Integer getCodTipoMov() {
		return this.codTipoMov;
	}

	public void setCodTipoMov(Integer codTipoMov) {
		this.codTipoMov = codTipoMov;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set getInvMovimientos() {
		return this.invMovimientos;
	}

	public void setInvMovimientos(Set invMovimientos) {
		this.invMovimientos = invMovimientos;
	}

}