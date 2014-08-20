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
import com.saldei.web.form.administracion.RptTipoMulticodeForm;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.util.commons.Querys;
import com.saldei.web.services.administracion.RptCommonServices;

public class RptTipoMulticodeAction extends DispatchAction {

	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptCommonServices commonservices=new RptCommonServices();
	
	/**
	 * Inicializa los campos de la pantalla de Reporte de  Tipo Multicode
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
	 * Genera Reporte de  Tipo de Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward rptTipoMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		
		GenerarReportesAction gRpt = new GenerarReportesAction();
		
		RptTipoMulticodeForm rptTipoMulticode = (RptTipoMulticodeForm)form;
		String estadoTipoMulticode = rptTipoMulticode.getEstado();
		String formato = rptTipoMulticode.getFormato();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		int filasActivos=-1,filasInactivos=-1;
		filasActivos=commonservices.hayDatos("registro.tipo_multicode", "est_tipo_multi", "A");
   	    filasInactivos=commonservices.hayDatos("registro.tipo_multicode","est_tipo_multi","I");
   	    String banderaVacia="",estadoActivo="",estadoInactivo="";		
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		 ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_adTipoMulticode.jasper");
		if(estadoTipoMulticode.equals("est_tipoMulticode")){
			if(filasActivos==0 && filasInactivos==0){
				banderaVacia="0";
				
				queryString=utilQuerys.tipoMulticodeActivoVacio()+" union " + utilQuerys.tipoMulticodeInactivoVacio();
			}
			if(filasActivos!=0 && filasInactivos==0){
				banderaVacia="0";
				queryString=Querys.tipoMulticodeSelect+ " union " + utilQuerys.tipoMulticodeInactivoVacio()+ " order by est_tipo_multi,nom_tipo_multicode";
			}
			if(filasActivos==0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.tipoMulticodeSelect+ " union " + utilQuerys.tipoMulticodeActivoVacio()+ " order by est_tipo_multi,nom_tipo_multicode";
			}
			if(filasActivos!=0 && filasInactivos!=0){
				banderaVacia="0";
				queryString=Querys.tipoMulticodeSelect + " order by est_tipo_multi,nom_tipo_multicode "; 
			}			
		}
			
		else{
			if(commonservices.hayDatos("registro.tipo_multicode", "est_tipo_multi", estadoTipoMulticode)!=0)
				queryString=utilQuerys.tipoMulticodeParametros(estadoTipoMulticode);
			else{
				banderaVacia="0";
				if(estadoTipoMulticode.equals("A"))
					queryString=utilQuerys.tipoMulticodeActivoVacio();
				else if(estadoTipoMulticode.equals("I"))
						queryString=utilQuerys.tipoMulticodeInactivoVacio();
				
			}		
		}
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();		
		parameters.put("pEstado", (String)estadoTipoMulticode);
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pVacio",banderaVacia);
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
}
