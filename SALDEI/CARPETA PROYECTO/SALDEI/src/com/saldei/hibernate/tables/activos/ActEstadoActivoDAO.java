package com.saldei.hibernate.tables.activos;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ActEstadoActivo.
 * 
 * @see com.saldei.hibernate.tables.activos.ActEstadoActivo
 * @author MyEclipse Persistence Tools
 */

public class ActEstadoActivoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActEstadoActivoDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";

	public void save(ActEstadoActivo transientInstance) {
		log.debug("saving ActEstadoActivo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActEstadoActivo persistentInstance) {
		log.debug("deleting ActEstadoActivo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActEstadoActivo findById(java.lang.Integer id) {
		log.debug("getting ActEstadoActivo instance with id: " + id);
		try {
			ActEstadoActivo instance = (ActEstadoActivo) getSession().get(
					"com.saldei.hibernate.tables.activos.ActEstadoActivo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActEstadoActivo instance) {
		log.debug("finding ActEstadoActivo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActEstadoActivo").add(
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
		log.debug("finding ActEstadoActivo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActEstadoActivo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all ActEstadoActivo instances");
		try {
			String queryString = "from ActEstadoActivo order by codEstado desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActEstadoActivo merge(ActEstadoActivo detachedInstance) {
		log.debug("merging ActEstadoActivo instance");
		try {
			ActEstadoActivo result = (ActEstadoActivo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActEstadoActivo instance) {
		log.debug("attaching dirty ActEstadoActivo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActEstadoActivo instance) {
		log.debug("attaching clean ActEstadoActivo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}