package com.saldei.hibernate.tables.inventario;

// Generated by MyEclipse Persistence Tools

import java.util.Date;
import java.util.Set;

/**
 * InvRecurso generated by MyEclipse Persistence Tools
 */
public class InvRecurso extends AbstractInvRecurso implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvRecurso() {
	}

	/**
	 * @param codRecurso
	 * @param invSubCategoria
	 * @param nombre
	 * @param codUnidadMedida
	 * @param serializable
	 */
	public InvRecurso(String codRecurso, InvSubCategoria invSubCategoria,
			String nombre, Integer codUnidadMedida, String serializable) {
		super(codRecurso, invSubCategoria, nombre, codUnidadMedida,
				serializable);
		// TODO Auto-generated constructor stub
	}

	public InvRecurso(InvSubCategoria invSubCategoria) {
		super.setInvSubCategoria(invSubCategoria);
	}

	/**
	 * @return the descConsumible
	 */
	public String getDescConsumible() {
		String desc = (this.getConsumible().equals("S")) ? "Si" : "No";
		return desc;
	}

	/**
	 * @param codRecurso
	 * @param invSubCategoria
	 * @param nombre
	 * @param creadoPor
	 * @param fechaCreacion
	 * @param codUnidadMedida
	 * @param minimo
	 * @param serializable
	 * @param consumible
	 * @param usuarioMov
	 * @param marca
	 * @param modelo
	 * @param invExistencias
	 * @param invDetMovimientos
	 */
	public InvRecurso(String codRecurso, InvSubCategoria invSubCategoria,
			String nombre, String creadoPor, Date fechaCreacion,
			Integer codUnidadMedida, Integer minimo, String serializable,
			String consumible, String usuarioMov, String marca, String modelo,
			Set invExistencias, Set invDetMovimientos) {
		super(codRecurso, invSubCategoria, nombre, creadoPor, fechaCreacion,
				codUnidadMedida, minimo, serializable, consumible, usuarioMov,
				marca, modelo, invExistencias, invDetMovimientos);
		// TODO Auto-generated constructor stub
	}

	public InvRecurso(String codRecurso) {
		this.setCodRecurso(codRecurso);
	}

}
