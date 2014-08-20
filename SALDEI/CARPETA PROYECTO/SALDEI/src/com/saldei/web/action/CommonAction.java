/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.bean.Util;

public class CommonAction extends DispatchAction {
	
	/**
	 * Action que se utiliza para salir de la aplicacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().invalidate();
		return mapping.findForward("logout");
	}
	
	/**
	 * Action que se utiliza para ir a la pagina de Bienvenida
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward bienvenida(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String user = (String) request.getSession().getAttribute("user");
		try {
			String solicitudesPendientes = Util.obt_mov_pendientes("'"+user+"'");
			if(solicitudesPendientes != null && !solicitudesPendientes.equals(""))
				request.setAttribute("mensajeSolPendientes",solicitudesPendientes);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HibernateSessionFactory.getSession().close();
		return mapping.findForward("bienvenida");
	}

}//class
