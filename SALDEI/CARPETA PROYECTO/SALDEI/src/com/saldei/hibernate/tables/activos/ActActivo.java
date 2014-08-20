package com.saldei.hibernate.tables.activos;

// Generated by MyEclipse Persistence Tools

import java.util.Date;
import java.util.Set;

/**
 * ActActivo generated by MyEclipse Persistence Tools
 */
public class ActActivo extends AbstractActActivo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ActActivo() {
	}

	/**
	 * @param codActivo
	 * @param actUnidad
	 * @param codRecurso
	 * @param estado
	 */
	public ActActivo(Integer codActivo, String codRecurso, String estado) {
		super(codActivo, codRecurso, estado);
		// TODO Auto-generated constructor stub
	}

	public ActActivo(ActUnidad actUnidad, ActMotivoBaja actMotivoBaja) {
		this.setActUnidad(actUnidad);
		this.setActMotivoBaja(actMotivoBaja);
		// TODO Auto-generated constructor stub
	}

	public ActActivo(ActMotivoBaja actMotivoBaja) {
		this.setActMotivoBaja(actMotivoBaja);
		// TODO Auto-generated constructor stub
	}

	public ActActivo(ActUnidad actUnidad, ActMotivoBaja actMotivoBaja,
			ActEstadoActivo actEstadoActivo) {
		this.setActUnidad(actUnidad);
		this.setActMotivoBaja(actMotivoBaja);
		this.setActEstadoActivo(actEstadoActivo);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codActivo
	 * @param actUnidad
	 * @param actMotivoBaja
	 * @param actEstadoActivo
	 * @param fechaCreacion
	 * @param codRecurso
	 * @param creadoPor
	 * @param codAltUca
	 * @param numSerie
	 * @param fechaBaja
	 * @param usuarioBaja
	 * @param estado
	 * @param motivo
	 * @param actDetSolicituds
	 * @param actHistUnidadActivos
	 */
	public ActActivo(Integer codActivo, ActUnidad actUnidad,
			ActMotivoBaja actMotivoBaja, ActEstadoActivo actEstadoActivo,
			Date fechaCreacion, String codRecurso, String creadoPor,
			String codAltUca, String numSerie, Date fechaBaja,
			String usuarioBaja, String estado, String motivo,
			Set actDetSolicituds, Set actHistUnidadActivos) {
		super(codActivo, actUnidad, actMotivoBaja, actEstadoActivo,
				fechaCreacion, codRecurso, creadoPor, codAltUca, numSerie,
				fechaBaja, usuarioBaja, estado, motivo, actDetSolicituds,
				actHistUnidadActivos);
		// TODO Auto-generated constructor stub
	}

}