/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.inventario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.exception.ConstraintViolationException;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.inventario.InvRecurso;
import com.saldei.hibernate.tables.inventario.InvRecursoDAO;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.action.BaseAction;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.DetalleSolDescActForm;
import com.saldei.web.form.inventario.DetalleSolAbaForm;
import com.saldei.web.form.inventario.MinimoxBodegaForm;
import com.saldei.web.form.inventario.RecursoForm;
import com.saldei.web.services.administracion.ParametroServices;
import com.saldei.web.services.seguridad.UsuarioServices;

/** 
 * MyEclipse Struts
 * Creation date: 07-07-2009
 * 
 * XDoclet definition:
 * @struts.action path="/detalleSolAbaAction" name="detalleSolAbaForm" input="/html/inventario/detalleSolAba.jsp" parameter="accion" scope="request"
 * @struts.action-forward name="updateFail" path="/html/inventario/detalleSolAba.jsp"
 * @struts.action-forward name="back" path="/solicitudAbastecimientoAction.do?accion=Find"
 * @struts.action-forward name="success" path="/html/inventario/detalleSolAba.jsp?accion="
 * @struts.action-forward name="cancel" path="/html/inventario/detalleSolAbaAction.do?accion=Find"
 */
public class DetalleSolAbaAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	protected Map getKeyMethodMap() {
        HashMap map = (HashMap) super.getKeyMethodMap();

        
		/*<html:submit  property="accion" styleId="enviarSolicitud">
		<bean:message key="opc.enviarSolicitud" />*/
        
        map.put("opc.enviarSolicitud", "enviarSolicitud");
               
        return map;
  }
	
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@SuppressWarnings("deprecation")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
	    
		DetalleSolAbaForm detalleSolAbaForm = (DetalleSolAbaForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();				    
		
		try {
			detalleSolAbaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				actDetSolicitudDAO.delete(detalleSolAbaForm.getActDetSolicitud());			    
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("detsolaba.mensaje.exito", new ActionError("detsolaba.mensaje.exito.delete"));
			}else{
				target = "updateFail";
				setFLAG_UPDATE(true);
			}
		} 
		 catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add("detsolaba.mensajeError.error", new ActionError("detsolaba.mensajeError.error.nodelete"));
				HibernateSessionFactory.getSession().beginTransaction().rollback();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
		}finally{
			HibernateSessionFactory.getSession().close();
		}
		
	    find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	@SuppressWarnings("deprecation")
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub

		DetalleSolAbaForm detalleSolAbaForm = (DetalleSolAbaForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();				    
		
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
				    
	    
		try {
			detalleSolAbaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				actDetSolicitudDAO.save(detalleSolAbaForm.getActDetSolicitud());
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("detsolaba.mensaje.exito", new ActionError("detsolaba.mensaje.exito.insert"));
			}else{
				setFLAG_UPDATE(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		}
		
	    find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		DetalleSolAbaForm detalleSolAbaForm = (DetalleSolAbaForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();	
		
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;					   			
		
		try {
			detalleSolAbaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				actDetSolicitudDAO.merge(detalleSolAbaForm.getActDetSolicitud());				  
			    HibernateSessionFactory.getSession().beginTransaction().commit();				
				errors.add("detsolaba.mensaje.exito", new ActionError("detsolaba.mensaje.exito.update"));
			}else{
				target = "updateFail";
				setFLAG_UPDATE(true);
			}
		} 
	
		catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();				
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		}
		
	    find(mapping, form, request, response);
		return mapping.findForward(target);
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {	
		String target = KEY_SUCCESS;
		String filtro;

		HttpSession session = request.getSession(true);
		
		List data;
		
		DetalleSolAbaForm detalleSolAbaForm = (DetalleSolAbaForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		
		//detalleSolDescActForm.setDescripcion(descripcion)

		
		try {
			//actSolicitud = actSolicitudDAO.findById(detalleSolDescActForm.getActSolicitud().getId());
			
			/* para guardar la configuracion del grid papa*/
			request.setAttribute("numeroPagina", request.getParameter("numeroPagina"));
			request.setAttribute("nombreGrid", request.getParameter("nombreGrid"));
			request.setAttribute("cantidadPagina", request.getParameter("cantidadPagina"));
			
			data = actDetSolicitudDAO.AbastecimientoDetFind(detalleSolAbaForm.getCodSolicitud().toString(), detalleSolAbaForm.getCodBodega());
			request.setAttribute("listaRecursosxSolicitud", data);
		 			  
	
		    
			if(!isFLAG_UPDATE()){
				detalleSolAbaForm.reset(mapping, request);
			}else{
				setFLAG_UPDATE(false);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();			
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		} 	
		return mapping.findForward(target);
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward enviarSolicitud(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response
		) throws Exception,
		ServletException {
	
		ActionErrors errors = new ActionErrors(); 
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
		
		//form.reset(mapping,request);		
		// TODO Auto-generated method stub
	    DetalleSolAbaForm detalleSolAbaForm = (DetalleSolAbaForm) form;
		
		
		ActSolicitud actSolicitud = new ActSolicitud();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		//ActSolicitudId actSolicitudId = new ActSolicitudId();
		//actSolicitudId.setCodSolicitud(solDescActForm.getCodSolicitud());
		//actSolicitudId.setTipoSolicitud(solDescActForm.getTipoSolicitud());
		actSolicitud = actSolicitudDAO.findById(detalleSolAbaForm.getActSolicitud().getId());
		UsuarioDto  usuario;
		ParametroServices parametroServices = new ParametroServices();
		Email email = new Email();
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		if(!actSolicitud.getActDetSolicituds().isEmpty()){
			actSolicitud.setEstado("E");
			detalleSolAbaForm.setEstado("E");
			detalleSolAbaForm.setEstadoNombre("Enviada");			   				
		try {
			msgText = "Buen dia.<br><br>" +
					"Se le informa que el usuario " + user.getPrimerNom() + " " + user.getPrimerApe() +
					" ha enviado una solicitud para abastecimiento de suministros<br>" +
					"con codigo " + actSolicitud.getId().getCodSolicitud() + " y creada en la fecha "  + actSolicitud.getFecha_creacion() +
					".<br>Para ver mas detalles y aprobar o rechazar dicha solicitud" +
					",<br> consulte la opcion de aprobacion de abastecimiento" +
					" en el sistema SALDEI.<br><br>";
			
			if(detalleSolAbaForm.getComentario()!=null && !detalleSolAbaForm.getComentario().trim().equals(""))
				msgText += "El usuario " +  user.getPrimerNom() + " " + user.getPrimerApe()  + " especifico la siguiente descripcion:<br>" +
				"\"" + detalleSolAbaForm.getComentario() + "\"";
			msgText += "<br>--------------------------------------------------------------------" +
			"-------------------------------------------------------------------<br>" +
			"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
			"Favor no responder a este correo.<br>Gracias.";
			usuario = usuarioServices.getUsuarioDto(parametroServices.valorParametro(Constants.Parametro_JEFE_DEPTO));
			email.sendEmail("UCA-SALDEI: Solicitud de Abastecimiento", "saldei@uca.edu.sv", new String[]{usuario.getEmail()}, null, msgText, null);			
				actSolicitudDAO.merge(actSolicitud);				  
			    HibernateSessionFactory.getSession().beginTransaction().commit();				
				errors.add("detsolaba.mensaje.exito", new ActionError("detsolaba.mensaje.exito.enviada"));
				this.addErrors(request, errors);
			}
		catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();				
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		}
		}//endif isEmpty
		
		else{
				errors.add("detsolaba.mensajeError.error", new ActionError("detsolaba.mensajeError.error.noenviar"));
				this.addErrors(request, errors);
				target = "updateFail";
		}
		
		
	    find(mapping, form, request, response);
		return mapping.findForward(target);
	}
	public ActionForward back(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception, 
                                                 ServletException {
	
		//form.reset(mapping,request);		
		DetalleSolAbaForm a = (DetalleSolAbaForm) form;
		
			
		request.setAttribute("flagBack", "lnk"+String.valueOf(a.getActSolicitud().getId().getCodSolicitud()));
		return mapping.findForward(KEY_BACK);
	}
	
}