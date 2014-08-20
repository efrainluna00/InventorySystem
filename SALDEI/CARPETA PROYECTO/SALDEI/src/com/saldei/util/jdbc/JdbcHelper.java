/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.RowSetDynaClass;

public class JdbcHelper{
	
 	private String url = "jdbc:postgresql://usaldei.uca.edu.sv:5433/SALDEI";
	
    //private String url = "jdbc:postgresql://localhost:5433/SALDEI";	
	
	private ConnectJdbcDao dao = new ConnectJdbcDao("postgres","postgres",url);
	
	private Connection con = null;
	
	public JdbcHelper(){}	
	
	public Connection getCon(){
		con = dao.connect();
		return con;
	}
	
	/**
	 *  Desconecta la conexion a la BD que se ha conectado desde esta clase	 
	 */
	public void desconect(){
		try{
			if(con!=null)
				con.close();
		}catch(Exception ex){
			if(con!= null){
				try{
					con.close();
				}catch(Exception x){
					x.printStackTrace();
				}
			}
			ex.printStackTrace();
		}
	}
	
	/**	 
	 * @param sql es el insert o update que se ejecutara
	 * @param params los parametros que posee
	 * @return TRUE si se realizo el insert o el update y FALSE si no se realizo
	 */
	public boolean saveOrUpdate(String sql,Object[] params){
		con = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = this.getPreparedStatement(pstmt, params);			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;
		}catch(Exception ex){
			try{
				if(con != null)
					con.close();
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param sql es el insert con parametros incluidos
	 * @return TRUE si se realizo el insert o el update y FALSE si no se realizo
	 */	
	public boolean saveOrUpdate(String sql){
		con = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);						
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;
		}catch(Exception ex){
			try{
				if(con != null)
					con.close();
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}			
			ex.printStackTrace();
			return false;
		}
	}
	
	/**	 
	 * @param sql Sentencia delete que se desea ejecutar
	 * @return TRUE si se realizo el delete y FALSE si no se realizo
	 */
	public boolean delete(String sql,Object[] params){
		con = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = this.getPreparedStatement(pstmt, params);			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;
		}catch(Exception ex){
			try{
				if(con != null)
					con.close();
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			ex.printStackTrace();			
			return false;
		}
	}
	
	/**	 
	 * @param tabla Nombre de la tabla de la que se devolvera todos los valores
	 * @return Objeto de tipo List con los datos de la tabla
	 */
	public List getAll(String tabla){
		String query = "select * from " + tabla;
		RowSetDynaClass rowset = null;
		List list = null;
		con = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rowset = new RowSetDynaClass(rs);
			rs.close();
			pstmt.close();
			con.close();
			list = rowset.getRows();
		}catch(Exception ex){
			if(con != null){
				try{
					con.close();
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
			}
			ex.printStackTrace();
		}
		return list;
	}
	
	/**	 
	 * @param query Es el query que se desea ejecutar
	 * @return Objeto de tipo RowSetDynaClass con los datos que trae el query
	 */
	public List getQuery(String query,Object[] params){		
		RowSetDynaClass rowset = null;
		con = null;
		List list = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt = this.getPreparedStatement(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			rowset = new RowSetDynaClass(rs);
			rs.close();
			pstmt.close();
			con.close();
			list = rowset.getRows();
		}catch(Exception ex){
			if(con != null){
				try{
					con.close();
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
			}
			ex.printStackTrace();
		}
		return list;
	}
	
	
	
	/**	 
	 * @param query Es el query que indica que número deseo traer de BD
	 * @param params Parametros que posee el query
	 * @return Número que regresa el query
	 */
	public int getNumberFromDB(String query,Object[] params){
		con = null;
		con = dao.connect();
		int number = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt = this.getPreparedStatement(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				number = rs.getInt(1);
			rs.close();
			pstmt.close();
			con.close();			
		}catch(Exception ex){
			if(con != null)
				try{
					con.close();
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
			ex.printStackTrace();
		}
		return number;
	}
	
	/**	 
	 * @param query Es el query que se ejecutara
	 * @return String con el contenido del query en formato Xml
	 */
	public String getXmlResultSet(String query){
		con = null;
		XmlResultSet xmlRs = null;
		con = dao.connect();
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			xmlRs = new XmlResultSet(rs);
			rs.close();
			pstmt.close();
			con.close();
			return xmlRs.getXml();
		}catch(Exception ex){
			if(con != null){
				try{
					con.close();
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
			}
			ex.printStackTrace();			
		}
		return "";
	}
	
	/**	 
	 * @param query Es el query que se ejecutara
	 * @return Número que se colocara como id
	 */
	public int getSequence(String query){
		con = null;
		int sequence = 0;
		con = dao.connect();		
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				sequence = rs.getInt(1);
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception ex){			
			if(con != null){
				try{
					con.close();
				}catch(SQLException sqlex){
					sqlex.printStackTrace();
				}
			}
			ex.printStackTrace();
		}
		return sequence;
	}
	
	/**
	 * @param user con el que se conectara
	 * @param password con el que se conectara
	 * @param query que contendra la tabla a la que se desea ver si se tiene permiso o no
	 * @return valor booleano true si tiene permisos y false si no los tiene
	 */
	public boolean isUserValid(String user,String password,String query){
		con = null;
		ConnectJdbcDao connect = new ConnectJdbcDao(user,password,url);
		con = connect.connect();
		try{
			if(con != null){				
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
				pstmt.close();
				con.close();
				return true;
			}else
				return false;
		}catch(Exception ex){
			if(con != null)
				try{
					con.close();
				}catch(Exception x){
					x.printStackTrace();
				}
			return false;
		}
	}//isValidUser	

	/**	 
	 * @param user con el que se conectara
	 * @param password con el que se conectara
	 * @return 0 si existe el usuario de BD, 1 si se ha bloqueado la contraseña y 2 si se produce otro error al momento de conectarse
	 */
	public int isUserValid(String user,String password){
		ConnectJdbcDao connect = new ConnectJdbcDao(user,password,url);
		String con = connect.connectValid();		
		if (con.equals(""))
			return 0;
		else
			if(con.substring(0,9).equals("ORA-28000"))
				return 1;
			else
				return 2;
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
	
}//clase
