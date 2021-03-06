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

import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.activos.ActUnidad;
import com.saldei.hibernate.tables.activos.ActUnidadDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.action.BaseAction;
import com.saldei.web.bean.Util;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.DetAprSolSuministroForm;
import com.saldei.web.services.administracion.ParametroServices;
import com.saldei.web.services.seguridad.UsuarioServices;

/**
 * MyEclipse Struts Creation date: 07-06-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/detAprSolSuministroAction"
 *                name="detAprSolSuministroForm"
 *                input="/html/activos/detAprSolSuministro.jsp"
 *                parameter="accion" scope="request"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/detAprSolSuministro.jsp"
 * @struts.action-forward name="back" path="/aprSolSuministroAction.do"
 * @struts.action-forward name="success"
 *                        path="/html/activos/detAprSolSuministro.jsp?accion="
 * @struts.action-forward name="cancel"
 *                        path="/detAprSolSumnistroAction?accion=Find"
 */
public class DetAprSolSuministroAction extends BaseAction {

	protected Map getKeyMethodMap() {
		HashMap map = (HashMap) super.getKeyMethodMap();
		map.put("opc.aprobar", "aprobar");
		map.put("opc.rechazar", "rechazar");
		map.put("opc.anular", "anular");
		return map;
	}

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
		return null;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		DetAprSolSuministroForm detAprSolSuministroForm = (DetAprSolSuministroForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();

		try {
			detAprSolSuministroForm
					.getActDetSolicitud()
					.getId()
					.setActSolicitud(
							actSolicitudDAO.findById(new ActSolicitudId(
									detAprSolSuministroForm.getTipoSolicitud(),
									detAprSolSuministroForm.getCodSolicitud())));
			data = actDetSolicitudDAO.findAllAprDetSum((detAprSolSuministroForm
					.getCodSolicitud()));
			request.setAttribute("listDetSum", data);
			if (!isFLAG_UPDATE()) {
				detAprSolSuministroForm.reset(mapping, request);
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

	public ActionForward aprobar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data, data2;
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		UsuarioDto usuarioDto;
		Email email = new Email();
		ParametroServices parametroServices = new ParametroServices();
		ActUnidadDAO actUnidadDAO = new ActUnidadDAO();
		ActUnidad actUnidad;
		DetAprSolSuministroForm detAprSolSuministroForm = (DetAprSolSuministroForm) form;
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		Util emailer = new Util();
		try {

			actSolicitud = actSolicitudDAO.findById(detAprSolSuministroForm
					.getActSolicitud().getId());
			actSolicitud.setEstado("A");
			actSolicitud.setMotivoRechazo(detAprSolSuministroForm
					.getComentario());

			detAprSolSuministroForm.setDesEstado("Aprobada");
			detAprSolSuministroForm.setEstado("A");
			detAprSolSuministroForm
					.setComentario2(actSolicitud.getComentario());
			actSolicitudDAO.merge(actSolicitud);

			/* NOTIFICACION PARA EL SOLICITANTE */
			// aprobacionSolicitudMail(String nombreSol, String codSol, String
			// fechaCreacion,String consulte,String descripcion,String
			// resolucion, String solicitante,String entregar){
			msgText = emailer.aprobacionSolicitudMail(
					"solicitud de suministro", detAprSolSuministroForm
							.getCodSolicitud().toString(), actSolicitud
							.getFecha_creacion(), "solicitud de suministros",
					actSolicitud.getComentario(), detAprSolSuministroForm
							.getComentario(), "S", "suministros");
			usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
					.getCodSolicitante());
			email
					.sendEmail(
							"UCA-SALDEI: Resolucion de Solicitud de Suministro",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			/* NOTIFICACION PARA EL ENCARGADO DE RECURSO */
			msgText = emailer.aprobacionSolicitudMail(
					"solicitud de suministro", detAprSolSuministroForm
							.getCodSolicitud().toString(), actSolicitud
							.getFecha_creacion(),
					"salida por solicitud de suministros", actSolicitud
							.getComentario(), detAprSolSuministroForm
							.getComentario(), "N", "suministros");
			actUnidad = actUnidadDAO.findById(Integer
					.valueOf(detAprSolSuministroForm.getCodBodega()));
			usuarioDto = usuarioServices.getUsuarioDto(actUnidad
					.getCodResponsable());
			email
					.sendEmail(
							"UCA-SALDEI: Resolucion de Solicitud de Suministro",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("aprDetSum.mensaje.exito", new ActionError(
					"aprDetSum.mensaje.exito.aceptada"));
			this.addErrors(request, errors);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	public ActionForward rechazar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		UsuarioDto usuarioDto;
		Email email = new Email();
		DetAprSolSuministroForm detAprSolSuministroForm = (DetAprSolSuministroForm) form;
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		Util emailer = new Util();
		try {

			actSolicitud = actSolicitudDAO.findById(detAprSolSuministroForm
					.getActSolicitud().getId());
			actSolicitud.setMotivoRechazo(detAprSolSuministroForm
					.getComentario());
			actSolicitud.setEstado("R");
			detAprSolSuministroForm.setDesEstado("Rechazada");
			detAprSolSuministroForm.setEstado("R");
			detAprSolSuministroForm
					.setComentario2(actSolicitud.getComentario());
			// rechazoSolicitudMail(String nombreSol, String codSol, String
			// fechaCreacion,String descripcion,String resolucion){
			msgText = emailer.rechazoSolicitudMail("solicitud de suministro",
					detAprSolSuministroForm.getCodSolicitud().toString(),
					actSolicitud.getFecha_creacion(), actSolicitud
							.getComentario(), detAprSolSuministroForm
							.getComentario());
			usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
					.getCodSolicitante());
			email
					.sendEmail(
							"UCA-SALDEI: Resolucion de Solicitud de Suministro",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("aprDetSum.mensaje.exito", new ActionError(
					"aprDetSum.mensaje.exito.rechazada"));
			this.addErrors(request, errors);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}

	public ActionForward anular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		UsuarioDto usuarioDto;
		Email email = new Email();
		DetAprSolSuministroForm detAprSolSuministroForm = (DetAprSolSuministroForm) form;
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		ActUnidadDAO actUnidadDAO = new ActUnidadDAO();
		ActUnidad actUnidad;
		Util emailer = new Util();
		try {

			actSolicitud = actSolicitudDAO.findById(detAprSolSuministroForm
					.getActSolicitud().getId());
			actSolicitud.setMotivoRechazo(detAprSolSuministroForm
					.getMotivoRechazo());
			actSolicitud.setEstado("B");
			detAprSolSuministroForm.setDesEstado("Anulada");
			detAprSolSuministroForm.setEstado("B");
			detAprSolSuministroForm.setComentario(detAprSolSuministroForm
					.getMotivoRechazo());

			/* NOTIFICACION PARA EL SOLICITANTE */
			msgText = emailer.anulacionSolicitudMail("solicitud de suministro",
					detAprSolSuministroForm.getCodSolicitud().toString(),
					actSolicitud.getFecha_creacion(),
					"solicitud de suministros");
			usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
					.getCodSolicitante());
			email
					.sendEmail(
							"UCA-SALDEI: Anulacion de Solicitud de Suministro",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			/* NOTIFICACION PARA EL ENCARGADO DE RECURSO */
			msgText = emailer.anulacionSolicitudMail("solicitud de suministro",
					detAprSolSuministroForm.getCodSolicitud().toString(),
					actSolicitud.getFecha_creacion(),
					"salida por solicitud de suministros");
			actUnidad = actUnidadDAO.findById(Integer
					.valueOf(detAprSolSuministroForm.getCodBodega()));
			usuarioDto = usuarioServices.getUsuarioDto(actUnidad
					.getCodResponsable());
			email
					.sendEmail(
							"UCA-SALDEI: Anulacion de Solicitud de Suministro",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("aprDetSum.mensaje.exito", new ActionError(
					"aprDetSum.mensaje.exito.anulada"));
			this.addErrors(request, errors);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}

}