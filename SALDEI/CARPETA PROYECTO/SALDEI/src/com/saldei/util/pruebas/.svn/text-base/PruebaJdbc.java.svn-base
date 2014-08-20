/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.pruebas;

import java.util.List;

import com.saldei.util.jdbc.JdbcHelper;

public class PruebaJdbc {

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		try{
			JdbcHelper jdbc = new JdbcHelper();
			List list = jdbc.getQuery("select fecha_ini_ciclo from adminlab.Ciclo", null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	

}//clase
