package com.saldei.hibernate.tables.inventario;

import com.saldei.hibernate.tables.activos.BaseHibernateDAO;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.inventario.AprSolicitudAbaForm;
import com.saldei.web.form.inventario.RecursoForm;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class InvRecurso.
 * 
 * @see com.saldei.hibernate.tables.inventario.InvRecurso
 * @author MyEclipse Persistence Tools
 */

public class InvRecursoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvRecursoDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String CREADO_POR = "creadoPor";
	public static final String COD_UNIDAD_MEDIDA = "codUnidadMedida";
	public static final String MINIMO = "minimo";
	public static final String SERIALIZABLE = "serializable";

	public void save(InvRecurso transientInstance) {
		log.debug("saving InvRecurso instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvRecurso persistentInstance) {
		log.debug("deleting InvRecurso instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvRecurso findById(java.lang.String id) {
		log.debug("getting InvRecurso instance with id: " + id);
		try {
			InvRecurso instance = (InvRecurso) getSession().get(
					"com.saldei.hibernate.tables.inventario.InvRecurso", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvRecurso instance) {
		log.debug("finding InvRecurso instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.inventario.InvRecurso").add(
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
		log.debug("finding InvRecurso instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvRecurso as model where model."
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

	public List findByCreadoPor(Object creadoPor) {
		return findByProperty(CREADO_POR, creadoPor);
	}

	public List findByCodUnidadMedida(Object codUnidadMedida) {
		return findByProperty(COD_UNIDAD_MEDIDA, codUnidadMedida);
	}

	public List findByMinimo(Object minimo) {
		return findByProperty(MINIMO, minimo);
	}

	public List findBySerializable(Object serializable) {
		return findByProperty(SERIALIZABLE, serializable);
	}

	public List findAll() throws HibernateException, SQLException {
		log.debug("finding all InvRecurso instances");
        log.debug("finding all InvSubCategoria instances");
        
        String queryString = 
        	"select a.cod_recurso as codRecurso, a.nombre as nombre, a.creado_por as creado_por, " +
        	"to_char(a.fecha_creacion , 'DD/MM/YYYY') as fechaCreacion, a.cod_categoria as codCategoria," +
        	"b.nombre as nombreCategoria, a.cod_sub_categoria as codSubCategoria, " +
        	"c.nombre as nombreSubCategoria, a.cod_unidad_medida as codUnidadMedida, " +
        	"a.serializable as serializable, case when a.serializable='S' then 'Si' " +
        	"else 'No' end as serializableDescr,d.nom_medida as nomMedida, a.marca, a.modelo, " +
        	"d.abv_medida as abvMedida, a.consumible, case when a.consumible='S' then 'Si' else 'No' end as descConsumible "  +
        	"FROM inventario.inv_recurso a, inventario.inv_categoria b," +
        	"inventario.inv_sub_categoria c,registro.medida d  " +
        	"where a.cod_categoria = c.cod_categoria  and a.cod_sub_categoria = c.cod_sub_categoria " +
        	"and b.cod_categoria = c.cod_categoria  " +
        	"and d.id_medida = a.cod_unidad_medida order by a.fecha_creacion desc "; 
        // "from InvSubCategoria";
        //SQLQuery queryObject = getSession().createSQLQuery(queryString);

        BeanListHandler handler = new BeanListHandler(RecursoForm.class); 
		//MapListHandler handler = new MapListHandler();//COMENTAREAR CUANDO SIRVA
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,handler);
        //return queryObject.list();
		/*
		try {
			String queryString = "from InvRecurso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}*/
	}
	
	
	public List findAllByUser(String codUsuario) throws HibernateException, SQLException {
		log.debug("finding all InvRecurso instances");
        log.debug("finding all InvSubCategoria instances");
        
        String queryString = 
        	"select a.cod_recurso as codRecurso, a.nombre as nombre, a.creado_por as creado_por, " +
        	"to_char(a.fecha_creacion , 'DD/MM/YYYY') as fechaCreacion, a.cod_categoria as codCategoria," +
        	"b.nombre as nombreCategoria, a.cod_sub_categoria as codSubCategoria, " +
        	"c.nombre as nombreSubCategoria, a.cod_unidad_medida as codUnidadMedida, " +
        	"a.serializable as serializable, case when a.serializable='S' then 'Si' " +
        	"else 'No' end as serializableDescr,d.nom_medida as nomMedida, a.marca, a.modelo, " +
        	"d.abv_medida as abvMedida, a.consumible, case when a.consumible='S' then 'Si' else 'No' end as descConsumible "  +
        	"FROM inventario.inv_recurso a, inventario.inv_categoria b," +
        	"inventario.inv_sub_categoria c,registro.medida d  " +
        	"where a.cod_categoria = c.cod_categoria  and a.cod_sub_categoria = c.cod_sub_categoria " +
        	"and b.cod_categoria = c.cod_categoria  " +
        	"and d.id_medida = a.cod_unidad_medida " +
        	"and a.cod_recurso in( select e.cod_recurso " +
        	"		   from inventario.inv_existencia e, activos.act_unidad u " +
        	"		   where e.cod_bodega = u.cod_unidad and u.cod_responsable = ?)" +
        	" order by a.cod_recurso "; 
        // "from InvSubCategoria";
        //SQLQuery queryObject = getSession().createSQLQuery(queryString);

        BeanListHandler handler = new BeanListHandler(RecursoForm.class); 
		//MapListHandler handler = new MapListHandler();//COMENTAREAR CUANDO SIRVA
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        
        Object []param = new Object[1];
		param[0] = codUsuario;	
		
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, param, handler);
        //return queryObject.list();
		/*
		try {
			String queryString = "from InvRecurso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}*/
	}
	
	public List findAllRecursos() throws HibernateException, SQLException {
		log.debug("finding all InvRecurso instances");
        log.debug("finding all InvSubCategoria instances");
        
        try {
			String queryString = "from InvRecurso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvRecurso merge(InvRecurso detachedInstance) {
		log.debug("merging InvRecurso instance");
		try {
			InvRecurso result = (InvRecurso) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvRecurso instance) {
		log.debug("attaching dirty InvRecurso instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvRecurso instance) {
		log.debug("attaching clean InvRecurso instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findRecursosxBodega() throws HibernateException, SQLException {
		log.debug("finding all InvRecurso instances");
        log.debug("finding all InvSubCategoria instances");
        
        String queryString = 
        	"select r.cod_recurso as codRecurso, r.nombre as nombreRecurso, " +
        	"c.nombre as categoria, s.nombre as subcat, m.nom_medida as medidaDes, " +
        	"r.minimo, e.existencia, r.minimo as cantidad " +
        	"from inventario.inv_recurso as r, inventario.inv_categoria as c, " +
        	"inventario.inv_sub_categoria as s, registro.medida as m, " +
        	"inventario.inv_existencia as e " +
        	"where r.cod_categoria=c.cod_categoria and " +
        	"r.cod_sub_categoria=s.cod_sub_categoria and " +
        	"r.cod_unidad_medida=m.id_medida and r.cod_recurso=e.cod_recurso";
        	 
        // "from InvSubCategoria";
        //SQLQuery queryObject = getSession().createSQLQuery(queryString);

        MapListHandler handler = new MapListHandler();
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
       
        //return queryObject.list();
		/*
		try {
			String queryString = "from InvRecurso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}*/
	}

	
}