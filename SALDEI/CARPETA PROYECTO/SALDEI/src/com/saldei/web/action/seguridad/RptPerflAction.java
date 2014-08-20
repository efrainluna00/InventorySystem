/**
 * 
 */
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
import com.saldei.web.form.seguridad.RptPerfilForm;
import com.saldei.web.services.administracion.RptCommonServices;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.util.commons.Querys;

/**
 * @author Carlos
 *
 */
public class RptPerflAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	
	RptCommonServices commonservices=new RptCommonServices();
	
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
	
	public ActionForward rptPerfiles(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		
		RptPerfilForm rptPerfil = (RptPerfilForm)form;
		String estadoPerfil = rptPerfil.getEstado();
		String banderaVacia="";
		int filasActivos=-1,filasInactivos=-1;			
		String formato = rptPerfil.getFormato();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgPerfil.jasper");
		filasActivos=commonservices.hayDatos("seguridad.perfil", "est_perfil", "A");
		filasInactivos=commonservices.hayDatos("seguridad.perfil","est_perfil","I");
		if(estadoPerfil.equals("est_perfil")){
			queryString=Querys.perfilSelect;
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.perfilActivoVacio()+" union " + utilQuerys.perfilInactivoVacio()+" order by est_perfil,nom_perfil";
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.perfilInactivoVacio()+" union "+ Querys.perfilSelect;
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=utilQuerys.perfilActivoVacio()+" union "+ Querys.perfilSelect;
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.perfilSelect;
			}
		}			
		else
		{
			if(commonservices.hayDatos("seguridad.perfil", "est_perfil", estadoPerfil)!=0)
				queryString=utilQuerys.perfilParametros(estadoPerfil);
			else{
				banderaVacia="0";
				if(estadoPerfil.equals("A"))
					queryString=utilQuerys.perfilActivoVacio();
				else if(estadoPerfil.equals("I"))
						queryString=utilQuerys.perfilInactivoVacio();
				
			}
		}
			
		
		System.out.println(queryString);
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoPerfil);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}	
}
