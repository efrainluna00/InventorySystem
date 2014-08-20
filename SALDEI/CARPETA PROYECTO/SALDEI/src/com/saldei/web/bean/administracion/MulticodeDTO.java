/**
 * 
 */
package com.saldei.web.bean.administracion;
import com.saldei.hibernate.tables.TipoMulticode;

/**
 * @author Carlos
 *
 */
public class MulticodeDTO {
	private static final long serialVersionUID = 1L;
	private String tipos;
	private String codigoHid;
	private String codigo;
	private String idTipoMulticode;	
	private String nombre;
	private String desc;
	private String estado;
	private String accion;
	private TipoMulticode tipomulticode;
	private String orden;
	
	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}
	/**
	 * @return the tipomulticode
	 */
	public TipoMulticode getTipomulticode() {
		return tipomulticode;
	}
	/**
	 * @param tipomulticode the tipomulticode to set
	 */
	public void setTipomulticode(TipoMulticode tipomulticode) {
		this.tipomulticode = tipomulticode;
	}
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
	 * @return the idTipoMulticode
	 */
	public String getIdTipoMulticode() {
		return idTipoMulticode;
	}
	/**
	 * @param idTipoMulticode the idTipoMulticode to set
	 */
	public void setIdTipoMulticode(String idTipoMulticode) {
		this.idTipoMulticode = idTipoMulticode;
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
	/**
	 * @return the tipos
	 */
	public String getTipos() {
		return tipos;
	}
	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(String tipos) {
		this.tipos = tipos;
	}
}
