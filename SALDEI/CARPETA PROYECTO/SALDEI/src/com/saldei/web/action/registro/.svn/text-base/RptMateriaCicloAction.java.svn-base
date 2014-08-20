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
import com.saldei.web.form.registro.RptMateriaCicloForm;
import com.saldei.web.services.registro.RptMateriaCicloService;

/**
 * @author Carlos
 *
 */
public class RptMateriaCicloAction extends DispatchAction {
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
	}
	public ActionForward rptMateriaCiclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptMateriaCicloForm rptMateriaCiclo = (RptMateriaCicloForm)form;
		RptMateriaCicloService service = new RptMateriaCicloService();
		
		
		String ciclo="",materia="",seccion="",formato="";
		ciclo=rptMateriaCiclo.getCiclo();
		if(ciclo.equals("Seleccione"))
			ciclo = service.cicloActivo();

		formato = rptMateriaCiclo.getFormato();
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
		int hayDatos=service.hayDatos(rptMateriaCiclo);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select 0 as idmateria,'' as seccion,'' as codmateria,'' as nombre,'' as descripcion,'' as uni_valorativas,'' as alumnos";			
		}
		else{
			banderaVacio="";
			queryString =service.query(rptMateriaCiclo);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgMateriaXCiclo.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pCiclo",ciclo.substring(0,2)+" - "+ciclo.substring(2,6) );
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
