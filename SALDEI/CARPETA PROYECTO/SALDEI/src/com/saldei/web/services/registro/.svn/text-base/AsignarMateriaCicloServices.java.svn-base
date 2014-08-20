package com.saldei.web.services.registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.util.commons.Constants;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.AsignarMateriaCicloDto;
import com.saldei.web.form.registro.AsignarMateriaCicloForm;

public class AsignarMateriaCicloServices {
	
	private JdbcHelper jdbc = new JdbcHelper();
	private HibDAO dao = new HibDAOImpl();
	
	public List getMateria(){
		try{
			String hql = "from Ciclo where estCiclo = 'A' order by idCiclo";
			List list = dao.find(hql);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	/*private String getCicloActivo(){
		String hql = "from Ciclo where cicloActivo = 'S'";
		List list = dao.find(hql);
		if(list.size() != 0){
			Ciclo ciclo = (Ciclo) list.get(0);
			return ciclo.getIdCiclo();
		}
		return "";
	}*/
	
	public ArrayList[] getMaterias(AsignarMateriaCicloForm asig){
		try{
			ArrayList[] listas = new ArrayList[2];
			String query = "select a.cod_materia as idmat, a.nom_materia as nommat, a.id_materia as pkmat " +
					"from   registro.materia a  " +
					"where a.est_materia = ? ";
			Object[] params = {"A"};
			List list = jdbc.getQuery(query, params);
			ArrayList newList = this.prepareDisplayToJsp(list, asig.getCiclo(), false);
			
//			String queryB = "select a.cod_materia as idmat, a.nom_materia as nommat, a.id_materia as pkmat, c.id_seccion as seccion " +
//			"from   registro.materia a , registro.seccion_x_materia c " +
//			"where c.id_materia = a.id_materia " +
//			"and     a.est_materia = ? " +					
//			"and    (a.id_materia, c.id_seccion) in (select b.id_materia, b.id_seccion from registro.materia_ciclo b where b.id_ciclo = ?)  order by 2, 4";
			String queryB = "select a.cod_materia as idmat, a.nom_materia as nommat, b.id_materia as pkmat, b.id_seccion as seccion " +
					"from registro.materia a, registro.materia_ciclo b " +
					"where a.est_materia = ? " +
					"and b.id_ciclo = ? " +
					"and a.id_materia = b.id_materia " +
					"and b.est_mat_ciclo = 'A' " +
					"order by 2,4";
			Object[] paramsB = {"A", asig.getCiclo()};
			List listB = jdbc.getQuery(queryB, paramsB);
			ArrayList newListB = this.prepareDisplayToJsp(listB, asig.getCiclo(), true);			
			listas[0] = newList;
			listas[1] = newListB;
			return listas;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean validarAño(String año){
		if(!Util.esNumero(año))
			return false;
		if(año.length() != 4)
			return false;
		return true;
	}
	
	private ArrayList prepareDisplayToJsp(List list, String ciclo, boolean flag){
		AsignarMateriaCicloDto dto ;
		ArrayList<AsignarMateriaCicloDto> newList = new ArrayList<AsignarMateriaCicloDto>();
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();
			dto = new AsignarMateriaCicloDto();			
			dto.setCiclo(ciclo);
			if(dyna.get("idmat") != null)
				dto.setIdMateria(dyna.get("idmat").toString());
			if(dyna.get("nommat") != null)
				dto.setMateria(dyna.get("nommat").toString());
			if(dyna.get("pkmat") != null)
				dto.setPkMat(dyna.get("pkmat").toString());							
			String estadox = "";
			if(flag){
				dto.setEstado("A");
				estadox = "Remover";
				if(dyna.get("seccion") != null)
					dto.setSeccion(dyna.get("seccion").toString());
				dto.setAccion("<a href='" + Constants.contextPath + "asigMatCiclo.do?cmd=hash&idSeccion=" + dto.getSeccion() + "&estado=" + dto.getEstado() + "" +
						"&pkMat=" + dto.getPkMat() + "&materia=" + dto.getMateria() + "&idCiclo=" + dto.getCiclo() + "&idMateria=" + dto.getIdMateria()+ "" +
						"'>" + estadox + "</a>");
			}				
			else{
				dto.setEstado("I");
				estadox = "Agregar";
				dto.setAccion("<a href='" + Constants.contextPath + "asigMatCiclo.do?cmd=hash&estado=" + dto.getEstado() + "" +
						"&pkMat=" + dto.getPkMat() + "&materia=" + dto.getMateria() + "&idCiclo=" + dto.getCiclo() + "&idMateria=" + dto.getIdMateria()+ "" +
						"'>" + estadox + "</a>");
			}			
			newList.add(dto);
		}
		return newList;
	}
	
	public ArrayList removeItemToList(ArrayList list, String codigo, String seccion){
		AsignarMateriaCicloDto dto = null;
		ArrayList<AsignarMateriaCicloDto> lst = new ArrayList<AsignarMateriaCicloDto>();
		for(int i=0; i<list.size(); i++){
			dto = (AsignarMateriaCicloDto) list.get(i);
			if(dto.getIdMateria().toLowerCase().equals(codigo.toLowerCase()) && dto.getSeccion().equals(seccion)){				
			}else{
				int sec = new Integer(dto.getSeccion());
				if(sec > new Integer(seccion)){
					int s = new Integer(dto.getSeccion()) - 1;
					dto.setSeccion(String.valueOf(s));
					dto.setAccion("<a href='" + Constants.contextPath + "asigMatCiclo.do?cmd=hash&estado=" + dto.getEstado() + "" +
							"&pkMat=" + dto.getPkMat() +  "&materia=" + dto.getMateria() + "&idCiclo=" + dto.getCiclo() + "&idMateria=" + dto.getIdMateria()+ "" +
							"&idSeccion=" + dto.getSeccion() + "'>Remover</a>");
				}					
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public void saveAll(ArrayList list, String idCiclo){
		try{
			if(list == null || list.size() == 0){
					String sql = "delete from registro.materia_ciclo where id_ciclo = '" + idCiclo + "'"; 
					jdbc.delete(sql, null);
			}else{
				String sql = "delete from registro.materia_ciclo where id_ciclo = '" + idCiclo + "'"; 
				jdbc.delete(sql, null);
				for(Iterator iter = list.iterator(); iter.hasNext(); ){
					AsignarMateriaCicloDto dto = (AsignarMateriaCicloDto) iter.next();
//					this.validarSeccion(dto.getPkMat(), dto.getSeccion());
					String insert = "insert into registro.materia_ciclo(id_ciclo, id_materia, id_seccion, est_mat_ciclo) " +
							"values(?,?,?,?)";
					Object[] params = {idCiclo, new Integer(dto.getPkMat()), new Integer(dto.getSeccion()), "A"};
					jdbc.saveOrUpdate(insert, params);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void validarSeccion(String materia, String seccion){
		String hql = "from SeccionXMateria where id.materia.idMateria = ? and id.idSeccion = ?";
		Object[] params = {new Integer(materia), new Integer(seccion)};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0){
			String sql = "insert into registro.seccion_x_materia(id_materia, id_seccion,est_secc_mat) values (?, ?, ?)";
			Object[] paramsx = {new Integer(materia), new Integer(seccion), "A"};
			jdbc.saveOrUpdate(sql, paramsx);
		}
	}
	
	/*public int existMatInList(String id, ArrayList list){
		AsignarMateriaCicloDto dto = null;
		String repeat = "";
		for(int i=0; i<list.size(); i++){
			dto = (AsignarMateriaCicloDto) list.get(i);
			if(dto.getIdMateria().equals(id))
				repeat += dto.getSeccion() + "&"; 
		}
		String[] secciones = repeat.split("&");
		if(secciones.length == 1)
			if(secciones[0].equals(""))
				return 1;
		String[] sec = this.numberInOrder(secciones); 
		for(int i=0; i<sec.length; i++){
			int num = i + 1;
			if(num < new Integer(sec[i]))
				return num;
		}
		return 1;
	}*/
	
	public int existMatInList(String id, ArrayList list){
		AsignarMateriaCicloDto dto = null;
		int repeat = 1;
		for(int i=0; i<list.size(); i++){
			dto = (AsignarMateriaCicloDto) list.get(i);
			if(dto.getIdMateria().equals(id))
				repeat = repeat + 1; 
		}
		return repeat;
	}
	
	/*private String[] numberInOrder(String[] n){
		int aux;
        for(int i=0; i<n.length-1; i++){
            for(int j=i+1; j<n.length; j++){
                if(new Integer(n[i]) > new Integer(n[j])){
                	aux = new Integer(n[j]);
                	n[j] = n[i];
                    n[i]= String.valueOf(aux);
                }
            }
        }
        return n;
	}*/
	
}//class
