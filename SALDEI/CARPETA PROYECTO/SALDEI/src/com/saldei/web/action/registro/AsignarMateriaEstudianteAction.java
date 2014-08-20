/**
 * 
 */
package com.saldei.web.action.registro;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.commons.Constants;
import com.saldei.web.bean.registro.AsignarMateriaEstudianteDto;
import com.saldei.web.form.registro.AsignarMateriaEstudianteForm;
import com.saldei.web.services.registro.AsignarMateriaEstudianteService;

/**
 * @author Carlos
 *
 */
public class AsignarMateriaEstudianteAction extends DispatchAction {
	
	private AsignarMateriaEstudianteService services = new AsignarMateriaEstudianteService();
	
	public ActionForward find(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
				AsignarMateriaEstudianteForm apForm = (AsignarMateriaEstudianteForm) form;
				Map mapEstudianteInscrito 			= services.estudiantesInscritos(apForm);  
				Map mapEstudianteNoInscrito     	= services.estudiantesNoInscritos(apForm);
				Map mapEstudianteInscritoCopia  	= services.estudiantesInscritos(apForm);
				Map mapEstudianteNoInscritoCopia    = services.estudiantesNoInscritos(apForm);
				request.getSession().setAttribute("mapEstInscrito", mapEstudianteInscrito);
				request.getSession().setAttribute("mapEstNoInscrito", mapEstudianteNoInscrito);
				request.getSession().setAttribute("copiaMapEstInscrito", mapEstudianteInscritoCopia);
				request.getSession().setAttribute("copiaMapEstNoInscrito", mapEstudianteNoInscritoCopia);								
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	//agregar los que no estan inscritos
	@SuppressWarnings("unchecked")
	public ActionForward Add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			AsignarMateriaEstudianteForm apf = (AsignarMateriaEstudianteForm) form;			
			Map mapEstudianteInscrito 	  = (Map)request.getSession().getAttribute("mapEstInscrito");
			Map mapEstudianteNoInscrito   = (Map)request.getSession().getAttribute("mapEstNoInscrito");
			if (mapEstudianteNoInscrito !=null && mapEstudianteNoInscrito.size()>0){
				AsignarMateriaEstudianteDto dto = (AsignarMateriaEstudianteDto) mapEstudianteNoInscrito.get(apf.getCarnetid());
				//String[] combinado=apf.getMateria().split("-");
//				boolean flag = services.verificarEstudianteMateria(combinado[1], apf.getCarnetid());
//				if(flag){
					mapEstudianteNoInscrito.remove(apf.getCarnetid());				
					mapEstudianteInscrito.put(dto.getCarnet(),dto);
					request.getSession().setAttribute("mapEstInscrito",mapEstudianteInscrito);
					request.getSession().setAttribute("mapEstNoInscrito",mapEstudianteNoInscrito);
					dto.setAccion("<a href='" + Constants.contextPath + "asignarMateriaEstudiante.do?cmd=Remove&carnetid=" +apf.getCarnetid() +"'>Remover</a>");	
//				}else{
//					request.setAttribute("asigMateriaEstudiante", "yaexiste");
//					return mapping.findForward("success");  
//				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");                        
	}
	//quitar los que estan inscritos
	@SuppressWarnings("unchecked")
	public ActionForward Remove(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		try{
			AsignarMateriaEstudianteForm apf = (AsignarMateriaEstudianteForm) form;
			Map mapEstudianteInscrito 	  = (Map)request.getSession().getAttribute("mapEstInscrito");
			Map mapEstudianteNoInscrito   = (Map)request.getSession().getAttribute("mapEstNoInscrito");
			if (mapEstudianteInscrito !=null && mapEstudianteInscrito.size()>0){
				AsignarMateriaEstudianteDto dto = (AsignarMateriaEstudianteDto) mapEstudianteInscrito.get(apf.getCarnetid());
				mapEstudianteInscrito.remove(apf.getCarnetid());				
				mapEstudianteNoInscrito.put(dto.getCarnet(),dto);
				request.getSession().setAttribute("mapEstInscrito", mapEstudianteInscrito);
				request.getSession().setAttribute("mapEstNoInscrito",     mapEstudianteNoInscrito);
				dto.setAccion("<a href='" + Constants.contextPath + "asignarMateriaEstudiante.do?cmd=Add&carnetid=" +apf.getCarnetid() +"'>Asignar</a>");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		try{
			AsignarMateriaEstudianteForm apf = (AsignarMateriaEstudianteForm) form;
			String ciclo="",materia="",seccion="";
			ciclo=services.getCicloActivo();
			String[] combinado=apf.getMateria().split("-");
			seccion=combinado[0];
			materia=combinado[1];
			
			//Recuperando mapa de trabajo y mapa original copia	
			Map actualizaInscritoOriginal=(Map)request.getSession().getAttribute("copiaMapEstInscrito");
			Map actualizaInscritoModificid=(Map)request.getSession().getAttribute("mapEstInscrito");
			
			
			Map actualizaNoInscritoOriginal=(Map)request.getSession().getAttribute("copiaMapEstNoInscrito");
			Map actualizaNoInscritoModificid=(Map)request.getSession().getAttribute("mapEstNoInscrito");
			
			
			// Mapas sobre los cuales se hace la operacion
			Map<String, AsignarMateriaEstudianteDto> mapCopiaModificarInscrito = new HashMap<String, AsignarMateriaEstudianteDto>();
			Map<String, AsignarMateriaEstudianteDto> mapCopiaModificarNoInscrito = new HashMap<String, AsignarMateriaEstudianteDto>();
			
			mapCopiaModificarInscrito= actualizaInscritoModificid;
			mapCopiaModificarNoInscrito= actualizaNoInscritoModificid;
			
			for(Iterator i = actualizaInscritoOriginal.keySet().iterator(); i.hasNext();) {
			    String key 	  = (String)i.next();		  
			    if(mapCopiaModificarInscrito.containsKey(key))
			    	mapCopiaModificarInscrito.remove(key);		    	
			    	//System.out.println(key);		
			}	
			for(Iterator i = actualizaNoInscritoOriginal.keySet().iterator(); i.hasNext();) {
			    String key 	  = (String)i.next();		  
			    if(mapCopiaModificarNoInscrito.containsKey(key))
			    	mapCopiaModificarNoInscrito.remove(key);		    	
			    	//System.out.println(key);		
			}
			int estado=services.actualizarRegistros(mapCopiaModificarInscrito, mapCopiaModificarNoInscrito,ciclo,seccion,materia);
			if(estado == 1)
				request.setAttribute("asigMateriaEstudiante", "exito");
			else 
				request.setAttribute("asigMateriaEstudiante", "error");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return find(mapping, form, request, response);
	}
	
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
}
