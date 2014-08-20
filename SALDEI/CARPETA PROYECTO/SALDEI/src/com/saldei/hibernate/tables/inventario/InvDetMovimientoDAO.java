package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvDetMovimiento.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvDetMovimiento
 * @author MyEclipse Persistence Tools
 */

public class InvDetMovimientoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvDetMovimientoDAO.class);
	// property constants
	public static final String CANTIDAD = "cantidad";

	public void save(InvDetMovimiento transientInstance,String usuarioMov) {
		log.debug("saving InvDetMovimiento instance");
		try {
			transientInstance.setUsuarioMov(usuarioMov);
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvDetMovimiento persistentInstance,String usuarioMov) {
		log.debug("deleting InvDetMovimiento instance");
		try {
			InvDetMovimiento a = new InvDetMovimiento();
			a = this.findById(persistentInstance.getId());
			this.merge(a, usuarioMov);
			HibernateSessionFactory.getSession().beginTransaction().commit();	
			HibernateSessionFactory.getSession().close();
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvDetMovimiento findById(
			com.saldei.hibernate.tables.inventario.InvDetMovimientoId id) {
		log.debug("getting InvDetMovimiento instance with id: " + id);
		try {
			InvDetMovimiento instance = (InvDetMovimiento) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvDetMovimiento",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvDetMovimiento instance) {
		log.debug("finding InvDetMovimiento instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvDetMovimiento")
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
		log.debug("finding InvDetMovimiento instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvDetMovimiento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCantidad(Object cantidad) {
		return findByProperty(CANTIDAD, cantidad);
	}

	public List findAll() {
		log.debug("finding all InvDetMovimiento instances");
		try {
			String queryString = "from InvDetMovimiento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvDetMovimiento merge(InvDetMovimiento detachedInstance,String usuarioMov) {
		log.debug("merging InvDetMovimiento instance");
		try {
			detachedInstance.setUsuarioMov(usuarioMov);
			InvDetMovimiento result = (InvDetMovimiento) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvDetMovimiento instance) {
		log.debug("attaching dirty InvDetMovimiento instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvDetMovimiento instance) {
		log.debug("attaching clean InvDetMovimiento instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}