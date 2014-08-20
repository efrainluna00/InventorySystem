package com.saldei.web.bean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.hibernate.HibernateException;


import com.saldei.util.hibernate.dao.HibernateSessionFactory;

public  class Util {
	public static String descripcionEstado(String estado){
	if (estado != "" && estado != null){	
		if(estado.equals("G")){
			return "Grabada";	
		}else if(estado.equals("E")){ 
			return "Enviada";
		}else if(estado.equals("A")){
			return "Aprobada";
		}else if(estado.equals("R")){
			return "Rechazada";
		}else if(estado.equals("B")){
			return "Anulada";
		}else if(estado.equals("F")){
			return "Finalizada";
		}else if(estado.equals("H")){
			return "Parcial";
		}else if(estado.equals("I")){
			return "Ingresada";
		}return "";
		
	}
	return "";
   }
   public static String obt_mov_pendientes(String p_usuario) throws HibernateException, SQLException{
	   String queryString = "select activos.obt_aviso_sol_pendientes("+p_usuario+") as valor ";
	   List l;
	   MapListHandler handler = new MapListHandler();
       //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
       QueryRunner query = new QueryRunner();
       
       l = (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
       Map algo = (Map) l.get(0);
       String valor = (String) algo.get("valor");
       return valor;
       
      
		/*try {
			String queryString = "from InvBodega";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}*/
	}
   
   public String anulacionSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String consulte){
	   String cad="";
	   cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido anulada." +  
		"<br>Para ver mas detalles consulte la opcion de " + consulte +
		"<br>en el sistema SALDEI.<br><br>";
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";		
   return cad;
   }
   
   public String aprobacionSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String consulte,String descripcion,String resolucion, String solicitante,String entregar){
	   String cad="";
		cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido aprobada." ;
		if(solicitante.equals("N")) cad += "<br>Por favor entregar los " + entregar +" solicitados." ; 
		cad+="<br>Para ver mas detalles consulte la opcion de " + consulte +
		"<br>en el sistema SALDEI.<br><br>";
		
		if(descripcion !=null && !descripcion.equals(""))
			cad += "El solicitante especifico la siguiente descripcion:<br>" +
			"\"" + descripcion + "\"<br><br>";
		
		if(resolucion !=null && !resolucion.equals(""))
			cad += "El Jefe del Departamento especifico los siguientes comentarios:<br>" +
			"\"" + resolucion + "\"";
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";	
   return cad;
   }
   
   public String rechazoSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String descripcion,String resolucion){
	   String cad="";
		cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido rechazada." ;
		cad+="<br>Para ver mas detalles consulte la opcion de " + nombreSol +
		"<br>en el sistema SALDEI.<br><br>";
		
		if(descripcion !=null && !descripcion.equals(""))
			cad += "El solicitante especifico la siguiente descripcion:<br>" +
			"\"" + descripcion + "\"<br><br>";
		
		if(resolucion !=null && !resolucion.equals(""))
			cad += "El Jefe del Departamento especifico los siguientes comentarios:<br>" +
			"\"" + resolucion + "\"";
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";	
   return cad;
   }
   
   public String aprobacionPrestamosSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String consulte,String descripcion,String resolucion, String jefe){
	   String cad="";
		cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido aprobada." ;
		cad+="<br>Para ver mas detalles consulte la opcion de " + consulte +
		"<br>en el sistema SALDEI.<br><br>";
		
		if(descripcion !=null && !descripcion.equals(""))
			cad += "El solicitante especifico la siguiente descripcion:<br>" +
			"\"" + descripcion + "\"<br><br>";
		
		if(resolucion !=null && !resolucion.equals("")){
			cad += "El ";
			cad += jefe.equals("S")? "Jefe del Departamento" : "Director de Unidad responsable";
			cad += " especifico los siguientes comentarios:<br>" +
			"\"" + resolucion + "\"";
		}
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";	
   return cad;
   }
   
   public String rechazoPrestamosSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String consulte,String descripcion,String resolucion, String jefe){
	   String cad="";
		cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido rechazada." ;
		cad+="<br>Para ver mas detalles consulte la opcion de " + consulte +
		"<br>en el sistema SALDEI.<br><br>";
		
		if(descripcion !=null && !descripcion.equals(""))
			cad += "El solicitante especifico la siguiente descripcion:<br>" +
			"\"" + descripcion + "\"<br><br>";
		
		if(resolucion !=null && !resolucion.equals("")){
			cad += "El ";
			cad += jefe.equals("S")? "Jefe del Departamento" : "Director de Unidad responsable";
			cad += " especifico los siguientes comentarios:<br>" +
			"\"" + resolucion + "\"";
		}
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";	
   return cad;
   }
   
   public String aprobacionTrasladoSolicitudMail(String nombreSol, String codSol, String fechaCreacion,String consulte,String descripcion,String resolucion, String solicitante,String entregar){
	   String cad="";
		cad = "Buen dia.<br><br>" +
		"Se le informa que la " + nombreSol + " con codigo " + codSol +
		"<br>y creada en la fecha " + fechaCreacion + " ha sido aprobada." ;
		if(solicitante.equals("N")) cad += "<br>Por favor trasladar los " + entregar +" solicitados." ; 
		cad+="<br>Para ver mas detalles consulte la opcion de " + consulte +
		"<br>en el sistema SALDEI.<br><br>";
		
		if(descripcion !=null && !descripcion.equals(""))
			cad += "El solicitante especifico la siguiente descripcion:<br>" +
			"\"" + descripcion + "\"<br><br>";
		
		if(resolucion !=null && !resolucion.equals(""))
			cad += "El Jefe del Departamento especifico los siguientes comentarios:<br>" +
			"\"" + resolucion + "\"";
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";	
   return cad;
   }
   
   public String trasladoMail(String nombreActivo, String codActivo, String unidadOrigen, String unidadDestino){
	   String cad="";
	   cad = "Buen dia.<br><br>" +
		"Se le informa que el activo "+ nombreActivo + "<br>con codigo " + codActivo +
		", sera trasladado de la unidad " + unidadOrigen + "<br>a la unidad " + unidadDestino + 
		".<br>El nuevo encargado pasara por su recurso ";  
		cad += "<br>--------------------------------------------------------------------" +
		"-------------------------------------------------------------------<br>" +
		"Este correo fue generado de manera automatica por el sistema SALDEI.<br>" +
		"Favor no responder a este correo.<br>Gracias.";
			
   return cad;
   }
   
   
  
   }
