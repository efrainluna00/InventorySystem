/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.activos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.LookupDispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActCotizacion;
import com.saldei.hibernate.tables.activos.ActCotizacionDAO;
import com.saldei.hibernate.tables.activos.ActCotizacionId;
import com.saldei.hibernate.tables.activos.ActCuentaDAO;
import com.saldei.hibernate.tables.activos.ActDetPresupuesto;
import com.saldei.hibernate.tables.activos.ActDetPresupuestoDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitudId;
import com.saldei.hibernate.tables.activos.ActUnidad;
import com.saldei.hibernate.tables.activos.ActUnidadDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.bean.Util;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.AprSolCompraForm;
import com.saldei.web.services.seguridad.UsuarioServices;

/**
 * MyEclipse Struts Creation date: 07-19-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/aprSolCompraAction" name="aprSolCompraForm"
 *                input="/html/activos/aprSolCompra.jsp" parameter="accion"
 *                scope="request"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/aprSolCompra.jsp"
 * @struts.action-forward name="success"
 *                        path="/html/activos/aprSolCompra.jsp?accion="
 */
public class AprSolCompraAction extends LookupDispatchAction {
	/*
	 * Generated Methods
	 */

	protected Map getKeyMethodMap() {
		HashMap map = new HashMap();

		map.put("opc.find", "enviadas");
		map.put("opc.insert", "findCotizacion");
		map.put("opc.aprobar", "aprobar");
		map.put("opc.rechazar", "rechazar");
		map.put("opc.enviarSolicitud", "enviar");
		map.put("opc.cancel", "enviar");
		map.put("opc.back", "enviadas");
		map.put("opc.anular", "anular");

		return map;
	}

	/**
	 * Method enviadas: busca todas las solicitudes con estado enviada para
	 * mostrarse en la pantalla de aprobacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward enviadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		String target = "aprobar";
		List data;
		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();

		try {

			if (aprSolCompraForm.getMostrarHistorico() != null
					&& aprSolCompraForm.getMostrarHistorico().equals("S"))
				data = actSolicitudDAO.findAllSolicutudComprasEnviadas("S");
			else
				data = actSolicitudDAO.findAllSolicutudComprasEnviadas("N");
			request.setAttribute("listSolComprasEnviadas", data);

			// data = actSolicitudDAO.findAllSolicutudComprasEnviadas();
			// request.setAttribute("listSolComprasEnviadas", data);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		return mapping.findForward(target);
	}

	/**
	 * Method enviar: se ocupara este metodo para setear todas las propiedades
	 * de una solicitu de compra para luego proceder a la aprobacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward enviar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		String target = "success";
		List data;
		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActCotizacion actCotizacion;
		UsuarioDto usuarioDto;
		UsuarioServices usuarioServices = new UsuarioServices();
		ActCotizacionDAO actCotizacionDAO = new ActCotizacionDAO();
		ActDetPresupuestoDAO actDetPresupuestoDAO = new ActDetPresupuestoDAO();
		ActCuentaDAO actCuentaDAO = new ActCuentaDAO();

		try {
			aprSolCompraForm.setActSolicitud(actSolicitudDAO
					.findById(new ActSolicitudId(aprSolCompraForm
							.getTipoSolicitud(), Integer
							.valueOf(aprSolCompraForm.getCodSolicitud()))));
			actCotizacion = actCotizacionDAO.findByEstado(aprSolCompraForm
					.getTipoSolicitud(), aprSolCompraForm.getCodSolicitud());
			usuarioDto = usuarioServices.getUsuarioDto(aprSolCompraForm
					.getActSolicitud().getCodSolicitante());
			aprSolCompraForm.setSolicitante(usuarioDto.getPrimerNom() + " "
					+ usuarioDto.getPrimerApe());
			if (actCotizacion != null) {
				aprSolCompraForm.setActCotizacion(actCotizacion);
				aprSolCompraForm.setActDetPresupuesto(actDetPresupuestoDAO
						.findById(aprSolCompraForm.getActSolicitud()
								.getActDetPresupuesto().getId()));
				aprSolCompraForm.getActDetPresupuesto().getId().setActCuenta(
						actCuentaDAO.findById(aprSolCompraForm
								.getActSolicitud().getActDetPresupuesto()
								.getId().getActCuenta().getCodCuenta()));
				request.setAttribute("listCotDet", aprSolCompraForm
						.getActCotizacion().getActDetCotizacions());
			} else {
				aprSolCompraForm.reset(mapping, request);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		return mapping.findForward(target);
	}

	/**
	 * Method findContizaciones: se ocupara este metodo para buscar una
	 * cotizacion de la solicitud de compra y poder mostrarla en la pantalla de
	 * aprobacion de la solicitud.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward findCotizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		ActCotizacionDAO actCotizacionDAO = new ActCotizacionDAO();
		ActCotizacion actCotizacion;

		HttpSession session = request.getSession(true);
		String target = "success";

		try {
			actCotizacion = actCotizacionDAO.findById(new ActCotizacionId(
					aprSolCompraForm.getActSolicitud(), Integer
							.valueOf(aprSolCompraForm.getCodCotizacion())));
			if (actCotizacion != null) {
				aprSolCompraForm.setActCotizacion(actCotizacion);
				request.setAttribute("listCotDet", aprSolCompraForm
						.getActCotizacion().getActDetCotizacions());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		return mapping.findForward(target);
	}

	/**
	 * Method aprobar: se ocupara este metodo para aprobar la solicitud de
	 * compra *
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward aprobar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActCotizacionDAO actCotizacionDAO = new ActCotizacionDAO();
		ActDetPresupuestoDAO actDetPresupuestoDAO = new ActDetPresupuestoDAO();
		ActUnidadDAO actUnidadDAO = new ActUnidadDAO();
		UsuarioServices usuarioServices = new UsuarioServices();
		ActCotizacion actCotizacion;
		ActSolicitud actSolicitud;
		UsuarioDto usuarioDto;
		ActUnidad actUnidad;

		Email email = new Email();
		ActionErrors errors = new ActionErrors();

		ResourceBundle mensajes = ResourceBundle
				.getBundle("com.saldei.web.ApplicationResources");

		String target = "aprobar";
		String msgText;
		Util emailer = new Util();
		try {

			aprSolCompraForm.validate(mapping, request, errors);

			if (errors.isEmpty()) {

				actSolicitud = actSolicitudDAO.findById(new ActSolicitudId(
						aprSolCompraForm.getTipoSolicitud(), Integer
								.valueOf(aprSolCompraForm.getCodSolicitud())));
				// aprSolCompraForm.setActSolicitud();
				actCotizacion = actCotizacionDAO.findById(new ActCotizacionId(
						aprSolCompraForm.getActSolicitud(), Integer
								.valueOf(aprSolCompraForm.getCodCotizacion())));
				if (actCotizacion != null) {
					aprSolCompraForm.setActCotizacion(actCotizacion);
					request.setAttribute("listCotDet", aprSolCompraForm
							.getActCotizacion().getActDetCotizacions());
				}

				Usuario user = (Usuario) request.getSession().getAttribute(
						"usuario");
				/* se actualiza en estado de la solicitud */
				actSolicitud.setEstado("A");
				actSolicitud.setActDetPresupuesto(aprSolCompraForm
						.getActDetPresupuesto());
				actSolicitud.setMotivoRechazo(aprSolCompraForm
						.getMotivoRechazo());
				actSolicitud.setCodBodega(Integer.valueOf(aprSolCompraForm
						.getCodBodega()));
				actSolicitud.setAsigCompra(aprSolCompraForm.getAsigCompra());
				actSolicitudDAO.merge(actSolicitud);

				/* se marca la cotizacion como seleccionada */
				aprSolCompraForm.getActCotizacion().setEstado("S");
				actCotizacionDAO.merge(aprSolCompraForm.getActCotizacion());

				/* se actualiza el saldo actual de presupuesto */
				aprSolCompraForm.getActDetPresupuesto().setSaldoActual(
						aprSolCompraForm.getActDetPresupuesto()
								.getSaldoActual()
								- Double.valueOf(aprSolCompraForm
										.getMontoActual()));
				actDetPresupuestoDAO.merge(aprSolCompraForm
						.getActDetPresupuesto(), user.getIdUsuario());

				/* se envian correo para el solicitante */
				// msgText = emailer.aprobacionSolicitudMail("solicitud de
				// suministro" ,
				// detAprSolSuministroForm.getCodSolicitud().toString(),
				// actSolicitud.getFecha_creacion(), "salida por solicitud de
				// suministros",actSolicitud.getComentario(),detAprSolSuministroForm.getComentario(),"N","suministros");
				msgText = emailer.aprobacionSolicitudMail(
						"solicitud de compra", aprSolCompraForm
								.getCodSolicitud(), actSolicitud
								.getFecha_creacion(), "solicitud de compra",
						actSolicitud.getComentario(), aprSolCompraForm
								.getMotivoRechazo(), "S", "");
				usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
						.getCodSolicitante());
				email.sendEmail(
						"UCA-SALDEI: Resolucion de Solicitud de Compra",
						"saldei@uca.edu.sv", new String[] { usuarioDto
								.getEmail() }, null, msgText, null);

				/* se envian correo para el encargado de bodega */
				msgText = emailer.aprobacionSolicitudMail(
						"solicitud de compra", aprSolCompraForm
								.getCodSolicitud(), actSolicitud
								.getFecha_creacion(),
						"salida por solicitud de recursos", actSolicitud
								.getComentario(), aprSolCompraForm
								.getMotivoRechazo(), "N", "recursos");
				actUnidad = actUnidadDAO.findById(Integer
						.valueOf(aprSolCompraForm.getCodBodega()));
				usuarioDto = usuarioServices.getUsuarioDto(actUnidad
						.getCodResponsable());
				email.sendEmail(
						"UCA-SALDEI: Resolucion de Solicitud de Compra",
						"saldei@uca.edu.sv", new String[] { usuarioDto
								.getEmail() }, null, msgText, null);
				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("aprSolCompra.mensaje.exito", new ActionError(
						"aprSolCompra.mensaje.exito.aprobar"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
		}
		return this.enviar(mapping, form, request, response);
	}

	/**
	 * Method rechazar: se ocupara este metodo para rechazar la solicitud de
	 * compra *
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward rechazar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;// TODO
																	// Auto-generated
																	// method
																	// stub
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActCotizacionDAO actCotizacionDAO = new ActCotizacionDAO();
		UsuarioServices usuarioServices = new UsuarioServices();
		ActCotizacion actCotizacion;
		ActSolicitud actSolicitud;
		UsuarioDto usuarioDto;
		Email email = new Email();
		ActionErrors errors = new ActionErrors();
		String msgText;
		Util emailer = new Util();

		try {

			// aprSolCompraForm.validate(mapping, request, errors);

			if (errors.isEmpty()) {

				actSolicitud = actSolicitudDAO.findById(new ActSolicitudId(
						aprSolCompraForm.getTipoSolicitud(), Integer
								.valueOf(aprSolCompraForm.getCodSolicitud())));
				// aprSolCompraForm.setActSolicitud();
				actCotizacion = actCotizacionDAO.findById(new ActCotizacionId(
						aprSolCompraForm.getActSolicitud(), Integer
								.valueOf(((aprSolCompraForm.getCodCotizacion()
										.equals("")) ? "-1" : aprSolCompraForm
										.getCodCotizacion()))));
				if (actCotizacion != null) {
					aprSolCompraForm.setActCotizacion(actCotizacion);
					request.setAttribute("listCotDet", aprSolCompraForm
							.getActCotizacion().getActDetCotizacions());
					/* se marca la cotizacion como seleccionada */
					aprSolCompraForm.getActCotizacion().setEstado("S");
					actCotizacionDAO.merge(aprSolCompraForm.getActCotizacion());
				}

				/* se actualiza en estado de la solicitud */
				actSolicitud.setEstado("R");
				// actSolicitud.setActDetPresupuesto(aprSolCompraForm.getActDetPresupuesto());
				actSolicitud.setMotivoRechazo(aprSolCompraForm
						.getMotivoRechazo());
				actSolicitudDAO.merge(actSolicitud);

				/* se envian correo para el solicitante */
				msgText = emailer.rechazoSolicitudMail("solicitud de compra",
						actSolicitud.getId().getCodSolicitud().toString(),
						actSolicitud.getFecha_creacion(), actSolicitud
								.getComentario(), aprSolCompraForm
								.getMotivoRechazo());
				usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
						.getCodSolicitante());
				email.sendEmail(
						"UCA-SALDEI: Resolucion de Solicitud de Compra",
						"saldei@uca.edu.sv", new String[] { usuarioDto
								.getEmail() }, null, msgText, null);
				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("aprSolCompra.mensaje.exito", new ActionError(
						"aprSolCompra.mensaje.exito.rechazar"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.getSession().beginTransaction().rollback();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
		}
		return this.enviar(mapping, form, request, response);
	}

	public ActionForward anular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Util emailer = new Util();
		String target = "success";
		String msgText;
		List data, data2;
		// ConfigEmail mail = new ConfigEmail();
		AprSolCompraForm aprSolCompraForm = (AprSolCompraForm) form;
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ActDetPresupuestoDAO b = new ActDetPresupuestoDAO();
		ActDetPresupuesto a = new ActDetPresupuesto();
		ActUnidad actUnidad;
		ActUnidadDAO actUnidadDAO = new ActUnidadDAO();
		a = b.findById(aprSolCompraForm.getActDetPresupuesto().getId());

		a.setSaldoActual(a.getSaldoActual()
				+ Double.valueOf(aprSolCompraForm.getMontoActual()));
		try {
			b.merge(a, user.getIdUsuario());
			HibernateSessionFactory.getSession().beginTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}

		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		UsuarioServices usuarioServices = new UsuarioServices();
		UsuarioDto usuarioDto;
		Email email = new Email();

		try {

			actSolicitud = actSolicitudDAO.findById(aprSolCompraForm
					.getActSolicitud().getId());
			actSolicitud.setEstado("B");
			// aprSolCompraForm.setEstadoNombre("Anulada");
			aprSolCompraForm.setEstado("B");
			/* NOTIFICA AL SOLICITANTE */
			msgText = emailer.anulacionSolicitudMail("solicitud de compra",
					actSolicitud.getId().getCodSolicitud().toString(),
					actSolicitud.getFecha_creacion(), "solicitud de compra");
			usuarioDto = usuarioServices.getUsuarioDto(actSolicitud
					.getCodSolicitante());
			email
					.sendEmail("UCA-SALDEI: Anulacion de Solicitud de Compra",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			/* NOTIFICA AL BODEGUERO */
			msgText = emailer.anulacionSolicitudMail("solicitud de compra",
					actSolicitud.getId().getCodSolicitud().toString(),
					actSolicitud.getFecha_creacion(),
					"salida por solicitud de recursos");
			actUnidad = actUnidadDAO.findById(Integer.valueOf(aprSolCompraForm
					.getCodBodega()));
			usuarioDto = usuarioServices.getUsuarioDto(actUnidad
					.getCodResponsable());
			email
					.sendEmail("UCA-SALDEI: Anulacion de Solicitud de Compra",
							"saldei@uca.edu.sv", new String[] { usuarioDto
									.getEmail() }, null, msgText, null);

			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("aprSolCompra.mensaje.exito", new ActionError(
					"aprSolCompra.mensaje.exito.anular"));
			this.addErrors(request, errors);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		this.enviar(mapping, form, request, response);
		// this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}

}