package com.saldei.hibernate.tables.activos;

// Generated by MyEclipse Persistence Tools

/**
 * ActDetSolicitudId generated by MyEclipse Persistence Tools
 */
public class ActDetSolicitudId extends AbstractActDetSolicitudId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ActDetSolicitudId() {
	}

	/** default constructor */
	public ActDetSolicitudId(ActSolicitud actSolicitud) {
		this.setActSolicitud(actSolicitud);
	}

	/**
	 * @param actSolicitud
	 * @param correlativo
	 */
	public ActDetSolicitudId(ActSolicitud actSolicitud, Integer correlativo) {
		super(actSolicitud, correlativo);
		// TODO Auto-generated constructor stub
	}

}
