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
 * Data access object (DAO) for domain model class ActProveedor.
 * 
 * @see com.saldei.hibernate.tables.activos.ActProveedor
 * @author MyEclipse Persistence Tools
 */

public class ActProveedorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActProveedorDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DIRECCION = "direccion";
	public static final String NIT = "nit";
	public static final String FAX = "fax";
	public static final String TELEFONO = "telefono";
	public static final String _EMAIL = "EMail";
	public static final String WEB_SITE = "webSite";
	public static final String REG_FISCAL = "regFiscal";
	public static final String GIRO = "giro";
	public static final String ESTADO = "estado";

	public void save(ActProveedor transientInstance) {
		log.debug("saving ActProveedor instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActProveedor persistentInstance) {
		log.debug("deleting ActProveedor instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActProveedor findById(java.lang.String id) {
		log.debug("getting ActProveedor instance with id: " + id);
		try {
			ActProveedor instance = (ActProveedor) getSession().get(
					"com.saldei.hibernate.tables.activos.ActProveedor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActProveedor instance) {
		log.debug("finding ActProveedor instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActProveedor").add(
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
		log.debug("finding ActProveedor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActProveedor as model where model."
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

	public List findByDireccion(Object direccion) {
		return findByProperty(DIRECCION, direccion);
	}

	public List findByNit(Object nit) {
		return findByProperty(NIT, nit);
	}

	public List findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	public List findByTelefono(Object telefono) {
		return findByProperty(TELEFONO, telefono);
	}

	public List findByEMail(Object EMail) {
		return findByProperty(_EMAIL, EMail);
	}

	public List findByWebSite(Object webSite) {
		return findByProperty(WEB_SITE, webSite);
	}

	public List findByRegFiscal(Object regFiscal) {
		return findByProperty(REG_FISCAL, regFiscal);
	}

	public List findByGiro(Object giro) {
		return findByProperty(GIRO, giro);
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findAll() throws SQLException, Exception, Error  {
		log.debug("finding all ActProveedor instances");
	
			String queryString = "Select cod_proveedor as codProveedor, nombre, direccion, nit " +
			", fax, telefono, e_mail as EMail, web_site as webSite, reg_fiscal " +
            "as regFiscal, giro, estado,contacto, case when estado='A' then 'Activo' " +
            "else 'Inactivo' end as estadoDescr,fecha_creacion as fechaCreacion, to_char(fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion " +
            "from activos.act_proveedor order by cast(cod_proveedor as integer) desc";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
				return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
	}

	public ActProveedor merge(ActProveedor detachedInstance) {
		log.debug("merging ActProveedor instance");
		try {
			ActProveedor result = (ActProveedor) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActProveedor instance) {
		log.debug("attaching dirty ActProveedor instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActProveedor instance) {
		log.debug("attaching clean ActProveedor instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}