/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.form.inventario;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.inventario.InvBodega;
import com.saldei.hibernate.tables.inventario.InvCategoria;
import com.saldei.hibernate.tables.inventario.InvExistencia;
import com.saldei.hibernate.tables.inventario.InvExistenciaId;
import com.saldei.hibernate.tables.inventario.InvRecurso;
import com.saldei.hibernate.tables.inventario.InvSubCategoria;
import com.saldei.hibernate.tables.inventario.InvSubCategoriaId;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2009
 * 
 * XDoclet definition:
 * @struts.form name="minimoxBodegaForm"
 */
public class MinimoxBodegaForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
	//enviarRecurso('Find',codRecurso,nombreRecurso,categoria,subcategoria,unidadmedida)
	private InvExistencia invExistencia = new InvExistencia(new InvExistenciaId(new InvBodega(),new InvRecurso()));
	private String codRecurso;
	private String nombreRecurso;
	private String categoria;
	private String subcategoria;
	private String unidadmedida;
	private String bodegaDes;
	private String codBodega;
	private String lBodega;
	private String abvunidadmedida;
	private String codBodeguero;
	private Integer isBodeguero;
	

	public double getExistencia() {
		return invExistencia.getExistencia();
	}

	public InvExistenciaId getId() {
		return invExistencia.getId();
	}

	public Integer getMinimo() {
		return invExistencia.getMinimo();
	}

	public void setExistencia(double existencia) {
		invExistencia.setExistencia(existencia);
	}

	public void setId(InvExistenciaId id) {
		invExistencia.setId(id);
	}

	public void setMinimo(Integer minimo) {
		invExistencia.setMinimo(minimo);
	}

	public InvExistencia getInvExistencia() {
		return invExistencia;
	}

	public void setInvExistencia(InvExistencia invExistencia) {
		this.invExistencia = invExistencia;
	}

	public String getCodRecurso() {
		return this.getInvExistencia().getId().getInvRecurso().getCodRecurso();
	}

	public void setCodRecurso(String codRecurso) {
		this.getInvExistencia().getId().getInvRecurso().setCodRecurso(codRecurso);
		//this.codRecurso = codRecurso;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getUnidadmedida() {
		return unidadmedida;
	}

	public void setUnidadmedida(String unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getBodegaDes() {
		return bodegaDes;
	}

	public void setBodegaDes(String bodegaDes) {
		this.bodegaDes = bodegaDes;
	}

	public String getCodBodega() {
		return this.getInvExistencia().getId().getInvBodega().getCodBodega().toString();
		//return codBodega;
	}

	public void setCodBodega(String codBodega) {
		this.getInvExistencia().getId().getInvBodega().setCodBodega(Integer.valueOf(codBodega));
		//this.codBodega = codBodega;
	}

	public String getLBodega() {
		return lBodega;
	}

	public void setLBodega(String bodega) {
		lBodega = bodega;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.setBodegaDes("");
		this.setMinimo(0);
		this.setCodBodega("0");
		InvExistencia invExistencia = new InvExistencia(new InvExistenciaId(new InvBodega(),new InvRecurso()));
	}

	public void validate(ActionMapping mapping,
			HttpServletRequest request, ActionErrors errors) {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		accion = (accion==null)?"":accion;
		
		ResourceBundle mensajes = ResourceBundle.getBundle("com.saldei.web.ApplicationResources");
		if(!accion.equals(mensajes.getString("opc.cancel")) && !accion.equals(mensajes.getString("opc.find"))){
			if(this.getCodBodega() == null || this.getCodBodega().trim().equals("") || this.getCodBodega().equals("0")){
				errors.add("minixbod.codBodega.requerido", new ActionError("minixbod.codBodega.requerido"));
			}
			if(this.getMinimo() == null){
				errors.add("minixbod.minimo.requerido", new ActionError("minixbod.minimo.requerido"));
			}
			if(this.getMinimo() != null && this.getMinimo()<0){
				errors.add("minixbod.minimo.valido", new ActionError("minixbod.minimo.valido"));
			}
			
			
		}				
	}

	public String getAbvunidadmedida() {
		return abvunidadmedida;
	}

	public void setAbvunidadmedida(String abvunidadmedida) {
		this.abvunidadmedida = abvunidadmedida;
	}

	public String getCodBodeguero() {
		return codBodeguero;
	}

	public void setCodBodeguero(String codBodeguero) {
		this.codBodeguero = codBodeguero;
	}

	public Integer getIsBodeguero() {
		return isBodeguero;
	}

	public void setIsBodeguero(Integer isBodeguero) {
		this.isBodeguero = isBodeguero;
	}
	
}