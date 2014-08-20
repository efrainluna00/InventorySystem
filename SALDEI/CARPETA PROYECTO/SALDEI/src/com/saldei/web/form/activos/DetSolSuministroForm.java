/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.activos;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.activos.ActActivo;
import com.saldei.hibernate.tables.activos.ActDetSolicitud;
import com.saldei.hibernate.tables.activos.ActDetSolicitudId;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.activos.ActUnidad;
import com.saldei.web.bean.Util;

/** 
 * MyEclipse Struts
 * Creation date: 06-21-2009
 * 
 * XDoclet definition:
 * @struts.form name="detReqRecursoForm"
 */
public class DetSolSuministroForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** actDetSolicitud property */
	private static final long serialVersionUID = 1L;
	public String nombreRecurso = new String();
	private ActDetSolicitud actDetSolicitud = new ActDetSolicitud(new ActDetSolicitudId(new ActSolicitud(new ActSolicitudId("",0))), new ActActivo());
	private String codBodega = new String(); 
	private String bodegaDes = new String();
	private String desEstado = new String();
	private String medida;
	private String unidadDes;




	


	/**
	 * @return the unidadDes
	 */
	public String getUnidadDes() {
		return unidadDes;
	}


	/**
	 * @param unidadDes the unidadDes to set
	 */
	public void setUnidadDes(String unidadDes) {
		this.unidadDes = unidadDes;
	}


	/**
	 * @return the medidad
	 */
	public String getMedida() {
		return medida;
	}


	/**
	 * @param medidad the medidad to set
	 */
	public void setMedida(String medida) {
		this.medida = medida;
	}


	/**
	 * @return the desEstado
	 */
	public String getDesEstado() {
		return com.saldei.web.bean.Util.descripcionEstado(this.getEstado());
		

	}


	/**
	 * @param desEstado the desEstado to set
	 */
	public void setDesEstado(String desEstado) {
		this.desEstado = com.saldei.web.bean.Util.descripcionEstado(this.getEstado());
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return this.getActDetSolicitud().getId().getActSolicitud().getEstado();
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		 this.getActDetSolicitud().getId().getActSolicitud().setEstado(estado);
	}


	/**
	 * @return the bodegaDes
	 */
	public String getBodegaDes() {
		return bodegaDes;
	}


	/**
	 * @param bodegaDes the bodegaDes to set
	 */
	public void setBodegaDes(String bodegaDes) {
		this.bodegaDes = bodegaDes;
	}


	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.actDetSolicitud.getCodRecurso() == null || this.actDetSolicitud.getCodRecurso().trim().equals("") ){
				errors.add("detSum.recurso.requerido", new ActionError("detSum.recurso.requerido"));
			}
			if(this.actDetSolicitud.getCantidad() == null || this.actDetSolicitud.getCantidad() <= 0 ){
				errors.add("detSum.cantidad.requerido", new ActionError("detSum.cantidad.requerido"));
			}	
		}			
	}
	

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
 	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActSolicitud actSolicitud = this.actDetSolicitud.getId().getActSolicitud();
		this.actDetSolicitud = new ActDetSolicitud(new ActDetSolicitudId(new ActSolicitud(this.actDetSolicitud.getId().getActSolicitud().getId())));
		this.actDetSolicitud.getId().setActSolicitud(actSolicitud);
		nombreRecurso = new String();
		this.setMedida("");
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
	public Integer getCorrelativo() {
		return this.actDetSolicitud.getId().getCorrelativo();
	}
	/**
	 * @param actSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitudId#setActSolicitud(com.saldei.hibernate.tables.activos.ActSolicitud)
	 */
	public void setActSolicitud(ActSolicitud actSolicitud) {
		this.actDetSolicitud.getId().setActSolicitud(actSolicitud);
	}
	/**
	 * @param correlativo
	 * @see com.saldei.hibernate.tables.activos.AbstractActDetSolicitudId#setCorrelativo(java.lang.Integer)
	 */
	public void setCorrelativo(Integer correlativo) {
		this.actDetSolicitud.getId().setCorrelativo(correlativo);
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getCodSolicitud()
	 */
	public Integer getCodSolicitud() {
		return this.actDetSolicitud.getId().getActSolicitud().getId().getCodSolicitud();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getTipoSolicitud()
	 */
	public String getTipoSolicitud() {
		return this.actDetSolicitud.getId().getActSolicitud().getId().getTipoSolicitud();
	}
	/**
	 * @param codSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setCodSolicitud(java.lang.Integer)
	 */
	public void setCodSolicitud(Integer codSolicitud) {
		this.actDetSolicitud.getId().getActSolicitud().getId().setCodSolicitud(codSolicitud);
	}
	/**
	 * @param tipoSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setTipoSolicitud(java.lang.String)
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		actDetSolicitud.getId().getActSolicitud().getId().setTipoSolicitud(tipoSolicitud);
		
	}


	/**
	 * @return the nombreRecurso
	 */
	public String getNombreRecurso() {
		return nombreRecurso;
	}


	/**
	 * @param nombreRecurso the nombreRecurso to set
	 */
	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}


	/**
	 * @return the codBodega
	 */
	public String getCodBodega() {
		return codBodega;
	}


	/**
	 * @param codBodega the codBodega to set
	 */
	public void setCodBodega(String codBodega) {
		this.codBodega = codBodega;
	}
}