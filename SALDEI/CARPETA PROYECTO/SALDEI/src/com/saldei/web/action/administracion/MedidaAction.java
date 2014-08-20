package com.saldei.web.action.administracion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.TipoMedida;
import com.saldei.util.commons.Constants;
import com.saldei.web.bean.administracion.MedidaDto;
import com.saldei.web.bean.administracion.TipoMedidaDto;
import com.saldei.web.services.administracion.MedidaServices;
import com.saldei.web.form.administracion.MedidaForm;

public class MedidaAction extends DispatchAction{

	private MedidaServices services = new MedidaServices();	
		
	/**
	 * Inicializa los campos de la pantalla de Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */	
	 public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("listMedidaActivas");
		request.getSession().removeAttribute("listMedidaInactivas");
		services.changeButtons(request);		
		return mapping.findForward("success");
	}
		
	/**
	 * Elimina un Registro de Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			MedidaForm matform = (MedidaForm) form;
			request.setAttribute("medidaFormJsp", matform);
			if(matform.getCodigoHidden().equals("")  || matform.getNombre().equals("") || matform.getAbrev().equals("") 
					|| matform.getTipo().equals("Seleccione")){
				request.setAttribute("medMsg", "vacioUpdate");
				return mapping.findForward("success");
			}else{
				boolean flag = services.delete(matform);
				if(flag)
					request.setAttribute("medMsg", "exitoUpdate");
				else{
					request.setAttribute("medMsg", "errorUpdate");
					return mapping.findForward("success");
				}	
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("medMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Limpia los Mapas de la Pantalla
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward limpiar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("listMedidaActivas");
		request.getSession().removeAttribute("listMedidaInactivas");
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda un nuevo Registro de Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			MedidaForm medform = (MedidaForm) form;
			request.setAttribute("medidaFormJsp", medform);
			boolean action = services.knowAction(request);
			if(!action){
				if(!services.isNullMedidaForm(medform)){
					request.setAttribute("medMsg", "vacio");
					return mapping.findForward("success");
				}					
				else{
					boolean flag = services.save(medform);
					if(flag)
						request.setAttribute("medMsg", "exitoSave");
					else{
						request.setAttribute("medMsg", "errorSave");
						return mapping.findForward("success");
					}						
				}	
			}else{
				ArrayList<TipoMedidaDto> listActivo = (ArrayList) request.getSession().getAttribute("listMedidaActivas");
				ArrayList<TipoMedidaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listMedidaInactivas");
				if(listActivo == null && listInactivo == null)
					request.setAttribute("medMsg","saveAllNull");
				else{
					services.updateAll(listActivo, listInactivo);
					request.setAttribute("medMsg","saveAllExito");
				}
			}	
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("medMsg", "error");
		}		
		return find(mapping, form, request, response);
	}
	
	/**
	 * Cambio la acción o modo de ejecutar
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("listMedidaActivas",null);
			request.getSession().setAttribute("listMedidaInactivas",null);
			String accion = (String) request.getSession().getAttribute("buttonsExtMed");
			if(accion == null)
				request.getSession().setAttribute("buttonsExtMed", "secondButtonMed");
			else
				if(accion.equals("firstButtonMed"))
					request.getSession().setAttribute("buttonsExtMed", "secondButtonMed");
				else
					request.getSession().setAttribute("buttonsExtMed", "firstButtonMed");
			services.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			request.setAttribute("medMsg", "error");
			return mapping.findForward("success");
			//return find(mapping, form, request, response);			
		}		
	}
	
	/**
	 * Realiza una Busqueda de las medidas segun los parametros ingresados
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			MedidaForm mtip = (MedidaForm) form;
			ArrayList[] listas = null;
			boolean action = services.knowAction(request);
			if(!action)
				listas = services.find(mtip, true);
			else
				listas = services.find(mtip, false);
			if(listas[0] == null && listas[1] == null){
				request.setAttribute("medMsg", "listasVacias");
				return mapping.findForward("success");
				
			}				
			request.getSession().setAttribute("listMedidaActivas", listas[0]);
			request.getSession().setAttribute("listMedidaInactivas", listas[1]);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("medMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Hace un cambio de Estado entre los Activos e Inactivos
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		MedidaForm matform = (MedidaForm) form;
		String estadox = "", est = "";
		ArrayList<MedidaDto> listActivo = (ArrayList) request.getSession().getAttribute("listMedidaActivas");
		ArrayList<MedidaDto> listInactivo = (ArrayList) request.getSession().getAttribute("listMedidaInactivas");
		MedidaDto dto = new MedidaDto();
		dto.setCodigo(matform.getCodigoHidden());
		dto.setNombre(matform.getNombre());
		dto.setAbrev(matform.getAbrev());
		dto.setFactor(matform.getFactor());
		TipoMedida tipo = services.getTipoMedidaToID(new Integer(matform.getTipo()));
		dto.setTipo(tipo.getNomTipoMedida());		
		if(matform.getEstado().equals("A")){
			dto.setEstado("Inactivo");
			estadox = "Activar";
			est = "I";
		}else{
			dto.setEstado("Activo");
			estadox = "Desactivar";
			est = "A";
		}
		dto.setAccion("<a href='" + Constants.contextPath + "mtoMed.do?cmd=hash&nombre=" + dto.getNombre()+ "&estado=" + est + "&abrev=" + dto.getAbrev() + "&codigoHidden=" + dto.getCodigo()+ "&factor=" + dto.getFactor()+ "&tipo=" + matform.getTipo() + "'>" + estadox+ "</a>");
		if(matform.getEstado().equals("A")){
			listActivo = services.removeItemToList(listActivo, matform.getNombre());
			listInactivo.add(dto);
		}else{
			listInactivo = services.removeItemToList(listInactivo, matform.getNombre());
			listActivo.add(dto);
		}
		request.getSession().setAttribute("listMedidaActivas", listActivo);
		request.getSession().setAttribute("listMedidaInactivas", listInactivo);
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza un registro de Medida
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{			
			MedidaForm matform = (MedidaForm) form;
			request.setAttribute("medidaFormJsp", matform);
			boolean action = services.knowAction(request);
			if(!action){
				if(matform.getCodigoHidden().equals("")  || matform.getNombre().equals("") || matform.getAbrev().equals("") 
						 || matform.getTipo().equals("Seleccione")){
					request.setAttribute("medMsg", "vacioUpdate");
					return mapping.findForward("success");
				}					
				else{
					boolean flag = services.update(matform);
					if(flag)
						request.setAttribute("medMsg", "exitoUpdate");
					else{
						request.setAttribute("medMsg", "errorUpdate");
						return mapping.findForward("success");
					}									
				}	
			}else{
				ArrayList[] list = services.findAll();
				request.getSession().setAttribute("listMedidaActivas", list[0]);
				request.getSession().setAttribute("listMedidaInactivas", list[1]);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("medMsg", "error");
		}
		return find(mapping, form, request, response);
	}
	
	/**
	 * Envia hacia la pagina de generación de Reporte
	 * @param mapping   ActionMapping
	 * @param form      ActionForm
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 
	 */
	public ActionForward generarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("reporte");
	}
	
}//class
