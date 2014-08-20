package com.saldei.web.action;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.saldei.web.bean.LdvBean;

/**
 * Clase encargada de realizar la carga de los datos, utilizando un archivo XML,
 * para generar los LOV de forma dinamica
 * 
 * @param xmlArchivo:
 *            Nombre del archivo XML del cual se extraera la informacion para
 *            mostrar en pantalla, se pasa como parametro en el request
 */
public class LdvAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession(true);
		String xmlArchivo = request.getParameter("xmlArchivo");
		String multiple = request.getParameter("multiple");

		// ALmaceno el atributo en el request, para que luego pueda obtenerlo
		// durante la autogeneracion de las columnas
		request.setAttribute("xmlArchivo", xmlArchivo);
		request.setAttribute("multiple", multiple);

		// Se obtiene la direccion fisica del archivo XML
		String xmlPath = getServlet().getServletContext().getRealPath(
				"/xml/" + xmlArchivo + ".xml");

		// Guardamos la ubicacion para que pueda ser utilizada a lo largo de la
		// generacion de la tabla
		request.setAttribute("xmlPath", xmlPath);

		LdvBean ldvBean = new LdvBean(); // habilitamos el Query Bean
		try {
			request.setAttribute("map", ldvBean.ldvAllRows(xmlArchivo, xmlPath,
					request));
		} catch (SQLException e) {
			throw e;
		} finally {
			// ldvBean.close();
		}
		saveErrors(request, errors);
		return mapping.findForward("success");
	}
}