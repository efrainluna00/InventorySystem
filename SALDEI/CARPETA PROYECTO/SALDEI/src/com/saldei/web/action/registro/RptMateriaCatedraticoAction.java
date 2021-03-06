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
import com.saldei.web.services.registro.RptMateriaCatedraticoService;
import com.saldei.web.form.registro.RptMateriaCatedratico;

/**
 * @author Carlos
 *
 */
public class RptMateriaCatedraticoAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptMateriaCatedraticoService service = new RptMateriaCatedraticoService();
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{						
			this.cleanSession(mapping, request); 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr == null)
				return mapping.findForward("login");
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().removeAttribute("cic");		
		request.getSession().removeAttribute("cat");		
	}
	public ActionForward rptMateriaCatedratico(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptMateriaCatedratico rptMateriaCatedratico = (RptMateriaCatedratico)form;		
		
		String ciclo="",materia="",catedratico="",formato="",banderaVacio="";;
		ciclo=rptMateriaCatedratico.getCiclo();
		if(ciclo.equals("Seleccione"))
			ciclo = service.cicloActivo();
		materia=rptMateriaCatedratico.getMateria();
		catedratico=rptMateriaCatedratico.getCatedratico();
		formato = rptMateriaCatedratico.getFormato();
		GenerarReportesAction gRpt = new GenerarReportesAction();		
		
		
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		String queryString="";
		int hayDatos=service.hayDatos(rptMateriaCatedratico);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '' as anyo_ciclo,0 as num_ciclo,'' as id_usuario,'' as id_ciclo,0 as id_materia,'' as id_seccion,'' as cod_materia,'' as nom_materia,'' as desc_materia,'' as uni_valorativas,'' as primer_nom,'' as primer_ape,'' as aperestante,'' as nombrerestante ";		 
		}
		else{
			banderaVacio="";
			queryString =service.query(rptMateriaCatedratico);
		}
		
		
		System.out.println(queryString);
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgMateriaXCatedratico.jasper");
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(rutaImagen);		
		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);
		parameters.put("pMateria",materia );
		parameters.put("pCiclo",ciclo );
		parameters.put("pCatedratico",catedratico);
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
	public ActionForward getCatedraticoCiclo(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		RptMateriaCatedratico rptMateriaCatedratico = (RptMateriaCatedratico)form;
		try{
			if(rptMateriaCatedratico.getCiclo().equals("Seleccione"))
				request.getSession().removeAttribute("cat");
			else{
				List list = service.getCatedraticosCiclo(rptMateriaCatedratico);	
				request.getSession().setAttribute("cat", list);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}

}
