
package com.saldei.web.action.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.web.form.administracion.ParametroForm;
import com.saldei.web.services.administracion.ParametroServices;

public class ParametroAction extends DispatchAction {
	
	private ParametroServices services = new ParametroServices();
	
	/**
	 * Limpia  los campos de la pantalla de Parametros
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("displayListParametro",null);		
		return mapping.findForward("success");
	}
	
	/**
	 * Inicializa los campos de la pantalla de Parametros
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda un nuevo registro de Parametro
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			ParametroForm param = (ParametroForm) form;
			request.setAttribute("parametroFormJsp", param);
			if(param.getNombre().equals("") || param.getDescripcion().equals("") || param.getValor().equals("")){
				request.setAttribute("paramMsg", "vacio");
				return mapping.findForward("success");
			}					
			else{
					boolean flag = services.save(param);
					if(flag)
						request.setAttribute("paramMsg", "exitoSave");
					else{
						request.setAttribute("paramMsg", "errorSave");
						return mapping.findForward("success");
					}						
				}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("paramMsg", "error");
		}		
		return find(mapping, form, request, response);		
	}
	
	/**
	 * Actualiza un registro de Parametro
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			ParametroForm param = (ParametroForm) form;
			request.setAttribute("parametroFormJsp", param);
			if(param.getNombre().equals("") || param.getDescripcion().equals("") || param.getValor().equals("") || param.getCodigo().equals("")){
				request.setAttribute("paramMsg", "errorUpdate");
				return mapping.findForward("success");
			}					
			else{
					boolean flag = services.update(param);
					if(flag)
						request.setAttribute("paramMsg", "exitoUpdate");
					else{
						request.setAttribute("paramMsg", "errorUpdateE");
						return mapping.findForward("success");
					}						
				}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("paramMsg", "error");
		}		
		return find(mapping, form, request, response);		
	}
	
	/**
	 * Realiza una busqueda de Parametro
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			ParametroForm param = (ParametroForm) form;
			ArrayList list = services.find(param);
			request.setAttribute("displayListParametro", null);
			if(list.size() == 0)
				request.setAttribute("paramMsg", "listVacias");
			else
				request.getSession().setAttribute("displayListParametro", list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * Elimina un registro de Parametro
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			ParametroForm param = (ParametroForm) form;
			boolean flag = services.delete(param);
			if(flag){
				request.setAttribute("paramMsg", "exitoUpdate");
				ArrayList list = services.find(param);
				request.getSession().setAttribute("displayListParametro", list);
			}
			else
				request.setAttribute("paramMsg", "errorUpdate");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Envia hacia la pagina de reporte
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
	
}//class
