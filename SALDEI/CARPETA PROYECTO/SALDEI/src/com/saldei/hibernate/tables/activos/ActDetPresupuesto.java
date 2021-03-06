package com.saldei.hibernate.tables.activos;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

// Generated by MyEclipse Persistence Tools

/**
 * ActDetPresupuesto generated by MyEclipse Persistence Tools
 */
public class ActDetPresupuesto extends AbstractActDetPresupuesto implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -5246095086101955763L;

	/** default constructor */
	public ActDetPresupuesto() {
	}

	/** minimal constructor */
	public ActDetPresupuesto(ActDetPresupuestoId id) {
		super(id);
	}

	/**
	 * @param id
	 * @param monto
	 * @param saldoActual
	 * @param usuarioMov
	 * @param actSolicituds
	 */
	public ActDetPresupuesto(ActDetPresupuestoId id, double monto,
			double saldoActual, String usuarioMov, Set actSolicituds) {
		super(id, monto, saldoActual, usuarioMov, actSolicituds);
		// TODO Auto-generated constructor stub
	}

}
