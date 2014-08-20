package com.saldei.hibernate.tables.activos;

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

import com.saldei.util.hibernate.dao.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model class ActTipoUnidad.
 * 
 * @see com.saldei.hibernate.tables.activos.ActTipoUnidad
 * @author MyEclipse Persistence Tools
 */

public class ActTipoUnidadDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActTipoUnidadDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";

	public void save(ActTipoUnidad transientInstance) {
		log.debug("saving ActTipoUnidad instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActTipoUnidad persistentInstance) {
		log.debug("deleting ActTipoUnidad instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActTipoUnidad findById(java.lang.Integer id) {
		log.debug("getting ActTipoUnidad instance with id: " + id);
		try {
			ActTipoUnidad instance = (ActTipoUnidad) getSession().get(
					"com.saldei.hibernate.tables.activos.ActTipoUnidad", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActTipoUnidad instance) {
		log.debug("finding ActTipoUnidad instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActTipoUnidad").add(
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
		log.debug("finding ActTipoUnidad instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActTipoUnidad as model where model."
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
		log.debug("finding all ActTipoUnidad instances");
		try {
			String queryString = "from ActTipoUnidad order by cod_tipo_unidad desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActTipoUnidad merge(ActTipoUnidad detachedInstance) {
		log.debug("merging ActTipoUnidad instance");
		try {
			ActTipoUnidad result = (ActTipoUnidad) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActTipoUnidad instance) {
		log.debug("attaching dirty ActTipoUnidad instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActTipoUnidad instance) {
		log.debug("attaching clean ActTipoUnidad instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAll2() throws SQLException, Exception, Error  {
		log.debug("finding all ActUnidad instances");
	
			String queryString = "select cod_tipo_unidad as codTipoUnidad, descripcion, case when " +
			" cod_tipo_unidad=4 then 'N' else 'S' end as registroModificable from activos.act_tipo_unidad order by cod_tipo_unidad desc ";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
			//return queryObject.list();
			}
}