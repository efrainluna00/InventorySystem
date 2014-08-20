package org.extremecomponents.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import mad.lov4j.lovxml.LovXml;
import mad.lov4j.lovxml.columns.ColumnLov;
import mad.lov4j.read.ReadLovXml;

import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.interceptor.RowInterceptor;
import org.jdom.JDOMException;

 
/**
 * Clase utilizada para modificar los atributos de los tags de las filas RowInterceptor
 * modificar en tiempo de ejecucion los atributos, y agregar nuevos
 */
public class MarkerInterceptor implements RowInterceptor  
{
  
 
    
    
    public void addRowAttributes(TableModel tableModel, Row row) {
    } 

   /**
   * Funcion que genera el script para las tablas de valores, 
   * la base de datos
   * @param model
   * @return 
   */
    @SuppressWarnings("unchecked")
	public void modifyRowAttributes(TableModel model, Row row) {
    	
    	Map fila = (HashMap) model.getCurrentRowBean();
        
        String script="JavaScript:";
        String multiple = (String) model.getContext().getRequestAttribute("multiple");
        multiple = (multiple == null?"null":multiple);
        //Obtengo q atributo se hara la lista de valores
        String xmlArchivo = (String) model.getContext().getRequestAttribute("xmlPath");
        //Declaro un objeto LovXml que sirve para manipular estructura del xml Para los LOV
        LovXml lovXml = null;
        
        //Declaracion de un ReadLovXml para la lectura del archivo XML con los datos para el LOV
        ReadLovXml readLovXml = new ReadLovXml();
        //se utiliza para utilizar un campo especificado como parametro en la pagina(y no del xml)
        String campo=null;
        
        try 
        {
            campo=model.getContext().getParameter("campo");
        	//se inicializa con la carga del archivo XML
            lovXml = readLovXml.readXml(xmlArchivo);        
            List listaColumnas = lovXml.getColumns().getColumns();
            
            //Se itera por cada columna y se agrega con su propiedad, tipo de celda, y titulo de columna
           if(multiple != null && !multiple.equals("true")){        	             
        	   ArrayList listaCampos=new ArrayList();
	            for(Iterator it = listaColumnas.iterator(); it.hasNext();){
	              ColumnLov columna = (ColumnLov) it.next();
	              if(columna.getScript().equals("yes")){
	                if(campo!=null ){//&& campo.equals(columna.getId())){
                  //$('#tipoServicioName',opener.document).val('Cosa = Paulo')
	                  script+= "$('#"+campo+"',opener.document).val('"+fila.get(columna.getId())+"');";
	                  listaCampos.add(campo);
	                  //script+=";if(opener.document.getElementById('"+campo+"').onchange!=null){";
	                  //script+="opener.document.getElementById('"+campo+"').onchange()};";
	                }else{
	                  listaCampos.add(columna.getId());	
	            	  script+= "$('#"+columna.getId()+"',opener.document).val('"+fila.get(columna.getId())+"');";
	            	  /*script+=";if(opener.document.getElementById('"+columna.getId()+"').onchange!=null){";
	  	              script+="opener.document.getElementById('"+columna.getId()+"').onchange()};";
	  	              */
	                }
	              }
	              
	            }
	            //Con esto se ejecutara el evento onchange (si esta definido)en cada campo que se seteara
	            for (Iterator iterator = listaCampos.iterator(); iterator.hasNext();) {
					String name = (String) iterator.next();
					script+=";if(opener.document.getElementById('"+name+"').onchange!=null){";
		            script+="opener.document.getElementById('"+name+"').onchange()};";
				}
	            /*for (Iterator iterator = listaCampos.iterator(); iterator.hasNext();) {
					String name = (String) iterator.next();
					script+=";$('#"+name+"',opener.document).change();";		            
				}*/
	            script+="window.close();";
	            row.setOnclick(script);
           }
            
        }catch (JDOMException e){
          e.printStackTrace();;
        }
        catch(Exception e){
          e.printStackTrace();
        }

    }


}