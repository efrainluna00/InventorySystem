package com.saldei.web.action.registro;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.registro.AsigMatCatServicesDto;
import com.saldei.web.form.registro.AsigMatCatForm;
import com.saldei.web.services.registro.AsigMatCatServices;

public class AsigMatCatAction extends DispatchAction {
	
	private AsigMatCatServices services = new AsigMatCatServices();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response){
		AsigMatCatForm asig = (AsigMatCatForm) form;
		if(asig.getCiclo().equals("Seleccione") || asig.getMateria().equals("Seleccione")){
			request.setAttribute("asigMsg", "vacio");
			request.getSession().setAttribute("lista0MatCat", null);
			request.getSession().setAttribute("lista1MatCat", null);
		}else{
			try{
				ArrayList[] listas = services.makeQuery(asig, asig.getMateria());
				request.getSession().setAttribute("lista0MatCat", listas[0]);
				request.getSession().setAttribute("lista1MatCat", listas[1]);
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("asigMsg", "error");
			}
		}
		return mapping.findForward("success");
	}
	
	public ActionForward getMateriaOnChange(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response){
		AsigMatCatForm asig = (AsigMatCatForm) form;
		try{
			if(asig.getCiclo().equals("Seleccione"))
				request.getSession().removeAttribute("materias");
			else{
				ArrayList list = services.getMaterias(asig);
				request.getSession().setAttribute("materias", list);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response){
		try{
			AsigMatCatForm asig = (AsigMatCatForm) form;
			ArrayList<AsigMatCatServicesDto> listB = (ArrayList) request.getSession().getAttribute("lista1MatCat");
			boolean flag = services.saveAll(listB, asig.getCiclo(), asig.getMateria());
			if(!flag)
				request.setAttribute("asigMsg", "errorSave");
			else
				request.setAttribute("asigMsg", "exito");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response){
		AsigMatCatForm asig = (AsigMatCatForm) form;
		try{
			ArrayList<AsigMatCatServicesDto> listA = (ArrayList) request.getSession().getAttribute("lista0MatCat");
			ArrayList<AsigMatCatServicesDto> listB = (ArrayList) request.getSession().getAttribute("lista1MatCat");
			AsigMatCatServicesDto dto = new AsigMatCatServicesDto();
			dto.setIdUsuario(asig.getIdUsuario());
			dto.setCargo(asig.getCargo());
			dto.setNombreUsuario(asig.getNombreUsuario());
			dto.setIdCargo(asig.getIdCargo());
			dto.setIdMateria(asig.getIdMateria());
			dto.setIdSeccion(asig.getIdSeccion());
			String estadox = "";
			if(asig.getEstado().equals("Remover"))
				estadox = "Asignar";
			else
				estadox = "Remover";
			dto.setAccion("<a href='" + Constants.contextPath + "asigMatCat.do?cmd=hash&idUsuario=" + dto.getIdUsuario() + "" +
					"&idCargo=" + dto.getIdCargo() + "&idMateria=" +  asig.getIdMateria() + "&estado=" + estadox + "" +
							"&idSeccion=" + asig.getIdSeccion() + "&nombreUsuario=" + dto.getNombreUsuario() + "&cargo= " + dto.getCargo() + "'>" + estadox +"</a>");
			if(asig.getEstado().equals("Remover")){
				listA.add(dto);
				listB = services.removeFormToList(listB, asig.getIdUsuario());
			}else{
				listB.add(dto);
				listA = services.removeFormToList(listA, asig.getIdUsuario());				
			}
			request.getSession().setAttribute("lista0MatCat", listA);
			request.getSession().setAttribute("lista1MatCat", listB);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("asigMsg", "error");
		}
		return mapping.findForward("success");
	}

}//class
