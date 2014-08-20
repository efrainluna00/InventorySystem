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
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.form.seguridad.RptUsuariosForm;
import com.saldei.web.services.seguridad.RptUsuarios;

public class RptUsuariosAction extends DispatchAction{
	
	JdbcHelper jdbcCon = new JdbcHelper();
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		return mapping.findForward("success");
	}
	public ActionForward rptUsuario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		RptUsuarios service = new RptUsuarios();
		RptUsuariosForm rptUsuario= (RptUsuariosForm)form;
		GenerarReportesAction gRpt = new GenerarReportesAction();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="",banderaVacio="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String formato = rptUsuario.getFormato();
		String queryString="";
		int hayDatos=service.hayDatos(rptUsuario);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select  '' as idusuario,'' as idTipoUsuario,'' as primernom,'' as primerape,'' as nombrerestante,'' as apellidorestante,'' as email,'M' as sexo,'' as fecha_ini,'' as fecha_fin,'' as est_usuario";																											
		}
		else{
			banderaVacio="";
			queryString =service.query(rptUsuario);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgUsuariosActivosTodos.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pVacio",banderaVacio);
		parameters.put("pFechaInicial", rptUsuario.getFechainicial());
		parameters.put("pFechaFinal", rptUsuario.getFechafinal());
		parameters.put("pTipoUsuario", rptUsuario.getTipousuario());
		
		
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
			
		
	}

}
