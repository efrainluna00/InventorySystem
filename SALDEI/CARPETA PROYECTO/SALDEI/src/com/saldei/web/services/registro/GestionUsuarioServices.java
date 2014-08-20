/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.mail.ConfigEmail;
import com.saldei.web.bean.registro.GestionUsuarioDto;
import com.saldei.web.form.registro.GestionUsuarioForm;

public class GestionUsuarioServices {
	
	private HibDAO dao = new HibDAOImpl();
	private ConfigEmail email = new ConfigEmail();
	
	public ArrayList getUser(){
		List list = dao.find("from Usuario where estUsuario in ('S', 'R')  order by idUsuario, estUsuario");
		ArrayList lst = this.prepareUsuarioToJsp(list);
		return lst;
	}
	
	public int gestionarUsuario(GestionUsuarioForm guf){
		try{
			String hql = "from Usuario where idUsuario = ?";
			Object[] params = {guf.getUsuario()};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				Usuario usuario = (Usuario) list.get(0);
				if(guf.getAccion().equals("A")){				
					String correo = usuario.getEmail();
					String bodyString = "Su solicitud ha sido aprobada. Su usario es: " + usuario.getIdUsuario() + " y su contraseña es: " + usuario.getPswUsuario();
					String subject = "Respuesta de Solicitud para el ingreso al Sistema SALDEI";
					boolean bandera = email.notificarEmail(correo, bodyString, subject);
					if(!bandera)
						return 0;
					usuario.setEstUsuario("A");
					dao.update(usuario);
				}else{
					String correo = usuario.getEmail();
					String bodyString = "Su solicitud ha sido rechazada";
					String subject = "Respuesta de Solicitud para el ingreso al Sistema SALDEI";
					boolean bandera = email.notificarEmail(correo, bodyString, subject);
					if(!bandera)
						return 0;
					usuario.setEstUsuario("R");
					dao.update(usuario);
				}
			}
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 2;
		}
	}	
	
	
	public ArrayList estadoUsuario(String id){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try{
			String estado = "";
			String hql = "from Usuario where idUsuario = ?";
			Object[] params = {id};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				Usuario usuario = (Usuario) list.get(0);
				estado = usuario.getEstUsuario();
				if(estado.equals("S"))
					estado = "Solicitado";
				else
					if(estado.equals("R"))
						estado = "Rechazado";				
				usuario.setEstUsuario(estado);
				lista.add(usuario);
				return lista;
			}
			return lista;
		}catch(Exception e){
			e.printStackTrace();
			return lista;
		}
	}
	
	public ArrayList getUser(GestionUsuarioForm guf){
		if(guf.getUsuario().equals("") || guf.getUsuario().equals("Seleccione")){
			List list = dao.find("from Usuario where estUsuario in ('S', 'R')  order by idUsuario, estUsuario");
			ArrayList lst = this.prepareUsuarioToJsp(list);
			return lst;
		}else{
			List list = dao.find("from Usuario where estUsuario in ('S', 'R') and idUsuario = '" + guf.getUsuario() + "' order by idUsuario, estUsuario");
			ArrayList lst = this.prepareUsuarioToJsp(list);
			return lst;
		}		
	}
	
	private ArrayList prepareUsuarioToJsp(List list){
		try{
			GestionUsuarioDto  dto = null;
			ArrayList<GestionUsuarioDto> lst = new ArrayList<GestionUsuarioDto>();
			for(Iterator iter = list.iterator(); iter.hasNext();){
				dto = new GestionUsuarioDto();
				Usuario usuario = (Usuario) iter.next();
				dto.setIdUsuario(usuario.getIdUsuario());
				dto.setNombreCompleto(usuario.getPrimerNom() + " " + usuario.getPrimerApe());
				dto.setCorreo(usuario.getEmail());
				if(usuario.getEstUsuario().equals("S")){
					dto.setEstado("Solicitado");
					dto.setAccion("<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
							"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=" + dto.getEstado() + "&estUser=1'>Activar</a>&nbsp; || &nbsp;" +
									"<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
							"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=" + dto.getEstado() + "&estUser=2'>Rechazar</a>");
				}					
				if(usuario.getEstUsuario().equals("R")){
					dto.setEstado("Rechazado");
					dto.setAccion("<a href='" + Constants.contextPath + "gestUser.do?cmd=hash&usuario=" + dto.getIdUsuario()  +
							"&nombreCompleto=" + dto.getNombreCompleto() + "&correo=" + dto.getCorreo() + "&estado=3'>Activar</a>");
				}
				lst.add(dto);
			}
			return lst;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}

}//class
