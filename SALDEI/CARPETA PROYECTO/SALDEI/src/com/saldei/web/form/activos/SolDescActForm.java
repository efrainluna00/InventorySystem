/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.activos;


import java.util.Date;
import java.util.ResourceBundle;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.activos.ActUnidad;

/** 
 * MyEclipse Struts
 * Creation date: 06-13-2009
 * 
 * XDoclet definition:
 * @struts.form name="solDescActForm"
 */
public class SolDescActForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */

	//public ActSolicitud(ActSolicitudId id, ActUnidad actUnidad, String estado) {
	/*public ActDetSolicitud(ActDetSolicitudId id, ActActivo actActivo,
			String codRecurso, double precioUnitario, Integer cantidad)*/
	private ActSolicitud actSolicitud = new ActSolicitud(new ActSolicitudId("",0));
	//private ActDetSolicitud actDetSolicitud = new ActDetSolicitud(new ActDetSolicitudId(actSolicitud,0), new ActActivo(),"",0.0,0);
	
	private String solDescActLink;

	private String fecha_creacion;
	
	private String estadoDescr;
	private Integer correlativo;
	private String codSol2;
	private String fecha_aprobacion;
	private String fecha_anulacion;
	private String fecha_finalizacion;
	private String comentario;
	private String resolucion;
	private String bodegaDes;
	
		
	//Metodos para solicitud
	
	public Set getActDetSolicituds() {
		return actSolicitud.getActDetSolicituds();
	}

	public ActUnidad getActUnidad() {
		return actSolicitud.getActUnidad();
	}
	public String getCodBodega() {
		if (actSolicitud.getCodBodega() != null)
			return String.valueOf(actSolicitud.getCodBodega());
		else return "";		
	}
	public String getCodSolicitante() {
		return actSolicitud.getCodSolicitante();
	}

	public String getEstado() {
		return actSolicitud.getEstado();
	}
	public Date getFechaCreacion() {
		return actSolicitud.getFechaCreacion();
		
	}
	public Date getFechaFinPrestamo() {
		return actSolicitud.getFechaFinPrestamo();
	}
	public Date getFechaIniPrestamo() {
		return actSolicitud.getFechaIniPrestamo();
	}
	public ActSolicitudId getId() {
		return actSolicitud.getId();
	}
	

	public String getPrestamoLink() {
		return actSolicitud.getPrestamoLink();
	}

	public void setActDetSolicituds(Set actDetSolicituds) {
		actSolicitud.setActDetSolicituds(actDetSolicituds);
	}

	public void setActUnidad(ActUnidad actUnidad) {
		actSolicitud.setActUnidad(actUnidad);
	}
	public void setCodBodega(String codBodega) {
		if(codBodega != null  && !codBodega.trim().equals(""))
			actSolicitud.setCodBodega(Integer.valueOf(codBodega));
	}
	public void setCodSolicitante(String codSolicitante) {
		actSolicitud.setCodSolicitante(codSolicitante);
	}
	public void setEstado(String estado) {
		actSolicitud.setEstado(estado);
	}
	public void setFechaCreacion(Date fechaCreacion) {
		actSolicitud.setFechaCreacion(fechaCreacion);
	}
	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		actSolicitud.setFechaFinPrestamo(fechaFinPrestamo);
	}
	public void setFechaIniPrestamo(Date fechaIniPrestamo) {
		actSolicitud.setFechaIniPrestamo(fechaIniPrestamo);
	}
	public void setId(ActSolicitudId id) {
		actSolicitud.setId(id);
	}

	
	//Metodos para extraer los codigos 
	
	
	public Integer getCodSolicitud(){
		return actSolicitud.getId().getCodSolicitud();
	}
	
	public void setCodSolicitud(Integer codSolicitud){
		actSolicitud.getId().setCodSolicitud(codSolicitud);
		//actSolicitud.getId().setCodSolicitud(codSolicitud);
		
	}
	
	public String getTipoSolicitud(){
		return actSolicitud.getId().getTipoSolicitud();
	}
	
	public void setTipoSolicitud(String tipoSolicitud){
		actSolicitud.getId().setTipoSolicitud(tipoSolicitud);
	}
	
	
	public String getEstadoDescr() {
		return estadoDescr;
	}
	public void setEstadoDescr(String estadoDescr) {
		this.estadoDescr = estadoDescr;
	}
	public void setSolDescActLink(String solDescActLink) {
		this.solDescActLink = this.getSolDescActLink();
	}
	/*
	public void setFechaCreacion(String fechaCreacion) {
		this.fecha_creacion = fecha_creacion;
	}
	*/
	public ActSolicitud getActSolicitud() {
		return actSolicitud;
	}
	public void setActSolicitud(ActSolicitud actSolicitud) {
		this.actSolicitud = actSolicitud;
	}
	
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.actSolicitud.getComentario() == null || this.actSolicitud.getComentario().trim().equals("")){
				errors.add("suministro.descripcion.requerido", new ActionError("suministro.descripcion.requerido"));
			}
		}
		// TODO Auto-generated method stub
		
		}
	
	public Integer getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
		
	}
	public String getCodSol2() {
		return codSol2;
		}
	
	public void setCodSol2(String codSol2) {
		this.codSol2 = codSol2;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
/*		public String solDescActLink;
		public String fecha_creacion;
		public String estadoDescr;
		public Integer correlativo;
		public String codSol2;*/
		//this.setSolDescActLink("");
		this.setFecha_creacion("");
		this.setEstadoDescr("");
		this.setCorrelativo(0);
		actSolicitud = new ActSolicitud(new ActSolicitudId("",0));
		this.setCodSol2("");
		this.setComentario("");
		//this.setCodBodega("");
		this.setBodegaDes("");
		
	}

	/*function enviarSolDescAct(accion, tipoSolicitud, codSolicitud,correlativo,codSolicitante,estado,codSol2,
      	  	comentario,estadoNombre,fecha_aprobacion, fecha_finalizacion, fecha_anulacion, comentario,
      	  	resolucion, fecha_creacion)*/
		public String getSolDescActLink() {
		String link;
		link = "<a href='#' onclick=\"javascript:enviarSolDescAct('Find',"
				+ "'D','" + this.getId().getCodSolicitud() + "','','"
				+ this.getCodSolicitante() + "','" + this.getEstado() + "','"
				+ this.getCodSolicitud().toString() + "','"
				+ this.getComentario() + "','"
				+ this.getEstadoDescr() + "','"
				+ this.getFecha_aprobacion() + "','"
				+ this.getFecha_finalizacion() + "','"
				+ this.getFecha_anulacion() + "','"
				+ this.getComentario() + "','"
				+ this.getResolucion() + "','"
				+ this.getFecha_creacion() + "','"
				+ "');return false;\">";
		//link += this.getEstado().equals("G") ? "Asociar activos a solicitud"
		//		: "Mostrar detalles de solicitud";
		link += "Detalle de la solicitud";
		link += "</a>";
		return link;
	}
		
		public String getFecha_aprobacion() {
			return fecha_aprobacion;
		}



		public void setFecha_aprobacion(String fecha_aprobacion) {
			this.fecha_aprobacion = fecha_aprobacion;
		}

		public String getFecha_anulacion() {
			return fecha_anulacion;
		}

		public void setFecha_anulacion(String fecha_anulacion) {
			this.fecha_anulacion = fecha_anulacion;
		}

		public String getFecha_finalizacion() {
			return fecha_finalizacion;
		}

		public void setFecha_finalizacion(String fecha_finalizacion) {
			this.fecha_finalizacion = fecha_finalizacion;
		}

		public String getComentario() {
			return this.getActSolicitud().getComentario();
		}

		public void setComentario(String comentario) {
			this.getActSolicitud().setComentario(comentario);
		}

		public String getResolucion() {
			return resolucion;
		}

		public void setResolucion(String resolucion) {
			this.resolucion = resolucion;
		}

		public String getBodegaDes() {
			return bodegaDes;
		}

		public void setBodegaDes(String bodegaDes) {
			this.bodegaDes = bodegaDes;
		}


}
	
