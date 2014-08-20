/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.inventario;

import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.saldei.hibernate.tables.inventario.InvTipoMov;

/** 
 * MyEclipse Struts
 * Creation date: 05-31-2009
 * 
 * XDoclet definition:
 * @struts.form name="tipoMovForm"
 */
public class TipoMovForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** invTipoMov property */
	private InvTipoMov invTipoMov = new InvTipoMov();
	private String registroModificable;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	@SuppressWarnings("deprecation")
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.getInvTipoMov().getCodTipoMov() == null || this.getInvTipoMov().getCodTipoMov().equals("")){
				errors.add("tm.codTipoMov.requerido", new ActionError("tm.codTipoMov.requerido"));
			}
			
			if(this.getInvTipoMov().getDescripcion() == null || this.getInvTipoMov().getDescripcion().trim().equals("")){
				errors.add("tm.descripcion.requerido", new ActionError("tm.descripcion.requerido"));
			}
			/*
			if(this.getInvTipoMov().getOperacion() == null || this.getInvTipoMov().getOperacion().equals(1)){
				errors.add("tm.fechaFin.requerido", new ActionError("tm.fechaFin.requerido"));
			}
			
			if(errors.isEmpty())
				this.setPreperties();
				
		    */
		}				
	}



	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	this.invTipoMov = new InvTipoMov();
	}



	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#getCodTipoMov()
	 */
	public String getCodTipoMov() {
		if(invTipoMov.getCodTipoMov()!=null)
			return String.valueOf(invTipoMov.getCodTipoMov());
			else
				return "";
	}



	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#getDescripcion()
	 */
	public String getDescripcion() {
		return invTipoMov.getDescripcion();
	}



	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#getInvMovimientos()
	 */
	public Set getInvMovimientos() {
		return invTipoMov.getInvMovimientos();
	}



	/**
	 * @return
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#getOperacion()
	 */
	public String getOperacion() {
		return invTipoMov.getOperacion();
	}



	/**
	 * @param codTipoMov
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#setCodTipoMov(java.lang.Integer)
	 */
	public void setCodTipoMov(String codTipoMov) {
		if(codTipoMov!=null && !codTipoMov.trim().equals(""))
			invTipoMov.setCodTipoMov(Integer.valueOf(codTipoMov));
		
	}



	/**
	 * @param descripcion
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#setDescripcion(java.lang.String)
	 */
	public void setDescripcion(String descripcion) {
		invTipoMov.setDescripcion(descripcion);
	}



	/**
	 * @param invMovimientos
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#setInvMovimientos(java.util.Set)
	 */
	public void setInvMovimientos(Set invMovimientos) {
		invTipoMov.setInvMovimientos(invMovimientos);
	}



	/**
	 * @param operacion
	 * @see com.saldei.hibernate.tables.inventario.AbstractInvTipoMov#setOperacion(byte)
	 */
	public void setOperacion(String operacion) {
		invTipoMov.setOperacion(operacion);
	}



	/**
	 * @return the invTipoMov
	 */
	public InvTipoMov getInvTipoMov() {
		return invTipoMov;
	}



	/**
	 * @param invTipoMov the invTipoMov to set
	 */
	public void setInvTipoMov(InvTipoMov invTipoMov) {
		this.invTipoMov = invTipoMov;
	}



	public String getRegistroModificable() {
		return registroModificable;
	}



	public void setRegistroModificable(String registroModificable) {
		this.registroModificable = registroModificable;
	}

	
}