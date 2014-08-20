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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.saldei.hibernate.tables.activos.ActCotizacion;
import com.saldei.hibernate.tables.activos.ActCotizacionDAO;
import com.saldei.hibernate.tables.activos.ActDetCotizacionDAO;
import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.SolicitudCompraDetForm;

/**
 * MyEclipse Struts Creation date: 06-25-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/solicitudCompraDetAction" name="solicitudCompraDetForm"
 *                input="/html/activos/solicitudCompraDet.jsp"
 *                parameter="accion" scope="request"
 * @struts.action-forward name="back" path="/solicitudCompraDet.do?accion=Find"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/solicitudCompraDet.jsp"
 * @struts.action-forward name="success"
 *                        path="/html/activos/solicitudCompraDet.jsp?accion="
 */
public class SolicitudCompraDetAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SolicitudCompraDetForm solicitudCompraDetForm = (SolicitudCompraDetForm) form;// TODO
																						// Auto-generated
																						// method
																						// stub
		ActDetSolicitudDAO actSolicitudDAO = new ActDetSolicitudDAO();
		ActDetCotizacionDAO actDetCotizacionDAO = new ActDetCotizacionDAO();
		ActCotizacionDAO actCotizacionDAO = new ActCotizacionDAO();
		ActCotizacion actCotizacion;

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;
		List data;

		try {
			actCotizacion = actCotizacionDAO.findByEstado(
					solicitudCompraDetForm.getCodSolicitud(),
					solicitudCompraDetForm.getTipoSolicitud());
			solicitudCompraDetForm.setActCotizacion(actCotizacion);
			data = actDetCotizacionDAO.findAll(String
					.valueOf(solicitudCompraDetForm.getActCotizacion().getId()
							.getCodCotizacion()), solicitudCompraDetForm
					.getCodSolicitud(), solicitudCompraDetForm
					.getTipoSolicitud());
			request.setAttribute("listCotDet", data);
			if (!isFLAG_UPDATE()) {
				// actCotizacion =
				// actCotizacionDAO.findById(cotizacionDetForm.getId().getActCotizacion().getId());
				// actProveedor = actCotizacion.getActProveedor();
				solicitudCompraDetForm.reset(mapping, request);
				// cotizacionDetForm.getId().setActCotizacion(actCotizacion);
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
}