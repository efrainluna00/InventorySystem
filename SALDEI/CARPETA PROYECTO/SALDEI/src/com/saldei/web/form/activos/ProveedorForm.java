/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.activos;

import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.activos.ActProveedor;

/** 
 * MyEclipse Struts
 * Creation date: 06-10-2009
 * 
 * XDoclet definition:
 * @struts.form name="proveedorForm"
 */
public class ProveedorForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
	private String fecha_creacion;
	private String estadoDescr;
	
	private ActProveedor actProveedor = new ActProveedor();
	
	@SuppressWarnings("deprecation")
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.getNombre() == null || this.getNombre().trim().equals(""))
				errors.add("proveedor.nombre.requerido", new ActionError("proveedor.nombre.requerido"));
			if(this.getEstado() == null || this.getEstado().trim().equals(""))
				errors.add("proveedor.estado.requerido", new ActionError("proveedor.estado.requerido"));
		}				
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.actProveedor = new ActProveedor();
	}

	/**
	 * @return the actProveedor
	 */
	public ActProveedor getActProveedor() {
		return actProveedor;
	}

	/**
	 * @param actProveedor the actProveedor to set
	 */
	public void setActProveedor(ActProveedor actProveedor) {
		this.actProveedor = actProveedor;
	}



	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getCodProveedor()
	 */
	public String getCodProveedor() {
		return actProveedor.getCodProveedor();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getDireccion()
	 */
	public String getDireccion() {
		return actProveedor.getDireccion();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getEMail()
	 */
	public String getEMail() {
		return actProveedor.getEMail();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getEstado()
	 */
	public String getEstado() {
		return actProveedor.getEstado();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getFax()
	 */
	public String getFax() {
		return actProveedor.getFax();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getGiro()
	 */
	public String getGiro() {
		return actProveedor.getGiro();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getNit()
	 */
	public String getNit() {
		return actProveedor.getNit();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getNombre()
	 */
	public String getNombre() {
		return actProveedor.getNombre();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getRegFiscal()
	 */
	public String getRegFiscal() {
		return actProveedor.getRegFiscal();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getTelefono()
	 */
	public String getTelefono() {
		return actProveedor.getTelefono();
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getWebSite()
	 */
	public String getWebSite() {
		return actProveedor.getWebSite();
	}



	/**
	 * @param codProveedor
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setCodProveedor(java.lang.String)
	 */
	public void setCodProveedor(String codProveedor) {
		actProveedor.setCodProveedor(codProveedor);
	}

	/**
	 * @param direccion
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setDireccion(java.lang.String)
	 */
	public void setDireccion(String direccion) {
		actProveedor.setDireccion(direccion);
	}

	/**
	 * @param EMail
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setEMail(java.lang.String)
	 */
	public void setEMail(String EMail) {
		actProveedor.setEMail(EMail);
	}

	/**
	 * @param estado
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setEstado(java.lang.String)
	 */
	public void setEstado(String estado) {
		actProveedor.setEstado(estado);
	}

	/**
	 * @param fax
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setFax(java.lang.String)
	 */
	public void setFax(String fax) {
		actProveedor.setFax(fax);
	}

	/**
	 * @param giro
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setGiro(java.lang.String)
	 */
	public void setGiro(String giro) {
		actProveedor.setGiro(giro);
	}

	/**
	 * @param nit
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setNit(java.lang.String)
	 */
	public void setNit(String nit) {
		actProveedor.setNit(nit);
	}

	/**
	 * @param nombre
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		actProveedor.setNombre(nombre);
	}

	/**
	 * @param regFiscal
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setRegFiscal(java.lang.String)
	 */
	public void setRegFiscal(String regFiscal) {
		actProveedor.setRegFiscal(regFiscal);
	}

	/**
	 * @param telefono
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setTelefono(java.lang.String)
	 */
	public void setTelefono(String telefono) {
		actProveedor.setTelefono(telefono);
	}

	/**
	 * @param webSite
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setWebSite(java.lang.String)
	 */
	public void setWebSite(String webSite) {
		actProveedor.setWebSite(webSite);
	}

	/**
	 * @return the fecha_creacion
	 */
	public String getFecha_creacion() {
		return fecha_creacion;
	}

	/**
	 * @param fecha_creacion the fecha_creacion to set
	 */
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	/**
	 * @return the estadoDescr
	 */
	public String getEstadoDescr() {
		return estadoDescr;
	}

	/**
	 * @param estadoDescr the estadoDescr to set
	 */
	public void setEstadoDescr(String estadoDescr) {
		this.estadoDescr = estadoDescr;
	}

	/**
	 * @return
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#getContacto()
	 */
	public String getContacto() {
		return actProveedor.getContacto();
	}

	/**
	 * @param contacto
	 * @see com.saldei.hibernate.tables.activos.AbstractActProveedor#setContacto(java.lang.String)
	 */
	public void setContacto(String contacto) {
		actProveedor.setContacto(contacto);
	}
}