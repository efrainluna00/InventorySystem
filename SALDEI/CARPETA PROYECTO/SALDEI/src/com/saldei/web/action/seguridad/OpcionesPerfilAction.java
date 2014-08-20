package com.saldei.web.action.seguridad;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.web.bean.seguridad.OpcionesPerfilDto;
import com.saldei.web.form.seguridad.OpcionesPerfilForm;
import com.saldei.web.services.seguridad.OpcionesPerfilServices;
import com.saldei.web.services.seguridad.PerfilServices;

public class OpcionesPerfilAction extends DispatchAction {

	private OpcionesPerfilServices services = new OpcionesPerfilServices();
	private PerfilServices      perServices = new PerfilServices();
	private Usuario usuarioAudit = null;
	
	/**
	 * Inicializa el formulario de la pantalla 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward inicio (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("per", null);
		this.cleanSession(form, mapping, request);
		request.getSession().setAttribute("per", perServices.getPerfiles());
		return mapping.findForward("success"); 
	}
	
	/**
	 * Obtiene los mapas de Opciones que no contiene un Perfil y Opciones de Perfil
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward find (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		OpcionesPerfilForm opcForm = (OpcionesPerfilForm) form;
		try {
			if(services.isNullPerfil(opcForm)){
				request.setAttribute("opcPer", "vacio");
				this.cleanSession(form, mapping, request);
			}else {
				Map mapOpc        = services.opcion(opcForm);
				Map mapOpcPer     = services.opcionPerfil(opcForm); 
				request.getSession().setAttribute("mapOpcPerfil",    mapOpcPer);
				request.getSession().setAttribute("mapOpc",          mapOpc);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return mapping.findForward("success"); 
	} 
		
	/**
	 * Asigna una Opcion al Mapa de las Opciones para el Perfil 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			OpcionesPerfilForm opcForm  = (OpcionesPerfilForm) form;
			Map mapOpc                  = (Map) request.getSession().getAttribute("mapOpc");
			Map mapOpcPerfil			= (Map) request.getSession().getAttribute("mapOpcPerfil");
			String strIdOpc		        = String.valueOf(opcForm.getIdOpcion()).trim();
			if (strIdOpc != null && !strIdOpc.trim().equals(""))
			{ 
				if (mapOpc != null && mapOpc.size() > 0){
					OpcionesPerfilDto opcDto = (OpcionesPerfilDto) mapOpc.get(strIdOpc);
					OpcionesPerfilServices.getAccion("Remover Opción", opcDto, "Remove");
					mapOpc.remove(strIdOpc);
					if (opcDto != null){
						mapOpcPerfil.put(strIdOpc, opcDto);
						request.getSession().setAttribute("mapOpc", mapOpc);
						request.getSession().setAttribute("mapOpcPerfil", mapOpcPerfil);
					}
				}
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * Quita una Opcion al Mapa de las Opciones x Perfil 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Remove(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			OpcionesPerfilForm opcForm  = (OpcionesPerfilForm) form;
			Map mapOpc                  = (Map) request.getSession().getAttribute("mapOpc");
			Map mapOpcPerfil			= (Map) request.getSession().getAttribute("mapOpcPerfil");
			String strIdOpc		        = String.valueOf(opcForm.getIdOpcion()).trim();
			if (strIdOpc != null && !strIdOpc.trim().equals(""))
			{ 
				if (mapOpcPerfil != null && mapOpcPerfil.size() > 0){
					OpcionesPerfilDto opcDto = (OpcionesPerfilDto) mapOpcPerfil.get(strIdOpc);
					OpcionesPerfilServices.getAccion("Asignar Opción", opcDto, "Add");
					mapOpcPerfil.remove(strIdOpc);
					if (opcDto != null){
						mapOpc.put(strIdOpc, opcDto);
						request.getSession().setAttribute("mapOpc", mapOpc);
						request.getSession().setAttribute("mapOpcPerfil", mapOpcPerfil);
					}
				}
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * Elimina las opciones de un Perfil 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			OpcionesPerfilForm opcForm  = (OpcionesPerfilForm) form;
			Map mapOpcPerfil			= (Map) request.getSession().getAttribute("mapOpcPerfil");
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			boolean boolsave 			= services.processSave(mapOpcPerfil, opcForm.getPerfil(), usuarioAudit); 
			if (boolsave)
				request.setAttribute("opcPer", "exito");
			else
				request.setAttribute("opcPer", "errorSave");			
		} catch (Exception e) {
			request.setAttribute("opcPer", "errorSave");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia las Sessiones de el formulario
	 * @param form     Objeto del tipo ActionForm
	 * @param mapping  Objeto del tipo ActionMapping
	 * @param request  Objeto del tipo HttpServletRequest
	 */
	public void cleanSession(ActionForm form, ActionMapping mapping, HttpServletRequest request){
		OpcionesPerfilForm opcForm = (OpcionesPerfilForm) form;	
		opcForm.setPerfil(Constants.Seleccione);
		request.getSession().setAttribute("mapOpc",       null);
		request.getSession().setAttribute("mapOpcPerfil", null);
	}
	
	
	
}
