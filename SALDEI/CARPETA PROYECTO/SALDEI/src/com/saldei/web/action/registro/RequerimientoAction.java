package com.saldei.web.action.registro;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.web.action.ActionGeneral;
import com.saldei.web.form.registro.RequerimientoForm;
import com.saldei.web.services.administracion.MateriaServices;
import com.saldei.web.services.registro.CicloServices;
import com.saldei.web.services.registro.RequerimientoServices;

public class RequerimientoAction  extends DispatchAction {

	RequerimientoServices reqServices   = new RequerimientoServices();
	CicloServices         cicloServices = new  CicloServices();
	ActionGeneral  		  actionGnl   	= new ActionGeneral();
	MateriaServices       matServices   = new MateriaServices();
	
	/**
	 * Inicializa los Valores de El Formulario de Requerimiento de Materia 
	 * @param mapping  Objeto tipo ActionMapping 
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 * @throws Exception 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			actionGnl.modificar(request);			
			this.cleanSession(mapping, request); 
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Verficica si el Usuario esta en session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				request.getSession().setAttribute("ciclos", reqServices.getCicloUsuario(usr));
				RequerimientoForm formReq = new RequerimientoForm(); 
				formReq.setIdUsuario(usr.getPrimerNom() + " " + usr.getPrimerApe()); 
				request.setAttribute("RequerimientoForm", formReq);
				request.getSession().setAttribute("listReq", reqServices.getRequerimientos());
			}
			else
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Busca las Materias de acuerdo el Usuario y el Ciclo
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 * @throws Exception 
	 */
	public ActionForward findMateria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			RequerimientoForm formReq = (RequerimientoForm) form;
			Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Valida si el Usuario de session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				formReq.setIdUsuario(usr.getPrimerNom() + " " + usr.getPrimerApe()); 
				List lista  = reqServices.getMateriaCiclo(usr, formReq) ;
				request.getSession().setAttribute("materias",  lista);	
				request.setAttribute("RequerimientoForm", formReq);	
			}
			else
				return mapping.findForward("login");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Busca los Requerimientos para una Materia Seccion Ciclo y Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward findRequerimiento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			RequerimientoForm formReq = (RequerimientoForm) form;
			Usuario usr  			  = (Usuario) request.getSession().getAttribute("usuario");
			 /**  Valida si el Usuario de session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				String idMat    		  = formReq.getIdMateria().toString();
				String SecMat[] 		  = idMat.split("-");
				String idSec = "";
				int numIdMat = 0, numIdSec = 0;
				if(SecMat.length != 1){
					idMat 					  = SecMat[0];
					idSec 			  = SecMat[1];
					numIdMat = new Integer(idMat);
					numIdSec = new Integer(idSec);
				}
				Map mapReq     			  = reqServices.getRequerimiento(usr.getIdUsuario(), formReq.getIdCiclo(), numIdMat, numIdSec, usr.getPrimerApe(), usr.getPrimerNom());
				request.getSession().setAttribute("mapReq",  mapReq);
				request.setAttribute("RequerimientoForm", formReq);	
			}
			else
				return mapping.findForward("login");	
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Guarda los datos de un Nuevo requerimeinto si ya esta en la Tabla de Solicitud solamente guarda el nuevo en Req_Materia 
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			RequerimientoForm formReq = (RequerimientoForm) form;
			if(!formReq.getRequerimiento().equals("") && formReq.getRequerimiento().length() >= 100)
				formReq.setRequerimiento(formReq.getRequerimiento().substring(0, 99));
			Usuario usr  	= (Usuario) request.getSession().getAttribute("usuario");
			 /**  Valida si el Usuario de session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				/* Obtiene los valores de el form */
				String idMat    = formReq.getIdMateria().toString();
				String SecMat[] = idMat.split("-");
				idMat = SecMat[0];
				String idSec = SecMat[1];
				request.getSession().removeAttribute("mapReq");  /*Remueve de Session el Mapa */
				boolean boolreq = false;
				int idSol    = reqServices.saveSolicitud(usr.getIdUsuario(), formReq.getIdCiclo(), Integer.parseInt(idMat), Integer.parseInt(idSec));				
				if (idSol > 0)
					boolreq  = reqServices.saveRequerimiento(idSol, formReq.getIdTipo(), formReq.getRequerimiento());
				if (boolreq)
					request.setAttribute("ReqMsg", "exito");
				else
					request.setAttribute("ReqMsg", "errorSave");
				Map mapReq   = reqServices.getRequerimiento(usr.getIdUsuario(), formReq.getIdCiclo(), Integer.parseInt(idMat), Integer.parseInt(idSec), usr.getPrimerApe(), usr.getPrimerNom());
				request.getSession().setAttribute("mapReq",  mapReq);
				request.setAttribute("RequerimientoForm", formReq);		
			}
			else
				return mapping.findForward("login");		
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Actualiza un Requerimiento de Materia, actualiza la descripcion del requermienito el Tipo y lo pone en Estado de Solicitado "S" 
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			RequerimientoForm formReq = (RequerimientoForm) form;
			if(!formReq.getRequerimiento().equals("") && formReq.getRequerimiento().length() >= 100)
				formReq.setRequerimiento(formReq.getRequerimiento().substring(0, 99));
			Usuario usr  	= (Usuario) request.getSession().getAttribute("usuario");
			 /**  Valida si el Usuario de session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				
				/* Obtiene los valores de el form */
				String idMat    = formReq.getIdMateria().toString();
				String SecMat[] = idMat.split("-");
				idMat = SecMat[1];
				String idSec = SecMat[0];
				boolean boolreq = false;
				if (formReq.getIdReqMat() > 0 && reqServices.isRequerimiento(formReq.getIdReqMat()))
					boolreq  = reqServices.updateRequerimiento(formReq.getIdTipo(), formReq.getRequerimiento(), formReq.getIdReqMat());
				if (boolreq)
					request.setAttribute("ReqMsg", "exitoDml");
				else
					request.setAttribute("ReqMsg", "errorUpdate");	
				request.getSession().removeAttribute("mapReq");  /*Remueve de Session el Mapa */
				Map mapReq   = reqServices.getRequerimiento(usr.getIdUsuario(), formReq.getIdCiclo(), Integer.parseInt(idMat), Integer.parseInt(idSec), usr.getPrimerApe(), usr.getPrimerNom());
				request.getSession().setAttribute("mapReq",  mapReq);
				request.setAttribute("RequerimientoForm", formReq);		
			}
			else
				return mapping.findForward("login");		
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return findRequerimiento(mapping, form, request, response);
	}
	
	/**
	 * Elimina un Requerimiento de Materia, 
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			RequerimientoForm formReq = (RequerimientoForm) form;
			Usuario usr  	= (Usuario) request.getSession().getAttribute("usuario");
			 /**  Valida si el Usuario de session es valido*/
			if (usr != null && usr.getIdUsuario()!=null && !usr.getIdUsuario().equals("")){
				/* Obtiene los valores de el form */
				String idMat    = formReq.getIdMateria().toString();
				String SecMat[] = idMat.split("-");
				idMat = SecMat[1];
				String idSec = SecMat[0];
				@SuppressWarnings("unused")
				int idSol = 0;
				if (formReq.getIdReqMat() > 0){
					idSol = reqServices.getIdSolicitud(usr.getIdUsuario(), formReq.getIdCiclo(), Integer.parseInt(idMat), Integer.parseInt(idSec));
					boolean eliminado = reqServices.deleteRequerimiento(formReq.getIdReqMat());
					if (eliminado)
						request.setAttribute("ReqMsg", "exitoDel");
					else
						request.setAttribute("ReqMsg", "errorDel");
				}
				else
					request.setAttribute("ReqMsg", "errorDel");	
				request.getSession().removeAttribute("mapReq");  /*Remueve de Session el Mapa */
				Map mapReq   = reqServices.getRequerimiento(usr.getIdUsuario(), formReq.getIdCiclo(), Integer.parseInt(idMat), Integer.parseInt(idSec), usr.getPrimerApe(), usr.getPrimerNom());
				request.getSession().setAttribute("mapReq",  mapReq);
				request.setAttribute("RequerimientoForm", formReq);		
			}
			else
				return mapping.findForward("login");		
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("ioError");
		}
		return findRequerimiento(mapping, form, request, response);
	}
	
	/**
	 * Limpia los Mapas del Usuario 
	 * @param mapping Objeto tipo ActionMapping
	 * @param request Objeto tipo HttpServletRequest
	 */
	public void cleanSession(ActionMapping mapping, HttpServletRequest request){
		request.getSession().setAttribute("materias",  new  LinkedList());
		request.getSession().removeAttribute("mapReq");
	}
	
	
	

}
