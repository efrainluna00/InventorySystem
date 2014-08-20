package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.Carrera;
import com.saldei.hibernate.tables.CarreraId;
import com.saldei.hibernate.tables.CarreraVigencia;
import com.saldei.hibernate.tables.CarreraVigenciaId;
import com.saldei.hibernate.tables.Multicode;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.administracion.CarreraDto;
import com.saldei.web.form.administracion.CarreraForm;
import com.saldei.util.commons.Util;
import com.saldei.hibernate.querys.QuerysAdministracion;

public class CarreraServices {
	
	private HibDAO dao = new HibDAOImpl();
	private Util util= new Util();
	private JdbcHelper jdbc = new JdbcHelper();
	
	/**
	 * Obtiene la lista de Facultades
	 * @return  True | False
	 */
	public List getFacultad(){
		String hql = "from Multicode where tipoMulticode.idTipoMulticode = (select valor from Parametro where nomParametro = '" + Constants.Parametro_FACULTAD + "')";
		List list = dao.find(hql);
		return list;
	}
	
	/**
	 * Cambia los botones
	 * @param  request Objeto del tipo HttpServletRequest
	 */
	public void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExtCarrera") == null || request.getSession().getAttribute("buttonsExtCarrera").toString().equals("firstButtonCarrera")){
			request.getSession().setAttribute("primeroCarrera", "<input type='button' value='Guardar' name='btnSave' id='btnSave' onclick='saveOutFunction();' class='ButtonText'>"); //
			request.getSession().setAttribute("segundoCarrera", "<input type='button' value='Actualizar' name='btnUpdate' onclick='updateOutFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroCarrera", "Mostrar");//
			request.getSession().setAttribute("cuartoCarrera", "Limpiar");//
			request.getSession().setAttribute("quintoCarrera", "Activar");//
			request.getSession().setAttribute("modulesCarrera", Constants.legend_first_module);//
		}else{
			request.getSession().setAttribute("primeroCarrera", "<input type='button' value='Guardar Cambios' name='btnSave' onclick='saveFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("segundoCarrera", "<input type='button' value='Cancelar Cambios' name='btnUpdate' onclick='updateFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroCarrera", "Mostrar");//
			request.getSession().setAttribute("cuartoCarrera", "Limpiar");//
			request.getSession().setAttribute("quintoCarrera", "Modificar");//
			request.getSession().setAttribute("modulesCarrera", Constants.legend_second_module);//
		}
	}
	
	/**
	 * Da a conocer la accion realizando Moficiar o Activacion
	 * @param  request Objeto del tipo HttpServletRequest
	 * @return  True | False
	 */
	public boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExtCarrera");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButtonCarrera"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	/**
	 * Busqueda deCarrera
	 * @param  cf Formulario de Carrera
	 * @param  flag determina que tipo de accion se va ejecutar
	 * @return  Lista
	 */
	public ArrayList[] find(CarreraForm cf, boolean flag){		
		String hql = "from Carrera where estCarrera is not null ";
		if(! cf.getNombreCarrera().equals(""))
			hql += "and lower(nomCarrera) like '" + cf.getNombreCarrera().toLowerCase() + "%' ";
		if(! cf.getIdFacultad().equals("Seleccione"))
			hql += "and multicode.idMulticode = " + cf.getIdFacultad() + " ";
//		if(! cf.getIdCarrera().equals("Seleccione"))
//			hql += "and lower(idCarrera) like '" + cf.getIdCarrera() + "%' ";
		hql += "order by nomCarrera";
		List list = dao.findSession(hql);
		ArrayList[] listas = this.getBothList(list);
		ArrayList[] lst = new ArrayList[2];
		lst[0] = this.getDtoCarrera(listas[0],flag);
		lst[1] = this.getDtoCarrera(listas[1],flag);
		dao.closeSession();
		return lst;
	}
	
	/**
	 * Obtiene AMbas listas
	 * @param  list Listas
	 * @return  Lista
	 */
	private ArrayList[] getBothList(List list){
		ArrayList<Carrera> listActivas = new ArrayList<Carrera>();
		ArrayList<Carrera> listInactivas = new ArrayList<Carrera>();
		ArrayList[] listas = new ArrayList[2];
		try{
			for(int i=0; i<list.size(); i++){
				Carrera labo = (Carrera) list.get(i);
				if(labo.getEstCarrera().equals("A"))
					listActivas.add(labo);
				else
					listInactivas.add(labo);
			}
			listas[0] = listActivas;
			listas[1] = listInactivas;
			return listas;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	//Solo actualizar fecha final 
	//Dejar solo la fecha final
	
	/**
	 * Obtiene el DTo de Carrera
	 * @param  list Listas de Carreras
	 * @param flag Bandera de Tipo de modo
	 * @return  Lista
	 */
	private ArrayList getDtoCarrera(List list, boolean flag){
		CarreraDto dto = null;
		ArrayList<CarreraDto> listx = new ArrayList<CarreraDto>();
		HashMap<String, String> fechas = new HashMap<String, String>();		
		String estadox = "";
		for(int i=0; i<list.size(); i++){
			Carrera carr = (Carrera) list.get(i);			
			dto = new CarreraDto();
			dto.setNombre(carr.getNomCarrera());
			dto.setIdCarrera(carr.getId().getIdCarrera());			
			dto.setFacultad(carr.getMulticode().getDescripcion());
			dto.setPlanEstudio(carr.getId().getPlanEstudio());
			fechas = this.fechasVigencia((String)carr.getId().getIdCarrera());			
			dto.setFechaini((String)fechas.get("1"));
			dto.setFechafin((String)fechas.get("2"));			
			
			
			if(!carr.getEstCarrera().equals("A"))	{
					dto.setEstado("Activo");
					estadox = "Activar";
				}else{
					dto.setEstado("Desactivar");
					estadox = "Desactivar";
				}
			if(flag)	
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getNombre() + "','" + dto.getIdCarrera() + "','" + carr.getMulticode().getIdMulticode().toString() + "','" + dto.getPlanEstudio() + "','" + util.fechaDDMMYY(dto.getFechaini(),"-") + "','" + util.fechaDDMMYY(dto.getFechafin(),"-") + "'  )\" > Modificar</a> ");
			else{
				dto.setAccion("<a href='" + Constants.contextPath + "mtoCarr.do?cmd=hash&estado=" + carr.getEstCarrera() + "&facultad=" + dto.getFacultad() + "&nombreCarrera=" + dto.getNombre() + "&nomCarreraHid=" + dto.getIdCarrera() + "&idFacultad=" + carr.getMulticode().getIdMulticode() + "&idCarrera=" + dto.getIdCarrera() + "'>" + estadox +"</a>");
			}
			listx.add(dto);			
		}
		return listx;
	}
	
	/**
	 * Valida el formulario
	 * @param   cf Formulario de Carrera
	 * @return  True | false
	 */
	public boolean isNullLabForm(CarreraForm cf){
		if(cf.getIdCarrera().equals("") || cf.getIdFacultad().equals("Seleccione") || cf.getNombreCarrera().equals(""))
			return false;
		return true;
	}
	
	/**
	 * Guarda una nueva Carrera
	 * @param   cf Formulario de Carrera
	 * @return  True | false
	 */
	public boolean save(CarreraForm cf){
		try{
			Object obj = this.existCarrera(cf.getIdCarrera());
			if(obj == null){
				Carrera carr = new Carrera();
				CarreraId carrId = new CarreraId();
				CarreraVigencia carrV = new CarreraVigencia();
				CarreraVigenciaId carrVid = new CarreraVigenciaId();
				carrId.setIdCarrera(cf.getIdCarrera());
				carrId.setPlanEstudio(cf.getPlanestudio());
				carr.setId(carrId);
				carr.setNomCarrera(cf.getNombreCarrera());
				carr.setEstCarrera("A");
				Multicode multicode = this.getMulticode(cf.getIdFacultad());
				carr.setMulticode(multicode);				
				
				carrVid.setCarrera(carr);
				carrVid.setFechaIni(util.fechaFormatoPostgres(cf.getFechainicial()));
				carrV.setId(carrVid);
				if(!cf.getFechafinal().equals(""))
					carrV.setFechaFin(util.fechaFormatoPostgres(cf.getFechafinal()));				
				dao.save(carr);
				dao.save(carrV);
				return true;
			}
			return false;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Valida si existe una carrera
	 * @param id Identificador de Carrer
	 */
	private Carrera existCarrera(String id){
		String hql = "from Carrera where lower(id.idCarrera) = ?";
		String[] params = {id.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return null;
		else return (Carrera) list.get(0);
	}
	
	/**
	 * Actualiza todas las listas
	 * @param   listActivo Lista de Carrera de Activos
	 * @param   listActivo Lista de Carrera de Inactivos
	 */
	public void updateAll(ArrayList listActivo, ArrayList listInactivo){
		CarreraDto dto = null;
		Carrera carr = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (CarreraDto) listActivo.get(i);
			carr = this.existCarrera(dto.getIdCarrera());			
			carr.setEstCarrera("A");			
			dao.update(carr);
		}
		for(int i=0; i<listInactivo.size(); i++){
			dto = (CarreraDto) listInactivo.get(i);
			carr = this.existCarrera(dto.getIdCarrera());			
			carr.setEstCarrera("I");
			dao.update(carr);
		}
	}
	
	/**
	 * Actualiza una Carrera
	 * @param   cf Formulario de Carrera
	 * @return  True | false
	 */
	public boolean update(CarreraForm cf){
		try{
			Carrera carr = this.existCarrera(cf.getNomCarreraHid());
			CarreraId carrId = new CarreraId();
			if(carr == null)
				return false;
			carrId.setIdCarrera(cf.getIdCarrera());
			carrId.setPlanEstudio(cf.getPlanestudio());
			carr.setId(carrId);
			carr.setNomCarrera(cf.getNombreCarrera());
			carr.setEstCarrera("A");			
			Multicode multicode = this.getMulticode(cf.getIdFacultad());
			carr.setMulticode(multicode);
			dao.update(carr);			
			jdbc.saveOrUpdate(QuerysAdministracion.updateCarreraVigencia(cf.getIdCarrera(), cf.getPlanestudio(), util.getFechaFormato(cf.getFechainicial()), cf.getFechafinal().equals("")?null:util.getFechaFormato(cf.getFechafinal())));
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene los Datos de MUlticode
	 * @param  id  Identificador de Multicode que se desea Obtener
	 * @return  Objeto Multicode
	 */
	private Multicode getMulticode(String id){
		String hql = "from Multicode where idMulticode = ?";
		Object[] params = {new Integer(id)};
		List list = dao.findByProps(hql, params);
		if(list.size() != 0){
			Multicode multicode = (Multicode) list.get(0);
			return multicode;	
		}
		return null;
	}
	
	/**
	 * Busca ambas Listas
	 * @return  Arreglo de Lista
	 */
	public ArrayList[] findAll(){
		try{			
			String hql = "from Carrera";			
			List list = dao.findSession(hql);
			ArrayList[] listas = this.getBothList(list);
			ArrayList[] lst = new ArrayList[2];
			lst[0] = this.getDtoCarrera(listas[0],false);
			lst[1] = this.getDtoCarrera(listas[1],false);
			dao.closeSession();
			return lst;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Remueve un item de una lista
	 * @param  list Lista a la que se eliminara el item 
	 * @param  id  Identificador de Item a remover
	 * @return  Lista
	 */
	public ArrayList removeItemToList(ArrayList list, String id){
		CarreraDto dto = null;
		ArrayList<CarreraDto> lst = new ArrayList<CarreraDto>();
		for(int i=0; i<list.size(); i++){
			dto = (CarreraDto) list.get(i);
			if(!dto.getIdCarrera().equals(id))
				lst.add(dto);
		}
		return lst;
	}
	
	/**
	 * Fechas Vigencias
	 * @param  idCarrera  Identificador de Carrera
	 * @return  Mapa
	 */
	public HashMap<String, String> fechasVigencia(String idCarrera){
		HashMap<String, String> mapa = new HashMap<String, String>();
		List list = null;	
		String sql=QuerysAdministracion.getFechaVigencia("id_carrera", idCarrera, "registro.carrera_vigencia");
		list = jdbc.getQuery(sql,null);
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();
			mapa.put("1", dyna.get("fecha_ini").toString());
			if(dyna.get("fecha_fin")!=null)
				mapa.put("2", dyna.get("fecha_fin").toString());
			else
				mapa.put("2", "");			
		}
		//System.out.println((String)mapa.get("1"));
		//System.out.println((String)mapa.get("2"));
		return mapa;		
	}
	
	/**
	 * Obtiene una Lista de Carreras Activas 
	 * @return Lista con Objetos carrera
	 */	
	public List getCarrera()throws Exception{
		List list  = new LinkedList();
		String hql = QuerysAdministracion.getCarrera();
		list  	   = dao.find(hql);
		List<ElementDto> lst   = new LinkedList<ElementDto>();
		if (list != null && list.size() >0 ){
			for (int i=0; i< list.size(); i++){
				Carrera car  = (Carrera) list.get(i);				
				ElementDto e = new ElementDto();
				e.setElement1(car.getId().getIdCarrera()+" - " + car.getId().getPlanEstudio());
				String name  = car.getNomCarrera() +" - " + car.getId().getPlanEstudio();  
				e.setElement2(name);	
				lst.add(e); 
			}
		}
		return lst;
	}

}//class
