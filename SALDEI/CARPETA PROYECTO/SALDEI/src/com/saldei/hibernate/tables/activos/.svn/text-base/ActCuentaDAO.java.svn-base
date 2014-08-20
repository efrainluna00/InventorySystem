package com.saldei.hibernate.tables.activos;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ActCuenta.
 * 
 * @see com.saldei.hibernate.tables.activos.ActCuenta
 * @author MyEclipse Persistence Tools
 */

public class ActCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActCuentaDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";

	public void save(ActCuenta transientInstance) {
		log.debug("saving ActCuenta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActCuenta persistentInstance) {
		log.debug("deleting ActCuenta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActCuenta findById(java.lang.String id) {
		log.debug("getting ActCuenta instance with id: " + id);
		try {
			ActCuenta instance = (ActCuenta) getSession().get(
					"com.saldei.hibernate.tables.activos.ActCuenta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActCuenta instance) {
		log.debug("finding ActCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActCuenta").add(
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
		log.debug("finding ActCuenta instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ActCuenta as model where model."
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
		log.debug("finding all ActCuenta instances");
		try {
			String queryString = "from ActCuenta order by fechaCreacion desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActCuenta merge(ActCuenta detachedInstance) {
		log.debug("merging ActCuenta instance");
		try {
			ActCuenta result = (ActCuenta) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActCuenta instance) {
		log.debug("attaching dirty ActCuenta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActCuenta instance) {
		log.debug("attaching clean ActCuenta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}