package com.saldei.web.bean.administracion;

public class MateriaDto {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	private String accion;
	private String uv;
	
	public void setUv(String uv){
		this.uv = uv;
	}
	public String getUv(){
		return uv;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
