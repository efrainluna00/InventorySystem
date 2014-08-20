/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.inventario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-26-2009
 * 
 * XDoclet definition:
 * @struts.form name="repAsigActualesForm"
 */
public class RepSalPorBodegaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String formato;
	private String bodega = null;
	private String bodegaDesc;
	private String fecha_ini;
	private String fecha_fin;
    private String grafico;
    private String consumible;
    private String flagTarget;
    private String usuario;
    

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}
	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	
	
	
	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
	
			if(this.getFecha_ini()== null || this.getFecha_ini().trim().equals("")){
				errors.add("repSalBod.fecha_ini.requerido", new ActionError("repSalBod.fecha_ini.requerido"));
			}
			if(this.getFecha_fin() == null || this.getFecha_fin().trim().equals("")){
				errors.add("repSalBod.fecha_fin.requerido", new ActionError("repSalBod.fecha_fin.requerido"));
				return;
			}
			
			try {
				if (!(this.getFecha_ini()== null || this.getFecha_ini().trim().equals("")) && !(this.getFecha_fin() == null || this.getFecha_fin().trim().equals(""))){
						if (sdf.parse(this.getFecha_ini()).compareTo(sdf.parse(this.getFecha_fin()))> 0){
					errors.add("repSalBod.rango_invalido", new ActionError("repSalBod.rango_invalido"));
				}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		}				
	}

	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getBodegaDesc() {
		return bodegaDesc;
	}
	public void setBodegaDesc(String bodegaDesc) {
		this.bodegaDesc = bodegaDesc;
	}
	public String getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getGrafico() {
		return grafico;
	}
	public void setGrafico(String grafico) {
		this.grafico = grafico;
	}
	public String getConsumible() {
		return consumible;
	}
	public void setConsumible(String consumible) {
		this.consumible = consumible;
	}
	/**
	 * @return the flagTarget
	 */
	public String getFlagTarget() {
		return flagTarget;
	}
	/**
	 * @param flagTarget the flagTarget to set
	 */
	public void setFlagTarget(String flagTarget) {
		this.flagTarget = flagTarget;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


}