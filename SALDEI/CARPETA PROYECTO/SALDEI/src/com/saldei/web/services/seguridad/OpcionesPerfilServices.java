package com.saldei.web.services.seguridad;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.hibernate.tables.Opcion;
import com.saldei.hibernate.tables.OpcionPerfil;
import com.saldei.hibernate.tables.OpcionPerfilId;
import com.saldei.hibernate.tables.Perfil;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Auditoria;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.seguridad.OpcionesPerfilDto;
import com.saldei.web.form.seguridad.OpcionesPerfilForm;

public class OpcionesPerfilServices {

private HibDAO dao = new HibDAOImpl();
private Auditoria auditoria = new Auditoria();

	/**
	 * Verifica si el valor de Perfil es el de la Constante Seleccione
	 * @param opcForm Form de OpcionPerfil
	 * @return True |False
	 */
	public boolean isNullPerfil(OpcionesPerfilForm opcForm){
		if(opcForm.getPerfil().equals(Constants.Seleccione))
			return true;
		return false;
	}

	/**
	 * Obtiene las Opciones que posee un Perfil 
	 * @param opcForm Form de Opciones Perfil 
	 * @return Mapa con Objetos OpcionesPerfilDTO
	 */
	@SuppressWarnings("unchecked")
	public Map opcionPerfil(OpcionesPerfilForm opcForm){
		Map mapOpcPer = new LinkedMap();		
		try {
			List lstOpcPer = new LinkedList();
			String hql = QuerysSeguridad.getOpcionPerfil(opcForm.getPerfil()) ;
			lstOpcPer = dao.find(hql);
			//TODO Mejorar esta parte obteniendo el Objeto desde el Query 
			if (lstOpcPer != null){				
				for(int i=0; i < lstOpcPer.size(); i++){
					List l = (List) lstOpcPer.get(i);
					OpcionesPerfilDto dto = new OpcionesPerfilDto();
					dto.setIdOpcion(l.get(0).toString());
					dto.setNombreOpcion(l.get(1).toString());
					dto.setDescripcion(l.get(2).toString());
					getAccion("Remover Opción", dto, "Remove");
					mapOpcPer.put(dto.getIdOpcion(), dto);
				}				
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapOpcPer;
	}
	
	/*funcion crreada por el grupo de tesis que desarrollo el modulo de inventario
	 * con el proposito de dar seguiridad al sistema.
	 * Obtiene las Opciones de los perfiles de un usario 
	 * @param opcForm Form de Opciones Perfil 
	 * @return Mapa con Objetos OpcionesPerfilDTO
	 */
	@SuppressWarnings("unchecked")
	public Map opcionesPerfilUsuario(String perfiles){
		Map mapOpcPer = new LinkedMap();		
		try {
			List lstOpcPer = new LinkedList();
			String hql = QuerysSeguridad.getOpcionesPerfilUsurario(perfiles) ;
			lstOpcPer = dao.find(hql);
			//TODO Mejorar esta parte obteniendo el Objeto desde el Query 
			if (lstOpcPer != null){				
				for(int i=0; i < lstOpcPer.size(); i++){
					List l = (List) lstOpcPer.get(i);
					String url;
					url = l.get(0).toString();
					url = url.substring(0,url.indexOf(".do")+3);
					url = url.substring(url.lastIndexOf("/"));
					mapOpcPer.put(url, url);
					
				}				
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapOpcPer;
	}
	
	/**
	 * Obtiene las Opciones que no posee un Perfil
	 * @param opcForm Form de Opciones Perfil
	 * @return Mapa con Objetos OpcionesPerfilDTO
	 */
	@SuppressWarnings("unchecked")
	public Map opcion(OpcionesPerfilForm opcForm){
		Map mapOpc = new LinkedMap();		
		try {
			List lstOpc = new LinkedList();
			String hql = QuerysSeguridad.getNoOpcionPerfil(opcForm.getPerfil());
			lstOpc = dao.find(hql);
			//TODO Mejorar esta parte obteniendo el Objeto desde el Query
			if (lstOpc != null){				
				for(int i=0; i < lstOpc.size(); i++){
					List l = (List) lstOpc.get(i);
					OpcionesPerfilDto dto = new OpcionesPerfilDto();
					dto.setIdOpcion(l.get(0).toString());
					dto.setNombreOpcion(l.get(1).toString());
					if (l.get(2) != null )
						dto.setDescripcion(l.get(2).toString());
					getAccion("Asignar Opción", dto, "Add");
					mapOpc.put(dto.getIdOpcion(), dto);
				}				
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapOpc;
	}	
	
	/** 
	 * Setea la Accion de un Dto
	 * @param Accion Nombre del a href 
	 * @param dto    Dto a setear
	 * @param cmd    Comando a ejecutar
	 * @return
	 */
	public static OpcionesPerfilDto getAccion(String Accion,OpcionesPerfilDto dto, String cmd){
		dto.setAccion("<a href='" + Constants.contextPath + "opcPerfil.do?cmd="+cmd+"&idOpcion=" + 
				dto.getIdOpcion()+"&nombreOpcion= "+ dto.getNombreOpcion()+"&descripcion="+dto.getDescripcion()+"'>"+Accion+"</a>");	
		return dto;		
	}
	
	public int  deleteOpcion(String p_Perfil){
		try{
			String hql = QuerysSeguridad.DeleteOpcPerf(p_Perfil);
			int rowCount = dao.update(hql);
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}    	
	}
	
	public OpcionPerfil getOpcionPerfil(OpcionesPerfilDto opcdto){
		OpcionPerfil    op   = new OpcionPerfil();
		OpcionPerfilId  opId = new OpcionPerfilId();
		Opcion          opc  = new Opcion();
		try {
			Perfil          per  = new Perfil();
			opc.setIdOpcion(Integer.valueOf(opcdto.getIdOpcion()));
			per.setIdPerfil(Integer.valueOf(opcdto.getIdPerfil()));
			opId.setOpcion(opc);
			opId.setPerfil(per);
			op.setId(opId);						
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return op; 
	}
	
	
	/**
	 * Proceso para guardar las opciones, maneja la Transaccionabilidad de los Datos eliminado primero la Tabla y luego  insertando los datos 
	 * @param mapOpcPerfil Mapa con las Opciones 
	 * @param p_Perfil     Perfil
	 * @return
	 */
	public boolean processSave (Map mapOpcPerfil, String p_Perfil, Usuario usuarioAudit) {
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		boolean retorna = false;
		try {
			String hql = QuerysSeguridad.DeleteOpcPerf(p_Perfil);
			dao.updateSinCommit(hql);
			auditoria.register(usuarioAudit.getIdUsuario(), "opcion_perfil", "DELETE", p_Perfil);
			if (mapOpcPerfil  != null && mapOpcPerfil.size() > 0){
				for(Iterator i = mapOpcPerfil.keySet().iterator(); i.hasNext();) {
				    String key 			  = (String)i.next();
				    OpcionesPerfilDto dto = (OpcionesPerfilDto) mapOpcPerfil.get(key);
				    dto.setIdPerfil(p_Perfil);
				    OpcionPerfil op =getOpcionPerfil(dto);
				    dao.save(op, session);
				    auditoria.register(usuarioAudit.getIdUsuario(), "opcion_perfil", "INSERT", op.getId().getOpcion().getIdOpcion() + " - " + op.getId().getPerfil().getIdPerfil());
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
	
}
