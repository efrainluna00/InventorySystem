package com.saldei.hibernate.tables.activos;

// Generated by MyEclipse Persistence Tools

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * ActPresupuesto generated by MyEclipse Persistence Tools
 */
public class ActPresupuesto extends AbstractActPresupuesto implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String link;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Constructors

	/** default constructor */
	public ActPresupuesto() {
	}

	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param fechaCreacion
	 * @param estado
	 * @param usuarioMov
	 * @param actDetPresupuestos
	 */
	public ActPresupuesto(Date fechaInicial, Date fechaFinal,
			Date fechaCreacion, String estado, String usuarioMov,
			Set actDetPresupuestos) {
		super(fechaInicial, fechaFinal, fechaCreacion, estado, usuarioMov,
				actDetPresupuestos);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param estado
	 */
	public ActPresupuesto(String estado) {
		super(estado);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the link
	 */
	public String getPptoLink() {
		String link = " <a  href='#' target='_blank' onclick=\"javascript:enviarPpto('Find','"
				+ this.getCodPresupuesto()
				+ "','"
				+ sdf.format(this.getFechaInicial())
				+ "','"
				+ sdf.format(this.getFechaFinal())
				+ "','"
				+ this.getEstado()
				+ "');return false;\" >";
		link += " Cuentas Asociadas</a>";
		return link;
	}

	public String getFecha_ini() {
		if (this.getFechaInicial() != null)
			return sdf.format(this.getFechaInicial());
		else
			return "";
	}

	public String getFecha_fin() {
		if (this.getFechaFinal() != null)
			return sdf.format(this.getFechaFinal());
		else
			return "";

	}

	public void setFecha_ini(String fecha_ini) throws ParseException {
		if (fecha_ini != null && !fecha_ini.equals(""))
			this.setFechaInicial(sdf.parse(fecha_ini));
	}

	public void setFecha_fin(String fecha_fin) throws ParseException {
		if (fecha_fin != null && !fecha_fin.equals(""))
			this.setFechaFinal(sdf.parse(fecha_fin));
	}

}
