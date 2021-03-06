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

import com.saldei.hibernate.tables.activos.ActCuenta;

/** 
 * MyEclipse Struts
 * Creation date: 06-03-2009
 * 
 * XDoclet definition:
 * @struts.form name="catalogoCuentaForm"
 */
public class CatalogoCuentaForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Generated Methods
	 */
	private ActCuenta actCuenta = new ActCuenta();
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.actCuenta = new ActCuenta();
	}
	
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.getCodCuenta() == null || this.getCodCuenta().trim().equals("")){
				errors.add("cuenta.codigo.requerido", new ActionError("cuenta.codigo.requerido"));
			}
			if(this.getDescripcion() == null || this.getDescripcion().trim().equals("")){
				errors.add("cuenta.descripcion.requerido", new ActionError("cuenta.descripcion.requerido"));
			}
		}				
	}
	
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActCuenta#getCodCuenta()
	 */
	public String getCodCuenta() {
		return actCuenta.getCodCuenta();
	}
	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActCuenta#getDescripcion()
	 */
	public String getDescripcion() {
		return actCuenta.getDescripcion();
	}
	/**
	 * @param codCuenta
	 * @see com.saldei.hibernate.tables.activos.AbstractActCuenta#setCodCuenta(java.lang.String)
	 */
	public void setCodCuenta(String codCuenta) {
		actCuenta.setCodCuenta(codCuenta);
	}
	/**
	 * @param descripcion
	 * @see com.saldei.hibernate.tables.activos.AbstractActCuenta#setDescripcion(java.lang.String)
	 */
	public void setDescripcion(String descripcion) {
		actCuenta.setDescripcion(descripcion);
	}
	/**
	 * @return the actCuenta
	 */
	public ActCuenta getActCuenta() {
		return actCuenta;
	}
	/**
	 * @param actCuenta the actCuenta to set
	 */
	public void setActCuenta(ActCuenta actCuenta) {
		this.actCuenta = actCuenta;
	}
}