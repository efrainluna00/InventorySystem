package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.saldei.hibernate.tables.Medida;
import com.saldei.hibernate.tables.TipoMedida;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.administracion.MedidaDto;
import com.saldei.web.form.administracion.MedidaForm;

public class MedidaServices {
	
	private HibDAO dao = new HibDAOImpl();
	
	/**
	 * Hace un cambio de Botones
	 * @param request   Objeto del tipo HttpServletRequest
	 */		
	public void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExtMed") == null || request.getSession().getAttribute("buttonsExtMed").toString().equals("firstButtonMed")){
			request.getSession().setAttribute("primeroMed", "<input type='button' name='btnSave'  id='btnSave' value='Guardar' onclick='saveOutFunction();' class='ButtonText'>"); //
			request.getSession().setAttribute("segundoMed", "<input type='button' name='btnUpdate' value='Actualizar' onclick='updateOutFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroMed", "Mostrar");//
			request.getSession().setAttribute("cuartoMed", "Limpiar");//
			request.getSession().setAttribute("quintoMed", "Activar");//
			request.getSession().setAttribute("modulesMedida", Constants.legend_first_module);//
		}else{
			if(request.getSession().getAttribute("buttonsExtMed").toString().equals("secondButtonMed")){
				request.getSession().setAttribute("primeroMed", "<input type='button' name='btnSave' value='Guardar Cambios' onclick='saveFunction();' class='ButtonText'>");//
				request.getSession().setAttribute("segundoMed", "<input type='button' name='btnUpdate' value='Cancelar Cambios' onclick='updateFunction();' class='ButtonText'>");//
				request.getSession().setAttribute("terceroMed", "Mostrar");//
				request.getSession().setAttribute("cuartoMed", "Limpiar");//
				request.getSession().setAttribute("quintoMed", "Modificar");//
				request.getSession().setAttribute("modulesMedida", Constants.legend_second_module);//
			}			
		}
	}
	
	/**
	 * Elimina un Registro de  Medida
	 * @param request   Objeto del tipo HttpServletRequest
	 * @return  True | False
	 */
	public boolean delete(MedidaForm mtip){
		try{
			Medida medida = new Medida();
			String hql = "from Medida where idMedida = "  + mtip.getCodigoHidden();
			List list = dao.find(hql);
			if(list.size() != 0){
				medida = (Medida) list.get(0);
				dao.delete(medida);
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Busqueda de Medidas
	 * @return  Lista de Medidas 
	 */
	public ArrayList[] findAll(){
		try{
			List list = dao.getAllSession(Medida.class);
			ArrayList[] listas = this.prepareListMedida(list, false);
			return listas;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Busqueda avanzada de los rtegistros de medida
	 * @param mtip   Formulario de Medida 
	 * @param flag    Determina si la busqueda es para Modificar o para Activar\Desactivar
	 * @return  Lista con los registros de medida
	 */
	public ArrayList[] find(MedidaForm mtip, boolean flag){
		try{
			String hql = "from Medida where estMedida is not null and tipoMedida.estTipoMedida = 'A' ";
				if(!mtip.getNombre().equals(""))
					hql += "and lower(nomMedida) like '" + mtip.getNombre().toLowerCase() + "%' ";
				if(!mtip.getAbrev().equals(""))
					hql += "and lower(abvMedida) like '" + mtip.getAbrev().toLowerCase() + "%' ";
				if(!mtip.getFactor().equals(""))
					hql += "and lower(factorConv) like '" + mtip.getFactor().toLowerCase() + "%' ";
				if(!mtip.getTipo().equals("Seleccione")){
					TipoMedida tipo = this.getTipoMedidaToID(new Integer(mtip.getTipo()));
					hql += "and lower(tipoMedida.nomTipoMedida) like '" + tipo.getNomTipoMedida().toLowerCase() + "%' ";					
				}
				hql += "order by nomMedida";
				List list = dao.findSession(hql);
				ArrayList[] listas = this.prepareListMedida(list, flag);
				dao.closeSession();
				return listas;						
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Actualiza un Registro de Medida
	 * @param medidaform   Formulario de Medida 
	 * @return True | False
	 */
	public boolean update(MedidaForm medidaform){
		try{
			String hql = "from Medida where idMedida = ?";
			Integer[] params = {new Integer(medidaform.getCodigoHidden())};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				Medida medida = (Medida) list.get(0);
				medida.setNomMedida(medidaform.getNombre());
				medida.setFactorConv(medidaform.getFactor());
				medida.setNomMedida(medidaform.getNombre());
				medida.setAbvMedida(medidaform.getAbrev());
				if(!medidaform.getTipo().equals("Seleccione")){
					String hqlTipo = "from TipoMedida where idTipoMedida = ?";
					Integer[] parmasTipo = {new Integer(medidaform.getTipo())};
					List lst = dao.findByProps(hqlTipo, parmasTipo);
					TipoMedida tipo = (TipoMedida) lst.get(0);
					medida.setTipoMedida(tipo);
				}
				dao.update(medida);
				return true;	
			}else
				return false;			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Prepara una Lista para determinar que tipo de busqueda va a realizar
	 * @param  list   Lista con los registros de Medida 
	 * @param flag    Determina si la busqueda es para Modificar o para Activar\Desactivar
	 * @return  Lista con los registros de medida
	 */
	private ArrayList[] prepareListMedida(List list, boolean flag){
		ArrayList[] listas = new ArrayList[2];
		ArrayList<MedidaDto> listA = new ArrayList<MedidaDto>();
		ArrayList<MedidaDto> listI = new ArrayList<MedidaDto>();
		MedidaDto dto = null;
		Medida medida = null;
		String estadox = "" ;
		for(int i=0; i<list.size(); i++){
			medida = (Medida) list.get(i);
			dto = new MedidaDto();
			dto.setAbrev(medida.getAbvMedida());
			if(dto.getAbrev() == null)
				dto.setAbrev("");
			dto.setCodigo(String.valueOf(medida.getIdMedida()));			
			dto.setFactor(medida.getFactorConv());
			if(dto.getFactor() == null)
				dto.setFactor("");
			dto.setNombre(medida.getNomMedida());
			if(dto.getNombre() == null)
				dto.setNombre("");
			dto.setTipo(medida.getTipoMedida().getNomTipoMedida());
			if(medida.getEstMedida().equals("A"))
				estadox = "Desactivar";
			else
				estadox = "Activar";
			if(flag)
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getNombre() + "','" + dto.getAbrev() + "','" + dto.getFactor() + "','" + medida.getTipoMedida().getIdTipoMedida() + "','" + dto.getCodigo() + "')\" >Modificar</a>");
			else
				dto.setAccion("<a href='" + Constants.contextPath + "mtoMed.do?cmd=hash&nombre=" + dto.getNombre()+ "&estado=" + medida.getEstMedida() + "&abrev=" + dto.getAbrev() + "&codigoHidden=" + dto.getCodigo()+ "&factor=" + dto.getFactor()+ "&tipo=" + medida.getTipoMedida().getIdTipoMedida() + "'>" + estadox+ "</a>");
			if(medida.getEstMedida().equals("A")){
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
	 * Realiza la Busqueda de los tipos de Medida
	 * @param  id   Lista con los registros de Medida 
	 * @return  Objeto de Tipo Medida
	 */
	public TipoMedida getTipoMedidaToID(int id){
		String hqlTipo = "from TipoMedida where idTipoMedida = ?";
		Integer[] params = {id};
		List list = dao.findByProps(hqlTipo, params);
		TipoMedida tipox = (TipoMedida) list.get(0);
		return tipox;
	}
	
	/**
	 * Determina si un Formulario es valido
	 * @param  medform  Formulario de Medida 
	 * @return  TRue | False
	 */
	public boolean isNullMedidaForm(MedidaForm medform){
		if(medform.getAbrev().equals("") ||  medform.getNombre().equals("") || medform.getTipo().equals("Seleccione"))
			return false;
		return true;
	}
	
	/**
	 * Guarda un registro de Medida
	 * @param  medform  Formulario de Medida 
	 * @return  True | False
	 */
	public boolean save(MedidaForm medform){
		try{
			boolean flag = this.existeMedida(medform.getNombre());
			if(!flag)
				return false;
			else{
				String hqlTipo = "from TipoMedida where idTipoMedida = ?";
				Integer[] params = {new Integer(medform.getTipo())};
				List list = dao.findByProps(hqlTipo, params);
				TipoMedida tipo = (TipoMedida) list.get(0);
				Medida medida = new Medida();
				medida.setAbvMedida(medform.getAbrev());
				medida.setEstMedida("A");
				medida.setFactorConv(medform.getFactor());
				medida.setIdMedida(this.getSequence());
				medida.setNomMedida(medform.getNombre());
				medida.setTipoMedida(tipo);
				dao.save(medida);
				return true;
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 *  Obtiene un Tipo de Medida de acuerdo al nombre
	 * @param  nombre Tipo de Medida
	 * @return  Objeto Tipo Medida
	 */
	public TipoMedida getTipoMedidaToHQL(String nombre){
		String hqlTipo = "from TipoMedida where lower(nomTipoMedida) = (?)";
		String[] params = {nombre};
		List list = dao.findByProps(hqlTipo, params);
		TipoMedida tipox = (TipoMedida) list.get(0);
		return tipox;
	}
	
	/**
	 *  Obtiene un Tipo de Medida con estado Activos
	* @return  Lista de Objeto Tipo Medida
	 */
	public List getTipoMedida(){
		String hql = "from TipoMedida where estTipoMedida = 'A'";
		List list = dao.find(hql);
		return list;
		
	}
	
	/**
	 *  Obtiene un correlativo para medida
	  * @return  numero de medida
	 */
	public int getSequence(){
		/*String hql = "from Medida";
		List list = dao.find(hql);
		return list.size() + 1;*/
		String hql = "select max(idMedida) from Medida";
		List list = dao.find(hql);
		Integer max;
		if(list.size() != 0){
			max = (Integer) list.get(0);
		}else
			max = 0;		
		return max + 1;
	}
	
	/**
	 *  Elimina un Item de una lista
	 * @param  list Lista a la que se le removera el Item
	 * @param  nombre Nombre del Item
	 * @return  
	 */
	public ArrayList removeItemToList(ArrayList list, String nombre){
		MedidaDto dto = null;
		ArrayList<MedidaDto> lst = new ArrayList<MedidaDto>();
		for(int i=0; i<list.size(); i++){
			dto = (MedidaDto) list.get(i);
			if(!dto.getNombre().toLowerCase().equals(nombre.toLowerCase()))
				lst.add(dto);
		}
		return lst;
	}
	
	/**
	 *  Determina si exite una medida registrada
	 * @param  list Lista a la que se le removera el Item
	 * @param  nombre Nombre del Item
	 * @return  
	 */
	public boolean existeMedida(String nombreMedida){
		try{
			String hql = "from Medida where lower(nomMedida) = lower(?)";
			String[] params = {nombreMedida};
			List list = dao.findByProps(hql, params);
			if(list.size() == 0)
				return true;
			else
				return false;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Seleccione uana Acccion
	 * @param  requestobjeto tipo HttpServletRequest 
	 * @return  true| false
	 */
	public boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExtMed");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButtonMed"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	/**
	 * Seleccione uana Acccion
	 * @param  listActivo    Lista de medidas Activos
	 * @param  listInactivo Lista de medidas Inactivos
	 */
	public void updateAll(ArrayList listActivo, ArrayList listInactivo){
		MedidaDto dto = null;
		Medida medida = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (MedidaDto) listActivo.get(i);
			medida = new Medida();
			medida.setIdMedida(new Integer(dto.getCodigo()));
			medida.setNomMedida(dto.getNombre());
			medida.setAbvMedida(dto.getAbrev());
			medida.setFactorConv(dto.getFactor());
			if(dto.getEstado().equals("Activo"))
				medida.setEstMedida("A");
			else
				medida.setEstMedida("I");
			medida.setTipoMedida(this.getTipoMedidaToHQL(dto.getTipo()));
			dao.update(medida);
		}
		for(int i=0; i<listInactivo.size(); i++){
			dto = (MedidaDto) listInactivo.get(i);
			medida = new Medida();
			medida.setIdMedida(new Integer(dto.getCodigo()));
			medida.setNomMedida(dto.getNombre());
			medida.setAbvMedida(dto.getAbrev());
			medida.setFactorConv(dto.getFactor());
			if(dto.getEstado().equals("Activo"))
				medida.setEstMedida("A");
			else
				medida.setEstMedida("I");
			medida.setTipoMedida(this.getTipoMedidaToHQL(dto.getTipo()));
			dao.update(medida);
		}
	}

}//class
