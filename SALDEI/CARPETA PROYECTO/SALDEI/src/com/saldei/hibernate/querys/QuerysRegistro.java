package com.saldei.hibernate.querys;

import java.util.Date;

import com.saldei.util.commons.Util;
import com.saldei.web.form.registro.CicloForm;



public class QuerysRegistro {
	private Util util  = new Util();
	
	/** --------------------------------- Ciclo ------------------------------------------*/
	
	/**
	 * Busca ciclo segun parametros de Busqueda 
	 * @param cicloForm Formulario de Ciclo
	 * @param p_Estado  Estado de la Busqueda
	 * @return
	 */
	public  String findCiclo(CicloForm cicloForm, String p_Estado){
		String hql = "From Ciclo Where estCiclo = '"+ p_Estado+"' ";
		if(cicloForm.getNumCiclo() > 0 )
			hql += " And  numCiclo= "+ cicloForm.getNumCiclo()+ " ";
		if(!cicloForm.getAnyoCiclo().equals("Seleccione"))
			hql += " And  anyoCiclo = '"+ cicloForm.getAnyoCiclo()+ "' ";
		if(!cicloForm.getFechaIni().equals(""))
			hql += " And fechaIniCiclo = '" + util.fechaFormatoPostgres(cicloForm.getFechaIni()) + "' ";
		if(!cicloForm.getFechaFin().equals(""))
			hql += " And fechaFinCiclo = '" + util.fechaFormatoPostgres(cicloForm.getFechaFin()) + "' ";
		return hql;		
	}
	/**
	 * Obtiene un Usuario registrado en la BD
	 * @param p_User Usuario a Validar
	 * @return 
	 */
	public static String getCiclo(String p_IdCiclo){
		String hql = "from Ciclo where idCiclo = '"+p_IdCiclo+"'";
		return hql;
	}
	
	/**
	 * Obtiene los ciclos activos y que esten vigentes
	 * @return Lista con ciclos
	 */
	public static String getCiclosActivos(){
		String hql = "From Ciclo Where estCiclo= 'A' And  current_date  <= Coalesce(fechaFinCiclo, current_date)"; 
		return hql;
	}
	/** 
	 * Actualiza un Registro en la Base de Datos
	 * @param p_IdCiclo Identificador de Ciclo
	 * @param p_Estado  Estado a actualizar 
	 * @return
	 */
	public static String updateCiclo(String p_IdCiclo, String p_Estado){
		String sql = "Update  Registro.Ciclo set est_ciclo = '"+p_Estado+"' where id_ciclo= '"+p_IdCiclo+"'  " ;
		return sql;
	}
	
	/**
	 * Determina si hay un ciclo Activo en la BD 
	 * @param p_ciclo S Si N No
	 * @return hql;
	 */
	public static String existeCicloActivo(String p_ciclo){
		String hql = "From Ciclo where cicloActivo = '"+p_ciclo+"'";
		return hql;
	}
	
	public static String getCicloUsuario(String Id_Usuario){
		String hql = " Select distinct(id.materiaCiclo.id.ciclo.idCiclo) From CatedraticoMateria where id.idUsuario = '"+ Id_Usuario+"'  " +		
					 " And id.materiaCiclo.id.ciclo.estCiclo= 'A' ";
		return hql;
	}
	
	public static String getCicloString(String Id_Usuario, String Ciclo){
		String hql = " Select new List( " +
					 "        id.materiaCiclo.id.seccionXMateria.id.materia.idMateria, 		" +
					 "        id.materiaCiclo.id.seccionXMateria.id.materia.nomMateria, 	" +
					 "		  id.materiaCiclo.id.seccionXMateria.id.idSeccion)				" +
					 " From CatedraticoMateria Where 										" +
				 "      id.idUsuario = '"+ Id_Usuario+"' And  estCatMat = 'A'  		    " +
					 " And  id.materiaCiclo.id.ciclo.idCiclo = '"+Ciclo+"'             		" +
					 " And  id.materiaCiclo.id.seccionXMateria.id.materia.estMateria= 'A'	" +
					 " And  id.materiaCiclo.id.seccionXMateria.estSeccMat= 'A'		   		" +			 
					 " Order By id.materiaCiclo.id.seccionXMateria.id.materia.idMateria,    " +
					 "          id.materiaCiclo.id.seccionXMateria.id.idSeccion             " ;
		return hql;
	}

	/************************************ Requerimiento ************************************************/
	
	/**
	 * Obtiene el Identificador de Solicitud 
	 * @param IdUsuario Identificador de Usuario
	 * @param IdCiclo   Identificador de Ciclo 
	 * @param IdMateria Identificador de Materia
	 * @param seccion   Seccion de Materia
	 * @return sql
	 */
	public static String getIdSolicitud(String IdUsuario, String IdCiclo, int IdMateria, int seccion){
		String sql = " Select id_solicitud From  registro.solicitud_req_mat 		     " +
					 " Where id_usuario= '"+ IdUsuario+"' And id_ciclo = '"+ IdCiclo +"' "+
					 " And id_materia ="+IdMateria+" And id_seccion = "+seccion+" 		 "; 	    
		return sql; 
	}
	
	/**
	 * Obtiene el Numero Maximo de la tabla solicitud 
	 * @return sql
	 */
	public static String getMaxIdSol(){
		String sql = " Select  (Coalesce(Max(id_solicitud),0) +1) As Max " +
					 " From  registro.solicitud_req_mat 				 " ;	    
		return sql; 
	}
		
	/**
	 * Obtiene el sql para guardar una Solicitud
	 * @param usr    Identificador de Usuario
	 * @param cargo  Identificador de cargo de Catedratico
	 * @param ciclo  Identificador de Ciclo 
	 * @param mat    Identificador de Materia
	 * @param secc   Seccion de Materia
	 * @return sql
	 */
	public static String saveSolicitud(int idSol, String usr, int cargo, String ciclo, int mat, int secc){
		String sql = " Insert Into Registro.Solicitud_req_mat (id_solicitud, id_materia, id_ciclo, id_seccion, id_usuario, id_cargo )" + 
					 " VALUES ("+idSol+", "+mat+", '"+ciclo+"', "+secc+", '"+usr+"', "+cargo+" )";
		return sql;
	}
	
	/**
	 * Obtiene el sql para Elminiar una Solicitud de Requerimiento  
	 * @param idSol Identificador de Solicitud
	 * @return sql
	 */
	public static String deleteSolicitud(int idSol){
		String sql = " Delete From registro.solicitud_req_mat "+
					 " Where  id_solicitud = "+ idSol + "     "; 
		return sql;
	} 
	
	/**
	 * Obtiene el sql para obtener el Requerimientos de una Solicitud de Requerimiento
	 * @param idSol Identificador de Solicitud
	 * @return sql
	 */
	public static String getRequerimiento(int idSol){
		String sql = " Select id_req_mat, id_solicitud, id_tipo, requerimiento, fecha_sol, fecha_resol, est_req_mat" +
					 " From registro.req_mat  Where id_solicitud =  "+ idSol +" "; 
		return sql; 
	}	
	
	/**
	 * Obtiene el sql para guardar un Requerimiento , Se pone en estado de S= Solicitado
	 * @param idSol Identificador de Solicitud de Requerimiento
	 * @param tipo  Tipo de Requerimiento H= Hardware y S= Software
	 * @param req   Requerimeinto Solicitado
	 * @return 		sql
	 */
	public static String saveRequerimiento(int idSol, String tipo, String req, String est ){
		String sql = " Insert Into Registro.req_mat ( id_solicitud, id_tipo, requerimiento,fecha_sol, est_req_mat)  " + 
					 " Values ("+ idSol+", '"+tipo+"', '"+req+"', current_Date , '"+est+"')";
		return sql;
	}	

	/**
	 * Obtiene el sql para actualizar un Requerimiento 
	 * @param tipo     Tipo de Requeriemiento de Hardaware H  y Software S  
	 * @param req      Requerimiento 
	 * @param idReqMat Identificador de Requerimiento 
	 * @param estado   Estado de Requerimiento
	 * @return		   sql
	 */
	public static String updateRequerimiento(String tipo, String req, int idReqMat, String estado){
		String sql = " Update registro.req_mat " +
					 " Set id_tipo= '"+tipo+"', requerimiento= '"+req +"', est_req_mat ='"+estado+"' "+
					 " Where id_req_mat = "+ idReqMat + " "; 
		return sql;
	} 
	
	/**
	 * Obtiene el sql para eliminar un Requerimiento
	 * @param idReqMat Identificador de Requerimiento de materia
	 * @return sql
	 */
	public static String deleteRequerimiento(int idReqMat){
		String sql = " Delete From registro.req_mat " +
					 " Where id_req_mat = "+ idReqMat + " "; 
		return sql;
	}
	
	/**
	 * Obtiene el sql para verificar si Requerimiento esta registrado
	 * @param idReqMat Identificador de Requerimiento de materia
	 * @return sql
	 */
	public static String isRequerimiento(int idReqMat){
		String sql = " Select 1 From registro.req_mat   " +
					 " Where id_req_mat = "+ idReqMat + " "; 
		return sql;
	} 
	
	public static String estudianteInscrito(String carnet,String idMateria,String idSeccion,String idCiclo){
		String hql="select a.id.estudiante.carnetEstudiante from MateriaInscrita a where a.id.estudiante.carnetEstudiante='" +carnet+ "' and a.id.materiaCiclo.id.seccionXMateria.id.idSeccion="+idSeccion+" and a.id.materiaCiclo.id.seccionXMateria.id.materia.idMateria="+idMateria+" and a.id.materiaCiclo.id.ciclo.idCiclo='"+idCiclo+"'";
		return hql;		
	}

	
	
	
	public static String getCicloActivo(){
		String ciclo="select idCiclo from Ciclo where estCiclo = 'A' and cicloActivo='S'";
		return ciclo;
	}
	//DEVUELVE LAS MATERIAS DE UN CICLO DETERMINADO
	/**
	 * Obtiene el sql para obtener las materias para un ciclo determinado
	 * @param idCiclo Identificador de ciclo
	 * @return sql
	 */
	public static String materiasXCiclo(String idCiclo){
		String 	sql= " Select b.id_materia || '-' || a.id_seccion as id_materia, b.nom_materia || ' - ' || '0' || a.id_seccion as nom_materia from";
				sql+=" registro.materia_ciclo a join   registro.materia b ";
				sql+=" on  a.id_materia=b.id_materia   where a.id_ciclo='"+idCiclo+"' ";
				sql+=" order by b.nom_materia, a.id_seccion";
		return sql;
	}
	
	
	/**
	 * Obtiene el sql para obtener las secciones de materia de un ciclo determinado
	 * @param idCiclo Identificador de ciclo
	 * @param idMateria Identificador de materia 
	 * @return sql
	 */
	public static String seccionXMateriaXciclo(String idCiclo,String idMateria){
		String sql="select a.id_seccion ";
		sql+="from registro.materia_ciclo a,registro.materia c,registro.ciclo d ";
		sql+="where a.id_ciclo='"+idCiclo+"' and a.id_materia="+idMateria+" ";
		sql+="and a.id_materia=c.id_materia and c.est_materia='A' and a.id_ciclo=d.id_ciclo and d.est_ciclo='A' ";		
		return sql;
	}
	
	/**
	 * Obtiene el sql para obtener los requerimiemtos de materia realizados durante un ciclo
	 * @param idCiclo Identificador de ciclo 
	 * @return sql
	 */	
	public static String requerimientosXMateria(String idCiclo){
		String sql="select e.num_ciclo,e.anyo_ciclo,a.id_ciclo,a.id_materia,'0' || a.id_seccion as seccion,cast(b.fecha_sol as varchar) as fecha_sol,cast(b.fecha_resol as varchar) as fecha_resol, ";
		sql+="b.id_tipo,b.requerimiento,b.est_req_mat,d.cod_materia,d.nom_materia,d.desc_materia,f.id_usuario,f.primer_nom,f.primer_ape, ";
		sql+="(case when f.nombre_restante is null then '' else f.nombre_restante end) as nombre_restante, ";
		sql+="(case when f.apellido_restante is null then '' else f.apellido_restante end) as apellido_restante, i.codigo ";
		sql+="from registro.solicitud_req_mat a,registro.req_mat b,registro.catedratico_materia c,registro.materia d,registro.ciclo e,seguridad.usuario f,seguridad.cargo_usuario_dei g,registro.materia_ciclo h,registro.multicode i ";
		sql+="where a.id_ciclo='"+idCiclo+"' ";
		sql+="and a.id_solicitud=b.id_solicitud and a.id_materia=c.id_materia and a.id_cargo_usr=c.id_cargo_usr and c.id_cargo_usr=g.id_cargo_usr and g.id_usuario=f.id_usuario ";
		sql+="and a.id_ciclo=c.id_ciclo and a.id_seccion=c.id_seccion  ";
		sql+="and c.id_materia=h.id_materia and c.id_seccion=h.id_seccion and c.id_ciclo=h.id_ciclo and h.id_materia=d.id_materia and h.id_ciclo=e.id_ciclo and e.est_ciclo='A' and d.est_materia='A' and b.id_tipo=i.id_multicode ";
		sql+=" ";
		sql+="order by d.nom_materia,a.id_seccion,b.id_tipo,b.est_req_mat,b.fecha_sol";		
		return sql;
	}
	
	
	/**
	 * Obtiene el sql para listar los requerimiemtos de materia realizados durante un ciclo
	 * @param idCiclo Identificador de ciclo
	 * @param idMateria Identificador de materia 
	 * @return sql
	 */	
	public static String requerimientosXMateria(String idCiclo,String idMateria){	
		String sql="select e.num_ciclo,e.anyo_ciclo,a.id_ciclo,a.id_materia,'0' || a.id_seccion as seccion,cast(b.fecha_sol as varchar) as fecha_sol,cast(b.fecha_resol as varchar) as fecha_resol, ";
		sql+="b.id_tipo,b.requerimiento,b.est_req_mat,d.cod_materia,d.nom_materia,d.desc_materia,f.id_usuario,f.primer_nom,f.primer_ape, ";
		sql+="(case when f.nombre_restante is null then '' else f.nombre_restante end) as nombre_restante, ";
		sql+="(case when f.apellido_restante is null then '' else f.apellido_restante end) as apellido_restante,i.codigo ";
		sql+="from registro.solicitud_req_mat a,registro.req_mat b,registro.catedratico_materia c,registro.materia d,registro.ciclo e,seguridad.usuario f,seguridad.cargo_usuario_dei g,registro.materia_ciclo h,registro.multicode i  ";
		sql+="where a.id_ciclo='"+idCiclo+"' and a.id_materia="+idMateria+" ";
		sql+="and a.id_solicitud=b.id_solicitud and a.id_materia=c.id_materia and a.id_cargo_usr=c.id_cargo_usr and c.id_cargo_usr=g.id_cargo_usr and g.id_usuario=f.id_usuario ";
		sql+="and a.id_ciclo=c.id_ciclo and a.id_seccion=c.id_seccion  ";
		sql+="and c.id_materia=h.id_materia and c.id_seccion=h.id_seccion and c.id_ciclo=h.id_ciclo and h.id_materia=d.id_materia and h.id_ciclo=e.id_ciclo and e.est_ciclo='A' and d.est_materia='A'and b.id_tipo=i.id_multicode ";
		sql+=" ";
		sql+="order by d.nom_materia,a.id_seccion,b.id_tipo,b.est_req_mat,b.fecha_sol";		
		return sql;
	}
	
	
	
	/**
	 * Obtiene el sql para listar los usuarios que tengan cargo de catedratico para un ciclo
	 * @return sql
	 */	
	public static String catedraticosCiclo(String idCiclo,String parametro){
		String sql="select distinct u.id_usuario as idusuario, u.primer_nom || ' ' || u.primer_ape as nombre ";
		sql+="from seguridad.usuario u, seguridad.cargo_usuario_dei c, registro.multicode m,registro.catedratico_materia f  ";
		sql+="where f.id_ciclo='"+idCiclo+"' ";
		sql+="and u.id_usuario=c.id_usuario and c.id_cargo_usr=f.id_cargo_usr  and m.id_multicode=c.id_cargo and m.id_multicode in (select valor from registro.parametro where nom_parametro='"+parametro+"') order by u.id_usuario";
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar todos los usuarios que tengan cargo de catedratico 
	 * @return sql
	 */	
	public static String catedraticos(String parametro){
		String sql="select distinct u.id_usuario as idusuario, u.primer_nom || ' ' || u.primer_ape as nombre ";
		sql+="from seguridad.usuario u, seguridad.cargo_usuario_dei c, registro.multicode m,registro.catedratico_materia f  ";		
		sql+="where u.id_usuario=c.id_usuario and c.id_cargo_usr=f.id_cargo_usr and  m.id_multicode in (select valor from registro.parametro where nom_parametro='"+parametro+"') order by u.id_usuario";
		return sql;
	}
	
	
	/**
	 * Obtiene el sql para listar el nombre de una materia especifica 
	 * @return sql
	 */	
	public static String getMateria(String idMateria){
		String sql="select nombre_materia from registro.materia where id_materia="+idMateria;		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar el nombre de un usuario especifico 
	 * @return sql
	 */	
	public static String getUsuario(String idUsuario){
		String sql="select primer_nom || ' ' || primer_ape from seguridad.usuario where id_usuario="+idUsuario;		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los estudiantes inscritos a las materias impartidas durante un ciclo especifico
	 * @param idCiclo Identificador de ciclo 
	 * @return sql
	 */	
	public static String estudiantesXMateria(String idCiclo){
		String sql="select tb1.carnet as carnet,tb1.cic as ciclo,tb1.idmat as idmateria,tb1.codmat as codigomateria,tb1.mat as nombremateria, '0' || '' || tb1.sec as seccion,tb2.iduser as idcatedratico,tb1.primernom as primernom,(case when tb1.nomrestante is null then '' else tb1.nomrestante end) as nombreresto,tb1.primerape as primerape,(case when tb1.aperestante is null then '' else tb1.aperestante end) as aperest,tb1.emailestudiante,tb2.primernom || ' ' || tb2.primerape as NombreCatedratico from ";
		sql+="(select a.carnet_estudiante as carnet,a.id_ciclo as cic,a.id_materia as idmat,a.id_seccion as sec,c.cod_materia as codmat,c.nom_materia as mat,d.primer_nom as primernom,d.primer_ape as primerape,d.nombre_restante as nomrestante,d.apellido_restante as aperestante,d.email as emailestudiante ";
		sql+="from registro.materia_inscrita a,registro.materia c,seguridad.usuario d ";
		sql+="where a.id_ciclo='"+idCiclo+"' and a.est_mat_inscrita='A' and a.id_materia=c.id_materia and c.est_materia='A' and a.carnet_estudiante=d.id_usuario and d.est_usuario='A' ";
		sql+=")tb1 inner join (select a.id_usuario as iduser,b.id_ciclo,b.id_materia,b.id_seccion,a.primer_nom as primernom, a.primer_ape as primerape,a.nombre_restante as nomrestante,a.apellido_restante as aperestante ";
		sql+="from seguridad.usuario a, registro.catedratico_materia b ";
		sql+="where a.id_usuario=b.id_usuario and b.id_seccion=1 and b.id_materia=3 and b.id_ciclo='012008')tb2 ";
		sql+="on tb1.cic = tb2.id_ciclo and tb1.idmat=tb2.id_materia and tb1.sec=tb2.id_seccion order by idmateria,seccion,primerape";		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los requerimiemtos de materia realizados durante un ciclo, materia o seccion
	 * @param idCiclo Identificador de ciclo
	 * @param idCampo Identificador de materia o Identificar de seccion dependiendo del valor del parametro 
	 * @param parametro Campo para seleccionar si busca por idMateria o idSeccion
	 * @return sql
	 */	
	public static String estudiantesXMateria(String idCiclo,String idCampo,int parametro){
		String sql="";
		// 1 = materia
		if(parametro==1){
			sql="select tb1.carnet as carnet,tb1.cic as ciclo,tb1.idmat as idmateria,tb1.codmat as codigomateria,tb1.mat as nombremateria, '0' || '' || tb1.sec as seccion,tb2.iduser as idcatedratico,tb1.primernom as primernom,(case when tb1.nomrestante is null then '' else tb1.nomrestante end) as nombreresto,tb1.primerape as primerape,(case when tb1.aperestante is null then '' else tb1.aperestante end) as aperest,tb1.emailestudiante,tb2.primernom || ' ' || tb2.primerape as NombreCatedratico from ";
			sql+="(select a.carnet_estudiante as carnet,a.id_ciclo as cic,a.id_materia as idmat,a.id_seccion as sec,c.cod_materia as codmat,c.nom_materia as mat,d.primer_nom as primernom,d.primer_ape as primerape,d.nombre_restante as nomrestante,d.apellido_restante as aperestante,d.email as emailestudiante ";
			sql+="from registro.materia_inscrita a,registro.materia c,seguridad.usuario d ";
			sql+="where a.id_ciclo='"+idCiclo+"' and a.id_materia="+idCampo+ "and a.est_mat_inscrita='A' and a.id_materia=c.id_materia and c.est_materia='A' and a.carnet_estudiante=d.id_usuario and d.est_usuario='A' ";
			sql+=")tb1 inner join (select a.id_usuario as iduser,b.id_ciclo,b.id_materia,b.id_seccion,a.primer_nom as primernom, a.primer_ape as primerape,a.nombre_restante as nomrestante,a.apellido_restante as aperestante ";
			sql+="from seguridad.usuario a, registro.catedratico_materia b ";
			sql+="where a.id_usuario=b.id_usuario and b.id_seccion=1 and b.id_materia=3 and b.id_ciclo='012008')tb2 ";
			sql+="on tb1.cic = tb2.id_ciclo and tb1.idmat=tb2.id_materia and tb1.sec=tb2.id_seccion order by idmateria,seccion,primerape";		
			
		}
		// 2 = seccion
		if(parametro==2){
			sql="select tb1.carnet as carnet,tb1.cic as ciclo,tb1.idmat as idmateria,tb1.codmat as codigomateria,tb1.mat as nombremateria, '0' || '' || tb1.sec as seccion,tb2.iduser as idcatedratico,tb1.primernom as primernom,(case when tb1.nomrestante is null then '' else tb1.nomrestante end) as nombreresto,tb1.primerape as primerape,(case when tb1.aperestante is null then '' else tb1.aperestante end) as aperest,tb1.emailestudiante,tb2.primernom || ' ' || tb2.primerape as NombreCatedratico from ";
			sql+="(select a.carnet_estudiante as carnet,a.id_ciclo as cic,a.id_materia as idmat,a.id_seccion as sec,c.cod_materia as codmat,c.nom_materia as mat,d.primer_nom as primernom,d.primer_ape as primerape,d.nombre_restante as nomrestante,d.apellido_restante as aperestante,d.email as emailestudiante ";
			sql+="from registro.materia_inscrita a,registro.materia c,seguridad.usuario d ";
			sql+="where a.id_ciclo='"+idCiclo+"' and a.id_seccion="+idCampo+ " and a.est_mat_inscrita='A' and a.id_materia=c.id_materia and c.est_materia='A' and a.carnet_estudiante=d.id_usuario and d.est_usuario='A' ";
			sql+=")tb1 inner join (select a.id_usuario as iduser,b.id_ciclo,b.id_materia,b.id_seccion,a.primer_nom as primernom, a.primer_ape as primerape,a.nombre_restante as nomrestante,a.apellido_restante as aperestante ";
			sql+="from seguridad.usuario a, registro.catedratico_materia b ";
			sql+="where a.id_usuario=b.id_usuario and b.id_seccion=1 and b.id_materia=3 and b.id_ciclo='012008')tb2 ";
			sql+="on tb1.cic = tb2.id_ciclo and tb1.idmat=tb2.id_materia and tb1.sec=tb2.id_seccion order by idmateria,seccion,primerape";		
			
		}		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los requerimiemtos de materia realizados durante un ciclo, materia y seccion
	 * @param idCiclo Identificador de ciclo
	 * @param idMateria Identificador de materia  
	 * @param idSeccion Identificar de seccion
	 * @return sql
	 */	
	public static String estudiantesXMateria(String idCiclo,String idMateria,String idSeccion){
		String sql="";
		sql="select tb1.carnet as carnet,tb1.cic as ciclo,tb1.idmat as idmateria,tb1.codmat as codigomateria,tb1.mat as nombremateria, '0' || '' || tb1.sec as seccion,tb2.iduser as idcatedratico,tb1.primernom as primernom,(case when tb1.nomrestante is null then '' else tb1.nomrestante end) as nombreresto,tb1.primerape as primerape,(case when tb1.aperestante is null then '' else tb1.aperestante end) as aperest,tb1.emailestudiante,tb2.primernom || ' ' || tb2.primerape as NombreCatedratico from ";
		sql+="(select a.carnet_estudiante as carnet,a.id_ciclo as cic,a.id_materia as idmat,a.id_seccion as sec,c.cod_materia as codmat,c.nom_materia as mat,d.primer_nom as primernom,d.primer_ape as primerape,d.nombre_restante as nomrestante,d.apellido_restante as aperestante,d.email as emailestudiante ";
		sql+="from registro.materia_inscrita a,registro.materia c,seguridad.usuario d ";
		sql+="where a.id_ciclo='"+idCiclo+"' and a.id_materia="+idMateria+" and a.id_seccion="+idSeccion+ " and a.est_mat_inscrita='A' and a.id_materia=c.id_materia and c.est_materia='A' and a.carnet_estudiante=d.id_usuario and d.est_usuario='A' ";
		sql+=")tb1 inner join (select a.id_usuario as iduser,b.id_ciclo,b.id_materia,b.id_seccion,a.primer_nom as primernom, a.primer_ape as primerape,a.nombre_restante as nomrestante,a.apellido_restante as aperestante ";
		sql+="from seguridad.usuario a, registro.catedratico_materia b ";
		sql+="where a.id_usuario=b.id_usuario and b.id_seccion=1 and b.id_materia=3 and b.id_ciclo='012008')tb2 ";
		sql+="on tb1.cic = tb2.id_ciclo and tb1.idmat=tb2.id_materia and tb1.sec=tb2.id_seccion order by idmateria,seccion,primerape";		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los perfiles asociados a un usuario especifico
	 * @param idUsuario Identificador de usuario
	 * @return sql
	 */	
	public static String perfilUsuario(String idUsuario){
		String sql="";
		sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
		sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c ";
		sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil order by b.id_perfil,c.primer_ape ";		
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los perfiles asociados al tipo de usuario seleccionado
	 * @param idTipoUsuario Identificador de tipoUsuario, 1= usuario dei, 2= usuario externo, 3= estudiantes
	 * @return sql
	 */	
	public static String perfilUsuario(int tipoUsuario){
		String sql="";
		switch(tipoUsuario){
			case 2:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_dei d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by b.id_perfil,c.primer_ape ";
				break;
			case 3:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_externo d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by b.id_perfil,c.primer_ape ";
				break;				
			case 1:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, registro.estudiante d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.carnet_estudiante order by b.id_perfil,c.primer_ape ";
				break;		
		}				
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar los perfiles asociados al tipo de usuario seleccionado y al usuario especifico
	 * @param idTipoUsuario Identificador de tipoUsuario, 1= usuario dei, 2= usuario externo, 3= estudiantes
	 * @return sql
	 */	
	public static String perfilUsuario(String idUsuario,int tipoUsuario){
		String sql="";
		switch(tipoUsuario){
			case 1:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_dei d ";
				sql+="where a.id_usuario='"+idUsuario+"' and  a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by b.id_perfil,c.primer_ape ";
				break;
			case 2:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_externo d ";
				sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by b.id_perfil,c.primer_ape ";
				break;				
			case 3:
				sql="select b.id_perfil,b.nom_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, registro.estudiante d ";
				sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.carnet_estudiante order by b.id_perfil,c.primer_ape ";
				break;	
		}				
		return sql;
	}
	
	
	/**
	 * Obtiene el sql para listar informacion personal de los usuarios segun el tipo de usuario
	 * @param idTipoUsuario Identificador de tipoUsuario, 1= usuario dei, 2= usuario externo, 3= estudiantes
	 * @return sql
	 */	
	public static String informacionPersonal(int tipoUsuario){
		String sql="";
		switch(tipoUsuario){
			case 1:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, seguridad.usuario_dei b,registro.multicode c ";
				sql+="where a.id_usuario = b.id_usuario and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;
			case 2:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, seguridad.usuario_externo b,registro.multicode c ";
				sql+="where a.id_usuario = b.id_usuario and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;				
			case 3:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, registro.estudiante b,registro.multicode c ";
				sql+="where a.id_usuario = b.carnet_estudiante and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;	
		}				
		return sql;
	}
	
	/**
	 * Obtiene el sql para listar informacion personal de los usuarios segun el tipo de usuario
	 * @param idTipoUsuario Identificador de tipoUsuario, 1= usuario dei, 2= usuario externo, 3= estudiantes
 	 * @param primerApellido Apellido de usuario como filtro de busqueda 
	 * @return sql
	 */	
	public static String informacionPersonal(String primerApellido,int tipoUsuario){
		String sql="";
		switch(tipoUsuario){
			case 1:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, seguridad.usuario_dei b,registro.multicode c ";
				sql+="where lower(a.primer_ape) like '"+primerApellido+"%' and a.id_usuario = b.id_usuario and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;
			case 2:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, seguridad.usuario_externo b,registro.multicode c ";
				sql+="where lower(a.primer_ape) like '"+primerApellido+"%' and a.id_usuario = b.id_usuario and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;				
			case 3:
				sql="select a.id_usuario,a.primer_nom,a.primer_ape,(case when a.apellido_restante is null then '' else a.apellido_restante end) as aperestante,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
				sql+="a.sexo,c.codigo,a.fecha_nac,a.direccion_part,a.email,a.telefono_casa,a.telefono_cel,a.telefono_trabajo from seguridad.usuario a, registro.estudiante b,registro.multicode c ";
				sql+="where lower(a.primer_ape) like '"+primerApellido+"%' and a.id_usuario = b.carnet_estudiante and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
				break;	
		}				
		return sql;
	}
	
	public static String getCarrerasVigencia(String idCarrera, String PlanEstudio){
		String hql= " From CarreraVigencia Where id.carrera.id.idCarrera = '"+idCarrera +"' " +
					" And id.carrera.id.planEstudio = '"+PlanEstudio +"' ";
		return hql;		
	}
	
	public static String getUsuarioVigencia(String idUsuario){
		String hql= " From UsuarioVigencia Where id.usuario.idUsuario = '"+idUsuario +"' " ;
		return hql;		
	}

	public static String isUsuarioVigencia(String idUsuario, Date fechaIni, Date fechaFin){
		String hql= " From UsuarioVigencia Where id.usuario.idUsuario = '"+idUsuario +"'" +
					" And  fechaFin ='"+fechaFin +"' And id.fechaIni= '"+fechaIni+"' " ;
		return hql;		
	}
	
	public static String MayorVigencia(String idUsuario, String fechaIni){
		String hql= " Select distinct(1) From UsuarioVigencia V Where V.id.usuario.idUsuario = '"+idUsuario +"'" +
				    " And '"+fechaIni+"' > (Select Max(id.fechaIni) From UsuarioVigencia  Where id.usuario.idUsuario = V.id.usuario.idUsuario) " ;
		return hql;		
	}
	public static String CarMayorVig(String idCar, String idPlan, String fechaIni){
		String hql= " Select distinct(1) From CarreraVigencia V Where V.id.carrera.id.idCarrera = '"+idCar+"' And V.id.carrera.id.planEstudio= '"+idPlan +"' " +
				    " And '"+fechaIni+"' > (Select Max(id.fechaIni) From CarreraVigencia Where id.carrera.id.idCarrera = V.id.carrera.id.idCarrera And" +
				    " V.id.carrera.id.planEstudio=  id.carrera.id.planEstudio) " ;
		return hql;		
	}
	
	
	
}
