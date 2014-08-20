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
import com.saldei.web.form.registro.RptMateriaCicloHistoricoForm;
import com.saldei.web.services.registro.RptMateriaCicloHistoricoService;
 

/**
 * @author Carlos
 *
 */
public class RptMateriaCicloHistoricoAction extends DispatchAction {
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
	public ActionForward rptMateriaCicloHistorico(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptMateriaCicloHistoricoForm rptMateriaCicloHistorico = (RptMateriaCicloHistoricoForm)form;
		RptMateriaCicloHistoricoService service = new RptMateriaCicloHistoricoService();		
		
		String cicloInicial="",formato="",cicloFinal="";		
		cicloInicial=rptMateriaCicloHistorico.getCicloini();
		cicloFinal=rptMateriaCicloHistorico.getCiclofin();

		formato = rptMateriaCicloHistorico.getFormato();
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
		int hayDatos=service.hayDatos(rptMateriaCicloHistorico);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '' as id_ciclo,0 as num_ciclo,'' as anyo_ciclo,0 as idmateria,'' as seccion,'' as codmateria,'' as nombre,'' as  descripcion,'' as uni_valorativas ";
		}
		else{
			banderaVacio="";
			queryString =service.query(rptMateriaCicloHistorico);
		}
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgMateriaXCicloHistorico.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);		
		parameters.put("pCicloIni",cicloInicial);
		parameters.put("pCicloFin",cicloFinal);
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
