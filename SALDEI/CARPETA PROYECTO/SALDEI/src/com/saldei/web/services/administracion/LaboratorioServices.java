/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.saldei.hibernate.tables.Laboratorio;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.administracion.LaboratorioDto;
import com.saldei.web.form.administracion.LaboratorioForm;

public class LaboratorioServices {
	
	private HibDAO dao = new HibDAOImpl();
		
	public void changeButtons(HttpServletRequest request){
		if(request.getSession().getAttribute("buttonsExtLab") == null || request.getSession().getAttribute("buttonsExtLab").toString().equals("firstButtonLab")){
			request.getSession().setAttribute("primeroLab", "<input type='button' value='Guardar'  name='btnSave' id='btnSave'onclick='saveOutFunction();' class='ButtonText'>"); //
			request.getSession().setAttribute("segundoLab", "<input type='button' value='Actualizar'  name='btnUpdate' id='btnUpdate'  onclick='updateOutFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroLab", "Mostrar");//
			request.getSession().setAttribute("cuartoLab", "Limpiar");//
			request.getSession().setAttribute("quintoLab", "Activar");//
			request.getSession().setAttribute("modulesLaboratorio", Constants.legend_first_module);//
		}else{
			request.getSession().setAttribute("primeroLab", "<input type='button' value='Guardar Cambios' name='btnSave' onclick='saveFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("segundoLab", "<input type='button' value='Cancelar Cambios' name='btnUpdate' onclick='updateFunction();' class='ButtonText'>");//
			request.getSession().setAttribute("terceroLab", "Mostrar");//
			request.getSession().setAttribute("cuartoLab", "Limpiar");//
			request.getSession().setAttribute("quintoLab", "Modificar");//
			request.getSession().setAttribute("modulesLaboratorio", Constants.legend_second_module);//
		}
	}
	
	public boolean knowAction(HttpServletRequest request){
		String accion = (String) request.getSession().getAttribute("buttonsExtLab");
		if(accion == null)
			return false; //hacer los primeros botones
		else
			if(accion.equals("firstButtonLab"))
				return false;
			else
				return true;  //hacer los segundos botones
	}
	
	public boolean isNullLabForm(LaboratorioForm labo){
		if(labo.getNombreLaboratorio().equals("") || labo.getNumColumnas().equals("") || labo.getNumFilas().equals(""))
			return true;
		return false;
	}
	
	
	
	public void updateAll(ArrayList listActivo, ArrayList listInactivo){
		LaboratorioDto dto = null;
		Laboratorio labo = null;
		for(int i=0; i<listActivo.size(); i++){
			dto = (LaboratorioDto) listActivo.get(i);
			labo = this.getLaboratorioByNombre(dto.getIdLaboratorio());			
			labo.setEstLaboratorio("A");			
			dao.update(labo);
		}
		for(int i=0; i<listInactivo.size(); i++){
			dto = (LaboratorioDto) listInactivo.get(i);
			labo = this.getLaboratorioByNombre(dto.getIdLaboratorio());			
			labo.setEstLaboratorio("I");
			dao.update(labo);
		}
	}
	
	public boolean save(LaboratorioForm labo){
		boolean flag = false;
		try{
			boolean bandera = this.existeLabo(labo.getNombreLaboratorio());
			if(bandera){
				Laboratorio laboratorio  = new Laboratorio();
				int id = this.getIdLaboratorio();
				laboratorio.setIdLaboratorio(id);
				laboratorio.setAbrevLaboratorio(labo.getAbrevLaboratorio());
				laboratorio.setEstLaboratorio("A");
				laboratorio.setNombreLaboratorio(labo.getNombreLaboratorio());
				laboratorio.setNumColumnas(new Integer(labo.getNumColumnas()));
				laboratorio.setNumFilas(new Integer(labo.getNumFilas()));		
				dao.save(laboratorio);
				return true;	
			}else
				return false;			
		}catch(Exception ex){
			ex.printStackTrace();
			return flag;	
		}		
	}
	
	public ArrayList[] find(LaboratorioForm labo,boolean flag){
		String hql ="from Laboratorio where estLaboratorio is not null ";		
		if(!labo.getAbrevLaboratorio().equals("")){
			hql += "and lower(abrevLaboratorio) like '" + labo.getAbrevLaboratorio().toLowerCase() + "%' ";			
		}			
		if(!labo.getNombreLaboratorio().equals("")){
			hql += "and lower(nombreLaboratorio) like '" + labo.getNombreLaboratorio().toLowerCase() + "%' ";			
		}			
		if(!labo.getNumColumnas().equals("")){
			hql += "and numColumnas = " + labo.getNumColumnas() + " ";			
		}
		if(!labo.getNumFilas().equals("")){
			hql += "and numFilas = " + labo.getNumFilas() + " ";			
		}
		hql += " order by nombreLaboratorio";
    	List list = dao.find(hql);
		ArrayList[] listas = this.getBothList(list);
		ArrayList[] lst = new ArrayList[2];
		lst[0] = this.getDtoLaboratorio(listas[0],flag);
		lst[1] = this.getDtoLaboratorio(listas[1],flag);
		return lst;
	}
	
	private ArrayList getDtoLaboratorio(List list, boolean flag){
		LaboratorioDto dto = null;
		ArrayList<LaboratorioDto> listx = new ArrayList<LaboratorioDto>();
		String estadox = "";
		for(int i=0; i<list.size(); i++){
			Laboratorio labo = (Laboratorio) list.get(i);
			dto = new LaboratorioDto();
			dto.setAbrevLaboratorio(labo.getAbrevLaboratorio());
			dto.setIdLaboratorio(String.valueOf(labo.getIdLaboratorio()));
			dto.setNombreLaboratorio(labo.getNombreLaboratorio());
			dto.setNumColumnas(String.valueOf(labo.getNumColumnas()));
			dto.setNumFilas(String.valueOf(labo.getNumFilas()));
			if(!labo.getEstLaboratorio().equals(""))
				if(labo.getEstLaboratorio().equals("A")){
					dto.setEstLaboratorio("Activo");
					estadox = "Desactivar";
				}else{
					dto.setEstLaboratorio("Activo");
					estadox = "Activar";
				}
			if(flag)
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getIdLaboratorio() + "','" + dto.getNombreLaboratorio()	 + "','" + dto.getAbrevLaboratorio() + "','" + dto.getNumColumnas() + "','" + dto.getNumFilas() + "')\" > Modificar</a> ");
			else{
				dto.setAccion("<a href='" + Constants.contextPath + "mtoLab.do?cmd=hash&idLaboratorio=" + dto.getIdLaboratorio() + "&nombreLaboratorio=" + dto.getNombreLaboratorio() + "&abrevLaboratorio=" + dto.getAbrevLaboratorio() + "&numFilas=" + dto.getNumFilas() + "&numColumnas=" + dto.getNumColumnas() + "&estLaboratorio=" + labo.getEstLaboratorio() + "'>" + estadox +"</a>");
			}
			listx.add(dto);			
		}
		return listx;
	}
	
	public ArrayList[] findAll(){
		try{			
			String hql = "from Laboratorio";			
			List list = dao.find(hql);
			ArrayList[] listas = this.getBothList(list);
			ArrayList[] lst = new ArrayList[2];
			lst[0] = this.getDtoLaboratorio(listas[0],false);
			lst[1] = this.getDtoLaboratorio(listas[1],false);
			return lst;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	private ArrayList[] getBothList(List list){
		ArrayList<Laboratorio> listActivas = new ArrayList<Laboratorio>();
		ArrayList<Laboratorio> listInactivas = new ArrayList<Laboratorio>();
		ArrayList[] listas = new ArrayList[2];
		try{
			for(int i=0; i<list.size(); i++){
				Laboratorio labo = (Laboratorio) list.get(i);
				if(labo.getEstLaboratorio().equals("A"))
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
	
	public boolean update(LaboratorioForm labo){
		try{
			if(!Util.esCadenaNumero(labo.getIdLaboratorio()))
				return false;
			Laboratorio labox = this.getLaboratorioByNombre(labo.getIdLaboratorio());
			if(labox == null)
				return false;
			labox.setAbrevLaboratorio(labo.getAbrevLaboratorio());
			labox.setNombreLaboratorio(labo.getNombreLaboratorio());
			labox.setNumColumnas(new Integer(labo.getNumColumnas()));
			labox.setNumFilas(new Integer(labo.getNumFilas()));
			dao.update(labox);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	private boolean existeLabo(String nombre){
		String hql = "from Laboratorio where lower(nombreLaboratorio) = ?";
		String[] params = {nombre.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return true;
		return false;
	}
	
	private int getIdLaboratorio(){
		String hql = "from Laboratorio";
		List list = dao.find(hql);
		return list.size() + 1;		
	}
	
	private Laboratorio getLaboratorioByNombre(String id){
		String hql = "from Laboratorio where idLaboratorio = ?";
		Integer[] params = {new Integer(id)};
		List list = dao.findByProps(hql, params);
		Laboratorio labo = null;
		if(list.size() != 0){
			labo = (Laboratorio) list.get(0);
			return labo;
		}
		return null;		
	}
	
	public ArrayList removeItemToList(ArrayList list, String id){
		LaboratorioDto dto = null;
		ArrayList<LaboratorioDto> lst = new ArrayList<LaboratorioDto>();
		for(int i=0; i<list.size(); i++){
			dto = (LaboratorioDto) list.get(i);
			if(!dto.getIdLaboratorio().equals(id))
				lst.add(dto);
		}
		return lst;
	}

}//class
