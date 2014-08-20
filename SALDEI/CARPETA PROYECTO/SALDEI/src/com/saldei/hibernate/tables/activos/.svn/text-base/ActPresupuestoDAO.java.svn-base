package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.Date;
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

import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.PresupuestoForm;
import com.saldei.web.form.activos.SolicitudCompraForm;

/**
 * Data access object (DAO) for domain model class ActPresupuesto.
 * @see com.saldei.hibernate.tables.activos.ActPresupuesto
  * @author MyEclipse Persistence Tools 
 */

public class ActPresupuestoDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(ActPresupuestoDAO.class);
	//property constants
	public static final String ESTADO = "estado";


    
    public void save(ActPresupuesto transientInstance) {
        log.debug("saving ActPresupuesto instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ActPresupuesto persistentInstance) {
        log.debug("deleting ActPresupuesto instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ActPresupuesto findById( java.lang.Integer id) {
        log.debug("getting ActPresupuesto instance with id: " + id);
        try {
            ActPresupuesto instance = (ActPresupuesto) getSession()
                    .get("com.saldei.hibernate.tables.activos.ActPresupuesto", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ActPresupuesto instance) {
        log.debug("finding ActPresupuesto instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.saldei.hibernate.tables.activos.ActPresupuesto")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ActPresupuesto instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ActPresupuesto as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}
	

	public List findAll() throws SQLException, Exception, Error {
		log.debug("finding all ActPresupuesto instances");
	
			String queryString = "select cod_presupuesto as codPresupuesto, fecha_inicial as fechaInicial, " +
			"fecha_final as fechaFinal, fecha_creacion as fechaCreacion, estado, case when estado='A' then " +
			"'Activo' else 'Inactivo' end as estadoDescr from activos.act_presupuesto order by cod_presupuesto desc ";
			
			BeanListHandler handler = new BeanListHandler(PresupuestoForm.class); 
			QueryRunner query = new QueryRunner();
	        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			/*MapListHandler handler = new MapListHandler();
			QueryRunner query = new QueryRunner();
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);*/
	}
	
    public ActPresupuesto merge(ActPresupuesto detachedInstance) {
        log.debug("merging ActPresupuesto instance");
        try {
            ActPresupuesto result = (ActPresupuesto) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ActPresupuesto instance) {
        log.debug("attaching dirty ActPresupuesto instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ActPresupuesto instance) {
        log.debug("attaching clean ActPresupuesto instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    //data = actPresupuestoDAO.traslapada(this.getFecha_ini(),this.getFecha_fin());
	public List traslapada(String fi,String ff, String codPres) throws SQLException, Exception, Error {
		log.debug("finding all ActPresupuesto instances");
	
			String queryString = "select cod_presupuesto from activos.act_presupuesto where (date '" + fi + 
			"',date '" + ff + "') overlaps (fecha_inicial, fecha_final) and cod_presupuesto <> " + codPres; 
			
			
			BeanListHandler handler = new BeanListHandler(PresupuestoForm.class); 
			QueryRunner query = new QueryRunner();
	        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			/*MapListHandler handler = new MapListHandler();
			QueryRunner query = new QueryRunner();
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);*/
	}
}