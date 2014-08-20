package com.saldei.web.action.seguridad;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.action.ActionGeneral;
import com.saldei.web.bean.seguridad.PerfilDto;
import com.saldei.web.form.seguridad.PerfilForm;
import com.saldei.web.services.seguridad.PerfilServices;

public class PerfilAction extends DispatchAction{

private PerfilServices services = new PerfilServices();
ActionGeneral   actionGnl    = new ActionGeneral();

	/**
	 * Inicializa los Valores de El Formulario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_first_module);//
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}
	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}

	/**
	 * Cambia los valores de los botones para el estado de Desactivacion y Limpia los Mapas de los usuarios
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward remover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		actionGnl.remover(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_second_module);//
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}

	/**
	 * Guarda un nuevo Perfil 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		PerfilForm perfilForm = (PerfilForm)form;		
		if(services.isNullMtoPerfilForm(perfilForm))
			request.setAttribute("ccMsg", "vacio");
		else{
			String codigoHid = "";
			codigoHid= perfilForm.getCodigoHid();
			if( codigoHid == null ||codigoHid.equals("null") || codigoHid.equals(""))
				codigoHid="";					
			if(!codigoHid.equals(""))
				request.setAttribute("ccMsg","copia");
			else{
				boolean flag = services.save(perfilForm);
				if(flag)
					request.setAttribute("ccMsg", "exito");
				else
					request.setAttribute("ccMsg", "errorSave");				
			}
		}	
		request.setAttribute("PerfilForm", perfilForm);
		this.cleanSession(mapping, request);
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_first_module);//
		return findActualizar(mapping, form, request, response);		
	}	
	
	/**
	 * Actualiza  un Perfil 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		PerfilForm perfilForm = (PerfilForm) form;	
		if(!services.isUpdatableMtoPerfilForm(perfilForm))
		{
			request.setAttribute("ccMsg", "nocambio");			
		}
		else{			
			boolean flag = services.update(perfilForm, false);
			if(flag){
					request.setAttribute("ccMsg", "exitoUpd");					
			}
			else
				request.setAttribute("ccMsg", "errorUpdate");
		}
		Map<String, PerfilDto> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		PerfilForm perfilFormSession = (PerfilForm)request.getSession().getAttribute("frmBusqueda");
		if(listActives!= null && listActives.size()>0 || listInactives!=null && listInactives.size()>0){
			listActives = services.findActives(perfilFormSession,1);
			listInactives = services.findInactives(perfilFormSession,1);
			request.getSession().setAttribute("ccListActives", listActives);
			request.getSession().setAttribute("ccListInactives", listInactives);
		}
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_first_module);//
		request.setAttribute("PerfilForm", perfilForm);
		return findActualizar(mapping, form, request, response);		 
	}
	
	public ActionForward findActualizar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		PerfilForm perfilForm = (PerfilForm) form;
		request.getSession().setAttribute("frmBusqueda",perfilForm);
		Map listActives = services.findActives(perfilForm,1);
		Map listInactives = services.findInactives(perfilForm,1);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);
		//modificar(mapping,form,request,response);
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_first_module);//
		return mapping.findForward("success");
	}
	
	public ActionForward findRemover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		PerfilForm perfilForm = (PerfilForm) form;
		request.getSession().setAttribute("frmBusqueda",perfilForm);
		Map listActives = services.findActives(perfilForm,2);
		Map listInactives = services.findInactives(perfilForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);		
		return mapping.findForward("success");
	}

	public ActionForward getDataToPutForm(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		PerfilForm perfilForm = (PerfilForm) form;
		//request.getParameter("");
		request.setAttribute("codigoHid",perfilForm.getCodigoHid());
		request.setAttribute("codigo", perfilForm.getCodigo());
		request.setAttribute("nombre", perfilForm.getNombre());
		request.setAttribute("desc", perfilForm.getDesc());
		request.setAttribute("estado", perfilForm.getEstado());
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_first_module);//
		return mapping.findForward("success");
	}	

	@SuppressWarnings("unchecked")
	public ActionForward ActivarPerfil(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		PerfilDto dto = new PerfilDto();
		PerfilForm perfilForm = (PerfilForm) form;		
		Map<String, PerfilDto> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		dto = (PerfilDto)listInactives.get(perfilForm.getCodigoHid());
		dto.setEstado("A");		
		dto.setAccion( "<a href='" + Constants.contextPath + "perfil.do?cmd=desactivarPerfil&codigoHid=" + dto.getCodigo() + "'>Remover</a>");
		listActives.put(perfilForm.getCodigoHid(),dto);
		listInactives.remove(perfilForm.getCodigoHid());
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");
		return mapping.findForward("success");		
	}	

	@SuppressWarnings("unchecked")
	public ActionForward desactivarPerfil(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		PerfilDto dto = new PerfilDto();
		PerfilForm perfilForm = (PerfilForm) form;		
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map<String, PerfilDto> listInactives = (Map)request.getSession().getAttribute("ccListInactives");				
		dto = (PerfilDto)listActives.get(perfilForm.getCodigoHid());
		dto.setEstado("I");
		dto.setAccion("<a href='" + Constants.contextPath + "perfil.do?cmd=ActivarPerfil&codigoHid=" + dto.getCodigo() + "'>Activar</a>");
		listInactives.put(perfilForm.getCodigoHid(),dto);
		listActives.remove(perfilForm.getCodigoHid());	
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");		
		return mapping.findForward("success");		
	}

	public ActionForward cancelarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)	
	{
		PerfilForm perfilForm = (PerfilForm) form;
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		listActives.clear();
		listInactives.clear();
		listActives = services.findActives(perfilForm,2);
		listInactives=services.findInactives(perfilForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);
		remover(mapping,form,request,response);
		request.getSession().setAttribute("modulesPerfil", Constants.legend_second_module);//
		return this.findRemover(mapping, form, request, response);
	}
public ActionForward guardarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)	
{
	request.setAttribute("codigo", "");
	request.setAttribute("nombre", "");
	request.setAttribute("desc", "");
	request.setAttribute("estado", "");
	Map listActives = (Map)request.getSession().getAttribute("ccListActives");
	Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
	if(listActives.size() == 0 && listInactives.size() == 0)
		return mapping.findForward("success");
	if(listActives !=null && listActives.size()>0)
		this.actualizarDatos(listActives,1);
	if(listInactives!=null && listInactives.size()>0)
		this.actualizarDatos(listInactives,2);
	request.setAttribute("ccMsg", "exitoAll");		
	return mapping.findForward("success");
	
}
	private void actualizarDatos(Map listPerfiles,int operacion) {
		String estado="";
		switch(operacion){
			case 1:
				estado="A";
				break;
			case 2:
				estado="I";
				break;
		}		
		try{
			for(Iterator i = listPerfiles.keySet().iterator(); i.hasNext();){
				String key = (String)i.next();
				PerfilDto perfilDTO = (PerfilDto)listPerfiles.get(key);
				actualizarEstado(perfilDTO,estado);				
			}
		}
		catch(Exception exception)
		{			
			exception.printStackTrace();
		}		
	}
	private void actualizarEstado(PerfilDto perfilDTO, String estado)
	{
		PerfilForm perfilForm=new PerfilForm();
		perfilForm.setCodigo(perfilDTO.getCodigo());
		perfilForm.setCodigoHid(perfilDTO.getCodigo());
		perfilForm.setNombre(perfilDTO.getNombre());
		perfilForm.setDesc(perfilDTO.getDesc());
		perfilForm.setEstado(estado);
		services.update(perfilForm, true);		
	}

	/**
	 * Limpia los Mapas del Usuario 
	 * @param mapping Objeto tipo ActionMapping
	 * @param request Objeto tipo HttpServletRequest
	 */
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().setAttribute("ccListActives",   null);
		request.getSession().setAttribute("ccListInactives", null);
		request.getSession().setAttribute("findBy",          null);
	
	}
}