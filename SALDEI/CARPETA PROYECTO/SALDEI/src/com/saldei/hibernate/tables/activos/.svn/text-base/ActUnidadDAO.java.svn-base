package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.Date;
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
 * Data access object (DAO) for domain model class ActUnidad.
 * 
 * @see com.saldei.hibernate.tables.activos.ActUnidad
 * @author MyEclipse Persistence Tools
 */

public class ActUnidadDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActUnidadDAO.class);
	// property constants
	public static final String DESCRIPCION = "descripcion";
	public static final String COD_RESPONSABLE = "codResponsable";
	public static final String ESTADO = "estado";

	public void save(ActUnidad transientInstance) {
		log.debug("saving ActUnidad instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActUnidad persistentInstance) {
		log.debug("deleting ActUnidad instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActUnidad findById(java.lang.Integer id) {
		log.debug("getting ActUnidad instance with id: " + id);
		try {
			ActUnidad instance = (ActUnidad) getSession().get(
					"com.saldei.hibernate.tables.activos.ActUnidad", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActUnidad instance) {
		log.debug("finding ActUnidad instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActUnidad").add(
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
		log.debug("finding ActUnidad instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ActUnidad as model where model."
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

	public List findByCodResponsable(Object codResponsable) {
		return findByProperty(COD_RESPONSABLE, codResponsable);
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findAll() throws SQLException, Exception, Error  {
		log.debug("finding all ActUnidad instances");
	
			String queryString = "Select u.cod_unidad as codUnidad, u.descripcion, u.cod_tipo_unidad as codTipoUnidad, " +
			"u.cod_responsable as codResponsable,u.fecha_creacion as fechaCreacion, to_char(u.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion, " +
		"case when u.estado='A' then 'Activo' else 'Inactivo' end as estadoDescr, " + 
		"u.estado, tu.descripcion as tipoUnidadDesc, us.primer_nom || ' ' || us.primer_ape as " +
		"responsableDescr from activos.act_unidad as u, activos.act_tipo_unidad as tu, " +
		"seguridad.usuario_dei as ud, seguridad.usuario as us " + 
		"where u.cod_tipo_unidad = tu.cod_tipo_unidad and u.cod_responsable = ud.codigo_empleado " +
		"and ud.codigo_empleado = us.id_usuario ORDER BY codUnidad desc";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
					
			
				return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
			//return queryObject.list();
			}

	public ActUnidad merge(ActUnidad detachedInstance) {
		log.debug("merging ActUnidad instance");
		try {
			ActUnidad result = (ActUnidad) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActUnidad instance) {
		log.debug("attaching dirty ActUnidad instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActUnidad instance) {
		log.debug("attaching clean ActUnidad instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
