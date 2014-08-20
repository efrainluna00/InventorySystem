package com.saldei.hibernate.tables;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.AprSolPrestamoForm;
import com.saldei.web.form.inventario.SolicitudAbastecimientoForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Opcion.
 * 
 * @see com.saldei.hibernate.tables.Opcion
 * @author MyEclipse Persistence Tools
 */

public class OpcionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OpcionDAO.class);
	// property constants
	public static final String ID_OPCION_PADRE = "idOpcionPadre";
	public static final String NOM_OPCION = "nomOpcion";
	public static final String DESC_OPCION = "descOpcion";
	public static final String URL_OPCION = "urlOpcion";
	public static final String ORDEN = "orden";
	public static final String METODO = "metodo";
	public static final String IS_SEPARADOR = "isSeparador";
	public static final String EST_OPCION = "estOpcion";

	public void save(Opcion transientInstance) {
		log.debug("saving Opcion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Opcion persistentInstance) {
		log.debug("deleting Opcion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Opcion findById(java.lang.Integer id) {
		log.debug("getting Opcion instance with id: " + id);
		try {
			Opcion instance = (Opcion) getSession().get(
					"com.saldei.hibernate.tables.Opcion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Opcion instance) {
		log.debug("finding Opcion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.Opcion").add(
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
		log.debug("finding Opcion instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Opcion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdOpcionPadre(Object idOpcionPadre) {
		return findByProperty(ID_OPCION_PADRE, idOpcionPadre);
	}

	public List findByNomOpcion(Object nomOpcion) {
		return findByProperty(NOM_OPCION, nomOpcion);
	}

	public List findByDescOpcion(Object descOpcion) {
		return findByProperty(DESC_OPCION, descOpcion);
	}

	public List findByUrlOpcion(Object urlOpcion) {
		return findByProperty(URL_OPCION, urlOpcion);
	}

	public List findByOrden(Object orden) {
		return findByProperty(ORDEN, orden);
	}

	public List findByMetodo(Object metodo) {
		return findByProperty(METODO, metodo);
	}

	public List findByIsSeparador(Object isSeparador) {
		return findByProperty(IS_SEPARADOR, isSeparador);
	}

	public List findByEstOpcion(Object estOpcion) {
		return findByProperty(EST_OPCION, estOpcion);
	}

	public List findAll() {
		log.debug("finding all Opcion instances");
		
		try {
			String queryString = "from Opcion as opc ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opcion merge(Opcion detachedInstance) {
		log.debug("merging Opcion instance");
		try {
			Opcion result = (Opcion) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opcion instance) {
		log.debug("attaching dirty Opcion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opcion instance) {
		log.debug("attaching clean Opcion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void jdbcGuardar(Opcion o, String v) throws SQLException, Exception, Error{
		QueryRunner query = new QueryRunner();
		String queryString = "insert into seguridad.opcion(id_opcion_padre,nom_opcion,desc_opcion," +
				"url_opcion,orden,metodo,est_opcion,is_separador) values('";
				queryString+= o.getEstOpcion().equals("I") ? "-1" : o.getIdOpcionPadre();
				queryString +=
			     "','"  +
				o.getNomOpcion() + "',' " +
				o.getDescOpcion() + "','" +
				o.getUrlOpcion() + "','" +
				o.getOrden() + "','" +
				o.getMetodo() + "','" +
				o.getEstOpcion() + "','" +
				o.getIsSeparador() + "')";
		query.update(HibernateSessionFactory.getSession().connection(), queryString);
		
		queryString = "insert into seguridad.opcion_seguridad values ( (Select " +
				"max(id_opcion) from seguridad.opcion),'" + v + "')";
		query.update(HibernateSessionFactory.getSession().connection(), queryString);
		
	}
	
	public List findAll2() throws SQLException, Exception, Error{
		log.debug("finding all Opcion instances");
		String queryString = 
			"Select o.id_opcion as idOpcion, o.id_opcion_padre as " +
			" idOpcionPadre, o2.nom_opcion as opcionPadre," +
			" o.nom_opcion as nomOpcion, o.desc_opcion as " +
			" descOpcion, o.url_opcion as urlOpcion, o.orden as orden, " +
			" o.metodo as metodo, o.is_separador as isSeparador, " +
			" case when o.is_separador='S' then 'Si' else 'No' end as separador, " +
			" case when o.est_opcion='A' then 'Activo' else 'Inactivo' end as estadoDescr, " +
			" case when os.visible='S' then 'Si' else 'No' end as visibleDescr, "+
			" o.est_opcion as estOpcion, os.visible as visible from seguridad.opcion as o " +
			" left outer join seguridad.opcion_seguridad as os on (o.id_opcion=os.id_opcion) "+
			" left outer join seguridad.opcion as o2 on (o.id_opcion_padre=o2.id_opcion) order by o.id_opcion desc ";
			
			
	   // BeanListHandler handler = new BeanListHandler(AprSolPrestamoForm.class); 
		MapListHandler handler = new MapListHandler();
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
		QueryRunner query = new QueryRunner();
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
		}
	
	
	
}