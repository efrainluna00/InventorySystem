package com.saldei.util.commons;

import com.saldei.util.commons.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.saldei.util.jdbc.JdbcHelper;

public class Auditoria {
	private String idUsuario;
	private String tabla;
	private String accion;
	private String pk;
	private Date fecha;

	private JdbcHelper jdbc = new JdbcHelper();
	private Util util = new Util();
	
	/**
	 * Devuelve un objeto tipo Auditoria con sus atributos seteados
	 * @param idUsuario
	 * @param tabla
	 * @param accion
	 * @param pk
	 * @param fecha2
	 */ 
	public Auditoria(String idUsuario, String tabla, String accion, String pk) {	
		this.idUsuario = idUsuario;
		this.tabla = tabla;
		this.accion = accion;
		this.pk = pk;		
	}
	
	public Auditoria(){}
	
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}
	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}
	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
	public boolean register(String idUsuario, String tabla, String accion, String pk) {		
		try{
			String sql="";
			SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy");
			String fecha=formateador.format(util.getFechaServidor());
			//sql+="insert into seguridad.auditoria values('"+this.idUsuario+"','"+fecha+"','"+this.tabla+"','"+this.accion+"','"+this.pk+"')";
			sql+="insert into seguridad.auditoria(id_usuario,fecha_accion,nom_tabla,accion,llave_primaria) values('"+idUsuario+"','"+fecha+"','"+tabla+"','"+accion+"','"+pk+"')";		
			boolean flag=jdbc.saveOrUpdate(sql);		
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}

