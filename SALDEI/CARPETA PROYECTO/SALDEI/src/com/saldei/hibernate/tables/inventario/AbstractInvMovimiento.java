package com.saldei.hibernate.tables.inventario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvMovimiento generated by MyEclipse Persistence Tools
 */

public abstract class AbstractInvMovimiento implements java.io.Serializable {

	// Fields

	private InvMovimientoId id;
	private InvBodega invBodega;
	private InvTipoMov invTipoMov;
	private String tipoSol;
	private Integer codSol;
	private Date fechaMov;
	private String usuarioMov;
	private String comentario;
	private Set invDetMovimientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvMovimiento() {
	}

	/** minimal constructor */
	public AbstractInvMovimiento(InvMovimientoId id, InvBodega invBodega,
			InvTipoMov invTipoMov) {
		this.id = id;
		this.invBodega = invBodega;
		this.invTipoMov = invTipoMov;
	}

	/** full constructor */
	public AbstractInvMovimiento(InvMovimientoId id, InvBodega invBodega,
			InvTipoMov invTipoMov, String tipoSol, Integer codSol,
			Date fechaMov, String usuarioMov, String comentario,
			Set invDetMovimientos) {
		this.id = id;
		this.invBodega = invBodega;
		this.invTipoMov = invTipoMov;
		this.tipoSol = tipoSol;
		this.codSol = codSol;
		this.fechaMov = fechaMov;
		this.usuarioMov = usuarioMov;
		this.comentario = comentario;
		this.invDetMovimientos = invDetMovimientos;
	}

	// Property accessors

	public InvMovimientoId getId() {
		return this.id;
	}

	public void setId(InvMovimientoId id) {
		this.id = id;
	}

	public InvBodega getInvBodega() {
		return this.invBodega;
	}

	public void setInvBodega(InvBodega invBodega) {
		this.invBodega = invBodega;
	}

	public InvTipoMov getInvTipoMov() {
		return this.invTipoMov;
	}

	public void setInvTipoMov(InvTipoMov invTipoMov) {
		this.invTipoMov = invTipoMov;
	}

	public String getTipoSol() {
		return this.tipoSol;
	}

	public void setTipoSol(String tipoSol) {
		this.tipoSol = tipoSol;
	}

	public Integer getCodSol() {
		return this.codSol;
	}

	public void setCodSol(Integer codSol) {
		this.codSol = codSol;
	}

	public Date getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(Date fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getUsuarioMov() {
		return this.usuarioMov;
	}

	public void setUsuarioMov(String usuarioMov) {
		this.usuarioMov = usuarioMov;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Set getInvDetMovimientos() {
		return this.invDetMovimientos;
	}

	public void setInvDetMovimientos(Set invDetMovimientos) {
		this.invDetMovimientos = invDetMovimientos;
	}

}