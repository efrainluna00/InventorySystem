package com.saldei.web.action.seguridad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.web.form.seguridad.CambiarPwdForm;
import com.saldei.web.services.seguridad.CambiarPwdServices;

public class CambiarPwdAction extends DispatchAction {

	private CambiarPwdServices services = new CambiarPwdServices();
	
	/**
	 * Inicializa los campos de la pantalla de cambiar Pwd
	 * @param mapping   ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		CambiarPwdForm PwdForm = (CambiarPwdForm)form ;
		PwdForm.setPwdActual("");
		PwdForm.setPwdConfirm("");
		PwdForm.setPwdNew("");
		return mapping.findForward("success");			
	}
	
	/**
	 * Cambia el Pwd para el Usuario que se ha logeado al sistema
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward cambiarPwd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			Usuario user 		   = (Usuario)request.getSession().getAttribute("usuario");
			CambiarPwdForm Pwdform = (CambiarPwdForm) form;
			if(!services.isNullForm(Pwdform))
				request.setAttribute("cmbPwd","vacio");
			else{
				if (!Pwdform.getPwdNew().equals(Pwdform.getPwdConfirm()))
					request.setAttribute("cmbPwd","newPswDif");
				else{
					//TODO encriptar Pws para comprobar
					if(!Pwdform.getPwdActual().trim().equals(user.getPswUsuario().trim()))
						request.setAttribute("cmbPwd","oldPswDif");	
					else{
						user.setPswUsuario(Pwdform.getPwdNew());						
						boolean cambiada = services.changePwd(user);
						if (cambiada){
							request.setAttribute("cmbPwd", "exito");
							request.getSession().setAttribute("usuario", user);
						}
						else
							request.setAttribute("cmbPwd", "error");	
					}	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
