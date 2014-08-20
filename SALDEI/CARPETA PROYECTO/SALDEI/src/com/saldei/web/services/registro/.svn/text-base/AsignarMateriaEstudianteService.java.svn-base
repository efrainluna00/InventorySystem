/**
 * 
 */
package com.saldei.web.services.registro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.querys.QuerysRegistro;
import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.AsignarMateriaEstudianteDto;
import com.saldei.web.form.registro.AsignarMateriaEstudianteForm;
/**
 * @author Carlos
 *
 */
public class AsignarMateriaEstudianteService {
	private JdbcHelper jdbc = new JdbcHelper();
	
	HibDAO dao = new HibDAOImpl();
	
	public String getCicloActivo(){
		String ciclo="";
		try{
			String hql = "from Ciclo where estCiclo = 'A' and cicloActivo= 'S'";
			List list = dao.find(hql);
			if(list.size() != 0){
				Ciclo c = (Ciclo) list.get(0);
				ciclo = c.getIdCiclo();
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return ciclo;
	}
	
//	public List MateriasCiclo(){
//		String ciclo="";
//		try{
//			ciclo=this.getCicloActivo();
//			String hql="select new list (a.id.seccionXMateria.id.idSeccion || '-' || a.id.seccionXMateria.id.materia.idMateria as idMateria  , " +
//					" '0' || a.id.seccionXMateria.id.idSeccion || ' - ' || d.nomMateria as NombreMateria) " +
//					"from MateriaCiclo a,Ciclo b,SeccionXMateria c,Materia d " +
//					"where a.id.ciclo.idCiclo= ?  " +
//					"and a.id.ciclo.idCiclo=b.idCiclo " +
//					"and b.estCiclo='A' " +
//					"and a.id.seccionXMateria.id.idSeccion=c.id.idSeccion " +
//					"and a.id.seccionXMateria.id.materia.idMateria=c.id.materia.idMateria " +
//					"and c.estSeccMat='A' " +
//					"and a.id.seccionXMateria.id.materia.idMateria=d.idMateria " +
//					"and d.estMateria='A' " +
//					"order by d.nomMateria,a.id.seccionXMateria.id.idSeccion";
//			Object[] params = {ciclo};
//			List list = dao.findByProps(hql, params);
//			return list;
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	public List MateriasCiclo(){
		String ciclo="";
		try{
			ciclo=this.getCicloActivo();
			String hql="select new list (a.id.idSeccion || '-' || a.id.materia.idMateria as idMateria  , " +
					"  d.nomMateria  || '-' || '0' || a.id.idSeccion as NombreMateria) " +
					"from MateriaCiclo a,Ciclo b,Materia d " +
					"where a.id.ciclo.idCiclo= ?  " +
					"and a.id.ciclo.idCiclo=b.idCiclo " +
					"and b.estCiclo='A' " +										
					"and a.id.materia.idMateria=d.idMateria " +
					"and d.estMateria='A' " +
					"order by d.nomMateria,a.id.idSeccion";
			Object[] params = {ciclo};
			List list = dao.findByProps(hql, params);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List getMateriaCiclo() throws Exception{
			List list   = this.MateriasCiclo();
			List<ElementDto> lista  = new LinkedList<ElementDto>();
			String idSeccionidMateria="";
			String idSeccionNomMateria="";
			for(int i=0; i<list.size(); i++){
				List listAux = (List)list.get(i);
				idSeccionidMateria=listAux.get(0).toString();
				idSeccionNomMateria=listAux.get(1).toString();
				ElementDto e = new ElementDto();
				e.setElement1(idSeccionidMateria);
				e.setElement2(idSeccionNomMateria);
				lista.add(e);
			}
			return lista;
	}
	public List getEstudianteInscrito(AsignarMateriaEstudianteForm form) throws Exception{
		String carnet="",apellido="",hql="",ciclo="",seccion="",materia="";
		ciclo=this.getCicloActivo();
		String[] combinado=form.getMateria().split("-");
		seccion=combinado[0];
		materia=combinado[1];
		carnet=form.getCarnet();
		apellido=form.getApellido();		
		List list = null;
		if(carnet.equals("") && apellido.equals("")){
			hql=this.obtenerQuery(1,apellido,carnet,ciclo,seccion,materia);
		}		
		if(!carnet.equals("") && apellido.equals("")){
			hql=this.obtenerQuery(2,apellido,carnet,ciclo,seccion,materia);			
		}			
		if(carnet.equals("") && !apellido.equals("")){
			hql=this.obtenerQuery(3,apellido,carnet,ciclo,seccion,materia);
		}			
		if(!carnet.equals("") && !apellido.equals("")){
			hql=this.obtenerQuery(4,apellido,carnet,ciclo,seccion,materia);			
		}
		try{			
			list=jdbc.getQuery(hql, null);									
		}catch(Exception e){
			e.printStackTrace();			
		}
		return list;		
	}
	public List getEstudianteNoInscrito(AsignarMateriaEstudianteForm form) throws Exception{
		String carnet="",apellido="",hql="",ciclo="",seccion="",materia="";
		ciclo=this.getCicloActivo();
		String[] combinado=form.getMateria().split("-");
		seccion=combinado[0];
		materia=combinado[1];
		carnet=form.getCarnet();
		apellido=form.getApellido();		
		List list = null;
		if(carnet.equals("") && apellido.equals("")){
			hql=this.obtenerQuery(5,apellido,carnet,ciclo,seccion,materia);
		}		
		if(!carnet.equals("") && apellido.equals("")){
			hql=this.obtenerQuery(6,apellido,carnet,ciclo,seccion,materia);			
		}			
		if(carnet.equals("") && !apellido.equals("")){
			hql=this.obtenerQuery(7,apellido,carnet,ciclo,seccion,materia);
		}			
		if(!carnet.equals("") && !apellido.equals("")){
			hql=this.obtenerQuery(8,apellido,carnet,ciclo,seccion,materia);			
		}
		try{			
			list=jdbc.getQuery(hql, null);									
		}catch(Exception e){
			e.printStackTrace();			
		}
		return list;
	}
	public String obtenerQuery(int tipo,String apellido,String carnet,String ciclo,String seccion,String materia) throws Exception{
		String hql="";
		String ape=apellido.toLowerCase();
		try{
			switch(tipo){
				// estudiantes asignados
				case 1:
					//nada
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where  a.carnet_estudiante in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario";
					break;
				case 2:
					//carnet
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where a.carnet_estudiante like '"+carnet+"%' and  a.carnet_estudiante in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario order by u.primer_ape";
					break;
					//apellido
				case 3:
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where  a.carnet_estudiante in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario and a.carnet_estudiante in (select c.id_usuario from seguridad.usuario c where lower(c.primer_ape) like '"+ape+"%')";
					
					break;
					//carnet y apellido
				case 4:
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where a.carnet_estudiante like'"+carnet+"%' and a.carnet_estudiante in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario and a.carnet_estudiante in (select c.id_usuario from seguridad.usuario c where lower(c.primer_ape) like '"+ape+"%') ";
					break;
				// estudiantes no asignados					
				case 5:
					//nada
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where  a.carnet_estudiante not in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario ";
					break;
				case 6:
					//carnet
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where  a.carnet_estudiante like '"+carnet+"%' and a.carnet_estudiante not in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario";
					break;
					//apellido
				case 7:
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where a.carnet_estudiante not in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario and a.carnet_estudiante in (select c.id_usuario from seguridad.usuario c where lower(c.primer_ape) like '"+ape+"%') ";
					
					break;
					//carnet y apellido
				case 8:
					hql="select a.carnet_estudiante as carnet,u.primer_nom as primernom, u.primer_ape as primerape from registro.estudiante a,seguridad.usuario u where a.carnet_estudiante like'"+carnet+"%' and a.carnet_estudiante not in (select b.carnet_estudiante from registro.materia_inscrita b where b.id_materia="+materia+" and b.id_ciclo ='"+ciclo+"' and b.id_seccion="+seccion+") and a.carnet_estudiante=u.id_usuario and a.carnet_estudiante in (select c.id_usuario from seguridad.usuario c where lower(c.primer_ape) like '"+ape+"%') ";
					break;

			}	
		}catch(Exception ex){
			ex.printStackTrace();		
		}
		return hql;
	}
	public boolean estudianteInscrito(String carnet,String idMateria,String idSeccion){
		String idCiclo="";
		boolean flag=false;		
		try{
			idCiclo=this.getCicloActivo();
			String hql=QuerysRegistro.estudianteInscrito(carnet, idMateria, idSeccion, idCiclo);
			List list=dao.find(hql);
			if(list.size()==0 || list.size()<0 ) flag=false;
			else flag=true;			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return flag;
	}
	
	public Map estudiantesInscritos(AsignarMateriaEstudianteForm form){
		Map<String, AsignarMateriaEstudianteDto> mapEstudianteInscrito = new HashMap<String, AsignarMateriaEstudianteDto>();		
		try {
			List list = this.getEstudianteInscrito(form);
			AsignarMateriaEstudianteDto dto=null;
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				DynaBean dyna = (DynaBean) iter.next();
				dto = new AsignarMateriaEstudianteDto();
				if(dyna.get("carnet") != null)
					dto.setCarnet(dyna.get("carnet").toString());
				if(dyna.get("primernom") != null)
					dto.setNombres(dyna.get("primernom").toString());
				if(dyna.get("primerape") != null)
					dto.setApellidos(dyna.get("primerape").toString());
				mapEstudianteInscrito.put(dto.getCarnet(),dto);				
				dto.setAccion("<a href='" + Constants.contextPath + "asignarMateriaEstudiante.do?cmd=Remove&carnetid=" +dto.getCarnet() +"'>Remover</a>");				
			}			
		} catch (Exception e) {
			mapEstudianteInscrito=null;
			e.printStackTrace();
		}		
		return mapEstudianteInscrito;
	}
	public Map estudiantesNoInscritos(AsignarMateriaEstudianteForm form){
		Map<String, AsignarMateriaEstudianteDto> mapEstudianteNoInscrito = new HashMap<String, AsignarMateriaEstudianteDto>();		
		try{
			List list = this.getEstudianteNoInscrito(form);
			AsignarMateriaEstudianteDto dto=null;
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				DynaBean dyna = (DynaBean) iter.next();
				dto = new AsignarMateriaEstudianteDto();
				if(dyna.get("carnet") != null)
					dto.setCarnet(dyna.get("carnet").toString());
				if(dyna.get("primernom") != null)
					dto.setNombres(dyna.get("primernom").toString());
				if(dyna.get("primerape") != null)
					dto.setApellidos(dyna.get("primerape").toString());
				mapEstudianteNoInscrito.put(dto.getCarnet(),dto);
				dto.setAccion("<a href='" + Constants.contextPath + "asignarMateriaEstudiante.do?cmd=Add&carnetid=" +dto.getCarnet() +"'>Asignar</a>");
			}			
		} catch (Exception e) {
			mapEstudianteNoInscrito=null;
			e.printStackTrace();
		}
		for(Iterator i = mapEstudianteNoInscrito.keySet().iterator(); i.hasNext();) {
		    String key 	  = (String)i.next();
		    @SuppressWarnings("unused")
			AsignarMateriaEstudianteDto dto = (AsignarMateriaEstudianteDto) mapEstudianteNoInscrito.get(key);		   
		}
		return mapEstudianteNoInscrito;
	}	
	public Map actualizarInscritos(Map estudianteInscritoCopia,Map estudianteInscrito){		
		for(Iterator i = estudianteInscritoCopia.keySet().iterator(); i.hasNext();) {
		    String key 	  = (String)i.next();
		    if(estudianteInscrito.containsKey(key))
		    	estudianteInscrito.remove(key);		    
		}
		return estudianteInscrito;
	}
	public Map actualizarNoInscritos(Map estudianteNoInscritoCopia,Map estudianteNoInscrito){
		@SuppressWarnings("unused")
		Map<String, AsignarMateriaEstudianteDto> estudianteInscritoActualizar = new HashMap<String, AsignarMateriaEstudianteDto>();
		for(Iterator i = estudianteNoInscritoCopia.keySet().iterator(); i.hasNext();) {
		    String key 	  = (String)i.next();
		    if(estudianteNoInscrito.containsKey(key))
		    	estudianteNoInscrito.remove(key);		    
		}
		return estudianteNoInscrito;
	}
	public int actualizarRegistros(Map estudiantesInscritos,Map estudiantesNoInscritos,String ciclo,String seccion,String materia){
		boolean flagInsert=true,flagDelete=true;	
		int bandera=0;
		String carnet="";
		String estado="A";
		String queryInsert="insert into registro.materia_inscrita (carnet_estudiante,id_materia,id_seccion,id_ciclo,retirada,est_mat_inscrita) values(";
		String query="";		
		String queryDelete="delete from registro.materia_inscrita where ";
		AsignarMateriaEstudianteDto dto = new AsignarMateriaEstudianteDto();
		try{
			if (estudiantesInscritos!=null && estudiantesInscritos.size()>0){
				for(Iterator i = estudiantesInscritos.keySet().iterator(); i.hasNext();){					
				    String key = (String)i.next();				    
				    dto = (AsignarMateriaEstudianteDto)estudiantesInscritos.get(key);
				    carnet=dto.getCarnet();				    
				    query+=queryInsert + "'"+carnet+"',"+materia+","+seccion+",'"+ciclo+"','N','"+estado+"'"+")";
				    flagInsert = jdbc.saveOrUpdate(query);
				    query="";
				    
				}				
			}
			query="";
			if(estudiantesNoInscritos !=null && estudiantesNoInscritos.size()>0){
				for(Iterator i = estudiantesNoInscritos.keySet().iterator(); i.hasNext();){					
				    String key = (String)i.next();				    
				    dto = (AsignarMateriaEstudianteDto)estudiantesNoInscritos.get(key);
				    carnet=dto.getCarnet();				    
				    query+= queryDelete+"carnet_estudiante='"+carnet+"' and id_materia="+materia+" and id_seccion="+seccion+ " and id_ciclo='"+ciclo+"'";				    		    
				    flagDelete=jdbc.delete(query,null);
				    query="";
				}				
			}
		if(flagInsert==true && flagDelete==true) bandera= 1;
		if(flagInsert==true && flagDelete==false) bandera= 2;
		if(flagInsert==false && flagDelete==true) bandera= 3;
		if(flagInsert==false && flagDelete==false) bandera= 4;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return bandera ;		
	}
	
	public boolean verificarEstudianteMateria(String materia, String carnet){
		String hql = "from MateriaInscrita where id.estudiante.carnetEstudiante = ? and id.materiaCiclo.id.materia.idMateria = ?";
		Object[] params = {carnet, new Integer(materia)};
		List list = dao.findByProps(hql, params);
		if(list.size() != 0)
			return false;
		return true;
	}
	
}
