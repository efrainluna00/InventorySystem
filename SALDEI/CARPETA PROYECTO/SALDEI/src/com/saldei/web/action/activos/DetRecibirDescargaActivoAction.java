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

import com.saldei.hibernate.tables.activos.ActDetSolicitudDAO;
import com.saldei.hibernate.tables.activos.ActSolicitud;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.hibernate.tables.inventario.InvBodega;
import com.saldei.hibernate.tables.inventario.InvBodegaDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.action.BaseAction;
import com.saldei.web.form.activos.DetRecibirDescargaActivoForm;

/**
 * MyEclipse Struts Creation date: 08-04-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/detRecibirDescargaActivoAction"
 *                name="detRecibirDescargaActivoForm"
 *                input="/html/activos/detRecibirDescargaActivo.jsp"
 *                parameter="accion" scope="request"
 * @struts.action-forward name="updateFail"
 *                        path="/html/activos/detRecibirDescargaActivoAction.jsp"
 * @struts.action-forward name="back"
 *                        path="/recibirDescargaActivoAction.do?accion=Find"
 * @struts.action-forward name="success"
 *                        path="/html/activos/detRecibirDescargaActivo.jsp?accion="
 */
public class DetRecibirDescargaActivoAction extends BaseAction {
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
		DetRecibirDescargaActivoForm detRecibirDescargaActivoForm = (DetRecibirDescargaActivoForm) form;
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		ActSolicitud actSolicitud = new ActSolicitud();

		HttpSession session = request.getSession(true);
		String target = KEY_SUCCESS;

		try {
			detRecibirDescargaActivoForm.validate(mapping, request, errors);
			if (errors.isEmpty()) {
				detRecibirDescargaActivoForm.setActActivo(null);
				// actSolicitud =
				// actSolicitudDAO.findById(detRecibirDescargaActivoForm.getActDetSolicitud().getId().getActSolicitud());
				detRecibirDescargaActivoForm.setEstado("F");
				detRecibirDescargaActivoForm.setEstadoNombre("Finalizada");
				detRecibirDescargaActivoForm.getActDetSolicitud().getId()
						.getActSolicitud().setEstado("F");
				actSolicitudDAO.merge(detRecibirDescargaActivoForm
						.getActDetSolicitud().getId().getActSolicitud());
				actDetSolicitudDAO.trasladarBodega(detRecibirDescargaActivoForm
						.getActDetSolicitud().getId().getActSolicitud()
						.getCodBodega().toString(),
						detRecibirDescargaActivoForm.getActDetSolicitud()
								.getId().getActSolicitud().getId()
								.getCodSolicitud(),
						detRecibirDescargaActivoForm.getActDetSolicitud()
								.getId().getActSolicitud()
								.getComentarioTecnico());
				HibernateSessionFactory.getSession().beginTransaction()
						.commit();
				errors.add("detrecibirdesc.mensaje.exito", new ActionError(
						"detrecibirdesc.mensaje.exito.update"));
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
		List data, data2;

		DetRecibirDescargaActivoForm detRecibirDescargaActivoForm = (DetRecibirDescargaActivoForm) form;
		ActDetSolicitudDAO actDetSolicitudDAO = new ActDetSolicitudDAO();
		ActSolicitud actSolicitud = new ActSolicitud();
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();

		try {
			if (!detRecibirDescargaActivoForm.getEstado().equals("E")) {
				actSolicitud = actSolicitudDAO
						.findById(detRecibirDescargaActivoForm
								.getActDetSolicitud().getId().getActSolicitud()
								.getId());
				/*
				 * if(detRecibirDescargaActivoForm.getEstado().equals("R")){
				 * if(actSolicitud.getMotivoRechazo()==null)
				 * detRecibirDescargaActivoForm.setComentarioResolucion("");
				 * else
				 * detRecibirDescargaActivoForm.setComentarioResolucion(actSolicitud.getMotivoRechazo()); }
				 */
				// else{
				InvBodega invBodega = new InvBodega();
				InvBodegaDAO invBodegaDAO = new InvBodegaDAO();
				invBodega = invBodegaDAO.findById(actSolicitud.getCodBodega());
				// detRecibirDescargaActivoForm.setCodBodega(String.valueOf(actSolicitud.getCodBodega()));
				// detRecibirDescargaActivoForm.setBodegaDes(invBodega.getNombre());
				// }
			}

			data = actDetSolicitudDAO
					.findAllRecDetDesc(detRecibirDescargaActivoForm
							.getCodSolicitud());
			request.setAttribute("listDetDesc", data);

			if (!isFLAG_UPDATE()) {
				detRecibirDescargaActivoForm.reset(mapping, request);
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

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {

		// form.reset(mapping,request);
		DetRecibirDescargaActivoForm a = (DetRecibirDescargaActivoForm) form;

		request
				.setAttribute("flagBack", "lnk"
						+ String.valueOf(a.getActSolicitud().getId()
								.getCodSolicitud()));
		return mapping.findForward(KEY_BACK);
	}
}