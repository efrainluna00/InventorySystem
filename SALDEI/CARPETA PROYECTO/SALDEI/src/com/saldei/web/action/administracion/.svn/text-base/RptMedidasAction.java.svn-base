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
import com.saldei.web.form.administracion.RptMedidasForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.util.commons.Querys;
import com.saldei.web.services.administracion.RptCommonServices;

public class RptMedidasAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptCommonServices commonservices=new RptCommonServices();
	
	/**
	 * Inicializa los campos de la pantalla de Reporte de  Medidas
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
	 * Genera Reporte de  Medidas
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward rptMedida(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		int filasActivos=-1,filasInactivos=-1;		
		RptMedidasForm rptMedida = (RptMedidasForm)form;
		String estadoMedida = rptMedida.getEstado();
		String formato = rptMedida.getFormato();
		String banderaVacia="";
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		filasActivos=commonservices.hayDatos("registro.medida", "est_medida", "A");
   	    filasInactivos=commonservices.hayDatos("registro.medida","est_medida","I");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		 ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_adMedida.jasper");
		if(estadoMedida.equals("est_medida")){
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				queryString=utilQuerys.medidaActivaVacio()+" union " + utilQuerys.medidaInactivaVacio();
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=Querys.medidaSelect+ " union " + utilQuerys.medidaInactivaVacio()+ " order by est_medida,id_tipo_medida,nom_tipo_medida,nom_medida";
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.medidaSelect+ " union " + utilQuerys.medidaActivaVacio()+ " order by est_medida,id_tipo_medida,nom_tipo_medida,nom_medida";
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.medidaSelect + " order by est_medida,id_tipo_medida,nom_tipo_medida,nom_medida "; 
			}			
		}			
		else
			if(commonservices.hayDatos("registro.medida", "est_medida", estadoMedida)!=0)
				queryString=utilQuerys.medidaParametros(estadoMedida);
			else{
				banderaVacia="0";
				if(estadoMedida.equals("A"))
					queryString=utilQuerys.medidaActivaVacio();
				else if(estadoMedida.equals("I"))
						queryString=utilQuerys.medidaInactivaVacio();
				
			}	
		System.out.println(queryString);
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoMedida);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}

}
