package com.saldei.web.action.seguridad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Querys;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.seguridad.RptAuditoriaForm;
import com.saldei.web.services.seguridad.RptAuditoriaService;
import com.saldei.util.commons.Util;

public class RptAuditoriaAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	Util util = new Util();
	RptAuditoriaService service = new RptAuditoriaService();
	
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {						
			this.cleanSession(mapping, request); 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr == null){
				return mapping.findForward("login");
			}
			else{
				List list1=null;
				List list2=null;
				list1 = service.getUsuarioAuditoria();
				list2= service.getTablaAuditoria();
				request.getSession().setAttribute("usuar", list1);			
				request.getSession().setAttribute("table", list2);
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
	
	public ActionForward rptAuditoria(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptAuditoriaForm rptAuditoria = (RptAuditoriaForm)form;
		RptAuditoriaService service = new RptAuditoriaService();
		
		
		String formato="";	

		formato = rptAuditoria.getFormato();
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
		int hayDatos=service.hayDatos(rptAuditoria);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select 0 as id_auditoria,'' as id_usuario,'' as accion,'' as fecha_accion ,'' as nom_tabla,'' as llave_primaria,'' as primer_nom,'' as primer_ape ";			
		}
		else{
			banderaVacio="";
			queryString =service.query(rptAuditoria);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgAuditoria.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pVacio",banderaVacio);
		parameters.put("pFechaIni", rptAuditoria.getFechainicial());
		parameters.put("pFechaFin", rptAuditoria.getFechafinal());
		parameters.put("pUsuarioAud", rptAuditoria.getUsuario());
		parameters.put("pTabla", rptAuditoria.getTabla());
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
	}	
}
