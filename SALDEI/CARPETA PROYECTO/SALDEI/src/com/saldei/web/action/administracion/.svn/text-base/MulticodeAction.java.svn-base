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
import com.saldei.web.bean.administracion.MulticodeDTO;
import com.saldei.web.form.administracion.MulticodeForm;
import com.saldei.web.services.administracion.MulticodeServices;


public class MulticodeAction extends DispatchAction {
	
	private MulticodeServices services = new	MulticodeServices();
	private Usuario usuarioAudit = null;
	
	/**
	 * Inicializa los campos de la pantalla de Multicode
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
	 * Elimina un codigo Multiple
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		MulticodeForm multicodeForm = (MulticodeForm) form;
		request.setAttribute("multicodeFormJsp",multicodeForm);
		if(multicodeForm.getCodigoHid().equals("") || multicodeForm.getNombre().equals("") || multicodeForm.getTipos().equals("Seleccione")){
			request.setAttribute("ccMsg","vacioUpdate");
			return mapping.findForward("success");
		}else{
			usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
			boolean flag = services.delete(multicodeForm, usuarioAudit);
			if(flag)
				request.setAttribute("ccMsg", "exitoUpd");
			else
				request.setAttribute("ccMsg", "errorUpdate");
		}
		request.getSession().removeAttribute("ccListActives");
		request.getSession().removeAttribute("ccListInactives");
		return mapping.findForward("success");
		//return findActualizar(mapping, form, request, response);
	}
	
	/**
	 * Limpia los campos de la pantalla de Multicode
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
	 * Modifica los botones
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
		request.getSession().setAttribute("modulesMulticode", Constants.legend_first_module);//
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		request.getSession().setAttribute("findBy", null);	
		return mapping.findForward("success");
	}
	
	/**
	 * pone los botones de Remover
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
		request.getSession().setAttribute("modulesMulticode", Constants.legend_second_module);//
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		request.getSession().setAttribute("findBy", null);	
		return mapping.findForward("success");
	}
	
	/**
	 * Hace una busqueda de Modificar 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward findActualizar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;
		request.getSession().setAttribute("frmBusqueda",tipoMulticodeForm);
		Map listActives = services.findActives(tipoMulticodeForm,1);
		Map listInactives = services.findInactives(tipoMulticodeForm,1);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);		
		return mapping.findForward("success");
	}
	
	/**
	 * Hace un busqueda para remover
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward findRemover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;
		request.getSession().setAttribute("frmBusqueda",tipoMulticodeForm);
		Map listActives = services.findActives(tipoMulticodeForm,2);
		Map listInactives = services.findInactives(tipoMulticodeForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);		
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda un nuevo registro de Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;
		request.setAttribute("multicodeFormJsp", tipoMulticodeForm);
		if(tipoMulticodeForm.getNombre().equals("") || tipoMulticodeForm.getTipos().equals("Seleccione")){
			request.setAttribute("ccMsg","vacio");
			return mapping.findForward("success");
		}
		String codigoHid = "";
		codigoHid= tipoMulticodeForm.getCodigoHid();
		if( codigoHid == null ||codigoHid.equals("null") || codigoHid.equals(""))
			codigoHid="";					
		if(!codigoHid.equals(""))
			request.setAttribute("ccMsg","copia");
			else{
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
		request.getSession().setAttribute("ccListActives", null);
		request.getSession().setAttribute("ccListInactives", null);
		modificar(mapping,form,request,response);				
		return findActualizar(mapping, form, request, response);		
	}
	
	/**
	 * Actualiza un registro de MUlticode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MulticodeForm multicodeForm = (MulticodeForm) form;
		request.setAttribute("multicodeFormJsp", multicodeForm);
		if(multicodeForm.getCodigoHid().equals("") || multicodeForm.getNombre().equals("") || multicodeForm.getTipos().equals("Seleccione")){
			request.setAttribute("ccMsg","vacioUpdate");
			return mapping.findForward("success");
		}
		usuarioAudit = (Usuario) request.getSession().getAttribute("usuario");
		boolean flag = services.update(multicodeForm, usuarioAudit);
			if(flag){
					request.setAttribute("ccMsg", "exitoUpd");
					//request.setAttribute("codigo", "");
					//request.setAttribute("nombre", "");
					//request.setAttribute("desc", "");
					//request.setAttribute("estado", "");					
			}
			else
				request.setAttribute("ccMsg", "errorUpdate");
		
		Map<String, MulticodeDTO> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
//		MtoTipoMulticode perfilFormSession = (MtoTipoMulticode)request.getSession().getAttribute("frmBusqueda");
		if(listActives!= null && listActives.size()>0 || listInactives!=null && listInactives.size()>0){
			listActives = services.findActives(multicodeForm,1);
			listInactives = services.findInactives(multicodeForm,1);
			request.getSession().setAttribute("ccListActives", listActives);
			request.getSession().setAttribute("ccListInactives", listInactives);
		}
		modificar(mapping,form,request,response);
		return findActualizar(mapping, form, request, response);
	}
	
	/**
	 * Activa un Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward activarMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		MulticodeDTO dto = new MulticodeDTO();
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;		
		Map<String, MulticodeDTO> listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");

		dto = (MulticodeDTO)listInactives.get(tipoMulticodeForm.getCodigoHid());
		dto.setEstado("A");
		dto.setAccion( "<a href='" + Constants.contextPath + "multicodeMto.do?cmd=desactivarMulticode&codigoHid=" + dto.getCodigo() + "'>Desactivar</a>");
		listActives.put(tipoMulticodeForm.getCodigoHid(),dto);
		listInactives.remove(tipoMulticodeForm.getCodigoHid());		
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");		
		return mapping.findForward("success");
		//dto.setAccion("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Recuperar Registro</a>");
	} 
	
	/**
	 * Desactiva un Multicode
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward desactivarMulticode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		MulticodeDTO dto = new MulticodeDTO();
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;		
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map<String, MulticodeDTO> listInactives = (Map)request.getSession().getAttribute("ccListInactives");				
		dto = (MulticodeDTO)listActives.get(tipoMulticodeForm.getCodigoHid());
		dto.setEstado("I");
		//dto.setAccion("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Modificar</a>");
	    dto.setAccion("<a href='" + Constants.contextPath + "multicodeMto.do?cmd=activarMulticode&codigoHid=" + dto.getCodigo() + "'>Activar</a>");
		listInactives.put(tipoMulticodeForm.getCodigoHid(),dto);
		listActives.remove(tipoMulticodeForm.getCodigoHid());		
		request.setAttribute("codigo", "");
		request.setAttribute("nombre", "");
		request.setAttribute("desc", "");
		request.setAttribute("estado", "");		
		return mapping.findForward("success");
		//dto.setAccion("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Recuperar Registro</a>");
	}

	/**
	 * Cancela los cambios realizados 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward cancelarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MulticodeForm tipoMulticodeForm = (MulticodeForm) form;
		Map listActives = (Map)request.getSession().getAttribute("ccListActives");
		Map listInactives = (Map)request.getSession().getAttribute("ccListInactives");
		listActives.clear();
		listInactives.clear();
		listActives = services.findActives(tipoMulticodeForm,2);
		listInactives=services.findInactives(tipoMulticodeForm,2);
		request.getSession().setAttribute("ccListActives", listActives);
		request.getSession().setAttribute("ccListInactives", listInactives);
		remover(mapping,form,request,response);
		return this.findRemover(mapping, form, request, response);
	}
	
	/**
	 * Guarda los cambios realizados 
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
		remover(mapping,form,request,response);
		return this.findRemover(mapping, form, request, response);
		
	}
	
	/**
	 * Actualiza lod Datos
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	private void actualizarDatos(Map listMulticode,int operacion, Usuario usuarioAudit) {		
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
			for(Iterator i = listMulticode.keySet().iterator(); i.hasNext();){
				String key = (String)i.next();
				MulticodeDTO multicodeDTO = (MulticodeDTO)listMulticode.get(key);
				actualizarEstado(multicodeDTO,estado, usuarioAudit);
			}
		}
		catch(Exception exception)
		{			
			exception.printStackTrace();
		}		
	}
	
	/**
	 * Actualiza el estado 
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	 private void actualizarEstado(MulticodeDTO multicodeDTO, String estado, Usuario usuarioAudit){
		MulticodeForm multicodeForm=new MulticodeForm();
		multicodeForm.setCodigo(multicodeDTO.getCodigo());
		multicodeForm.setCodigoHid(multicodeDTO.getCodigo());
		multicodeForm.setNombre(multicodeDTO.getNombre());
		multicodeForm.setDesc(multicodeDTO.getDesc());
		multicodeForm.setTipos(multicodeDTO.getTipos());		
		multicodeForm.setEstado(estado);		
		services.update(multicodeForm, usuarioAudit);		
	}
	
	/**
	 * Genera reporte de Multicode
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