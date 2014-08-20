/**
 * 
 */
package com.saldei.web.action.registro;

import org.apache.struts.actions.DispatchAction;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
import com.saldei.web.services.registro.RptRequerimientoXMateriaService;
import com.saldei.web.form.registro.RptRequerimientoXMateriaForm;

/**
 * @author Carlos
 *
 */
public class RptRequerimientoXMateriaAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptRequerimientoXMateriaService service = new RptRequerimientoXMateriaService();
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
	public ActionForward rptRequerimientoMateria(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptRequerimientoXMateriaForm rptRequerimiento = (RptRequerimientoXMateriaForm)form;
		
		
		
		String ciclo="",materia="",banderaVacio="",formato="";
		ciclo=rptRequerimiento.getCiclo();
		if(ciclo.equals("Seleccione"))
			ciclo = service.cicloActivo();
		if(!rptRequerimiento.getMateria().equals("Seleccione")){
			materia=rptRequerimiento.getMateria().substring(0,rptRequerimiento.getMateria().indexOf("-"));				
		}
		else
			materia =rptRequerimiento.getMateria();
		
		formato = rptRequerimiento.getFormato();
		GenerarReportesAction gRpt = new GenerarReportesAction();		
		
		
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		int hayDatos=service.hayDatos(rptRequerimiento);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select 0 as num_ciclo,'' as anyo_ciclo,'' as id_ciclo,0 as id_materia,'' as seccion,'' as fecha_sol,'' as fecha_resol,'' as id_tipo,'' as requerimiento,'' as est_req_mat,'' as cod_materia,'' as nom_materia,'' as desc_materia,'' as id_usuario,'' as primer_nom,'' as primer_ape,'' as nombre_restante,'' as apellido_restante,'' as codigo";			
		}
		else{
			banderaVacio="";
			queryString =service.query(rptRequerimiento);
		}
		
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgRequerimientoXMateria.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pMateria",materia.equals("Seleccione")?materia:service.recuperarMateria(materia));
		parameters.put("pCiclo",ciclo );
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
	public ActionForward getMateriasCiclo(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		RptRequerimientoXMateriaForm rptRequerimiento = (RptRequerimientoXMateriaForm)form;
		try{
			if(rptRequerimiento.getCiclo().equals("Seleccione"))
				request.getSession().removeAttribute("mat");
			else{
				List list = service.getMateriaCiclo(rptRequerimiento); 
				request.getSession().setAttribute("mat", list);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}

}
