package com.saldei.web.action.seguridad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.web.form.seguridad.RptPerfilUsuarioForm;

import com.saldei.web.services.seguridad.RptPerfilUsuarioService;

public class RptInformacionPersonal extends DispatchAction {
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {			 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");			
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr == null){
				return mapping.findForward("login");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward rptInformacionPersonal(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptPerfilUsuarioForm rptPerfilUsuario = (RptPerfilUsuarioForm)form;
		RptPerfilUsuarioService service = new RptPerfilUsuarioService();		
		String formato="";	

		formato = rptPerfilUsuario.getFormato();
		GenerarReportesAction gRpt = new GenerarReportesAction();		
		
		String banderaVacio="";
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		int hayDatos=service.hayDatos3(rptPerfilUsuario);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '1' as id_usuario,'' as primer_nom,'' as primer_ape,'' as aperestante,'' as nombrerestante,'' as sexo,'' as codigo,'' as direccion_part,'' as email,'' as telefono_casa,'' as telefono_cel,'' as telefono_trabajo";			
		}
		else{
			banderaVacio="";
			queryString =service.query3(rptPerfilUsuario);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgInformacionPersonal.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pVacio",banderaVacio);
		parameters.put("pTipoUsuario", rptPerfilUsuario.getTipousuario());		
		
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}
}
