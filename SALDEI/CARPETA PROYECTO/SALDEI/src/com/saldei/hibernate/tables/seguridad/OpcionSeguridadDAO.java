package com.saldei.hibernate.tables.seguridad;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class OpcionSeguridad.
 * 
 * @see com.saldei.hibernate.tables.seguridad.OpcionSeguridad
 * @author MyEclipse Persistence Tools
 */

public class OpcionSeguridadDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OpcionSeguridadDAO.class);
	// property constants
	public static final String VISIBLE = "visible";

	public void save(OpcionSeguridad transientInstance) {
		log.debug("saving OpcionSeguridad instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OpcionSeguridad persistentInstance) {
		log.debug("deleting OpcionSeguridad instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OpcionSeguridad findById(java.lang.Integer id) {
		log.debug("getting OpcionSeguridad instance with id: " + id);
		try {
			OpcionSeguridad instance = (OpcionSeguridad) getSession()
					.get(
							"com.saldei.hibernate.tables.seguridad.OpcionSeguridad",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OpcionSeguridad instance) {
		log.debug("finding OpcionSeguridad instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.seguridad.OpcionSeguridad")
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
		log.debug("finding OpcionSeguridad instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OpcionSeguridad as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByVisible(Object visible) {
		return findByProperty(VISIBLE, visible);
	}

	public List findAll() {
		log.debug("finding all OpcionSeguridad instances");
		try {
			String queryString = "from OpcionSeguridad";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OpcionSeguridad merge(OpcionSeguridad detachedInstance) {
		log.debug("merging OpcionSeguridad instance");
		try {
			OpcionSeguridad result = (OpcionSeguridad) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OpcionSeguridad instance) {
		log.debug("attaching dirty OpcionSeguridad instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OpcionSeguridad instance) {
		log.debug("attaching clean OpcionSeguridad instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}