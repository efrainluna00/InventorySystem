package com.saldei.hibernate.tables.activos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractActSolicitud generated by MyEclipse Persistence Tools
 */

public abstract class AbstractActSolicitud implements java.io.Serializable {

	// Fields

	private ActSolicitudId id;
	private ActUnidad actUnidad;
	private ActDetPresupuesto actDetPresupuesto;
	private String codSolicitante;
	private String estado;
	private Integer codBodega;
	private Date fechaCreacion;
	private Date fechaIniPrestamo;
	private Date fechaFinPrestamo;
	private String codPropietario;
	private String comentario;
	private String motivoRechazo;
	private String motivoAnulacion;
	private Date fechaAprobacion;
	private Date fechaAnulacion;
	private Date fechaFinalizacion;
	private String asigCompra;
	private String comentarioTecnico;
	private Set actDetSolicituds = new HashSet(0);
	private Set actCotizacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractActSolicitud() {
	}

	/** minimal constructor */
	public AbstractActSolicitud(ActSolicitudId id, String estado) {
		this.id = id;
		this.estado = estado;
	}

	/** full constructor */
	public AbstractActSolicitud(ActSolicitudId id, ActUnidad actUnidad,
			ActDetPresupuesto actDetPresupuesto, String codSolicitante,
			String estado, Integer codBodega, Date fechaCreacion,
			Date fechaIniPrestamo, Date fechaFinPrestamo,
			String codPropietario, String comentario, String motivoRechazo,
			String motivoAnulacion, Date fechaAprobacion, Date fechaAnulacion,
			Date fechaFinalizacion, String asigCompra,
			String comentarioTecnico, Set actDetSolicituds, Set actCotizacions) {
		this.id = id;
		this.actUnidad = actUnidad;
		this.actDetPresupuesto = actDetPresupuesto;
		this.codSolicitante = codSolicitante;
		this.estado = estado;
		this.codBodega = codBodega;
		this.fechaCreacion = fechaCreacion;
		this.fechaIniPrestamo = fechaIniPrestamo;
		this.fechaFinPrestamo = fechaFinPrestamo;
		this.codPropietario = codPropietario;
		this.comentario = comentario;
		this.motivoRechazo = motivoRechazo;
		this.motivoAnulacion = motivoAnulacion;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaAnulacion = fechaAnulacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.asigCompra = asigCompra;
		this.comentarioTecnico = comentarioTecnico;
		this.actDetSolicituds = actDetSolicituds;
		this.actCotizacions = actCotizacions;
	}

	// Property accessors

	public ActSolicitudId getId() {
		return this.id;
	}

	public void setId(ActSolicitudId id) {
		this.id = id;
	}

	public ActUnidad getActUnidad() {
		return this.actUnidad;
	}

	public void setActUnidad(ActUnidad actUnidad) {
		this.actUnidad = actUnidad;
	}

	public ActDetPresupuesto getActDetPresupuesto() {
		return this.actDetPresupuesto;
	}

	public void setActDetPresupuesto(ActDetPresupuesto actDetPresupuesto) {
		this.actDetPresupuesto = actDetPresupuesto;
	}

	public String getCodSolicitante() {
		return this.codSolicitante;
	}

	public void setCodSolicitante(String codSolicitante) {
		this.codSolicitante = codSolicitante;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCodBodega() {
		return this.codBodega;
	}

	public void setCodBodega(Integer codBodega) {
		this.codBodega = codBodega;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaIniPrestamo() {
		return this.fechaIniPrestamo;
	}

	public void setFechaIniPrestamo(Date fechaIniPrestamo) {
		this.fechaIniPrestamo = fechaIniPrestamo;
	}

	public Date getFechaFinPrestamo() {
		return this.fechaFinPrestamo;
	}

	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	public String getCodPropietario() {
		return this.codPropietario;
	}

	public void setCodPropietario(String codPropietario) {
		this.codPropietario = codPropietario;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getMotivoRechazo() {
		return this.motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getMotivoAnulacion() {
		return this.motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public Date getFechaAprobacion() {
		return this.fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public Date getFechaAnulacion() {
		return this.fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getAsigCompra() {
		return this.asigCompra;
	}

	public void setAsigCompra(String asigCompra) {
		this.asigCompra = asigCompra;
	}

	public String getComentarioTecnico() {
		return this.comentarioTecnico;
	}

	public void setComentarioTecnico(String comentarioTecnico) {
		this.comentarioTecnico = comentarioTecnico;
	}

	public Set getActDetSolicituds() {
		return this.actDetSolicituds;
	}

	public void setActDetSolicituds(Set actDetSolicituds) {
		this.actDetSolicituds = actDetSolicituds;
	}

	public Set getActCotizacions() {
		return this.actCotizacions;
	}

	public void setActCotizacions(Set actCotizacions) {
		this.actCotizacions = actCotizacions;
	}

}