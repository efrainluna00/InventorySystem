package com.saldei.web.action.administracion;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.web.bean.administracion.TipoMulticodeDto;
import com.saldei.web.form.administracion.TipoMulticodeForm;
import com.saldei.web.services.administracion.TipoMulticodeServices;

public class TipoMulticodeAction extends DispatchAction {
	
	private TipoMulticodeServices services = new	TipoMulticodeServices();
	private Usuario usuarioAudit = null;
	
	/**
	 * Inicializa los campos de la pantalla de Tipo Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		request.getSession().setAttribute("findBy", null);
		modificar(mapping,form,request,response);	
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los campos de la pantalla de Tipo Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward limpiar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		request.getSession().setAttribute("findBy", null);			
		return mapping.findForward("success");
	}
	
	/**
	 * Selecciona los Botones de Modificacion
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */		
	public ActionForward modificar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("btnSave",   "Guardar");
		request.getSession().setAttribute("btnUpdate", "Actualizar");
		request.getSession().setAttribute("btnFind",   "Mostrar");
		request.getSession().setAttribute("btnClean",  "Limpiar");
		request.getSession().setAttribute("btnRem",    "Activar");
		request.getSession().setAttribute("modules",   Constants.legend_first_module);
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		return mapping.findForward("success");
	}
	
	/**
	 * Elimina un Registro de Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;	
		request.setAttribute("tipoMulticodeFormJsp", tipoMulticodeForm);
		if(!services.isUpdatableMtoPerfilForm(tipoMulticodeForm)){
			request.setAttribute("ccMsg","vacio");
			return mapping.findForward("success");
		}else{
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			boolean flag = services.delete(tipoMulticodeForm, usuarioAudit);
			if(flag)
				request.setAttribute("ccMsg", "exitoUpd");
			else
				request.setAttribute("ccMsg", "errorUpdate");
		}
		return findActualizar(mapping, form, request, response);
	}
	
	/**
	 *  Selecciona los Botones de Activacion
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward remover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("btnSave",   "Guardar Cambios");
		request.getSession().setAttribute("btnUpdate", "Cancelar Cambios");
		request.getSession().setAttribute("btnFind",   "Mostrar");
		request.getSession().setAttribute("btnClean",  "Limpiar");
		request.getSession().setAttribute("btnRem",    "Modificar");
		request.getSession().setAttribute("modules",   Constants.legend_second_module);
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		return mapping.findForward("success");
	}
	
	/**
	 *  Selecciona los Botones de Activacion
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward findActualizar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;
		request.getSession().setAttribute("frmBusqueda",tipoMulticodeForm);
		Map listActives = services.findActives(tipoMulticodeForm,1);
		Map listInactives = services.findInactives(tipoMulticodeForm,1);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);	
		return mapping.findForward("success");
	}
	
	/**
	 *  Selecciona los Botones de Activacion
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward findRemover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;
		request.getSession().setAttribute("frmBusqueda",tipoMulticodeForm);
		Map listActives = services.findActives(tipoMulticodeForm,2);
		Map listInactives = services.findInactives(tipoMulticodeForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);
		return mapping.findForward("success");
	}
	
	/**
	 *   Guarda un nuevo Registro 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;	
		request.setAttribute("tipoMulticodeFormJsp", tipoMulticodeForm);
		if(services.isNullMtoPerfilForm(tipoMulticodeForm)){
			request.setAttribute("ccMsg", "vacio");
			return mapping.findForward("success");
		}			
			else{
				String codigoHid = "";
				codigoHid= tipoMulticodeForm.getCodigoHid();
				if( codigoHid == null ||codigoHid.equals("null") || codigoHid.equals(""))
					codigoHid="";					
				if(!codigoHid.equals("")){
					request.setAttribute("ccMsg","copia");
					return mapping.findForward("success");
				}else{
						usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
						boolean flag = services.save(tipoMulticodeForm, usuarioAudit);
						if(flag){
							request.setAttribute("ccMsg", "exito");
							request.setAttribute("codigoHid", "");
							request.setAttribute("codigo", "");
							request.setAttribute("nombre", "");
							request.setAttribute("desc", "");
							request.setAttribute("estado", "");
						}
						else{
							request.setAttribute("ccMsg", "errorSave");
							return mapping.findForward("success");
						}							
					}
		}		
		modificar(mapping,form,request,response);				
		return findActualizar(mapping, form, request, response);		
	}	
	
	/**
	 *  Actualiza un registro
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */		
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;
		request.setAttribute("tipoMulticodeFormJsp", tipoMulticodeForm);
		if(!services.isUpdatableMtoPerfilForm(tipoMulticodeForm))
		{
			request.setAttribute("ccMsg", "nocambio");
			request.setAttribute("codigo", "");
			request.setAttribute("nombre", "");
			request.setAttribute("desc", "");
			request.setAttribute("estado", "");
		}
		else{
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			boolean flag = services.update(tipoMulticodeForm, usuarioAudit);
			if(flag){
					request.setAttribute("ccMsg", "exitoUpd");					
			}
			else
				request.setAttribute("ccMsg", "errorUpdate");
		}
		Map<String, TipoMulticodeDto> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		if(listActives!= null && listActives.size()>0 || listInactives!=null && listInactives.size()>0){
			listActives = services.findActives(tipoMulticodeForm,1);
			listInactives = services.findInactives(tipoMulticodeForm,1);
			request.getSession().setAttribute("ccListActives", listActives);
			request.getSession().setAttribute("ccListInactives", listInactives);
		}
		modificar(mapping,form,request,response);
		return findActualizar(mapping, form, request, response);
	}
	
	/**
	 *  Obtiene los datos de un formulario
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward getDataToPutForm(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;
		request.setAttribute("codigoHid",tipoMulticodeForm.getCodigoHid());
		request.setAttribute("codigo", tipoMulticodeForm.getCodigo());
		request.setAttribute("nombre", tipoMulticodeForm.getNombre());
		request.setAttribute("desc", tipoMulticodeForm.getDesc());
		request.setAttribute("estado", tipoMulticodeForm.getEstado());		
		return mapping.findForward("success");
	}
	
	/**
	 *  Activa los Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward ActivarTipoMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		TipoMulticodeDto dto = new TipoMulticodeDto();
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;		
		Map<String, TipoMulticodeDto> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");				
		dto = (TipoMulticodeDto)listInactives.get(tipoMulticodeForm.getCodigoHid());
		dto.setEstado("A");	
		dto.setAccion( "<a href='" + Constants.contextPath + "tipoMulticodeMto.do?cmd=desactivarTipoMulticode&codigoHid=" + dto.getCodigo() + "'>Desactivar</a>");
		listActives.put(tipoMulticodeForm.getCodigoHid(),dto);
		listInactives.remove(tipoMulticodeForm.getCodigoHid());		
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");		
		return mapping.findForward("success");
	}
	
	/**
	 *  Desactiva los Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward desactivarTipoMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		TipoMulticodeDto dto = new TipoMulticodeDto();
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;		
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map<String, TipoMulticodeDto> listInactives = (Map)request.getSession().getAttribute("ccListInactives");				
		dto = (TipoMulticodeDto)listActives.get(tipoMulticodeForm.getCodigoHid());
		dto.setEstado("I");	
	    dto.setAccion("<a href='" + Constants.contextPath + "tipoMulticodeMto.do?cmd=ActivarTipoMulticode&codigoHid=" + dto.getCodigo() + "'>Activar</a>");
		listInactives.put(tipoMulticodeForm.getCodigoHid(),dto);
		listActives.remove(tipoMulticodeForm.getCodigoHid());		
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");		
		return mapping.findForward("success");
	}
	
	/**
	 *  Cancela los cambios
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward cancelarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		TipoMulticodeForm tipoMulticodeForm = (TipoMulticodeForm) form;
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		listActives.clear();
		listInactives.clear();
		listActives = services.findActives(tipoMulticodeForm,2);
		listInactives=services.findInactives(tipoMulticodeForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);		
		return this.findRemover(mapping, form, request, response);
	}
	
	/**
	 * Guarda los cambios 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward guardarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		if(listActives !=null && listActives.size()>0)
			this.actualizarDatos(listActives,1, usuarioAudit);
		if(listInactives!=null && listInactives.size()>0)
			this.actualizarDatos(listInactives,2, usuarioAudit);
		request.setAttribute("ccMsg", "exitoSaveAll");		
		return mapping.findForward("success");
		
	}
	
	/**
	 *  Actualiza los Datos
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */		
	private void actualizarDatos(Map listTipoMulticode,int operacion, Usuario usuarioAudit) {
		boolean actualizoData=true;
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
			for(Iterator i = listTipoMulticode.keySet().iterator(); i.hasNext();){
				String key = (String)i.next();
				TipoMulticodeDto tipoMulticodeDTO = (TipoMulticodeDto)listTipoMulticode.get(key);
				actualizarEstado(tipoMulticodeDTO,estado, usuarioAudit);				
			}
		}
		catch(Exception exception)
		{			
			exception.printStackTrace();
		}		
	}
	
	/**
	 *  Actualiza los estados
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	private void actualizarEstado(TipoMulticodeDto tipoMulticodeDTO, String estado, Usuario usuarioAudit){
		TipoMulticodeForm tipoMulticodeForm=new TipoMulticodeForm();
		tipoMulticodeForm.setCodigo(tipoMulticodeDTO.getCodigo());
		tipoMulticodeForm.setCodigoHid(tipoMulticodeDTO.getCodigo());
		tipoMulticodeForm.setNombre(tipoMulticodeDTO.getNombre());
		tipoMulticodeForm.setDesc(tipoMulticodeDTO.getDesc());
		tipoMulticodeForm.setEstado(estado);
		services.update(tipoMulticodeForm, usuarioAudit);
	}
	
	/**
	 *  Genera reporte de multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
}
