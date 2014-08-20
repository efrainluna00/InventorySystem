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
import com.saldei.web.bean.administracion.LaboratorioDto;
import com.saldei.web.form.administracion.LaboratorioForm;
import com.saldei.web.services.administracion.LaboratorioServices;

public class LaboratorioAction extends DispatchAction {
	
	private LaboratorioServices services = new LaboratorioServices();
	
	/**
	 * Inicializa los campos de la pantalla de Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listLabActivas",null);
		request.getSession().setAttribute("listLabInactivas",null);
		services.changeButtons(request);	
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los campos de la pantalla de Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listLabActivas",null);
		request.getSession().setAttribute("listLabInactivas",null);
		return mapping.findForward("success");
	}
	
	/**
	 * Cambia los estados de Activacion y Modificacion 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("listLabActivas",null);
			request.getSession().setAttribute("listLabInactivas",null);
			String accion = (String) request.getSession().getAttribute("buttonsExtLab");
			if(accion == null)
				request.getSession().setAttribute("buttonsExtLab", "secondButtonLab");							
			else
				if(accion.equals("firstButtonLab"))
					request.getSession().setAttribute("buttonsExtLab", "secondButtonLab");				
				else
					request.getSession().setAttribute("buttonsExtLab", "firstButtonLab");				
			services.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			request.setAttribute("laboMsg", "error");
			return find(mapping, form, request, response);			
		}
	}
	
	/**
	 * Guarda un nuevo Laboratorio
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
				LaboratorioForm labo = (LaboratorioForm) form;
				request.setAttribute("labFormJsp", labo);
				if(services.isNullLabForm(labo))
					request.setAttribute("laboMsg", "vacio");
				else{
					if(!Util.esCadenaNumero(labo.getNumColumnas()) || !Util.esCadenaNumero(labo.getNumFilas())){
						request.setAttribute("laboMsg", "numCodigo");
						return mapping.findForward("success");
					}						
					else{
						boolean flag = services.save(labo);
						if(flag)
							request.setAttribute("laboMsg", "exitoSave");
						else{
							request.setAttribute("laboMsg", "errorSave");
							return mapping.findForward("success");
						}							
					}			
				}	
			}else{
				ArrayList<LaboratorioDto> listActivo = (ArrayList) request.getSession().getAttribute("listLabActivas");
				ArrayList<LaboratorioDto> listInactivo = (ArrayList) request.getSession().getAttribute("listLabInactivas");
				if(listActivo == null && listInactivo == null)
					request.setAttribute("laboMsg","saveAllNull");
				else{
					services.updateAll(listActivo, listInactivo);
					request.setAttribute("laboMsg","saveAllExito");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("laboMsg", "error");
		}
		return find(mapping, form, request, response);		
	}
	
	/**
	 * Busca los Laboratorios
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			LaboratorioForm labo = (LaboratorioForm) form;
			ArrayList[] list = null;
			boolean action = services.knowAction(request);
			if(!Util.esCadenaNumero(labo.getNumColumnas()) || !Util.esCadenaNumero(labo.getNumFilas())){
				request.setAttribute("laboMsg", "numCodigo");
				return mapping.findForward("success");
			}		
			if(!action)				
				list = services.find(labo,true);
			else
				list = services.find(labo,false);
			if(list[0] == null && list[1] == null)
				request.setAttribute("laboMsg", "vacioLista");
			else{
				request.getSession().setAttribute("listLabActivas", list[0]);
				request.getSession().setAttribute("listLabInactivas", list[1]);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("laboMsg", "error");
		}		
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza un registro de Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{			
			LaboratorioForm labo = (LaboratorioForm) form;
			request.setAttribute("labFormJsp", labo);
			boolean action = services.knowAction(request);
			if(!action){
				if(labo.getIdLaboratorio().equals("") || labo.getNumFilas().equals("") || labo.getNumColumnas().equals("")){
					request.setAttribute("laboMsg", "vacioUpdate");
					return mapping.findForward("success");
				}					
				else{
					if(!Util.esCadenaNumero(labo.getNumColumnas()) || !Util.esCadenaNumero(labo.getNumFilas())){
						request.setAttribute("laboMsg", "numCodigo");
						return mapping.findForward("success");
					}		
					else{
						boolean flag = services.update(labo);
						if(flag)
							request.setAttribute("laboMsg", "exitoUpdate");
						else
							request.setAttribute("laboMsg", "errorUpdate");	
					}			
				}	
			}else{
				ArrayList[] list = services.findAll();
				request.getSession().setAttribute("listLabActivas", list[0]);
				request.getSession().setAttribute("listLabInactivas", list[1]);
			}				
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("laboMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Activa o Desactiva un Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			LaboratorioForm labo = (LaboratorioForm) form;
			String estadox = "", est = "";
			ArrayList<LaboratorioDto> listActivo = (ArrayList) request.getSession().getAttribute("listLabActivas");
			ArrayList<LaboratorioDto> listInactivo = (ArrayList) request.getSession().getAttribute("listLabInactivas");
			LaboratorioDto dto = new LaboratorioDto();
			dto.setIdLaboratorio(labo.getIdLaboratorio());
			dto.setAbrevLaboratorio(labo.getAbrevLaboratorio());
			dto.setNombreLaboratorio(labo.getNombreLaboratorio());
			dto.setNumColumnas(labo.getNumColumnas());
			dto.setNumFilas(labo.getNumFilas());
			if(labo.getEstLaboratorio().equals("A")){
				dto.setEstLaboratorio("Inactivo");
				estadox = "Activar";
				est = "I";
			}else{
				dto.setEstLaboratorio("Activo");
				estadox = "Desactivar";
				est = "A";
			}
			dto.setAccion("<a href='" + Constants.contextPath + "mtoLab.do?cmd=hash&idLaboratorio=" + dto.getIdLaboratorio() + "&nombreLaboratorio=" + dto.getNombreLaboratorio() + "&abrevLaboratorio=" + dto.getAbrevLaboratorio() + "&numFilas=" + dto.getNumFilas() + "&numColumnas=" + dto.getNumColumnas() + "&estLaboratorio=" + est + "'>" + estadox +"</a>");
			if(labo.getEstLaboratorio().equals("A")){
				listActivo = services.removeItemToList(listActivo, labo.getIdLaboratorio());
				listInactivo.add(dto);
			}else{
				listInactivo = services.removeItemToList(listInactivo, labo.getIdLaboratorio());
				listActivo.add(dto);
			}
			request.getSession().setAttribute("listLabActivas",null);
			request.getSession().setAttribute("listLabInactivas",null);
			request.getSession().setAttribute("listLabActivas", listActivo);
			request.getSession().setAttribute("listLabInactivas", listInactivo);
		}catch(Exception e){
			request.setAttribute("laboMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Genera un reporte de  Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}

}//class
