/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.activos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.activos.ActProveedor;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.activos.ActUnidad;

/** 
 * MyEclipse Struts
 * Creation date: 06-16-2009
 * 
 * XDoclet definition:
 * @struts.form name="aprSolPrestamoForm"
 */
public class AprSolPrestamoForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ActSolicitud actSolicitud = new ActSolicitud(new ActSolicitudId(),new ActUnidad());
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String solicitante;
	private String estadoNombre;
	private String mostrarHistorico;
	private String responsable;
	private String motivoRechazo;
	/**
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}


	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.actSolicitud = new ActSolicitud(new ActSolicitudId(),new ActUnidad());
	
	}
/*	
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.fecha_ini == null || this.fecha_ini.equals("")){
				errors.add("presupuesto.fechaIni.requerido", new ActionError("presupuesto.fechaIni.requerido"));
			}
			
			if(this.fecha_fin == null || this.fecha_fin.equals("")){
				errors.add("presupuesto.fechaFin.requerido", new ActionError("presupuesto.fechaFin.requerido"));
			}
			
			if(errors.isEmpty())
				this.setPreperties();
		}				
	}*/
	
	
	public String getArprestamoLink() {
		String link = " <a  href='#' onclick=\"javascript:enviarDetAprSolPrestamo('Find','"
				+ this.getId().getCodSolicitud()+ "','"
				+ this.getCodSolicitante()+ "','"
				+ this.getEstado()+ "','"
				+ this.getEstadoNombre()+ "','"
				+ this.getSolicitante()+ "','"
				+ sdf.format(this.getFechaIniPrestamo())+ "','"
				+ sdf.format(this.getFechaFinPrestamo())+ "','"
				+ this.getComentario()+ "','"
				+ this.getCodPropietario()+ "','"
				+ this.getCodUnidad()+ "','"
				+ this.getMotivoRechazo()+
				"');return false;\" >";
		link += " Ver Detalle</a>";	
	
	return link;
}
		

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getActUnidad()
	 */
	public ActUnidad getActUnidad() {
		return actSolicitud.getActUnidad();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getCodBodega()
	 */
	public String getCodBodega() {
		if (actSolicitud.getCodBodega() != null)
			return String.valueOf(actSolicitud.getCodBodega());
		else return "";
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getCodSolicitante()
	 */
	public String getCodSolicitante() {
		return actSolicitud.getCodSolicitante();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getEstado()
	 */
	public String getEstado() {
		return actSolicitud.getEstado();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getFechaCreacion()
	 */
	public Date getFechaCreacion() {
		return actSolicitud.getFechaCreacion();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getFechaFinPrestamo()
	 */
	public Date getFechaFinPrestamo() {
		return actSolicitud.getFechaFinPrestamo();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getFechaIniPrestamo()
	 */
	public Date getFechaIniPrestamo() {
		return actSolicitud.getFechaIniPrestamo();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getId()
	 */
	public ActSolicitudId getId() {
		return actSolicitud.getId();
	}


	/**
	 * @param actUnidad
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setActUnidad(com.saldei.hibernate.tables.activos.ActUnidad)
	 */
	public void setActUnidad(ActUnidad actUnidad) {
		actSolicitud.setActUnidad(actUnidad);
	}
	/**
	 * @param codBodega
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setCodBodega(java.lang.String)
	 */
	public void setCodBodega(String codBodega) {
		if(codBodega != null  && !codBodega.trim().equals(""))
			actSolicitud.setCodBodega(Integer.valueOf(codBodega));
	}
	/**
	 * @param codSolicitante
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setCodSolicitante(java.lang.String)
	 */
	public void setCodSolicitante(String codSolicitante) {
		actSolicitud.setCodSolicitante(codSolicitante);
	}
	/**
	 * @param estado
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setEstado(java.lang.String)
	 */
	public void setEstado(String estado) {
		actSolicitud.setEstado(estado);
	}
	/**
	 * @param fechaCreacion
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setFechaCreacion(java.util.Date)
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		actSolicitud.setFechaCreacion(fechaCreacion);
	}
	/**
	 * @param fechaFinPrestamo
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setFechaFinPrestamo(java.util.Date)
	 */
	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		actSolicitud.setFechaFinPrestamo(fechaFinPrestamo);
	}
	/**
	 * @param fechaIniPrestamo
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setFechaIniPrestamo(java.util.Date)
	 */
	public void setFechaIniPrestamo(Date fechaIniPrestamo) {
		actSolicitud.setFechaIniPrestamo(fechaIniPrestamo);
	}
	/**
	 * @param id
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setId(com.saldei.hibernate.tables.activos.ActSolicitudId)
	 */
	public void setId(ActSolicitudId id) {
		actSolicitud.setId(id);
	}

	/**
	 * @return the actSolicitud
	 */
	public ActSolicitud getActSolicitud() {
		return actSolicitud;
	}
	/**
	 * @param actSolicitud the actSolicitud to set
	 */
	public void setActSolicitud(ActSolicitud actSolicitud) {
		this.actSolicitud = actSolicitud;
	}


	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActUnidad#getCodResponsable()
	 */
	public String getCodResponsable() {
		return actSolicitud.getActUnidad().getCodResponsable();
	}


	/**
	 * @return 
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActUnidad#getCodUnidad()
	 */

	public Integer getCodUnidad() {
		return actSolicitud.getActUnidad().getCodUnidad();
	}

	/**
	 * @param codResponsable
	 * @see com.saldei.hibernate.tables.activos.AbstractActUnidad#setCodResponsable(java.lang.String)
	 */
	public void setCodResponsable(String codResponsable) {
		actSolicitud.getActUnidad().setCodResponsable(codResponsable);
	}


	/**
	 * @param codUnidad
	 * @see com.saldei.hibernate.tables.activos.AbstractActUnidad#setCodUnidad(java.lang.Integer)
	 */
	public void setCodUnidad(Integer codUnidad) {
		actSolicitud.getActUnidad().setCodUnidad(codUnidad);
	}


	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getCodSolicitud()
	 */
	public Integer getCodSolicitud() {
		return actSolicitud.getId().getCodSolicitud();
	}


	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getTipoSolicitud()
	 */
	public String getTipoSolicitud() {
		return actSolicitud.getId().getTipoSolicitud();
	}


	/**
	 * @param codSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setCodSolicitud(java.lang.Integer)
	 */
	public void setCodSolicitud(Integer codSolicitud) {
		actSolicitud.getId().setCodSolicitud(codSolicitud);
	}


	/**
	 * @param tipoSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setTipoSolicitud(java.lang.String)
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		actSolicitud.getId().setTipoSolicitud(tipoSolicitud);
	}

	
	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getActDetSolicituds()
	 */
	public Set getActDetSolicituds() {
		return actSolicitud.getActDetSolicituds();
	}

	/**
	 * @param actDetSolicituds
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setActDetSolicituds(java.util.Set)
	 */
	public void setActDetSolicituds(Set actDetSolicituds) {
		actSolicitud.setActDetSolicituds(actDetSolicituds);
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getCodPropietario()
	 */
	public String getCodPropietario() {
		return actSolicitud.getCodPropietario();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#getComentario()
	 */
	public String getComentario() {
		return actSolicitud.getComentario();
	}

	/**
	 * @param codPropietario
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setCodPropietario(java.lang.String)
	 */
	public void setCodPropietario(String codPropietario) {
		actSolicitud.setCodPropietario(codPropietario);
	}

	/**
	 * @param comentario
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitud#setComentario(java.lang.String)
	 */
	public void setComentario(String comentario) {
		actSolicitud.setComentario(comentario);
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#getFecha_fin()
	 */
	public String getFecha_fin() {
		return actSolicitud.getFecha_fin();
	}


	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#getFecha_ini()
	 */
	public String getFecha_ini() {
		return actSolicitud.getFecha_ini();
	}


	/**
	 * @param fecha_fin
	 * @throws ParseException
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#setFecha_fin(java.lang.String)
	 */
	public void setFecha_fin(String fecha_fin) throws ParseException {
		actSolicitud.setFecha_fin(fecha_fin);
	}


	/**
	 * @param fecha_ini
	 * @throws ParseException
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#setFecha_ini(java.lang.String)
	 */
	public void setFecha_ini(String fecha_ini) throws ParseException {
		actSolicitud.setFecha_ini(fecha_ini);
	}


	/**
	 * @return the estadoNombre
	 */
	public String getEstadoNombre() {
		return estadoNombre;
	}


	/**
	 * @param estadoNombre the estadoNombre to set
	 */
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}


	public String getMostrarHistorico() {
		return mostrarHistorico;
	}


	public void setMostrarHistorico(String mostrarHistorico) {
		this.mostrarHistorico = mostrarHistorico;
	}


	public String getMotivoRechazo() {
		return motivoRechazo;
	}


	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

}