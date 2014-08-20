/**
 * 
 */
package com.saldei.util.commons;


 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping; //import org.apache.struts.action.RequestProcessor;
import org.apache.struts.tiles.TilesRequestProcessor;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.web.services.seguridad.OpcionesPerfilServices;




/**
 * @author Dario Hernandez
 *
 */
public class Security extends TilesRequestProcessor {

	public boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws java.io.IOException, javax.servlet.ServletException { 
		super.processPreprocess(request, response);
		String urlAsignado = processPath(request, response);
		String target = "failOption";
		
		if (!urlAsignado.equals("/login") && !urlAsignado.equals("/ldvAction")&& !urlAsignado.equals("/common") && !urlAsignado.equals("/solUsr")) {
			try {
				String path = urlAsignado+".do";
				Map data =  (Map) request.getSession().getAttribute("opciones_de_Usuario");
				Usuario user  = (Usuario) request.getSession().getAttribute("usuario");
								
				if(user == null || data == null){
					target = "logoutExn";
					throw new Exception("SESION_EXPIRADA");
				}
						
				String validaOpcion = (String) data.get(path);
				if (validaOpcion == null || validaOpcion.equals("null")){
					target = "failOption";
					throw new Exception("SIN_PERMISO");
				}					
			} catch (Exception e) {
				e.printStackTrace();
				processForwardConfig(request, response, mapping
						.findForward(target));
				return false;
			}
			catch (Error e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
	
		return true;

	}

}
