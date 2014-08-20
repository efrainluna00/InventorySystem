package com.saldei.hibernate.querys;

import com.saldei.util.commons.Constants;

public class QuerysAdministracion {

/** ------------------------------------------Multicode --------------------------------------------*/
	
	public static String getEstadoCivil(){
		//" Select new list(M.idMulticode, M.codigo)      " +
		String hql = 	" From Multicode M Where M.estMulticode ='A' And M.tipoMulticode.estTipoMulti = 'A' " +
						" 		And M.tipoMulticode.idTipoMulticode = (Select P.valor From Parametro P 	" +
						" 							   Where  P.nomParametro = '"+ Constants.Parametro_EST_CIVIL +"')";
		return hql;
	}
	/**
	 * Obtiene las Materias activas
	 * @return
	 */
	public static String getMateriaActiva(){
		String hql = " From Materia Where estMateria = 'A'";
		return hql;
	}
	
	/**
	 * Obtiene el hql para extraer el Objeto Multicode segun un nombre de la Tabla Parametro
	 * @param Parametro Nombre del Parametro en la tabla Parametro 
	 * @return hql que obtiene el Objeto
	 */
	public static String getIdMultiParametro(String Parametro){
		String hql = " From Multicode Where idMulticode = " +
					 "				  (Select valor From Parametro  Where  nomParametro = '"+ Parametro +"')";
		return hql;
	}
	
/*****************************************************	Carrera 	*************************************************************/
	
	/**
	 * Obtiene el hql para extraer los Objetos de Carrera Activas 
	 * @return hql
	 */
	public static String getCarrera(){
		String hql =" From Carrera Where estCarrera ='A' " ; 
		return hql; 	
	}	
	
	public static String getFechaVigencia(String campoClave,String idClave,String tabla){
		String sql="select fecha_ini,fecha_fin from "+tabla+ " ";
		sql+="where fecha_ini=(select max(fecha_ini) from "+tabla+" where "+campoClave+"="+idClave+") and "+campoClave+"="+idClave;
		return sql;
//		select fecha_ini,fecha_fin 
//		from registro.carrera_vigencia 
//		where fecha_ini=(select max(fecha_ini) from registro.carrera_vigencia) and id_carrera=3111
		
	}
	
	public static String updateCarrera(String idCarrera,String idPlan,String nombreCarrera,String idFacultad){
		String sql="update registro.carrera set id_carrera='"+idCarrera+"',plan_estudio='"+idPlan+"', nom_carrera='"+nombreCarrera+"', id_facultad="+idFacultad+" ";
		sql+="where idCarrera='"+idCarrera+"'";
		return sql;
	}
	public static String updateCarreraVigencia(String idCarrera,String idPlan,String fechaInicio,String fechaFin){
		String sql="update registro.carrera_vigencia set  fecha_fin='"+fechaFin+"' where id_carrera='"+idCarrera+"' and plan_estudio='"+idPlan+"' ";
		sql+=" and fecha_ini=(select max(fecha_ini) from registro.carrera_vigencia where id_carrera='"+idCarrera+"' and plan_estudio='"+idPlan+"')";
		return sql;
	}


	
	
}
