package com.saldei.web.action.registro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Util;
import com.saldei.web.bean.registro.VigenciaDto;
import com.saldei.web.form.registro.VigenciaForm;
import com.saldei.web.services.registro.VigenciaServices;

public class VigenciaAction extends DispatchAction {
	VigenciaServices vigServices = new VigenciaServices();
	Util util = new Util();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String idUsuario   = (String) request.getParameter("idUsuario");
		String idCarrera   = (String) request.getParameter("idCarrera");
		String planEstudio = (String) request.getParameter("planEstudio");
		request.getSession().setAttribute("idUsu", idUsuario);
		request.getSession().setAttribute("idCar", idCarrera);
		request.getSession().setAttribute("planE", planEstudio);	
		
		VigenciaForm frm = (VigenciaForm) form;
		try{
			request.getSession().removeAttribute("UsrVigencia");
			request.getSession().removeAttribute("CarVigencia");
			if (idUsuario != null){
				request.getSession().setAttribute("UsrVigencia", vigServices.getUsuario(idUsuario));
				frm.setIdUsuario(idUsuario);
				frm.setTipo("U");
			}
			else if(idCarrera != null && planEstudio != null){
				request.getSession().setAttribute("CarVigencia", vigServices.getCarreras(idCarrera, planEstudio));
				frm.setIdCarrera(idCarrera);
				frm.setPlanEstudio(planEstudio);
				frm.setTipo("C");
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("VigenciaForm", frm);
		return mapping.findForward("success");
	}
	
	public ActionForward saveUsrVigencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String idUsuario    = (String) request.getParameter("idUsuario");
		String idCarrera    = (String) request.getParameter("idCarrera");
		String planEstudio  = (String) request.getParameter("planEstudio");
		VigenciaForm frm 	= null;
		
		try {
			frm = (VigenciaForm) form;
			boolean flag=false;
			VigenciaDto  dto = new VigenciaDto();
			BeanUtils.copyProperties(dto,frm);
			if(vigServices.isUsuarioVigencia(dto)){
				if(vigServices.validarFecha(util.getFechaFormato(dto.getFechaIni()))){
					flag=vigServices.saveUsuarioVigencia(dto);
					if(flag){
						request.setAttribute("vigencia", "exito");
						request.getSession().setAttribute("UsrVigencia", vigServices.getUsuario(idUsuario));
					}
					else
						request.setAttribute("vigencia", "error");
				}
				else request.setAttribute("vigencia", "errorFecha");
			}
			else request.setAttribute("vigencia", "errorFechaIni");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("idUsuario", idUsuario);
		request.setAttribute("idCarrera", idCarrera);
		request.setAttribute("planEstudio", planEstudio);
		request.setAttribute("VigenciaForm", frm);
		return mapping.findForward("success");
	}
	
	public ActionForward saveCarVigencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String idCarrera   = (String) request.getParameter("idCarrera");
		String planEstudio = (String) request.getParameter("planEstudio");
		VigenciaForm frm 	= null;
		try {	
			frm = (VigenciaForm) form;
			boolean flag=false;
			VigenciaDto  dto = new VigenciaDto();
			BeanUtils.copyProperties(dto,frm);
			if(vigServices.isCarreraVigencia(dto)){
				if(vigServices.validarFecha(util.getFechaFormato(dto.getFechaIni()))){
					flag=vigServices.saveCarreraVigencia(dto);
					if(flag){
						request.setAttribute("vigencia", "exito");
						request.getSession().setAttribute("CarVigencia", vigServices.getCarreras(idCarrera, planEstudio));
					}
					else
						request.setAttribute("vigencia", "error");
				}
				else request.setAttribute("vigencia","errorFecha");
			}else
				request.setAttribute("vigencia","errorFecha2");
		} catch (Exception e){
			e.printStackTrace();
		}
		request.setAttribute("VigenciaForm", frm);
		return mapping.findForward("success");
	}

}
