/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.inventario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Querys;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.form.inventario.RepMovRecursoForm;
import com.saldei.web.services.administracion.RptCommonServices;

/** 
 * MyEclipse Struts
 * Creation date: 06-26-2009
 * 
 * XDoclet definition:
 * @struts.action path="/repAsigActualesAction" name="repAsigActualesForm" input="/html/activos/repAsigActuales.jsp" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="success" path="/html/activos/repAsigActuales.jsp"
 */
public class RepMovRecursoAction extends DispatchAction {
		JdbcHelper jdbcCon = new JdbcHelper();
		Querys utilQuerys= new Querys();
		RptCommonServices commonservices=new RptCommonServices();
		
		/**
		 * Inicializa los campos de la pantalla de Reporte de  Laboratorio
		 * @param mapping   ActionMapping
		 * @param form      ActionForm
		 * @param request  HttpServletRequest
		 * @param response HttpServletResponse
		 * @return 
		 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RepMovRecursoForm movRecursoForm = (RepMovRecursoForm)form;
		movRecursoForm.setCodRecurso("%");
		movRecursoForm.setRecursoDesc("Todos los Recursos");
		try {		 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr == null)
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
			//return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward rptMovRecurso(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException{
		ActionErrors errors = new ActionErrors();
		GenerarReportesAction gRpt = new GenerarReportesAction();
		RepMovRecursoForm movRecursoForm = (RepMovRecursoForm)form;
		
		movRecursoForm.validate(mapping, request, errors);
		
		if(movRecursoForm.getFlagTarget()!= null  &&  movRecursoForm.getFlagTarget().equals("")){		
			
			if(errors.isEmpty()){
				movRecursoForm.setFlagTarget("false");
				return mapping.findForward("updateFail");
			}
		}
		
		
		
		if (errors.isEmpty()) {

		String formato = movRecursoForm.getFormato();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String usuario="";
		String codUsu = user.getIdUsuario();
		
		if(user==null)
			usuario="Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		ruta=this.getServlet().getServletContext().getRealPath("reportesJasper/kardex.jasper");
		HashMap<String, String> parameters = new HashMap<String, String>();
		//parameters.put("pQuery",queryString);
		//parameters.put("pUsuario",usuario);
		parameters.put("p_fecha_ini",movRecursoForm.getFecha_ini());
		parameters.put("p_fecha_fin",movRecursoForm.getFecha_fin());
		parameters.put("p_usuario",user.getIdUsuario());
		 if (movRecursoForm.getCodRecurso() != null && !movRecursoForm.getCodRecurso().trim().equals("")&& !movRecursoForm.getCodRecurso().equals("%")){
			 parameters.put("p_recurso",movRecursoForm.getCodRecurso());	 
		 }
	         	        
		
		parameters.put("SUBREPORT_DIR",this.getServlet().getServletContext().getRealPath("reportesJasper")+"/");
		return gRpt.rptUsuario(mapping, form, request, response, formato, ruta, parameters);
		}		
		if(!errors.isEmpty()){
			this.saveErrors(request, errors);
		}
		return mapping.findForward("updateFail");
		
	}

	
}