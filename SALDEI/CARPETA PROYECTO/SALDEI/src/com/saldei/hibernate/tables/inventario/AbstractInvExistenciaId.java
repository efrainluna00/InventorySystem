package com.saldei.hibernate.tables.inventario;

/**
 * AbstractInvExistenciaId generated by MyEclipse Persistence Tools
 */

public abstract class AbstractInvExistenciaId implements java.io.Serializable {

	// Fields

	private InvBodega invBodega;
	private InvRecurso invRecurso;

	// Constructors

	/** default constructor */
	public AbstractInvExistenciaId() {
	}

	/** full constructor */
	public AbstractInvExistenciaId(InvBodega invBodega, InvRecurso invRecurso) {
		this.invBodega = invBodega;
		this.invRecurso = invRecurso;
	}

	// Property accessors

	public InvBodega getInvBodega() {
		return this.invBodega;
	}

	public void setInvBodega(InvBodega invBodega) {
		this.invBodega = invBodega;
	}

	public InvRecurso getInvRecurso() {
		return this.invRecurso;
	}

	public void setInvRecurso(InvRecurso invRecurso) {
		this.invRecurso = invRecurso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvExistenciaId))
			return false;
		AbstractInvExistenciaId castOther = (AbstractInvExistenciaId) other;

		return ((this.getInvBodega() == castOther.getInvBodega()) || (this
				.getInvBodega() != null
				&& castOther.getInvBodega() != null && this.getInvBodega()
				.equals(castOther.getInvBodega())))
				&& ((this.getInvRecurso() == castOther.getInvRecurso()) || (this
						.getInvRecurso() != null
						&& castOther.getInvRecurso() != null && this
						.getInvRecurso().equals(castOther.getInvRecurso())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getInvBodega() == null ? 0 : this.getInvBodega().hashCode());
		result = 37
				* result
				+ (getInvRecurso() == null ? 0 : this.getInvRecurso()
						.hashCode());
		return result;
	}

}