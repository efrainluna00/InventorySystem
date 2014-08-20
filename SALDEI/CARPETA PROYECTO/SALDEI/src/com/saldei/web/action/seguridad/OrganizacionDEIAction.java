package com.saldei.web.action.seguridad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.seguridad.OrgDeiDto;
import com.saldei.web.bean.seguridad.OrganizacionDeiDto;
import com.saldei.web.form.seguridad.OrganizacionDEIForm;
import com.saldei.web.services.seguridad.OrganizacionDEIServices;

/**
 * Acciones de la pantalla de Mantenimiento de la Organizacion DEI
 * @author WiRoCaRo 
 * @version 1.1
 */
public class OrganizacionDEIAction extends DispatchAction {	
	private OrganizacionDEIServices servOrgDei = new OrganizacionDEIServices();
	
	/**
	 * Inicializa los campos de la pantalla
	 * @param mapping   ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		this.cleanSession(form, mapping, request);
		List lstUser = servOrgDei.getUsers();
		request.getSession().setAttribute("usuarios", lstUser);
		return mapping.findForward("success");		
	}
	
	/**
	 * Obtiene los mapas de los Cargos que contienen el usuario
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;
			if(! servOrgDei.isNullUsers(orgForm)){
				ArrayList mapCargoUser    = servOrgDei.cargoUser(orgForm); 
				ArrayList mapCargo        = servOrgDei.cargo(orgForm);
				ArrayList mapCargoUserAll = servOrgDei.cargoUserAll(orgForm);
				request.getSession().setAttribute("MapCargoUser",    mapCargoUser);
				request.getSession().setAttribute("MapCargo",        mapCargo);
				request.getSession().setAttribute("MapCargoUserAll", mapCargoUserAll);
			}
			else{
				request.setAttribute("orgDEI", "vacio");
				this.cleanSession(form, mapping, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success"); 
	}

	/**
	 * Agrega un Item de un Mapa
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request   HttpServletRequest
	 * @param response  HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {			
			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;				
			ArrayList MapCargo = (ArrayList) request.getSession().getAttribute("MapCargo");
			ArrayList MapCargoUser = (ArrayList) request.getSession().getAttribute("MapCargoUser");
			int orden = new Integer(orgForm.getIdobt());
			OrganizacionDeiDto orgDto = null;
			for(int i=0; i<MapCargo.size(); i++){
				if(orden == (i+1)){
					orgDto = (OrganizacionDeiDto) MapCargo.get(i);
					OrgDeiDto dto = new OrgDeiDto();
					dto.setFechaInif(orgForm.getFechaIni());
					dto.setFechaFinf(orgForm.getFechaFin());
					dto.setDescripcionf(orgDto.getDescripcion());
					dto.setNombreCargof(orgDto.getNombreCargo());
					dto.setIdUsuariof(orgDto.getIdUsuario());
					dto.setCargof(orgDto.getcargo());
					dto.setAccionf("<a href='" + Constants.contextPath + "orgDEI.do?cmd=Remove&cargo= " + 
							dto.getCargof()+"&idUsuario= " + dto.getIdUsuariof() + "&fechaIni= " + dto.getFechaInif()+ "&fechaFin=" + dto.getFechaFinf() + "" +
							"&nombreCargo= "+ dto.getNombreCargof()+"&descripcion="+dto.getDescripcionf()+"'>Remover Cargo</a>");
					MapCargoUser.add(dto);
				}
			}
			request.getSession().setAttribute("MapCargo", MapCargo);
			request.getSession().setAttribute("MapCargoUser", MapCargoUser);
//			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;
//			ArrayList mapCargo                = (ArrayList) request.getSession().getAttribute("MapCargo");
//			ArrayList mapCargoUser			= (ArrayList) request.getSession().getAttribute("MapCargoUser");
//			String strCargo		        = (String) orgForm.getCargo();
//			String strNombreCargo		= (String) orgForm.getNombreCargo();
//			String strDescripcion		= (String) orgForm.getDescripcion();
//			if (strCargo != null && !strCargo.equals(""))
//			{ 
//				OrganizacionDeiDto orgDei  = new OrganizacionDeiDto();
//				orgDei.setcargo(strCargo); 
//				orgDei.setNombreCargo(strNombreCargo);    
//				orgDei.setDescripcion(strDescripcion);
//				orgDei.setIdUsuario(orgForm.getUsr());
//				servOrgDei.getAccion("Remover Cargo", orgDei, "Remove");
//				if (mapCargo != null &&  mapCargo.size() > 0){					
//					mapCargo.remove(orgDei.getcargo().trim());
//				}				
//				request.getSession().setAttribute("MapCargo", mapCargo);
//				request.getSession().setAttribute("MapCargoUser", mapCargoUser);				
//			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * Remueve un Item de un Mapa
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request   HttpServletRequest
	 * @param response  HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward Remove(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;
			ArrayList MapCargo = (ArrayList) request.getSession().getAttribute("MapCargoUser");			
			ArrayList newList = new ArrayList();
			OrgDeiDto orgDto = null;
			for(Iterator iter= MapCargo.iterator(); iter.hasNext();){
				orgDto = (OrgDeiDto) iter.next();
				if(orgDto.getNombreCargof().trim().equals(orgForm.getNombreCargo().trim()) && orgDto.getFechaInif().trim().equals(orgForm.getFechaIni().trim()) && orgDto.getFechaFinf().trim().equals(orgForm.getFechaFin().trim())
						&& orgDto.getDescripcionf().trim().equals(orgForm.getDescripcion().trim())){}
				else
					newList.add(orgDto);
				
			}
			request.getSession().setAttribute("MapCargoUser", newList);
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		try {
//			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;
//			ArrayList mapCargo                = (ArrayList) request.getSession().getAttribute("MapCargo");
//			ArrayList mapCargoUser			= (ArrayList) request.getSession().getAttribute("MapCargoUser");
//			String strCargo		        = (String) orgForm.getCargo();
//			String strNombreCargo		= (String) orgForm.getNombreCargo();
//			String strDescripcion		= (String) orgForm.getDescripcion();
//			if (strCargo != null && !strCargo.equals(""))
//			{
//				OrganizacionDeiDto orgDei  = new OrganizacionDeiDto();
//				orgDei.setcargo(strCargo);
//				orgDei.setFechaIni(orgForm.getFechaIni());
//				orgDei.setFechaFin(orgForm.getFechaFin());
//				orgDei.setNombreCargo(strNombreCargo);    
//				orgDei.setDescripcion(strDescripcion);
//				orgDei.setIdUsuario(orgForm.getUsr());
//				servOrgDei.getAccion("Asignar Cargo", orgDei, "Add");
//				if (mapCargoUser != null &&  mapCargoUser.size() > 0){
//					
//				}				
//				mapCargo.add(orgDei);
//				request.getSession().setAttribute("MapCargo", mapCargo);
//				request.getSession().setAttribute("MapCargoUser", mapCargoUser);
//			} 			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return mapping.findForward("success");
	}

	/**
	 * Elimina las opciones de un Perfil 
	 * @param mapping  ActionMapping
	 * @param form     ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;
			ArrayList mapCargoUser    = (ArrayList) request.getSession().getAttribute("MapCargoUser");
			boolean flag = servOrgDei.saveAll(mapCargoUser, orgForm.getUsr());
			if(flag)
				request.setAttribute("orgDEI", "exito");
			else
				request.setAttribute("orgDEI", "errorSave");
//			ArrayList mapCargoUserAll = (ArrayList) request.getSession().getAttribute("MapCargoUserAll");
//			servOrgDei.RemoverAllCargo(mapCargoUserAll);
//			if (mapCargoUser  != null && mapCargoUser.size() > 0 ){
//				for(Iterator i = mapCargoUser.keySet().iterator(); i.hasNext();){
//				    String key 					= (String)i.next();
//					OrganizacionDeiDto orgDto 	= (OrganizacionDeiDto) mapCargoUser.get(key);
//					orgDto.setEstado("A");
//					if (mapCargoUserAll.containsKey(key))
//						servOrgDei.actualizaCargo(orgDto);
//					else
//						servOrgDei.saveCargo(orgDto);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("orgDEI", "errorSave");
		}
		return mapping.findForward("success");		
	}

	/**
	 * Limpia las Sessiones de el formulario
	 * @param form     Objeto del tipo ActionForm
	 * @param mapping  Objeto del tipo ActionMapping
	 * @param request  Objeto del tipo HttpServletRequest
	 */
	public void cleanSession(ActionForm form, ActionMapping mapping, HttpServletRequest request){
		OrganizacionDEIForm orgForm = (OrganizacionDEIForm) form;	
		orgForm.setUsr(Constants.Seleccione);
		request.getSession().setAttribute("MapCargoUser",    null);
		request.getSession().setAttribute("MapCargo", 		 null);
		request.getSession().setAttribute("MapCargoUserAll", null);
		request.getSession().setAttribute("usr",             null);
	}
		
	
		

}
