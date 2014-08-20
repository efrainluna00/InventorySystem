/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.activos;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.activos.ActActivo;
import com.saldei.hibernate.tables.activos.ActCotizacion;
import com.saldei.hibernate.tables.activos.ActCotizacionId;
import com.saldei.hibernate.tables.activos.ActDetCotizacion;
import com.saldei.hibernate.tables.activos.ActDetCotizacionId;
import com.saldei.hibernate.tables.activos.ActDetSolicitud;
import com.saldei.hibernate.tables.activos.ActDetSolicitudId;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudId;

/** 
 * MyEclipse Struts
 * Creation date: 06-25-2009
 * 
 * XDoclet definition:
 * @struts.form name="solicitudCompraDetForm"
 */
public class SolicitudCompraDetForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** actDetSolicitud property */
	private ActDetSolicitud actDetSolicitud =  new ActDetSolicitud(new ActDetSolicitudId(new ActSolicitud(new ActSolicitudId())),new ActActivo());
	private ActCotizacion actCotizacion = new ActCotizacion();
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.actDetSolicitud =  new ActDetSolicitud(new ActDetSolicitudId(new ActSolicitud(new ActSolicitudId())),new ActActivo());
	}

	/** 
	 * Returns the actDetSolicitud.
	 * @return ActDetSolicitud
	 */
	public ActDetSolicitud getActDetSolicitud() {
		return actDetSolicitud;
	}

	/** 
	 * Set the actDetSolicitud.
	 * @param actDetSolicitud The actDetSolicitud to set
	 */
	public void setActDetSolicitud(ActDetSolicitud actDetSolicitud) {
		this.actDetSolicitud = actDetSolicitud;
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#getActActivo()
	 */
	public ActActivo getActActivo() {
		return actDetSolicitud.getActActivo();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#getCantidad()
	 */
	public Integer getCantidad() {
		return actDetSolicitud.getCantidad();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#getCodRecurso()
	 */
	public String getCodRecurso() {
		return actDetSolicitud.getCodRecurso();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#getId()
	 */
	public ActDetSolicitudId getId() {
		return actDetSolicitud.getId();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#getPrecioUnitario()
	 */
	public double getPrecioUnitario() {
		return actDetSolicitud.getPrecioUnitario();
	}

	/**
	 * @param actActivo
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#setActActivo(com.saldei.hibernate.tables.activos.ActActivo)
	 */
	public void setActActivo(ActActivo actActivo) {
		actDetSolicitud.setActActivo(actActivo);
	}

	/**
	 * @param cantidad
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#setCantidad(java.lang.Integer)
	 */
	public void setCantidad(Integer cantidad) {
		actDetSolicitud.setCantidad(cantidad);
	}

	/**
	 * @param codRecurso
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#setCodRecurso(java.lang.String)
	 */
	public void setCodRecurso(String codRecurso) {
		actDetSolicitud.setCodRecurso(codRecurso);
	}

	/**
	 * @param id
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#setId(com.saldei.hibernate.tables.activos.ActDetSolicitudId)
	 */
	public void setId(ActDetSolicitudId id) {
		actDetSolicitud.setId(id);
	}

	/**
	 * @param precioUnitario
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitud#setPrecioUnitario(double)
	 */
	public void setPrecioUnitario(double precioUnitario) {
		actDetSolicitud.setPrecioUnitario(precioUnitario);
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getCodSolicitud()
	 */
	public String getCodSolicitud() {
		if(this.getActDetSolicitud().getId().getActSolicitud().getId().getCodSolicitud() != null)
			return String.valueOf(this.getActDetSolicitud().getId().getActSolicitud().getId().getCodSolicitud());
		else return "";
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getTipoSolicitud()
	 */
	public String getTipoSolicitud() {
		return this.getActDetSolicitud().getId().getActSolicitud().getId().getTipoSolicitud();
	}

	/**
	 * @param codSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setCodSolicitud(java.lang.Integer)
	 */
	public void setCodSolicitud(String codSolicitud) {
		if(codSolicitud != null && !codSolicitud.trim().equals(""))
			this.getActDetSolicitud().getId().getActSolicitud().getId().setCodSolicitud(Integer.valueOf(codSolicitud));
	}

	/**
	 * @param tipoSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setTipoSolicitud(java.lang.String)
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.getActDetSolicitud().getId().getActSolicitud().getId().setTipoSolicitud(tipoSolicitud);
	}

	/**
	 * @return the actCotizacion
	 */
	public ActCotizacion getActCotizacion() {
		return actCotizacion;
	}

	/**
	 * @param actCotizacion the actCotizacion to set
	 */
	public void setActCotizacion(ActCotizacion actCotizacion) {
		this.actCotizacion = actCotizacion;
	}
}