/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.inventario;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.activos.ActActivo;
import com.saldei.hibernate.tables.activos.ActCotizacion;
import com.saldei.hibernate.tables.activos.ActDetCotizacion;
import com.saldei.hibernate.tables.activos.ActDetCotizacionId;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.inventario.InvRecurso;

/** 
 * MyEclipse Struts
 * Creation date: 07-31-2009
 * 
 * XDoclet definition:
 * @struts.form name="ingOrdenCompraForm"
 */
public class IngOrdenCompraForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** actSolicitud property */
	private ActSolicitud actSolicitud =  new ActSolicitud(new ActSolicitudId());	
	/** actCotizacion property */
	private ActCotizacion actCotizacion;
	private ActDetCotizacion actDetCotizacion = new ActDetCotizacion(new ActDetCotizacionId(this.actCotizacion, new InvRecurso()));
	private String Solicitante;
	
	//private InvRecurso invRecurso;
	private ActActivo actActivo = new ActActivo();
	private String[] numSeries;
	private String[] cantidadIngresar;
	private String mostrarHistorico;

	/*
	 * Generated Methods
	 */

	
	/**
	 * @return
	 * @throws Error 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws Error 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws SQLException
	 * @throws Exception
	 * @throws Error
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#getNombre()
	 */
	
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#getNombre()
	 */
	public String getNombre() {
		return actSolicitud.getNombre();
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public void validate(ActionMapping mapping,
			HttpServletRequest request,ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){

			for(int i=0;i < this.getCantidadIngresar().length;i++){
				String datos = this.getCantidadIngresar()[i];
				String[] valores = datos.split(",");
				String cantidadIngresada;
				String cantidadIngresar;
				String cantidad;
				InvRecurso invRecurso =  new InvRecurso();
				/* se setean los valores para ingresar un detalle de movimiento*/
				cantidad = valores[1];
				cantidadIngresada = valores[2];				
				cantidadIngresar = (valores.length > 3)?valores[3]:"0";
				
				if(Integer.valueOf(cantidadIngresada)+Integer.valueOf(cantidadIngresar) > Integer.valueOf(cantidad)){
					errors.add("ingSolAbas.cantidad.requerido"+valores[0], new ActionError("ingSolAbas.cantidad.requerido",Integer.valueOf(cantidad)-Integer.valueOf(cantidadIngresada)));
				}
				
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
		this.numSeries = null;
	}

	/** 
	 * Returns the actSolicitud.
	 * @return ActSolicitud
	 */
	public ActSolicitud getActSolicitud() {
		return actSolicitud;
	}

	/** 
	 * Set the actSolicitud.
	 * @param actSolicitud The actSolicitud to set
	 */
	public void setActSolicitud(ActSolicitud actSolicitud) {
		this.actSolicitud = actSolicitud;
	}

	/** 
	 * Returns the actCotizacion.
	 * @return ActCotizacion
	 */
	public ActCotizacion getActCotizacion() {
		return actCotizacion;
	}

	/** 
	 * Set the actCotizacion.
	 * @param actCotizacion The actCotizacion to set
	 */
	public void setActCotizacion(ActCotizacion actCotizacion) {
		this.actCotizacion = actCotizacion;
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
	 * @see com.saldei.hibernate.tables.activos.ActSolicitud#getEstadoDesc()
	 */
	public String getEstadoDesc() {
		return actSolicitud.getEstadoDesc();
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
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getCodSolicitud()
	 */
	public Integer getCodSolicitud() {
		return this.actSolicitud.getId().getCodSolicitud();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#getTipoSolicitud()
	 */
	public String getTipoSolicitud() {
		return this.actSolicitud.getId().getTipoSolicitud();
	}

	/**
	 * @param codSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setCodSolicitud(java.lang.Integer)
	 */
	public void setCodSolicitud(Integer codSolicitud) {
		this.actSolicitud.getId().setCodSolicitud(codSolicitud);
	}

	/**
	 * @param tipoSolicitud
	 * @see com.saldei.hibernate.tables.activos.AbstractActSolicitudId#setTipoSolicitud(java.lang.String)
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.actSolicitud.getId().setTipoSolicitud(tipoSolicitud);
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return Solicitante;
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		Solicitante = solicitante;
	}


	/**
	 * @return the actDetCotizacion
	 */
	public ActDetCotizacion getActDetCotizacion() {
		return actDetCotizacion;
	}

	/**
	 * @param actDetCotizacion the actDetCotizacion to set
	 */
	public void setActDetCotizacion(ActDetCotizacion actDetCotizacion) {
		this.actDetCotizacion = actDetCotizacion;
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvRecurso#getCodRecurso()
	 */
	public String getCodRecurso() {
		return this.actDetCotizacion.getId().getInvRecurso().getCodRecurso();
	}

	/**
	 * @param codRecurso
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvRecurso#setCodRecurso(java.lang.String)
	 */
	public void setCodRecurso(String codRecurso) {
		this.actDetCotizacion.getId().getInvRecurso().setCodRecurso(codRecurso);
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvRecurso#getNombre()
	 */
	public String getNombreRecurso() {
		return this.actDetCotizacion.getId().getInvRecurso().getNombre();
	}

	/**
	 * @param nombre
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvRecurso#setNombre(java.lang.String)
	 */
	public void setNombreRecurso(String nombreRecurso) {
		this.actDetCotizacion.getId().getInvRecurso().setNombre(nombreRecurso);
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActActivo#getNumSerie()
	 */
	public String getNumSerie() {
		return actActivo.getNumSerie();
	}

	/**
	 * @param numSerie
	 * @see com.saldei.hibernate.tables.activos.AbstractActActivo#setNumSerie(java.lang.String)
	 */
	public void setNumSerie(String numSerie) {
		actActivo.setNumSerie(numSerie);
	}

	/**
	 * @return the actActivo
	 */
	public ActActivo getActActivo() {
		return actActivo;
	}

	/**
	 * @param actActivo the actActivo to set
	 */
	public void setActActivo(ActActivo actActivo) {
		this.actActivo = actActivo;
	}

	/**
	 * @return the numSeries
	 */
	public String[] getNumSeries() {
		return numSeries;
	}

	/**
	 * @param numSeries the numSeries to set
	 */
	public void setNumSeries(String[] numSeries) {
		this.numSeries = numSeries;
	}

	/**
	 * @return the cantidadIngresar
	 */
	public String[] getCantidadIngresar() {
		return cantidadIngresar;
	}

	/**
	 * @param cantidadIngresar the cantidadIngresar to set
	 */
	public void setCantidadIngresar(String[] cantidadIngresar) {
		this.cantidadIngresar = cantidadIngresar;
	}

	/**
	 * @return the mostrarHistorico
	 */
	public String getMostrarHistorico() {
		return mostrarHistorico;
	}

	/**
	 * @param mostrarHistorico the mostrarHistorico to set
	 */
	public void setMostrarHistorico(String mostrarHistorico) {
		this.mostrarHistorico = mostrarHistorico;
	}
}