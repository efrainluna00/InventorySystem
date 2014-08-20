package com.saldei.web.action;

import javax.servlet.http.HttpServletRequest;

import com.saldei.util.commons.Constants;


public class ActionGeneral {
	
	/**
	 * Setea los valores de los botones a mostrar cuando se encuentra en el modo de Remover
	 * @param request  
	 */
	public void remover(HttpServletRequest request){
		request.getSession().setAttribute("btnSave",    "Guardar Cambios");
		request.getSession().setAttribute("btnUpdate",  "Cancelar Cambios");
		request.getSession().setAttribute("btnFind",    "Mostrar");
		request.getSession().setAttribute("btnClean",   "Limpiar");
		request.getSession().setAttribute("btnRem",     Constants.btnRem1);
	}
	
	/**
	 * Setea los valores de los botones a mostrar cuando se encuentra en el modo de Modificar
	 * @param request
	 */
	public void modificar(HttpServletRequest request) {		
		request.getSession().setAttribute("btnSave",    "Guardar");
		request.getSession().setAttribute("btnUpdate",  "Actualizar");
		request.getSession().setAttribute("btnFind",    "Mostrar");
		request.getSession().setAttribute("btnClean",   "Limpiar");
		request.getSession().setAttribute("btnRem",     Constants.btnRem2);
	}
	/*public ActionForward validacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {			
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr == null)
				return mapping.findForward("success");	
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}*/
	
}
