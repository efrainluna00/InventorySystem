package com.saldei.hibernate.tables.activos;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ActMotivoBaja.
 * 
 * @see com.saldei.hibernate.tables.activos.ActMotivoBaja
 * @author MyEclipse Persistence Tools
 */

public class ActMotivoBajaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActMotivoBajaDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";

	public void save(ActMotivoBaja transientInstance) {
		log.debug("saving ActMotivoBaja instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActMotivoBaja persistentInstance) {
		log.debug("deleting ActMotivoBaja instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActMotivoBaja findById(java.lang.Integer id) {
		log.debug("getting ActMotivoBaja instance with id: " + id);
		try {
			ActMotivoBaja instance = (ActMotivoBaja) getSession().get(
					"com.saldei.hibernate.tables.activos.ActMotivoBaja", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActMotivoBaja instance) {
		log.debug("finding ActMotivoBaja instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActMotivoBaja").add(
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
		log.debug("finding ActMotivoBaja instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActMotivoBaja as model where model."
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
		log.debug("finding all ActMotivoBaja instances");
		try {
			String queryString = "from ActMotivoBaja order by codBaja desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActMotivoBaja merge(ActMotivoBaja detachedInstance) {
		log.debug("merging ActMotivoBaja instance");
		try {
			ActMotivoBaja result = (ActMotivoBaja) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActMotivoBaja instance) {
		log.debug("attaching dirty ActMotivoBaja instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActMotivoBaja instance) {
		log.debug("attaching clean ActMotivoBaja instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}