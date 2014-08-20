
package com.saldei.web.action.seguridad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.web.bean.seguridad.PerfilDto;
import com.saldei.web.form.seguridad.AsignarPerfilForm;
import com.saldei.web.services.seguridad.AsignarPerfilServices;
import com.saldei.web.services.seguridad.UsuarioServices;

public class AsignarPerfilAction extends DispatchAction {
	
	private AsignarPerfilServices services = new AsignarPerfilServices();
	private UsuarioServices servUser = new UsuarioServices();
	private Usuario usuarioAudit;
	
	/**
	 * Inicializa la pantalla de Asignar Perfil
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		List lstUser = servUser.getUsers();
		for(int i=0; i<lstUser.size(); i++){
			Usuario usuario = (Usuario) lstUser.get(i);
			usuario.setPrimerNom(usuario.getPrimerNom() + " " + usuario.getPrimerApe());
			lista.add(usuario);
		}
		request.getSession().setAttribute("usuarios",      lstUser);
		this.cleanSession(form, mapping, request);
		return mapping.findForward("success");
	}
		
	/**
	 * Ver los perfiles que tienen un Usuario en especifico
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			AsignarPerfilForm apForm = (AsignarPerfilForm) form;
			if(services.isNullUsers(apForm)){
				request.setAttribute("msgAsgPer", "vacio");
				this.cleanSession(form, mapping, request);
			}	
			else{
				Map mapUserPerfil = services.perfilUsuarios(apForm);  
				Map mapPerfil     = services.notPerfilUsuarios(apForm);
				request.getSession().setAttribute("mapUserPerfil", mapUserPerfil);
				request.getSession().setAttribute("mapPerfil", mapPerfil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}	
	
	/**
	 * Asigna un perfil a un usuario 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {			
			AsignarPerfilForm apf = (AsignarPerfilForm) form;
			Map mapUserPerfil 	  = (Map)request.getSession().getAttribute("mapUserPerfil");
			Map mapPerfil     	  = (Map)request.getSession().getAttribute("mapPerfil");
			if (mapPerfil !=null && mapPerfil.size()>0){
				PerfilDto dto = (PerfilDto) mapPerfil.get(apf.getIdPerfil());
				mapPerfil.remove(apf.getIdPerfil());
				services.getAccion(dto, apf.getIdUsuario(), "Remover Perfil", "Remove");
				mapUserPerfil.put(dto.getCodigo(), dto);
				request.getSession().setAttribute("mapUserPerfil", mapUserPerfil);
				request.getSession().setAttribute("mapPerfil",     mapPerfil);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");                        
	}
	
	/** Quita un Perfil de un Usuario 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Remove(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			AsignarPerfilForm apf = (AsignarPerfilForm) form;
			Map mapUserPerfil     = (Map)request.getSession().getAttribute("mapUserPerfil");
			Map mapPerfil         = (Map)request.getSession().getAttribute("mapPerfil");
			if (mapUserPerfil !=null && mapUserPerfil.size()>0){
				PerfilDto dto = (PerfilDto) mapUserPerfil.get(apf.getIdPerfil());
				mapUserPerfil.remove(apf.getIdPerfil());
				services.getAccion(dto, apf.getIdUsuario(), "Asignar Perfil", "Add");
				mapPerfil.put(dto.getCodigo(), dto);
				request.getSession().setAttribute("mapUserPerfil", mapUserPerfil);
				request.getSession().setAttribute("mapPerfil",     mapPerfil);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda los cambios a un Usuario 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			AsignarPerfilForm apf = (AsignarPerfilForm) form;
			Map mapUserPerfil     = (Map)request.getSession().getAttribute("mapUserPerfil");
			boolean boolsave      =  services.saveProcess(apf.getIdUsuario(), mapUserPerfil, usuarioAudit);
			if (boolsave)
				request.setAttribute("msgAsgPer", "exito");
			else
				request.setAttribute("msgAsgPer", "errorSave");
		} catch (Exception e) {
			request.setAttribute("msgAsgPer", "errorSave");
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
		AsignarPerfilForm perForm = (AsignarPerfilForm) form;	
		perForm.setUsr(Constants.Seleccione);
		request.getSession().setAttribute("mapUserPerfil", null);
		request.getSession().setAttribute("mapPerfil",     null);		
	}

}

