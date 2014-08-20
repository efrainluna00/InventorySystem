package com.saldei.web.action.seguridad;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.bean.Util;
import com.saldei.web.bean.seguridad.OpcionesPerfilDto;
import com.saldei.web.form.seguridad.LoginForm;
import com.saldei.web.form.seguridad.OpcionesPerfilForm;
import com.saldei.web.services.seguridad.LoginServices;
import com.saldei.web.services.seguridad.OpcionesPerfilServices;
import com.saldei.web.services.seguridad.RecordarPwdServices;

/**
 * Acciones de la pantalla de Login
 * @author WiRoCaRo 
 * @version 1.0
 */
public class LoginAction extends DispatchAction {
	/** Servicios de Login*/
	private LoginServices service = new LoginServices();
		
	/**
	 * Inicializa los campos de la pantalla de Login
	 * @param mapping   ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("idPerfil", null);
		request.getSession().setAttribute("usuario",  null);
		request.getSession().setAttribute("Menu",     null);
		request.getSession().setAttribute("data",     null);
		request.getSession().setAttribute("opciones_de_Usuario",     null);		
		LoginForm  iform = (LoginForm) form;
		iform.setPswUsuario("");
		iform.setIdUsuario("");
		return mapping.findForward("failure");
	}
	public ActionForward correo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		return mapping.findForward("failure");
	}
	
	/**
	 * Valida si un Usuario esta Autorizado para ingresar al sistema
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		String destino = "failure";
		OpcionesPerfilServices opcionesPerfilServices =  new OpcionesPerfilServices();		
		Usuario user = null;
		try {
			LoginForm logForm  = (LoginForm) form;
			if(service.isNullForm(logForm))
				request.setAttribute("loginMsg","vacio");
			else{
				if(service.isUsrExist(logForm)){
					user = service.isUsrValid(logForm);/** Si el Usuario Existe el Sistema Valida que este Activo*/
					if (user != null){
						String strPerfilUsr  = service.getPerfilUsr(logForm);
						request.getSession().setAttribute("idPerfil", strPerfilUsr);
						request.getSession().setAttribute("usuario",  user);
						request.getSession().setAttribute("user", user.getIdUsuario());
						Map data = opcionesPerfilServices.opcionesPerfilUsuario(strPerfilUsr);
						if(data != null){
							request.getSession().setAttribute("opciones_de_Usuario", data);
						}
						destino = "success";
					}
					else
						request.setAttribute("loginMsg","inactivo");
				}	
				else
					request.setAttribute("loginMsg","invalido");
			}	
			String solicitudesPendientes = Util.obt_mov_pendientes("'"+user.getIdUsuario()+"'");
			if(solicitudesPendientes != null && !solicitudesPendientes.equals(""))
				request.setAttribute("mensajeSolPendientes",solicitudesPendientes);
			HibernateSessionFactory.getSession().close();
		} catch (Exception e) {
			e.printStackTrace();			
		}							
		return mapping.findForward(destino);
	}
	
	public ActionForward recordar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		LoginForm logForm  = (LoginForm) form;		
		try{
			if(logForm.getIdUsuario().equals(""))
				request.setAttribute("loginMsg","vacioPwd");
			else{
				RecordarPwdServices rps = new RecordarPwdServices();			
				boolean flag = rps.recordarPwd(logForm.getIdUsuario());
				if(!flag)
					request.setAttribute("loginMsg","noCorreoPwd");
				else
					request.setAttribute("loginMsg","exitoPwd");
			}			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("loginMsg","errorPwd");
		}
		return mapping.findForward("failure");
	}

	
	/**
	 * Envia por Correo la Contraseña del usuario ingresado
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request   HttpServletRequest
	 * @param response  HttpServletResponse
	 * @return
	 */
/*	public ActionForward sendEmailPwd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		try {
			InitForm iform = (InitForm) form;
			int flag =service.sendEmailPwd(iform);
			if(flag == 0)
				request.setAttribute("initMsg", "noExiste");
			else
				if(flag == 1)
					request.setAttribute("initMsg", "invalid");
				else
					if(flag == 2)
						request.setAttribute("initMsg", "noEmail");
					else
						request.setAttribute("initMsg", "email");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("failure");	
	}*/

}
