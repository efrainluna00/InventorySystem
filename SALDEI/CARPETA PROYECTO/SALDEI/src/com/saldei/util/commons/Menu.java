package com.saldei.util.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.util.jdbc.JdbcHelper;

public class Menu {
	/**
	 * Obtiene un Mapa con la información de un Menu
	 * @param idPerfil Identificador de perfil
	 * @return Mapa con la información del Menu
	 */
	@SuppressWarnings("unchecked")
	public static Map getMapMenu(String p_idPerfil){
		String sql = "";
		if (p_idPerfil != null && !p_idPerfil.equals(""))	
			sql= QuerysSeguridad.getMenu(p_idPerfil);
		else 
			sql= QuerysSeguridad.getMenuLogin();
		
		Map     mapMenu   = new LinkedMap();
		Map     mapHijo   = null;
		Connection con    = null;
		JdbcHelper jdbc   = new JdbcHelper();
		String padre 	  = "";
		String id         = "";
		String valor      = "";
		String url        = "";
		String sep        = "";
		String metodo     = "";
		try {
			con =  jdbc.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs 			= pstmt.executeQuery();
			while (rs.next()){
				id     = String.valueOf(rs.getInt(1));
				valor  = String.valueOf(rs.getInt(2));
				url    = rs.getString(5);
				sep    = rs.getString(8);
				metodo = rs.getString(7);
				boolean separador = false;
				if (!padre.equals(valor)) {
					if (mapHijo != null)
						mapMenu.put(padre, mapHijo);
					mapHijo = new LinkedMap();
					padre = valor;
				}
				if (url == null || url.trim().equals(""))
					url = "#nogo";
				if(sep != null && sep.equals("S"))separador = true;
				if(metodo == null) metodo = "";
				url = url + "=" + metodo;
				MenuBeans menu = new MenuBeans(id, valor, rs.getString(3), rs.getString(4), url, rs.getString(7), rs.getInt(6), separador);
				if (mapMenu.containsKey(id))
					menu.setMapa((Map)mapMenu.get(id));
				mapHijo.put(String.valueOf(rs.getInt(1)), menu);
			}
			if (mapHijo != null)
				mapMenu.put(padre, mapHijo);
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
		return mapMenu;
	}
	
	/**
	 * Obtiene el Menu según un perfil
	 * @param idPerfil Identificador de Perfil
	 * @return String con el Menu 
	 */
	public static String getMenu(String idPerfil)
	{
		String	strMenu = "";
		try {
			Map mapMenu = getMapMenu(idPerfil);
			if (mapMenu.size() > 0)
			{
				strMenu =  "<span class=\"preload1\"></span><span class=\"preload2\"></span>";
				strMenu += "<ul id=\"nav\"> ";
				Map mapTop  = (Map) mapMenu.get("0");
				for (Iterator i = mapTop.keySet().iterator(); i.hasNext();)
				{
					String strKey  = (String) i.next();
					MenuBeans menu = (MenuBeans) mapTop.get(strKey);
					String name    = menu.getNombre();
					Map mapHijo    = menu.getMapa();
					strMenu       += "<li class=\"top\">";
					strMenu       += "<a href=\"#nogo\" class=\"top_link\">";
					if (mapHijo != null && mapHijo.size() > 0) {
						strMenu   += "<span class=\"down\">"+ name +"</span>";
						strMenu   += " <!--[if gte IE 7]><!--></a><!--<![endif]--><!--[if lte IE 6]><table><tr><td><![endif]-->";
						strMenu   += getMenuDet(mapHijo, "1");
					}	
					else
						strMenu += "<span>"+ name +"</span></a>";
					strMenu += "</li>";
				}
				strMenu += "</ul> ";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strMenu;
	} 
	
	/**
	 * Obtiene el Detalle de un Menu 
	 * @param p_Mapa  mapa de donde obtener la infomación
	 * @param p_Nivel Nivel indica si es el primero o son los siguientes
	 * @return String con la porción de Menu
	 */
	public static String getMenuDet(Map p_Mapa, String p_Nivel)
	{
		String strMenu = "";
		try {
			if (p_Nivel.equals("1"))
				strMenu = "<ul class=\"sub\"> ";
			else
				strMenu ="<ul>";			
			for (Iterator i = p_Mapa.keySet().iterator(); i.hasNext();){
				String strKey  = (String) i.next();
				MenuBeans menu = (MenuBeans) p_Mapa.get(strKey);
				String strName =  menu.getNombre(); 
				String strUrl  =  menu.getUrl();
				Map mapMenu	   = menu.getMapa();   
				if (menu.isSeparador())
					strMenu += "<li><b>"+strName + "</b></li>";
				else {
					if(mapMenu != null && mapMenu.size() >0) {
						strMenu += "<li><a href=\""+ strUrl +"\" class=\"fly\">"+ strName ;
						strMenu += " <!--[if gte IE 7]><!--></a><!--<![endif]--><!--[if lte IE 6]><table><tr><td><![endif]-->";
						strMenu += getMenuDet(mapMenu, "0");	
						strMenu +="</li>";
					}	
					else{
						strMenu +="<li> <a href=\""+ strUrl +"\">"+ strName +"</a></li>"; 
					}
				}	
			}
			strMenu += "</ul>";
			strMenu += "<!--[if lte IE 6]></td></tr></table></a><![endif]-->";
		} catch (Exception e) {
			e.printStackTrace();
		}
	return strMenu;	
	}

}
