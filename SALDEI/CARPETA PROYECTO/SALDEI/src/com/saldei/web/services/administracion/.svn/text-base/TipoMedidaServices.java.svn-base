package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.saldei.hibernate.tables.TipoMedida;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.administracion.TipoMedidaDto;
import com.saldei.web.form.administracion.TipoMedidaForm;

public class TipoMedidaServices {
	
	private HibDAO dao = new HibDAOImpl();
	
	/**
	 * Cambia los Botones Registro de  Tipo Medida
	 * @param request Objeto HttpServletRequest
	 */
	public void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExtTipoMed") == null || request.getSession().getAttribute("buttonsExtTipoMed").toString().equals("firstButtonTipoMed")){
			request.getSession().setAttribute("primeroTipoMed", "<input type='button' name='btnSave'  id='btnSave' value='Guardar' onclick='saveOutFunction();' class='ButtonText'>"); //
			request.getSession().setAttribute("segundoTipoMed", "<input type='button' value='Actualizar' onclick='updateOutFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroTipoMed", "Mostrar");//
			request.getSession().setAttribute("cuartoTipoMed", "Limpiar");//
			request.getSession().setAttribute("quintoTipoMed", "Activar");//
			request.getSession().setAttribute("modulesTipoMedida", Constants.legend_first_module);//
		}else{
			if(request.getSession().getAttribute("buttonsExtTipoMed").toString().equals("secondButtonTipoMed")){
				request.getSession().setAttribute("primeroTipoMed", "<input type='button' name='btnSave' value='Guardar Cambios' onclick='saveFunction();' class='ButtonText'>");//
				request.getSession().setAttribute("segundoTipoMed", "<input type='button' value='Cancelar Cambios' onclick='updateFunction();' class='ButtonText'>");//
				request.getSession().setAttribute("terceroTipoMed", "Mostrar");//
				request.getSession().setAttribute("cuartoTipoMed", "Limpiar");//
				request.getSession().setAttribute("quintoTipoMed", "Modificar");//	
				request.getSession().setAttribute("modulesTipoMedida", Constants.legend_second_module);//
			}			
		}
	}
	
	/**
	 * Actualiza Todos losRegistro de  Tipo Medida
	 * @param listActivo lista de Tipo Medida Activas
	 * @param listInactivo lista de Tipo Medida Inactivas
	 */
	public void updateAll(ArrayList listActivo, ArrayList listInactivo){
		TipoMedidaDto dto = null;
		TipoMedida tipo = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (TipoMedidaDto) listActivo.get(i);
			tipo = new TipoMedida();
			tipo.setIdTipoMedida(this.getIdTipoMedida(dto.getNombre()));
			tipo.setNomTipoMedida(dto.getNombre());
			if(dto.getEstado().equals("Activo"))
				tipo.setEstTipoMedida("A");
			else
				tipo.setEstTipoMedida("I");
			dao.update(tipo);
		}
		for(int i=0; i<listInactivo.size(); i++){
			dto = (TipoMedidaDto) listInactivo.get(i);
			tipo = new TipoMedida();
			tipo.setIdTipoMedida(this.getIdTipoMedida(dto.getNombre()));
			tipo.setNomTipoMedida(dto.getNombre());
			if(dto.getEstado().equals("Activo"))
				tipo.setEstTipoMedida("A");
			else
				tipo.setEstTipoMedida("I");
			dao.update(tipo);
		}
	}
	
	/**
	 * Determina los botones a mostar
	 * @param request   Objeto HttpServletRequest
	 * @return  True | False
	 */
	public boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExtTipoMed");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButtonTipoMed"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	/**
	 * Guarda  un nuevo  Registro de  Tipo Medida
	 * @param tipoform   Formulario de Tipo Medida
	 * @return  True | False
	 */
	public boolean save(TipoMedidaForm tipoform){
		try{
			boolean flag = this.existTipoMedida(tipoform.getNombre());
			if(!flag){
				TipoMedida tipo = new TipoMedida();
				int id = this.getSequenceTipoMedida();
				tipo.setIdTipoMedida(id);
				tipo.setEstTipoMedida("A");
				tipo.setNomTipoMedida(tipoform.getNombre());
				dao.save(tipo);
				return true;	
			}else
				return false;			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene el correlativo de  Registro de  Tipo Medida
	 * @return  numero de Tipo MEdida
	 */
	private int getSequenceTipoMedida(){
		/*String hql = "from TipoMedida";
		List list = dao.find(hql);
		return list.size() + 1;*/
		String hql = "select max(idTipoMedida) from TipoMedida";
		List list = dao.find(hql);
		Integer max;
		if(list.size() != 0){
			max = (Integer) list.get(0);
		}else
			max = 0;		
		return max + 1;
	}
	
	/**
	 * Verifica si un  Registro de  Tipo Medida existe en la BD
	 * @param nombre   Nombre  de Tipo Medida
	 * @return  True | False
	 */
	private boolean existTipoMedida(String nombre){
		String hql = "from TipoMedida where lower(nomTipoMedida) = ?";
		String[] params = {nombre.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Actualiza  un  Registro de  Tipo Medida
	 * @param tipoform   Formulario de Tipo Medida
	 * @return  True | False
	 */
	public boolean update(TipoMedidaForm tipoform){
		try{
			String hql = "from TipoMedida where lower(nomTipoMedida) = lower(?)";
			String[] params = {tipoform.getCodigo()};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				TipoMedida tipo = (TipoMedida) list.get(0);
				tipo.setNomTipoMedida(tipoform.getNombre());
				dao.update(tipo);
				return true;	
			}else
				return false;			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Elimina  un  Registro de  Tipo Medida
	 * @param tipo   Formulario de Tipo Medida
	 * @return  True | False
	 */
	public boolean delete(TipoMedidaForm tipo){
		try{
			String hql = "from TipoMedida where lower(nomTipoMedida) = '" + tipo.getCodigo().toLowerCase() + "'";
			List list = dao.find(hql);
			if(list.size() != 0){
				TipoMedida tipoMedida = (TipoMedida) list.get(0);
				dao.delete(tipoMedida);
				return true;
			}
			return false;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Busqueda de  Registro de  Tipo Medida segun criterio
	 * @param mtip   Formulario de Tipo Medida
	 * @param flag   Determina si es busqueda para modificar o para activación
	 * @return  Lista
	 */
	public ArrayList[] find(TipoMedidaForm mtip, boolean flag){
		try{
			if(mtip.getNombre().equals("")){
				List list = dao.getAll(TipoMedida.class);
				ArrayList[] listas = this.prepareListTipoMedida(list, flag);
				return listas;
			}else{				
				String hqlx = "from TipoMedida where lower(nomTipoMedida) like '" +mtip.getNombre().toLowerCase() + "%' order by nomTipoMedida";								
				List list = dao.find(hqlx);
				ArrayList[] listas = this.prepareListTipoMedida(list, flag);
				return listas;	
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Busqueda de  Registro de  Tipo Medida
	 * @param mtip   Formulario de Tipo Medida
	 * @return  Lista
	 */
	public ArrayList[] findAll(){
		List list = dao.getAll(TipoMedida.class);
		ArrayList[] listas = this.prepareListTipoMedida(list, false);
		return listas;
	}
	
	/**
	 * Obtiene el Id Tipo Medida
	 * @param nombre Nombre de Tipo Medida
	 * @return  Id
	 */
	private int getIdTipoMedida(String nombre){
		try{
			int id = 0;
			String hql = "from TipoMedida where lower(nomTipoMedida) = lower(?)";
			String[] params = {nombre.toLowerCase()};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				TipoMedida tipo = (TipoMedida) list.get(0);
				id = tipo.getIdTipoMedida();
				return id;
			}else
				return 0;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Prepara una Lista  de  Registro de  Tipo Medida para Activacion o Modificar
	 * @param list   Lista de Tipo Medida
	 * @param flag   Determina si es busqueda para modificar o para activación
	 * @return  lista
	 */	
	private ArrayList[] prepareListTipoMedida(List list, boolean flag){
		ArrayList[] listas = new ArrayList[2];
		ArrayList<TipoMedidaDto> listA = new ArrayList<TipoMedidaDto>();
		ArrayList<TipoMedidaDto> listI = new ArrayList<TipoMedidaDto>();
		TipoMedidaDto dto = null;
		TipoMedida tipo = null;
		String estadox = "";
		for(int i=0; i<list.size(); i++){
			tipo = (TipoMedida) list.get(i);
			dto = new TipoMedidaDto();
			dto.setNombre(tipo.getNomTipoMedida());
			if(tipo.getEstTipoMedida().equals("A"))
				estadox = "Desactivar";
			else
				estadox = "Activar";
			if(flag)
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getNombre() + "')\" >Modificar</a>");
			else
				dto.setAccion("<a href='" + Constants.contextPath + "mtoTipMed.do?cmd=hash&nombre=" + dto.getNombre()+ "&codigo=" + tipo.getIdTipoMedida() + "&est=" + tipo.getEstTipoMedida() + "'>" + estadox+ "</a>");
			if(tipo.getEstTipoMedida().equals("A")){
				dto.setEstado("Activo");
				listA.add(dto);
			}				
			else{
				dto.setEstado("Inactivo");
				listI.add(dto);
			}				
		}
		listas[0] = listA;
		listas[1] = listI;
		return listas;
	}
	
	/**
	 * Elimina un Item de una LIsta
	 * @param list   Lista de Tipo Medida
	 * @param codigo Codigo del Item a Eliminar
	 * @return  lista
	 */	
	public ArrayList removeItemToList(ArrayList list, String codigo){
		TipoMedidaDto dto = null;
		ArrayList<TipoMedidaDto> lst = new ArrayList<TipoMedidaDto>();
		for(int i=0; i<list.size(); i++){
			dto = (TipoMedidaDto) list.get(i);
			if(!dto.getNombre().toLowerCase().equals(codigo.toLowerCase()))
				lst.add(dto);
		}
		return lst;
	}

}//clase
