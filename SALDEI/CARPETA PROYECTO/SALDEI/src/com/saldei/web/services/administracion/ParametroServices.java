package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.List;

import com.saldei.hibernate.tables.Parametro;
import com.saldei.hibernate.tables.TipoMulticode;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.administracion.ParametroDto;
import com.saldei.web.form.administracion.ParametroForm;

public class ParametroServices {
	
	private HibDAO dao = new HibDAOImpl();
	
	/**
	 * Determina si un Parametro exite o no 
	 * @param nombre  nombre del Parametro
	 * @return  True | False
	 */
	private boolean existeParametro(String nombre){
		try{
			String hql = "from Parametro where lower(nomParametro) = ?";
			String[] params = {nombre.toLowerCase()};
			List list = dao.findByProps(hql, params);
			if(list == null || list.size() == 0)
				return true;
			return false;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene el Correlativo de el Parametro
	* @return  número de Parametro
	 */
	private int getSequenceParam(){
		String hql = "select max(idParametro) from Parametro";
		List list = dao.find(hql);
		Integer max;
		if(list.size() != 0){
			max = (Integer) list.get(0);
		}else
			max = 0;		
		return max + 1;
	}
	
	/**
	* Guarda un nuevo registro de Parametro
	* @param param  Formulario de Parametro
	* @return  True | False
	 */
	public boolean save(ParametroForm param){
		try{
			boolean flag = this.existeParametro(param.getNombre());
			if(!flag)
				return false;
			else{
				int id = this.getSequenceParam();
				Parametro parametro = new Parametro();
				parametro.setIdParametro(id);
				parametro.setNomParametro(param.getNombre());
				if (param.getDescripcion()!= null && !param.getDescripcion().equals("")){
					if (param.getDescripcion().length() > 50)
						param.setDescripcion(param.getDescripcion().substring(0, 49));					
				}
				parametro.setDescripcion(param.getDescripcion());
				parametro.setValor(param.getValor());
				dao.save(parametro);
				return true;
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Actualiza un egistro de Parametro
	 * @param param  Formulario de Parametro
	 * @return  True | False
	 */
	public boolean update(ParametroForm param){
		try{
			Parametro parametro = new Parametro();
			parametro.setIdParametro(new Integer(param.getCodigo()));
			parametro.setNomParametro(param.getNombre());
			if (param.getDescripcion()!= null && !param.getDescripcion().equals("")){
				if (param.getDescripcion().length() > 50)
					param.setDescripcion(param.getDescripcion().substring(0, 49));				
			}
			parametro.setDescripcion(param.getDescripcion());
			parametro.setValor(param.getValor());
			dao.update(parametro);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Realiza una busqueda de  Parametro segun el criterio
	 * @param param  Formulario de Parametro
	 * @return  Lista de Parametros
	 */
	public ArrayList find(ParametroForm param) throws Exception{
		try{
			String hql = "from Parametro where nomParametro is not null ";
			if(!param.getNombre().equals(""))
					hql += "and lower(nomParametro) like '" + param.getNombre().toLowerCase() + "%' ";
			if(!param.getDescripcion().equals(""))
				hql += "and lower(descripcion) like '" + param.getDescripcion().toLowerCase() + "%' ";
			if(!param.getValor().equals(""))
				hql += "and lower(valor) like '" + param.getValor().toLowerCase() + "%' ";
			hql += "order by nomParametro";
			List list = dao.find(hql);
			ArrayList lst = this.prepareListParametro(list);
			return lst;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * Perapa la lista de Paremtros
	 * @param list Lista de Parametro
	 * @return  Lista de Parametros
	 */
	private ArrayList prepareListParametro(List list){
		ArrayList<ParametroDto> lst = new ArrayList<ParametroDto>();
		ParametroDto dto = null;
		for(int i=0; i<list.size(); i++){
			dto = new ParametroDto();
			Parametro param = (Parametro) list.get(i);
			dto.setCodigo(String.valueOf(param.getIdParametro()));
			dto.setNombre(param.getNomParametro());
			dto.setDescripcion(param.getDescripcion());
			dto.setValor(param.getValor());
			dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getNombre() + "','" + dto.getDescripcion() + "','" + dto.getValor() + "','" + dto.getCodigo() + "')\" >Modificar</a>");
			lst.add(dto);
		}
		return lst;
	}
	
	/**
	 * Elimina un registro de Parametro
	 * @param pform Lista de Parametro
	 * @return  True | False
	 */
	public boolean delete(ParametroForm pform){
		try{
			Parametro parametro = new Parametro();
			parametro.setIdParametro(new Integer(pform.getCodigo()));
			parametro.setDescripcion(pform.getDescripcion());
			parametro.setNomParametro(pform.getNombre());
			parametro.setValor(pform.getValor());
			dao.delete(parametro);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}		
	}
	
	@SuppressWarnings("unused")
	public String valorParametro(String nombreParametro){
		String idTipoCodigo="";		
		Parametro parametro=null;
		try{
			String hql = "From Parametro where nomParametro = '"+ nombreParametro + "'";			
			List list= dao.find(hql);
			parametro = (Parametro)list.get(0);
			idTipoCodigo = parametro.getValor();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return idTipoCodigo;
	}

}//class
