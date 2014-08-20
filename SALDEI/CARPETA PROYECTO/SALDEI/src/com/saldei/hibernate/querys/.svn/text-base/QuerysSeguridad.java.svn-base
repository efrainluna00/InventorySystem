package com.saldei.hibernate.querys;

import java.util.Date;

import com.saldei.util.commons.Constants;
import com.saldei.web.form.seguridad.LoginForm;

/**
 * Contiene las Querys Hql y Sql del modulo de Seguridad
 * @author WiRoCaRo
 * @version 1.0
 * 
 */
public class QuerysSeguridad {

	/** ---------------------------------	 Login 		---------------------------------  */
	
	/**
	 * Verifica si un Usuario Existe en la BD
	 * @param loginFrm Formulario de Login
	 * @return hql
	 */
	public static String queryLoginExist(LoginForm loginFrm){
		String hql = " from Usuario u Where  u.idUsuario = '"+loginFrm.getIdUsuario()+"' And u.pswUsuario = '"+loginFrm.getPswUsuario()+"' "; 
		return hql;
	}

	/**
	 * Valida que un Usuario tenga permisos para entrar al Sistema
	 * @param loginFrm formulario de Login 
	 * @return hql
	 */
	public static String queryLoginValid(LoginForm loginFrm){
		String hql = " from Usuario u Where  u.idUsuario = '"+loginFrm.getIdUsuario()+"' And u.pswUsuario = '"+loginFrm.getPswUsuario()+"' " +
					 " And u.estUsuario= 'A' ";
		return hql;
	}
		
	/**
	 * Obtiene los Id de Perfil de un Usuario
	 * @param p_User   Identificador de Usuario
	 * @param p_estado Estado en el que se encuentre 
	 * @return hql
	 */
	public static String getPerfilUsr(String p_User, String p_estado) {
		String hql  = "Select p.id.perfil.idPerfil from UsuarioPerfil p where p.id.usuario.idUsuario = '"+p_User +"'  " +
					  " and p.id.usuario.estUsuario = '"+p_estado +"' and  p.id.perfil.estPerfil= '"+p_estado +"'     ";
		return hql;	
	}
	
	/** ------------------------------------ Menu  -------------------------------------------------*/
	private static String getMenuSelect(){
		String sel = " Select Distinct O.ID_OPCION, O.ID_OPCION_PADRE, O.NOM_OPCION, O.DESC_OPCION, O.URL_OPCION, O.ORDEN, O.METODO, O.IS_SEPARADOR " ;
		return sel;
	}
	
	public static String getMenuLogin() {
		String sql = getMenuSelect();		
			   sql += " From Seguridad.Opcion O  Where O.ID_OPCION = (Select VALOR From Registro.Parametro " +
					  " Where Nom_Parametro = '"+ Constants.Parametro_LOGIN+"') And O.EST_OPCION = 'A' ";
			   sql += " Union ";
			   sql += getMenuSelect();
			   sql += " From Seguridad.Opcion O Where O.ID_OPCION_PADRE= (Select VALOR From Registro.Parametro " +
				  	  " Where Nom_Parametro = '"+ Constants.Parametro_LOGIN+"') And O.EST_OPCION = 'A' " +
				  	  " order By ID_OPCION_PADRE desc";
		return sql;	
	}
	public static  String getMenu(String p_idPerfil){
		String sql  =getMenuSelect(); 
			   sql += " FROM SEGURIDAD.OPCION O JOIN SEGURIDAD.OPCION_PERFIL OP ON O.ID_OPCION = OP.ID_OPCION " +
				      " WHERE  OP.ID_PERFIL IN ("+p_idPerfil+")AND O.EST_OPCION = 'A' " +
				      " ORDER BY O.ID_OPCION_PADRE desc, O.ORDEN   ";
		return sql;
	}
	
	/**-------------------------------------- Usuarios -------------------------------------------- */
	
	/**
	 * Obtiene un Usuario registrado en la BD
	 * @param p_User Usuario a Validar
	 * @return 
	 */
	public static String getUser(String p_User){
		String hql = " From Usuario where idUsuario = '"+p_User+"'";
		return hql;
	}

	
	public static String isUserDei(String p_User){
		String hql = " From UsuarioDei Where codigoEmpleado = '"+p_User+"'";
		return hql;
	}
	
	public static String isUserExt(String p_User){
		String hql = " From UsuarioExterno Where idUsuario = '"+p_User+"'";
		return hql;
	}
	
	public static String isEstudiante(String p_User){
		String hql = " From Estudiante Where carnetEstudiante = '"+p_User+"'";
		return hql;
	}
	
	/**
	 * Obtiene Todos los Usuarios activos 
	 * @return
	 */
	public static String getUserActivos(){ 
		String hql = "from Usuario where estUsuario = 'A'  Order By idUsuario ";
		return hql;
	}
	
	/**
	 * Verifica si un Usuario esta en la Tabla Usuario DEI
	 * @param p_Estado Estado del Usuario A Activo I inactivo S solicitado
	 * @return
	 */
	public static String getUserDei(String p_Estado){
		String hql = "from Usuario u where u.usuarioDeis.idUsuario = u.idUsuario and u.estUsuario = '"+p_Estado+"' ";
		return hql;
	}
	
	public static String findUser(){
		String sql = " Select u.Id_Usuario, " +
 		  			 " s.carnet_estudiante, " +
 		  			 " d.codigo_empleado,   " +
 		  			 " e.id_usuario As externo, " +
 		  			 " u.psw_Usuario, " +
 		  			 " u.Primer_Nom, " +
 		  			 " u.Primer_Ape, " +
 		  			 " u.Nombre_Restante, " + 
 		  			 " u.apellido_restante, " +
 		  			 " u.fecha_nac,	 " +
 		  			 " u.direccion_part, " +
 		  			 " u.email,	 " +
 		  			 " u.telefono_casa, " +
 		  			 " u.telefono_cel,	 " +
 		  			 " u.telefono_trabajo, " +
 		  			 " u.est_usuario , " +
 		  			 " u.sexo, " +
 		  			 " u.estado_civil, " +
 		  			 " u.nit, " +
 		  			 " u.dui, " +
 		  			 " u.isss, " +
 		  			 " u.nup, " +
 		  			 " u.autorizado_por, " +
 		  			 " d.extension_uca, " +
 		  			 " e.comentario, " +
 		  			 " e.solicitado_por, " +
 		  			 " s.oyente, " +
 		  			 " s.ciclo_ingreso, "+
                     " s.anyo_ingreso," +
 		  			 " v.fecha_ini, " +
 		  			 " v.fecha_fin,  " +
 		  			 " c.id_carrera, " +
 		  			 " c.plan_estudio " +
 		  			 " From   seguridad.Usuario u left join seguridad.usuario_dei d " +
 		  			 " On u.Id_Usuario =  d.codigo_empleado, " +
 		  			 " seguridad.usuario u2 left Join seguridad.usuario_externo  e " +
 		  			 " On u2.id_usuario = e.id_usuario, " +
 		  			 " seguridad.usuario u3 left Join registro.estudiante s " +
 		  			 " On u3.id_usuario = s.carnet_estudiante, " +
 		  			 " seguridad.usuario u4 left Join seguridad.usuario_vigencia v " +
 		  			 " On u4.id_usuario = v.id_usuario, " +
 		  			 " seguridad.usuario u5 left Join registro.estudiante_carrera c " +
 		  			 " On u5.id_usuario = c.carnet_estudiante And " +
 		  			 " c.fecha_ini = (Select Max(fecha_ini) From registro.estudiante_carrera Where " + 
 		  			 " carnet_estudiante =  u5.id_usuario) " +
 		  			 " Where  u.Id_Usuario = u2.id_usuario And  u.Id_Usuario = u3.id_usuario And " +
 		  			 " u3.Id_Usuario = u2.id_usuario And u.Id_Usuario = u4.id_usuario And " +
 		  			 " u.Id_Usuario = u5.id_usuario And " +
 		  			 " v.fecha_ini = (Select Max(fecha_ini) From seguridad.usuario_vigencia Where " + 
 		  			 " id_Usuario =  u4.id_usuario)   " ;

		return sql;	
	}

	
	
	/** ----------------------------------  Perfiles ---------------------------------------------------*/
	/**
	 * Obtiene todos los perfiles segun el Estado de parametro
	 * @param p_estado Estado de la Busqueda 
	 * @return hql
	 */
	public static String getPerfilByStatus(String p_estado){
		String hql = "from Perfil where estPerfil = '"+p_estado+"'";
		return hql;
	}
	
	
	/**
	 * Obtiene una lista de los Periles asignados a un Usuario
	 * @param p_usuario usuario a obtener los perfiles
	 * @return hql
	 */
	public static String getUsuarioPerfil(String p_usuario){
		 String hql =   " Select new list(u.id.perfil.idPerfil, u.id.perfil.nomPerfil, u.id.perfil.descPerfil, u.id.perfil.estPerfil) " +
			  			" From UsuarioPerfil u Where u.id.usuario.idUsuario = '"+p_usuario+"' And u.id.perfil.estPerfil = 'A'" +
			  			" And u.id.usuario.estUsuario = 'A' Order By u.id.perfil.nomPerfil ";
		return hql;
	}
	
	public static String getNotUsuarioPerfil(String p_usuario){
		 String hql =   " Select new list(idPerfil, nomPerfil, descPerfil, estPerfil) " +
			  			" From Perfil Where estPerfil= 'A' And idPerfil Not In " +
			  			"			  (Select u.id.perfil.idPerfil From UsuarioPerfil u Where u.id.usuario.idUsuario = '"+p_usuario+"') " +
			  			" Order By nomPerfil ";
		return hql;
	}
	
	public static String deletePerfilUsuario(String p_usuario){
		 String hql = " Delete  From UsuarioPerfil u Where u.id.usuario.idUsuario = '"+p_usuario+"' "; 
		return hql;
	}
	
	public static String perfilInUsuario(String p_idUsuario, String p_idPerfil){
		String hql = "from UsuarioPerfil where id.usuario.idUsuario = '" + p_idUsuario + "' and " +
					 "id.perfil.idPerfil = " + p_idPerfil;
		return hql;
	}
	
	/**----------------------------------------- Organizacion DEI -------------------------------------------*/
	
	public static String getCargoUser(String p_Usuario){
		 String sql = " Select M.Id_Multicode, M.Codigo, M.Descripcion From Registro.Multicode M Inner Join     " +
					  " Seguridad.Cargo_Usuario_Dei C ON  M.Id_Tipo_Multicode= '"+ Constants.Table_Cargo+"' And " + 
					  " C.Id_Cargo = M.ID_Multicode And M.Est_Multicode = 'A' And C.Est_Cargo_Dei = 'A'         " +
					  " Where C.Id_Usuario = '"+p_Usuario +"'                                                   ";
		return sql;
	}	
		
	public static String getCargos(String p_Usuario){
		String sql = " SELECT M.ID_MULTICODE, M.CODIGO, M.descripcion FROM REGISTRO.MULTICODE M    " +
					 " WHERE M.ID_TIPO_MULTICODE = '"+ Constants.Table_Cargo+"' AND                " +
					 " M.ID_MULTICODE NOT IN (SELECT C.ID_CARGO FROM SEGURIDAD.cargo_usuario_dei C " +
					 " WHERE  C.ID_USUARIO = '"+p_Usuario +"' AND C.est_cargo_dei ='A' )           ";
		return sql;
	}
	
	/** ------------------------------------------Opcion Perfil --------------------------------------------*/
	
	public static String getNoOpcionPerfil(String p_Perfil){
		String hql = " Select new list(O.idOpcion, O.nomOpcion, O.descOpcion )      " +
		 			 " From Opcion O Where O.estOpcion ='A' And O.idOpcion Not In   " +
		 			 "			(Select OP.id.opcion.idOpcion from OpcionPerfil OP 	" +
		 			 " Where OP.id.perfil.idPerfil = '"+ p_Perfil +"')";
		return hql;
	}
	
	public static String getOpcionPerfil(String p_Perfil){
		String hql = " Select new list( op.id.opcion.idOpcion, op.id.opcion.nomOpcion, op.id.opcion.descOpcion )        " +
		 			 " From OpcionPerfil op Where op.id.perfil.idPerfil='"+ p_Perfil +"' and op.id.opcion.estOpcion ='A'";
		
		return hql;
	}
	
	/* funcion crreada por el grupo de tesis que desarrollo el modulo de inventario
	 * con el proposito de dar seguiridad al sistema.
	 * esta funcion devuelve el queri que se necesita para obtener las opciones
	 * que estan ligadas a los perfiles de un usuario
	 * recibe como parametro un string con los id de los perfiles de usuarios
	 * separados por como
	 */
	public static String getOpcionesPerfilUsurario(String p_Perfil){
		String hql = " Select new list(op.id.opcion.urlOpcion )        " +
		 			 " From OpcionPerfil op Where op.id.perfil.idPerfil  in ("+ p_Perfil +") " +
		 			 " and op.id.opcion.estOpcion ='A' " +
		 			 " and op.id.opcion.urlOpcion <> ''";
		
		return hql;
	}
	
	public static String DeleteOpcPerf(String p_Perfil){
		String hql = "delete from OpcionPerfil where id.perfil.idPerfil='"+ p_Perfil +"'";
		return hql;
	}
	
	/**  ---------------------------------------------------------------------- */
	
	/**
	 * Obtiene el sql para listar los perfiles asociados a un usuario especifico
	 * @param idUsuario Identificador de usuario
	 * @return sql
	 */	
	public static String perfilUsuario(String idUsuario){
		String sql="";
		sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
		sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c ";
		sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil order by c.primer_ape,b.id_perfil ";		
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
			case 1:
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_dei d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.codigo_empleado order by c.primer_ape,b.id_perfil ";
				break;
			case 2:
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_externo d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by c.primer_ape,b.id_perfil ";
				break;				
			case 3:
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, registro.estudiante d ";
				sql+="where a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.carnet_estudiante order by c.primer_ape,b.id_perfil ";
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
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_dei d ";
				sql+="where a.id_usuario='"+idUsuario+"' and  a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.codigo_empleado order by c.primer_ape,b.id_perfil ";
				break;
			case 2:
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, seguridad.usuario_externo d ";
				sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.id_usuario order by c.primer_ape,b.id_perfil ";
				break;				
			case 3:
				sql="select b.id_perfil,b.nom_perfil,b.desc_perfil,c.id_usuario,c.primer_nom,c.primer_ape ";
				sql+="from seguridad.usuario_perfil a,seguridad.perfil b,seguridad.usuario c, registro.estudiante d ";
				sql+="where a.id_usuario='"+idUsuario+"' and a.id_usuario=c.id_usuario and a.id_perfil=b.id_perfil and c.id_usuario=d.carnet_estudiante order by c.primer_ape,b.id_perfil ";
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
				sql+="where a.id_usuario = b.codigo_empleado and a.estado_civil=c.id_multicode order by a.primer_ape,a.apellido_restante ";
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
	
	public static String usuarioAuditoria(){
		String sql="select tb1.id_usuario as idusuario,b.primer_nom as primernom,b.primer_ape as primerape from ( ";
		sql+="select distinct id_usuario from seguridad.auditoria )tb1 ";
		sql+="inner join seguridad.usuario b on tb1.id_usuario=b.id_usuario ";
		sql+="order by b.primer_ape asc ";
		return sql;
	}
	public static String tablaAuditoria(){
		String sql="select nom_tabla from seguridad.	auditoria ";
		return sql;
	}
	public static String auditoriaMaestroFechas(String fechaInicial,String fechaFinal){
		String sql="select tb1.id_auditoria,tb1.id_usuario,tb1.fecha_accion,tb1.nom_tabla,tb1.accion,tb1.llave_primaria,b.primer_nom,b.primer_ape ";
		sql+="from ( select id_auditoria,id_usuario,cast(fecha_accion as varchar) as fecha_accion,nom_tabla,accion,llave_primaria from seguridad.auditoria a ";
		sql+="where fecha_accion between '"+fechaInicial+"' and '"+fechaFinal+"' and accion <> 'cargadatos'";
		sql+=")tb1 inner join seguridad.usuario b on tb1.id_usuario=b.id_usuario order by tb1.nom_tabla,tb1.accion,tb1.fecha_accion";
		return sql;
	}
	public static String auditoriaMaestroUsuario(String fechaInicial,String fechaFinal,String idUsuario){
		String sql="select tb1.id_auditoria,tb1.id_usuario,tb1.fecha_accion,tb1.nom_tabla,tb1.accion,tb1.llave_primaria,b.primer_nom,b.primer_ape ";
		sql+="from ( select id_auditoria,id_usuario,cast(fecha_accion as varchar) as fecha_accion,nom_tabla,accion,llave_primaria from seguridad.auditoria a ";
		sql+="where fecha_accion between '"+fechaInicial+"' and '"+fechaFinal+"' and id_usuario='"+idUsuario+"' and accion <> 'cargadatos' ";
		sql+=")tb1 inner join seguridad.usuario b on tb1.id_usuario=b.id_usuario order by tb1.nom_tabla,tb1.accion,tb1.fecha_accion";
		return sql;
	}
	public static String auditoriaMaestroTabla(String fechaInicial,String fechaFinal,String tabla){
		String sql="select tb1.id_auditoria,tb1.id_usuario,tb1.fecha_accion,tb1.nom_tabla,tb1.accion,tb1.llave_primaria,b.primer_nom,b.primer_ape ";
		sql+="from ( select id_auditoria,id_usuario,cast(fecha_accion as varchar) as fecha_accion,nom_tabla,accion,llave_primaria from seguridad.auditoria a ";
		sql+="where fecha_accion between '"+fechaInicial+"' and '"+fechaFinal+"' and nom_tabla='"+tabla+"' ";
		sql+=")tb1 inner join seguridad.usuario b on tb1.id_usuario=b.id_usuario order by tb1.nom_tabla,tb1.accion,tb1.fecha_accion";
		
		return sql;
	}
	public static String auditoriaMaestroTodos(String fechaInicial,String fechaFinal,String tabla,String idUsuario){
		String sql="select tb1.id_auditoria,tb1.id_usuario,tb1.fecha_accion,tb1.nom_tabla,tb1.accion,tb1.llave_primaria,b.primer_nom,b.primer_ape ";
		sql+="from ( select id_auditoria,id_usuario,cast(fecha_accion as varchar) as fecha_accion,nom_tabla,accion,llave_primaria from seguridad.auditoria a ";
		sql+="where fecha_accion between '"+fechaInicial+"' and '"+fechaFinal+"' and id_usuario='"+idUsuario+"' and nom_tabla='"+tabla+"' ";
		sql+=")tb1 inner join seguridad.usuario b on tb1.id_usuario=b.id_usuario order by tb1.nom_tabla,tb1.accion,tb1.fecha_accion";
		return sql;
	}
	
	public static String usuariosSistema(int estado){		
		String sql="";
		String constante1="",constante2="";		
		constante1="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante, ";
		constante1+="tb2.email,tb2.sexo,tb2.fecha_ini,tb2.fecha_fin,tb2.est_usuario from ";
		constante1+="(select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei ";
		constante1+="union select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo ";
		constante1+="union select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante order by idTipoUsuario )tb1 inner join ( ";
		constante1+="select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape, ";
		constante1+="(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
		constante1+="(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante, ";
		constante1+="a.email as email,a.sexo,cast(b.fecha_ini as varchar) as fecha_ini,cast(b.fecha_fin as varchar) as fecha_fin,a.est_usuario ";
		constante1+="from seguridad.usuario a,seguridad.usuario_vigencia b where ";
		constante2+="a.id_usuario=b.id_usuario )tb2 on tb1.idusuario=tb2.idusuario order by tb1.idTipoUsuario,tb2.primerape";
		switch(estado){
			// usuario activos
			case 1:
				sql+=constante1;
				sql+="a.est_usuario='A' and ";
				sql+=constante2;				
				break;
			// usuarios inactivos		
			case 2:
				sql+=constante1;
				sql+="a.est_usuario='I' and ";
				sql+=constante2;			
				break;
			// todos
			case 3:
				sql+=constante1;				
				sql+=constante2;			
				break;
			
		}
		return sql;		
	}
	public static String usuariosSistema(String fechaInicial,String fechaFinal,int estado){
		String sql="";
		String constante1="",constante2="";		
		constante1="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante, ";
		constante1+="tb2.email,tb2.sexo,tb2.fecha_ini,tb2.fecha_fin,tb2.est_usuario from ";
		constante1+="(select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei ";
		constante1+="union select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo ";
		constante1+="union select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante order by idTipoUsuario )tb1 inner join ( ";
		constante1+="select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape, ";
		constante1+="(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
		constante1+="(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante, ";
		constante1+="a.email as email,a.sexo,cast(b.fecha_ini as varchar) as fecha_ini,cast(b.fecha_fin as varchar) as fecha_fin,a.est_usuario ";
		constante1+="from seguridad.usuario a,seguridad.usuario_vigencia b where ";
		constante2+="a.id_usuario=b.id_usuario )tb2 on tb1.idusuario=tb2.idusuario order by tb1.idTipoUsuario,tb2.primerape";
		switch(estado){
			// usuario activos
			case 1:
				sql+=constante1;
				sql+="a.est_usuario='A' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
				sql+=constante2;				
				break;
			// usuarios inactivos		
			case 2:
				sql+=constante1;
				sql+="a.est_usuario='I' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
				sql+=constante2;			
				break;
			// todos
			case 3:
				sql+=constante1;
				sql+="fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
				sql+=constante2;			
				break;
			
		}
		return sql;		
	}
	public static String usuariosSistema(int tipoUsuario,int estado){
		String sql="";
		String constante1="",constante2="",constante3="";		
		constante1="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante, ";
		constante1+="tb2.email,tb2.sexo,tb2.fecha_ini,tb2.fecha_fin,tb2.est_usuario from (";
		constante2=")tb1 inner join ( ";
		constante2+="select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape, ";
		constante2+="(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
		constante2+="(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante, ";
		constante2+="a.email as email,a.sexo,cast(b.fecha_ini as varchar) as fecha_ini,cast(b.fecha_fin as varchar) as fecha_fin,a.est_usuario ";
		constante2+="from seguridad.usuario a,seguridad.usuario_vigencia b where ";
		constante3+="a.id_usuario=b.id_usuario )tb2 on tb1.idusuario=tb2.idusuario order by tb2.primerape";
		switch(tipoUsuario){
			// Usuario DEI
			case 1:
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;
						sql+="a.est_usuario='A' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;
						sql+="a.est_usuario='I' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;						
						sql+=constante3;
						break;
				}
				break;
			// Usuario Externo
			case 2:
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;
						sql+="a.est_usuario='A' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;
						sql+="a.est_usuario='I' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;						
						sql+=constante3;
						break;
					}
				break;
			// Estudiante
			case 3:				
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;
						sql+="a.est_usuario='A' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;
						sql+="a.est_usuario='I' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;						
						sql+=constante3;
						break;
				}
				break;
		}
		return sql;
	}
	public static String usuariosSistema(String fechaInicial,String fechaFinal,int tipoUsuario,int estado){
		String sql="";
		String constante1="",constante2="",constante3="";		
		constante1="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante, ";
		constante1+="tb2.email,tb2.sexo,tb2.fecha_ini,tb2.fecha_fin,tb2.est_usuario from (";
		constante2=")tb1 inner join ( ";
		constante2+="select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape, ";
		constante2+="(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante, ";
		constante2+="(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante, ";
		constante2+="a.email as email,a.sexo,cast(b.fecha_ini as varchar) as fecha_ini,cast(b.fecha_fin as varchar) as fecha_fin,a.est_usuario ";
		constante2+="from seguridad.usuario a,seguridad.usuario_vigencia b where ";
		constante3+="a.id_usuario=b.id_usuario )tb2 on tb1.idusuario=tb2.idusuario order by tb2.primerape";		
		switch(tipoUsuario){
			// Usuario DEI
			case 1:
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;
						sql+="a.est_usuario='A' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;
						sql+="a.est_usuario='I' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei";
						sql+=constante2;						
						sql+="fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
			}
				break;
			// Usuario Externo
			case 2:
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;
						sql+="a.est_usuario='A' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;
						sql+="a.est_usuario='I' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo";
						sql+=constante2;
						sql+="fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					}
				break;
			// Estudiante
			case 3:
				switch(estado){
					case 1:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;
						sql+="a.est_usuario='A' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 2:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;
						sql+="a.est_usuario='I' and fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
					case 3:
						sql+=constante1;
						sql+="select carnet_estudiante as idusuario, '3' as idTipoUsuario from registro.estudiante";
						sql+=constante2;
						sql+="fecha_ini>='"+fechaInicial+"' and fecha_fin<='"+fechaFinal+"' and ";
						sql+=constante3;
						break;
				}
				break;				
		}
		return sql;
	}

	

	public static String isEstudianteCarrera(String est, String idCar, String planEst){
		String hql = " From EstudianteCarrera Where id.estudiante.carnetEstudiante = '"+ est +"'" +
					 " And id.carrera.id.idCarrera = '"+idCar+"' And id.carrera.id.planEstudio = '"+planEst+"'" +
					 " And estEstCar ='A' ";
		return hql;
	}
	
	public static String EstCarreraActiva(String est){
		String hql = " From EstudianteCarrera Where id.estudiante.carnetEstudiante = '"+ est +"'" +
		 			 " And estEstCar ='A' ";
		return hql;
	}
	public static String EstCarrera(String est){
		String hql = " From EstudianteCarrera Where id.estudiante.carnetEstudiante = '"+ est +"'";
		return hql;
	}
	
	public static String isUserVigencia(String p_User, Date p_fechaIni){
		String hql = " From UsuarioVigencia Where id.usuario.idUsuario = '"+p_User+"' And id.fechaIni = '"+p_fechaIni+"' ";
		return hql;
	}


	
	
}
