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
import com.saldei.util.commons.Querys;
import com.saldei.util.commons.Util;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.seguridad.RptPerfilUsuarioForm;

import com.saldei.web.services.seguridad.RptPerfilUsuarioService;

public class RptPerfilUsuarioAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	Util util = new Util();	
	
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {						
			this.cleanSession(mapping, request); 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			RptPerfilUsuarioService service = new RptPerfilUsuarioService();
			request.getSession().setAttribute("perf",service.getPerfiles());
			request.getSession().setAttribute("opc", service.getOpciones());			
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
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().removeAttribute("cic");		
		request.getSession().removeAttribute("cat");		
	}
	public ActionForward rptPerfilUsuario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
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
		int hayDatos=service.hayDatos(rptPerfilUsuario);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select  0 as id_perfil,'' as nom_perfil,'' as desc_perfil,'' as id_usuario,'' as primer_nom,'' as primer_ape,'' as aperestante,'' as nombrerestante";			
		}
		else{
			banderaVacio="";
			queryString =service.query(rptPerfilUsuario);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgPerfilesUsuario.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pVacio",banderaVacio);
		parameters.put("pTipoUsuario", rptPerfilUsuario.getTipousuario());
		parameters.put("pIdUsuario", rptPerfilUsuario.getIdusuario());
		
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}
	public ActionForward rptOpcionesPerfil(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
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
		int hayDatos=service.hayDatos2(rptPerfilUsuario);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '1' as id_perfil,'' as nom_perfil,'' as nom_opcion,'' as desc_opcion";			
		}
		else{
			banderaVacio="";
			queryString =service.query2(rptPerfilUsuario);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgOpcionesPerfil.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pVacio",banderaVacio);
		
		
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}	
	public ActionForward rptOpciones(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptPerfilUsuarioForm rptPerfilUsuario = (RptPerfilUsuarioForm)form;
		RptPerfilUsuarioService service = new RptPerfilUsuarioService();		
		String formato="";	
		String idOpcionPadre=rptPerfilUsuario.getOpcion();

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
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgOpciones.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);				
		parameters.put("pOpcion",idOpcionPadre);
		parameters.put("pNombreOpcion",service.recuperarOpcion(idOpcionPadre));
		
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}
	
	public ActionForward rptDirectores(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
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
		int hayDatos=service.hayDatos();
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '1' as id_laboratorio,'' as nombre_laboratorio,'' as id_usuario,'' as primer_nom,'' as primer_ape,'' as fecha_ini,'' as fecha_fin,'' as email,'' as telefono_trabajo,'' as apellidoRestante,'' as nombreRestante";
		}
		else{
			banderaVacio="";
			queryString="select distinct cast(b.id_laboratorio as varchar) as id_laboratorio,b.nombre_laboratorio,u.id_usuario,u.primer_nom, ";
			queryString+="u.primer_ape,cast(a.fecha_ini as varchar) as fecha_ini,cast(a.fecha_fin as varchar) as fecha_fin,u.email,u.telefono_trabajo, ";
			queryString+="(case when u.apellido_restante is null then '' else u.apellido_restante end) as apellidoRestante, ";
			queryString+="(case when u.nombre_restante is null then '' else u.nombre_restante end) as nombreRestante ";
			queryString+="from registro.director_laboratorio a,registro.laboratorio b,seguridad.cargo_usuario_dei c,seguridad.usuario u, ";
			queryString+="seguridad.usuario_dei d ";
			queryString+="where a.id_laboratorio = b.id_laboratorio and a.id_cargo_usr = c.id_cargo_usr and c.id_usuario=u.id_usuario ";
			queryString+="order by b.nombre_laboratorio,u.primer_ape ";
		}
			
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgDirectoresLabo.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pVacio",banderaVacio);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);	
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}
}
