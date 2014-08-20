package com.saldei.hibernate.tables.activos;

import java.util.Iterator;
import java.util.List;

import com.saldei.hibernate.tables.inventario.InvBodega;
import com.saldei.hibernate.tables.inventario.InvDetMovimiento;
import com.saldei.hibernate.tables.inventario.InvDetMovimientoDAO;
import com.saldei.hibernate.tables.inventario.InvDetMovimientoId;
import com.saldei.hibernate.tables.inventario.InvExistencia;
import com.saldei.hibernate.tables.inventario.InvExistenciaDAO;
import com.saldei.hibernate.tables.inventario.InvExistenciaId;
import com.saldei.hibernate.tables.inventario.InvMovimiento;
import com.saldei.hibernate.tables.inventario.InvMovimientoDAO;
import com.saldei.hibernate.tables.inventario.InvRecurso;
import com.saldei.hibernate.tables.inventario.InvRecursoDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

// Generated by MyEclipse Persistence Tools

/**
 * ActDetSolicitud generated by MyEclipse Persistence Tools
 */
public class ActDetSolicitud extends AbstractActDetSolicitud implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default constructor */
	public ActDetSolicitud() {
	}

	/** minimal constructor */
	public ActDetSolicitud(ActDetSolicitudId id) {
		super(id);
	}

	/** minimal constructor */
	public ActDetSolicitud(ActDetSolicitudId id, ActActivo actActivo) {
		super.setId(id);
		super.setActActivo(actActivo);

	}

	/** full constructor */
	public ActDetSolicitud(ActDetSolicitudId id, ActActivo actActivo,
			String codRecurso, double precioUnitario, Integer cantidad) {
		super(id, actActivo, codRecurso, precioUnitario, cantidad);
	}

	public ActDetSolicitud(ActDetSolicitudId id, String codRecurso,
			Integer cantidad) {
		super.setId(id);
		super.setCodRecurso(codRecurso);
		super.setCantidad(cantidad);
	}

	public String getNombreRecurso() {
		InvRecurso invRecurso;
		InvRecursoDAO invRecursoDAO = new InvRecursoDAO();
		invRecurso = invRecursoDAO.findById(this.getCodRecurso());
		HibernateSessionFactory.getSession().close();
		if (invRecurso != null) {
			return invRecurso.getNombre();
		}
		return "";
	}

	public Double getExistencias() {
		InvExistencia invExistencia;
		InvRecurso invRecurso = new InvRecurso();
		InvBodega invBodega = new InvBodega();
		InvExistenciaDAO invExistenciaDAO = new InvExistenciaDAO();
		invRecurso.setCodRecurso(this.getCodRecurso());
		invBodega.setCodBodega(this.getId().getActSolicitud().getCodBodega());
		InvExistenciaId invExistenciaId = new InvExistenciaId(invBodega,
				invRecurso);
		invExistencia = invExistenciaDAO.findById(invExistenciaId);
		HibernateSessionFactory.getSession().close();
		if (invExistencia != null)
			return invExistencia.getExistencia();
		return 0.0;
	}

	public Integer getCantidadEntregada() {

		List data;
		InvMovimiento invMovimiento;
		InvMovimientoDAO invMovimientoDAO = new InvMovimientoDAO();
		InvDetMovimientoDAO invDetMovimientoDAO = new InvDetMovimientoDAO();
		InvDetMovimiento invDetMovimiento = null;
		InvRecurso invRecurso = new InvRecurso();
		invRecurso.setCodRecurso(this.getCodRecurso());
		Iterator it;
		int cantidadAcumulada = 0;
		data = invMovimientoDAO.findAllBySolicitudOut(this.getId()
				.getActSolicitud().getId().getTipoSolicitud(), this.getId()
				.getActSolicitud().getId().getCodSolicitud());
		if (data != null && !data.isEmpty()) {
			it = data.iterator();
			for (int i = 0; it.hasNext(); i++) {
				invMovimiento = (InvMovimiento) it.next();
				invDetMovimiento = invDetMovimientoDAO
						.findById(new InvDetMovimientoId(invMovimiento,
								invRecurso));
				if (invDetMovimiento != null)
					cantidadAcumulada = cantidadAcumulada
							+ invDetMovimiento.getCantidad();
			}

		}

		HibernateSessionFactory.getSession().close();
		return cantidadAcumulada;
	}

	public String getSeriesIngresadas() {
		String series = "";
		List data;
		Iterator it;
		int flag = 0;
		ActActivoDAO actActivoDAO = new ActActivoDAO();
		try {

			if (this.getId().getActSolicitud().getId().getTipoSolicitud()
					.equals("R")) {
				data = actActivoDAO.findBySolicitudInReq(this.getId()
						.getActSolicitud().getId().getTipoSolicitud(), this
						.getId().getActSolicitud().getId().getCodSolicitud(),
						this.getCodRecurso());
			} else {
				data = actActivoDAO.findBySolicitudIn(this.getId()
						.getActSolicitud().getId().getTipoSolicitud(), this
						.getId().getActSolicitud().getId().getCodSolicitud(),
						this.getCodRecurso());
			}

			if (!data.isEmpty()) {
				it = data.iterator();
				for (int i = 0; it.hasNext(); i++) {
					ActActivo actActivo = (ActActivo) it.next();
					if (flag == 2) {
						series = series + "<br>" + actActivo.getNumSerie();
						flag = 0;
					} else {
						series = series + "," + actActivo.getNumSerie();
						flag = flag + 1;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		series = series.replaceFirst(",", "");
		return series;
	}

	public String getSeriesEntregadas() {
		String series = "";
		List data;
		Iterator it;
		int flag = 0;
		ActActivoDAO actActivoDAO = new ActActivoDAO();
		try {
			if (this.getId().getActSolicitud().getId().getTipoSolicitud()
					.equals("R")) {
				data = actActivoDAO.findBySolicitudInReq(this.getId()
						.getActSolicitud().getId().getTipoSolicitud(), this
						.getId().getActSolicitud().getId().getCodSolicitud(),
						this.getCodRecurso());
			} else {
				data = actActivoDAO.findBySolicitudIn(this.getId()
						.getActSolicitud().getId().getTipoSolicitud(), this
						.getId().getActSolicitud().getId().getCodSolicitud(),
						this.getCodRecurso());
			}

			if (!data.isEmpty()) {
				it = data.iterator();
				for (int i = 0; it.hasNext(); i++) {
					ActActivo actActivo = (ActActivo) it.next();
					if (flag == 2) {
						series = series + "<br>" + actActivo.getNumSerie();
						flag = 0;
					} else {
						series = series + "," + actActivo.getNumSerie();
						flag = flag + 1;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateSessionFactory.getSession().close();
		}
		series = series.replaceFirst(",", "");
		return series;
	}

}