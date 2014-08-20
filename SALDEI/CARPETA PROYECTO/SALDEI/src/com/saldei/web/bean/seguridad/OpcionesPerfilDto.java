package com.saldei.web.bean.seguridad;

public class OpcionesPerfilDto {
	private static final long serialVersionUID = 1L;
	private String idOpcion;
	private String nombreOpcion;
	private String accion;
	private String descripcion;
	private String idPerfil;
	
	
	public String getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
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
	 * @return the idOpcion
	 */
	public String getIdOpcion() {
		return idOpcion;
	}
	/**
	 * @param idOpcion the idOpcion to set
	 */
	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}
	/**
	 * @return the nomOpcion
	 */
	public String getNombreOpcion() {
		return nombreOpcion;
	}
	/**
	 * @param nomOpcion the nomOpcion to set
	 */
	public void setNombreOpcion(String nomOpcion) {
		this.nombreOpcion = nomOpcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
