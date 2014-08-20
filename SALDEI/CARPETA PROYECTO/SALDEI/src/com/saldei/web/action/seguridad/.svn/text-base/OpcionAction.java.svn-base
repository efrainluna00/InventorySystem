package com.saldei.web.action.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.seguridad.OpcionDto;
import com.saldei.web.services.seguridad.OpcionServices;
import com.saldei.web.form.seguridad.OpcionForm;


public class OpcionAction extends DispatchAction{
	private OpcionServices services = new OpcionServices();

	private void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExt") == null || request.getSession().getAttribute("buttonsExt").toString().equals("firstButton")){
			request.getSession().setAttribute("primero", "Guardar"); //
			request.getSession().setAttribute("segundo", "Actualizar");//
			request.getSession().setAttribute("tercero", "Mostrar");//
			request.getSession().setAttribute("cuarto", "Limpiar");//
			request.getSession().setAttribute("quinto", "Activar");//
			request.getSession().setAttribute("modulesOpcion", Constants.legend_first_module);//
		}else{
			request.getSession().setAttribute("primero", "Guardar Cambios");//
			request.getSession().setAttribute("segundo", "Cancelar Cambios");//
			request.getSession().setAttribute("tercero", "Mostrar");//
			request.getSession().setAttribute("cuarto", "Limpiar");//
			request.getSession().setAttribute("quinto", "Modificar");//
			request.getSession().setAttribute("modulesOpcion", Constants.legend_second_module);//
		}
	}
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("allOpcionActivo",null);
		request.getSession().setAttribute("allOpcionInactivo",null);
		this.changeButtons(request);
		request.setAttribute("codigox", "");
		request.setAttribute("nombrex", "");
		request.setAttribute("descx", "");
		request.setAttribute("urlx", "");
		request.setAttribute("ordenx", "");
		request.setAttribute("separadorx", "Seleccione");
		request.setAttribute("padrex", "Seleccione");
		request.setAttribute("metodox", "");
		return mapping.findForward("success");
	}
	
	public ActionForward cleanAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("allOpcionActivo",null);
		request.getSession().setAttribute("allOpcionInactivo",null);
		return mapping.findForward("success");
	}
	
	public ActionForward changeAction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			request.getSession().setAttribute("allOpcionActivo",null);
			request.getSession().setAttribute("allOpcionInactivo",null);
			String accion = (String) request.getSession().getAttribute("buttonsExt");
			if(accion == null)
				request.getSession().setAttribute("buttonsExt", "secondButton");
			else
				if(accion.equals("firstButton"))
					request.getSession().setAttribute("buttonsExt", "secondButton");
				else
					request.getSession().setAttribute("buttonsExt", "firstButton");
			this.changeButtons(request);
			return mapping.findForward("success");
		}catch(Exception ex){
			return find(mapping, form, request, response);
		}
		
		
	}
	
	private boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExt");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButton"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			boolean action = this.knowAction(request);
			if(!action){
				OpcionForm oform = (OpcionForm) form;
				if(oform.getDesc() != null & !oform.getDesc().equals("") && oform.getDesc().length() >= 200)
					oform.setDesc(oform.getDesc().substring(0, 198));
				if(oform.getUrl() != null & !oform.getUrl().equals("") && oform.getUrl().length() >= 100)
					oform.setUrl(oform.getUrl().substring(0, 99));				
				if(services.isNullOpcionForm(oform)){
					request.setAttribute("opMsg","vacio");
					return mapping.findForward("success");
				}else
					if(services.isNumberOrden(oform.getOrden())){
						boolean flag = services.save(oform);
						if(flag)
							request.setAttribute("opMsg","exito");
						else
							request.setAttribute("opMsg","errorSave");
					}else{
						request.setAttribute("opMsg","notNumber");
						return mapping.findForward("success");
					}					
			}else{
				return updateAll(mapping, form, request, response);
			}
			return find(mapping,form,request,response);	
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("opMsg","error");
			return mapping.findForward("success");
		}
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		OpcionForm oform = (OpcionForm) form;
		services.delete(oform);
		return find(mapping,form,request,response);
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean action = this.knowAction(request);
		if(!action){
			OpcionForm oform = (OpcionForm) form;
			if(oform.getDesc() != null & !oform.getDesc().equals("") && oform.getDesc().length() >= 200)
				oform.setDesc(oform.getDesc().substring(0, 198));
			if(oform.getUrl() != null & !oform.getUrl().equals("") && oform.getUrl().length() >= 100)
				oform.setUrl(oform.getUrl().substring(0, 99));			
			if(services.isNullOpcionFormDml(oform)){
				request.setAttribute("opMsg","vacioDml");
				return mapping.findForward("success");
			}else
				if(services.isNumberOrden(oform.getOrden())){
					boolean flag = services.update(oform);
					if(flag)
						request.setAttribute("opMsg","exitoUpdate");
					else
						request.setAttribute("opMsg","errorUpdate");					
				}				
				
		}else{
			if(!action){			
				ArrayList[] list = services.findEvery(false);		
				request.getSession().setAttribute("allOpcionActivo",list[0]);
				request.getSession().setAttribute("allOpcionInactivo",list[1]);
			}else{
				ArrayList[] list = services.findEvery(true);		
				request.getSession().setAttribute("allOpcionActivo",list[0]);
				request.getSession().setAttribute("allOpcionInactivo",list[1]);
			}
			return mapping.findForward("success");
		}
					
		return find(mapping,form,request,response);
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		boolean action = this.knowAction(request);
		OpcionForm oform = (OpcionForm) form;
		if(oform.getDesc() != null & !oform.getDesc().equals("") && oform.getDesc().length() >= 200)
			oform.setDesc(oform.getDesc().substring(0, 198));
		if(oform.getUrl() != null & !oform.getUrl().equals("") && oform.getUrl().length() >= 100)
			oform.setUrl(oform.getUrl().substring(0, 99));		
		if(!oform.getOrden().equals("")){
			if(services.isNumberOrden(oform.getOrden())){
				if(!action){			
					ArrayList[] list = services.find(oform, false);		
					request.getSession().setAttribute("allOpcionActivo",list[0]);
					request.getSession().setAttribute("allOpcionInactivo",list[1]);
				}else{
					ArrayList[] list = services.find(oform, true);		
					request.getSession().setAttribute("allOpcionActivo",list[0]);
					request.getSession().setAttribute("allOpcionInactivo",list[1]);
				}			
			}else{
				request.setAttribute("opMsg","notNumber");
			}
		}else{
			if(!action){			
				ArrayList[] list = services.find(oform, false);		
				request.getSession().setAttribute("allOpcionActivo",list[0]);
				request.getSession().setAttribute("allOpcionInactivo",list[1]);
			}else{
				ArrayList[] list = services.find(oform, true);		
				request.getSession().setAttribute("allOpcionActivo",list[0]);
				request.getSession().setAttribute("allOpcionInactivo",list[1]);
			}
		}
		
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		OpcionForm oform = (OpcionForm) form;
		ArrayList<OpcionDto> listActivo = (ArrayList) request.getSession().getAttribute("allOpcionActivo");
		ArrayList<OpcionDto> listInactivo = (ArrayList) request.getSession().getAttribute("allOpcionInactivo");
		OpcionDto dto = new OpcionDto();
		dto.setNombre(oform.getNombre());
		dto.setDesc(oform.getDesc());
		dto.setMetodo(oform.getMetodo());
		dto.setPadre(services.getNombrePadreOpcion(new Integer(oform.getPadre())));
		dto.setOrden(oform.getOrden());
		dto.setPk(oform.getCodigo());
		dto.setSeparador(oform.getSeparador());
		dto.setUrl(oform.getUrl());
		String estadox = "";
		String est = "";
		if(oform.getEstado().equals("A")){
			dto.setEst("Inactivo");
			estadox = "Activar";
			est = "I";
		}			
		else{
			dto.setEst("Activo");
			estadox = "Desactivar";
			est = "A";
		}			
		dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=hash&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + est + "&padre=" + oform.getPadre() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>" + estadox + "</a>");
		if(oform.getEstado().equals("A")){
			listActivo = services.removeItemToList(listActivo, dto.getPk());
			listInactivo.add(dto);
		}else{
			listInactivo = services.removeItemToList(listInactivo, dto.getPk());
			listActivo.add(dto);		
		}
		request.getSession().setAttribute("allOpcionActivo",listActivo);
		request.getSession().setAttribute("allOpcionInactivo",listInactivo);
		return mapping.findForward("success");
	}
	
	public ActionForward getDataToPutForm(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		OpcionForm oform = (OpcionForm) form;		
		request.setAttribute("codigox", oform.getCodigo());
		request.setAttribute("nombrex", oform.getNombre());
		String desc = " ";
		if(!oform.getDesc().equals("null"))
			desc = oform.getDesc();
		request.setAttribute("descx", desc);
		request.setAttribute("urlx", oform.getUrl());
		request.setAttribute("ordenx", oform.getOrden());
		request.setAttribute("separadorx", oform.getSeparador());
		request.setAttribute("padrex", oform.getPadre());
		String metodo = " ";
		if(!oform.getMetodo().equals("null"))
			metodo = oform.getMetodo();
		request.setAttribute("metodox", metodo);
		request.setAttribute("estadox", oform.getEstado());
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward updateAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		ArrayList<OpcionDto> listActivo = (ArrayList) request.getSession().getAttribute("allOpcionActivo");
		ArrayList<OpcionDto> listInactivo = (ArrayList) request.getSession().getAttribute("allOpcionInactivo");
		if(listActivo == null && listInactivo == null)
			request.setAttribute("opMsg", "noList");
		else{
			try{
				services.updateAll(listActivo, listInactivo);
			}catch(Exception ex){
				ex.printStackTrace();
				request.setAttribute("opMsg", "noList");
			}
			
			request.setAttribute("opMsg","exitoUpdate");
		}
		return find(mapping,form,request,response);
	}
	
	public ActionForward findAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		List list = services.findAllOpciones();
		request.getSession().setAttribute("allOpcion",list);
		return mapping.findForward("success");
	}

}
