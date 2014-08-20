/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.action.registro;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.registro.AsigDirecLabDto;
import com.saldei.web.bean.registro.AsigDirecLabDtoForm;
import com.saldei.web.form.registro.AsigDirecLabForm;
import com.saldei.web.services.registro.AsigDirecLabServices;

public class AsigDirecLabAction extends DispatchAction {
	
	private AsigDirecLabServices services = new AsigDirecLabServices();

	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	public ActionForward saveAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsigDirecLabForm asl = (AsigDirecLabForm) form;
			ArrayList list = (ArrayList) request.getSession().getAttribute("listDsigDirec");			
			services.saveAll(list, asl.getLaboratorio(), asl);
			request.setAttribute("aslMsg", "exito");
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("aslMsg", "error");
		}		
		return  mapping.findForward("success");
	}
	
	public ActionForward cancelAll(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return show(mapping, form, request, response);
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		AsigDirecLabForm asl = (AsigDirecLabForm) form;
		ArrayList list = (ArrayList) request.getSession().getAttribute("listDsigDirec");
		ArrayList<AsigDirecLabDto> newList = new ArrayList<AsigDirecLabDto>();
		for(int i=0; i<list.size(); i++){
			AsigDirecLabDto adl = (AsigDirecLabDto) list.get(i);
			if(adl.getIdUsuario().equals(asl.getIdUsuario()) && adl.getFechaIniDto().equals(asl.getFechaIni()) && adl.getFechaFinDto().equals(asl.getFechaFin())){}
			else
				newList.add(adl);				
		}
		request.getSession().setAttribute("listDsigDirec", newList);
		return  mapping.findForward("success");
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsigDirecLabForm asl = (AsigDirecLabForm) form;
			if(asl.getLaboratorio().equals("Seleccione") || asl.getLaboratorio().equals("")){
				request.setAttribute("aslMsg", "vacio");
				request.getSession().setAttribute("listAsigDirec", null);
				request.getSession().setAttribute("listDsigDirec", null);
				return mapping.findForward("success");
			}else
				services.mostrar(asl, request);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("aslMsg", "error");
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsigDirecLabForm asl = (AsigDirecLabForm) form;				
			ArrayList listA = (ArrayList) request.getSession().getAttribute("listAsigDirec");
			ArrayList listB = (ArrayList) request.getSession().getAttribute("listDsigDirec");
			int orden = new Integer(asl.getIdobt());					
			ArrayList lista = this.listas(listA, orden, asl.getFechaIni(), asl.getFechaFin(), asl.getId(), asl.getLaboratorio(), asl.getIdCargo(), listB);			
			request.getSession().setAttribute("listDsigDirec", lista);			
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("aslMsg", "error");
		}
		return mapping.findForward("success");
	}

	public ArrayList listas(ArrayList list, int orden, String fechaIni, String fechaFin, String id, String labo, String cargo, ArrayList<AsigDirecLabDto> lista){
		AsigDirecLabDtoForm dto = null;
		AsigDirecLabDto form = null;		
		for(int i=0; i<list.size(); i++){				
			if(orden == (i + 1)){
				dto = (AsigDirecLabDtoForm) list.get(i);
				form = new AsigDirecLabDto();
				form.setFechaIniDto(fechaIni);
				form.setFechaFinDto(fechaFin);		
				form.setRemove("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=remove&fechaIni=" + fechaIni + "&fechaFin=" + form.getFechaFinDto()+ "&id=" + id + "&labos=" + dto.getLabosf() + "&laboratorio=" + labo + "&idUsuario=" +
						"" + dto.getIdUsuariof() + "&desc= " + dto.getDescf() + "&idCargo=" + cargo  + "&nombre=" + dto.getNombref() + "&estado=" + dto.getEstadof() + "'>Remover</a>");
				form.setIdUsuario(dto.getIdUsuariof());
				form.setNombre(dto.getNombref());
				lista.add(form);
			}
		}
		return lista;
	}
	
	/*@SuppressWarnings("unchecked")
	public ActionForward hash(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsigDirecLabForm asl = (AsigDirecLabForm) form;
			AsigDirecLabDto dto = new AsigDirecLabDto();			
			dto.setIdUsuario(asl.getIdUsuario());
			dto.setIdMulti(asl.getIdCargo());
			dto.setLaboratorio(asl.getLaboratorio());
			dto.setNombre(asl.getNombre());
			dto.setDesc(asl.getDesc());
			dto.setId(asl.getId());
			dto.setFechaIniDto(asl.getFechaIni());
			dto.setFechaFinDto(asl.getFechaFin());
			dto.setLabos(services.getLabByDirector(dto.getIdUsuario(), asl.getLaboratorio()));
			if(asl.getEstado().equals("A")){
				dto.setEstado("I");
				dto.setAccion("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=hash&id=" + asl.getId() + "&labos=" + dto.getLabos() + "&laboratorio=" + asl.getLaboratorio() + "&idUsuario=" +
						"" + dto.getIdUsuario() + "&desc= " + dto.getDesc() + "&idCargo=" + asl.getIdCargo()  + "&nombre=" + dto.getNombre() + "&estado=" + dto.getEstado() + "'>Remover</a>");
			}else{
				dto.setEstado("A");
				dto.setAccion("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=hash&id=" + asl.getId() + "&labos=" + dto.getLabos() + "&laboratorio=" + asl.getLaboratorio() + "&idUsuario=" +
						"" + dto.getIdUsuario() + "&desc=" + dto.getDesc() + "&idCargo=" + asl.getIdCargo()  + "&nombre=" + dto.getNombre() + "&estado=" + dto.getEstado() + "'>Activar</a>");				
			}
			ArrayList listA = (ArrayList) request.getSession().getAttribute("listAsigDirec");
			ArrayList listB = (ArrayList) request.getSession().getAttribute("listDsigDirec");
			if(dto.getEstado().equals("A")){
				listA.add(dto);
				listB = services.removeFormToList(listB, dto.getIdUsuario());
			}				
			else
				listB.add(dto);					
			request.getSession().setAttribute("listAsigDirec", listA);
			request.getSession().setAttribute("listDsigDirec", listB);
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("aslMsg", "error");
		}
		return mapping.findForward("success");
	}*/

	
}//class
