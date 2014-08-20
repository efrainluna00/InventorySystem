/**
 * 
 */
package com.saldei.web.action.registro;

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
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.form.registro.RptSolicitudUsuarioForm;
import com.saldei.web.services.registro.RptSolicitudUsuarioService;
/**
 * @author Carlos
 *
 */
public class RptSolicitudUsuarioAction extends DispatchAction
{
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {						
			this.cleanSession(mapping, request); 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr == null)
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.removeAttribute("cic");
		request.removeAttribute("mat");	
	}
	public ActionForward rptSolicitud(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptSolicitudUsuarioForm rptSolicitud = (RptSolicitudUsuarioForm)form;
		RptSolicitudUsuarioService service = new RptSolicitudUsuarioService();
		
		
		String fechaInicial="",fechaFinal="",formato="";
		fechaInicial= rptSolicitud.getFechainicial();
		fechaFinal=rptSolicitud.getFechafinal();
		formato = rptSolicitud.getFormato();
		
		
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
		int hayDatos=service.hayDatos(rptSolicitud);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '' as idusuario,'' as idTipoUsuario,'' as primernom,'' as primerape,'' as nombrerestante,'' as apellidorestante,'' as email,'' as fechasoli";
		}
		else{
			banderaVacio="";
			queryString =service.query(rptSolicitud);
		}
		
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgSolicitudUsuario.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pFechaIni",fechaInicial);
		parameters.put("pFechaFin",fechaFinal);		
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
}
