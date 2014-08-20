package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.CotizacionDetForm;

/**
 * Data access object (DAO) for domain model class ActDetCotizacion.
 * 
 * @see com.saldei.hibernate.tables.activos.ActDetCotizacion
 * @author MyEclipse Persistence Tools
 */

public class ActDetCotizacionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActDetCotizacionDAO.class);
	// property constants
	public static final String PRECIO_UNITARIO = "precioUnitario";
	public static final String CANTIDAD = "cantidad";

	public void save(ActDetCotizacion transientInstance) {
		log.debug("saving ActDetCotizacion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActDetCotizacion persistentInstance) {
		log.debug("deleting ActDetCotizacion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActDetCotizacion findById(
			com.saldei.hibernate.tables.activos.ActDetCotizacionId id) {
		log.debug("getting ActDetCotizacion instance with id: " + id);
		try {
			ActDetCotizacion instance = (ActDetCotizacion) getSession().get(
					"com.saldei.hibernate.tables.activos.ActDetCotizacion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActDetCotizacion instance) {
		log.debug("finding ActDetCotizacion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActDetCotizacion")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ActDetCotizacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActDetCotizacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPrecioUnitario(Object precioUnitario) {
		return findByProperty(PRECIO_UNITARIO, precioUnitario);
	}

	public List findByCantidad(Object cantidad) {
		return findByProperty(CANTIDAD, cantidad);
	}

	public List findAll(String codCotizacion, String codSolicitud, String tipoSolicitud) throws Exception {
		log.debug("finding all ActDetCotizacion instances");
		try {
			String queryString = "from ActDetCotizacion as det where det.id.actCotizacion.id.codCotizacion = ? " +
				 	             " and det.id.actCotizacion.id.actSolicitud.id.codSolicitud = ? and det.id.actCotizacion.id.actSolicitud.id.tipoSolicitud = ?";
			/*String queryString = "select det.precio_unitario as precioUnitario, det.cantidad, " +
								 "		 req.cod_recurso as codRecurso, req.nombre as nombreRecurso, " +
								 "		 cot.cod_cotizacion as codCotizacion, cot.url_cotizacion as urlCotizacion, " +
								 "		 sol.tipo_solicitud as tipoSolicitud, sol.cod_solicitud as codSolicitud, " +
								 "		 prov.cod_proveedor as codProveedor, prov.nombre as nombreProveedor " +
								 "  from inventario.inv_recurso req,activos.act_det_cotizacion det,activos.act_cotizacion cot, " +
								 "		 activos.act_solicitud sol, activos.act_proveedor prov " +
								 " where req.cod_recurso = det.cod_recurso and det.tipo_solicitud = cot.tipo_solicitud " +
								 "	 and det.cod_solicitud = cot.cod_solicitud and det.cod_cotizacion = cot.cod_cotizacion " +
								 "	 and cot.tipo_solicitud = sol.tipo_solicitud and cot.cod_solicitud = sol.cod_solicitud " +
								 "	 and cot.cod_proveedor = prov.cod_proveedor " +
								 "	 and cot.cod_cotizacion = ? ";*/
			/*BeanListHandler handler = new BeanListHandler(CotizacionDetForm.class);
			QueryRunner query = new QueryRunner();
			Object [] param = new Object[1];
			param[0] = codCotizacion;
			
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, param, handler);*/
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, Integer.valueOf(codCotizacion));
			queryObject.setParameter(1, Integer.valueOf(codSolicitud));
			queryObject.setParameter(2, tipoSolicitud);

			return queryObject.list();
			
						
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActDetCotizacion merge(ActDetCotizacion detachedInstance) {
		log.debug("merging ActDetCotizacion instance");
		try {
			ActDetCotizacion result = (ActDetCotizacion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActDetCotizacion instance) {
		log.debug("attaching dirty ActDetCotizacion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActDetCotizacion instance) {
		log.debug("attaching clean ActDetCotizacion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}