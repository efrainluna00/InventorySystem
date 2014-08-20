
package com.saldei.web.action.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.administracion.CarreraDto;
import com.saldei.web.bean.administracion.LaboratorioDto;
import com.saldei.web.form.administracion.CarreraForm;
import com.saldei.web.services.administracion.CarreraServices;

public class CarreraAction extends DispatchAction {
	
	private CarreraServices services = new CarreraServices();
	
	/**
	 * Inicializa los campos de la pantalla de Carrera
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listCarreraActivas",null);
		request.getSession().setAttribute("listCarreraInactivas",null);
		services.changeButtons(request);	
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los campos de la pantalla de Carrera
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listCarreraActivas",null);
		request.getSession().setAttribute("listCarreraInactivas",null);
		return mapping.findForward("success");
	}
	
	/**
	 * Cambia el estado de Acción
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("listCarreraActivas",null);
			request.getSession().setAttribute("listCarreraInactivas",null);
			String accion = (String) request.getSession().getAttribute("buttonsExtCarrera");
			if(accion == null)
				request.getSession().setAttribute("buttonsExtCarrera", "secondButtonCarrera");							
			else
				if(accion.equals("firstButtonCarrera"))
					request.getSession().setAttribute("buttonsExtCarrera", "secondButtonCarrera");				
				else
					request.getSession().setAttribute("buttonsExtCarrera", "firstButtonCarrera");				
			services.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			request.setAttribute("cfMsg", "error");
			return find(mapping, form, request, response);			
		}
	}
	
	/**
	 * Realiza una busqueda de Carrera
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			CarreraForm cf = (CarreraForm) form;
			ArrayList[] list = null;
			boolean action = services.knowAction(request);
			if(!action)				
				list = services.find(cf,true);
			else
				list = services.find(cf,false);
			if(list[0] == null && list[1] == null)
				request.setAttribute("cfMsg", "vacioLista");
			else{
				request.getSession().setAttribute("listCarreraActivas", list[0]);
				request.getSession().setAttribute("listCarreraInactivas", list[1]);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("cfMsg", "error");
		}		
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda una nueva Carrera
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
			CarreraForm cf = (CarreraForm) form;
			request.setAttribute("carrFormJsp", cf);
			if(!services.isNullLabForm(cf))
				request.setAttribute("cfMsg", "vacio");
			else{
				boolean flag = services.save(cf);
				if(flag)
					request.setAttribute("cfMsg", "exitoSave");
				else{
					request.setAttribute("cfMsg", "errorSave");
					return mapping.findForward("success");
				}							
			}
		}else{
			ArrayList<LaboratorioDto> listActivo = (ArrayList) request.getSession().getAttribute("listCarreraActivas");
			ArrayList<LaboratorioDto> listInactivo = (ArrayList) request.getSession().getAttribute("listCarreraInactivas");
			if(listActivo == null && listInactivo == null)
				request.setAttribute("cfMsg","saveAllNull");
			else{
				services.updateAll(listActivo, listInactivo);
				request.setAttribute("cfMsg","saveAllExito");
			}
		}
	}catch(Exception ex){
		ex.printStackTrace();
		request.setAttribute("cfMsg", "error");
	}
	return find(mapping, form, request, response);		
}
	
	/**
	 * Actualiza una Carrera 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{			
			CarreraForm cf = (CarreraForm) form;
			request.setAttribute("carrFormJsp", cf);
			boolean action = services.knowAction(request);
			if(!action){
				if(cf.getIdCarrera().equals("") || cf.getIdFacultad().equals("Seleccione") || cf.getNombreCarrera().equals("") || cf.getNomCarreraHid().equals("")){
					request.setAttribute("cfMsg", "vacioUpdate");
					return mapping.findForward("success");
				}					
				else{
					boolean flag = services.update(cf);
					if(flag)
						request.setAttribute("cfMsg", "exitoUpdate");
					else
						request.setAttribute("cfMsg", "errorUpdate");								
				}	
			}else{
				ArrayList[] list = services.findAll();
				request.getSession().setAttribute("listCarreraActivas", list[0]);
				request.getSession().setAttribute("listCarreraInactivas", list[1]);
			}				
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("cfMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Genera el Reporte de Carrera
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
	
	/**
	 * Desactiva o Activa una Carrera
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		CarreraForm cf = (CarreraForm) form;
		String estadox = "", est = "";
		ArrayList<CarreraDto> listActivo = (ArrayList) request.getSession().getAttribute("listCarreraActivas");
		ArrayList<CarreraDto> listInactivo = (ArrayList) request.getSession().getAttribute("listCarreraInactivas");
		CarreraDto dto = new CarreraDto();
		dto.setNombre(cf.getNombreCarrera());
		dto.setFacultad(cf.getFacultad());
		dto.setIdCarrera(cf.getIdCarrera());		
		if(cf.getEstado().equals("A")){
			dto.setEstado("Inactivo");
			estadox = "Activar";
			est = "I";
		}else{
			dto.setEstado("Activo");
			estadox = "Desactivar";			
			est = "A";
		}
		dto.setAccion("<a href='" + Constants.contextPath + "mtoCarr.do?cmd=hash&estado=" + est + "&facultad=" + dto.getFacultad() + "&nombreCarrera=" + dto.getNombre() + "&nomCarreraHid=" + dto.getIdCarrera() + "&idFacultad=" + cf.getIdFacultad() + "&idCarrera=" + dto.getIdCarrera() + "'>" + estadox +"</a>");
		if(cf.getEstado().equals("A")){
			listActivo = services.removeItemToList(listActivo, cf.getIdCarrera());
			listInactivo.add(dto);
		}else{
			listInactivo = services.removeItemToList(listInactivo, cf.getIdCarrera());
			listActivo.add(dto);
		}
		request.getSession().setAttribute("listCarreraActivas",null);
		request.getSession().setAttribute("listCarreraInactivas",null);
		request.getSession().setAttribute("listCarreraActivas", listActivo);
		request.getSession().setAttribute("listCarreraInactivas", listInactivo);
		return mapping.findForward("success");
	}

}//class
