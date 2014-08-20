/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.seguridad;

import java.util.Iterator;
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

import com.saldei.hibernate.tables.UsuarioDei;
import com.saldei.hibernate.tables.UsuarioTitulo;
import com.saldei.hibernate.tables.UsuarioTituloDAO;
import com.saldei.hibernate.tables.activos.ActUnidadDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.UnidadForm;
import com.saldei.web.form.seguridad.UsuarioTituloForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-17-2009
 * 
 * XDoclet definition:
 * @struts.action path="/usuarioTituloAction" name="usuarioTituloForm" input="/html/seguridad/mantenimientos/usuarioTitulo.jsp" parameter="accion" scope="request"
 * @struts.action-forward name="updateFail" path="/html/seguridad/mantenimientos/usuarioTitulo.jsp"
 * @struts.action-forward name="back" path="/usuarioTituloAction.do?accion=Regresar"
 * @struts.action-forward name="success" path="/html/seguridad/mantenimientos/usuarioTitulo.jsp?accion="
 */
public class UsuarioTituloAction extends BaseAction {
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
	    
	    UsuarioTituloForm usuarioTituloForm = (UsuarioTituloForm) form;
		UsuarioTituloDAO usuarioTituloDAO = new UsuarioTituloDAO();			    
		
		try {
					
				usuarioTituloForm.setIdUsuario(usuarioTituloForm.getCodResponsable());
				usuarioTituloDAO.delete(usuarioTituloForm.getUsuarioTitulo());			    
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("uti.mensaje.exito", new ActionError("uti.mensaje.exito.delete"));
			
		} 
		 catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add("uti.mensajeError.error", new ActionError("uti.mensajeError.error.nodelete"));
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

		UsuarioTituloForm usuarioTituloForm = (UsuarioTituloForm) form;
		UsuarioTituloDAO usuarioTituloDAO = new UsuarioTituloDAO();	
		UsuarioTitulo usuarioTitulo = new UsuarioTitulo();
		UsuarioDei usuarioDei = new UsuarioDei();
		//UsuarioDei usuarioDei = new UsuarioDei();
		
		List lut;
		
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
				    
	    
		try {
			usuarioTituloForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				if(usuarioTituloForm.getPorDefecto().equals("S")){
					lut = usuarioTituloDAO.buscarPorDefecto(usuarioTituloForm.getCodResponsable());
					if(!lut.isEmpty()){
						Map map;
						Iterator iter = lut.iterator();
						while(iter.hasNext()){
							map = (Map) iter.next();
							usuarioDei.setCodigoEmpleado(map.get("id_usuario").toString());
							usuarioTitulo.setUsuarioDei(usuarioDei);
							usuarioTitulo.setCorrelativo((Integer)map.get("correlativo"));
						}
						usuarioTitulo = usuarioTituloDAO.findById(usuarioTitulo.getCorrelativo());
						usuarioTitulo.setPorDefecto("N");
						usuarioTituloDAO.merge(usuarioTitulo);
					}
				}
							
				usuarioTituloForm.setIdUsuario(usuarioTituloForm.getCodResponsable());
				
				//usuarioTituloForm.getUsuarioTitulo().setCorrelativo(0);
				usuarioTituloDAO.save(usuarioTituloForm.getUsuarioTitulo());
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("uti.mensaje.exito", new ActionError("uti.mensaje.exito.insert"));
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
		UsuarioTituloForm usuarioTituloForm = (UsuarioTituloForm) form;
		UsuarioTituloDAO usuarioTituloDAO = new UsuarioTituloDAO();			    			    
		UsuarioTitulo usuarioTitulo = new UsuarioTitulo();
		UsuarioDei usuarioDei = new UsuarioDei();
		
		List lut;
		
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
	    
		
		try {
			usuarioTituloForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				if(usuarioTituloForm.getPorDefecto().equals("S")){
					
					lut = usuarioTituloDAO.buscarPorDefecto(usuarioTituloForm.getCodResponsable());
					if(!lut.isEmpty()){
						Iterator iter = lut.iterator();
						Map map;
						while(iter.hasNext()){
							map = (Map) iter.next();
							usuarioDei.setCodigoEmpleado(map.get("id_usuario").toString());
							usuarioTitulo.setUsuarioDei(usuarioDei);
							usuarioTitulo.setCorrelativo((Integer)map.get("correlativo"));
						}
						usuarioTitulo = usuarioTituloDAO.findById(usuarioTitulo.getCorrelativo());
						usuarioTitulo.setPorDefecto("N");
						usuarioTituloDAO.merge(usuarioTitulo);
					}
				}
				
	usuarioTituloForm.setIdUsuario(usuarioTituloForm.getCodResponsable());
				
				//usuarioTituloForm.getUsuarioTitulo().setCorrelativo(0);
				usuarioTituloDAO.merge(usuarioTituloForm.getUsuarioTitulo());		  
			    HibernateSessionFactory.getSession().beginTransaction().commit();				
				errors.add("uti.mensaje.exito", new ActionError("uti.mensaje.exito.update"));
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
		
		UsuarioTituloForm usuarioTituloForm = (UsuarioTituloForm) form;
		UsuarioTituloDAO usuarioTituloDAO = new UsuarioTituloDAO();
	    
		try {
		
			data = usuarioTituloDAO.findAll();
		    request.setAttribute("listaUsuarioTitulo", data);
				  
		    //TipoUnidadForm f = (TipoUnidadForm) listaTipoUnidad;
		    //unidadForm.setActTipoUnidad(f);
		    
			if(!isFLAG_UPDATE()){
				usuarioTituloForm.reset(mapping, request);
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