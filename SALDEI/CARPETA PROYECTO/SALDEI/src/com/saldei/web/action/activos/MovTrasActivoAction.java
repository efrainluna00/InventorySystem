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

import com.saldei.hibernate.tables.activos.ActHistUnidadActivoDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.action.BaseAction;
import com.saldei.web.bean.Util;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.MovTrasActivoForm;
import com.saldei.web.services.seguridad.UsuarioServices;

/**
 * MyEclipse Struts Creation date: 07-01-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/movTrasActivoAction" name="movTrasActivoForm"
 *                input="/html/activos/movTrasActivo.jsp" parameter="accion"
 *                scope="request" validate="true"
 * @struts.action-forward name="back" path="/movTrasActivoAction.do?accion=Find"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/movTrasActivo.jsp"
 * @struts.action-forward name="success"
 *                        path="/html/activos/movTrasActivo.jsp?accion="
 */
public class MovTrasActivoAction extends BaseAction {

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List act;
		UsuarioServices usuarioServices = new UsuarioServices();
		String msgText;
		UsuarioDto usuarioDto;
		Email email = new Email();
		MovTrasActivoForm movTrasActivoForm = (MovTrasActivoForm) form;
		ActHistUnidadActivoDAO actHistUnidadActivoDAO = new ActHistUnidadActivoDAO();
		Util emailer = new Util();
		try {
			movTrasActivoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				act = actHistUnidadActivoDAO.verificarAct(movTrasActivoForm
						.getActActivo().getCodActivo());
				if (act.isEmpty()) {
					// CORREO PARA EL RESPONSABLE ACTUAL
					msgText = emailer.trasladoMail(movTrasActivoForm
							.getRecursoDesc(), movTrasActivoForm.getCodActivo()
							.toString(), movTrasActivoForm.getDescripcion2(),
							movTrasActivoForm.getDescripcion());
					usuarioDto = usuarioServices
							.getUsuarioDto(movTrasActivoForm
									.getCodResponsable2());
					email.sendEmail("UCA-SALDEI: Traslado de Activos",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);
					// CORREO PARA EL NUEVO RESPONSABLE
					usuarioDto = usuarioServices
							.getUsuarioDto(movTrasActivoForm
									.getCodResponsable());
					email.sendEmail("UCA-SALDEI: Traslado de Activos",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);
					actHistUnidadActivoDAO.modActivosUni(movTrasActivoForm
							.getCodUnidad(), movTrasActivoForm.getCodActivo());
					HibernateSessionFactory.getSession().beginTransaction()
							.commit();
					errors.add("trasactivo.mensaje.exito", new ActionError(
							"trasactivo.mensaje.exito.traslado"));
				} else
					errors.add("trasactivo.mensajeError.error",
							new ActionError(
									"trasactivo.mensajeError.error.traslado"));
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
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		MovTrasActivoForm activoForm = (MovTrasActivoForm) form;
		ActHistUnidadActivoDAO histUnidadActivoDAO = new ActHistUnidadActivoDAO();

		try {

			data = histUnidadActivoDAO.findAll();
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

}