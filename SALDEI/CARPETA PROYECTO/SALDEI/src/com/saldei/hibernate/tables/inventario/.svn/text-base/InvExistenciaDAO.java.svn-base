package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvExistencia.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvExistencia
 * @author MyEclipse Persistence Tools
 */

public class InvExistenciaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvExistenciaDAO.class);
	// property constants
	public static final String EXISTENCIA = "existencia";

	public void save(InvExistencia transientInstance) {
		log.debug("saving InvExistencia instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvExistencia persistentInstance) {
		log.debug("deleting InvExistencia instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvExistencia findById(
			com.saldei.hibernate.tables.inventario.InvExistenciaId id) {
		log.debug("getting InvExistencia instance with id: " + id);
		try {
			InvExistencia instance = (InvExistencia) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvExistencia", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvExistencia instance) {
		log.debug("finding InvExistencia instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvExistencia")
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
		log.debug("finding InvExistencia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvExistencia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByExistencia(Object existencia) {
		return findByProperty(EXISTENCIA, existencia);
	}

	public List findAll() {
		log.debug("finding all InvExistencia instances");
		try {
			String queryString = "from InvExistencia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvExistencia merge(InvExistencia detachedInstance) {
		log.debug("merging InvExistencia instance");
		try {
			InvExistencia result = (InvExistencia) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvExistencia instance) {
		log.debug("attaching dirty InvExistencia instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvExistencia instance) {
		log.debug("attaching clean InvExistencia instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findBodegaxMinimo(String codRecurso) throws HibernateException, SQLException {
	    
        String queryString = 
        	"select e.cod_bodega as codBodega, b.nombre as bodegaDes, e.cod_recurso as codRecurso, " +
        	"e.minimo from inventario.inv_existencia as e, inventario.inv_bodega as b where " + 
        	"e.cod_bodega=b.cod_bodega and e.cod_recurso='" + codRecurso + "'"+
        	" order by e.fecha_creacion desc";
        	 
        MapListHandler handler = new MapListHandler();
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
    }
	
	public Integer isBodeguero(String user) throws HibernateException, SQLException {
	    List l;
        String queryString = 
        	"select cod_unidad from activos.act_unidad where cod_responsable ='"  + user + "' " +
        	"and cod_tipo_unidad = '4'";
        	 
        MapListHandler handler = new MapListHandler();
        QueryRunner query = new QueryRunner();
        l = (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
        if(l.isEmpty())
        	return 0;
        else
        	return 1;
        }
}