/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.saldei.web.action.activos;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.activos.ActSolicitudDAO;
import com.saldei.util.commons.Querys;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.action.seguridad.GenerarReportesAction;
import com.saldei.web.form.activos.RepAsigActualesForm;
import com.saldei.web.services.administracion.RptCommonServices;

/**
 * MyEclipse Struts Creation date: 06-26-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/repAsigActualesAction" name="repAsigActualesForm"
 *                input="/html/activos/repAsigActuales.jsp" parameter="accion"
 *                scope="request" validate="true"
 * @struts.action-forward name="success"
 *                        path="/html/activos/repAsigActuales.jsp"
 */
public class RepAsigActualesAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	Querys utilQuerys = new Querys();
	RptCommonServices commonservices = new RptCommonServices();

	/**
	 * Inicializa los campos de la pantalla de Reporte de Laboratorio
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Usuario usr = (Usuario) request.getSession()
					.getAttribute("usuario");
			/** Verficica si el Usuario esta en session es valido */
			if (usr == null)
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
			// return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}

	public ActionForward rptAsignaciones(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, Error {
		GenerarReportesAction gRpt = new GenerarReportesAction();
		RepAsigActualesForm asigActualesForm = (RepAsigActualesForm) form;
		String formato = asigActualesForm.getFormato();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ActSolicitudDAO actSolicitudDAO = new ActSolicitudDAO();
		String usuario = "";
		String codUsu = user.getIdUsuario();
		List data;
		String queryString = "select a.cod_recurso as act_activo_cod_recurso, a.cod_activo  as act_activo_cod_activo, au.cod_responsable as act_unidad_cod_responsable,"
				+ "u.primer_nom || ' ' || u.primer_ape as usuario,r.nombre AS nombreRecurso,"
				+ "au.descripcion AS descripcion "
				+ "from activos.act_unidad au, activos.act_activo a, inventario.inv_recurso r, seguridad.usuario_dei ud, seguridad.usuario u "
				+ "where au.cod_unidad = a.cod_unidad and a.cod_recurso = r.cod_recurso and au.cod_responsable = ud.codigo_empleado "
				+ "and ud.codigo_empleado = u.id_usuario  and au.cod_responsable='"
				+ codUsu
				+ "' "
				+ " and (a.estado = 'A' or a.estado = 'P') "
				+ "order by usuario, au.descripcion";
		data = actSolicitudDAO.esJefe(codUsu);
		if (user == null)
			usuario = "Sin Informacion";
		else
			usuario = user.getPrimerNom() + " " + user.getPrimerApe();
		String ruta;
		ruta = this.getServlet().getServletContext().getRealPath(
				"reportesJasper/asigActuales.jasper");
		if (!data.isEmpty()) {
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("pUsuario", usuario);
			return gRpt.rptUsuario(mapping, form, request, response, formato,
					ruta, parameters);
		} else {
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("pQuery", queryString);
			parameters.put("pUsuario", usuario);
			return gRpt.rptUsuario(mapping, form, request, response, formato,
					ruta, parameters);
		}

	}

}