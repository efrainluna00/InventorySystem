/**
 * 
 */
package com.saldei.web.form.administracion;

import org.apache.struts.action.ActionForm;

/**
 * @author Carlos
 *
 */
public class TipoMulticodeForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoHid;
	private String codigo;
	private String nombre;
	private String desc;
	private String estado;
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the codigoHid
	 */
	public String getCodigoHid() {
		return codigoHid;
	}
	/**
	 * @param codigoHid the codigoHid to set
	 */
	public void setCodigoHid(String codigoHid) {
		this.codigoHid = codigoHid;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
