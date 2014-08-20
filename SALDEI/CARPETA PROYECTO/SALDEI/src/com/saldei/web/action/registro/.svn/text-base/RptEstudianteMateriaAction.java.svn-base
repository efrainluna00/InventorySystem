/**
 * 
 */
package com.saldei.web.action.registro;

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
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Querys;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.form.registro.RptEstudianteMateriaForm;
import com.saldei.web.services.registro.RptEstudianteMateriaService;


/**
 * @author Carlos
 *
 */
public class RptEstudianteMateriaAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys= new Querys();
	RptEstudianteMateriaService service = new RptEstudianteMateriaService();
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
	public ActionForward rptEstudianteMateria(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		RptEstudianteMateriaForm rptEstudianteMateriaForm = (RptEstudianteMateriaForm)form;		
		String ciclo="",materia="",seccion="",formato="";
		System.out.println(rptEstudianteMateriaForm.getMateria());
		ciclo=rptEstudianteMateriaForm.getCiclo();
		if(ciclo.equals("Seleccione"))
			ciclo = service.cicloActivo();
		if(!rptEstudianteMateriaForm.getMateria().equals("Seleccione")){
			materia=rptEstudianteMateriaForm.getMateria().substring(0,rptEstudianteMateriaForm.getMateria().indexOf("-"));		
			seccion=rptEstudianteMateriaForm.getMateria().substring(rptEstudianteMateriaForm.getMateria().indexOf("-")+1,rptEstudianteMateriaForm.getMateria().length());;	
		}
		else
			materia =rptEstudianteMateriaForm.getMateria();
		
		formato = rptEstudianteMateriaForm.getFormato();
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
		int hayDatos=service.hayDatos(rptEstudianteMateriaForm);
		if(hayDatos==0){
			banderaVacio="0";
			queryString="select '' as carnet,'' as ciclo,0 as idmateria,'' as codigomateria,'' as nombremateria,'' as seccion,'' as primernom,'' as nombreresto,'' as primerape,'' as aperest,'' as iduser,'' as emailestudiante,'' as NombreCatedratico";
		}
		else{
			banderaVacio="";
			queryString =service.query(rptEstudianteMateriaForm);
		}
		
		System.out.println(queryString);
		
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_rgEstudiantesXMateria.jasper");
//		ruta="/SALDEI/WebContent/reportesJasper/"+"rpt_rgEstudiantesXMateria.jasper";
		
		
		
		String rutaImagen = this.getServlet().getServletContext().getRealPath("images/uca/Uca3Color.JPG");
		System.out.println(ruta);
		System.out.println(rutaImagen);		
//		System.out.println(request.getContextPath());
//		System.out.println(request.getContextPath());
		HashMap<String, String> parameters = new HashMap<String, String>();	
		
		parameters.put("pUrlImagen",(String)rutaImagen);
		parameters.put("pUsuario",usuario);
		parameters.put("pQuery",queryString);	
		parameters.put("pMateria",materia.equals("Seleccione")?materia:service.recuperarMateria(materia));
		parameters.put("pCiclo",ciclo);
		parameters.put("pSeccion",seccion.length()==1?"0"+seccion:seccion);
		parameters.put("pVacio",banderaVacio);
		
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);		
		
	}
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
//		request.removeAttribute("cic");
//		request.removeAttribute("mat");
//		request.removeAttribute("sec");		
		request.setAttribute("cic", null);

	}
	public ActionForward getMateriasCiclo(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		RptEstudianteMateriaForm rptEstudianteMateriaForm = (RptEstudianteMateriaForm)form;
		try{
			if(rptEstudianteMateriaForm.getCiclo().equals("Seleccione"))
				request.getSession().removeAttribute("mat");
			else{
				List list = service.getMateriaCiclo(rptEstudianteMateriaForm); 
				request.getSession().setAttribute("mat", list);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}
	public ActionForward getSeccionXMateria(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		RptEstudianteMateriaForm rptEstudianteMateriaForm = (RptEstudianteMateriaForm)form;
		try{
			if(rptEstudianteMateriaForm.getMateria().equals("Seleccione"))
				request.getSession().removeAttribute("sec");
			else{
				List list = service.getSeccionXMateria(rptEstudianteMateriaForm); 
				request.getSession().setAttribute("sec", list);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}
	
}
