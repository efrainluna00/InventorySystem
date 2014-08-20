/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.registro;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;

import com.saldei.util.commons.Util;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.util.mail.ConfigEmail;
import com.saldei.web.bean.registro.CargarDatosDto;
import com.saldei.web.services.seguridad.UsuarioServices;

public class CargaDatosUtilities {
	
	private JdbcHelper jdbc = new JdbcHelper();
	private Connection con = null;
	private UsuarioServices usuarioServices = new UsuarioServices();
	private Util util = new Util();
	
	public CargaDatosUtilities(){
		con = jdbc.getCon();
	}
	
	public void disconnect(){
		try{
			if(con != null)
				con.close();	
		}catch(Exception e){
			try{
				if(con != null){
					con.close();
				}	
			}catch(Exception ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
		}		
	}
	
	public boolean guardarUsuario(CargarDatosDto dto, int idPerfilEstudiante) throws Exception{
		String query = "select * from seguridad.usuario where lower(id_usuario) = ?";
		Object[] params = {dto.getCarnetEstudiante().toLowerCase()};
		List list = this.getQuery(query, params);
		if(list.size() == 0){
			String primerNombre = "", segundoNombre = "", apellidoRestante = "";
			String[] campoNombre = dto.getPrimerNombre().split(" ");
			if(campoNombre.length == 3){
				apellidoRestante = campoNombre[0];
				primerNombre = campoNombre[1];
				segundoNombre = campoNombre[2];
			}else
				if(campoNombre.length == 2){
					apellidoRestante = campoNombre[0];
					primerNombre = campoNombre[1];
					segundoNombre = " ";
				}else
					if(campoNombre.length == 1){
						primerNombre = campoNombre[0];
						segundoNombre = " ";
						apellidoRestante = " ";
					}else
						if(campoNombre.length == 4){
							apellidoRestante = campoNombre[0];
							primerNombre = campoNombre[1];
							segundoNombre = campoNombre[2] + " " + campoNombre[3];
						}
						else
							if(campoNombre.length == 7){
								apellidoRestante = campoNombre[0];
								primerNombre = campoNombre[2];
							    segundoNombre = campoNombre[4] + " " + campoNombre[6]; 
							}else
								primerNombre = segundoNombre = apellidoRestante = " ";
			Date date = util.getFechaServidor();
			String dateString = util.dateToString(date);
			String fecha = util.fechaConFormato(dateString);
			String pwd = usuarioServices.generarContrasena();
			String sql = "insert into seguridad.usuario(id_usuario, psw_usuario, primer_nom, primer_ape,nombre_restante,apellido_restante,sexo,estado_civil," +
					"fecha_nac,email,est_usuario) values(?, ?, ?, ?, ?, ?, ?, ?,'" + fecha+ "' ,?, ?)";
			
			Object[] paramsInsert = {dto.getCarnetEstudiante(),pwd,primerNombre,dto.getPrimerApellido(),segundoNombre,apellidoRestante,"M",1,dto.getEmail(),"A"};
			saveOrUpdate(sql, paramsInsert);
			
			String sqlInsertPerfilUsuario = "insert into seguridad.usuario_perfil(id_usuario,id_perfil) values(?,?)";
			Object[] paramsInsertPerfilUsuario = {dto.getCarnetEstudiante(), idPerfilEstudiante};
			saveOrUpdate(sqlInsertPerfilUsuario, paramsInsertPerfilUsuario);
			
			ConfigEmail email = new ConfigEmail();
			String correo = dto.getEmail();
			String bodyString = "Ha sido ingresado al Sistema de Administracion de Laboratorios DEI. Su usario es: " + dto.getCarnetEstudiante() + " y su contraseña es: " + pwd;
			String subject = "Ingreso al Sistema de Administracion de Laboratorios SALDEI";
			email.notificarEmail(correo, bodyString, subject);
			return true;
		}
		return false;
	}
	
	public boolean guardarEstudiante(CargarDatosDto dto) throws Exception{
		String query = "select * from registro.estudiante where carnet_estudiante = ?";
		Object[] params = {dto.getCarnetEstudiante()};
		List list = getQuery(query, params);
		if(list.size() == 0){
			String sql = "insert into registro.estudiante(carnet_estudiante,oyente) values(?, ?)";
			Object[] paramsInsert = {dto.getCarnetEstudiante(), "N"};
			saveOrUpdate(sql, paramsInsert);
			return true;
		}
		return false;
	}
	
	public boolean guardarEstudianteXCarrera(CargarDatosDto dto, String carrera) throws Exception{
		String[] datos = carrera.split("-");
		String query = "select * from registro.estudiante_carrera where carnet_estudiante = ? and id_carrera = ? and plan_estudio = ?";
		Object[] params = {dto.getCarnetEstudiante(), datos[0], datos[1]};
		List list = getQuery(query, params);
		if(list.size() == 0){
			String sql = "insert into registro.estudiante_carrera(carnet_estudiante, id_carrera, plan_estudio, fecha_ini, est_est_car) values" +
			"('" + dto.getCarnetEstudiante() + "', '" + datos[0] + "', '" + datos[1]+ "', current_date, 'A')";
			saveOrUpdate(sql, null);
			return true;
		}
		return false;
	}
	
	public boolean guardarMateria(CargarDatosDto dto)throws Exception{
		String query = "select * from registro.materia where cod_materia = ?";
		Object[] params = {dto.getCodMateria()};
		List list = getQuery(query, params);
		if(list.size() == 0){
			String sqlMax = "SELECT MAX(ID_MATERIA) + 1 as num FROM REGISTRO.MATERIA";
			List listMax = getQuery(sqlMax, null);
			int id = 0;
			if(listMax.size() != 0){
				DynaBean dyna = (DynaBean) listMax.get(0);
				String numString = dyna.get("num").toString();
				id = new Integer(numString);
			}
			String sql = "insert into registro.materia(id_materia,cod_materia, nom_materia, uni_valorativas, est_materia) values(?,?, ?, ?, ?)";
			Object[] paramsInsert = {id,dto.getCodMateria(), dto.getNombreMateria(), new Integer(dto.getUnidadesValorativas()), "A"};
			saveOrUpdate(sql, paramsInsert);
			return true;
		}
		return false;
	}
	
	public boolean guardarMateriaCiclo(CargarDatosDto dto, String ciclo) throws Exception{
		String queryMateria = "select id_materia from registro.materia where cod_materia = ?";
		Object[] paramsMateria = {dto.getCodMateria()};
		List listMateria = getQuery(queryMateria, paramsMateria);
		if(listMateria .size() != 0){
			DynaBean dyna = (DynaBean) listMateria.get(0);
			String idMateria = dyna.get("id_materia").toString();
			String sqlMateriaCiclo = "select * from registro.materia_ciclo where id_ciclo = ? and id_materia = ? and id_seccion = ?";
			Object[] paramsMateriaCiclo = {ciclo, new Integer(idMateria), 
										new Integer(dto.getSeccionMateria())};
			List list = getQuery(sqlMateriaCiclo, paramsMateriaCiclo);
			if(list.size() == 0){
				String sql = "insert into registro.materia_ciclo(id_ciclo, id_materia, id_seccion, est_mat_ciclo) values(?,?,?,?)";
				Object[] paramsX = {ciclo, new Integer(idMateria), 
						                       new Integer(dto.getSeccionMateria()), "A"};
				saveOrUpdate(sql, paramsX);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean guardarMateriaInscrita(CargarDatosDto dto, String ciclo) throws Exception{
		String queryMateria = "select id_materia from registro.materia where cod_materia = ?";
		Object[] paramsMateria = {dto.getCodMateria()};
		List listMateria = getQuery(queryMateria, paramsMateria);					
		if(listMateria .size() != 0){				
			DynaBean dyna = (DynaBean) listMateria.get(0);
			String idMateria = dyna.get("id_materia").toString();
			String query = "select * from registro.materia_inscrita where carnet_estudiante = ? and id_materia = ? and id_seccion = ? and id_ciclo = ?";				
			Object[] params = {dto.getCarnetEstudiante(), new Integer(idMateria), new Integer(dto.getSeccionMateria()), ciclo};
			List list = getQuery(query, params);
			if(list.size() == 0){
				String sql = "insert into registro.materia_inscrita(carnet_estudiante, id_materia, id_seccion, id_ciclo," +
								   "retirada, est_mat_inscrita) values(?, ?, ?, ?, ?, ?)";
				Object[] paramsX = {dto.getCarnetEstudiante(), new Integer(idMateria), new Integer(dto.getSeccionMateria()),
						ciclo, "N","A"};
				saveOrUpdate(sql, paramsX);
				return true;
			}
			return false;
		}
		return false;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private List getQuery(String query,Object[] params) throws Exception{		
		RowSetDynaClass rowset = null;		
		List list = null;		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = this.getPreparedStatement(pstmt, params);
		ResultSet rs = pstmt.executeQuery();
		rowset = new RowSetDynaClass(rs);
		rs.close();
		pstmt.close();			
		list = rowset.getRows();		
		return list;
	}
	
	private int resolveType(Object param) {
		int sqlType = Types.VARCHAR;
		if (param == null) {
			sqlType = Types.NULL;
		} else {
			Class paramClass = param.getClass();		
			if (param instanceof String) {
				sqlType = Types.VARCHAR;		
			} else if (paramClass.equals(double.class) || param instanceof Double) {
				sqlType = Types.DOUBLE;
			} else if (param instanceof BigDecimal) {
				sqlType = Types.NUMERIC;
			} else if (param instanceof Calendar) {
				sqlType = Types.DATE;		
			} else if (paramClass.equals(int.class) || param instanceof Integer) {
				sqlType = Types.INTEGER;
			} else if (paramClass.equals(long.class) || param instanceof Long) {
				sqlType = Types.BIGINT;
			} else if (paramClass.equals(float.class) || param instanceof Float) {
				sqlType = Types.REAL;			
			}
		}		
		return sqlType;
	}//resolveType
	
	private PreparedStatement getPreparedStatement(PreparedStatement pstmt,Object[] params) throws Exception{
		if(params != null)
			for(int i=0; i<params.length; i++)				
				pstmt.setObject(i+1,params[i],this.resolveType(params[i]));
		return pstmt;
	}
	
	private boolean saveOrUpdate(String sql,Object[] params){
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = this.getPreparedStatement(pstmt, params);			
			pstmt.executeUpdate();
			pstmt.close();	
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}	
}//class
