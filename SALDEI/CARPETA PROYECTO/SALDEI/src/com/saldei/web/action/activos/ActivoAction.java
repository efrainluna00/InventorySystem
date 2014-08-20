/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.activos;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.exception.ConstraintViolationException;

import com.saldei.hibernate.tables.activos.ActActivoDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.ActivoForm;

/**
 * MyEclipse Struts Creation date: 06-13-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/activoAction" name="activoForm"
 *                input="/html/activos/activo.jsp" parameter="accion"
 *                scope="request"
 * @struts.action-forward name="updateFail" path="/html/activos/activo.jsp"
 * @struts.action-forward name="success" path="/html/activos/activo.jsp?accion="
 */
public class ActivoAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * Method find
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		ActivoForm activoForm = (ActivoForm) form;
		ActActivoDAO actActivoDAO = new ActActivoDAO();

		try {

			data = actActivoDAO.findAll();
			request.setAttribute("listActivos", data);
			if (!isFLAG_UPDATE()) {
				activoForm.reset(mapping, request);
			} else {
				setFLAG_UPDATE(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		return mapping.findForward(target);
	}

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		ActivoForm activoForm = (ActivoForm) form;
		ActActivoDAO activoDAO = new ActActivoDAO();

		try {
			activoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				activoForm.getActActivo().setActMotivoBaja(null);
				activoDAO.delete(activoForm.getActActivo());

				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("activo.mensaje.exito", new ActionError(
						"activo.mensaje.exito.delete"));
			} else {
				target = "updateFail";
				setFLAG_UPDATE(true);
			}
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("activo.mensajeError.error", new ActionError(
					"activo.mensajeError.error.nodelete"));
			HibernateSessionFactory.getSession().beginTransaction().rollback();
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
		} finally {
			HibernateSessionFactory.getSession().close();
		}

		find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	@Override
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		List data;

		ActivoForm activoForm = (ActivoForm) form;
		ActActivoDAO actActivoDAO = new ActActivoDAO();
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		try {
			activoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				if (activoForm.getCodAltUca() != null
						&& !activoForm.getCodAltUca().trim().equals("")) {
					data = actActivoDAO.findByCodAltUca(activoForm
							.getCodAltUca());
					if (!data.isEmpty())
						errors.add("activo.mensajeError.error",
								new ActionError(
										"activo.mensajeError.error.insert"));
				} else {
					activoForm.getActActivo().setActMotivoBaja(null);
					actActivoDAO.save(activoForm.getActActivo());
					HibernateSessionFactory.getSession().beginTransaction()
							.commit();
					errors.add("activo.mensaje.exito", new ActionError(
							"activo.mensaje.exito.insert"));
				}

			} else {
				setFLAG_UPDATE(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}

		find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	@Override
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		ActivoForm activoForm = (ActivoForm) form;
		ActActivoDAO actActivoDAO = new ActActivoDAO();

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		try {
			activoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				if (activoForm.getCodAltUca() != null
						&& !activoForm.getCodAltUca().trim().equals("")) {
					data = actActivoDAO.findByCodAltUcaUpd(activoForm
							.getCodAltUca(), activoForm.getCodActivo()
							.toString());
					if (!data.isEmpty())
						errors.add("activo.mensajeError.error",
								new ActionError(
										"activo.mensajeError.error.insert"));
					else {
						activoForm.getActActivo().setActMotivoBaja(null);
						actActivoDAO.merge(activoForm.getActActivo());
						HibernateSessionFactory.getSession().beginTransaction()
								.commit();
						errors.add("activo.mensaje.exito", new ActionError(
								"activo.mensaje.exito.update"));
					}

				} else {
					activoForm.getActActivo().setActMotivoBaja(null);
					actActivoDAO.merge(activoForm.getActActivo());
					HibernateSessionFactory.getSession().beginTransaction()
							.commit();
					errors.add("activo.mensaje.exito", new ActionError(
							"activo.mensaje.exito.update"));
				}

			} else {
				target = "updateFail";
				setFLAG_UPDATE(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}

		find(mapping, form, request, response);
		return mapping.findForward(target);
	}
}