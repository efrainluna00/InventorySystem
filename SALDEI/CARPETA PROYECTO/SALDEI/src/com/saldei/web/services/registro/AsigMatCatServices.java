package com.saldei.web.services.registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.CargoUsuarioDei;
import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.AsigMatCatServicesDto;
import com.saldei.web.form.registro.AsigMatCatForm;

public class AsigMatCatServices {

	private JdbcHelper jdbc = new JdbcHelper();
	private HibDAO dao = new HibDAOImpl();
	
	public ArrayList getCiclo(){
		try{
			String query = "select distinct id_ciclo from registro.materia_ciclo  order by id_ciclo";
			List list = jdbc.getQuery(query, null);
			Ciclo ciclo = null;
			ArrayList<Ciclo> lst = new ArrayList<Ciclo>();
			for(Iterator iter= list.iterator(); iter.hasNext(); ){
				ciclo = new Ciclo();
				DynaBean dyna = (DynaBean) iter.next();
				ciclo.setIdCiclo(dyna.get("id_ciclo").toString());
				lst.add(ciclo);
			}
			return lst;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public ArrayList getMaterias(AsigMatCatForm asig){
		try{
			ArrayList<AsigMatCatServicesDto> lst = new ArrayList<AsigMatCatServicesDto>();
			AsigMatCatServicesDto dto;
			String query = "select a.nom_materia || ' - 0' || b.id_seccion  as nombre, a.id_materia  || '-' || b.id_seccion as idmateria " +
						"from registro.materia a, registro.materia_ciclo b " +						
						"where a.est_materia = ? " +
						"and a.id_materia = b.id_materia " +
						"and b.id_ciclo = ?  order by a.nom_materia";
			Object[] params = {"A", asig.getCiclo()};
			List list = jdbc.getQuery(query, params);
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				DynaBean dyna = (DynaBean) iter.next();
				dto = new AsigMatCatServicesDto();
				dto.setNombreMateria(dyna.get("nombre").toString());
				dto.setIdMateria(dyna.get("idmateria").toString());
				lst.add(dto);
			}
			return lst;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList[] makeQuery(AsigMatCatForm asig, String idMateria){
		ArrayList[] listas = new ArrayList[2];
		try{
			String query = "select distinct u.id_usuario as idusuario, u.primer_nom || ' ' || u.primer_ape as nombre, c.id_cargo as idcargo, m.descripcion as desc " +
					"from seguridad.usuario u, seguridad.cargo_usuario_dei c, registro.multicode m " +
					"where u.id_usuario      = c.id_usuario  " +
					"and   m.id_multicode    = c.id_cargo " +
					"and   u.id_usuario not in (select d.id_usuario from registro.catedratico_materia ca, seguridad.cargo_usuario_dei d where ca.id_ciclo =? and id_materia = ? and id_seccion = ? and ca.id_cargo_usr = d.id_cargo_usr) " +
					"and   m.id_multicode in (select valor from registro.parametro where nom_parametro = ?) order by u.id_usuario" ;					
			String queryB = "select distinct u.id_usuario as idusuario, u.primer_nom  || ' ' || u.primer_ape as nombre, c.id_cargo as idcargo, m.descripcion as desc " +
			"from seguridad.usuario u, seguridad.cargo_usuario_dei c, registro.multicode m " +
			"where u.id_usuario      = c.id_usuario  " +
			"and   m.id_multicode    = c.id_cargo " +
			"and   u.id_usuario in (select d.id_usuario from registro.catedratico_materia ca, seguridad.cargo_usuario_dei d where ca.id_ciclo =? and id_materia = ? and id_seccion = ? and ca.id_cargo_usr = d.id_cargo_usr) " +
			"and   m.id_multicode in (select valor from registro.parametro where nom_parametro = ?) order by u.id_usuario" ;
			String[] arr = idMateria.split("-");
			Object[] params = {asig.getCiclo(), arr[0], arr[1], Constants.Parametro_CATEDRATICO};
			List listA = jdbc.getQuery(query, params);
			List listB = jdbc.getQuery(queryB, params);
			ArrayList lstA = this.prepareDisplayTag(listA, true, idMateria);
			ArrayList lstB = this.prepareDisplayTag(listB, false, idMateria);
			listas[0] = lstA;
			listas[1] = lstB;
			return listas;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList prepareDisplayTag(List list, boolean flag, String idMateria){
		ArrayList<AsigMatCatServicesDto> lst = new ArrayList<AsigMatCatServicesDto>();
		try{
			AsigMatCatServicesDto dto = null;
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				DynaBean dyna = (DynaBean) iter.next();
				dto = new AsigMatCatServicesDto();
				dto.setIdUsuario(dyna.get("idusuario").toString());
				dto.setNombreUsuario(dyna.get("nombre").toString());
				dto.setCargo(dyna.get("desc").toString());
				dto.setIdCargo(dyna.get("idcargo").toString());
				String[] arr = idMateria.split("-");
				dto.setIdSeccion(arr[1]);
				dto.setIdMateria(arr[0]);
				String estadox = "";
				if(!flag)
					estadox = "Remover";
				else
					estadox = "Asignar";
				dto.setAccion("<a href='" + Constants.contextPath + "asigMatCat.do?cmd=hash&idUsuario=" + dto.getIdUsuario() + "" +
						"&idCargo=" + dto.getIdCargo() + "&idMateria=" +  arr[0] + "&estado=" + estadox + "" +
								"&idSeccion=" + dto.getIdSeccion() + "&nombreUsuario=" + dto.getNombreUsuario() + "&cargo= " + dto.getCargo() + "'>" + estadox +"</a>");
				lst.add(dto);
			}
			return lst;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList removeFormToList(ArrayList list, String id){
		ArrayList<AsigMatCatServicesDto> newList = new ArrayList<AsigMatCatServicesDto>();
		for(int i=0; i<list.size(); i++){
			AsigMatCatServicesDto dto = (AsigMatCatServicesDto) list.get(i);
			if(!dto.getIdUsuario().equals(id))
				newList.add(dto);
		}
		return newList;
	}

	public boolean saveAll(ArrayList list, String idCiclo, String idMateria){		
		try{
			boolean flagx = true;
			boolean flag = true;
			if(list.size() == 0){
				String[] idmat = idMateria.split("-");
				String delete = "delete from registro.catedratico_materia where id_materia = " + idmat[0] + " " +
				"and id_ciclo = '" + idCiclo + "'  and est_cat_mat = 'A' and id_seccion = " + idmat[1]+ "";					
				jdbc.delete(delete, null);
			}
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				AsigMatCatServicesDto dto = (AsigMatCatServicesDto) iter.next();
				int o = this.getCargoUsr(dto.getIdUsuario(), dto.getIdCargo());
				if(flagx){					
					//String delete = "delete from registro.catedratico_materia where id_cargo_usr = " + o + "  and id_materia = " + dto.getIdMateria() + " " +
					//"and id_seccion = " + dto.getIdSeccion() + "and id_ciclo = '" + idCiclo + "'  and est_cat_mat = 'A'";
					String delete = "delete from registro.catedratico_materia where id_materia = " + dto.getIdMateria() + " " +
					"and id_seccion = " + dto.getIdSeccion() + "and id_ciclo = '" + idCiclo + "'  and est_cat_mat = 'A'";
					jdbc.delete(delete, null);
					flagx = false;
				}
				String sql = "insert into registro.catedratico_materia(id_cargo_usr, id_materia, id_seccion, id_ciclo," +
						"est_cat_mat) values(" + o + ", " + dto.getIdMateria() + ", " + dto.getIdSeccion() + ", '" + idCiclo + "', 'A')";				
				flag = jdbc.saveOrUpdate(sql, null);				
			}
			return flag;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public int getCargoUsr(String usuario, String cargo){
		try{
			String hql = "from CargoUsuarioDei where usuario.idUsuario = ? and idCargo = ?";
			Object[] params = {usuario, new Integer(cargo)};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				CargoUsuarioDei cud = (CargoUsuarioDei) list.get(0);
				return cud.getIdCargoUsr();
			}
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}//class
