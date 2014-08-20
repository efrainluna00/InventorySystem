package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.CotizacionForm;

/**
 * Data access object (DAO) for domain model class ActCotizacion.
 * 
 * @see com.saldei.hibernate.tables.activos.ActCotizacion
 * @author MyEclipse Persistence Tools
 */

public class ActCotizacionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActCotizacionDAO.class);
	// property constants
	public static final String URL_COTIZACION = "urlCotizacion";

	public void save(ActCotizacion transientInstance) {
		log.debug("saving ActCotizacion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActCotizacion persistentInstance) {
		log.debug("deleting ActCotizacion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActCotizacion findById(
			com.saldei.hibernate.tables.activos.ActCotizacionId id) {
		log.debug("getting ActCotizacion instance with id: " + id);
		try {
			ActCotizacion instance = (ActCotizacion) getSession().get(
					"com.saldei.hibernate.tables.activos.ActCotizacion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActCotizacion instance) {
		log.debug("finding ActCotizacion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActCotizacion").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ActCotizacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActCotizacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrlCotizacion(Object urlCotizacion) {
		return findByProperty(URL_COTIZACION, urlCotizacion);
	}

	public List findByEstadoSol(String tipoSolicitud, String codSolicitud, String estado ) throws Exception {
		log.debug("finding all ActCotizacion instances");
		try {
			String queryString = "from ActCotizacion as cot " +
								 " where cot.id.actSolicitud.id.tipoSolicitud = ? " +
								 " and cot.id.actSolicitud.id.codSolicitud = ? " +
								 " and cot.estado = ?";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, Integer.valueOf(codSolicitud));
			queryObject.setParameter(2, estado);
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(String tipoSolicitud, String codSolicitud ) throws Exception {
		log.debug("finding all ActCotizacion instances");
		try {
			String queryString = "from ActCotizacion as cot " +
								 " where cot.id.actSolicitud.id.tipoSolicitud = ? " +
								 " and cot.id.actSolicitud.id.codSolicitud = ?";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, Integer.valueOf(codSolicitud));
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActCotizacion findByEstado(String tipoSolicitud, String codSolicitud) {
		log.debug("finding all ActCotizacion instances");
		List data;
		try {
			String queryString = "from ActCotizacion as cot " +
								 " where cot.id.actSolicitud.id.tipoSolicitud = ? " +
								 " and cot.id.actSolicitud.id.codSolicitud = ? " +
								 " and cot.estado = 'S'";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, Integer.valueOf(codSolicitud));
			data = queryObject.list();
			if(data != null  && !data.isEmpty()){
				return (ActCotizacion) data.get(0);
			}else
				return null;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public ActCotizacion merge(ActCotizacion detachedInstance) {
		log.debug("merging ActCotizacion instance");
		try {
			ActCotizacion result = (ActCotizacion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActCotizacion instance) {
		log.debug("attaching dirty ActCotizacion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActCotizacion instance) {
		log.debug("attaching clean ActCotizacion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}