package org.extremecomponents.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mad.lov4j.lovxml.LovXml;
import mad.lov4j.lovxml.columns.ColumnLov;
import mad.lov4j.read.ReadLovXml;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.AutoGenerateColumns;
import org.extremecomponents.table.core.TableModel;

import org.jdom.JDOMException;
 

/**
 * Clase utilizada para autogeneracion de las COLUMNAS de la tabla
 * Se forman las columnas segun la lista obtenida del atributo
 * lovAttribute almacenado en el Request.
 */
public class AutoGenerateColumnsImpl implements AutoGenerateColumns 
{
  /**
   * Agrega las columnas de forma dinamica, segun el parametro del xmlArchivo
   * @param model
   */
  public void addColumns(TableModel model) {
    Iterator iterator = columnsToAdd(model).iterator();
    
    while (iterator.hasNext()) {
      Map columnToAdd = (Map) iterator.next();
      Column  column = model.getColumnInstance(); 
      column.setProperty((String) columnToAdd.get("propiedad"));
      column.setCell((String) columnToAdd.get("celda"));
      column.setTitle((String) columnToAdd.get("titulo"));
      column.setFilterCell((String) columnToAdd.get("filterCell"));
      //column.setFilterStyle(filterStyle)
      model.addColumn(column);
    }
    
  }
    
    /**
     * Se encarga de introducir todas las columnas con sus respectivas propiedades
     * @param model
     * @return Devuelve el listado de columnas con sus propiedades
     * @throws JDOMException
     * @throws Exception
     */
    private List columnsToAdd(TableModel model) {
        List columnas = new ArrayList();
        String xmlArchivo = (String) model.getContext().getRequestAttribute("xmlPath");
        String multiple = (String) model.getContext().getRequestAttribute("multiple");
        multiple = (multiple == null?"null":multiple);

        //Se obtiene la direccion fisica del archivo XML
        //String xmlPath ="/"+model.getContext().getContextPath()+"/xml/"+xmlArchivo+".xml";
        
        //Declaro un objeto LovXml que sirve para manipular estructura del xml Para los LOV
        LovXml lovXml = null;
        
        //Declaracion de un ReadLovXml para la lectura del archivo XML con los datos para el LOV
        ReadLovXml readLovXml = new ReadLovXml();
        
        try 
        {
            //se inicializa con la carga del archivo XML
            lovXml = readLovXml.readXml(xmlArchivo);        
            List listaColumnas = lovXml.getColumns().getColumns();
            
            //Se itera por cada columna y se agrega con su propiedad, tipo de celda, y titulo de columna
            for(Iterator it = listaColumnas.iterator(); it.hasNext();){
              ColumnLov columna = (ColumnLov) it.next();
              //String visible = columna.getVisible();
              if(columna.isShow()){
            	  if(columna.isDropList()){	
            		  columnas.add(columnToAdd(columna.getId(),"display", columna
							.getTitle(),true));
            	  }else{
            		  columnas.add(columnToAdd(columna.getId(),"display", columna
  							.getTitle(),false));
            	  }	  
				}              
              //columnas.add(columnToAdd(columna.getId(),"display",columna.getTitle()));
            }
            if(multiple != null && multiple.equals("true")){
            	columnas.add(columnToAdd("select","display", "Select",false));
            }
            

        }catch (JDOMException e){
          e.printStackTrace();;
        }
        catch(Exception e){
          e.printStackTrace();
        }
      
        /*columns.add(columnToAdd("fullName", "display"));
        columns.add(columnToAdd("nickName", "display"));
        columns.add(columnToAdd("term", "display"));
        columns.add(columnToAdd("born", "date"));
        columns.add(columnToAdd("died", "date"));
        columns.add(columnToAdd("career", "display"));/**/
        return columnas;
    }
    
    /**
     * Se encarga de introducir todas las propiedades de una columna al mapa
     * @param property
     * @param celda
     * @param titulo
     * @return Mapa de la columna con sus propiedades
     */
    private Map columnToAdd(String property, String celda,String titulo,boolean isDroopList) {
      Map column = new HashMap();
      column.put("propiedad", property);
      column.put("celda", celda);
      column.put("titulo",titulo);
      column.put("filterCell",(isDroopList)?"droplist":"filter");
      return column;      
    }
    
    
}