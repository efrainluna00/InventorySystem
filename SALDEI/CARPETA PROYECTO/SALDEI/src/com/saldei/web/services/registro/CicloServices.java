package com.saldei.web.services.registro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.saldei.hibernate.querys.QuerysRegistro;
import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.CicloDto;
import com.saldei.web.form.registro.CicloForm;

public class CicloServices {
	private HibDAO 			 dao  = new HibDAOImpl();
	private Util 			util  = new Util();
	private JdbcHelper 		jdbc  = new JdbcHelper();
	private QuerysRegistro query  = new QuerysRegistro();
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public List getAnyos() throws Exception{
		List list = new LinkedList();
		int anyo =  Integer.parseInt(util.getAnyoServidor());
		for (int i=5; i>0; i--){
			int anyoAux = anyo - i;
			ElementDto e = new ElementDto();
			e.setElement1(String.valueOf(anyoAux));
			list.add(e);
		}
		ElementDto e = new ElementDto();
		e.setElement1(String.valueOf(anyo));
		list.add(e);
		for (int i=1; i< 5; i++){
			int anyoAux = anyo + i;
			ElementDto e1 = new ElementDto();
			e1.setElement1(String.valueOf(anyoAux));
			list.add(e1);
		}
		return list;		
	}
	
	public List getCiclosActivos() throws Exception{
		String hql =QuerysRegistro.getCiclosActivos();
		List list  = dao.find(hql);
		return list;		
	}
	
	
	/**
	 * Busca ciclos segun parametros 
	 * @param cicloForm
	 * @param p_Estado
	 * @param isModificar
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public Map find(CicloForm cicloForm, String p_Estado, boolean isModificar ){
		String hql = query.findCiclo(cicloForm, p_Estado);
		List list  = dao.find(hql);	
		Map mapx = new HashMap();
		for(int i=0; i<list.size(); i++){
			Ciclo  ciclo = (Ciclo) list.get(i);
			CicloDto dto = new CicloDto();
			dto.setIdCiclo( ciclo.getIdCiclo());
			dto.setAnyoCiclo(ciclo.getAnyoCiclo());
			dto.setCicloActivo(ciclo.getCicloActivo());
			dto.setNumCiclo(ciclo.getNumCiclo());
			dto.setFechaFin(util.dateToStringDDMMYYYY(ciclo.getFechaFinCiclo()));
			dto.setFechaIni(util.dateToStringDDMMYYYY(ciclo.getFechaIniCiclo()));
			if (ciclo.getEstCiclo() != null){
				if(ciclo.getEstCiclo().trim().equals("A"))
					dto.setEstCiclo("Activo");
				else
					dto.setEstCiclo("Inactivo");
			}		
			
			if(isModificar){ 
				dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar( " +
								  " '"+ dto.getIdCiclo()  +"', '"+ dto.getNumCiclo() +"', '"+ dto.getAnyoCiclo()   +"', " +
								  "	'"+ dto.getFechaIni() +"', '"+ dto.getFechaFin() +"', '"+ dto.getCicloActivo() +"' " +
								  ")\" > Modificar</a> ");
			}else{
				String estadox = "";
				String estado  = "";
				if(dto.getEstCiclo().equals("Activo")){
					estadox = "Desactivar";
					estado   = "A";
				}
				else{
					estadox = "Activar";
					estado   = "I";
				}
				this.getAccionDto(dto, "hash", estadox, estado);						
			}
			mapx.put(dto.getIdCiclo(), dto);
		}
		return mapx;
	}
	
	public void getAccionDto (CicloDto p_dto, String p_cmd, String p_Accion, String p_estado){
		p_dto.setAccion("<a href='" + Constants.contextPath + "ciclo.do?cmd="+ p_cmd +"&idCiclo=" + p_dto.getIdCiclo() +"&numCiclo=" + p_dto.getNumCiclo() + 
				"&anyoCiclo=" + p_dto.getAnyoCiclo() + "&fechaIni="+ p_dto.getFechaIni()+ "&fechaFin=" + p_dto.getFechaFin()+ "&cicloActivo=" + p_dto.getCicloActivo()+
				"&estCiclo="+p_estado+" '>" + p_Accion + "</a>");
	}

	/**
	 * Construye el Identificador de Ciclo y lo setea al objeto
	 * @param p_dto Objeto de Contenedor de Ciclo    
	 * @throws Exception
	 */
	public String getIdCiclo(CicloDto p_dto)throws Exception{
		String id = "0" + String.valueOf(p_dto.getNumCiclo());
		id += p_dto.getAnyoCiclo();
		return id; 
	}
	
	/**
	 * Valida que un formulario sea nulo 
	 * @param form Formulario de la pantalla de Mantenimiento de Ciclo
	 * @return True | False
	 */
	public boolean isNullForm(CicloForm p_form)throws Exception{
		if( p_form.getNumCiclo() < 0 || p_form.getAnyoCiclo()==null || p_form.getAnyoCiclo().equals("Seleccione") || p_form.getFechaIni().equals("") ||
		   p_form.getFechaFin().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Verifica si un Ciclo esta en la tabla Ciclo
	 * @param p_idCiclo Identificador de Ciclo
	 * @return True | False
	 */
	public boolean isCiclo(String p_idCiclo){
		String hql = QuerysRegistro.getCiclo(p_idCiclo);
		List list = dao.find(hql);
		if(list.size() != 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica si hay un ciclo Activo
	 * @return True | False
	 */
	public boolean existeCicloActivo(CicloDto dto) throws Exception{
		if (dto.getCicloActivo() != null && dto.getCicloActivo().equals("S")){
			String hql = QuerysRegistro.existeCicloActivo("S");
			List list = dao.find(hql);
			if(list.size() != 0)
				return true;
			else
				return false;
		}
		else
			return false;	
		
	}
	
	/**
	 * Ingresa un ciclo nuevo a la BD
	 * @param dto Contenedor de Ciclo
	 * @return True | False
	 * @throws Exception
	 */
	public boolean saveCiclo(CicloDto dto) throws Exception{
		dto.setEstCiclo("A");
		dao.save(this.cicloDtoToCiclo(dto, null));
		return true;	
	}

	/**
	 * Actualiza un Ciclo en la Base de Datos
	 * @param dto Contenedor de Ciclo
	 * @return True | False
	 * @throws Exception
	 */
	public boolean updateCiclo(CicloDto dto) throws Exception{
		String hql = QuerysRegistro.getCiclo(dto.getIdCiclo());
		List list = dao.find(hql);
		if (list != null) {
			Ciclo ciclo = (Ciclo) list.get(0); 
			dto.setEstCiclo("");
			dao.update(this.cicloDtoToCiclo(dto, ciclo));
			return true;
		}	
		return false;
	}
	
	/**
	 * Convierte un Dto a Ciclo
	 * @param dto Contendor de Ciclo
	 * @return Objeto Hibernate de Ciclo
	 * @throws Exception
	 */	
	public Ciclo cicloDtoToCiclo(CicloDto dto, Ciclo ciclo) throws Exception {
		if (ciclo == null ) ciclo = new Ciclo();
		ciclo.setIdCiclo(dto.getIdCiclo());
		if (dto.getNumCiclo() > 0)
			ciclo.setNumCiclo(dto.getNumCiclo());
		if (!dto.getAnyoCiclo().trim().equals("Seleccione"))
			ciclo.setAnyoCiclo(dto.getAnyoCiclo());
		if(dto.getFechaIni() !=null && !dto.getFechaIni().equals(""))
			ciclo.setFechaIniCiclo(util.fechaFormatoPostgres(dto.getFechaIni()));
		if(dto.getFechaFin() !=null && !dto.getFechaFin().equals(""))
			ciclo.setFechaFinCiclo(util.fechaFormatoPostgres(dto.getFechaFin()));
		if(dto.getCicloActivo() !=null && !dto.getCicloActivo().equals("Seleccione"))
			ciclo.setCicloActivo(dto.getCicloActivo());
		else
			ciclo.setCicloActivo("N");
		if (dto.getEstCiclo()!=null && !dto.getEstCiclo().equals(""))
			ciclo.setEstCiclo(dto.getEstCiclo());
		return ciclo;
	}
	
	public boolean  guardarMap (Map p_mapa, String p_estado){
		try {
			boolean flag = true;
			for (Iterator i = p_mapa.keySet().iterator(); i.hasNext();){
				String strKey  = (String) i.next();				
				String sql     = QuerysRegistro.updateCiclo(strKey, p_estado);
				flag = jdbc.saveOrUpdate(sql);
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return false;
	}
	
	public String getCicloActivo(){
		String hql = "from Ciclo where cicloActivo = 'S'";
		List list = dao.find(hql);
		if(list.size() != 0){
			Ciclo ciclo = (Ciclo) list.get(0);
			return "<font color='#4682b4'><b>El Ciclo de Trabajo es: " + ciclo.getIdCiclo() + "</b></font>"; 
		}
		else
			return "<font color='#4682b4'><b>Actualmente no existe registrado ningun Ciclo de Trabajo" + "</b></font>";
	}

	
}
