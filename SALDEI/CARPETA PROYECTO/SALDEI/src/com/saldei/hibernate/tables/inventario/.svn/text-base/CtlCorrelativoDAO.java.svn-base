package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class CtlCorrelativo.
 * 
 * @see com.saldei.hibernate.tables.inventario.CtlCorrelativo
 * @author MyEclipse Persistence Tools
 */

public class CtlCorrelativoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtlCorrelativoDAO.class);
	// property constants
	public static final String ANIO = "anio";
	public static final String CORRELATIVO = "correlativo";

	public void save(CtlCorrelativo transientInstance) {
		log.debug("saving CtlCorrelativo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtlCorrelativo persistentInstance) {
		log.debug("deleting CtlCorrelativo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtlCorrelativo findById(java.lang.String id) {
		log.debug("getting CtlCorrelativo instance with id: " + id);
		try {
			CtlCorrelativo instance = (CtlCorrelativo) getSession()
					.get(
							"com.saldei.hibernate.tables.inventario.CtlCorrelativo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtlCorrelativo instance) {
		log.debug("finding CtlCorrelativo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.CtlCorrelativo")
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
		log.debug("finding CtlCorrelativo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtlCorrelativo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAnio(Object anio) {
		return findByProperty(ANIO, anio);
	}

	public List findByCorrelativo(Object correlativo) {
		return findByProperty(CORRELATIVO, correlativo);
	}

	public List findAll() {
		log.debug("finding all CtlCorrelativo instances");
		try {
			String queryString = "from CtlCorrelativo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtlCorrelativo merge(CtlCorrelativo detachedInstance) {
		log.debug("merging CtlCorrelativo instance");
		try {
			CtlCorrelativo result = (CtlCorrelativo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtlCorrelativo instance) {
		log.debug("attaching dirty CtlCorrelativo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtlCorrelativo instance) {
		log.debug("attaching clean CtlCorrelativo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}