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
import com.saldei.web.form.seguridad.RptOrgDeiForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;

public class RptOrgDeiAction extends DispatchAction{
	
	JdbcHelper jdbcCon = new JdbcHelper();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		return mapping.findForward("success");
	}
	
	public ActionForward rptUsuario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		
		RptOrgDeiForm rptUsuario = (RptOrgDeiForm)form;
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String formato = rptUsuario.getFormato();
		String agrupacion = rptUsuario.getGrupo();
		
		String ruta="";
		if (agrupacion.equals("1"))	
			ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgOrganizacionDEI.jasper");
		if (agrupacion.equals("2"))
			ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgOrganizacionDEI2.jasper");
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
