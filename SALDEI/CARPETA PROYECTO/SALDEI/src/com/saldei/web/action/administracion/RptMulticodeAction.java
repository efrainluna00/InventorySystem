package com.saldei.web.action.administracion;

import org.apache.struts.actions.DispatchAction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.administracion.RptMulticodeForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.util.commons.Querys;
import com.saldei.web.services.administracion.RptCommonServices;

public class RptMulticodeAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptCommonServices commonservices=new RptCommonServices();
	
	/**
	 * Inicializa los campos de la pantalla de Reporte de  Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {		 
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
	
	/**
	 * Genera Reporte de  Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward rptMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		
		RptMulticodeForm rptMulticode = (RptMulticodeForm)form;
		String estadoMulticode = rptMulticode.getEstado();
		String formato = rptMulticode.getFormato();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		int filasActivos=-1,filasInactivos=-1;
		filasActivos=commonservices.hayDatos("registro.multicode", "est_multicode", "A");
   	    filasInactivos=commonservices.hayDatos("registro.multicode","est_multicode","I");
   	    String banderaVacia="",estadoActivo="",estadoInactivo="";		
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		 ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_adMulticode.jasper");
		if(estadoMulticode.equals("est_multicode"))
		{
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				
				queryString=utilQuerys.multicodeActivoVacio()+" union " + utilQuerys.multicodeInactivoVacio();
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=Querys.multicodeSelect+ " union " + utilQuerys.multicodeInactivoVacio()+ " order by est_multicode,id_tipo_multicode,nom_tipo_multicode,codigo";
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.multicodeSelect+ " union " + utilQuerys.multicodeActivoVacio()+ " order by est_multicode,id_tipo_multicode,nom_tipo_multicode,codigo";
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.multicodeSelect + " order by est_multicode,id_tipo_multicode,nom_tipo_multicode,codigo "; 
			}			
		}
		else{
			if(commonservices.hayDatos("registro.multicode", "est_multicode", estadoMulticode)!=0)
				queryString=utilQuerys.multicodeParametros(estadoMulticode);
			else{
				banderaVacia="0";
				if(estadoMulticode.equals("A"))
					queryString=utilQuerys.multicodeActivoVacio();
				else if(estadoMulticode.equals("I"))
						queryString=utilQuerys.multicodeInactivoVacio();
				
			}		
		}
						
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoMulticode);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
}
