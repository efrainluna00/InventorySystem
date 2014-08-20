package com.saldei.web.action.registro;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.action.ActionGeneral;
import com.saldei.web.bean.registro.CicloDto;
import com.saldei.web.form.registro.CicloForm;
import com.saldei.web.services.registro.CicloServices;


public class CicloAction extends DispatchAction {

	CicloServices cicloServices = new CicloServices();
	ActionGeneral   actionGnl   = new ActionGeneral();
	
	/**
	 * Inicializa los Valores de El Formulario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 * @throws Exception 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesCiclo", Constants.legend_first_module);//
		this.cleanSession(mapping, request);
		request.getSession().setAttribute("anyos", cicloServices.getAnyos());
		return mapping.findForward("success");
	}
	
	/**
	 * Cambia los valores de los botones para el estado de Desactivacion y Limpia los Mapas de los ciclos
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward remover(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		actionGnl.remover(request);
		request.getSession().setAttribute("modulesCiclo", Constants.legend_second_module);//
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}

	/**
	 * Realiza una Busqueda segun el Form y pone la Accion dependiendo de los botones
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		CicloForm cicloForm = (CicloForm) form;
		request.setAttribute("CicloForm", cicloForm);
		try{
			String btn     = (String)request.getSession().getAttribute("btnRem"); 
			Boolean RemMod = true;
			if (btn.equals(Constants.btnRem1))
				RemMod = false;				
			Map mapCicloA = (Map) cicloServices.find(cicloForm, "A", RemMod);
			Map mapCicloI = (Map) cicloServices.find(cicloForm, "I", RemMod);		
			request.getSession().setAttribute("MapCicloA", mapCicloA);
			request.getSession().setAttribute("MapCicloI", mapCicloI);
			request.getSession().setAttribute("findBy",    cicloForm );			
		}catch (Exception e) {
			 e.printStackTrace();
			 
		}
		request.setAttribute("CicloForm", cicloForm);
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda un Nuevo Ciclo
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			CicloForm cicloForm  = (CicloForm) form;	
			CicloDto    dto = new CicloDto();			
			BeanUtils.copyProperties(dto,cicloForm);
			dto.setEstCiclo("A");
			dto.setIdCiclo(cicloServices.getIdCiclo(dto));
			if(cicloServices.isNullForm(cicloForm))
				request.setAttribute("cicloMsg", "vacio");
			else{
				if(!cicloServices.isCiclo(dto.getIdCiclo())){
					if (!cicloServices.existeCicloActivo(dto)){
						cicloServices.saveCiclo(dto);
						request.setAttribute("cicloMsg", "exito");	
					}
					else
						request.setAttribute("cicloMsg", "existeCicloA");						
				}
				else
					request.setAttribute("cicloMsg", "existe");
			}
			request.setAttribute("CicloForm", cicloForm);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza los datos de un Ciclo 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		CicloForm cicloForm = (CicloForm) form;
		try {	
			CicloDto  cicloDto  = new CicloDto();
			BeanUtils.copyProperties(cicloDto, cicloForm);
			if(cicloDto.getIdCiclo().equals(""))
				request.setAttribute("cicloMsg", "vacio");
			else{
				if (!cicloServices.existeCicloActivo(cicloDto)){
					cicloServices.updateCiclo(cicloDto);
					request.setAttribute("cicloMsg", "exitoDml");
				}
				else
					request.setAttribute("cicloMsg", "existeCicloA");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("CicloForm", cicloForm);
		actionGnl.modificar(request);
		request.getSession().setAttribute("modulesCiclo", Constants.legend_first_module);//
		return mapping.findForward("success");
	}
	
	/**
	 * Hace la Ejecuccion de los grid 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		 CicloForm cicloForm = (CicloForm) form;
		 try {
			Map<String, CicloDto> mapActivo   = (Map<String , CicloDto>)  request.getSession().getAttribute("MapCicloA");
			Map<String, CicloDto> mapInactivo = (Map<String ,   CicloDto>)  request.getSession().getAttribute("MapCicloI");
			CicloDto dto = new CicloDto();
			BeanUtils.copyProperties(dto,  cicloForm);
			String estadox = "";
			String est 		= "";
			if(cicloForm.getEstCiclo().equals("A")){
				dto.setEstCiclo("A");
				estadox = "Activar";
				est = "I";
			}			
			else{
				dto.setEstCiclo("I");
				estadox = "Desactivar";
				est = "A";
			}	
			cicloServices.getAccionDto(dto, "hash", estadox, est);
			if(cicloForm.getEstCiclo().equals("A")){
				mapActivo.remove(dto.getIdCiclo());
				mapInactivo.put(dto.getIdCiclo(), dto);
			}else {
				mapInactivo.remove(dto.getIdCiclo());
				mapActivo.put(dto.getIdCiclo(), dto);
			}
			request.getSession().setAttribute("MapUserA", mapActivo);
			request.getSession().setAttribute("MapUserI", mapInactivo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//request.setAttribute("CicloForm", cicloForm);
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda los Cambios de la desactivacion de Usuarios
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	 @SuppressWarnings("unchecked")
	public ActionForward guardarCambios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, CicloDto> mapActivo    = (Map<String , CicloDto>)  request.getSession().getAttribute("MapUserA");
			Map<String, CicloDto> mapInactivo  = (Map<String , CicloDto>)  request.getSession().getAttribute("MapUserI");
			if(mapActivo != null && mapActivo.size() > 0)
				cicloServices.guardarMap(mapActivo, "A");
			if (mapInactivo != null && mapInactivo.size() > 0)
				cicloServices.guardarMap(mapInactivo, "I");
			request.setAttribute("cicloMsg", "cambios"); 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("cicloMsg", "errorCambios"); 
		}
		actionGnl.remover(request);
		request.getSession().setAttribute("modulesCiclo", Constants.legend_second_module);//
		return mapping.findForward("success");
	}
	
	/** 
	 * Limpia las Sessiones para la Pagina
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cleanSession(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		this.cleanSession(mapping, request);
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia los Mapas del Usuario 
	 * @param mapping Objeto tipo ActionMapping
	 * @param request Objeto tipo HttpServletRequest
	 */
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().removeAttribute("MapCicloA");
		request.getSession().removeAttribute("MapCicloI");
	}
}
