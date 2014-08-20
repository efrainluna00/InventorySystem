package com.saldei.web.services.registro;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.querys.QuerysRegistro;
import com.saldei.hibernate.tables.CargoUsuarioDei;
import com.saldei.hibernate.tables.Multicode;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.registro.RequerimientoForm;
import com.saldei.web.services.administracion.MulticodeServices;

public class RequerimientoServices {

	private HibDAO 			 dao  = new HibDAOImpl();
	private JdbcHelper 		jdbc  = new JdbcHelper();
	private MulticodeServices multiServ = new MulticodeServices();
	
	/**
	 * Obtiene una lista con los ciclos de un Usuario
	 * @param usr Usuario que esta en Session
	 * @return  Lista de Ciclos
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List getCicloUsuario(Usuario usr) throws Exception{
		String hql  = getCicloUsuario(usr.getIdUsuario()); 
		List list   = dao.find(hql);
		List lista  = new LinkedList();
		for(int i=0; i<list.size(); i++){
			ElementDto e = new ElementDto();
			e.setElement1(list.get(i).toString());
			lista.add(e);
		}
		return lista;
	}
	
	public List getRequerimientos(){
		try{
			String hql = "from Multicode where lower(tipoMulticode.nomTipoMulticode) like '%requerimiento%' ";
			List list = dao.find(hql);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public  String getCicloUsuario(String Id_Usuario){
		int cargousr = this.getCargoUsr(Id_Usuario); 
		String hql = " Select distinct(id.materiaCiclo.id.ciclo.idCiclo) From CatedraticoMateria where id.idCargoUsr = "+ cargousr +"  " +		
					 " And id.materiaCiclo.id.ciclo.estCiclo= 'A' ";
		return hql;
	}
	
	public int getCargoUsr(String idUsuario){
		String hql = "from CargoUsuarioDei where usuario.idUsuario = ? and idCargo = 12";
		Object[] params = {idUsuario};
		List list = dao.findByProps(hql, params);
		int cargousr = 0;
		if(list.size() != 0){
			CargoUsuarioDei cud = (CargoUsuarioDei) list.get(0);
			cargousr = cud.getIdCargoUsr();
		}
		return cargousr;
	}
	/**
	 * Obtiene una lista de Materias /Seccion de un Ciclo para un Catedratico especifico
	 * @param usr  Objeto Usuario al que se le buscaran las materias
	 * @param form Formulario de Requermiento 
	 * @return Lista
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getMateriaCiclo(Usuario usr, RequerimientoForm form) throws Exception{
		String hql  = getCicloString(usr.getIdUsuario(), form.getIdCiclo()); 
		List list   = dao.find(hql);
		List lista  = new LinkedList();
		for(int i=0; i<list.size(); i++){
			List listAux = (List)list.get(i);
			String seccionId   = listAux.get(0).toString() + "-" + listAux.get(2).toString();
			String seccionName =  listAux.get(1).toString() +  "-" + "0" + listAux.get(2).toString() ;
			ElementDto e = new ElementDto();
			e.setElement1(seccionId);
			e.setElement2(seccionName);
			lista.add(e);
		}
		return lista;
	}
	
	public  String getCicloString(String idUsuario, String ciclo){
		String hql = " Select new List( " +
					 "        id.materiaCiclo.id.materia.idMateria, 		" +
					 "        id.materiaCiclo.id.materia.nomMateria, 	" +
					 "		  id.materiaCiclo.id.idSeccion)				" +
					 " From CatedraticoMateria Where 										" +
				 "      id.idCargoUsr in (select idCargoUsr from CargoUsuarioDei where usuario.idUsuario =  '" + idUsuario + "') And  estCatMat = 'A'  		    " +
					 " And  id.materiaCiclo.id.ciclo.idCiclo = '" + ciclo + "'             		" +
					 " And  id.materiaCiclo.estMatCiclo= 'A'	" +					 			 
					 " Order By 1,3    ";					 
		return hql;
	}
	
	/**
	 * Obtiene un Mapa con los Requerimientos de una Materia Seccion, Ciclo y usuario Escpecifico 
	 * @param IdUsuario Identificador de Usuario
	 * @param IdCiclo   Identificador de Ciclo
	 * @param IdMateria Identificador de Materia
	 * @param seccion   seccion de Materia
	 * @return Mapa de Form de Requerimiento  
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map getRequerimiento(String IdUsuario, String IdCiclo, int IdMateria, int seccion, String primerApe, String primerNom) throws Exception{
		Map mapReq = new HashMap();		
		int id = this.getIdSolicitud(IdUsuario, IdCiclo, IdMateria, seccion);
		if (id > 0 ){
			String sql  = QuerysRegistro.getRequerimiento(id);
			List lstAux =  jdbc.getQuery(sql, null);
			if (lstAux != null && lstAux.size() >0){
				for(int i=0; i<lstAux.size(); i++){
					RequerimientoForm form = new RequerimientoForm();
					DynaBean dyna = (DynaBean) lstAux.get(i);
					if(dyna.get("id_req_mat") != null)
						form.setIdReqMat(Integer.parseInt(dyna.get("id_req_mat").toString()));	
					if(dyna.get("id_solicitud") != null)
						form.setIdSolicitud(Integer.parseInt(dyna.get("id_solicitud").toString()));	
					if(dyna.get("id_tipo") != null)
						form.setIdTipo(dyna.get("id_tipo").toString());	
					if(dyna.get("requerimiento") != null)
						form.setRequerimiento(dyna.get("requerimiento").toString());	
					if(dyna.get("fecha_sol") != null)
						form.setFechaSol(dyna.get("fecha_sol").toString());		
					if(dyna.get("fecha_resol") != null)
						form.setFechaResol(dyna.get("fecha_resol").toString());
					if(dyna.get("est_req_mat") != null)
						form.setEstReqMat(dyna.get("est_req_mat").toString());
					if(form.getEstReqMat()!= null && form.getEstReqMat().equals("S")){
						form.setAccion( "<a href= \"#\" onclick= \"javascript:modificar( " +
						  		 		"'"+form.getIdReqMat() +"',   '"+form.getRequerimiento()+"', '"+form.getIdTipo()+"','" + primerNom + " " + primerApe + "' )\" > Modificar</a> ");
					}
					if(form.getEstReqMat()!= null && !form.getEstReqMat().equals("A")){
						form.setEliminar("<a href='" + Constants.contextPath + "req.do?cmd=delete&idReqMat=" + form.getIdReqMat() +
										 "&idSolicitud=" + form.getIdSolicitud() +" '> Eliminar </a>");
					}
					form.setIdTipo(this.getTipoReq(form.getIdTipo()));
					mapReq.put(form.getIdReqMat(),form);
				}				
			}
		}	
		return mapReq;
	}
	
	private String getTipoReq(String tipo){
		String hql = "from Multicode where idMulticode = ?";
		Object[] params = {new Integer(tipo)};
		List list = dao.findByProps(hql, params);
		if(list.size() != 0){
			Multicode m = (Multicode) list.get(0);
			return m.getCodigo();
		}
		return "";
	}
	
	/**
	 * Obtiene el Identificador de Solicitud 
	 * @param IdUsuario  Identificador de Usuario
	 * @param IdCiclo    Identificador de Ciclo
	 * @param IdMateria  Identificador de Materia
	 * @param seccion    Seccion de Materia
	 * @return identificador de Solicitud pero si no hay en la Base registros devuelve 0
	 * @throws Exception
	 */
	public int getIdSolicitud(String IdUsuario, String IdCiclo, int IdMateria, int seccion)throws Exception{
		int id = 0;
		String sql = getIdSolicitudQuery(IdUsuario, IdCiclo, IdMateria, seccion);
		List lstSol =  jdbc.getQuery(sql, null);
		if (lstSol != null && lstSol.size() > 0){
			DynaBean dyna = (DynaBean) lstSol.get(0);
			if(dyna.get("id_solicitud") != null)
				id = Integer.parseInt(dyna.get("id_solicitud").toString());
		}	
		return id;
	}
	
	public  String getIdSolicitudQuery(String idUsuario, String idCiclo, int idMateria, int seccion){
		String sql = " Select id_solicitud From  registro.solicitud_req_mat 		     " +
					 " Where id_cargo_usr in (select id_cargo_usr from registro.catedratico_materia where id_cargo_usr in ( select id_cargo_usr from seguridad.cargo_usuario_dei where id_usuario = '"+ idUsuario+"')) And id_ciclo = '"+ idCiclo +"' "+
					 " And id_materia ="+idMateria+" And id_seccion = "+seccion+" 		 "; 	    
		return sql; 
	}
	/**
	 * Obtiene el Numero Mayor de la tabla Solicitud 
	 * @return
	 * @throws Exception
	 */
	private int getMaxIdSol()throws Exception{
		int id = 1;
		String sql = QuerysRegistro.getMaxIdSol();
		List lstSol =  jdbc.getQuery(sql, null);
		if (lstSol != null && lstSol.size() > 0){
			DynaBean dyna = (DynaBean) lstSol.get(0);
			if(dyna.get("max") != null)
				id = Integer.parseInt(dyna.get("max").toString());
		}	
		return id;		
	}

	/**
	 * Guarda una Solicitud de Requerimiento de Materia 
	 * @param IdUsuario  Identificador de Usuario
	 * @param IdCiclo    Identificador de Ciclo
	 * @param IdMateria  Identificador de Materia
	 * @param seccion    Seccion de Materia
	 * @return True | False
	 * @throws Exception
	 */
	public int saveSolicitud (String IdUsuario, String IdCiclo, int IdMateria, int seccion)throws Exception{
		int id = this.getIdSolicitud(IdUsuario, IdCiclo, IdMateria, seccion);
		if (id == 0){
			id   = this.getMaxIdSol();   
			int idCatedratico = multiServ.getIdMultiParametro(Constants.Parametro_CATEDRATICO);
			String sql = saveSolicitud(id, IdUsuario, idCatedratico, IdCiclo, IdMateria, seccion);
			boolean retorna = jdbc.saveOrUpdate(sql,null);
			if (!retorna)
				id = 0;
		}
		return id;
	}
	
	public String saveSolicitud(int idSol, String usr, int cargo, String ciclo, int mat, int secc){
		String idCargoUsr = this.getCodUsr(usr, String.valueOf(mat), ciclo, String.valueOf(secc)); 
		String sql = " Insert Into Registro.Solicitud_req_mat (id_solicitud, id_cargo_usr, id_materia, id_ciclo, id_seccion)" + 
					 " VALUES ("+idSol+","+idCargoUsr+","+mat+", '"+ciclo+"', "+secc+")";
		return sql;
	}
	
	public String getCodUsr(String usuario, String idMat, String ciclo, String seccion){
		String idCargoUsr = "";
		String query = "select a.id_cargo_usr as valor from   registro.catedratico_materia a where  a.id_materia = ? and    a.id_seccion = ? and    a.id_ciclo   = ? " +
				"and    a.id_cargo_usr in (select id_cargo_usr from seguridad.cargo_usuario_dei where id_usuario = ?)";
		Object[] params = {new Integer(idMat), new Integer(seccion),ciclo,usuario};
		List list = jdbc.getQuery(query, params);
		if(list.size() != 0){
			DynaBean dyna = (DynaBean) list.get(0);
			idCargoUsr = dyna.get("valor").toString();
		}
		return idCargoUsr;
	}
	
	/**
	 * Guarda un Nuevo requerimiento
	 * @param idSol Identificador de Solicitud
	 * @param tipo  Tipo de Requerimiento Hardware H o Software S 
	 * @param req   Requerimiento
	 * @return      True | False
	 * @throws Exception
	 */
	public boolean saveRequerimiento(int idSol, String tipo, String req) throws Exception{
		if (idSol > 0){
			String sql = saveRequerimiento(idSol, tipo, req, "S");
			return jdbc.saveOrUpdate(sql);			
		}
		return false;
	}
	
	public String saveRequerimiento(int idSol, String tipo, String req, String est ){
		int idReqMat = this.getMaxReqMat();
		String sql = " Insert Into Registro.req_mat ( id_req_mat, id_solicitud, id_tipo, requerimiento,fecha_sol, est_req_mat)  " + 
					 " Values (" + idReqMat + "," + idSol+", "+tipo+", '"+req+"', current_Date , 'S')";
		return sql;
	}	
	
	private int getMaxReqMat(){
		//List list = dao.find("from ReqMat");
		String hql = "select max(idReqMat) from ReqMat";
		List list = dao.find(hql);
		Integer max;
		if(list.size() != 0){
			max = (Integer) list.get(0);
		}else
			max = 0;
		if(max == null)
			return 1;
		return max + 1;
		//return list.size() + 1;
	}
	/**
	 * Actualiza un Requerimiento de Materia
	 * @param tipo     Tipo de Requerimiento S=Software o H = Hardware 
	 * @param req      Requerimiento de Materia
	 * @param idReqMat Identificador de Requerimiento de Materia 
	 * @param estado   Estado del Requerimiento S= Solicitado
	 * @return  True | False        
	 * @throws Exception
	 */
	public boolean updateRequerimiento(String tipo, String req, int idReqMat)throws Exception{
		if (idReqMat > 0){
			String sql = QuerysRegistro.updateRequerimiento(tipo, req, idReqMat, "S");
			return jdbc.saveOrUpdate(sql);	
		}
		return false;
	} 
	
	/**
	 * Elimina una Solicitud de Requerimiento 
	 * @param idSol Identificador de Solicitud
	 * @return True | False
	 * @throws Exception
	 */
	public boolean deleteSolicitud(int idSol)throws Exception{
		if (idSol > 0){
			String sql = QuerysRegistro.deleteSolicitud(idSol);
			return jdbc.delete(sql, null);
		}
		return false;
	}
	
	/**
	 * Elimina un Requerimiento de Materia 
	 * @param idReqMat Identificador de Requerimiento de Materia 
	 * @return sql
	 * @throws Exception
	 */
	public boolean deleteRequerimiento(int idReqMat)throws Exception{
		if (idReqMat > 0){
			String sql = QuerysRegistro.deleteRequerimiento(idReqMat);
			return jdbc.delete(sql, null);
		}
		return false;
	}
	
	/**
	 * Verifica si un Requerimiento esta en la Base de Datos 
	 * @param idReqMat Identificador de Requerimiento 
	 * @return True o False
	 */
	public boolean isRequerimiento(int idReqMat){
		if (idReqMat > 0){
			String sql = QuerysRegistro.isRequerimiento(idReqMat);
			List lst   = jdbc.getQuery(sql, null);
			if (lst != null && lst.size() > 0)
				return true;
		}
		return false;
	}
}
