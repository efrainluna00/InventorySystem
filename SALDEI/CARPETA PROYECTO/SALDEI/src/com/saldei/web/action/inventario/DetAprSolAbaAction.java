/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.inventario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActUnidad;
import com.saldei.hibernate.tables.activos.ActUnidadDAO;
import com.saldei.hibernate.tables.inventario.InvBodega;
import com.saldei.hibernate.tables.inventario.InvBodegaDAO;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.util.mail.Email;
import com.saldei.web.action.BaseAction;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.bean.Util;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.activos.DetAprSolDescActForm;
import com.saldei.web.form.activos.DetAprSolSuministroForm;
import com.saldei.web.form.activos.RepHisAsigForm;
import com.saldei.web.form.inventario.DetAprSolAbaForm;
import com.saldei.web.services.administracion.ParametroServices;
import com.saldei.web.services.seguridad.UsuarioServices;

/** 
 * MyEclipse Struts
 * Creation date: 07-12-2009
 * 
 * XDoclet definition:
 * @struts.action path="/detAprSolAbaAction" name="detAprSolAbaForm" input="/html/inventario/detAprSolAba.jsp" parameter="accion" scope="request"
 * @struts.action-forward name="updateFail" path="/html/inventario/detAprSolAba.jsp"
 * @struts.action-forward name="back" path="/aprSolicitudAbaAction.do?accion=Find"
 * @struts.action-forward name="success" path="/html/inventario/detAprSolAbaAction.jsp?accion="
 */
public class DetAprSolAbaAction extends BaseAction {
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
	protected Map getKeyMethodMap(){
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
	
	public ActionForward aprobar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		//ConfigEmail mail = new ConfigEmail();
		DetAprSolAbaForm detAprSolAbaForm = (DetAprSolAbaForm) form;
		//AprSolPrestamoForm prestamo = new AprSolPrestamoForm();
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		UsuarioDto  usuarioDto;
		ParametroServices parametroServices = new ParametroServices();
		Email email = new Email();
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		Util emailer = new Util();
		//Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		try {
			detAprSolAbaForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
			detAprSolAbaForm.validate(mapping, request, errors);
			actSolicitud= actSolicitudDAO.findById(detAprSolAbaForm.getActSolicitud().getId());
			actSolicitud.setEstado("A");
			//actSolicitud.setCodBodega(detAprSolAbaForm.getCodBodega());
			actSolicitud.setMotivoRechazo(detAprSolAbaForm.getComentarioResolucion());
			//actSolicitud.setEstado("A");
			detAprSolAbaForm.setEstadoNombre("Aprobada");
			detAprSolAbaForm.setEstado("A");
			//mail.notificarEmail("blanca.fifi@hotmail.com","prueba de envio", "prestamo");
			//setCodPropietario(detalleSolPrestamoForm.getCodPropietario());
			msgText = emailer.aprobacionSolicitudMail("solicitud de abastecimiento" , actSolicitud.getId().getCodSolicitud().toString() , 
			actSolicitud.getFecha_creacion(), "solicitud de abastecimiento",actSolicitud.getComentario(),detAprSolAbaForm.getComentarioResolucion(),"S","");
			usuarioDto = usuarioServices.getUsuarioDto(detAprSolAbaForm.getCodSolicitante().toString());
			email.sendEmail("UCA-SALDEI: Resolucion de Solicitud de Abastecimiento", "saldei@uca.edu.sv", new String[]{usuarioDto.getEmail()}, null, msgText, null);	
			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("detAprDesc.mensaje.exito", new ActionError("detAprDesc.mensaje.exito.aprobada"));
			this.addErrors(request, errors);
			
			}
			else{
				this.addErrors(request, errors);
				setFLAG_UPDATE(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		} 	
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}
	
	public ActionForward rechazar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data,data2;
		//ConfigEmail mail = new ConfigEmail();
		DetAprSolAbaForm detAprSolAbaForm = (DetAprSolAbaForm) form;
		//DetalleSolPrestamoForm prestamo = new DetalleSolPrestamoForm();
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		ParametroServices parametroServices = new ParametroServices();
		Email email = new Email();
		String msgText;
		UsuarioDto usuarioDto;
		UsuarioServices usuarioServices = new UsuarioServices();
		Util emailer = new Util();
		//Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		try {
			detAprSolAbaForm.validate(mapping, request, errors);
			
			actSolicitud= actSolicitudDAO.findById(detAprSolAbaForm.getActSolicitud().getId());
			//detalleSolDescActForm.getActDetSolicitud().getId().getActSolicitud().setEstado("R");
			//detalleSolDescActForm.getActDetSolicitud().getId().getActSolicitud().setMotivoRechazo(detalleSolDescActForm.getComentarioResolucion());
			actSolicitud.setEstado("R");
			actSolicitud.setMotivoRechazo(detAprSolAbaForm.getComentarioResolucion());
			detAprSolAbaForm.setEstadoNombre("Rechazada");
			detAprSolAbaForm.setEstado("R");
			//mail.notificarEmail("blanca.fifi@hotmail.com","prueba de envio", "prestamo");
			//setCodPropietario(detalleSolPrestamoForm.getCodPropietario());
			msgText = emailer.rechazoSolicitudMail("solicitud de abastecimiento" , actSolicitud.getId().getCodSolicitud().toString(), actSolicitud.getFecha_creacion(),actSolicitud.getComentario(),detAprSolAbaForm.getComentarioResolucion());	
			usuarioDto = usuarioServices.getUsuarioDto(detAprSolAbaForm.getCodSolicitante());
			email.sendEmail("UCA-SALDEI: Resolucion de Solicitud de Abastecimiento", "saldei@uca.edu.sv", new String[]{usuarioDto.getEmail()}, null, msgText, null);	
			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("detAprDesc.mensaje.exito", new ActionError("detAprDesc.mensaje.exito.rechazada"));
			this.addErrors(request, errors);
			} catch (Exception e) {
			e.printStackTrace();			
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		} 	
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}
	
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {		
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data,data2;
		
		
		DetAprSolAbaForm detAprSolAbaForm = (DetAprSolAbaForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		ActSolicitud actSolicitud = new ActSolicitud();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		
		try {
			if(!detAprSolAbaForm.getEstado().equals("E")){
				actSolicitud = actSolicitudDAO.findById(detAprSolAbaForm.getActDetSolicitud().getId().getActSolicitud().getId());
				if(detAprSolAbaForm.getEstado().equals("R")){
					if(actSolicitud.getMotivoRechazo()==null)
						detAprSolAbaForm.setComentarioResolucion("");
					else
						detAprSolAbaForm.setComentarioResolucion(actSolicitud.getMotivoRechazo());
					}
				else{
					InvBodega invBodega = new InvBodega();
					InvBodegaDAO invBodegaDAO = new InvBodegaDAO();
					invBodega = invBodegaDAO.findById(actSolicitud.getCodBodega());
					detAprSolAbaForm.setCodBodega(String.valueOf(actSolicitud.getCodBodega()));
					detAprSolAbaForm.setBodegaDes(invBodega.getNombre());
				}
			}
			
			data = actDetSolicitudDAO.AbastecimientoAprDetFind(detAprSolAbaForm.getCodSolicitud().toString());
			request.setAttribute("listAprDetAba", data);
		    
			if(!isFLAG_UPDATE()){
				detAprSolAbaForm.reset(mapping, request);
			}else{
				setFLAG_UPDATE(false);
			}
			//detallePptoForm.setCodPresupuesto(codppto);
			
		} catch (Exception e) {
			e.printStackTrace();			
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		} 	
		return mapping.findForward(target);
	}
	public ActionForward anular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;		
		String msgText;
		UsuarioServices usuarioServices = new UsuarioServices();
		UsuarioDto usuarioDto;
		Email email = new Email();		
		DetAprSolAbaForm detAprSolAbaForm = (DetAprSolAbaForm) form;
		ActSolicitud actSolicitud;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActionErrors errors = new ActionErrors();
		Util emailer = new Util();
		try {
			actSolicitud= actSolicitudDAO.findById(detAprSolAbaForm.getActSolicitud().getId());
			actSolicitud.setEstado("B");
			detAprSolAbaForm.setEstado("B");
			msgText = emailer.anulacionSolicitudMail("solicitud de abastecimiento" , actSolicitud.getId().getCodSolicitud().toString(), actSolicitud.getFecha_creacion(), "solicitud de abastecimiento");
			usuarioDto = usuarioServices.getUsuarioDto(actSolicitud.getCodSolicitante());
			email.sendEmail("UCA-SALDEI: Anulacion de Solicitud de Abastecimiento", "saldei@uca.edu.sv", new String[]{usuarioDto.getEmail()}, null, msgText, null);
			actSolicitudDAO.merge(actSolicitud);
			HibernateSessionFactory.getSession().beginTransaction().commit();
			errors.add("detAprDesc.mensaje.exito", new ActionError("detAprDesc.mensaje.exito.anular"));
			this.addErrors(request, errors);
		} catch (Exception e) {
			e.printStackTrace();			
			// TODO: handle exception
		}finally{
			HibernateSessionFactory.getSession().close();
		} 	
		this.find(mapping, form, request, response);
		return mapping.findForward(target);
	}
}