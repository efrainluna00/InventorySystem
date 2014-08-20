package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvBodega.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvBodega
 * @author MyEclipse Persistence Tools
 */

public class InvBodegaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvBodegaDAO.class);
	
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String UBICACION = "ubicacion";
	public static final String ESTADO = "estado";

	public void save(InvBodega transientInstance) {
		log.debug("saving InvBodega instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvBodega persistentInstance) {
		log.debug("deleting InvBodega instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvBodega findById(java.lang.Integer id) {
		log.debug("getting InvBodega instance with id: " + id);
		try {
			InvBodega instance = (InvBodega) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvBodega", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvBodega instance) {
		log.debug("finding InvBodega instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvBodega").add(
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
		log.debug("finding InvBodega instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvBodega as model where model."
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

	public List findByUbicacion(Object ubicacion) {
		return findByProperty(UBICACION, ubicacion);
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findAll() throws SQLException, Exception, Error{
		log.debug("finding all InvBodega instances");
		String queryString = "select "+
		"cod_bodega as codBodega, "+
		"nombre as nombre, "+
		"ubicacion as ubicacion, "+
		"estado as estado, "+
		"case when estado='A' then 'Activa' "+
		"when estado='I' then 'Inactiva' end as estadoNombre "+
		"from inventario.inv_bodega";
		
		MapListHandler handler = new MapListHandler();
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
       
		/*try {
			String queryString = "from InvBodega";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}*/
	}

	public InvBodega merge(InvBodega detachedInstance) {
		log.debug("merging InvBodega instance");
		try {
			InvBodega result = (InvBodega) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvBodega instance) {
		log.debug("attaching dirty InvBodega instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvBodega instance) {
		log.debug("attaching clean InvBodega instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}