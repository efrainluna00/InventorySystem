
package com.saldei.web.services.seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.hibernate.tables.Perfil;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.seguridad.PerfilDto;
import com.saldei.web.form.seguridad.PerfilForm;

public class PerfilServices {
	
	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc= new JdbcHelper(); 
	
	/**
	 * Obtiene todos los perfiles Activos 
	 * @return Lista de Objetos 'Perfil' 
	 */
	public List getPerfiles(){
		String hql = QuerysSeguridad.getPerfilByStatus("A");
		List l = dao.find(hql);
		return 	l;	
	}
	
	public boolean save(PerfilForm cc){
		Perfil perfil=null;
		boolean flag=false;
		try{
			boolean exist = this.existPerfil(cc.getNombre());
			if(exist)
				return false;
			else{
				perfil= new Perfil();
				int codigo = jdbc.getSequence("select max(id_perfil)+1 from seguridad.perfil");
				perfil.setIdPerfil(codigo);
				perfil.setNomPerfil(cc.getNombre());
				if (cc.getDesc() != null && !cc.getDesc().equals("")  && cc.getDesc().length() >= 200){
					cc.setDesc(cc.getDesc().substring(0,199));
				}
				perfil.setDescPerfil(cc.getDesc());				
				perfil.setEstPerfil("A");
				dao.save(perfil);
				flag=true;	
			}			
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}		
		return flag;
	}
	
	private boolean existPerfil(String nombre){
		try{
			String hql = "from Perfil where lower(nomPerfil) = '" + nombre.toLowerCase() + "' ";
			List list = dao.find(hql);
			if(list.size() == 0)
				return false;			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean existPerfilById(String id){
		try{
			String hql = "from Perfil where idPerfil = " + new Integer(id) + " ";
			List list = dao.find(hql);
			if(list.size() == 0)
				return false;			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(PerfilForm cc, boolean bandera){		
		Perfil perfil = null;
		boolean flag=false;
		try{
			if(bandera){
				perfil=new Perfil();		
				perfil.setIdPerfil(Integer.parseInt(cc.getCodigoHid()));
				perfil.setNomPerfil(cc.getNombre());
				perfil.setDescPerfil(cc.getDesc());
				perfil.setEstPerfil(cc.getEstado());				
				dao.update(perfil);
				flag=true;
			}else{
				boolean exist = this.existPerfilById(cc.getCodigo());
				if(exist){
					perfil=new Perfil();		
					perfil.setIdPerfil(Integer.parseInt(cc.getCodigoHid()));
					perfil.setNomPerfil(cc.getNombre());
					perfil.setDescPerfil(cc.getDesc());
					perfil.setEstPerfil(cc.getEstado());				
					dao.update(perfil);
					flag=true;	
				}else
					return false;	
			}						
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;			
	}
	public boolean delete(PerfilForm cc,String usuario){		
		Perfil perfil =null;
		boolean flag=false;
		try{
			perfil= new Perfil();
			perfil.setIdPerfil(Integer.parseInt(cc.getCodigoHid()));
			perfil.setNomPerfil(cc.getEstado());
			perfil.setDescPerfil(cc.getDesc());
			perfil.setEstPerfil(cc.getEstado());				
			dao.delete(perfil);
			flag=true;	
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	public Map findActives(PerfilForm cc,int operacion){
		Map<String,PerfilDto> perfilesActivos= new HashMap<String, PerfilDto>();
		try{
			String hql = this.obtenerHQL(cc,1);			
			List list= dao.find(hql);
			ArrayList<PerfilDto> listx = new ArrayList<PerfilDto>();
					
			for(int i=0; i<list.size(); i++){
				Perfil form = (Perfil)list.get(i);			
				PerfilDto dto = new PerfilDto();			
				dto.setCodigo(String.valueOf(form.getIdPerfil()));
				dto.setNombre(form.getNomPerfil());
				dto.setDesc(form.getDescPerfil());
				dto.setEstado(form.getEstPerfil());
				if(operacion==1)
					//dto.setAccion("<a href='" + Constants.contextPath + "perfil.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Modificar</a>");
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado() + "')\" > Modificar</a>");
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "perfil.do?cmd=desactivarPerfil&codigoHid=" + dto.getCodigo()+ "'>Desactivar</a>" );						
				perfilesActivos.put(form.getIdPerfil().toString(),dto);
				listx.add(dto);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return perfilesActivos;
	}
	
	public Map findInactives(PerfilForm cc,int operacion){
		Map<String,PerfilDto> perfilesInactivos= new HashMap<String, PerfilDto>();	
		try{
			String hql = this.obtenerHQL(cc,2);
			//List list = dao.getAll(Perfil.class);
			List list= dao.find(hql);		
			ArrayList<PerfilDto> listx = new ArrayList<PerfilDto>();
			
			for(int i=0; i<list.size(); i++){
				Perfil form = (Perfil)list.get(i);
				PerfilDto dto = new PerfilDto();			
				dto.setCodigo(String.valueOf(form.getIdPerfil()));
				dto.setNombre(form.getNomPerfil());
				dto.setDesc(form.getDescPerfil());
				dto.setEstado(form.getEstPerfil());
				if(operacion==1)
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado() + "')\" > Modificar</a>");
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "perfil.do?cmd=ActivarPerfil&codigoHid=" + dto.getCodigo()+ "'>Activar</a>" );		
				perfilesInactivos.put(form.getIdPerfil().toString(),dto);
				listx.add(dto);
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		return perfilesInactivos;
	}
	public List getOpciones(){
		List list = null;
		try{
			String hql = "from Opcion o where o.estOpcion = 'A' and o.opcion is null order by o.nomOpcion";		
			list = dao.find(hql);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public boolean isNullMtoPerfilForm(PerfilForm cc){
		if(cc.getNombre().equals("") || cc.getDesc().equals(""))			
			return true;		
		return false;
	}
	public boolean isUpdatableMtoPerfilForm(PerfilForm cc){
		if(cc.getCodigoHid().equals("") || cc.getCodigo().equals("") || cc.getCodigo().equals(null))			
			return false;		
		return true;
	}
	public String nextId(){
		String id="";
		try{
			Perfil form = new Perfil();			
			String hql="select max(idPerfil)+ 1 from Perfil";
			List lstPerfil = dao.find(hql);
			for(int i=0;i<lstPerfil.size();i++)
				form = (Perfil)lstPerfil.get(i);
			id = String.valueOf(form.getIdPerfil());		
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return id;
		
	}
	public String obtenerHQL(PerfilForm cc,int estado){
		String hql = "";
		switch(estado){
			case 1:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='A' order by nomPerfil";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='A' and lower(nomPerfil) like '"+cc.getNombre().toLowerCase() +"%'  order by nomPerfil";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='A' and lower(nomPerfil) like '"+cc.getNombre().toLowerCase()+"%' and lower(descPerfil) like '"+cc.getDesc().toLowerCase()+"%'  order by nomPerfil";
				if(cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='A'  and lower(descPerfil) like '"+cc.getDesc().toLowerCase()+"%'  order by nomPerfil";
				break;
			case 2:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='I' order by nomPerfil";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='I' and lower(nomPerfil) like '"+cc.getNombre().toLowerCase()+"%'  order by nomPerfil";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='I' and lower(nomPerfil) like '"+cc.getNombre().toLowerCase()+"%' and lower(descPerfil) like '"+cc.getDesc().toLowerCase()+"%'  order by nomPerfil";
				if(cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Perfil where estPerfil='I'  and lower(descPerfil) like '"+cc.getDesc().toLowerCase()+"%'  order by nomPerfil";
		}
		return hql;
	}
}
