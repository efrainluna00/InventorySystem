/**
 * 
 */
package com.saldei.web.action.registro;

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
import com.saldei.util.commons.Querys;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.services.registro.RptMateriaInscritaHistoricoService;
import com.saldei.web.form.registro.RptMateriaInscritaHistoricoForm;

/**
 * @author Carlos
 *
 */
public class RptMateriaInscritaHistoricoAction extends DispatchAction {
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
	public ActionForward rptMateriaInscritaEstudiante(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptMateriaInscritaHistoricoForm rptMateriaInscritaHistorico = (RptMateriaInscritaHistoricoForm)form;
		RptMateriaInscritaHistoricoService service = new RptMateriaInscritaHistoricoService();
		
		
		String cicloInicio="",cicloFin="",materia="",carnet="",formato="",banderaVacio="";
		cicloInicio=rptMateriaInscritaHistorico.getCicloini();
		cicloFin=rptMateriaInscritaHistorico.getCiclofin();		
		formato = rptMateriaInscritaHistorico.getFormato();
		carnet=rptMateriaInscritaHistorico.getCarnet();
		GenerarReportesAction gRpt = new GenerarReportesAction();		
		
		
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		//queryString =service.query(rptMateriaCatedraticoHistorico);
		//System.out.println(queryString);
		int hayDatos=service.hayDatos(rptMateriaInscritaHistorico);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select 0 as num_ciclo,'' as anyo_ciclo,'' as carnet_estudiante,'' as id_ciclo,0 as idmateria,'' as seccion,'' as codmateria,'' as nombre,'' as descripcion,'' as uni_valorativas,'' as primer_nom, '' as primer_ape, ''  as apellidorestante, ''  as nombrerestante,'' as oyente,'' as ciclo_ingreso,'' as anyo_ingreso ";
		}		
		else{
			banderaVacio="";
			queryString =service.query(rptMateriaInscritaHistorico);
		}
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgMatEstHistorico.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pMateria",materia );
		parameters.put("pCicloIni",cicloInicio );
		parameters.put("pCicloFin",cicloFin );		
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
