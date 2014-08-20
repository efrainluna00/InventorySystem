/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.activos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.action.BaseAction;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.DetReqRecursoForm;
import com.saldei.web.services.administracion.ParametroServices;
import com.saldei.web.services.seguridad.UsuarioServices;

/**
 * MyEclipse Struts Creation date: 06-21-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/detReqRecurso" name="detReqRecursoForm"
 *                input="/html/activos/detReqRecurso.jsp" parameter="accion"
 *                scope="request" validate="true"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/detReqRecurso.jsp"
 * @struts.action-forward name="back" path="/reqRecursoAction.do"
 * @struts.action-forward name="success"
 *                        path="/html/activos/detReqRecurso.jsp?accion="
 * @struts.action-forward name="cancel"
 *                        path="/html/activos/detReqRecurso.jsp?accion=Find"
 */
public class DetReqRecursoAction extends BaseAction {

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors, ActionMessages messages) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		DetReqRecursoForm detReqRecursoForm = (DetReqRecursoForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		try {
			detReqRecursoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				detReqRecursoForm.setActActivo(null);
				actDetSolicitudDAO.delete(detReqRecursoForm
						.getActDetSolicitud());

				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("detReq.mensaje.exito", new ActionError(
						"detReq.mensaje.exito.delete"));
			} else {
				target = "updateFail";
				setFLAG_UPDATE(true);
			}
		} catch (Exception e) {
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

		DetReqRecursoForm detReqRecursoForm = (DetReqRecursoForm) form;

		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		try {
			detReqRecursoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				detReqRecursoForm.setActActivo(null);
				actDetSolicitudDAO.save(detReqRecursoForm.getActDetSolicitud());

				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("detReq.mensaje.exito", new ActionError(
						"detReq.mensaje.exito.insert"));
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
		DetReqRecursoForm detReqRecursoForm = (DetReqRecursoForm) form;

		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		try {
			detReqRecursoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				detReqRecursoForm.setActActivo(null);
				actDetSolicitudDAO
						.merge(detReqRecursoForm.getActDetSolicitud());
				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("detReq.mensaje.exito", new ActionError(
						"detReq.mensaje.exito.update"));
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

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		DetReqRecursoForm detReqRecursoForm = (DetReqRecursoForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();

		try {
			/* para guardar la configuracion del grid papa */
			request.setAttribute("numeroPagina", request
					.getParameter("numeroPagina"));
			request.setAttribute("nombreGrid", request
					.getParameter("nombreGrid"));
			request.setAttribute("cantidadPagina", request
					.getParameter("cantidadPagina"));

			detReqRecursoForm.getActDetSolicitud().getId().setActSolicitud(
					actSolicitudDAO.findById(new ActSolicitudId(
							detReqRecursoForm.getTipoSolicitud(),
							detReqRecursoForm.getCodSolicitud())));
			data = actDetSolicitudDAO.findAllRequisicion((detReqRecursoForm
					.getCodSolicitud()));
			request.setAttribute("listDetRequisicion", data);
			// int codppto = detallePptoForm.getCodPresupuesto();
			if (!isFLAG_UPDATE()) {
				detReqRecursoForm.reset(mapping, request);
			} else {
				setFLAG_UPDATE(false);
			}
			// detallePptoForm.setCodPresupuesto(codppto);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		return mapping.findForward(target);
	}

	protected Map getKeyMethodMap() {
		HashMap map = (HashMap) super.getKeyMethodMap();

		/*
		 * <html:submit property="accion" styleId="enviarSolicitud">
		 * <bean:message key="opc.enviarSolicitud" />
		 */

		map.put("opc.enviarSolicitud", "enviarRequisicion");

		return map;
	}

	@SuppressWarnings("deprecation")
	public ActionForward enviarRequisicion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServletException {

		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionErrors();
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		UsuarioDto usuario;
		ParametroServices parametroServices = new ParametroServices();
		Email email = new Email();
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		// form.reset(mapping,request);
		// TODO Auto-generated method stub
		DetReqRecursoForm detReqRecursoForm = (DetReqRecursoForm) form;

		ActSolicitud actSolicitud = new ActSolicitud();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		// ActSolicitudId actSolicitudId = new ActSolicitudId();
		// actSolicitudId.setCodSolicitud(solDescActForm.getCodSolicitud());
		// actSolicitudId.setTipoSolicitud(solDescActForm.getTipoSolicitud());
		actSolicitud = actSolicitudDAO.findById(detReqRecursoForm.getId()
				.getActSolicitud().getId());

		if (!actSolicitud.getActDetSolicituds().isEmpty()) {
			actSolicitud.setEstado("E");
			detReqRecursoForm.setEstado("E");

			try {
				msgText = "Buen dia.<br><br>"
						+ "Se le informa que el usuario "
						+ user.getPrimerNom()
						+ " "
						+ user.getPrimerApe()
						+ " ha enviado una solicitud de requisicion de recursos<br>"
						+ "con codigo "
						+ actSolicitud.getId().getCodSolicitud()
						+ " y creada en la fecha "
						+ actSolicitud.getFecha_creacion()
						+ ".<br>Para ver mas detalles y aprobar o rechazar dicha solicitud,"
						+ "<br>consulte la opci�n de aprobaci�n de requisicion de recursos"
						+ " en el sistema SALDEI.<br><br>";
				if (actSolicitud.getComentario() != null
						&& !actSolicitud.getComentario().trim().equals(""))
					msgText += "El usuario " + user.getPrimerNom() + " "
							+ user.getPrimerApe()
							+ " especifico la siguiente descripcion:<br>"
							+ "\"" + actSolicitud.getComentario() + "\"";
				msgText += "<br>--------------------------------------------------------------------"
						+ "-------------------------------------------------------------------<br>"
						+ "Este correo fue generado de manera automatica por el sistema SALDEI.<br>"
						+ "Favor no responder a este correo.<br>Gracias.";
				usuario = usuarioServices.getUsuarioDto(parametroServices
						.valorParametro(Constants.Parametro_JEFE_DEPTO));
				email.sendEmail("UCA-SALDEI: Requisicion de Suministros",
						"saldei@uca.edu.sv",
						new String[] { usuario.getEmail() }, null, msgText,
						null);
				actSolicitudDAO.merge(actSolicitud);
				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("detReq.mensaje.exito", new ActionError(
						"detReq.mensaje.exito.enviada"));
				this.addErrors(request, errors);
			} catch (Exception e) {
				e.printStackTrace();
				HibernateSessionFactory.getSession().beginTransaction()
						.rollback();
				// TODO: handle exception
			} finally {
				HibernateSessionFactory.getSession().close();
			}
		}// endif isEmpty

		else {
			errors.add("detsoldesca.mensajeError.error", new ActionError(
					"detsoldesca.mensajeError.error.noenviar"));
			this.addErrors(request, errors);
			target = "updateFail";
		}

		find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {

		// form.reset(mapping,request);
		DetReqRecursoForm a = (DetReqRecursoForm) form;

		request.setAttribute("flagBack", "lnk"
				+ String.valueOf(a.getId().getActSolicitud().getId()
						.getCodSolicitud()));
		return mapping.findForward(KEY_BACK);
	}
}
