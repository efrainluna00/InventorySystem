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
import com.saldei.web.form.administracion.RptMateriasForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.util.commons.Querys;
import com.saldei.web.services.administracion.RptCommonServices;

public class RptMateriasAction extends DispatchAction{
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptCommonServices commonservices=new RptCommonServices();
	
	/**
	 * Inicializa los campos de la pantalla de Reporte de  Materias
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
	 * Genera Reporte de  Materia
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward rptMateria(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		int filasActivos=-1,filasInactivos=-1;		
		RptMateriasForm rptMateria = (RptMateriasForm)form;
		String estadoMateria = rptMateria.getEstado();
		String formato = rptMateria.getFormato();
		String banderaVacia="";
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		filasActivos=commonservices.hayDatos("registro.materia", "est_materia", "A");
   	    filasInactivos=commonservices.hayDatos("registro.materia","est_materia","I");
		 ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_adMateria.jasper");
		if(estadoMateria.equals("est_materia")){
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.materiaActivaVacio()+" union " + utilQuerys.materiaInactivaVacio()+" order by est_laboratorio,nombre_laboratorio";
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.materiaInactivaVacio()+" union "+ Querys.materiaSelect;
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=utilQuerys.materiaActivaVacio()+" union "+ Querys.materiaSelect;
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.materiaSelect; 
			}
		}			
		else{
			if(commonservices.hayDatos("registro.materia", "est_materia", estadoMateria)!=0)
				queryString=utilQuerys.materiaParametros(estadoMateria);
			else{
				banderaVacia="0";
				if(estadoMateria.equals("A"))
					queryString=utilQuerys.materiaActivaVacio();
				else if(estadoMateria.equals("I"))
						queryString=utilQuerys.materiaInactivaVacio();
				
			}
		}
						
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoMateria);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
