package com.saldei.hibernate.tables;

/**
 * AbstractUsuarioTitulo generated by MyEclipse Persistence Tools
 */

public abstract class AbstractUsuarioTitulo implements java.io.Serializable {

	// Fields

	private Integer correlativo;
	private UsuarioDei usuarioDei;
	private String titulo;
	private String abvTitulo;
	private String porDefecto;

	// Constructors

	/** default constructor */
	public AbstractUsuarioTitulo() {
	}

	/** minimal constructor */
	public AbstractUsuarioTitulo(UsuarioDei usuarioDei) {
		this.usuarioDei = usuarioDei;
	}

	/** full constructor */
	public AbstractUsuarioTitulo(UsuarioDei usuarioDei, String titulo,
			String abvTitulo, String porDefecto) {
		this.usuarioDei = usuarioDei;
		this.titulo = titulo;
		this.abvTitulo = abvTitulo;
		this.porDefecto = porDefecto;
	}

	// Property accessors

	public Integer getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public UsuarioDei getUsuarioDei() {
		return this.usuarioDei;
	}

	public void setUsuarioDei(UsuarioDei usuarioDei) {
		this.usuarioDei = usuarioDei;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAbvTitulo() {
		return this.abvTitulo;
	}

	public void setAbvTitulo(String abvTitulo) {
		this.abvTitulo = abvTitulo;
	}

	public String getPorDefecto() {
		return this.porDefecto;
	}

	public void setPorDefecto(String porDefecto) {
		this.porDefecto = porDefecto;
	}

}