/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectJdbcDao{

	private Connection con = null;
	private String usuario;
	private String contraseña;
	private String url;
	private String clazz = "org.postgresql.Driver";
	
	protected ConnectJdbcDao(){}
	
	protected ConnectJdbcDao(String usuario,String contraseña,String url){
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.url = url;
	}
	
	/**	 
	 * @param usuario con el que se conectara
	 * @param contraseña con la que se conectara	 
	 * @param url especificacion de la ubicación de la base de datos a la que se conectara
	 * @return un objeto de tipo Connection que tendra la conexión hacia esa base de datos
	 */
	protected Connection connect(String usuario,String contraseña,String url){
		try{
			Class.forName(this.clazz);
			con = DriverManager.getConnection(url,usuario,contraseña);
		}catch(SQLException sqlex){
			sqlex.printStackTrace();			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return con;
	}
	
	protected String connectValid(){
		try{
			Class.forName(this.clazz);
			con = DriverManager.getConnection(this.url,this.usuario,this.contraseña);
			con.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return "";
	}
	
	/**	 
	 * Usuario contraseña y url son los que se pasaron al constructor
	 * @return Devolvera un objeto Connection con acceso a la base de datos
	 */
	protected Connection connect(){
		try{
			Class.forName(this.clazz);
			con = DriverManager.getConnection(this.url,this.usuario,this.contraseña);
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return con;
	}
	/**
	 * @param con conexión que se desea cerrar
	 */
	protected void disconnect(Connection con){
		try{
			con.close();
		}catch(SQLException sqlex){			
			sqlex.printStackTrace();
		}
	}	
	
}//clase
