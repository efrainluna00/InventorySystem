package com.saldei.hibernate.tables.activos;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.hql.ast.tree.UpdateStatement;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.AprDetReqRecursoForm;
import com.saldei.web.form.activos.DetAprSolDescActForm;
import com.saldei.web.form.activos.DetAprSolPrestamoForm;
import com.saldei.web.form.activos.DetSolSuministroForm;
import com.saldei.web.form.activos.DetalleSolDescActForm;
import com.saldei.web.form.activos.DetReqRecursoForm;
import com.saldei.web.form.activos.SolDescActForm;
import com.saldei.web.form.inventario.DetAprSolAbaForm;
import com.saldei.web.form.inventario.DetalleSolAbaForm;
import com.saldei.web.form.inventario.SolicitudAbastecimientoForm;

import com.saldei.util.hibernate.dao.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model class ActDetSolicitud.
 * 
 * @see com.saldei.hibernate.tables.activos.ActDetSolicitud
 * @author MyEclipse Persistence Tools
 */

public class ActDetSolicitudDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActDetSolicitudDAO.class);
	// property constants
	public static final String COD_RECURSO = "codRecurso";
	public static final String PRECIO_UNITARIO = "precioUnitario";
	public static final String CANTIDAD = "cantidad";

	public void save(ActDetSolicitud transientInstance) {
		log.debug("saving ActDetSolicitud instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActDetSolicitud persistentInstance) {
		log.debug("deleting ActDetSolicitud instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActDetSolicitud findById(
			com.saldei.hibernate.tables.activos.ActDetSolicitudId id) {
		log.debug("getting ActDetSolicitud instance with id: " + id);
		try {
			ActDetSolicitud instance = (ActDetSolicitud) getSession().get(
					"com.saldei.hibernate.tables.activos.ActDetSolicitud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	

	public List findByExample(ActDetSolicitud instance) {
		log.debug("finding ActDetSolicitud instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActDetSolicitud").add(
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
		log.debug("finding ActDetSolicitud instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActDetSolicitud as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySolicitud(String tipoSolicitud, Integer codSolicitud) {
		try {
			String queryString = "from ActDetSolicitud as model " +
					             " where model.id.actSolicitud.id.tipoSolicitud = ? " +
					             " and model.id.actSolicitud.id.codSolicitud = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoSolicitud);
			queryObject.setParameter(1, codSolicitud);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findDetSolAbastecimiento(Integer codSolicitud, Integer codBodega) {		
		try {
			String queryString = "from ActDetSolicitud as model " +
								 " where model.id.actSolicitud.id.tipoSolicitud = 'A' " +
								 " and model.id.actSolicitud.id.codSolicitud = ? " +
								 " and model.id.actSolicitud.codBodega = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codSolicitud);
			queryObject.setParameter(1, codBodega);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List findByCodRecurso(Object codRecurso) {
		return findByProperty(COD_RECURSO, codRecurso);
	}

	public List findByPrecioUnitario(Object precioUnitario) {
		return findByProperty(PRECIO_UNITARIO, precioUnitario);
	}

	public List findByCantidad(Object cantidad) {
		return findByProperty(CANTIDAD, cantidad);
	}

	public List findAll(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select "+
		"act_activo.\"cod_activo\" as codActivo, " +
	     "act_activo.\"cod_recurso\" AS act_activo_cod_recurso, "+
	     "act_unidad.\"cod_responsable\" AS codPropietario, "+
	     "usuario.\"primer_nom\" || ' ' || usuario.\"primer_ape\" as propietario, "+
	     "inv_recurso.\"nombre\" AS nombreRecurso,"+
	     "act_unidad.\"descripcion\" AS ubicacion,"+
	     "act_activo.\"num_serie\" AS serie,"+
	     "inv_categoria.\"nombre\" AS categoria,"+
	     "act_activo.\"estado\" ,act_det_solicitud.\"correlativo\" as correlativo, " +
	     "inv_sub_categoria.\"nombre\" AS subcat "+
        "from "+
        " \"activos\".\"act_activo\" act_activo INNER JOIN \"activos\".\"act_det_solicitud\" act_det_solicitud ON act_activo.\"cod_activo\" = act_det_solicitud.\"cod_activo\" "+
        "INNER JOIN \"activos\".\"act_unidad\" act_unidad ON act_activo.\"cod_unidad\" = act_unidad.\"cod_unidad\" " +
        "INNER JOIN \"inventario\".\"inv_recurso\" inv_recurso ON act_activo.\"cod_recurso\" = inv_recurso.\"cod_recurso\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei_A ON act_unidad.\"cod_responsable\" = usuario_dei_A.\"codigo_empleado\" "+
        "INNER JOIN \"activos\".\"act_solicitud\" act_solicitud ON act_solicitud.\"tipo_solicitud\" = act_det_solicitud.\"tipo_solicitud\" and act_det_solicitud.\"tipo_solicitud\"='P' " +
        "AND act_solicitud.\"cod_solicitud\" = act_det_solicitud.\"cod_solicitud\" " +
        "INNER JOIN \"inventario\".\"inv_sub_categoria\" inv_sub_categoria ON inv_recurso.\"cod_categoria\" = inv_sub_categoria.\"cod_categoria\" "+
	     "AND inv_sub_categoria.\"cod_sub_categoria\" = inv_recurso.\"cod_sub_categoria\" "+
	     "INNER JOIN \"inventario\".\"inv_categoria\" inv_categoria ON inv_sub_categoria.\"cod_categoria\" = inv_categoria.\"cod_categoria\" "+
        "INNER JOIN \"seguridad\".\"usuario\" usuario ON usuario_dei_A.\"codigo_empleado\" = usuario.\"id_usuario\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei ON usuario.\"id_usuario\" = usuario_dei.\"codigo_empleado\" " +
		"where " +
	    "act_det_solicitud.\"cod_solicitud\" = ?; ";

		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}
	public List findAllRequisicion(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select a.cod_recurso as codRecurso,b.nombre as nombreRecurso,a.cantidad as cantidad ," +
        " a.correlativo as correlativo "+			
		" from activos.act_solicitud c " +
			" inner join activos.act_det_solicitud a " +
			" on(c.tipo_solicitud = a.tipo_solicitud and c.cod_solicitud = a.cod_solicitud) " +
			" inner join inventario.inv_recurso b " +
			" on (a.cod_recurso = b.cod_recurso) " +
			" where c.tipo_solicitud = 'R' " +
			" and c.cod_solicitud = ? "+
			" order by a.cod_recurso ";
		BeanListHandler handler = new BeanListHandler(DetReqRecursoForm.class);
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}	
	
	
	public List findAllDetSolSuministro(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select a.cod_recurso as codRecurso,b.nombre as nombreRecurso,a.cantidad as cantidad ," +
        " a.correlativo as correlativo, m.nom_medida as medida "+			
		" from activos.act_solicitud c " +
			" inner join activos.act_det_solicitud a " +
			" on(c.tipo_solicitud = a.tipo_solicitud and c.cod_solicitud = a.cod_solicitud) " +
			" inner join inventario.inv_recurso b " +
			" on (a.cod_recurso = b.cod_recurso) " +
			" inner join registro.medida m"+
			" on(b.cod_unidad_medida = m.id_medida)" +
			" where c.tipo_solicitud = 'S' " +
			" and c.cod_solicitud = ? "+
			" order by a.cod_recurso ";
		BeanListHandler handler = new BeanListHandler(DetSolSuministroForm.class);
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}	
	
	public List findAllActivos(String codPropietario, int codSol, int unidad )throws SQLException, Exception, Error {
		String queryString = "select "+
		"act_activo.\"cod_activo\" as codActivo, " +
	     "act_activo.\"cod_recurso\" AS act_activo_cod_recurso, "+
	     "act_unidad.\"cod_responsable\" AS codPropietario, "+
	     "usuario.\"primer_nom\" || ' ' || usuario.\"primer_ape\" as propietario, "+
	     "inv_recurso.\"nombre\" AS nombreRecurso,"+
	     "act_unidad.\"descripcion\" AS ubicacion,"+
	     "act_activo.\"num_serie\" AS serie,"+
	     "inv_categoria.\"nombre\" AS categoria,"+
	     "act_activo.\"estado\" , " +
	     "inv_sub_categoria.\"nombre\" AS subcat "+
	"from "+
	     "\"activos\".\"act_unidad\" act_unidad INNER JOIN \"activos\".\"act_activo\" act_activo ON act_unidad.\"cod_unidad\" = act_activo.\"cod_unidad\" "+
	     "INNER JOIN \"inventario\".\"inv_recurso\" inv_recurso ON act_activo.\"cod_recurso\" = inv_recurso.\"cod_recurso\" "+
	     "INNER JOIN \"inventario\".\"inv_sub_categoria\" inv_sub_categoria ON inv_recurso.\"cod_categoria\" = inv_sub_categoria.\"cod_categoria\" "+
	     "AND inv_sub_categoria.\"cod_sub_categoria\" = inv_recurso.\"cod_sub_categoria\" "+
	     "INNER JOIN \"inventario\".\"inv_categoria\" inv_categoria ON inv_sub_categoria.\"cod_categoria\" = inv_categoria.\"cod_categoria\" "+
	     "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei ON act_unidad.\"cod_responsable\" = usuario_dei.\"codigo_empleado\" "+
	     "INNER JOIN \"seguridad\".\"usuario\" usuario ON usuario_dei.\"codigo_empleado\" = usuario.\"id_usuario\" "+
	     "where act_unidad.cod_responsable= ? and act_activo.\"estado\"='A' and act_activo.\"cod_unidad\"=? and act_activo.\"cod_activo\" not in (select cod_activo from activos.act_det_solicitud where  cod_solicitud = ? and tipo_solicitud='P')";
	    
		
		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[3];
		param[0] = codPropietario;
		param[1] = unidad;	
		param[2] = codSol;
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
	}
	public List findAllAprDetReqRecurso(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select a.cod_recurso as codRecurso,b.nombre as nombreRecurso,a.cantidad as cantidad ," +
        " a.correlativo as correlativo "+			
		" from activos.act_solicitud c " +
			" inner join activos.act_det_solicitud a " +
			" on(c.tipo_solicitud = a.tipo_solicitud and c.cod_solicitud = a.cod_solicitud) " +
			" inner join inventario.inv_recurso b " +
			" on (a.cod_recurso = b.cod_recurso) " +
			" where c.tipo_solicitud = 'R' " +
			" and c.cod_solicitud = ? "+
			" order by a.cod_recurso ";
		BeanListHandler handler = new BeanListHandler(AprDetReqRecursoForm.class);
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}	
	
	public List findAllAprDetSum(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select a.cod_recurso as codRecurso,b.nombre as nombreRecurso,a.cantidad as cantidad ," +
        " a.correlativo as correlativo "+			
		" from activos.act_solicitud c " +
			" inner join activos.act_det_solicitud a " +
			" on(c.tipo_solicitud = a.tipo_solicitud and c.cod_solicitud = a.cod_solicitud) " +
			" inner join inventario.inv_recurso b " +
			" on (a.cod_recurso = b.cod_recurso) " +
			" where c.tipo_solicitud = 'S' " +
			" and c.cod_solicitud = ? "+
			" order by a.cod_recurso ";
		BeanListHandler handler = new BeanListHandler(AprDetReqRecursoForm.class);
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
	}
	
	public List revisarAct(Date ini, Date fin, int sol,int esJefe) throws SQLException, Exception, Error{
		String queryString;
		if(esJefe==1){
			queryString = "select det.cod_activo  "+ 
			"from activos.act_det_solicitud det "+
			"inner join  activos.act_solicitud sol "+
			"on (det.cod_solicitud=sol.cod_solicitud and det.tipo_solicitud=sol.tipo_solicitud) "+ 
			"inner join activos.act_activo a " +
			"on (a.cod_activo=det.cod_activo) "+
			"where sol.cod_solicitud = " + sol +
			" and sol.tipo_solicitud = 'P' "+
			"and det.cod_activo in( "+
				"select ds.cod_activo "+ 
				"from activos.act_det_solicitud ds "+ 
				"inner join  activos.act_solicitud s "+ 
				"on (ds.cod_solicitud=s.cod_solicitud and ds.tipo_solicitud=s.tipo_solicitud) "+ 
				"where s.cod_solicitud <> "+ sol + 
				"and (s.estado = 'A' or s.estado = 'D') "+
			") "+
			"and (date '"+ini+"', date '"+fin+"') overlaps (sol.fecha_ini_prestamo,sol.fecha_fin_prestamo)";
 
		}
		else{
			queryString = "select det.cod_activo  "+ 
			"from activos.act_det_solicitud det "+
			"inner join  activos.act_solicitud sol "+
			"on (det.cod_solicitud=sol.cod_solicitud and det.tipo_solicitud=sol.tipo_solicitud) "+ 
			"inner join activos.act_activo a " +
			"on (a.cod_activo=det.cod_activo) "+
			"where sol.cod_solicitud = " + sol +
			" and sol.tipo_solicitud = 'P' "+
			"and det.cod_activo in( "+
				"select ds.cod_activo "+ 
				"from activos.act_det_solicitud ds "+ 
				"inner join  activos.act_solicitud s "+ 
				"on (ds.cod_solicitud=s.cod_solicitud and ds.tipo_solicitud=s.tipo_solicitud) "+ 
				"where s.cod_solicitud <> "+ sol + 
				"and (s.estado = 'A' or s.estado = 'P' or s.estado = 'D') "+
			") "+
			"and (date '"+ini+"', date '"+fin+"') overlaps (sol.fecha_ini_prestamo,sol.fecha_fin_prestamo)";
		}
			
		BeanListHandler handler = new BeanListHandler(DetAprSolPrestamoForm.class);
		QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);

	}
	
	public List verificarAct(int sol, int responsable) throws SQLException, Exception, Error{
		String queryString = "select det.cod_activo  "+ 
		"from activos.act_det_solicitud det "+ 
		"inner join  activos.act_solicitud sol "+
		"on (det.cod_solicitud=sol.cod_solicitud and det.tipo_solicitud=sol.tipo_solicitud) "+ 
		"inner join activos.act_activo a "+
		"on (a.cod_activo=det.cod_activo) "+
		"where sol.cod_solicitud = "+sol+
		"and sol.tipo_solicitud = 'P' "+
		"and (det.cod_activo in( "+
			"select a.cod_activo "+ 
			"from activos.act_activo a "+  
			"inner join  activos.act_unidad u "+
			"on (a.cod_unidad = u.cod_unidad) "+ 
			"where a.cod_unidad <> "+responsable+	
		") "+
		"or a.estado='B')";

		BeanListHandler handler = new BeanListHandler(DetAprSolPrestamoForm.class);
		QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
		
	}
	
	public List verificarApr(int sol, String tipo) throws SQLException, Exception, Error{
			String queryString = "select det.cod_activo "+
			"from activos.act_det_solicitud det "+
			"inner join  activos.act_solicitud sol "+
			"on (det.cod_solicitud=sol.cod_solicitud and det.tipo_solicitud=sol.tipo_solicitud) "+
			"inner join activos.act_activo a "+
			"on (a.cod_activo=det.cod_activo) "+
			"where sol.cod_solicitud="+sol+
			" and sol.tipo_solicitud = '"+tipo+
			"' and det.cod_activo in( "+
			"select ds.cod_activo "+ 
			"from activos.act_det_solicitud ds "+ 
			"inner join  activos.act_solicitud s "+ 
			"on (ds.cod_solicitud=s.cod_solicitud and ds.tipo_solicitud=s.tipo_solicitud) "+ 
			"where s.cod_solicitud <> "+sol+
			" and s.estado = 'A' "+
			") "+
			"or a.estado='B'";
		BeanListHandler handler = new BeanListHandler(DetAprSolDescActForm.class);
		QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
	}

	
	
	public ActDetSolicitud merge(ActDetSolicitud detachedInstance) {
		log.debug("merging ActDetSolicitud instance");
		try {
			ActDetSolicitud result = (ActDetSolicitud) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
	public void attachDirty(ActDetSolicitud instance) {
		log.debug("attaching dirty ActDetSolicitud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActDetSolicitud instance) {
		log.debug("attaching clean ActDetSolicitud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List descActfindSolDet(String codSol, String codResponsable) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		/*	String queryString =  "select cod_activo as codActivo from activos.act_det_solicitud " +  
		"where cod_solicitud ='" + codSol + "'";*/
		String queryString = "Select a.cod_activo as codActivo, r.nombre as nombreRecurso, r.cod_recurso as codRecurso, tu.descripcion " +
				", u.descripcion as ubicacion, ds.correlativo from activos.act_activo as a, " + 
		"inventario.inv_recurso as r, activos.act_unidad as u, activos.act_tipo_unidad as tu, activos.act_det_solicitud as ds where " +   
		"a.cod_recurso = r.cod_recurso and a.cod_unidad = u.cod_unidad and tu.cod_tipo_unidad = u.cod_tipo_unidad " +
		"and ds.cod_activo = a.cod_activo " +
		//"u.cod_responsable ='" + codResponsable + 
		" and ds.cod_solicitud ='" + codSol + "' and ds.tipo_solicitud='D' ";
		
		
		 //  BeanListHandler handler = new BeanListHandler(DetalleSolDescActForm.class);
			MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	
	public void modActivos(int codSol, String estado) throws SQLException, Exception, Error{
		String queryString ="update activos.act_activo set estado = ? where cod_activo in "+ 
			"(select cod_activo from activos.act_det_solicitud where cod_solicitud = ? and tipo_solicitud='P')";
		
		//MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[2];
		param[0] = estado;
		param[1] = codSol;	
		
		query.update(HibernateSessionFactory.getSession().connection(), queryString,param);
	}
	
	public List AbastecimientoDetFind(String codSol,String codBodega) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = 
				
				"Select ds.cod_recurso as codRecurso, r.nombre as nombreRecurso,sol.tipo_solicitud as tipoSolicitud , sol.cod_solicitud as codSolicitud, " +
				"ds.correlativo, ds.cantidad, c.nombre as categoria, s.nombre as subcategoria, sol.estado " +
				"from inventario.inv_recurso as r, activos.act_det_solicitud as ds, inventario.inv_categoria as c, " +
				"inventario.inv_sub_categoria as s, activos.act_solicitud as sol " +
				"where r.cod_categoria=c.cod_categoria and r.cod_sub_categoria=s.cod_sub_categoria " +
				"and r.cod_categoria = s.cod_categoria " +
				"and ds.cod_solicitud=sol.cod_solicitud and sol.tipo_solicitud=ds.tipo_solicitud " +
				"and ds.cod_recurso=r.cod_recurso " +
				"and ds.cod_solicitud ='" + codSol + "' "+
				"and sol.cod_bodega='" + codBodega + "' "+
				"and ds.tipo_solicitud='A' ";
			
			
				
			
							
		   BeanListHandler handler = new BeanListHandler(DetalleSolAbaForm.class);
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List AbastecimientoAprDetFind(String codSol) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString =
				"Select ds.cod_recurso as codRecurso, r.nombre as nombreRecurso, " +
				"ds.correlativo, ds.cantidad, c.nombre as categoria, s.nombre as subcat, sol.estado, m.nom_medida as medidaDes " +
				"from inventario.inv_recurso as r, activos.act_det_solicitud as ds, inventario.inv_categoria as c, " +
				"inventario.inv_sub_categoria as s, activos.act_solicitud as sol, registro.medida as m " +
				"where r.cod_categoria=c.cod_categoria and r.cod_sub_categoria=s.cod_sub_categoria " +
				"and r.cod_categoria = s.cod_categoria " +
				"and ds.cod_solicitud=sol.cod_solicitud and sol.tipo_solicitud=ds.tipo_solicitud " +
				"and ds.cod_recurso=r.cod_recurso " +
				"and r.cod_unidad_medida=m.id_medida " +
				"and ds.cod_solicitud ='" + codSol + "' "+
				"and ds.tipo_solicitud='A' ";

				
				
				/*
				"Select ds.cod_recurso as codRecurso, r.nombre as nombreRecurso, " +
				"ds.correlativo, ds.cantidad, c.nombre as categoria, s.nombre as subcategoria, sol.estado " +
				"from inventario.inv_recurso as r, activos.act_det_solicitud as ds, inventario.inv_categoria as c, " +
				"inventario.inv_sub_categoria as s, activos.act_solicitud as sol " +
				"where r.cod_categoria=c.cod_categoria and r.cod_sub_categoria=s.cod_sub_categoria " +
				"and r.cod_categoria = s.cod_categoria " +
				"and ds.cod_solicitud=sol.cod_solicitud and sol.tipo_solicitud=ds.tipo_solicitud " +
				"and ds.cod_recurso=r.cod_recurso " +
				"and ds.cod_solicitud ='" + codSol + "' "+
				"and ds.tipo_solicitud='A' ";*/
				
				
									
		   BeanListHandler handler = new BeanListHandler(DetAprSolAbaForm.class);
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public void trasladarBodega(String codBodega, Integer codSol, String comentarioTecnico) throws SQLException, Exception, Error{
		String queryString ="update activos.act_activo set cod_unidad = ?, estado='T' where cod_activo in "+ 
			"(select cod_activo from activos.act_det_solicitud where cod_solicitud = ? and tipo_solicitud='D')";
		
		//MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[2];
		param[0] = Integer.valueOf(codBodega);
		param[1] = codSol;	
		query.update(HibernateSessionFactory.getSession().connection(), queryString,param);
		
		/*Ahora actualizo la solicitud con los comentarios tecnicos
		queryString = "update activos.act_solicitud set comentario_tecnico= ?" +
		" where cod_solicitud = ? and tipo_solicitud='D' ";
		param[0] = comentarioTecnico;
		param[1] = codSol;	
		query.update(HibernateSessionFactory.getSession().connection(), queryString, param);
		*/
	}
	
	public List findAllDetDescAct(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select "+
		"act_activo.\"cod_activo\" as codActivo, " +
	     "act_activo.\"cod_recurso\" AS act_activo_cod_recurso, "+
	     "act_unidad.\"cod_responsable\" AS codPropietario, "+
	     "usuario.\"primer_nom\" || ' ' || usuario.\"primer_ape\" as propietario, "+
	     "inv_recurso.\"nombre\" AS nombreRecurso,"+
	     "act_unidad.\"descripcion\" AS ubicacion,"+
	     "act_activo.\"num_serie\" AS serie,"+
	     "inv_categoria.\"nombre\" AS categoria,"+
	     "act_activo.\"estado\" ,act_det_solicitud.\"correlativo\" as correlativo, " +
	     "inv_sub_categoria.\"nombre\" AS subcat "+
        "from "+
        " \"activos\".\"act_activo\" act_activo INNER JOIN \"activos\".\"act_det_solicitud\" act_det_solicitud ON act_activo.\"cod_activo\" = act_det_solicitud.\"cod_activo\" "+
        "INNER JOIN \"activos\".\"act_unidad\" act_unidad ON act_activo.\"cod_unidad\" = act_unidad.\"cod_unidad\" " +
        "INNER JOIN \"inventario\".\"inv_recurso\" inv_recurso ON act_activo.\"cod_recurso\" = inv_recurso.\"cod_recurso\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei_A ON act_unidad.\"cod_responsable\" = usuario_dei_A.\"codigo_empleado\" "+
        "INNER JOIN \"activos\".\"act_solicitud\" act_solicitud ON act_solicitud.\"tipo_solicitud\" = act_det_solicitud.\"tipo_solicitud\" and act_det_solicitud.\"tipo_solicitud\"='D' " +
        "AND act_solicitud.\"cod_solicitud\" = act_det_solicitud.\"cod_solicitud\" " +
        "INNER JOIN \"inventario\".\"inv_sub_categoria\" inv_sub_categoria ON inv_recurso.\"cod_categoria\" = inv_sub_categoria.\"cod_categoria\" "+
	     "AND inv_sub_categoria.\"cod_sub_categoria\" = inv_recurso.\"cod_sub_categoria\" "+
	     "INNER JOIN \"inventario\".\"inv_categoria\" inv_categoria ON inv_sub_categoria.\"cod_categoria\" = inv_categoria.\"cod_categoria\" "+
        "INNER JOIN \"seguridad\".\"usuario\" usuario ON usuario_dei_A.\"codigo_empleado\" = usuario.\"id_usuario\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei ON usuario.\"id_usuario\" = usuario_dei.\"codigo_empleado\" " +
		"where " +
	    "act_det_solicitud.\"cod_solicitud\" = ?; ";

		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}
	
	public Integer esAceptable(int sol, String tipo) throws SQLException, Exception, Error{
		List data;
		String queryString = "select det.cod_activo, det.cod_solicitud, det.tipo_solicitud from activos.act_det_solicitud  as det, " +
		"activos.act_solicitud as acs where cod_activo in " +
		"(select det.cod_activo from activos.act_det_solicitud as det where det.cod_solicitud = '" + sol + "' " +
		"and det.tipo_solicitud = '" + tipo + "')	and (det.tipo_solicitud = 'P' or det.tipo_solicitud ='D' )"+
		"and det.cod_solicitud <> '" + sol + "' and acs.estado = 'E' and det.cod_solicitud=acs.cod_solicitud"+
		"and det.tipo_solicitud=acs.tipo_solicitud";
		BeanListHandler handler = new BeanListHandler(DetAprSolDescActForm.class);
	QueryRunner query = new QueryRunner();
    data = (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
    if(!data.isEmpty()) 
    	return 0;
    else{
    	queryString = "select det.cod_activo from activos.act_det_solicitud as det, activos.act_activo as ac where det.cod_solicitud = '" + sol + "' "+
    					"and det.tipo_solicitud = '" + tipo + "' and det.cod_activo = ac.cod_activo and ac.estado <> 'A'";
    	data = (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
    	if(!data.isEmpty()) 
        	return 0;
    	}
    return 1;	
    }
	
	public List findAllRecDetDesc(int valor) throws SQLException, Exception, Error{
		log.debug("finding all ActDetSolicitud instances");
		String queryString = "select "+
		"act_activo.\"cod_activo\" as codActivo, " +
	     "act_activo.\"cod_recurso\" AS act_activo_cod_recurso, "+
	     "act_unidad.\"cod_responsable\" AS codPropietario, "+
	     "usuario.\"primer_nom\" || ' ' || usuario.\"primer_ape\" as propietario, "+
	     "inv_recurso.\"nombre\" AS nombreRecurso,"+
	     "act_unidad.\"descripcion\" AS ubicacion,"+
	     "act_activo.\"num_serie\" AS serie,"+
	     "inv_categoria.\"nombre\" AS categoria,"+
	     "act_activo.\"estado\" ,act_det_solicitud.\"correlativo\" as correlativo, " +
	     "inv_sub_categoria.\"nombre\" AS subcat "+
        "from "+
        " \"activos\".\"act_activo\" act_activo INNER JOIN \"activos\".\"act_det_solicitud\" act_det_solicitud ON act_activo.\"cod_activo\" = act_det_solicitud.\"cod_activo\" "+
        "INNER JOIN \"activos\".\"act_unidad\" act_unidad ON act_activo.\"cod_unidad\" = act_unidad.\"cod_unidad\" " +
        "INNER JOIN \"inventario\".\"inv_recurso\" inv_recurso ON act_activo.\"cod_recurso\" = inv_recurso.\"cod_recurso\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei_A ON act_unidad.\"cod_responsable\" = usuario_dei_A.\"codigo_empleado\" "+
        "INNER JOIN \"activos\".\"act_solicitud\" act_solicitud ON act_solicitud.\"tipo_solicitud\" = act_det_solicitud.\"tipo_solicitud\" and act_det_solicitud.\"tipo_solicitud\"='D' " +
        "AND act_solicitud.\"cod_solicitud\" = act_det_solicitud.\"cod_solicitud\" " +
        "INNER JOIN \"inventario\".\"inv_sub_categoria\" inv_sub_categoria ON inv_recurso.\"cod_categoria\" = inv_sub_categoria.\"cod_categoria\" "+
	     "AND inv_sub_categoria.\"cod_sub_categoria\" = inv_recurso.\"cod_sub_categoria\" "+
	     "INNER JOIN \"inventario\".\"inv_categoria\" inv_categoria ON inv_sub_categoria.\"cod_categoria\" = inv_categoria.\"cod_categoria\" "+
        "INNER JOIN \"seguridad\".\"usuario\" usuario ON usuario_dei_A.\"codigo_empleado\" = usuario.\"id_usuario\" " +
        "INNER JOIN \"seguridad\".\"usuario_dei\" usuario_dei ON usuario.\"id_usuario\" = usuario_dei.\"codigo_empleado\" " +
		"where " +
	    "act_det_solicitud.\"cod_solicitud\" = ?; ";

		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = valor;	
		
	   
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
		/*Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();*/
	}
    
}
