/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.action.registro;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.registro.GestionUsuarioDto;
import com.saldei.web.form.registro.GestionUsuarioForm;
import com.saldei.web.services.registro.GestionUsuarioServices;

public class GestionUsuarioAction extends DispatchAction{

	private GestionUsuarioServices services = new GestionUsuarioServices();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	public ActionForward changeUsuario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			GestionUsuarioForm guf = (GestionUsuarioForm) form;
			ArrayList lista = services.estadoUsuario(guf.getUsuario());
			if(lista == null || lista.size() == 0)
				request.setAttribute("gufMsg", "noexiste");
			else
				request.getSession().setAttribute("estadogest", lista);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("gufMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			GestionUsuarioForm guf = (GestionUsuarioForm) form;
			ArrayList list = services.getUser(guf);
			request.getSession().setAttribute("gufDisplayTag", list);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("gufMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward saveAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			GestionUsuarioForm guf = (GestionUsuarioForm) form;
			int flag = services.gestionarUsuario(guf);
			if(flag == 0)
				request.setAttribute("gufMsg", "errorMail");
			else
				if(flag == 1)
				request.setAttribute("gufMsg", "exito");
				else
					request.setAttribute("gufMsg", "error");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("gufMsg", "error");
		}
		return changeUsuario(mapping, form, request, response);
	}
	
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			GestionUsuarioForm guf = (GestionUsuarioForm) form;
			GestionUsuarioDto dto = new GestionUsuarioDto();
			dto.setIdUsuario(guf.getUsuario());
			dto.setNombreCompleto(guf.getNombreCompleto());
			dto.setCorreo(guf.getCorreo());
			if(guf.getEstUser().equals("1")){
				dto.setEstado("Se activara el Usuario");
				dto.setAccion("<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
						"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=3'>Regresar</a>");
			}
			if(guf.getEstUser().equals("2")){
				dto.setEstado("Se rechazara el Usuario");
				dto.setAccion("<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
						"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=3'>Regresar</a>");
			}
			if(guf.getEstUser().equals("3")){
				dto.setEstado("Se activara el Usuario");
				dto.setAccion("<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
						"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=3'>Regresar</a>");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("gufMsg", "error");
		}
		return mapping.findForward("success");
	}

}//class