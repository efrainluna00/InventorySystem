package com.saldei.hibernate.tables.activos;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractActCotizacion generated by MyEclipse Persistence Tools
 */

public abstract class AbstractActCotizacion implements java.io.Serializable {

	// Fields

	private ActCotizacionId id;
	private ActProveedor actProveedor;
	private String urlCotizacion;
	private String estado;
	private String comentario;
	private String iva;
	private Set actDetCotizacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractActCotizacion() {
	}

	/** minimal constructor */
	public AbstractActCotizacion(ActCotizacionId id, ActProveedor actProveedor) {
		this.id = id;
		this.actProveedor = actProveedor;
	}

	/** full constructor */
	public AbstractActCotizacion(ActCotizacionId id, ActProveedor actProveedor,
			String urlCotizacion, String estado, String comentario, String iva,
			Set actDetCotizacions) {
		this.id = id;
		this.actProveedor = actProveedor;
		this.urlCotizacion = urlCotizacion;
		this.estado = estado;
		this.comentario = comentario;
		this.iva = iva;
		this.actDetCotizacions = actDetCotizacions;
	}

	// Property accessors

	public ActCotizacionId getId() {
		return this.id;
	}

	public void setId(ActCotizacionId id) {
		this.id = id;
	}

	public ActProveedor getActProveedor() {
		return this.actProveedor;
	}

	public void setActProveedor(ActProveedor actProveedor) {
		this.actProveedor = actProveedor;
	}

	public String getUrlCotizacion() {
		return this.urlCotizacion;
	}

	public void setUrlCotizacion(String urlCotizacion) {
		this.urlCotizacion = urlCotizacion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getIva() {
		return this.iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public Set getActDetCotizacions() {
		return this.actDetCotizacions;
	}

	public void setActDetCotizacions(Set actDetCotizacions) {
		this.actDetCotizacions = actDetCotizacions;
	}

}