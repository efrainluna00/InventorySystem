package com.saldei.hibernate.tables;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class UsuarioTitulo.
 * 
 * @see com.saldei.hibernate.tables.UsuarioTitulo
 * @author MyEclipse Persistence Tools
 */

public class UsuarioTituloDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UsuarioTituloDAO.class);
	// property constants
	public static final String TITULO = "titulo";
	public static final String ABV_TITULO = "abvTitulo";
	public static final String POR_DEFECTO = "porDefecto";

	public void save(UsuarioTitulo transientInstance) {
		log.debug("saving UsuarioTitulo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UsuarioTitulo persistentInstance) {
		log.debug("deleting UsuarioTitulo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UsuarioTitulo findById(java.lang.Integer id) {
		log.debug("getting UsuarioTitulo instance with id: " + id);
		try {
			UsuarioTitulo instance = (UsuarioTitulo) getSession().get(
					"com.saldei.hibernate.tables.UsuarioTitulo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UsuarioTitulo instance) {
		log.debug("finding UsuarioTitulo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.UsuarioTitulo").add(
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
		log.debug("finding UsuarioTitulo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UsuarioTitulo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitulo(Object titulo) {
		return findByProperty(TITULO, titulo);
	}

	public List findByAbvTitulo(Object abvTitulo) {
		return findByProperty(ABV_TITULO, abvTitulo);
	}

	public List findByPorDefecto(Object porDefecto) {
		return findByProperty(POR_DEFECTO, porDefecto);
	}

	public List findAll()  throws SQLException, Exception, Error {
		log.debug("finding all UsuarioTitulo instances");
		
			String queryString = 
				"select usu.primer_nom || ' ' || usu.primer_ape as responsableDescr, uti.id_usuario as idUsuario, " +
				"uti.titulo, uti.abv_titulo as abvTitulo, uti.por_defecto as porDefecto, " +
				"case when uti.por_defecto='S' then 'Si' else 'No' end as porDefectoDescr, "+
				"uti.correlativo from seguridad.usuario as usu, seguridad.usuario_titulo " +
				"as uti where usu.id_usuario = uti.id_usuario ";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
					
			
				return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
	}

	public UsuarioTitulo merge(UsuarioTitulo detachedInstance) {
		log.debug("merging UsuarioTitulo instance");
		try {
			UsuarioTitulo result = (UsuarioTitulo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UsuarioTitulo instance) {
		log.debug("attaching dirty UsuarioTitulo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UsuarioTitulo instance) {
		log.debug("attaching clean UsuarioTitulo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List buscarPorDefecto(String user)  throws SQLException, Exception, Error {
		log.debug("finding all UsuarioTitulo instances");
		
			String queryString = 
				"select id_usuario,correlativo from seguridad.usuario_titulo where id_usuario ='" +
				user + "' and por_defecto='S'";
			MapListHandler handler = new MapListHandler();
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			//BeanListHandler handler = new BeanListHandler(OptionAppForm.class);		
			QueryRunner query = new QueryRunner();
					
			
				return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
	}
}