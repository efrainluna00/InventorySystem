package com.saldei.web.action.seguridad;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Util;
import com.saldei.web.services.administracion.CarreraServices;
import com.saldei.web.services.administracion.MulticodeServices;
import com.saldei.web.services.seguridad.UsuarioServices;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.seguridad.SolicitarUsrForm;


public class SolicitarUsrAction extends DispatchAction {

	private UsuarioServices  userServices = new UsuarioServices();
	private MulticodeServices   multiServ = new MulticodeServices();
	private CarreraServices       carServ = new CarreraServices();
	private Util 					 util = new Util();
	
	/**
	 * Inicializa los Valores de El Formulario de Solicitud de Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			request.setAttribute("SolicitarUsrForm",         new SolicitarUsrForm()); 
			request.getSession().setAttribute("estCivil", multiServ.getEstadoCivil());		
			request.getSession().setAttribute("carrera",  carServ.getCarrera()); 
			request.getSession().setAttribute("anyoUsrIngreso", userServices.getAnyos());
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return mapping.findForward("success");
	}

	/**
	 * Ingresa la Solicitud de un Nuevo Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward send(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){	
		SolicitarUsrForm usrForm = (SolicitarUsrForm) form;
		try {
			UsuarioDto usrDto = new UsuarioDto();	
			boolean existe	= false;			
			BeanUtils.copyProperties(usrDto,usrForm);
			usrDto.setEstUsuario("S");
			usrDto.setFinVigencia(util.dateToStringDDMMYYYY(util.getFechaServidor()));
			usrDto.setIniVigencia(util.dateToStringDDMMYYYY(util.getFechaServidor()));
			usrDto.setFechaSolicitud(util.dateToStringDDMMYYYY(util.getFechaServidor()));
			usrDto.setPswUsuario(this.userServices.generarContrasena());
			this.userServices.setIdUsuarioTipo(usrDto); /**Setea el IdUsuario segun el tipo que ha seleccionado */
			if (usrDto.getIdUsuario() != null && !usrDto.getIdUsuario().equals(""))
				existe = userServices.isUser(usrForm.getIdUsuario());
			if(existe)
				request.setAttribute("solMsg", "existe");
			else{				
				boolean send = userServices.saveProcess(usrDto);
				if (send)
					request.setAttribute("solMsg", "exito");
				else
					request.setAttribute("solMsg", "errorSave");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("solMsg", "error");
		}
		return mapping.findForward("success");
	}	
}
