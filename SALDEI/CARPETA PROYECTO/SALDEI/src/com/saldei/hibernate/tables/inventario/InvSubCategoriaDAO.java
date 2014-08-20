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
 * Data access object (DAO) for domain model class InvSubCategoria.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvSubCategoria
 * @author MyEclipse Persistence Tools
 */

public class InvSubCategoriaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvSubCategoriaDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";

	public void save(InvSubCategoria transientInstance) {
		log.debug("saving InvSubCategoria instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvSubCategoria persistentInstance) {
		log.debug("deleting InvSubCategoria instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvSubCategoria findById(
			com.saldei.hibernate.tables.inventario.InvSubCategoriaId id) {
		log.debug("getting InvSubCategoria instance with id: " + id);
		try {
			InvSubCategoria instance = (InvSubCategoria) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvSubCategoria",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvSubCategoria instance) {
		log.debug("finding InvSubCategoria instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvSubCategoria")
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
		log.debug("finding InvSubCategoria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvSubCategoria as model where model."
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

	public List findAll() throws SQLException, Exception, Error {
        log.debug("finding all InvSubCategoria instances");
       
            String queryString = "select "+
        "c.cod_categoria as codCategoria, "+
        "sc.cod_sub_categoria as codSubCategoria, "+
        "sc.nombre as nombreSubCategoria, "+       
        "c.nombre as nombreCategoria "  +
        "from "+
        "    inventario.inv_sub_categoria sc,inventario.inv_categoria c "+
        "where c.cod_categoria = sc.cod_categoria order by sc.fecha_creacion,sc.cod_categoria, sc.cod_sub_categoria ";// "from InvSubCategoria";
            //SQLQuery queryObject = getSession().createSQLQuery(queryString);

            MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
                   
           
                return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
            //return queryObject.list();
            }


	public InvSubCategoria merge(InvSubCategoria detachedInstance) {
		log.debug("merging InvSubCategoria instance");
		try {
			InvSubCategoria result = (InvSubCategoria) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvSubCategoria instance) {
		log.debug("attaching dirty InvSubCategoria instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvSubCategoria instance) {
		log.debug("attaching clean InvSubCategoria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}