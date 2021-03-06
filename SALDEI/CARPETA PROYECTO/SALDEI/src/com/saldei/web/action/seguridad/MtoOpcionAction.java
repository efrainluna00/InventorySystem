/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.seguridad;

import java.util.List;

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

import com.saldei.hibernate.tables.Opcion;
import com.saldei.hibernate.tables.OpcionDAO;
import com.saldei.hibernate.tables.activos.ActTipoUnidadDAO;
import com.saldei.hibernate.tables.activos.ActUnidadDAO;
import com.saldei.hibernate.tables.seguridad.OpcionSeguridad;
import com.saldei.hibernate.tables.seguridad.OpcionSeguridadDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.UnidadForm;
import com.saldei.web.form.seguridad.MtoOpcionForm;
import com.saldei.web.form.seguridad.OpcionForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-06-2009
 * 
 * XDoclet definition:
 * @struts.action path="/mtoOpcionAction" name="mtoOpcionForm" input="/html/seguridad/mantenimientos/mtoOpcion.jsp" parameter="accion" scope="request"
 * @struts.action-forward name="updateFail" path="/html/seguridad/mantenimientos/mtoOpcion.jsp"
 * @struts.action-forward name="back" path="/mtoOpcionAction.do?accion=Find"
 * @struts.action-forward name="success" path="/html/seguridad/mantenimientos/mtoOpcion.jsp?accion="
 */
public class MtoOpcionAction extends BaseAction {
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
	//NO SE BORRAN LAS UNIDADES SOLAMENTE SE PONEN EN ESTADO INACTIVO
	@SuppressWarnings("deprecation")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
	    
		MtoOpcionForm mtoOpcionForm = (MtoOpcionForm) form;
		OpcionDAO opcionDAO = new OpcionDAO();	
		OpcionSeguridadDAO opcionSeguridadDAO = new OpcionSeguridadDAO();
		OpcionSeguridad opcionSeguridad = new OpcionSeguridad();
		
		
		try {
		
				mtoOpcionForm.setEstOpcion("I");
				opcionDAO.merge(mtoOpcionForm.getOpcion());
				HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("mopc.mensaje.exito", new ActionError("mopc.mensaje.exito.delete"));
		} 
		 catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add("uni.mensajeError.error", new ActionError("uni.mensajeError.error.nodelete"));
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

		MtoOpcionForm mtoOpcionForm = (MtoOpcionForm) form;
		OpcionDAO opcionDAO = new OpcionDAO();		
		OpcionSeguridadDAO opcionSeguridadDAO = new OpcionSeguridadDAO();
		
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
				    
	    
		try {
			mtoOpcionForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				opcionDAO.jdbcGuardar(mtoOpcionForm.getOpcion(),mtoOpcionForm.getVisible());
				//opcionDAO.save(mtoOpcionForm.getOpcion());
				//opcionSeguridadDAO.save(mtoOpcionForm.getOpcionSeguridad());
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("mopc.mensaje.exito", new ActionError("mopc.mensaje.exito.insert"));
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
		MtoOpcionForm mtoOpcionForm = (MtoOpcionForm) form;
		OpcionDAO opcionDAO = new OpcionDAO();
		OpcionSeguridad opcionSeguridad = new OpcionSeguridad();
		OpcionSeguridadDAO opcionSeguridadDAO = new OpcionSeguridadDAO();
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;					   			
		
		try {
			mtoOpcionForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				opcionDAO.merge(mtoOpcionForm.getOpcion());
				opcionSeguridad = opcionSeguridadDAO.findById(mtoOpcionForm.getIdOpcion());
				if(opcionSeguridad!=null)
					opcionSeguridadDAO.merge(opcionSeguridad);
			    HibernateSessionFactory.getSession().beginTransaction().commit();				
				errors.add("mopc.mensaje.exito", new ActionError("mopc.mensaje.exito.update"));
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
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;
		Opcion opcion = new Opcion();
		
		MtoOpcionForm mtoOpcionForm = (MtoOpcionForm) form;
		OpcionDAO opcionDAO = new OpcionDAO();
	    
		try {
		
			data = opcionDAO.findAll2();
		    request.setAttribute("listaUnidad", data);
				  
		    //TipoUnidadForm f = (TipoUnidadForm) listaTipoUnidad;
		    //unidadForm.setActTipoUnidad(f);
		    
			if(!isFLAG_UPDATE()){
				mtoOpcionForm.reset(mapping, request);
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
}