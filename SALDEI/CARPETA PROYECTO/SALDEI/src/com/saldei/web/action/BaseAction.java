package com.saldei.web.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

public abstract class BaseAction extends LookupDispatchAction {
	public static final String KEY_RESET = "reset";
	public static final String KEY_CANCELAR = "cancel";
	public static final String KEY_BACK = "back";
	public static final String KEY_SUCCESS = "success";
	public static final String KEY_INICIO = "success";
	public static final String KEY_ENVIAR_SOLICITUD = "enviada";
	public static final String KEY_APROBAR_SOLICITUD = "aprobar";
	private boolean FLAG_UPDATE = false;

	/**
	 * @return the fLAG_UPDATE
	 */
	public boolean isFLAG_UPDATE() {
		return FLAG_UPDATE;
	}

	/**
	 * @param flag_update
	 *            the fLAG_UPDATE to set
	 */
	public void setFLAG_UPDATE(boolean flag_update) {
		FLAG_UPDATE = flag_update;
	}

	protected Map getKeyMethodMap() {
		HashMap map = new HashMap();

		/*
		 * <html:submit property="accion" styleId="enviarSolicitud">
		 * <bean:message key="opc.enviarSolicitud" />
		 */

		map.put("opc.insert", "insert");
		map.put("opc.update", "update");
		map.put("opc.delete", "delete");
		map.put("opc.cancel", "find");
		map.put("opc.find", "find");
		map.put("opc.back", "back");
		map.put("opc.byPass", "byPass");
		map.put("opc.enviarSolicitud", "enviarSolicitud");

		return map;
	}

	@SuppressWarnings("deprecation")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		ActionForward forward = update(mapping, form, request, response,
				errors, messages);
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
		}
		return forward;
	}

	public abstract ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionErrors errors,
			ActionMessages messages) throws Exception, ServletException;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		ActionForward forward = insert(mapping, form, request, response,
				errors, messages);
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
		}
		return forward;
	}

	public abstract ActionForward insert(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionErrors errors,
			ActionMessages messages) throws Exception, ServletException;

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		ActionForward forward = delete(mapping, form, request, response,
				errors, messages);
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
		}
		return forward;
	}

	public abstract ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionErrors errors,
			ActionMessages messages) throws Exception, ServletException;

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		return mapping.findForward(KEY_CANCELAR);
	}

	public ActionForward reset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		form.reset(mapping, request);
		return mapping.findForward(KEY_RESET);
	}

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// form.reset(mapping,request);
		return mapping.findForward(KEY_BACK);
	}

	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException {
		// form.reset(mapping,request);
		return mapping.findForward(KEY_BACK);
	}
}
