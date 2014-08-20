package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvDetMovActivo.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvDetMovActivo
 * @author MyEclipse Persistence Tools
 */

public class InvDetMovActivoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvDetMovActivoDAO.class);

	// property constants

	public void save(InvDetMovActivo transientInstance) {
		log.debug("saving InvDetMovActivo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvDetMovActivo persistentInstance) {
		log.debug("deleting InvDetMovActivo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvDetMovActivo findById(
			com.saldei.hibernate.tables.inventario.InvDetMovActivoId id) {
		log.debug("getting InvDetMovActivo instance with id: " + id);
		try {
			InvDetMovActivo instance = (InvDetMovActivo) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvDetMovActivo",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvDetMovActivo instance) {
		log.debug("finding InvDetMovActivo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvDetMovActivo")
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
		log.debug("finding InvDetMovActivo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvDetMovActivo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all InvDetMovActivo instances");
		try {
			String queryString = "from InvDetMovActivo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvDetMovActivo merge(InvDetMovActivo detachedInstance) {
		log.debug("merging InvDetMovActivo instance");
		try {
			InvDetMovActivo result = (InvDetMovActivo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvDetMovActivo instance) {
		log.debug("attaching dirty InvDetMovActivo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvDetMovActivo instance) {
		log.debug("attaching clean InvDetMovActivo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}