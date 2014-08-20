package com.saldei.hibernate.tables.activos;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ActHisResponsableUnidad.
 * 
 * @see com.saldei.hibernate.tables.activos.ActHisResponsableUnidad
 * @author MyEclipse Persistence Tools
 */

public class ActHisResponsableUnidadDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ActHisResponsableUnidadDAO.class);
	// property constants
	public static final String COD_RESPONSABLE = "codResponsable";
	public static final String CREADO_POR = "creadoPor";

	public void save(ActHisResponsableUnidad transientInstance) {
		log.debug("saving ActHisResponsableUnidad instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActHisResponsableUnidad persistentInstance) {
		log.debug("deleting ActHisResponsableUnidad instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActHisResponsableUnidad findById(java.lang.Integer id) {
		log.debug("getting ActHisResponsableUnidad instance with id: " + id);
		try {
			ActHisResponsableUnidad instance = (ActHisResponsableUnidad) getSession()
					.get(
							"com.saldei.hibernate.tables.activos.ActHisResponsableUnidad",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActHisResponsableUnidad instance) {
		log.debug("finding ActHisResponsableUnidad instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.saldei.hibernate.tables.activos.ActHisResponsableUnidad")
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
		log.debug("finding ActHisResponsableUnidad instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActHisResponsableUnidad as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCodResponsable(Object codResponsable) {
		return findByProperty(COD_RESPONSABLE, codResponsable);
	}

	public List findByCreadoPor(Object creadoPor) {
		return findByProperty(CREADO_POR, creadoPor);
	}

	public List findAll() {
		log.debug("finding all ActHisResponsableUnidad instances");
		try {
			String queryString = "from ActHisResponsableUnidad";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActHisResponsableUnidad merge(
			ActHisResponsableUnidad detachedInstance) {
		log.debug("merging ActHisResponsableUnidad instance");
		try {
			ActHisResponsableUnidad result = (ActHisResponsableUnidad) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActHisResponsableUnidad instance) {
		log.debug("attaching dirty ActHisResponsableUnidad instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActHisResponsableUnidad instance) {
		log.debug("attaching clean ActHisResponsableUnidad instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}