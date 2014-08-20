/**
 * 
 */
package com.saldei.web.services.administracion;

import java.util.LinkedList;
import java.util.List;

import com.saldei.util.jdbc.JdbcHelper;

/**
 * @author Carlos
 *
 */
public class RptCommonServices {
	
	JdbcHelper jdbc = new JdbcHelper();	
	
	public int hayDatos(String tabla,String parametroBusqueda,String valor){
		String query="";
		List list = new LinkedList();		
		query+="select * from "+tabla+" where "+parametroBusqueda+"="+"'"+valor+"'";
		try {
			list = jdbc.getQuery(query, null);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list.size();
		
	}
	
	public int hayDatos(String tabla){
		String query="";
		List list = new LinkedList();
		query+="select * from "+tabla;
		try {
			list = jdbc.getQuery(query, null);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list.size();
		
	}
}
