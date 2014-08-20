package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.ActivoForm;
import com.saldei.web.form.activos.DetAprSolPrestamoForm;

/**
 * Data access object (DAO) for domain model class ActHistUnidadActivo.
 * 
 * @see com.saldei.hibernate.tables.activos.ActHistUnidadActivo
 * @author MyEclipse Persistence Tools
 */

public class ActHistUnidadActivoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ActHistUnidadActivoDAO.class);
	// property constants
	public static final String CREADO_POR = "creadoPor";

	public void save(ActHistUnidadActivo transientInstance) {
		log.debug("saving ActHistUnidadActivo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActHistUnidadActivo persistentInstance) {
		log.debug("deleting ActHistUnidadActivo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActHistUnidadActivo findById(
			com.saldei.hibernate.tables.activos.ActHistUnidadActivoId id) {
		log.debug("getting ActHistUnidadActivo instance with id: " + id);
		try {
			ActHistUnidadActivo instance = (ActHistUnidadActivo) getSession()
					.get(
							"com.saldei.hibernate.tables.activos.ActHistUnidadActivo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActHistUnidadActivo instance) {
		log.debug("finding ActHistUnidadActivo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActHistUnidadActivo")
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
		log.debug("finding ActHistUnidadActivo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActHistUnidadActivo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreadoPor(Object creadoPor) {
		return findByProperty(CREADO_POR, creadoPor);
	}

	public List findAll() throws SQLException {
		log.debug("finding all ActHistUnidadActivo instances");
		try {
			String queryString = "select a.cod_activo as codActivo, a.cod_unidad as codUnidad, u.descripcion as unidadDesc, "+ 
			 "a.cod_recurso as codRecurso, r.nombre as recursoDesc, a.cod_alt_uca as codAltUca, a.num_serie as numSerie, "+
			 "us.primer_nom || ' ' || us.primer_ape as responsable, u.cod_responsable, "+
			 "a.estado as estado, a.motivo as motivo, a.cod_baja as codBaja, ea.descripcion as estadoDescr "+
			 "from activos.act_activo a "+
			 "inner join inventario.inv_recurso r on (a.cod_recurso = r.cod_recurso) "+ 
			 "inner join activos.act_unidad u on (a.cod_unidad = u.cod_unidad) "+
			 "inner join seguridad.usuario us on (u.cod_responsable = us.id_usuario) "+
			 "inner join activos.act_estado_activo ea on (a.estado_activo = ea.cod_estado) "+
			 "left outer join activos.act_motivo_baja m on (a.cod_baja = m.cod_baja)"+
			 "where a.estado = 'A' or a.estado = 'T'";
			MapListHandler handler = new MapListHandler();
			QueryRunner query = new QueryRunner();

			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActHistUnidadActivo merge(ActHistUnidadActivo detachedInstance) {
		log.debug("merging ActHistUnidadActivo instance");
		try {
			ActHistUnidadActivo result = (ActHistUnidadActivo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActHistUnidadActivo instance) {
		log.debug("attaching dirty ActHistUnidadActivo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActHistUnidadActivo instance) {
		log.debug("attaching clean ActHistUnidadActivo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void modActivosUni(int unidad , int activo) throws SQLException, Exception, Error{
		String queryString ="update activos.act_activo set cod_unidad = ? where cod_activo = ?";
		QueryRunner query = new QueryRunner();
		Object []param = new Object[2];
		param[0] = unidad;
		param[1] = activo;	
		
		query.update(HibernateSessionFactory.getSession().connection(), queryString,param);
	}
	
	public List verificarAct(int activo) throws SQLException, Exception, Error{
		String queryString = "select ds.cod_solicitud "+ 
		"from activos.act_det_solicitud ds, activos.act_solicitud s "+
		"where ds.cod_activo = " + activo + " and ds.cod_solicitud=s.cod_solicitud and ds.tipo_solicitud=s.tipo_solicitud " +
		"and (s.estado='A' or s.estado='P')";

		BeanListHandler handler = new BeanListHandler(DetAprSolPrestamoForm.class);
		QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
		
	}
	
	}	