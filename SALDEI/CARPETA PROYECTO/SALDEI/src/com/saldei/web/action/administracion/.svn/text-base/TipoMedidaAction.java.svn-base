package com.saldei.web.action.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.administracion.TipoMedidaDto;
import com.saldei.web.form.administracion.TipoMedidaForm;
import com.saldei.web.services.administracion.TipoMedidaServices;

public class TipoMedidaAction extends DispatchAction{
	
	private TipoMedidaServices services = new TipoMedidaServices();

	/**
	 * Inicializa los campos de la pantalla de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listTipoMedidaActivas",null);
		request.getSession().setAttribute("listTipoMedidaInactivas",null);
		services.changeButtons(request);	
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los campos de la pantalla de Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("listTipoMedidaActivas",null);
		request.getSession().setAttribute("listTipoMedidaInactivas",null);
		return mapping.findForward("success");
	}
	
	/**
	 * Elimia un registro de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			TipoMedidaForm mtip = (TipoMedidaForm) form;
			request.setAttribute("tipoMedidaFormJsp", mtip);
			if(mtip.getNombre().equals("") || mtip.getCodigo().equals(""))
				request.setAttribute("mtipMsg", "vacio");
			else{
				boolean flag = services.delete(mtip);
				if(flag)
					request.setAttribute("mtipMsg", "exitoUpdate");
				else
					request.setAttribute("mtipMsg", "errorUpdate");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("mtipMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Cambia el modo de Acción
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("listTipoMedidaActivas",null);
			request.getSession().setAttribute("listTipoMedidaInactivas",null);
			String accion = (String) request.getSession().getAttribute("buttonsExtTipoMed");
			if(accion == null)
				request.getSession().setAttribute("buttonsExtTipoMed", "secondButtonTipoMed");
			else
				if(accion.equals("firstButtonTipoMed"))
					request.getSession().setAttribute("buttonsExtTipoMed", "secondButtonTipoMed");
				else
					request.getSession().setAttribute("buttonsExtTipoMed", "firstButtonTipoMed");
			services.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			request.setAttribute("mtipMsg", "error");
			return find(mapping, form, request, response);			
		}		
	}
	
	/**
	 * Guarda un nuevo Registro de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			TipoMedidaForm mtip = (TipoMedidaForm) form;
			request.setAttribute("tipoMedidaFormJsp", mtip);
			boolean action = services.knowAction(request);
			if(!action){
				if(mtip.getNombre().equals("")){
					request.setAttribute("mtipMsg", "vacio");
					return mapping.findForward("success");
				}					
				else{
					boolean flag = services.save(mtip);
					if(flag)
						request.setAttribute("mtipMsg", "exitoSave");
					else
						request.setAttribute("mtipMsg", "errorSave");
				}	
			}else{
				ArrayList<TipoMedidaDto> listActivo = (ArrayList) request.getSession().getAttribute("listTipoMedidaActivas");
				ArrayList<TipoMedidaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listTipoMedidaInactivas");
				if(listActivo == null && listInactivo == null)
					request.setAttribute("mtipMsg","saveAllNull");
				else{
					services.updateAll(listActivo, listInactivo);
					request.setAttribute("mtipMsg","saveAllExito");
				}
				return mapping.findForward("success");
			}	
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("mtipMsg", "error");
		}		
		return find(mapping, form, request, response);
	}
	
	/**
	 * Actualiza un Registro de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			TipoMedidaForm mtip = (TipoMedidaForm) form;
			request.setAttribute("tipoMedidaFormJsp", mtip);
			boolean action = services.knowAction(request);
			if(!action){
				if(mtip.getNombre().equals("") || mtip.getCodigo().equals(""))
					request.setAttribute("mtipMsg", "vacio");
				else{
					boolean flag = services.update(mtip);
					if(flag)
						request.setAttribute("mtipMsg", "exitoUpdate");
					else
						request.setAttribute("mtipMsg", "errorUpdate");
					return find(mapping, form, request, response);
				}	
			}else{
				ArrayList[] listas = services.findAll();
				request.getSession().setAttribute("listTipoMedidaActivas", listas[0]);
				request.getSession().setAttribute("listTipoMedidaInactivas", listas[1]);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("mtipMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Realiza una busqueda de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			TipoMedidaForm mtip = (TipoMedidaForm) form;
			ArrayList[] listas = null;
			boolean action = services.knowAction(request);
			if(!action)
				listas = services.find(mtip, true);
			else
				listas = services.find(mtip, false);
			request.getSession().setAttribute("listTipoMedidaActivas", listas[0]);
			request.getSession().setAttribute("listTipoMedidaInactivas", listas[1]);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("mtipMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Realiza un cambio de Activación
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMedidaForm mtip = (TipoMedidaForm) form;
		String estadox 		= "", est = "";
		ArrayList<TipoMedidaDto> listActivo = (ArrayList) request.getSession().getAttribute("listTipoMedidaActivas");
		ArrayList<TipoMedidaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listTipoMedidaInactivas");
		TipoMedidaDto dto = new TipoMedidaDto();
		dto.setCodigo(mtip.getCodigo());
		dto.setNombre(mtip.getNombre());		
		if(mtip.getEst().equals("A")){
			dto.setEstado("Inactivo");
			estadox = "Activar";
			est = "I";
		}else{
			dto.setEstado("Activo");
			estadox = "Desactivar";
			est = "A";
		}
		dto.setAccion("<a href='" + Constants.contextPath + "mtoTipMed.do?cmd=hash&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&est=" + est + "'>" + estadox +"</a>");
		if(mtip.getEst().equals("A")){
			listActivo = services.removeItemToList(listActivo, mtip.getNombre());
			listInactivo.add(dto);
		}else{
			listInactivo = services.removeItemToList(listInactivo, mtip.getNombre());
			listActivo.add(dto);
		}
		request.getSession().setAttribute("listTipoMedidaActivas", listActivo);
		request.getSession().setAttribute("listTipoMedidaInactivas", listInactivo);
		return mapping.findForward("success");
	}

	/**
	 * Genera el Reporte de Tipo Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
}
