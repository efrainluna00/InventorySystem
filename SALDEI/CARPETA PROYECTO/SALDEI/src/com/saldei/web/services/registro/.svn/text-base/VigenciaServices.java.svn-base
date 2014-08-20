package com.saldei.web.services.registro;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.querys.QuerysRegistro;
import com.saldei.hibernate.tables.Carrera;
import com.saldei.hibernate.tables.CarreraId;
import com.saldei.hibernate.tables.CarreraVigencia;
import com.saldei.hibernate.tables.CarreraVigenciaId;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.UsuarioVigencia;
import com.saldei.hibernate.tables.UsuarioVigenciaId;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.VigenciaDto;

public class VigenciaServices {
	private HibDAO dao  = new HibDAOImpl();
	private Util 			util  = new Util();
	JdbcHelper jdbc = new JdbcHelper();
	
	public List getCarreras(String idCarrera, String PlanEstudio) throws Exception{
		List list   = new LinkedList();
		List<VigenciaDto> lstCar = new LinkedList<VigenciaDto>();
		String hql = QuerysRegistro.getCarrerasVigencia(idCarrera, PlanEstudio);
		list = dao.find(hql);
		if (list != null && list.size()>0){
			for (int i=0; i< list.size(); i++){
				CarreraVigencia car = (CarreraVigencia) list.get(i);
				VigenciaDto dto = new VigenciaDto();
				dto.setIdCarrera(car.getId().getCarrera().getId().getIdCarrera());
				dto.setPlanEstudio(car.getId().getCarrera().getId().getPlanEstudio());
				if(car.getFechaFin()==null || car.getFechaFin().equals(null) || car.getFechaFin().equals(""))
					dto.setFechaFin("");
				else dto.setFechaFin(util.dateToStringDDMMYYYY(car.getFechaFin()));
				dto.setFechaIni(util.dateToStringDDMMYYYY(car.getId().getFechaIni()));
				lstCar.add(dto);
			}
		}
		return lstCar;
	}
	
	public List getUsuario(String idUsuario) throws Exception{
		List list   = new LinkedList();
		List<VigenciaDto> lstUsr = new LinkedList<VigenciaDto>();
		String hql = QuerysRegistro.getUsuarioVigencia(idUsuario);
		list = dao.find(hql);
		if (list != null && list.size()>0){
			for (int i=0; i< list.size(); i++){
				UsuarioVigencia usr = (UsuarioVigencia) list.get(i);
				VigenciaDto dto = new VigenciaDto();
				dto.setIdUsuario(usr.getId().getUsuario().getIdUsuario());
				dto.setFechaFin(util.dateToStringDDMMYYYY(usr.getFechaFin()));
				dto.setFechaIni(util.dateToStringDDMMYYYY(usr.getId().getFechaIni()));
				lstUsr.add(dto);
			}
		}
		return lstUsr;
	}
	
	public boolean saveUsuarioVigencia(VigenciaDto dto) throws Exception {
		if(dto != null){
			UsuarioVigencia usrVig = new UsuarioVigencia();
			UsuarioVigenciaId id   = new UsuarioVigenciaId();
			Usuario         usr    = new Usuario(); 
			usrVig.setFechaFin(util.fechaFormatoPostgres(dto.getFechaFin()));
			id.setFechaIni(util.fechaFormatoPostgres(dto.getFechaIni()));
			usr.setIdUsuario(dto.getIdUsuario());
			id.setUsuario(usr);
			usrVig.setId(id);		
			dao.save(usrVig);
			return true;
		}
		return false;
	}
	
	public boolean isUsuarioVigencia(VigenciaDto dto) throws Exception{
		if (dto != null){
			List list  = new LinkedList();
			String hql = QuerysRegistro.MayorVigencia(dto.getIdUsuario(), util.getFechaFormatoMMDDYYY(dto.getFechaIni())); 
			list = dao.find(hql);
			if(list != null && list.size() >0){
				return true; 
			}
		}
		return false;
	}
	
	public boolean isCarreraVigencia(VigenciaDto dto) throws Exception{
		if (dto != null){
			List list  = new LinkedList();
			String hql = QuerysRegistro.CarMayorVig(dto.getIdCarrera(), dto.getPlanEstudio(), util.getFechaFormatoMMDDYYY(dto.getFechaIni())); 
			list = dao.find(hql);
			if(list != null && list.size() >0){
				return true; 
			}
		}
		return false;
	}
	
	public boolean saveCarreraVigencia(VigenciaDto dto) throws Exception {
		if(dto != null){
			CarreraVigencia carVig = new CarreraVigencia();
			CarreraVigenciaId id   = new CarreraVigenciaId();
			Carrera           car  = new Carrera();
			CarreraId        carId = new CarreraId();
			carId.setIdCarrera(dto.getIdCarrera());
			carId.setPlanEstudio(dto.getPlanEstudio());
			car.setId(carId);
			id.setFechaIni(util.fechaFormatoPostgres(dto.getFechaIni()));
			id.setCarrera(car);
			if(!dto.getFechaFin().equals(""))
				carVig.setFechaFin(util.fechaFormatoPostgres(dto.getFechaFin()));
			carVig.setId(id);
			dao.save(carVig);
			return true;
		}
		return false;
	}
	
	public boolean validarFecha(String p_strFecha) throws Exception {
		String query = "select date(now()) - date('"+p_strFecha +"') as fecha";
		String resultado="";
		boolean flag=false;
		try{									
			List list = jdbc.getQuery(query, null);
			if(list.size()>0){
				DynaBean dyna 	= 	(DynaBean) list.get(0);
				resultado		=	dyna.get("fecha").toString();
				if(Integer.parseInt(resultado)<0)
						flag	=	true;
				else flag		= 	false;
			}			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return flag;
		
	}
}
