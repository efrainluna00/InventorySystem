package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvCategoria.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvCategoria
 * @author MyEclipse Persistence Tools
 */

public class InvCategoriaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvCategoriaDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";

	public void save(InvCategoria transientInstance) {
		log.debug("saving InvCategoria instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvCategoria persistentInstance) {
		log.debug("deleting InvCategoria instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvCategoria findById(java.lang.String id) {
		log.debug("getting InvCategoria instance with id: " + id);
		try {
			InvCategoria instance = (InvCategoria) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvCategoria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvCategoria instance) {
		log.debug("finding InvCategoria instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvCategoria").add(
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
		log.debug("finding InvCategoria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvCategoria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List findAll() {
		log.debug("finding all InvCategoria instances");
		try {
			String queryString = "from InvCategoria order by fecha_creacion desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvCategoria merge(InvCategoria detachedInstance) {
		log.debug("merging InvCategoria instance");
		try {
			InvCategoria result = (InvCategoria) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvCategoria instance) {
		log.debug("attaching dirty InvCategoria instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvCategoria instance) {
		log.debug("attaching clean InvCategoria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}