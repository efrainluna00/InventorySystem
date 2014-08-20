package com.saldei.web.action.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.util.commons.Util;
import com.saldei.web.bean.administracion.MateriaDto;
import com.saldei.web.services.administracion.MateriaServices;
import com.saldei.web.form.administracion.MateriaForm;

public class MateriaAction extends DispatchAction {
	
	private MateriaServices services = new MateriaServices();
	
	/**
	 * Inicializa los campos de la pantalla de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listMatActivas",null);
		request.getSession().setAttribute("listMatInactivas",null);
		services.changeButtons(request);	
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los campos de la pantalla de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listMatActivas",null);
		request.getSession().setAttribute("listMatInactivas",null);
		return mapping.findForward("success");
	}
	
	/**
	 * Cambia la accion de Materia (Activacion\ Desactivacion)
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("listMatActivas",null);
			request.getSession().setAttribute("listMatInactivas",null);
			String accion = (String) request.getSession().getAttribute("buttonsExtMat");
			if(accion == null)
				request.getSession().setAttribute("buttonsExtMat", "secondButtonMat");							
			else
				if(accion.equals("firstButtonMat"))
					request.getSession().setAttribute("buttonsExtMat", "secondButtonMat");				
				else
					request.getSession().setAttribute("buttonsExtMat", "firstButtonMat");				
			services.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			return find(mapping, form, request, response);
		}
	}
	
	/**
	 * Guarda un nuevo registro de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			boolean action = services.knowAction(request);
			if(!action){
				MateriaForm matform = (MateriaForm) form;
				request.setAttribute("materiaFormJsp", matform);
				if(services.isNullMatForm(matform))
					request.setAttribute("matMsg", "vacio");
				else{
					if(!Util.esCadenaNumero(matform.getCodigo()) || !Util.esCadenaNumero(matform.getUv())){
						request.setAttribute("matMsg", "numCodigo");
						return mapping.findForward("success");
					}						
					else{
						boolean flag = services.save(matform);
						if(flag)
							request.setAttribute("matMsg", "exitoSave");
						else{
							request.setAttribute("matMsg", "errorSave");
							return mapping.findForward("success");
						}							
					}			
				}	
			}else{
				ArrayList<MateriaDto> listActivo = (ArrayList) request.getSession().getAttribute("listMatActivas");
				ArrayList<MateriaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listMatInactivas");
				if(listActivo == null && listInactivo == null)
					request.setAttribute("matMsg","saveAllNull");
				else{
					services.updateAll(listActivo, listInactivo);
					request.setAttribute("matMsg","saveAllExito");
				}
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("matMsg", "error");
		}		
		return find(mapping, form, request, response);
	}

	/**
	 * Hace el cambio de Activacion\Desactivacion
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MateriaForm matform = (MateriaForm) form;
		String estadox = "", est = "";
		ArrayList<MateriaDto> listActivo = (ArrayList) request.getSession().getAttribute("listMatActivas");
		ArrayList<MateriaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listMatInactivas");
		MateriaDto dto = new MateriaDto();
		dto.setCodigo(matform.getCodigo());
		dto.setDescripcion(matform.getDesc());
		dto.setNombre(matform.getNombre());
		if(matform.getEstado().equals("A")){
			dto.setEstado("Inactivo");
			estadox = "Activar";
			est = "I";
		}else{
			dto.setEstado("Activo");
			estadox = "Desactivar";
			est = "A";
		}
		String descripcion = "";
		if(dto.getDescripcion() != null)
			descripcion = dto.getDescripcion();
		dto.setAccion("<a href='" + Constants.contextPath + "mtoMat.do?cmd=hash&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + descripcion + "&estado=" + est + "'>" + estadox +"</a>");
		if(matform.getEstado().equals("A")){
			listActivo = services.removeItemToList(listActivo, matform.getCodigo());
			listInactivo.add(dto);
		}else{
			listInactivo = services.removeItemToList(listInactivo, matform.getCodigo());
			listActivo.add(dto);
		}
		request.getSession().setAttribute("listMatActivas", listActivo);
		request.getSession().setAttribute("listMatInactivas", listInactivo);
		return mapping.findForward("success");
	}
	
	/**
	 * Realiza una busqueda  de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			MateriaForm matform = (MateriaForm) form;
			ArrayList[] list = null;
			boolean action = services.knowAction(request);
			if(!Util.esCadenaNumero(matform.getCodigo()) || !Util.esCadenaNumero(matform.getUv())){
				request.setAttribute("matMsg", "numCodigo");
				return mapping.findForward("success");
			}						
			if(!action)				
				list = services.find(matform,true);
			else
				list = services.find(matform,false);
			if(list[0] == null && list[1] == null)
				request.setAttribute("matMsg", "vacioLista");
			else{
				request.getSession().setAttribute("listMatActivas", list[0]);
				request.getSession().setAttribute("listMatInactivas", list[1]);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("matMsg", "error");
		}		
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza un registro de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{			
			MateriaForm matform = (MateriaForm) form;
			request.setAttribute("materiaFormJsp", matform);
			boolean action = services.knowAction(request);
			if(!action){
				if(matform.getCodigo().equals("") || matform.getNombre().equals(""))
					request.setAttribute("matMsg", "vacioUpdate");
				else{
					if(!Util.esCadenaNumero(matform.getCodigo()))
						request.setAttribute("matMsg", "numCodigo");
					else{
						boolean flag = services.update(matform);
						if(flag)
							request.setAttribute("matMsg", "exitoUpdate");
						else
							request.setAttribute("matMsg", "errorUpdate");	
					}			
				}	
			}else{
				ArrayList[] list = services.findAll();
				request.getSession().setAttribute("listMatActivas", list[0]);
				request.getSession().setAttribute("listMatInactivas", list[1]);
			}				
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("matMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Genera un reporte de Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
	
}//clase
