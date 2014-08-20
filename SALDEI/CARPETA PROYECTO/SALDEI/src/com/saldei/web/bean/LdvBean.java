package com.saldei.web.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mad.lov4j.lovxml.LovXml;
import mad.lov4j.lovxml.columns.ColumnLov;
import mad.lov4j.lovxml.sqlparams.SQLParamLov;
import mad.lov4j.read.ReadLovXml;

import org.jdom.JDOMException;

import com.saldei.util.jdbc.JdbcHelper;
 
public class LdvBean {

	private JdbcHelper 	jdbc  = new JdbcHelper();
	private Connection con = null;
  
  /**
    * Funcion que devuelve los datos, que llenaran la tabla del LdV, en forma de List 
    * @param xmlArchivo request
    * @return Lista con los datos para insertar en la tabla
    */
    public List ldvAllRows (String xmlArchivo,String xmlPath,HttpServletRequest request)throws JDOMException,Exception{

      con = jdbc.getCon();
    	//Declaro un objeto LovXml que sirve para manipular estructura del xml Para los LDV
      LovXml lovXml = null;
      String sql = null;
      
      //Declaracion de un ReadLovXml para la lectura del archivo XML con los datos para el LDV
      ReadLovXml readLovXml = new ReadLovXml();
      List listLdvDatos = new ArrayList();
      PreparedStatement ps = null;
	  Statement st = null;
	  String[] sqlParts = null;
	  String multiple = (String) request.getAttribute("multiple");
	  multiple = (multiple == null?"null":multiple);
	  int i = 1;
	  int numColumn = 0;
      try 
      {
        //se inicializa con la carga del archivo XML
        lovXml = readLovXml.readXml(xmlPath);
        
        request.setAttribute("nombreLista", lovXml.getMessages());
        
        //Se obtiene la consulta sql que devolvera los datos para el LDV
        sql = lovXml.getSql();
        //PreparedStatement ps = getPreparedStatement(sql);
        
        if (lovXml.getTypeSql().equalsIgnoreCase(("preparedstatement"))) {
			ps =  con.prepareStatement(lovXml.getSql());
		} else {
			st = con.createStatement();
			// divimos el sql
			sqlParts = lovXml.getSql().split("[?]");
			sql = sqlParts[0];// Tendra toda la parte del select con el from
		}
        
        //Se obtiene el listado de todos los parametros q necesita la consulta
        List lista = lovXml.getParams().getParams();
        
        
        
        // Se cargan los parametros del request o de la sesion al prepared Statement
        for(Iterator it = lista.iterator(); it.hasNext();i++){
          SQLParamLov param = (SQLParamLov) it.next();
          String value= null;
          
          //Captura los parametros que se pasaron por medio de parametros o Atributos al request, 
          // o como variables de session
          if(lovXml.getParams().getScope().equals("request")){
              value= (String) request.getAttribute(param.getId());
            if(value ==null)
              value = request.getParameter(param.getId());
          }else
            value=(String) request.getSession().getAttribute(param.getId());

          if (lovXml.getTypeSql().equalsIgnoreCase(("preparedstatement"))) {
				ps.setString(param.getPosition(),value);
		  } else {
				//String value2 = request.getParameter(param.getId());
				sql += ((value == null) ? "''" : "" + value + "")
						+ sqlParts[i];
		  }
          //ps.setString(param.getPosition(),value);
        }
          
        if ((sqlParts != null) && (i < (sqlParts.length - 1))) {
			sql += sqlParts[i + 1];
		}
        ResultSet result=null;
		if (lovXml.getTypeSql().equalsIgnoreCase(("preparedstatement"))) {
			result = ps.executeQuery();// executeQuery(ps);
		} else {
			result = st.executeQuery(sql);// executeQuery(st, sql);
		}
        //ResultSet result = ps.executeQuery();
        
        //Se encarga de llenar el mapa hash con los datos de la consulta 
        // con nombres de columnas dinamicos
        while (result.next()){
          listLdvDatos.add(OneRow(result, lovXml, multiple));
        }
        List listaColumnas = lovXml.getColumns().getColumns();
        
        for(Iterator it = listaColumnas.iterator(); it.hasNext();){
        	ColumnLov columna = (ColumnLov) it.next();
        	if(columna.getScript().equals("yes")){
          	  numColumn++;
        	}
        }
        request.setAttribute("numColumn", String.valueOf(numColumn));
      
      }catch (JDOMException e){
        throw e;
      }catch(Exception e){
        throw e;
      }finally{
    	  jdbc.desconect();
      }
      return listLdvDatos;      
    }
    
  /**
   * Inserta los datos del resultado de la consulta al mapa hash, 
   * extrayendo los nombres de las columnas de forma dinamica, por medio del lovXml
   * @param rs
   * @param lovXml
   * @return mapa hash con la fila generada con columnas dinamicas
   * @throws java.lang.Exception
   */
    private Map OneRow(ResultSet rs,LovXml lovXml,String multiple) throws Exception {
      Map map = new HashMap();
      String value = "";
      String checked = "";
      try {
        //se extraen el listado de las columnas con sus debidas propiedades
        List listaColumnas = lovXml.getColumns().getColumns();
        
        for(Iterator it = listaColumnas.iterator(); it.hasNext();){
          ColumnLov columna = (ColumnLov) it.next();
          map.put(columna.getId(),rs.getString(columna.getSelect()));
          if(columna.getScript().equals("yes")){        	  
        	  value = value+columna.getId()+"="+rs.getString(columna.getSelect()).replaceAll(" ", "_")+",";
          }else{
        	  if(columna.getId().equals("p_filter")){
        		  checked = rs.getString(columna.getSelect());
        		  checked = (checked.equals("1")?"checked":" ");
        	  }
          }
        }
        //value = value.substring(0, value.length()-1);
        if(multiple != null && multiple.equals("true")){        
        	map.put("select","<input type='checkbox' name='ck_' id = 'ck_' value ="+value+" "+checked+"  >");
        }
        
      } catch (Exception e) {
          throw e;
      }
      return map;
    }
}