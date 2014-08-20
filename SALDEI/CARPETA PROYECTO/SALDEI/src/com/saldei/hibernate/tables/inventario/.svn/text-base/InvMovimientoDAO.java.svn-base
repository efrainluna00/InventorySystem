package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.ActDetPresupuesto;
import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvMovimiento.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvMovimiento
 * @author MyEclipse Persistence Tools
 */

public class InvMovimientoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvMovimientoDAO.class);
	// property constants
	public static final String FECHA_MOV = "fechaMov";
	public static final String TIPO_SOL = "tipoSol";
	public static final String COD_SOL = "codSol";

	public void save(InvMovimiento transientInstance, String usuarioMov) {
		log.debug("saving InvMovimiento instance");
		try {
			transientInstance.setUsuarioMov(usuarioMov);
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvMovimiento persistentInstance,String usuarioMov) {
		log.debug("deleting InvMovimiento instance");
		try {
			InvMovimiento a = new InvMovimiento();
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

	public InvMovimiento findById(
			com.saldei.hibernate.tables.inventario.InvMovimientoId id) {
		log.debug("getting InvMovimiento instance with id: " + id);
		try {
			InvMovimiento instance = (InvMovimiento) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvMovimiento", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public InvMovimiento findBySolicitud(String tipoSol, Integer codSol) {
		log.debug("finding InvMovimiento instance with property: ");
		List data;
		try {
			String queryString = "from InvMovimiento as model where model.tipoSol = ? " +
								 " and model.codSol = ? " +
								 " order by model.id.anio, model.id.correlativoMov desc ";					
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSol);
			queryObject.setParameter(1, codSol);
			data = queryObject.list();
			if (data.isEmpty())
				return null;
			else 
				return (InvMovimiento) data.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public InvMovimiento findLast() {
		log.debug("finding InvMovimiento instance with property: ");
		List data;
		try {
			String queryString = "from InvMovimiento as model" +								 
								 " order by model.id.anio, model.id.correlativoMov desc ";					
			Query queryObject = getSession().createQuery(queryString);
			data = queryObject.list();
			if (data.isEmpty())
				return null;
			else 
				return (InvMovimiento) data.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/* metodo que busca todos los movimientos  para una solicitud*/
	public List findAllBySolicitudIn(String tipoSol, Integer codSol) {
		log.debug("finding InvMovimiento instance with property: ");
		List data;
		try {
			String queryString = "from InvMovimiento as model where model.tipoSol = ? " +
								 " and model.codSol = ? " +
								 " and model.invTipoMov.operacion = 'S' ";					
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSol);
			queryObject.setParameter(1, codSol);
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/* metodo que busca todos los movimientos de salida  para una solicitud de Compra*/
	public List findAllBySolicitudOut(String tipoSol, Integer codSol) {
		log.debug("finding InvMovimiento instance with property: ");
		List data;
		try {
			String queryString = "from InvMovimiento as model where model.tipoSol = ? " +
								 " and model.codSol = ? " +
								 " and model.invTipoMov.operacion = 'R'";					
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSol);
			queryObject.setParameter(1, codSol);
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByExample(InvMovimiento instance) {
		log.debug("finding InvMovimiento instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvMovimiento")
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
		log.debug("finding InvMovimiento instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvMovimiento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFechaMov(Object fechaMov) {
		return findByProperty(FECHA_MOV, fechaMov);
	}

	public List findByTipoSol(Object tipoSol) {
		return findByProperty(TIPO_SOL, tipoSol);
	}

	public List findByCodSol(Object codSol) {
		return findByProperty(COD_SOL, codSol);
	}

	public List findAll() {
		log.debug("finding all InvMovimiento instances");
		try {
			String queryString = "from InvMovimiento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvMovimiento merge(InvMovimiento detachedInstance, String usuarioMov) {
		log.debug("merging InvMovimiento instance");
		try {
			detachedInstance.setUsuarioMov(usuarioMov);
			InvMovimiento result = (InvMovimiento) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvMovimiento instance) {
		log.debug("attaching dirty InvMovimiento instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvMovimiento instance) {
		log.debug("attaching clean InvMovimiento instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}