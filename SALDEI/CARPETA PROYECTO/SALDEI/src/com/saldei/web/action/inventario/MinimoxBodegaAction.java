/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.inventario;

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

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActPresupuesto;
import com.saldei.hibernate.tables.activos.ActPresupuestoDAO;
import com.saldei.hibernate.tables.inventario.InvBodega;
import com.saldei.hibernate.tables.inventario.InvBodegaDAO;
import com.saldei.hibernate.tables.inventario.InvExistencia;
import com.saldei.hibernate.tables.inventario.InvExistenciaDAO;
import com.saldei.hibernate.tables.inventario.InvExistenciaId;
import com.saldei.hibernate.tables.inventario.InvRecurso;
import com.saldei.hibernate.tables.inventario.InvRecursoDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.DetallePptoForm;
import com.saldei.web.form.activos.PresupuestoForm;
import com.saldei.web.form.inventario.BodegaForm;
import com.saldei.web.form.inventario.MinimoxBodegaForm;
import com.saldei.web.form.inventario.RecursoForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2009
 * 
 * XDoclet definition:
 * @struts.action path="/minimoxBodegaAction" name="minimoxBodegaForm" input="/html/inventario/minimoxBodega.jsp" parameter="accion" scope="request"
 * @struts.action-forward name="updateFail" path="/html/inventario/minimoxBodega.jsp"
 * @struts.action-forward name="back" path="/minimoxBodegaAction.do?accion=Find"
 * @struts.action-forward name="success" path="/html/inventario/minimoxBodega.jsp?accion="
 */
public class MinimoxBodegaAction extends BaseAction {
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;
	    
	    MinimoxBodegaForm minimoxBodegaForm = (MinimoxBodegaForm) form;
		InvExistenciaDAO invExistenciaDAO = new InvExistenciaDAO();
		InvExistencia invExistencia = new InvExistencia();
		
		
		try {
			invExistencia = invExistenciaDAO.findById(new InvExistenciaId(new InvBodega(Integer.valueOf(minimoxBodegaForm.getCodBodega())),new InvRecurso(minimoxBodegaForm.getCodRecurso())));
			if(invExistencia!=null && invExistencia.getExistencia()<=0){
				invExistenciaDAO.delete(invExistencia);
				 HibernateSessionFactory.getSession().beginTransaction().commit();
				errors.add("minixbod.mensaje.exito", new ActionError("minixbod.mensaje.exito.delete"));
			}
			else
			{
				//minixbod.mensajeError.error.nodesasoc
				errors.add("minixbod.mensajeError.error", new ActionError("minixbod.mensajeError.error.nodesasoc"));
			}
	
		}catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("minixbod.mensajeError.error", new ActionError("minixbod.mensajeError.error.nodelete"));
			HibernateSessionFactory.getSession().beginTransaction().rollback();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//errors.add("bode.mensaje.exito", new ActionError("bode.mensaje.exito.nodelete"));
			HibernateSessionFactory.getSession().beginTransaction().rollback();
		}finally{
			HibernateSessionFactory.getSession().close();
		}
		
	    find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	/* (non-Javadoc)
	 * @see com.saldei.web.action.BaseAction#insert(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	@Override
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		
		MinimoxBodegaForm minimoxBodegaForm = (MinimoxBodegaForm) form;
		InvExistencia invExistencia = new InvExistencia();
		InvExistenciaDAO invExistenciaDAO = new InvExistenciaDAO();
		InvExistenciaId invExistenciaId = new InvExistenciaId(new InvBodega(), new InvRecurso());
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		
		try {
			minimoxBodegaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				invExistenciaId.getInvBodega().setCodBodega(Integer.valueOf(minimoxBodegaForm.getCodBodega()));
				invExistenciaId.getInvRecurso().setCodRecurso(minimoxBodegaForm.getCodRecurso());
				invExistencia = invExistenciaDAO.findById(invExistenciaId);
				invExistenciaDAO.save(minimoxBodegaForm.getInvExistencia());
			    HibernateSessionFactory.getSession().beginTransaction().commit();
				minimoxBodegaForm.reset(mapping, request);
				errors.add("minixbod.mensaje.exito", new ActionError("minixbod.mensaje.exito.insert"));
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

	/* (non-Javadoc)
	 * @see com.saldei.web.action.BaseAction#update(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	@Override
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		MinimoxBodegaForm minimoxBodegaForm = (MinimoxBodegaForm) form;
		InvExistenciaDAO invExistenciaDAO = new InvExistenciaDAO();
				
		HttpSession session = request.getSession(true);
	    String target = KEY_SUCCESS;					   			
		
		try {
			minimoxBodegaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				invExistenciaDAO.merge(minimoxBodegaForm.getInvExistencia());			    			  
			    HibernateSessionFactory.getSession().beginTransaction().commit();				
				errors.add("minixbod.mensaje.exito", new ActionError("minixbod.mensaje.exito.update"));
			}else{
				target = "updateFail";
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
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {		
		HttpSession session = request.getSession(true);
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		String target = KEY_SUCCESS;
		List data;
		Integer isBodeguero;
		
		MinimoxBodegaForm minimoxBodegaForm = (MinimoxBodegaForm) form;
		InvExistenciaDAO invExistenciaDAO = new InvExistenciaDAO();
		
		try {			
			request.setAttribute("numeroPagina", request.getParameter("numeroPagina"));
			request.setAttribute("nombreGrid", request.getParameter("nombreGrid"));
			request.setAttribute("cantidadPagina", request.getParameter("cantidadPagina"));
			isBodeguero = invExistenciaDAO.isBodeguero(user.getIdUsuario());
			minimoxBodegaForm.setIsBodeguero(isBodeguero);
			data = invExistenciaDAO.findBodegaxMinimo(minimoxBodegaForm.getCodRecurso());
		    request.setAttribute("listaMinimo", data);
			if(!isFLAG_UPDATE()){
				minimoxBodegaForm.reset(mapping, request);
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
	
	public ActionForward back(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception, 
                                                 ServletException {
		InvRecursoDAO invRecursoDAO = new InvRecursoDAO();
		InvRecurso invRecurso;
		RecursoForm recursoForm = new RecursoForm();
		
		

		//form.reset(mapping,request);		
		MinimoxBodegaForm minimo = (MinimoxBodegaForm) form;
		invRecurso = invRecursoDAO.findById(minimo.getCodRecurso());
		recursoForm.setInvRecurso(invRecurso);		
		request.setAttribute("flagBack", "lnk"+String.valueOf(minimo.getCodRecurso()));
		return mapping.findForward(KEY_BACK);
	}
	
}