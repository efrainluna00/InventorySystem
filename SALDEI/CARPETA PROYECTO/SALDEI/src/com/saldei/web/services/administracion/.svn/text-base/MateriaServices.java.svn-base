package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.saldei.hibernate.tables.Materia;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.administracion.MateriaDto;
import com.saldei.web.form.administracion.MateriaForm;

public class MateriaServices {
	
	private HibDAO dao = new HibDAOImpl();
	
	/**
	 * Verifica si un Formulario es Valido
	 * @param matform  Formulario de Materia
	 * @return  True | False
	 */	
	public boolean isNullMatForm(MateriaForm matform){
		if(matform.getCodigo().equals("") ||  matform.getNombre().equals("")|| matform.getUv().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Conoce la Accion que se esta ejecutando
	 * @param request Objeto tipo HttpServletRequest
	 * @return  True | False
	 */	
	public boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExtMat");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButtonMat"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	/**
	 * Cambia los botones de Accion
	 * @param request Objeto tipo HttpServletRequest
	 */	
	public void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExtMat") == null || request.getSession().getAttribute("buttonsExtMat").toString().equals("firstButtonMat")){
			request.getSession().setAttribute("primeroMat", "<input type='button' value='Guardar' name='btnSave'  id='btnSave' onclick='saveOutFunction();' class='ButtonText'>"); //
			request.getSession().setAttribute("segundoMat", "<input type='button' value='Actualizar' name='btnUpdate' id='btnUpdate' onclick='updateOutFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroMat", "Mostrar");//
			request.getSession().setAttribute("cuartoMat", "Limpiar");//
			request.getSession().setAttribute("quintoMat", "Activar");//
			request.getSession().setAttribute("modulesMateria", Constants.legend_first_module);//
		}else{
			request.getSession().setAttribute("primeroMat", "<input type='button' value='Guardar Cambios' name='btnSave' onclick='saveFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("segundoMat", "<input type='button' value='Cancelar Cambios' name='btnUpdate' onclick='updateFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroMat", "Mostrar");//
			request.getSession().setAttribute("cuartoMat", "Limpiar");//
			request.getSession().setAttribute("quintoMat", "Modificar");//
			request.getSession().setAttribute("modulesMateria", Constants.legend_second_module);//
		}
	}
	
	/**
	 * Busqueda completa de Materias
	 * @return  Lista 
	 */	
	public ArrayList[] findAll(){
		try{			
			String hql = "from Materia";			
			List list = dao.find(hql);
			ArrayList[] listas = this.getBothList(list);
			ArrayList[] lst = new ArrayList[2];
			lst[0] = this.getDtoMateria(listas[0],false);
			lst[1] = this.getDtoMateria(listas[1],false);
			return lst;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Guarda un nuevo Requerimiento de Materia
	 * @param matform Formulario de Materia
	 * @return  True | False
	 */
	public boolean save(MateriaForm matform){
		try{
			Object flag = this.existeMateria(matform.getCodigo());
			if(flag != null)
				return false;
			String hql = "from Materia";
			List list = dao.find(hql);
			int id = list.size() + 1;
			Materia materia = new Materia();
			materia.setIdMateria(id);
			materia.setCodMateria(matform.getCodigo());
			materia.setNomMateria(matform.getNombre());
			if (matform.getDesc()!= null && ! matform.getDesc().equals("")){
				if (matform.getDesc().length() > 200)
					matform.setDesc(matform.getDesc().substring(0,199));
			}	
			materia.setDescMateria(matform.getDesc());
			materia.setEstMateria("A");
			materia.setUniValorativas(new Integer(matform.getUv()));
			dao.save(materia);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene una Materia
	 * @param codigo Codigo  de Materia
	 * @return  Objeto Materia
	 */
	public Materia getMateriaFromCodigo(String codigo){
		try{
			String hql = "from Materia where lower(codMateria) = lower(?)";
			String[] params = {codigo.toLowerCase()};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0)
				return (Materia) list.get(0);
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Actualiza Materias
	 * @param listActivo lista de MAterias Activas
	 * @param listInactivo lista de MAterias Inactivas
	*/
	public void updateAll(ArrayList listActivo, ArrayList listInactivo){
		MateriaDto dto = null;
		Materia materia = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (MateriaDto) listActivo.get(i);
			materia = new Materia();			
			materia = this.getMateriaFromCodigo(dto.getCodigo());
			if(dto.getEstado().equals("Activo"))
				materia.setEstMateria("A");
			else
				materia.setEstMateria("I");
			dao.update(materia);
		}
		for(int i=0; i<listInactivo.size(); i++){
			dto = (MateriaDto) listInactivo.get(i);
			materia = new Materia();
			materia = this.getMateriaFromCodigo(dto.getCodigo());
			if(dto.getEstado().equals("Activo"))
				materia.setEstMateria("A");
			else
				materia.setEstMateria("I");
			dao.update(materia);
		}
	}
	
	/**
	 * Busqueda de Materias
	 * @param matform Formulario de Materia
	 * @param flag Bandera que determina si la busqueda es de activacion o modificacion
	 * @return  Lista
	 */
	public ArrayList[] find(MateriaForm matform,boolean flag){
		String hql ="from Materia where estMateria is not null ";		
		if(!matform.getCodigo().equals("")){
			hql += "and codMateria like '" + matform.getCodigo() + "%' ";			
		}			
		if(!matform.getNombre().equals("")){
			hql += "and lower(nomMateria) like '" + matform.getNombre().toLowerCase() + "%' ";			
		}			
		if(!matform.getDesc().equals("")){
			hql += "and lower(descMateria) like '" + matform.getDesc().toLowerCase() + "%'  ";			
		}
		if(!matform.getUv().equals(""))
			hql += "and uniValorativas = " + matform.getUv();
		hql += "  order by codMateria";
    	List list = dao.find(hql);
		ArrayList[] listas = this.getBothList(list);
		ArrayList[] lst = new ArrayList[2];
		lst[0] = this.getDtoMateria(listas[0],flag);
		lst[1] = this.getDtoMateria(listas[1],flag);
		return lst;
	}
	
	/**
	 *Obtiene ambas listas (activas Inactivas)
	 * @param list Lista  de Materias
	 * @return  Lista
	 */
	private ArrayList[] getBothList(List list){
		ArrayList<Materia> listActivas = new ArrayList<Materia>();
		ArrayList<Materia> listInactivas = new ArrayList<Materia>();
		ArrayList[] listas = new ArrayList[2];
		try{
			for(int i=0; i<list.size(); i++){
				Materia materia = (Materia) list.get(i);
				if(materia.getEstMateria().equals("A"))
					listActivas.add(materia);
				else
					listInactivas.add(materia);
			}
			listas[0] = listActivas;
			listas[1] = listInactivas;
			return listas;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 *Obtieneel identificador de Materia
	 * @param codigo codigo de Materia
	 * @return  nuemero 
	 */
	private int getIdMateria(String codigo){
		try{
			String hql = "from Materia where lower(codMateria) = ?";
			String[] params = {codigo.toLowerCase()};
			List list = dao.findByProps(hql, params);
			int id = 0;
			if(list.size() != 0){
				Materia materia = (Materia) list.get(0);
				return materia.getIdMateria();
			}
			return id;
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Actualiza una Materia 
	 * @param codigo codigo de Materia
	 * @return  nuemero 
	 */
	public boolean update(MateriaForm matform){
		try{
			Object flag = this.existeMateria(matform.getCodigoHidden());
			if(flag == null)
				return false;
			if(matform.getCodigo().equals(matform.getCodigoHidden())){
				int id = this.getIdMateria(matform.getCodigo());
				Materia materia = (Materia) flag;
				materia.setIdMateria(id);
				materia.setCodMateria(matform.getCodigo());
				materia.setUniValorativas(new Integer(matform.getUv()));
				if(!matform.getNombre().equals(""))
					materia.setNomMateria(matform.getNombre());
				if (matform.getDesc()!= null && ! matform.getDesc().equals("")){
					if (matform.getDesc().length() > 200)
						matform.setDesc(matform.getDesc().substring(0,199));
					materia.setDescMateria(matform.getDesc());
				}		
				dao.update(materia);
			}else{
				int uv = 0;
				if(!matform.getUv().equals(""))
					uv = new Integer(matform.getUv());
				String hql = "update Materia set codMateria = ?, nomMateria = ?, descMateria = ?, uniValorativas = ? where codMateria = ?";
				Object[] params = {matform.getCodigo(),matform.getNombre(),matform.getDesc(),uv,matform.getCodigoHidden()};
				dao.updateByProps(hql, params);
			}			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifica si existe una materia 
	 * @param id Identificado de Materia
	 * @return   
	 */
	private Object existeMateria(String id){
		try{
			String hql = "from Materia where lower(codMateria) = ?";
			String[] params = {id.toLowerCase()};
			List list = dao.findByProps(hql, params);
			if(list.size() == 0)
				return null;
			return list.get(0);			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtiene el obejto de Datos de Materia 
	 * @param list Lista de Materias
	 * @param flag Determina si es Activacion o Modificacion
	 * @return Litsa 
	 */
	private ArrayList getDtoMateria(List list, boolean flag){
		MateriaDto dto = null;
		ArrayList<MateriaDto> listx = new ArrayList<MateriaDto>();
		String estadox = "";
		for(int i=0; i<list.size(); i++){
			Materia materia = (Materia) list.get(i);
			dto = new MateriaDto();
			dto.setId(String.valueOf(materia.getIdMateria()));
			dto.setCodigo(materia.getCodMateria());
			dto.setNombre(materia.getNomMateria());
			dto.setDescripcion(materia.getDescMateria());
			if(materia.getUniValorativas() != null  || materia.getUniValorativas() == 0)
				dto.setUv(String.valueOf(materia.getUniValorativas()));
			if(!materia.getEstMateria().equals(""))
				if(materia.getEstMateria().equals("A")){
					dto.setEstado("Activo");
					estadox = "Desactivar";
				}else{
					dto.setEstado("Inactivo");
					estadox = "Activar";
				}
			String descripcion = "";
			if(dto.getDescripcion() != null)
				descripcion = dto.getDescripcion();
			if(flag)
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + descripcion + "','" + dto.getUv() + "')\" > Modificar</a> ");
			else{
				dto.setAccion("<a href='" + Constants.contextPath + "mtoMat.do?cmd=hash&codigo=" + dto.getCodigo()+ "&uv=" + dto.getUv() + "&nombre=" + dto.getNombre()+ "&desc=" + descripcion + "&estado=" + materia.getEstMateria() + "'>" + estadox +"</a>");
			}
			listx.add(dto);			
		}
		return listx;
	}
	
	/**
	 * Remueve los Item de una Lista 
	 * @param list Lista de Materias
	 * @param codigo codigo del Item 
	 * @return Lista 
	 */
	public ArrayList removeItemToList(ArrayList list, String codigo){
		MateriaDto dto = null;
		ArrayList<MateriaDto> lst = new ArrayList<MateriaDto>();
		for(int i=0; i<list.size(); i++){
			dto = (MateriaDto) list.get(i);
			if(!dto.getCodigo().toLowerCase().equals(codigo.toLowerCase()))
				lst.add(dto);
		}
		return lst;
	}
}//clase
