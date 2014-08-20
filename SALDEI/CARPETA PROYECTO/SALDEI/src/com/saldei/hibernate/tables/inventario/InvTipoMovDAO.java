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
 * Data access object (DAO) for domain model class InvTipoMov.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvTipoMov
 * @author MyEclipse Persistence Tools
 */

public class InvTipoMovDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvTipoMovDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";
	public static final String OPERACION = "operacion";

	public void save(InvTipoMov transientInstance) {
		log.debug("saving InvTipoMov instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvTipoMov persistentInstance) {
		log.debug("deleting InvTipoMov instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvTipoMov findById(java.lang.Integer id) {
		log.debug("getting InvTipoMov instance with id: " + id);
		try {
			InvTipoMov instance = (InvTipoMov) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvTipoMov", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTipoMov instance) {
		log.debug("finding InvTipoMov instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvTipoMov").add(
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
		log.debug("finding InvTipoMov instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvTipoMov as model where model."
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

	public List findByOperacion(Object operacion) {
		return findByProperty(OPERACION, operacion);
	}

	public List findAll() throws SQLException, Exception, Error  {
		log.debug("finding all InvTipoMov instances");
		
			String queryString = "select cod_tipo_mov as codTipoMov, descripcion, operacion, case when operacion='S' " + 
				"then 'Aumento' else 'Disminución' end as operacionDescr, case when cod_tipo_mov>=0 and cod_tipo_mov<=6 then 'N' else 'S' " +
				"end as registroModificable from inventario.inv_tipo_mov order by fecha_creacion desc";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
					
			
				return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
		
	}

	public InvTipoMov merge(InvTipoMov detachedInstance) {
		log.debug("merging InvTipoMov instance");
		try {
			InvTipoMov result = (InvTipoMov) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvTipoMov instance) {
		log.debug("attaching dirty InvTipoMov instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvTipoMov instance) {
		log.debug("attaching clean InvTipoMov instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}