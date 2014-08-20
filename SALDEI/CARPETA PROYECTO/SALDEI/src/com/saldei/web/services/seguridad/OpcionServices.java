/**
 * Proyecto: SATC
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.Opcion;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.seguridad.OpcionDto;
import com.saldei.web.form.seguridad.OpcionForm;

public class OpcionServices {
	
	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc = new JdbcHelper();
	
	public List getOpciones(){
		String hql = "from Opcion o where o.estOpcion = 'A' order by o.nomOpcion";
		return dao.find(hql);		
	}
	
	public boolean existeOpcion(String nombre, String id){
		String hql = "";
		if(id == null || id.equals("")){
			hql = "from Opcion where lower(nomOpcion) = lower(?)";
			String[] params = {nombre};
			List list = dao.findByProps(hql, params);
			if(list.size() == 0)
				return true;
			return false;
		}else{
			hql = "from Opcion where lower(nomOpcion) = lower(?) and idOpcion = ?";
			Object[] params = {nombre, new Integer(id)};
			List list = dao.findByProps(hql, params);
			if(list.size() == 0)
				return true;
			return false;
		}
	}
	
	public ArrayList findAllOpciones(){
		String hql = "from Opcion o order by o.nomOpcion";
		List list = dao.find(hql);
		ArrayList<OpcionDto> listx = new ArrayList<OpcionDto>();
		OpcionDto dto = null;
		for(int i=0; i<list.size(); i++){
			dto = new OpcionDto();
			Opcion opcion = (Opcion) list.get(i);
			dto.setPk(String.valueOf(opcion.getIdOpcion()));
			dto.setDesc(opcion.getDescOpcion());
			if(opcion.getEstOpcion().equals("A"))
				dto.setEst("Activo");
			else
				dto.setEst("Inactivo");
			dto.setMetodo(opcion.getMetodo());
			dto.setNombre(opcion.getNomOpcion());
			String padre = "";
			if(!opcion.getIdOpcionPadre().equals("0")){
				padre = this.getNombrePadreOpcion(new Integer(opcion.getIdOpcionPadre()));
				dto.setPadre(padre);
			}				
			dto.setOrden(String.valueOf(opcion.getOrden()));
			dto.setUrl(opcion.getUrlOpcion());
			
			dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=getDataToPutForm&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + opcion.getEstOpcion()+ "&padre=" + opcion.getIdOpcionPadre() + "&codigo=" + opcion.getIdOpcion()+ "'>Modificar</a> | <a href='" + Constants.contextPath + "opcion.do?cmd=delete&codigo=" + dto.getPk() + "'>Eliminar</a>");			
			listx.add(dto);
		}
		return listx;
	}
	
	public ArrayList[] findEvery(boolean flag){
		ArrayList[] listas = new ArrayList[2];
		String hqlActivo = "from Opcion where estOpcion = 'A'";
		List listActivo = dao.find(hqlActivo);
		String hqlInactivo = "from Opcion where estOpcion = 'I'";
		List listInactivo = dao.find(hqlInactivo);
		listas[0] = this.prepareList(listActivo, flag);
		listas[1] = this.prepareList(listInactivo, flag);
		return listas;
	}

	
	public ArrayList[] find(OpcionForm oform,boolean flag){
		ArrayList[] listas = new ArrayList[2];
		if(oform.getDesc().equals("") && oform.getMetodo().equals("") && oform.getNombre().equals("") && oform.getOrden().equals("") && oform.getSeparador().equals("Seleccione") && oform.getPadre().equals("Seleccione")){
			String hqlActivo = "from Opcion where estOpcion = 'A'";
			List listActivo = dao.find(hqlActivo);
			String hqlInactivo = "from Opcion where estOpcion = 'I'";
			List listInactivo = dao.find(hqlInactivo);
			listas[0] = this.prepareList(listActivo, flag);
			listas[1] = this.prepareList(listInactivo, flag);
			return listas;			
		}
		else{
			if(oform.getDesc().equals("") && oform.getMetodo().equals("") && oform.getNombre().equals("") && oform.getOrden().equals("") && oform.getSeparador().equals("Seleccione") && oform.getPadre().equals("Seleccione")){
				String hqlActivo = "from Opcion where estOpcion = 'A'";
				List listActivo = dao.find(hqlActivo);
				String hqlInactivo = "from Opcion where estOpcion = 'I'";
				List listInactivo = dao.find(hqlInactivo);
				listas[0] = this.prepareList(listActivo, flag);
				listas[1] = this.prepareList(listInactivo, flag);
				return listas;
			}else{
				String estadox = "";
				ArrayList<OpcionDto> listActivo = new ArrayList<OpcionDto>();
				ArrayList<OpcionDto> listInactivo = new ArrayList<OpcionDto>();
				String query = "";
				if(oform.getNombre().equals("") && oform.getDesc().equals("") && oform.getUrl().equals("") && oform.getOrden().equals("") && 
						oform.getMetodo().equals("")  && oform.getPadre().equals("Seleccione") && oform.getSeparador().equals("Seleccione"))
					query = "select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,est_opcion,metodo,url_opcion,orden,is_separador from seguridad.opcion where est_opcion='A' ";
				else
					query = "select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,est_opcion,metodo,url_opcion,orden,is_separador from seguridad.opcion where id_opcion is not null ";
				if(!oform.getNombre().equals(""))
					query += "and lower(nom_opcion) like lower('" + oform.getNombre() + "%') " ;
				if(!oform.getDesc().equals(""))
					query += "and lower(desc_opcion) like lower('" + oform.getDesc() + "%') " ;
				if(!oform.getUrl().equals(""))
					query += "and lower(url_opcion) like lower('" + oform.getUrl() + "%') " ;
				if(!oform.getOrden().equals(""))
					query += "and orden = " + oform.getOrden() + " " ;
				if(!oform.getMetodo().equals(""))
					query += "and lower(metodo) like lower('" + oform.getMetodo() + "%') " ;				
				if(!oform.getPadre().equals("Seleccione"))
					query += "and id_opcion_padre = " + oform.getPadre() + " " ;
				if(!oform.getSeparador().equals("Seleccione"))
					query += "and is_separador = '" + oform.getSeparador() + "' " ;
				List lst = jdbc.getQuery(query, null);
				OpcionDto dto = null;
				for(int i=0; i<lst.size(); i++){
					dto = new OpcionDto();
					DynaBean dyna = (DynaBean) lst.get(i);
					dto.setPk(dyna.get("id_opcion").toString());
					if(dyna.get("nom_opcion") != null)
						dto.setNombre(dyna.get("nom_opcion").toString());
					if(dyna.get("desc_opcion") != null)
						dto.setDesc(dyna.get("desc_opcion").toString());
					if(dyna.get("url_opcion") != null)
						dto.setUrl(dyna.get("url_opcion").toString());
					if(dyna.get("orden") != null)
						dto.setOrden(dyna.get("orden").toString());
					if(dyna.get("metodo") != null)
						dto.setMetodo(dyna.get("metodo").toString());
					if(dyna.get("is_separador") != null)
						dto.setSeparador(dyna.get("is_separador").toString());					
					if(dyna.get("est_opcion") != null){
						String est = dyna.get("est_opcion").toString();
						if(est.equals("A")){
							dto.setEst("Activo");
							estadox = "Desactivar";
						}							
						else{
							dto.setEst("Inactivo");
							estadox = "Activar";
						}
							
					}				
					String padre = "";
					if(dyna.get("id_opcion_padre") != null){
						dto.setPadre(this.getNombrePadreOpcion(new Integer(dyna.get("id_opcion_padre").toString())));
						padre = dyna.get("id_opcion_padre").toString();
						dto.setIdPadre(padre);
					}
					if(flag)
						dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=hash&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + dyna.get("est_opcion").toString() + "&padre=" + dyna.get("id_opcion_padre").toString() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>" + estadox + "</a>");
					else
						//dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=getDataToPutForm&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + dyna.get("est_opcion").toString() + "&padre=" + padre + "&codigo=" + dto.getPk() + "'>Modificar</a>");
						dto.setDelete("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getPk() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getUrl() + "','" + dto.getOrden() + "','" + dto.getMetodo() + "','" + dyna.get("est_opcion").toString() + "','" + padre + "','" + dto.getSeparador() + "')\" > Modificar</a>");					
					if(dto.getEst().equals("Activo"))
						listActivo.add(dto);
					else
						listInactivo.add(dto);			
				}
				listas[0] = listActivo;
				listas[1] = listInactivo;
				return listas;
			}
			
		}
		/*ArrayList[] listas = new ArrayList[2];
		ArrayList<OpcionDto> listActivo = new ArrayList<OpcionDto>();
		ArrayList<OpcionDto> listInactivo = new ArrayList<OpcionDto>();
		String query = "";
		if(oform.getNombre().equals("") && oform.getDesc().equals("") && oform.getUrl().equals("") && oform.getOrden().equals("") && 
				oform.getMetodo().equals("") && oform.getEstado().equals("Seleccione") && oform.getPadre().equals("Seleccione"))
			query = "select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,est_opcion,metodo,url_opcion,orden from seguridad.opcion where est_opcion='A' ";
		else
			query = "select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,est_opcion,metodo,url_opcion,orden from seguridad.opcion where id_opcion is not null ";
		if(!oform.getNombre().equals(""))
			query += "and lower(nom_opcion) = lower('" + oform.getNombre() + "') " ;
		if(!oform.getDesc().equals(""))
			query += "and lower(desc_opcion) = lower('" + oform.getDesc() + "') " ;
		if(!oform.getUrl().equals(""))
			query += "and lower(url_opcion) = lower('" + oform.getUrl() + "') " ;
		if(!oform.getOrden().equals(""))
			query += "and orden = " + oform.getOrden() + " " ;
		if(!oform.getMetodo().equals(""))
			query += "and lower(metodo) = lower('" + oform.getMetodo() + "') " ;
		if(!oform.getEstado().equals("Seleccione"))
			query += "and lower(est_opcion) = lower('" + oform.getEstado() + "') " ;
		if(!oform.getPadre().equals("Seleccione"))
			query += "and id_opcion_padre = " + oform.getPadre() + " " ;
		List lst = jdbc.getQuery(query, null);
		OpcionDto dto = null;
		for(int i=0; i<lst.size(); i++){
			dto = new OpcionDto();
			DynaBean dyna = (DynaBean) lst.get(i);
			dto.setPk(dyna.get("id_opcion").toString());
			if(dyna.get("nom_opcion") != null)
				dto.setNombre(dyna.get("nom_opcion").toString());
			if(dyna.get("desc_opcion") != null)
				dto.setDesc(dyna.get("desc_opcion").toString());
			if(dyna.get("url_opcion") != null)
				dto.setUrl(dyna.get("url_opcion").toString());
			if(dyna.get("orden") != null)
				dto.setOrden(dyna.get("orden").toString());
			if(dyna.get("metodo") != null)
				dto.setMetodo(dyna.get("metodo").toString());
			if(dyna.get("est_opcion") != null){
				String est = dyna.get("est_opcion").toString();
				if(est.equals("A"))
					dto.setEst("Activo");
				else
					dto.setEst("Inactivo");
			}				
			String padre = "";
			if(dyna.get("id_opcion_padre") != null){
				dto.setPadre(this.getNombrePadreOpcion(new Integer(dyna.get("id_opcion_padre").toString())));
				padre = dyna.get("id_opcion_padre").toString();
			}
			dto.setDelete("<a href='" + Constants.contextPath + "mopcion.do?cmd=getDataToPutForm&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + dyna.get("est_opcion").toString() + "&padre=" + padre + "&codigo=" + dto.getPk() + "'>Modificar</a> | <a href='" + Constants.contextPath + "mopcion.do?cmd=delete&codigo=" + dto.getPk() + "'>Eliminar</a>");
			if(dto.getEst().equals("A"))
				listActivo.add(dto);
			else
				listInactivo.add(dto);			
		}
		listas[0] = this.prepareList(listActivo);
		listas[1] = this.prepareList(listInactivo);
		return listas;*/
	}
	
	public ArrayList prepareList(List list, boolean flag){
		OpcionDto dto = null;
		ArrayList<OpcionDto> lst = new ArrayList<OpcionDto>();
		for(int i=0; i<list.size(); i++){
			Opcion opcion = (Opcion) list.get(i);
			dto = new OpcionDto();
			dto.setDesc(opcion.getDescOpcion());
			dto.setMetodo(opcion.getMetodo());
			dto.setNombre(opcion.getNomOpcion());
			dto.setPadre(this.getNombrePadreOpcion(opcion.getIdOpcionPadre()));
			dto.setIdPadre(String.valueOf(opcion.getIdOpcionPadre()));
			dto.setOrden(String.valueOf(opcion.getOrden()));
			dto.setUrl(opcion.getUrlOpcion());
			dto.setPk(String.valueOf(opcion.getIdOpcion()));
			dto.setSeparador(opcion.getIsSeparador());
			String estadox = "";
			if(opcion.getEstOpcion().equals("A")){
				dto.setEst("Activo");
				estadox = "Desactivar";
			}else{
				dto.setEst("Inactivo");
				estadox = "Activar";
			}
			//dto.setDelete("<a href='" + Constants.contextPath + "mopcion.do?cmd=getDataToPutForm&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + opcion.getEstOpcion() + "&padre=" + opcion.getIdOpcionPadre() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>Modificar</a> || <a href='" + Constants.contextPath + "mopcion.do?cmd=hash&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + opcion.getEstOpcion() + "&padre=" + opcion.getIdOpcionPadre() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>" + estadox + "</a>");
			if(flag)
				dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=hash&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + opcion.getEstOpcion() + "&padre=" + opcion.getIdOpcionPadre() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>" + estadox + "</a>");
			else
				//dto.setDelete("<a href='" + Constants.contextPath + "opcion.do?cmd=getDataToPutForm&codigo=" + dto.getPk()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc()+ "&url=" + dto.getUrl()+ "&orden=" + dto.getOrden()+ "&metodo=" + dto.getMetodo()+ "&estado=" + opcion.getEstOpcion() + "&padre=" + opcion.getIdOpcionPadre() + "&codigo=" + dto.getPk() + "&separador=" + dto.getSeparador()+ "'>Modificar</a>");
				dto.setDelete("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getPk() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getUrl() + "','" + dto.getOrden() + "','" + dto.getMetodo() + "','" + opcion.getEstOpcion() + "','" + opcion.getIdOpcionPadre() + "','" + dto.getSeparador() + "')\" > Modificar</a>");
			lst.add(dto);
		}
		return lst;
	}
	
	public ArrayList removeItemToList(ArrayList list, String codigo){
		OpcionDto dto = null;
		ArrayList<OpcionDto> lst = new ArrayList<OpcionDto>();
		for(int i=0; i<list.size(); i++){
			dto = (OpcionDto) list.get(i);
			if(!dto.getPk().equals(codigo))
				lst.add(dto);
		}
		return lst;
	}
	
	public void updateAll(ArrayList listActivo, ArrayList listInactivo) throws Exception{
		OpcionDto dto = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (OpcionDto) listActivo.get(i);
			Opcion opcion = new Opcion();
			opcion.setIdOpcion(new Integer(dto.getPk()));
			opcion.setDescOpcion(dto.getDesc());
			opcion.setEstOpcion(opcion.getEstOpcion());
			if(!dto.getPadre().equals(""))
				opcion.setIdOpcionPadre(new Integer(dto.getIdPadre()));
			else
				opcion.setIdOpcionPadre(0);
			if(dto.getSeparador() == null || dto.getSeparador().equals("null") || dto.getSeparador().equals(""))
				opcion.setIsSeparador("N");
			else
				opcion.setIsSeparador(dto.getSeparador());
			opcion.setMetodo(dto.getMetodo());
			opcion.setNomOpcion(dto.getNombre());
			opcion.setOrden(new Long(dto.getOrden()));
			opcion.setUrlOpcion(dto.getUrl());
			if(dto.getEst().equals("Activo"))
				opcion.setEstOpcion("A");
			else
				opcion.setEstOpcion("I");			
			dao.update(opcion);
		}		
		for(int i=0; i<listInactivo.size(); i++){
			dto = (OpcionDto)listInactivo.get(i);
			Opcion opcion = new Opcion();
			opcion.setIdOpcion(new Integer(dto.getPk()));
			opcion.setDescOpcion(dto.getDesc());
			opcion.setEstOpcion(opcion.getEstOpcion());
			if(!dto.getPadre().equals(""))
				opcion.setIdOpcionPadre(this.getIdPadreOpcion(dto.getPadre()));
			else
				opcion.setIdOpcionPadre(0);
			if(dto.getSeparador() == null || dto.getSeparador().equals("null") || dto.getSeparador().equals(""))
				opcion.setIsSeparador("N");
			else
				opcion.setIsSeparador(dto.getSeparador());
			opcion.setMetodo(dto.getMetodo());
			opcion.setNomOpcion(dto.getNombre());
			opcion.setOrden(new Long(dto.getOrden()));
			opcion.setUrlOpcion(dto.getUrl());
			if(dto.getEst().equals("Activo"))
				opcion.setEstOpcion("A");
			else
				opcion.setEstOpcion("I");			
			dao.update(opcion);
		}
	}
	
	public String getNombrePadreOpcion(int id){
		String nombre = "";
		String hql = "select nomOpcion from Opcion where idOpcion = ?";
		Object[] params = {id};
		List list = dao.findByProps(hql, params);
		if(list.size() != 0)
			nombre = (String) list.get(0);
		return nombre;
	}
	
	public int getIdPadreOpcion(String id){
		Integer nombre = 0;
		String hql = "select idOpcion from Opcion where lower(nomOpcion) = ?";
		Object[] params = {id.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() != 0)
			nombre = (Integer) list.get(0);
		return nombre;
	}


	public boolean isNullOpcionForm(OpcionForm oform){
		if(oform.getNombre().equals("") || oform.getDesc().equals("") || oform.getUrl().equals("") || oform.getOrden().equals("") || oform.getMetodo().equals(""))
			return true;		
		return false;
	}
	
	public boolean isNumberOrden(String orden){
		return Util.esNumero(orden);
	}
	
	public boolean isNullOpcionFormDml(OpcionForm oform){
		if(oform.getCodigo() == null || oform.getNombre().equals("") || oform.getOrden().equals(""))
			return true;
		return false;
	}
	
	public boolean save(OpcionForm oform){
		try{
			String padre = "", query = "";		
			boolean flag = this.existeOpcion(oform.getNombre(), oform.getCodigo());
			if(!flag)
				return false;
			String pk = this.getSequenceOpcion();
			this.nextOrden(oform.getOrden(),pk,oform.getPadre());
			String separador = oform.getSeparador();
			if(separador.equals("Seleccione") || separador.equals("N"))
				separador = "N";
			else
				separador = "S";
			if(!oform.getPadre().equals("Seleccione")){
				padre = oform.getPadre();
				query = "insert into seguridad.opcion(id_opcion,id_opcion_padre,nom_opcion,desc_opcion," +
				"url_opcion,orden,metodo,est_opcion,is_separador) values(" + pk + "," + padre + ",'" + oform.getNombre() + "','" +
				oform.getDesc() + "','" + oform.getUrl() + "'," + oform.getOrden() + ",'" + oform.getMetodo() + "','A','" + separador + "')";
			}else{
				query = "insert into seguridad.opcion(id_opcion,nom_opcion,desc_opcion," +
				"url_opcion,orden,metodo,est_opcion,id_opcion_padre,is_separador) values(" + pk + ",'" + oform.getNombre() + "','" +
				oform.getDesc() + "','" + oform.getUrl() + "'," + oform.getOrden() + ",'" + oform.getMetodo() + "','A',0,'" + separador + "')";
			}		
			jdbc.saveOrUpdate(query, null);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}	
	
	private void nextOrden(String orden,String idOpcion,String idPadre){
		if(!idPadre.equals("Seleccione") && !idPadre.equals("0")){
			String hql = "from Opcion where idOpcionPadre = ? and idOpcion <> ?";
			Object[] params = {new Integer(idPadre),new Integer(idOpcion)};
			List list = dao.findByProps(hql, params);
			for(int i=0; i<list.size(); i++){
				Opcion opcion = (Opcion) list.get(i);				
				Integer numOrden = new Integer(orden);
				int numero = opcion.getOrden().intValue();
				if(numero >= numOrden){
					opcion.setOrden((new Long(opcion.getOrden()) + 1));
					dao.update(opcion);
				}					
			}
		}
	}
	
	public boolean update(OpcionForm oform){
		try{
			String padre = "";
			this.nextOrden(oform.getOrden(),oform.getCodigo(),oform.getPadre());
			if(!oform.getPadre().equals("Seleccione")){
				padre = oform.getPadre();
			}else
				padre = "0";
			String separador = oform.getSeparador();
			if(separador.equals("Seleccione") || separador.equals("N"))
				separador = "N";
			else
				separador = "S";
			String sql = "update seguridad.opcion set is_separador = '" + separador + "', id_opcion_padre = " + padre + ", nom_opcion = '" + oform.getNombre() + "', desc_opcion = '" + oform.getDesc() + "'," +
					"url_opcion = '" + oform.getUrl() + "', orden = " + oform.getOrden() + ", metodo = '" + oform.getMetodo() + "'  where id_opcion = " + oform.getCodigo();
			boolean flag = jdbc.saveOrUpdate(sql, null);
			return flag;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}
	
	public void delete(OpcionForm oform){
		String sql = "update seguridad.opcion set est_opcion = 'I' where id_opcion = " + oform.getCodigo();
		jdbc.saveOrUpdate(sql);		
	}
	
	private String getSequenceOpcion(){
		String sql = "select max(id_opcion) + 1 as num from seguridad.opcion";
		List list = jdbc.getQuery(sql, null);
		DynaBean dyna = (DynaBean) list.get(0);
		if(dyna.get("num") == null)
			return "1";
		return dyna.get("num").toString();
	}


}
