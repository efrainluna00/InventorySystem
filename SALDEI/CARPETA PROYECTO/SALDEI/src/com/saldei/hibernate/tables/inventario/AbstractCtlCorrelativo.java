package com.saldei.hibernate.tables.inventario;

/**
 * AbstractCtlCorrelativo generated by MyEclipse Persistence Tools
 */

public abstract class AbstractCtlCorrelativo implements java.io.Serializable {

	// Fields

	private String clave;
	private Integer anio;
	private long correlativo;

	// Constructors

	/** default constructor */
	public AbstractCtlCorrelativo() {
	}

	/** minimal constructor */
	public AbstractCtlCorrelativo(String clave) {
		this.clave = clave;
	}

	/** full constructor */
	public AbstractCtlCorrelativo(String clave, Integer anio, long correlativo) {
		this.clave = clave;
		this.anio = anio;
		this.correlativo = correlativo;
	}

	// Property accessors

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getAnio() {
		return this.anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public long getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

}