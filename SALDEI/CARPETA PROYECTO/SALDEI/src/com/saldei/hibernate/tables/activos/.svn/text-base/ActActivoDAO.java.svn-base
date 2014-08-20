package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.springframework.jdbc.object.SqlQuery;

import com.saldei.hibernate.tables.inventario.InvDetMovActivo;
import com.saldei.hibernate.tables.inventario.InvDetMovActivoDAO;
import com.saldei.hibernate.tables.inventario.InvDetMovActivoId;
import com.saldei.hibernate.tables.inventario.InvDetMovimiento;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.ActivoForm;
import com.saldei.web.form.activos.DetAprSolDescActForm;

/**
 * Data access object (DAO) for domain model class ActActivo.
 * 
 * @see com.saldei.hibernate.tables.activos.ActActivo
 * @author MyEclipse Persistence Tools
 */

public class ActActivoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActActivoDAO.class);
	// property constants
	public static final String COD_RECURSO = "codRecurso";
	public static final String CREADO_POR = "creadoPor";
	public static final String COD_ALT_UCA = "codAltUca";
	public static final String NUM_SERIE = "numSerie";
	public static final String USUARIO_BAJA = "usuarioBaja";
	public static final String ESTADO = "estado";
	public static final String MOTIVO = "motivo";

	public void save(ActActivo transientInstance) {
		log.debug("saving ActActivo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActActivo persistentInstance) {
		log.debug("deleting ActActivo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActActivo findById(java.lang.Integer id) {
		log.debug("getting ActActivo instance with id: " + id);
		try {
			ActActivo instance = (ActActivo) getSession().get(
					"com.saldei.hibernate.tables.activos.ActActivo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActActivo instance) {
		log.debug("finding ActActivo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActActivo").add(
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
		log.debug("finding ActActivo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ActActivo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySolicitudIn(String tipoSolicitud, Integer codSolicitud, String codRecurso) {
		
		try {
			String queryString = "from ActActivo as act	" +
					" where act.codActivo " +
					" in(select detAct.id.codActivo " +
					" from InvDetMovActivo as detAct " +
					" where detAct.id.invDetMovimiento.id.invMovimiento.tipoSol = ? " +
					" and detAct.id.invDetMovimiento.id.invMovimiento.codSol = ? " +
					" and detAct.id.invDetMovimiento.id.invMovimiento.invTipoMov.operacion = 'S' " +
					" and detAct.id.codActivo not in(select det.id.codActivo " +
					" from InvDetMovActivo as det " +
					" where det.id.invDetMovimiento.id.invMovimiento.tipoSol = ? " +
					" and det.id.invDetMovimiento.id.invMovimiento.codSol = ? " +
					" and det.id.invDetMovimiento.id.invMovimiento.invTipoMov.operacion = 'R')) " +
					" and act.codRecurso = ? ";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, codSolicitud);
			queryObject.setParameter(2, tipoSolicitud);
			queryObject.setParameter(3, codSolicitud);
			queryObject.setParameter(4, codRecurso);
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySolicitudInReq(String tipoSolicitud, Integer codSolicitud, String codRecurso) {
		
		try {
			String queryString = "from ActActivo as act	" +
					" where act.codActivo " +
					" in(select detAct.id.codActivo " +
					" from InvDetMovActivo as detAct " +
					" where detAct.id.invDetMovimiento.id.invMovimiento.tipoSol = ? " +
					" and detAct.id.invDetMovimiento.id.invMovimiento.codSol = ? " +
					" and detAct.id.invDetMovimiento.id.invMovimiento.invTipoMov.operacion = 'R' " +
					" ) " +
					" and act.codRecurso = ? ";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, codSolicitud);
			queryObject.setParameter(2, codRecurso);
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	
	
	public List verificarBaja(int activo) throws SQLException, Exception, Error{
		String queryString = "select s.cod_solicitud, s.tipo_solicitud from activos.act_activo a, activos.act_det_solicitud ds, activos.act_solicitud s  "+ 
		"where ds.cod_activo = " + activo +
		"and ds.cod_solicitud = s.cod_solicitud and (s.estado='P' or s.estado='A') " ;
		BeanListHandler handler = new BeanListHandler(DetAprSolDescActForm.class);
		QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
	}
	
	
public List findBySolicitudOut(String tipoSolicitud, Integer codSolicitud, String codRecurso) {
		
		try {
			String queryString = " from ActActivo as act " +
								" where act.codActivo " +
								" in(select detAct.id.codActivo " +
								" from InvMovimiento as mov, InvDetMovActivo as detAct " +
								" where detAct.id.invDetMovimiento.id.invMovimiento.id = mov.id " +
								" and mov.tipoSol =? " +
								" and mov.codSol = ? " +
								" and mov.invTipoMov.operacion = 'R')" +
								" and act.codRecurso = ?" ;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, codSolicitud);
			queryObject.setParameter(2, codRecurso);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

public void  updateMovActivosBySolicitud(String tipoSolicitud, Integer codSolicitud, Integer codUnidad, String codRecurso, InvDetMovimiento invDetMovimiento) throws SQLException {
	List activos;
	List movActivos;
	InvDetMovActivoDAO invDetMovActivoDAO =  new InvDetMovActivoDAO();
	try {
		String queryString = "from  InvDetMovActivo as detAct" +
							" where detAct.id.invDetMovimiento.id.invMovimiento.tipoSol = ?" +
							" and detAct.id.invDetMovimiento.id.invMovimiento.codSol = ?" +
							" and detAct.id.invDetMovimiento.id.invMovimiento.invTipoMov.operacion = 'S'" +
							" and detAct.id.invDetMovimiento.id.invRecurso.codRecurso = ?";

		Query queryObject1 = getSession().createQuery(queryString);
		queryObject1.setParameter(0, tipoSolicitud);
		queryObject1.setParameter(1, codSolicitud);
		queryObject1.setParameter(2, codRecurso);
		
		movActivos = queryObject1.list();
		
		if(!movActivos.isEmpty()){
			Iterator iter = movActivos.iterator();
			for(int j = 0; iter.hasNext(); j++){
				InvDetMovActivoId invDetMovActivoId;
				InvDetMovActivo invDetMovActivoNew;
				InvDetMovActivo invDetMovActivo = (InvDetMovActivo) iter.next();
				invDetMovActivo.getId().setInvDetMovimiento(invDetMovimiento);// getInvDetMovimiento().getId().getInvMovimiento().getId().setAnio(anioMov);
				//invDetMovActivo.getId().getInvDetMovimiento().getId().getInvMovimiento().getId().setCorrelativoMov(corre);
				
				invDetMovActivoId =  new InvDetMovActivoId(invDetMovimiento,invDetMovActivo.getId().getCodActivo());
				invDetMovActivoNew =  new InvDetMovActivo(invDetMovActivoId);								
				invDetMovActivoDAO.save(invDetMovActivoNew);
				
				//invDetMovActivoDAO.save(invDetMovActivo);
				
				ActActivo activo = this.findById(invDetMovActivo.getId().getCodActivo());
				ActUnidad actUnidad = new ActUnidad();
				actUnidad.setCodUnidad(codUnidad);
				activo.setActUnidad(actUnidad);
				this.merge(activo);
				
			}
		}
		
	} catch (RuntimeException re) {
		log.error("find by property name failed", re);
		throw re;
	}
}

public void  updateMovActivosByReq(Integer codUnidad, InvDetMovimiento invDetMovimiento, String series) throws SQLException {
	List activos;
	
	InvDetMovActivoDAO invDetMovActivoDAO =  new InvDetMovActivoDAO();
	
	String valoresSeries[];
	
	try {
			valoresSeries = series.split(",");
			for(int j = 0; j < valoresSeries.length; j++){
				
				if(valoresSeries[j] != null  && !valoresSeries[j].equals("")){
					InvDetMovActivoId invDetMovActivoId;
					InvDetMovActivo invDetMovActivoNew;								
					
					invDetMovActivoId =  new InvDetMovActivoId(invDetMovimiento, Integer.valueOf(valoresSeries[j]));
					invDetMovActivoNew =  new InvDetMovActivo(invDetMovActivoId);								
					invDetMovActivoDAO.save(invDetMovActivoNew);
								
					ActActivo activo = this.findById(Integer.valueOf(valoresSeries[j]));
					ActUnidad actUnidad = new ActUnidad();
					actUnidad.setCodUnidad(codUnidad);
					activo.setActUnidad(actUnidad);
					activo.setEstado("A");
					this.merge(activo);
				}
			}
		
		
	} catch (RuntimeException re) {
		log.error("find by property name failed", re);
		throw re;
	}
}


public void  updateActivosBySolicitud(String tipoSolicitud, Integer codSolicitud, Integer codUnidad, String codRecurso,InvDetMovimiento invDetMovimiento) throws SQLException {
	List activos;
	List movActivos;
	InvDetMovActivoDAO invDetMovActivoDAO =  new InvDetMovActivoDAO();
	try {
			
		String queryString = " from ActActivo as act " +
							" where act.codActivo " +
							" in(select detAct.id.codActivo " +
							" from InvDetMovActivo as detAct " +
							" where detAct.id.invDetMovimiento.id.invMovimiento.tipoSol = ? " +
							" and detAct.id.invDetMovimiento.id.invMovimiento.codSol = ? " +
							" and detAct.id.invDetMovimiento.id.invMovimiento.invTipoMov.operacion = 'R') " +
							" and act.codRecurso = ? " ;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, codSolicitud);
			queryObject.setParameter(2, codRecurso);
			activos = queryObject.list();
			if(!activos.isEmpty()){
				Iterator it =  activos.iterator();
				for (int i = 1; it.hasNext();i++){
					ActActivo activo = (ActActivo) it.next();
					activo.getActUnidad().setCodUnidad(codUnidad);
					this.merge(activo);
				}
			}
				
		
	} catch (RuntimeException re) {
		log.error("find by property name failed", re);
		throw re;
	}
}

	public List findByCodRecurso(Object codRecurso) {
		return findByProperty(COD_RECURSO, codRecurso);
	}
	
	public ActActivo findByCodRecursoNew(String codRecurso) {
		List data;
		try {
			String queryString = "from ActActivo as model where "+
					" model.codRecurso = ? " +
							" order by model.codActivo desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codRecurso);
			data = queryObject.list(); 
			if(!data.isEmpty()){
				return (ActActivo) data.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreadoPor(Object creadoPor) {
		return findByProperty(CREADO_POR, creadoPor);
	}

	public List findByCodAltUca(Object codAltUca) {
		return findByProperty(COD_ALT_UCA, codAltUca);
	}

	public List findByNumSerie(Object numSerie) {
		return findByProperty(NUM_SERIE, numSerie);
	}

	public List findByUsuarioBaja(Object usuarioBaja) {
		return findByProperty(USUARIO_BAJA, usuarioBaja);
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findByMotivo(Object motivo) {
		return findByProperty(MOTIVO, motivo);
	}

	public List findAll() throws SQLException {
		log.debug("finding all ActActivo instances");
		try {
			String queryString = "select a.cod_activo as codActivo, a.cod_unidad as codUnidad, u.descripcion as unidadDesc, "+ 
								 "a.cod_recurso as codRecurso, r.nombre as recursoDesc, a.cod_alt_uca as codAltUca, a.num_serie as numSerie, "+
								 "a.estado as estado, a.motivo as motivo, a.cod_baja as codBaja, case when a.estado='A' then 'No' when a.estado='P' then 'No' else 'Si' end as estadoDescr, "+
								 "case when a.estado='A' then 'A' when a.estado='P' then 'A' else 'B' end as estadoShow, " +
								 "ae.descripcion as estadoFisico, a.estado_activo as codEstadoActivo " +
								 "from activos.act_activo a "+
								 "inner join inventario.inv_recurso r on (a.cod_recurso = r.cod_recurso) "+ 
								 "inner join activos.act_unidad u on (a.cod_unidad = u.cod_unidad) "+
								 "left outer join activos.act_estado_activo ae on (a.estado_activo = ae.cod_estado) "+
								 "left outer join activos.act_motivo_baja m on (a.cod_baja = m.cod_baja) " +
								 "where a.estado <> 'N' ";
			BeanListHandler handler = new BeanListHandler(ActivoForm.class);		
			QueryRunner query = new QueryRunner();
			
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActActivo merge(ActActivo detachedInstance) {
		log.debug("merging ActActivo instance");
		try {
			ActActivo result = (ActActivo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActActivo instance) {
		log.debug("attaching dirty ActActivo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActActivo instance) {
		log.debug("attaching clean ActActivo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByCodAltUcaUpd(String codAltUca,String codActivo) throws SQLException  {
		log.debug("finding all ActActivo instances");
		try {
			String queryString = "select cod_activo from activos.act_activo where cod_alt_uca='" +
			codAltUca + "' and cod_activo <> '" + codActivo + "' ";
			BeanListHandler handler = new BeanListHandler(ActivoForm.class);		
			QueryRunner query = new QueryRunner();
			
			return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}
