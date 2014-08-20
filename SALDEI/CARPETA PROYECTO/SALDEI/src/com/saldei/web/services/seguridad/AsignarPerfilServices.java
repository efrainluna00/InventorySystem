package com.saldei.web.services.seguridad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.hibernate.tables.Perfil;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.UsuarioPerfil;
import com.saldei.hibernate.tables.UsuarioPerfilId;
import com.saldei.util.commons.Auditoria;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.seguridad.PerfilDto;
import com.saldei.web.form.seguridad.AsignarPerfilForm;

/**
 * Servicios para la Asignación de Perfiles a un Usuario
 * @author WiRoCaRo
 * @version 1.0
 */
public class AsignarPerfilServices {

	private HibDAO dao = new HibDAOImpl();
	private Auditoria auditoria = new Auditoria();
	
	/**
	 * Valida que un Formulario este Invalido
	 * @param asgPerfil Formulario de Asignación de Perfil 
	 * @return
	 */
	public boolean isNullUsers(AsignarPerfilForm asgPerfil){
		if(asgPerfil.getUsr().equals("Seleccione"))
			return true;
		return false;
	}
			
	/**
	 * Ver los Perfiles de un Usuario
	 * @param form Formulario de Asignacion de Perfil
	 * @return Mapa con los Perfiles del Usuario
	 */
	public Map perfilUsuarios(AsignarPerfilForm form){
		Map mapa = null;
		try {
			String hql = QuerysSeguridad.getUsuarioPerfil(form.getUsr());
			List list = dao.find(hql);
			mapa = this.perfilMapa(list, form.getUsr(), "Remover Perfil", "Remove");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapa;
	}
	
	/**
	 * Ver los perfiles que no posee un Usuario
	 * @param form Formulario de Asignacion de Perfil
	 * @return Mapa
	 */
	public Map notPerfilUsuarios(AsignarPerfilForm form){
		Map mapa = null;		
		try {
			String hql = QuerysSeguridad.getNotUsuarioPerfil(form.getUsr());
			List list  = dao.find(hql);			
			mapa = this.perfilMapa(list,form.getUsr(), "Asignar Perfil", "Add");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapa;
	}	

	/**
	 * Obtiene un Mapa de Objetos de PerfilDto 
	 * @param list      Lista de la cual sacara la información para llenar los objetos 
	 * @param p_Usuario Usuario
	 * @param p_Accion  Nombre del Accion a ejecutar
	 * @return
	 */
	public Map perfilMapa(List list,  String p_Usuario, String p_Accion, String p_cmd){
		Map<String, PerfilDto> mapPerfil = new HashMap<String, PerfilDto>();
		try {
			PerfilDto dto = null;
			for(int i=0; i<list.size(); i++){
				List l = (List) list.get(i);
				dto = new PerfilDto();
				dto.setCodigo(l.get(0).toString().trim());
				dto.setNombre(l.get(1).toString().trim());
				dto.setDesc(  l.get(2).toString());
				dto.setEstado(l.get(3).toString());
				this.getAccion(dto, p_Usuario, p_Accion, p_cmd);
				mapPerfil.put(dto.getCodigo(), dto);
			}
		} catch (Exception e) {
			mapPerfil = null;
			e.printStackTrace();
		}
		return mapPerfil; 
	}
	
	/**
	 * Setea a la Accion el link correspondiente de acuerdo a los parametros
	 * @param dto       Contenedor
	 * @param p_Usuario Identificador de Usuario
	 * @param p_Accion  Nombre de la accion 
	 * @param cmd       Comando de Action
	 */
	public void getAccion (PerfilDto dto, String p_Usuario, String p_Accion, String cmd){
		dto.setAccion("<a href='" + Constants.contextPath + "asgPerfil.do?cmd="+cmd +"&idUsuario=" +p_Usuario + 
			      "&idPerfil=" + dto.getCodigo() + "'>"+ p_Accion +"</a>");
	}
	
	public void deleteUserPerfil(AsignarPerfilForm apf){
		UsuarioPerfil usuPerf = new UsuarioPerfil();
		UsuarioPerfilId id = new UsuarioPerfilId();
		Perfil perfil = new Perfil();
		Usuario usuario = new Usuario();
		
		perfil.setIdPerfil(new Integer(apf.getIdPerfil()));
		usuario.setIdUsuario(apf.getIdUsuario());
		id.setPerfil(perfil);
		id.setUsuario(usuario);
		usuPerf.setId(id);
		
		dao.delete(usuPerf);
	}
	
	public boolean addUserPerfil(AsignarPerfilForm apf){
		boolean flag = this.isPerfilInUser(apf.getIdPerfil(), apf.getUsr());
		if(!flag){
			UsuarioPerfil usuPerf = new UsuarioPerfil();
			UsuarioPerfilId id = new UsuarioPerfilId();
			Perfil perfil = new Perfil();
			Usuario usuario = new Usuario();
			perfil.setIdPerfil(new Integer(apf.getIdPerfil()));
			usuario.setIdUsuario(apf.getUsr());
			id.setPerfil(perfil);
			id.setUsuario(usuario);
			usuPerf.setId(id);
			
			dao.save(usuPerf);
			return true;
		}
		return false;
	}
	
	public UsuarioPerfil getUsuarioPerfil(String p_Usuario, String p_idPerfil) throws Exception{
		Perfil perfil = new Perfil();
		Usuario usuario   = new Usuario();
		UsuarioPerfil uPerfil = new UsuarioPerfil();
		UsuarioPerfilId UPid = new UsuarioPerfilId();
		perfil.setIdPerfil(new Integer( p_idPerfil));
		usuario.setIdUsuario(p_Usuario);
		UPid.setPerfil(perfil);
		UPid.setUsuario(usuario);		
		uPerfil.setId(UPid);
		return uPerfil;
	}
	
	public boolean saveProcess(String p_Usuario, Map p_Mapa, Usuario usuarioAudit){
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		boolean retorna = false;
		try {
			String hql = QuerysSeguridad.deletePerfilUsuario(p_Usuario); 
			dao.updateSinCommit(hql);
			auditoria.register(usuarioAudit.getIdUsuario(), "usuario_perfil", "DELETE", p_Usuario);
			if (p_Mapa  != null && p_Mapa.size() > 0){
				for(Iterator i = p_Mapa.keySet().iterator(); i.hasNext();) {
				    String key 	  = (String)i.next();
				    PerfilDto dto = (PerfilDto) p_Mapa.get(key);
				    UsuarioPerfil up = getUsuarioPerfil(p_Usuario, dto.getCodigo());
				    dao.save(up, session);
				    auditoria.register(usuarioAudit.getIdUsuario(), "usuario_perfil", "INSERT", p_Usuario + " - " + dto.getCodigo());
				}
			}
			tx.commit(); 
			retorna = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();			
		}
		finally{
			try {
				session.close(); 
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		return retorna;
	}
	
	public boolean deletePerfilUser(String p_Usuario){
		try {
			String hql = QuerysSeguridad.deletePerfilUsuario(p_Usuario);
			int row    = dao.update(hql);
			if(row >0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean savePerfilUser(String p_Usuario, Map p_Mapa){
		try {
			for (Iterator i = p_Mapa.keySet().iterator(); i.hasNext();){
				String strKey  = (String) i.next();
				PerfilDto dto = (PerfilDto) p_Mapa.get(strKey);
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(new Integer( dto.getCodigo()));
				Usuario usuario   = new Usuario();
				usuario.setIdUsuario(p_Usuario);
				UsuarioPerfilId UPid = new UsuarioPerfilId();
				UPid.setPerfil(perfil);
				UPid.setUsuario(usuario);
				UsuarioPerfil uPerfil = new UsuarioPerfil();
				uPerfil.setId(UPid);
				dao.save(uPerfil);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	private boolean isPerfilInUser(String idPerfil,String idUsuario){
		String hql = QuerysSeguridad.perfilInUsuario(idUsuario, idPerfil);		
		List list = dao.find(hql);
		if(list.size() == 0)
			return false;
		return true;
	}
	
}//class
