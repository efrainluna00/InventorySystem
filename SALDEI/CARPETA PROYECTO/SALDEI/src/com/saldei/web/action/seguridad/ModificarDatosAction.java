package com.saldei.web.action.seguridad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.seguridad.ModificarDatosForm;
import com.saldei.web.services.administracion.CarreraServices;
import com.saldei.web.services.administracion.MulticodeServices;
import com.saldei.web.services.seguridad.UsuarioServices;

public class ModificarDatosAction extends DispatchAction {

	private UsuarioServices  userServices = new UsuarioServices();
	private MulticodeServices   multiServ = new MulticodeServices();
	CarreraServices   carServ    = new CarreraServices(); 
	
	/** Inicializa los Valores de El Formulario de Modificación de Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return 
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		try{
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			if (user != null){
				ModificarDatosForm modFrm = new ModificarDatosForm();
				UsuarioDto dto =  userServices.getUsuarioDto(user.getIdUsuario());
					BeanUtils.copyProperties(modFrm,dto);
					if(modFrm.getTipo()!= null){
						if(modFrm.getTipo().equals("D"))
							modFrm.setTipoStr("Usuario DEI");
						if(modFrm.getTipo().equals("S"))
							modFrm.setTipoStr("Estudiante");
						if(modFrm.getTipo().equals("E"))
							modFrm.setTipoStr("Externo");
					}	
					request.setAttribute("ModDatosForm",             modFrm);
					request.getSession().setAttribute("estadoCivil", multiServ.getEstadoCivil());		
					request.getSession().setAttribute("carrera",    carServ.getCarrera()); 
					request.getSession().setAttribute("UsuarioDto",  dto);	
			}
			else
				return mapping.findForward("login");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	/** Actualiza los Datos de un Usuario
	 * @param mapping  Objeto tipo ActionMapping
	 * @param form     Objeto tipo ActionForm 
	 * @param request  Objeto tipo HttpServletRequest
	 * @param response Objeto tipo HttpServletResponse
	 * @return 
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UsuarioDto 			dto   = (UsuarioDto)request.getSession().getAttribute("UsuarioDto");
		ModificarDatosForm modFrm = (ModificarDatosForm) form;
		try {
			UsuarioDto Userdto = userServices.modificarUsr(dto,modFrm);
			boolean send       = userServices.updateProcess(Userdto, dto.getIdUsuario());			
			if (send)
				request.setAttribute("modDatos", "exito");
			else
				request.setAttribute("modDatos", "errorSave");
			request.setAttribute("ModDatosForm", modFrm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Limpia las Sessiones de el formulario
	 * @param request  Objeto tipo HttpServletRequest
	 */
	public void cleanSession(HttpServletRequest request){
		request.getSession().removeAttribute("estadoCivil");		
		request.getSession().removeAttribute("carrera");
		request.getSession().removeAttribute("UsuarioDto");	
	}
}

