package com.saldei.util.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.util.jdbc.JdbcHelper;

public class Util {
	
	private JdbcHelper jdbc = new JdbcHelper();
	
	public static boolean esCadenaNumero(String texto){
		for(int i=0; i<texto.length(); i++){
			String text = texto.substring(i, i + 1);
			if(!Util.esNumero(text))
				return false;			
		}
		return true;
	}
	
	public void actualizarVigencias(){
		try{
			String sqlUsuario = "update seguridad.usuario set est_usuario = 'I' where (id_usuario) " +
										  "in (select a.id_usuario from seguridad.usuario_vigencia a, seguridad.usuario b " +
										  "where a.fecha_fin = (current_date - 1) and 	a.id_usuario = b.id_usuario)";
			String sqlCarrera = "update registro.carrera set est_carrera = 'I' where (id_carrera,plan_estudio) " +
											"in (select a.id_carrera, a.plan_estudio from registro.carrera_vigencia a, registro.carrera b " +
											"where a.fecha_fin = (current_date - 1) and a.id_carrera = b.id_carrera and a.plan_estudio = b.plan_estudio)";
			String sqlEstudianteCarrera = "update registro.estudiante_carrera set est_est_car = 'I' where fecha_fin = (current_date - 1)";
			String sqlCargoUsuarioDei = "update seguridad.cargo_usuario_dei set est_cargo_dei = 'I' where fecha_fin = (current_date - 1)";
			String sqlDirectorLaboratorio = "update registro.director_laboratorio set est_dir_lab = 'I' where fecha_fin = (current_date - 1)";
			
			jdbc.saveOrUpdate(sqlUsuario, null);
			jdbc.saveOrUpdate(sqlCarrera, null);
			jdbc.saveOrUpdate(sqlEstudianteCarrera, null);
			jdbc.saveOrUpdate(sqlCargoUsuarioDei, null);
			jdbc.saveOrUpdate(sqlDirectorLaboratorio, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**	 
	 * @param fecha1
	 * @param fecha2
	 * @return boolean
	 * <b> Regresa true si la fecha1 es menor y false si la fecha 2 es menor</b>
	 */
	public boolean compararFechas(String fecha1, String fecha2){
		String[] datos1 = this.separarFecha(fecha1);
		String[] datos2 = this.separarFecha(fecha2);
		Integer mes1 = new Integer(datos1[0]);
		Integer dia1 = new Integer(datos1[1]);
		Integer anio1 = new Integer(datos1[2]);
		Integer mes2 = new Integer(datos2[0]);
		Integer dia2 = new Integer(datos2[1]);
		Integer anio2 = new Integer(datos2[2]);
		if(anio1 < anio2)
			return true;
		if(anio2 < anio1)
			return false;
		if(mes1 < mes2)
			return true;
		if(mes2 < mes1)
			return false;
		if(dia1 < dia2)
			return true;
		if(dia2 < dia1)
			return false;
		return false;
	}
	
	/**	 
	 * @param fecha
	 * @return String[]
	 * <b>Regresa un arreglo con la fecha dividida en mes, dia y anio</b>
	 */
	public String[] separarFecha(String fecha){
		String[] datos = fecha.split("/");
		return datos;
	}
	
	public String[] separarFecha(String fecha,String split){
		String[] datos = fecha.split(split);
		return datos;
	}
	
	/**	 
	 * @param fecha
	 * @return boolean
	 * <b>Regresa true si la fecha tiene el formato mm/dd/yyyy de lo contrario regresa false</b>
	 */
	public boolean esFechaValida(String fecha){
		String[] datos = this.separarFecha(fecha);
		try{
			Integer mes = new Integer(datos[0]);
			Integer dia = new Integer(datos[1]);
			Integer anio = new Integer(datos[2]);
			if(mes < 1 || mes > 12)
				return false;
			if(dia < 1 || dia > 31)
				return false;
			if(anio < 1900 || anio > 2100)
				return false;
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**	 
	 * @param numero
	 * @return boolean
	 * <b>Regresa true si el parametro es numero de lo contrario false</b>
	 */
	public static boolean esNumero(String numero){
		try{
			Integer num = new Integer(numero);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	/**	 
	 * @param fecha
	 * @return Date
	 * <b>Convierte un String a tipo Date con formato MM/dd/yyyy</b>
	 */
	public Date stringToDate(String fecha,boolean flag){
		if(flag == true)
			fecha = this.fechaConFormato(fecha);
		try{
			String format = "MM/dd/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date newDate = sdf.parse(fecha);
			return newDate;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Date stringToDateAnyFormat(String fecha){
		try{
			fecha = fecha.replace("/", "-");
			String format = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date newDate = sdf.parse(fecha);
			return newDate;			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/**	 
	 * @return Date
	 * <b>Regresa la fecha del servidor con formato MM/dd/yyyy</b>
	 */
	public Date getFechaServidor(){
		String query = "select date(now()) as fecha";
		List list = jdbc.getQuery(query, null);
		DynaBean dyna = (DynaBean) list.get(0);
		String fecha = dyna.get("fecha").toString();
		fecha = fecha.replaceAll("-", "/");
		String[] arreglo = this.separarFecha(fecha);
		String fechaNueva = arreglo[1] + "/" + arreglo[2] + "/" + arreglo[0];
		return stringToDate(fechaNueva,false);
	}
	
	public String getAnioActual(){
		Date date = this.getFechaServidor();
		String fecha = this.dateToString(date);
		fecha = fecha.replace("-", "/");
		String[] fechas = this.separarFecha(fecha);
		return fechas[2];
	}
	
	/**	 
	 * @return Date
	 * <b>Regresa la fecha del servidor con formato MM/dd/yyyy</b>
	 */
	public String getAnyoServidor(){
		String query = "select date(now()) as fecha";
		List list = jdbc.getQuery(query, null);
		DynaBean dyna = (DynaBean) list.get(0);
		String fecha = dyna.get("fecha").toString();
		fecha = fecha.replaceAll("-", "/");
		String[] arreglo = this.separarFecha(fecha);
		return  arreglo[0];
	}
	
	public String dateToStringDDMMYYYY(Date date){
		String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String fecha = sdf.format(date); 
		return fecha;
	}

	/**	 
	 * @param date
	 * @return String
	 * <b>Convierte un tipo Date a String</b>
	 */
	public String dateToString(Date date){
		DateFormat df = DateFormat.getDateInstance();
		String fecha = df.format(date);
		return fecha;
	}
	
	public String fechaConFormato(String fecha){
		try{
			fecha = fecha.replaceAll("-", "/");
			String[] datos = this.separarFecha(fecha);
			if(datos[1].equals("ene"))
				datos[1] = "01";
			if(datos[1].equals("feb"))
				datos[1] = "02";
			if(datos[1].equals("mar"))
				datos[1] = "03";
			if(datos[1].equals("abr"))
				datos[1] = "04";
			if(datos[1].equals("may"))
				datos[1] = "05";
			if(datos[1].equals("jun"))
				datos[1] = "06";
			if(datos[1].equals("jul"))
				datos[1] = "07";
			if(datos[1].equals("ago"))
				datos[1] = "08";
			if(datos[1].equals("sep"))
				datos[1] = "09";
			if(datos[1].equals("oct"))
				datos[1] = "10";
			if(datos[1].equals("nov"))
				datos[1] = "11";
			if(datos[1].equals("dic"))
				datos[1] = "12";
			fecha = datos[1] + "/" + datos[0] +  "/" +datos[2];
			return fecha;
		}catch(Exception ex){
			return null;
		}
	}
	
	public boolean esAnioValido(String anio){
		if(Util.esNumero(anio) == false)
			return false;
		if(!anio.substring(0,1).equals("2"))
			return false;			
		if(anio.length() != 4)
			return false;
		return true;
	}
	
	public String getFechaFormato(String fecha){
		String[] datos = this.separarFecha(fecha);
		fecha = datos[2] + "-" + datos[1] + "-" + datos[0];
		return fecha;
	}
	
	public Date fechaFormatoPostgres(String fecha){
		String[] datos = this.separarFecha(fecha);
		fecha = datos[1] + "/" + datos[0] + "/" + datos[2]; 
		return this.stringToDate(fecha, false);
	}
	
	public Date fechaFormatoPostgresSplit(String fecha){
		String[] datos = this.separarFecha(fecha,"-");
		fecha = datos[1] + "/" + datos[0] + "/" + datos[2]; 
		return this.stringToDate(fecha, false);
	}


	public String fechaDDMMYY(String fecha){
		String[] datos = this.separarFecha(fecha,"-");
		fecha = datos[2] + "/" + datos[1] + "/" + datos[0];
		return fecha;
	}
	
	/**
	 * Convierte una lista a un string separado por el String de entrada
	 * @param lista     Lista a Convertir
	 * @param separador separador de la String
	 * @return 
	 */
	public String getListToString(List lista, String separador )
	{
		String strList = ""; 
		try 
		{
			for(int i =0; i<lista.size(); i++)
			{
				strList += (String) lista.get(i).toString() + separador  ;  
			}
			if (!strList.equals(""))
				strList = strList.substring(0, strList.length()-1);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList;
	}
	
	public boolean validarTelefono(String telefono){
		if(telefono.equals(""))
			return false;
		else{
			String[] datos = telefono.split("-");
			if(datos.length != 2)
				return false;
			else{
				String primero = datos[0];
				for(int i=0; i<primero.length(); i++){
					String sub = primero.substring(i,1 + i);
					if(!Util.esNumero(sub))
						return false;
				}
				String segundo = datos[1];
				for(int i=0; i<primero.length(); i++){
					String sub = primero.substring(i,1 + i);
					if(!Util.esNumero(sub))
						return false;
				}
			}
		}
		return true;
	}
	
	public String getFechaFormatoMMDDYYY(String fecha){
		String[] datos = this.separarFecha(fecha);
		fecha = datos[1] + "/" + datos[0] + "/" + datos[2];
		return fecha;
	}
	public String getFechaFormatoYYYYMMDD(String fecha){
		String[] datos = this.separarFecha(fecha);
		fecha = datos[2] + "-" + datos[1] + "-" + datos[0];
		return fecha;
	}

	public String fechaDDMMYY(String fecha,String separador){
		String parametro=fecha;
		if(parametro==null || parametro.equals("null") || parametro.equals(""))
			parametro="";
		else
		{
			String[] datos = this.separarFecha(fecha,separador);
			fecha = datos[2] + "/" + datos[1] + "/" + datos[0];
			parametro=fecha;			
		}
		return parametro;
	}
	
	
}