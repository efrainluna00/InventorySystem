package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model class ActDetPresupuesto.
 * 
 * @see com.saldei.hibernate.tables.activos.ActDetPresupuesto
 * @author MyEclipse Persistence Tools
 */

public class ActDetPresupuestoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ActDetPresupuestoDAO.class);
	// property constants
	public static final String MONTO = "monto";

	public void save(ActDetPresupuesto transientInstance, String usuarioMov) {
		log.debug("saving ActDetPresupuesto instance");
		try {
			transientInstance.setUsuarioMov(usuarioMov);
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActDetPresupuesto persistentInstance , String usuarioMov) {
		log.debug("deleting ActDetPresupuesto instance");
		ActDetPresupuesto a = new ActDetPresupuesto();
		a = this.findById(persistentInstance.getId());
		this.merge(a, usuarioMov);
		HibernateSessionFactory.getSession().beginTransaction().commit();	
		HibernateSessionFactory.getSession().close();
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActDetPresupuesto findById(
			com.saldei.hibernate.tables.activos.ActDetPresupuestoId id) {
		log.debug("getting ActDetPresupuesto instance with id: " + id);
		try {
			ActDetPresupuesto instance = (ActDetPresupuesto) getSession()
					.get(
							"com.saldei.hibernate.tables.activos.ActDetPresupuesto",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActDetPresupuesto instance) {
		log.debug("finding ActDetPresupuesto instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActDetPresupuesto")
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
		log.debug("finding ActDetPresupuesto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActDetPresupuesto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMonto(Object monto) {
		return findByProperty(MONTO, monto);
	}

	public List findAll(int valor) throws HibernateException, SQLException {
		log.debug("finding all ActDetPresupuesto instances");
			String queryString = "select ppto.cod_presupuesto , c.cod_cuenta as codCuenta, c.descripcion, det.monto, det.saldo_actual as saldoActual, "+
								" to_char (det.monto, '$9,999,999,999,999,999,999.99') as montoMoney,  " +
								" to_char (det.saldo_actual, '$9,999,999,999,999,999,999.99') as saldoActualMoney " +
								"from activos.act_det_presupuesto det,activos.act_cuenta c, activos.act_presupuesto ppto "+
								"where cuenta = cod_cuenta "+
								"and det.cod_presupuesto = ppto.cod_presupuesto " +
								"and ppto.cod_presupuesto = ?" +
								" order by det.fecha_creacion ;";
			

			MapListHandler handler = new MapListHandler();
			QueryRunner query = new QueryRunner();
			Object []param = new Object[1];
			param[0] = valor;
	    	
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, param, handler);
			

	}

	public ActDetPresupuesto merge(ActDetPresupuesto detachedInstance, String usuarioMov) {
		log.debug("merging ActDetPresupuesto instance");
		try {
			detachedInstance.setUsuarioMov(usuarioMov);
			ActDetPresupuesto result = (ActDetPresupuesto) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActDetPresupuesto instance) {
		log.debug("attaching dirty ActDetPresupuesto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActDetPresupuesto instance) {
		log.debug("attaching clean ActDetPresupuesto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
