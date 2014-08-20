/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.action.registro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.web.form.registro.CargarDatosForm;
import com.saldei.web.services.registro.CargarDatosServices;

public class CargaDatosAction extends DispatchAction {
	
	private CargarDatosServices services = new CargarDatosServices();
	private Usuario usuarioAudit = null;

	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		List list = services.getCiclo();
		List listCarreras = services.getCarreras();
		request.getSession().setAttribute("carreras", listCarreras);
		request.getSession().setAttribute("ciclos", list);
		request.getSession().removeAttribute("listBuenos");
		request.getSession().removeAttribute("listMalos");
		request.getSession().removeAttribute("listBuenosDisplayTag");
		request.getSession().removeAttribute("listMalosDisplayTag");		
		return mapping.findForward("success");
	}
	
	public ActionForward verCorrectos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		List listBuenos = (List) request.getSession().getAttribute("listBuenosDisplayTag");
		request.setAttribute("listasBuenos", listBuenos);
		return mapping.findForward("correctos");
	}
	
	public ActionForward verIncorrectos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		List listMalos = (List) request.getSession().getAttribute("listMalosDisplayTag");
		request.setAttribute("listasMalas", listMalos);
		return mapping.findForward("correctos");
	}
	
	public ActionForward verificacionArchivo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			CargarDatosForm cdf = (CargarDatosForm) form;			
			request.getSession().removeAttribute("listBuenos");
			request.getSession().removeAttribute("listMalos");
			request.getSession().removeAttribute("listBuenosDisplayTag");
			request.getSession().removeAttribute("listMalosDisplayTag");	
			if(services.isNullForm(cdf))
				request.setAttribute("cdfMsg", "vacio");
			else{
				ArrayList list = services.recuperarInfoArchivo(cdf);
				if(list == null){
					request.setAttribute("cdfMsg", "valid");
					return mapping.findForward("success");
				}
				ArrayList[] listas = services.validarInfoExcel(list);
				request.getSession().setAttribute("carreraCarga", cdf.getCarrera());
				request.getSession().setAttribute("cicloCargaMasiva", cdf.getCiclo());
				request.getSession().setAttribute("listBuenos", String.valueOf(listas[0].size()) + "&nbsp; <a href='" + Constants.contextPath  +"cdatos.do?cmd=verCorrectos'>Ver Registros Correctos</a>");
				request.getSession().setAttribute("listMalos", String.valueOf(listas[1].size()) + "&nbsp; <a href='" + Constants.contextPath  +"cdatos.do?cmd=verIncorrectos'>Ver Registros Incorrectos</a>");
				request.getSession().setAttribute("listBuenosDisplayTag", listas[0]);
				request.getSession().setAttribute("listMalosDisplayTag", listas[1]);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("cdfMsg", "error");
		}		
		return mapping.findForward("success");
	}
	
	public ActionForward cargarArchivo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			ArrayList list = (ArrayList) request.getSession().getAttribute("listBuenosDisplayTag");
			if(list == null || list.size() == 0)
				request.setAttribute("cdfMsg", "noList");
			else{
				String ciclo = (String) request.getSession().getAttribute("cicloCargaMasiva");
				String carrera = (String) request.getSession().getAttribute("carreraCarga");
				boolean flag = services.cargarDatos(list, ciclo, carrera, usuarioAudit);
				if(flag)
					request.setAttribute("cdfMsg", "exito");
				else
					request.setAttribute("cdfMsg", "error");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("cdfMsg", "error");
		}		
		return mapping.findForward("success");
	}
	
	public ActionForward historial(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			ArrayList list = services.mostrarHistorial();
			request.getSession().setAttribute("listHistorial", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}//class
