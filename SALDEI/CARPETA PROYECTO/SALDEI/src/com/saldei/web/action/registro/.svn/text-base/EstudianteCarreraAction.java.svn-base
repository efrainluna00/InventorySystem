package com.saldei.web.action.registro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.saldei.util.commons.Util;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.EstudianteCarreraDto;
import com.saldei.web.form.registro.EstudianteCarreraForm;
import com.saldei.web.services.administracion.CarreraServices;
import com.saldei.web.services.seguridad.UsuarioServices;

public class EstudianteCarreraAction extends DispatchAction {
	CarreraServices   carServ    = new CarreraServices(); 
	UsuarioServices userServices = new UsuarioServices();
	private JdbcHelper jdbc = new JdbcHelper();
	private Util util = new Util();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String carnetEstudiante      = (String) request.getParameter("idUsuario");
		EstudianteCarreraForm estCar = new EstudianteCarreraForm();
		try{
			request.getSession().removeAttribute("UsrVigencia");
			request.getSession().removeAttribute("carrera");
			request.getSession().removeAttribute("lstEstCar");
			estCar.setCarnetEst(carnetEstudiante);
			request.getSession().setAttribute("carrera",  carServ.getCarrera());
			request.getSession().setAttribute("lstEstCar",userServices.EstCarreraFind(carnetEstudiante));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("EstCar", estCar);
		return mapping.findForward("success");
	}
	
	public ActionForward saveEstCar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		EstudianteCarreraForm frm = (EstudianteCarreraForm) form;
		try{
			if(frm.getIdCarrera()!= null && !frm.getIdCarrera().equals("") && !frm.getIdCarrera().equals("Seleccione") )
			{
				String[] Carrera = frm.getIdCarrera().split("-");
				if (Carrera.length > 1){
					frm.setIdCarrera(Carrera[0].trim());
					frm.setPlanEstudio(Carrera[1].trim());
				}
				EstudianteCarreraDto  dto = new EstudianteCarreraDto();
				BeanUtils.copyProperties(dto,frm);
				if(!userServices.isEstCar(dto)){
					if(validarFecha(util.getFechaFormato(dto.getFechaIni()))){
							userServices.saveEstCarProcess(dto);
							request.getSession().removeAttribute("lstEstCar");
							request.getSession().setAttribute("lstEstCar",userServices.EstCarreraFind(dto.getCarnetEst()));
							request.setAttribute("vigenciaCarrera", "exito");
					}
					else request.setAttribute("vigenciaCarrera", "errorFecha");
				}
				else request.setAttribute("vigenciaCarrera", "validacionFecha");
				
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("EstCar", frm);
		return mapping.findForward("success");
	}
	public boolean validarFecha(String p_strFecha) {
		String query = "select date(now()) - date('"+p_strFecha +"') as fecha";
		String resultado="";
		boolean flag=false;
		try{									
			List list = jdbc.getQuery(query, null);
			if(list.size()>0){
				DynaBean dyna 	= 	(DynaBean) list.get(0);
				resultado		=	dyna.get("fecha").toString();
				if(Integer.parseInt(resultado)<0)
						flag	=	true;
				else flag		= 	false;
			}			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return flag;
		
	}

	
}
