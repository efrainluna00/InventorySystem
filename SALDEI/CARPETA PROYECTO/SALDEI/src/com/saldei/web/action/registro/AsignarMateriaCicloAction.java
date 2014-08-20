package com.saldei.web.action.registro;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.registro.AsignarMateriaCicloDto;
import com.saldei.web.services.registro.AsignarMateriaCicloServices;
import com.saldei.web.form.registro.AsignarMateriaCicloForm;

public class AsignarMateriaCicloAction extends DispatchAction{
	
	private AsignarMateriaCicloServices services = new AsignarMateriaCicloServices();
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		AsignarMateriaCicloForm asig = (AsignarMateriaCicloForm) form;
		if(asig.getCiclo().equals("Seleccione"))
			request.setAttribute("asigMsg", "vacio");
		else{
			try{
				ArrayList[] listas = services.getMaterias(asig);
				request.getSession().setAttribute("lista0", listas[0]);
				request.getSession().setAttribute("lista1", listas[1]);
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("asigMsg", "error");
			}
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsignarMateriaCicloForm asig = (AsignarMateriaCicloForm) form;			
			ArrayList<AsignarMateriaCicloDto> lista1 = (ArrayList) request.getSession().getAttribute("lista1");
			/*if(lista1 == null || lista1.size() == 0){
				request.setAttribute("asigMsg","noData");
				return mapping.findForward("success");		
			}*/
			services.saveAll(lista1, asig.getCiclo());
			request.setAttribute("asigMsg","exito");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("asigMsg","error");
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		AsignarMateriaCicloForm asig = (AsignarMateriaCicloForm) form;
		try{
			ArrayList<AsignarMateriaCicloDto> lista0 = (ArrayList) request.getSession().getAttribute("lista0");
			ArrayList<AsignarMateriaCicloDto> lista1 = (ArrayList) request.getSession().getAttribute("lista1");
			AsignarMateriaCicloDto dto = new AsignarMateriaCicloDto();
			dto.setCiclo(asig.getIdCiclo());
			dto.setIdMateria(asig.getIdMateria());
			dto.setMateria(asig.getMateria());
			dto.setPkMat(asig.getPkMat());
			String estadox = "";
			if(asig.getEstado().equals("I")){
				dto.setEstado("A");
				estadox = "Remover";
				int seccion = services.existMatInList(dto.getIdMateria(), lista1);
				dto.setSeccion(String.valueOf(seccion));
			}				
			else{
				dto.setEstado("I");
				estadox = "Agregar";
			}
			dto.setAccion("<a href='" + Constants.contextPath + "asigMatCiclo.do?cmd=hash&estado=" + dto.getEstado() + "" +
					"&pkMat=" + dto.getPkMat() +  "&materia=" + dto.getMateria() + "&idCiclo=" + dto.getCiclo() + "&idMateria=" + dto.getIdMateria()+ "" +
					"&idSeccion=" + dto.getSeccion() + "'>" + estadox + "</a>");
			if(asig.getEstado().equals("I"))
				lista1.add(dto);
			else				
				lista1 = services.removeItemToList(lista1, dto.getIdMateria(), asig.getIdSeccion());			
			request.getSession().setAttribute("lista0", lista0);
			request.getSession().setAttribute("lista1", lista1);
		}catch(Exception e){
			request.setAttribute("asigMsg", "error");
			e.printStackTrace();
		}
		return mapping.findForward("success");	
	}

}//class
