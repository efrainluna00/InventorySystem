 package com.saldei.web.action.seguridad;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.web.action.ActionGeneral;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.seguridad.UsuarioForm;
import com.saldei.web.services.administracion.CarreraServices;
import com.saldei.web.services.administracion.MulticodeServices;
import com.saldei.web.services.seguridad.UsuarioServices;

/**
 * Maneja las Acciones que tiene  la pantalla de usuarios
 * @author WiRoCaRo
 * @version 1.1
 */
public class UsuarioAction extends DispatchAction {

	UsuarioServices userServices = new UsuarioServices();
	ActionGeneral   actionGnl    = new ActionGeneral();
	MulticodeServices multiServ  = new MulticodeServices();
	CarreraServices   carServ    = new CarreraServices(); 
	Usuario userLogin            = null; 
	
	/**
	 * Inicializa los Valores de El Formulario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			userLogin = (Usuario) request.getSession().getAttribute("usuario");
			if(userLogin != null){
				actionGnl.modificar(request);
				request.getSession().setAttribute("modulesUsuario", Constants.legend_first_module);//
				request.getSession().setAttribute("estCivil", multiServ.getEstadoCivil());		
				request.getSession().setAttribute("carrera",  carServ.getCarrera()); 
				request.getSession().setAttribute("anyoUsrIngreso", userServices.getAnyos());
				this.cleanSession(mapping, request);
			}
			else
				return mapping.findForward("login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Cambia los valores de los botones para el estado de Desactivacion y Limpia los Mapas de los usuarios
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward remover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		userLogin = (Usuario) request.getSession().getAttribute("usuario");
		try {
			if(userLogin != null){
				actionGnl.remover(request);		
				request.getSession().setAttribute("modulesUsuario", Constants.legend_second_module);//
				this.cleanSession(mapping, request);
			}else{
				return mapping.findForward("login");
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
		
	}

	/**
	 * Realiza una Busqueda segun el Form y pone la Accion dependiendo de los botones
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		userLogin = (Usuario) request.getSession().getAttribute("usuario");
		UsuarioForm usrForm = (UsuarioForm) form;
		request.setAttribute("UsuarioForm", usrForm);
		try{
			if(userLogin != null){
				String btn     = (String)request.getSession().getAttribute("btnRem"); 
				Boolean RemMod = true;
				if (btn.equals(Constants.btnRem1))
					RemMod = false;				
				Map <String, UsuarioDto> mapUserA = (Map <String, UsuarioDto> ) userServices.find(usrForm, "A", RemMod);
				Map <String, UsuarioDto> mapUserI = (Map <String, UsuarioDto> ) userServices.find(usrForm, "I", RemMod);		
				request.getSession().setAttribute("MapUserA", mapUserA);
				request.getSession().setAttribute("MapUserI", mapUserI);
				request.getSession().setAttribute("findBy",   usrForm );
			}else
				return mapping.findForward("login");
		}catch (Exception e) {
			 e.printStackTrace();
		}
		request.setAttribute("UsuarioForm", usrForm);
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda un Nuevo Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		userLogin = (Usuario) request.getSession().getAttribute("usuario");
		try {
			if(userLogin != null){
				UsuarioForm usrForm  = (UsuarioForm) form;	
				UsuarioDto    usrDto = new UsuarioDto();			
				if(userServices.isNullUsuarioForm(usrForm))
					request.setAttribute("usuMsg", "vacio");
				else{
					BeanUtils.copyProperties(usrDto, usrForm);
					usrDto.setEstUsuario("A");
					usrDto.setPswUsuario(this.userServices.generarContrasena());
					usrDto.setAutorizadoPor(userLogin.getIdUsuario());
					this.userServices.setIdUsuarioTipo(usrDto); /**Setea el IdUsuario segun el tipo que ha seleccionado */
					if(!userServices.isUser(usrDto.getIdUsuario())){
						boolean valor = userServices.saveProcess(usrDto);
						if (valor)
							request.setAttribute("usuMsg", "exito");
						else
							request.setAttribute("usuMsg", "errorSave");
					}
					else
						request.setAttribute("usuMsg", "existe");
				}
				request.setAttribute("UsuarioForm", usrForm);
			}else
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza los datos de un Usuario 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		userLogin   = (Usuario) request.getSession().getAttribute("usuario");
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		UsuarioForm usrForm  = (UsuarioForm) form;
		try {	
			if(userLogin != null){
				UsuarioDto usrDto = new UsuarioDto();
				BeanUtils.copyProperties(usrDto, usrForm);
				usrDto.setTipo(usrForm.getTipoHidden()); 
				boolean var = userServices.updateProcess(usrDto, usr.getIdUsuario());
				if (var)
					request.setAttribute("usuMsg", "exitoDml");
				else
					request.setAttribute("usuMsg", "errorAct");					
			}else
				return mapping.findForward("login");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("UsuarioForm", usrForm);
		actionGnl.modificar(request);	
		request.getSession().setAttribute("modulesUsuario", Constants.legend_first_module);//
		return mapping.findForward("success");
	}
	
	/**
	 * Hace la Ejecuccion de los grid 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		 UsuarioForm usrForm = (UsuarioForm) form;
		 try {
			Map<String, UsuarioDto> mapActivo    = (Map<String , UsuarioDto>)  request.getSession().getAttribute("MapUserA");
			Map<String, UsuarioDto> mapInactivo = (Map<String , UsuarioDto>)  request.getSession().getAttribute("MapUserI");
			UsuarioDto dto = new UsuarioDto();
			BeanUtils.copyProperties(dto,  usrForm);
			String estadox = "";
			String est = "";
			if(usrForm.getEstado().equals("A")){
				dto.setEstUsuario("A");
				estadox = "Activar";
				est = "I";
			}			
			else{
				dto.setEstUsuario("I");
				estadox = "Desactivar";
				est = "A";
			}	
			userServices.getAccionDto(dto, "hash", estadox, est);
			if(usrForm.getEstado().equals("A")){
				mapActivo.remove(dto.getIdUsuario());
				mapInactivo.put(dto.getIdUsuario(), dto);
			}else {
				mapInactivo.remove(dto.getIdUsuario());
				mapActivo.put(dto.getIdUsuario(), dto);
			}
			request.getSession().setAttribute("MapUserA", mapActivo);
			request.getSession().setAttribute("MapUserI", mapInactivo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("UsuarioForm", usrForm);
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda los Cambios de la desactivacion de Usuarios
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	 @SuppressWarnings("unchecked")
	public ActionForward guardarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, UsuarioDto> mapActivo    = (Map<String , UsuarioDto>)  request.getSession().getAttribute("MapUserA");
			Map<String, UsuarioDto> mapInactivo  = (Map<String , UsuarioDto>)  request.getSession().getAttribute("MapUserI");
			if(mapActivo != null && mapActivo.size() > 0)
				userServices.guardarMap(mapActivo, "A");
			if (mapInactivo != null && mapInactivo.size() > 0)
				userServices.guardarMap(mapInactivo, "I");
			request.setAttribute("usuMsg", "cambios"); 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("usuMsg", "errorCambios"); 
		}
		actionGnl.remover(request);	
		request.getSession().setAttribute("modulesUsuario", Constants.legend_second_module);//
		return mapping.findForward("success");
	}
	
	/** 
	 * Limpia las Sessiones para la Pagina
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cleanSession(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los Mapas del Usuario 
	 * @param mapping Objeto tipo ActionMapping
	 * @param request Objeto tipo HttpServletRequest
	 */
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().setAttribute("MapUserA", null);
		request.getSession().setAttribute("MapUserI", null);
	}
}
