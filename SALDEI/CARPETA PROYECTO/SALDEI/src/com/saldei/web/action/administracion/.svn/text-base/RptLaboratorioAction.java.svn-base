package com.saldei.web.action.administracion;

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
import com.saldei.web.form.administracion.RptLaboratorioForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.services.administracion.RptCommonServices;
import com.saldei.util.commons.Querys;


public class RptLaboratorioAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptCommonServices commonservices=new RptCommonServices();
	
	/**
	 * Inicializa los campos de la pantalla de Reporte de  Laboratorio
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
	 *Genera de Reporte de  Laboratorio
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward rptLaboratorio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		GenerarReportesAction gRpt = new GenerarReportesAction();
		int filasActivos=-1,filasInactivos=-1;
		RptLaboratorioForm rptLaboratorio = (RptLaboratorioForm)form;
		String estadoLaboratorio = rptLaboratorio.getEstado();
		String formato = rptLaboratorio.getFormato();
		String banderaVacia="";
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_adLaboratorio.jasper");
		filasActivos=commonservices.hayDatos("registro.laboratorio", "est_laboratorio", "A");
		filasInactivos=commonservices.hayDatos("registro.laboratorio","est_laboratorio","I");
		 if(estadoLaboratorio.equals("est_laboratorio")){					
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.laboratorioActivoVacio()+" union " + utilQuerys.laboratorioInactivoVacio()+" order by est_laboratorio,nombre_laboratorio";
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.laboratorioInactivoVacio()+" union "+ Querys.laboratorioSelect;
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=utilQuerys.laboratorioActivoVacio()+" union "+ Querys.laboratorioSelect;
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.laboratorioSelect; 
			}
		}			
		else{			
			if(commonservices.hayDatos("registro.laboratorio", "est_laboratorio", estadoLaboratorio)!=0)
				queryString=utilQuerys.laboratorioParametros(estadoLaboratorio);
			else{
				banderaVacia="0";
				if(estadoLaboratorio.equals("A"))
					queryString=utilQuerys.laboratorioActivoVacio();
				else if(estadoLaboratorio.equals("I"))
						queryString=utilQuerys.laboratorioInactivoVacio();
				
			}
		}
			
			
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
//		System.out.println(rutaImagen);		
//		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoLaboratorio);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
