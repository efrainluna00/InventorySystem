package com.saldei.hibernate.tables.activos;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.config.BeanHandler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.hibernate.dao.HibernateSessionFactory;
import com.saldei.web.form.activos.AprSolPrestamoForm;

import com.saldei.web.form.activos.ReqRecursoForm;

import com.saldei.web.form.activos.SolDescActForm;

import com.saldei.web.form.activos.AprReqRecursoForm;
//import com.saldei.web.form.activos.AprSolDescActForm;
import com.saldei.web.form.activos.AprSolDescActForm;
import com.saldei.web.form.activos.AprSolSuministroForm;
import com.saldei.web.form.activos.MovSolSuministroForm;
import com.saldei.web.form.activos.RecibirDescargaActivoForm;
import com.saldei.web.form.activos.SolPrestamoForm;
import com.saldei.web.form.activos.SolSuministroForm;
import com.saldei.web.form.activos.SolicitudCompraForm;
import com.saldei.web.form.inventario.AprSolicitudAbaForm;
import com.saldei.web.form.inventario.SolicitudAbastecimientoForm;

/**
 * Data access object (DAO) for domain model class ActSolicitud.
 * 
 * @see com.saldei.hibernate.tables.activos.ActSolicitud
 * @author MyEclipse Persistence Tools
 */

public class ActSolicitudDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActSolicitudDAO.class);
	// property constants
	public static final String COD_SOLICITANTE = "codSolicitante";
	public static final String ESTADO = "estado";
	public static final String COD_BODEGA = "codBodega";
	public static final String URL_COTIZACION = "urlCotizacion";

	public void save(ActSolicitud transientInstance) {
		log.debug("saving ActSolicitud instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActSolicitud persistentInstance) {
		log.debug("deleting ActSolicitud instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActSolicitud findById(
			com.saldei.hibernate.tables.activos.ActSolicitudId id) {
		log.debug("getting ActSolicitud instance with id: " + id);
		try {
			ActSolicitud instance = (ActSolicitud) getSession().get(
					"com.saldei.hibernate.tables.activos.ActSolicitud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActSolicitud instance) {
		log.debug("finding ActSolicitud instance by example");
		try {
			List results = getSession().createCriteria(
					"com.saldei.hibernate.tables.activos.ActSolicitud").add(
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
		log.debug("finding ActSolicitud instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActSolicitud as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCodSolicitante(Object codSolicitante) {
		return findByProperty(COD_SOLICITANTE, codSolicitante);
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findByCodBodega(Object codBodega) {
		return findByProperty(COD_BODEGA, codBodega);
	}

	public List findByUrlCotizacion(Object urlCotizacion) {
		return findByProperty(URL_COTIZACION, urlCotizacion);
	}
	
	public List findSol(String cod, String mostrarHistorico) throws SQLException, Exception, Error{
		String queryString = "select "+

        "ac.cod_solicitud as codSolicitud, "+
        "ac.cod_solicitante as codSolicitante,ac.comentario as comentario, "+
        "ac.fecha_ini_prestamo as fechaIniPrestamo, "+       
        "ac.fecha_fin_prestamo as fechaFinPrestamo, "  +
        "u.primer_nom || ' ' || u.primer_ape as solicitante, "  +
        "u2.primer_nom || ' ' || u2.primer_ape as responsable, "  +
        "ac.cod_propietario as codPropietario, ac.cod_unidad as codUnidad, un.descripcion as descripcion, "+
        "ac.estado as estado, "+
        "case when ac.estado='G' then 'Grabada' " +
        "when ac.estado='E' then 'Enviada' " +
        "when ac.estado='A' then 'Aprobada' " +
        "when ac.estado='R' then 'Rechazada' " +
        "when ac.estado='B' then 'Anulada' " +
        "when ac.estado='D' then 'Entregado' " +
        "when ac.estado='P' then 'Pre Aprobada' " +
        "when ac.estado='F' then 'Finalizada' end as estadoNombre, "+
        "u.id_usuario, un.cod_unidad,ac.tipo_solicitud, ac.motivo_rechazo as motivoRechazo " +
        "from "+
        "activos.act_solicitud ac, seguridad.usuario u,seguridad.usuario u2, activos.act_unidad un "+ //seguridad.usuario u2,
        "where ac.cod_solicitante = u.id_usuario and ac.cod_unidad = un.cod_unidad and ac.cod_propietario = coalesce('" + cod +  "',ac.cod_propietario) and  u2.id_usuario = coalesce('" + cod +  "',ac.cod_propietario) and ac.tipo_solicitud='P'";// and u2.id_usuario = '"+ cod + "'";
		queryString += mostrarHistorico.equals("S") ? " and (ac.estado='A' or ac.estado='D' or ac.estado='R' or ac.estado='F' or ac.estado='P' or ac.estado='B') " : " and ac.estado='E' ";
        queryString += "order by ac.fecha_creacion desc";
		
		
	    BeanListHandler handler = new BeanListHandler(AprSolPrestamoForm.class); 
		//MapListHandler handler = new MapListHandler();
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        Object []param = new Object[1];
		param[0] = cod;	
		
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
      
	}

	public List findSol2(String cod,String mostrarHistorico) throws SQLException, Exception, Error{
		
		String queryString;
		queryString = mostrarHistorico.equals("S") ?
		"select "+
		 "ac.cod_solicitud as codSolicitud, "+
	        "ac.cod_solicitante as codSolicitante,ac.comentario as comentario, "+
	        "ac.fecha_ini_prestamo as fechaIniPrestamo, "+       
	        "ac.fecha_fin_prestamo as fechaFinPrestamo, "  +
	        "u.primer_nom || ' ' || u.primer_ape as solicitante, "  +
	        "u2.primer_nom || ' ' || u2.primer_ape as responsable, "  +
	        "ac.cod_propietario as codPropietario, ac.cod_unidad as codUnidad, un.descripcion as descripcion, "+
	        "ac.estado as estado,  "+
	        "case when ac.estado='G' then 'Grabada' " +
	        "when ac.estado='E' then 'Enviada' " +
	        "when ac.estado='A' then 'Aprobada' " +
	        "when ac.estado='R' then 'Rechazada' " +
	        "when ac.estado='B' then 'Anulada' " +
	        "when ac.estado='D' then 'Entregado' " +
	        "when ac.estado='F' then 'Finalizada' end as estadoNombre, "+
	        "u.id_usuario, un.cod_unidad,ac.tipo_solicitud " +
	        "from "+
	        "activos.act_solicitud ac, seguridad.usuario u,seguridad.usuario u2, activos.act_unidad un "+ 
	        "where ac.cod_solicitante = u.id_usuario and ac.cod_unidad = un.cod_unidad "+ //and ac.cod_propietario ='" + cod + 
	        " and ac.estado <> 'G' and ac.estado <> 'P' and ac.estado <> 'E' and ac.tipo_solicitud='P' and u2.id_usuario='" + cod + "' order by ac.fecha_creacion desc "
			 :
			"select ac.cod_solicitud as codSolicitud, "+
			"ac.cod_solicitante as codSolicitante,ac.comentario as comentario, "+
			"ac.fecha_ini_prestamo as fechaIniPrestamo, "+       
			"ac.fecha_fin_prestamo as fechaFinPrestamo, "  +
			"u.primer_nom || ' ' || u.primer_ape as solicitante, "  +
			"u2.primer_nom || ' ' || u2.primer_ape as responsable, "  +
			"ac.cod_propietario as codPropietario, ac.cod_unidad as codUnidad, un.descripcion as descripcion, "+
			"ac.estado as estado, "+
			"case when ac.estado='P' then 'Pre Aprobada' " +
			" end as estadoNombre, "+
			"u.id_usuario, un.cod_unidad,ac.tipo_solicitud " +
			"from "+
			"activos.act_solicitud ac, seguridad.usuario u,seguridad.usuario u2, activos.act_unidad un "+ 
			"where ac.cod_solicitante = u.id_usuario and ac.cod_unidad = un.cod_unidad and ac.cod_propietario = ac.cod_propietario " +
			" and ac.estado = 'P' and ac.tipo_solicitud='P' and u2.id_usuario=ac.cod_propietario order by ac.fecha_creacion desc";

	    BeanListHandler handler = new BeanListHandler(AprSolPrestamoForm.class); 
		//MapListHandler handler = new MapListHandler();
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class); 
	    QueryRunner query = new QueryRunner();
        Object []param = new Object[1];
		param[0] = cod;	
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
      
	}
	public List tieneSuministros(int codSol)throws SQLException, Exception, Error{
		String queryString = "select "+
		"* from activos.act_det_solicitud where cod_solicitud=? and tipo_solicitud='S'";
		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = codSol;
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param,handler);
	}
	
	public List esJefe(String codUsu)throws SQLException, Exception, Error{
		String queryString = "select "+
		"* from seguridad.usuario_perfil where id_usuario=? and id_perfil=1";
		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = codUsu;
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param,handler);
	}

	public List findAll(String cod) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = "select "+
	        "ac.cod_solicitud as codSolicitud, "+
	        "ac.cod_solicitante as codSolicitante,ac.comentario as comentario, "+
	        "ac.fecha_ini_prestamo as fechaIniPrestamo, "+       
	        "ac.fecha_fin_prestamo as fechaFinPrestamo, "  +
	        "u.primer_nom || ' ' || u.primer_ape as propietario, "  +
	        "ac.cod_propietario as codPropietario, ac.cod_unidad as codUnidad, un.descripcion as descripcion, "+
	        "ac.estado as estado, ac.tipo_solicitud, ac.fecha_creacion as fechaCreacion,to_char(ac.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion, " +
	        "ac.fecha_aprobacion as fechaAprobacion,to_char(ac.fecha_aprobacion,'dd/mm/yyyy hh24:mi:ss') as fecha_aprobacion, " +
	        "ac.fecha_finalizacion as fechaFinalizacion, " +
	        "ac.fecha_anulacion as fechaAnulacion, " +
	        "ac.motivo_rechazo as motivoRechazo, " +
	        "ac.motivo_anulacion as motivoAnulacion, " +
	        "case when ac.estado='G' then 'Grabada' " +
	        "when ac.estado='E' then 'Enviada' " +
	        "when ac.estado='A' then 'Aprobada' " +
	        "when ac.estado='R' then 'Rechazada' " +
	        "when ac.estado='B' then 'Anulada' " +
	        "when ac.estado='D' then 'Entregado' " +
	        "when ac.estado='P' then 'Pendiente' " +
	        "when ac.estado='F' then 'Finalizada' end as estadoNombre, "+
	        "u.id_usuario, un.cod_unidad " +
	        "from "+
	        "activos.act_solicitud ac, seguridad.usuario u, activos.act_unidad un "+
	        "where ac.cod_propietario = u.id_usuario and ac.cod_unidad = un.cod_unidad and ac.cod_solicitante =? and ac.tipo_solicitud='P' "+
	        "order by ac.fecha_creacion desc,ac.estado desc";
		    BeanListHandler handler = new BeanListHandler(SolPrestamoForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            Object []param = new Object[1];
    		param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}

	public List findUsr(String id) throws SQLException, Exception, Error{
		String queryString = "select * "+
		"from seguridad.usuario where id_usuario=?";
		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = id;
		return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param,handler);
	
	}
	
	public List findAllSolicutudCompras(String id_usuario) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		String queryString = "from ActSolicitud as ac where ac.codSolicitante = '" +id_usuario+
				"' and ac.id.tipoSolicitud = 'C' order by ac.fechaCreacion desc ";	
		
		Query queryObject = getSession().createQuery(queryString);			
		
		return queryObject.list();
		
	}
	 /*
	  * metodo que busca todas las solicitudes de compras que estan con estado enviadas
	  */
	public List findAllSolicutudComprasEnviadas(String mh) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud = 'C' ";
		queryString += mh.equals("N") ? "and ac.estado = 'E' order by ac.fechaCreacion, ac.estado desc  " : 
		"and (ac.estado = 'F' or ac.estado = 'R' or ac.estado='A' or ac.estado='H' or ac.estado='I' or ac.estado='B') order by ac.fechaCreacion,ac.estado desc  ";
		
		Query queryObject = getSession().createQuery(queryString);
		
		return queryObject.list();
		
	}
	
	/*
	 * metodo que busca todas las solicitudes de compras que estan en estadao Aprobadas
	 */
	public List findAllSolicutudComprasAprobadas(String codUsuario,String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
				
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud in ('C') " +
		 					 " and ac.codBodega in (select uni.codUnidad from ActUnidad as uni where uni.codResponsable = ?) " ;	
		queryString += mostrarHistorico.equals("N")	? "and ac.estado in ('A','H') order by ac.fechaCreacion desc, ac.estado desc " : " and (ac.estado = 'F' or ac.estado = 'I' or ac.estado = 'B' )  order by ac.fechaCreacion desc, ac.estado desc " ;	
		
		
		Query queryObject = getSession().createQuery(queryString);
		
		queryObject.setParameter(0, codUsuario);
		
		return queryObject.list();
		
	}
	
	
	/*
	 * metodo que busca todas las solicitudes de abastecimiento que estan en estadao Aprobadas
	 */
	public List findAllSolicutudAbasAprobadas(String codUsuario,String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud = 'A' " +
							 " and ac.codBodega in (select uni.codUnidad from ActUnidad as uni where uni.codResponsable = ?)" ;
		queryString += mostrarHistorico.equals("N") ? " and ac.estado in ('A','H') " : "and (ac.estado = 'F' or ac.estado = 'B' ) ";
		queryString += " order by ac.fechaCreacion, ac.estado desc ";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, codUsuario);
		
		return queryObject.list();
		
	}	
	
	public List findAllSolicutudSuministroAprobadas(String codUsuario, String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud = 'S' " +
							 " and ac.codBodega in (select uni.codUnidad from ActUnidad as uni where uni.codResponsable = ?) ";	
		queryString += mostrarHistorico.equals("N") ? " and ac.estado in ('A','H') " : " and (ac.estado = 'F' or ac.estado='B' ) " ;
		queryString += " order by ac.fechaCreacion, ac.estado desc ";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, codUsuario);
		
		return queryObject.list();
		
	}
	
	public List findAllSolicutudReq(String codUsuario, String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud in ('R') " +
							 " and ac.codBodega in (select uni.codUnidad from ActUnidad as uni where uni.codResponsable = ?) " ;	
		queryString+= mostrarHistorico.equals("N") ? " and ac.estado in ('A','H') " : " and (ac.estado = 'F' or ac.estado='B') ";
		queryString += " order by ac.fechaCreacion, ac.estado desc ";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, codUsuario);
		
		return queryObject.list();
		
	}
	
	public List findAllSolicutudCom(String codUsuario, String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
		String queryString = "from ActSolicitud as ac where ac.id.tipoSolicitud in ('C') " +
							 " and ac.codBodega in (select uni.codUnidad from ActUnidad as uni where uni.codResponsable = ?) " ;	
		queryString+= mostrarHistorico.equals("N") ? " and ac.estado in ('I','H') " : " and (ac.estado = 'F' or ac.estado='B')";
		queryString += " order by ac.fechaCreacion, ac.estado desc ";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, codUsuario);
		
		return queryObject.list();
		
	}
	
	public List findAllRequisicion(String cod) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
			" a.fecha_creacion as fechaCreacion, "+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'F' then 'Finalizada'" +
	 "	  end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , comentario as comentario, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion  "+
					" from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c " +
					" where a.tipo_solicitud = 'R'	" +
					" and a.cod_solicitante = ? " +
					" and a.cod_unidad = b.cod_unidad "+
					" and a.cod_bodega = c.cod_bodega " +
					"order by a.fecha_creacion desc,a.estado desc";
		    BeanListHandler handler = new BeanListHandler(ReqRecursoForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            Object []param = new Object[1];
    		param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List findAllReq(String mh) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
			" a.fecha_creacion as fechaCreacion, u.primer_nom || ' ' || u.primer_ape as solicitante,"+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'H' then 'Parcial' " +
	 " when 'F' then 'Finalizada'" +
	 " end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , a.motivo_rechazo as motivoRechazo, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion, a.comentario as comentario  "+
					" from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c, seguridad.usuario u" +
					" where a.tipo_solicitud = 'R'	" +
					" and a.cod_unidad = b.cod_unidad "+
					" and a.cod_bodega = c.cod_bodega " +
					" and a.cod_solicitante = u.id_usuario ";
			queryString += mh.equals("S") ? " and (a.estado='F' or a.estado='A' or a.estado='R' or a.estado='B' or a.estado='H' ) " :
				" and a.estado='E' ";
			queryString += "order by a.fecha_creacion, a.estado desc";
					//" and a.estado <> 'G' "+
					
		    BeanListHandler handler = new BeanListHandler(AprReqRecursoForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
           // Object []param = new Object[1];
    		//param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(),queryString,handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List findAllSum(String mh) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
	String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
	 " a.fecha_creacion as fechaCreacion, u.primer_nom || ' ' || u.primer_ape as solicitante,"+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'H' then 'Parcial' " +
	 " when 'F' then 'Finalizada'" +
	 " end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , a.comentario as comentario, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion  "+
	 " from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c, seguridad.usuario u" +
	 " where a.tipo_solicitud = 'S'	" +
	 " and a.cod_unidad = b.cod_unidad "+
	 " and a.cod_bodega = c.cod_bodega " +
	 " and a.cod_solicitante = u.id_usuario ";
			
	queryString += mh.equals("S") ? " and (a.estado='F' or a.estado='R' or a.estado='A' or a.estado='B' or a.estado='H') " : 
	" and a.estado='E' ";
		
	queryString += " order by a.fecha_creacion, a.estado desc ";
		    BeanListHandler handler = new BeanListHandler(AprSolSuministroForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
           // Object []param = new Object[1];
    		//param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(),queryString,handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	
	
	public List findAllSolSuministro(String cod) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
			" a.fecha_creacion as fechaCreacion, "+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'F' then 'Finalizada'" +
	 "	  end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , comentario as comentario, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion  "+
					" from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c " +
					" where a.tipo_solicitud = 'S'	" +
					" and a.cod_solicitante = ? " +
					" and a.cod_unidad = b.cod_unidad "+
					" and a.cod_bodega = c.cod_bodega " +
					"order by a.fecha_creacion desc,a.estado desc";
		    BeanListHandler handler = new BeanListHandler(SolSuministroForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            Object []param = new Object[1];
    		param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List findAllMovSolSuministro(String cod) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
			" a.fecha_creacion as fechaCreacion, "+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'F' then 'Finalizada'" +
	 "	  end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , comentario as comentario, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion  "+
					" from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c " +
					" where a.tipo_solicitud = 'S'	" +
					" and a.cod_solicitante = ? " +
					" and a.cod_unidad = b.cod_unidad "+
					" and a.cod_bodega = c.cod_bodega " +
					"order by a.fecha_creacion desc";
		    BeanListHandler handler = new BeanListHandler(MovSolSuministroForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            Object []param = new Object[1];
    		param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List findAllAprRequisicion(String cod) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString = " select a.cod_solicitud as codSolicitud, to_char(a.fecha_creacion,'DD/MM/YYYY HH24:MI:SS') as fecha_creacion, " +
			" a.fecha_creacion as fechaCreacion, "+
	 " case a.estado when 'G' then 'Grabada'  when 'E' then 'Enviada'  " +
	 " when 'A' then 'Aprobada' " +
	 " when 'R' then 'Rechazada' " +
	 " when 'B' then 'Anulada' " +
	 " when 'F' then 'Finalizada'" +
	 "	  end as estadoDes , a.estado as estado, b.cod_unidad as codUnidad, b.descripcion as unidadDes, "  +
	 " c.cod_bodega as codBodega, c.nombre as bodegaDes, a.tipo_solicitud as tipoSolicitud , comentario as comentario, " +
	 " to_char(a.fecha_anulacion,'DD/MM/YYYY HH24:MI:SS') as fecha_anulacion,to_char(a.fecha_finalizacion,'DD/MM/YYYY HH24:MI:SS') as fecha_finalizacion,  to_char(a.fecha_aprobacion,'DD/MM/YYYY HH24:MI:SS') as fecha_aprobacion, " +
	 "  a.motivo_rechazo as motivoRechazo,a.motivo_anulacion as motivoAnulacion  "+
					" from activos.act_solicitud a, activos.act_unidad b, inventario.inv_bodega c " +
					" where a.tipo_solicitud = 'R'	" +
					" and a.cod_solicitante = ? " +
					" and a.cod_unidad = b.cod_unidad "+
					" and a.cod_bodega = c.cod_bodega " +
					"order by a.fecha_creacion desc";
		    BeanListHandler handler = new BeanListHandler(AprReqRecursoForm.class); 
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            Object []param = new Object[1];
    		param[0] = cod;	
    		
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	public String findSolicitante(String codSolicitante) throws SQLException, Exception, Error{
		String queryString = "select u.primer_nom || ' ' || u.primer_ape as solicitante " +
		"from seguridad.usuario u where u.id_usuario = ?;";
			
			
		MapListHandler handler = new MapListHandler();
		QueryRunner query = new QueryRunner();
		Object []param = new Object[1];
		param[0] = codSolicitante;	
		
	   
        return (String) query.query(HibernateSessionFactory.getSession().connection(), queryString,param, handler);
       
	}
	

	public ActSolicitud merge(ActSolicitud detachedInstance) {
		log.debug("merging ActSolicitud instance");
		try {
			ActSolicitud result = (ActSolicitud) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActSolicitud instance) {
		log.debug("attaching dirty ActSolicitud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActSolicitud instance) {
		log.debug("attaching clean ActSolicitud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//to_char(u.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion
	public List descActfindSolicitudes(String codUsuario) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString =  
			"select s.cod_solicitud as codSolicitud, s.fecha_creacion as fechaCreacion, to_char(s.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion, s.estado as estado, " + 
			"to_char(s.fecha_aprobacion,'dd/mm/yyyy hh24:mi:ss') as fecha_aprobacion, " +
			"to_char(s.fecha_finalizacion,'dd/mm/yyyy hh24:mi:ss') as fecha_finalizacion, " +
			"to_char(s.fecha_anulacion,'dd/mm/yyyy hh24:mi:ss') as fecha_anulacion, " +
			"s.comentario, coalesce(s.motivo_rechazo, s.motivo_anulacion) as resolucion, "+
			"case when s.estado = 'G' then 'Grabada' when s.estado='E' then 'Enviada' when s.estado='A' " +  
			"then 'Aprobada' when s.estado='R' then 'Rechazada' when s.estado='B' then 'Anulada' " +
			"else 'Finalizada' end as estadoDescr from activos.act_solicitud as s " + 
			"where s.cod_solicitante ='" + codUsuario + "' and s.tipo_solicitud = 'D' order by fechaCreacion desc,estado desc ";
		   BeanListHandler handler = new BeanListHandler(SolDescActForm.class);
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	//Metodo para encontrar y aprobar/rechazar solicitudes de descarga de activos
	public List findAprSolDescAct(String mh) throws SQLException, Exception, Error{
		String queryString = "select "+

        "ac.cod_solicitud as codSolicitud, "+
        "ac.cod_solicitante as codSolicitante, "+
        "u.primer_nom || ' ' || u.primer_ape as solicitante, "  +
        "ac.cod_unidad as codUnidad, "+
        "ac.estado as estado, "+
        "case when ac.estado='G' then 'Grabada' " +
        "when ac.estado='E' then 'Enviada' " +
        "when ac.estado='A' then 'Aprobada' " +
        "when ac.estado='R' then 'Rechazada' " +
        "when ac.estado='B' then 'Anulada' " +
        "when ac.estado='F' then 'Finalizada' end as estadoNombre, "+
        "ac.tipo_solicitud as tipoSolicitud, " +
        "to_char(ac.fecha_creacion,'dd/mm/yy hh24:mi:ss') as fecha_creacion, "+
        "ac.comentario as comentario " +
        "from "+
        "activos.act_solicitud ac, seguridad.usuario u "+
        "where ac.cod_solicitante = u.id_usuario and ac.tipo_solicitud='D' ";
		queryString += mh.equals("S") ? "and (ac.estado='F' or ac.estado='A' or ac.estado='R' or ac.estado='B' ) " :
			"and ac.estado='E' ";
		queryString +=" order by ac.fecha_creacion desc";
		//LO COMENTE XQ DA UN ERROR XQ NO SE SUBIO EL APRSOLDESCACTFORM
	   BeanListHandler handler = new BeanListHandler(AprSolDescActForm.class); 
		//MapListHandler handler = new MapListHandler();//COMENTAREAR CUANDO SIRVA
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,handler);
      
	}

	public List AbastecimientoFindSolicitudes(String codUsuario) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString =  
			"select s.cod_solicitud as codSolicitud, s.fecha_creacion as fechaCreacion, to_char(s.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion, s.estado as estado, " + 
			"to_char(s.fecha_aprobacion,'dd/mm/yyyy hh24:mi:ss') as fecha_aprobacion, " +
			"to_char(s.fecha_finalizacion,'dd/mm/yyyy hh24:mi:ss') as fecha_finalizacion, " +
			"to_char(s.fecha_anulacion,'dd/mm/yyyy hh24:mi:ss') as fecha_anulacion, s.cod_bodega as codBodega, b.nombre as bodegaDes," +
			"s.comentario, coalesce(s.motivo_rechazo, s.motivo_anulacion) as resolucion,"+
			"case when s.estado = 'G' then 'Grabada' when s.estado='E' then 'Enviada' when s.estado='A' " +  
			"then 'Aprobada' when s.estado='R' then 'Rechazada' when s.estado='B' then 'Anulada' " +
			"else 'Finalizada' end as estadoDescr, s.tipo_solicitud as tipoSolicitud from activos.act_solicitud as s, inventario.inv_bodega as b " + 
			"where s.cod_solicitante ='" + codUsuario + "' and s.tipo_solicitud = 'A' and s.cod_bodega=b.cod_bodega " + 
			"order by fechaCreacion desc ";
		   BeanListHandler handler = new BeanListHandler(SolicitudAbastecimientoForm.class);
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	public List findAprSolAba(String mh) throws SQLException, Exception, Error{
		String queryString = "select "+

        "ac.cod_solicitud as codSolicitud, "+
        "ac.cod_solicitante as codSolicitante, "+
        "u.primer_nom || ' ' || u.primer_ape as solicitante, "  +
        "ac.cod_unidad as codUnidad, "+
        "ac.estado as estado, "+
        "case when ac.estado='G' then 'Grabada' " +
        "when ac.estado='E' then 'Enviada' " +
        "when ac.estado='A' then 'Aprobada' " +
        "when ac.estado='R' then 'Rechazada' " +
        "when ac.estado='B' then 'Anulada' " +
        "when ac.estado='H' then 'Parcial' " +
        "when ac.estado='F' then 'Finalizada' end as estadoNombre, "+
        "ac.tipo_solicitud as tipoSolicitud, " +
        "to_char(ac.fecha_creacion,'dd/mm/yy hh24:mi:ss') as fecha_creacion, "+
        "ac.comentario as comentario, " +
        "b.nombre as bodegaDes " +
        "from "+
        "activos.act_solicitud as ac, seguridad.usuario u, inventario.inv_bodega as b "+
        "where ac.cod_solicitante = u.id_usuario  and ac.tipo_solicitud='A' " +
        "and ac.cod_bodega=b.cod_bodega ";
		
		queryString += mh.equals("S") ? " and (ac.estado='F' or ac.estado='A' or ac.estado='R' or ac.estado='B' or ac.estado='H') " :
			" and ac.estado='E' " ;
		queryString +=  "order by ac.fecha_creacion, ac.estado desc";
		
		//and ac.estado <> 'G'
		//LO COMENTE XQ DA UN ERROR XQ NO SE SUBIO EL APRSOLDESCACTFORM
	   BeanListHandler handler = new BeanListHandler(AprSolicitudAbaForm.class); 
		//MapListHandler handler = new MapListHandler();//COMENTAREAR CUANDO SIRVA
        //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
        QueryRunner query = new QueryRunner();
        return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString,handler);
      
	}
	
	public List recDescActfindSolicitudes(String codUsuario,String mostrarHistorico) throws SQLException, Exception, Error{
		log.debug("finding all ActSolicitud instances");
		
			String queryString =  
			"select s.cod_solicitud as codSolicitud, s.fecha_creacion as fechaCreacion, to_char(s.fecha_creacion,'dd/mm/yyyy hh24:mi:ss') as fecha_creacion, s.estado as estado, " + 
			"to_char(s.fecha_aprobacion,'dd/mm/yyyy hh24:mi:ss') as fecha_aprobacion, " +
			"to_char(s.fecha_finalizacion,'dd/mm/yyyy hh24:mi:ss') as fecha_finalizacion, " +
			"to_char(s.fecha_anulacion,'dd/mm/yyyy hh24:mi:ss') as fecha_anulacion, " +
			"s.comentario, s.motivo_rechazo as resolucion, "+
			"case when s.estado = 'G' then 'Grabada' when s.estado='E' then 'Enviada' when s.estado='A' " +  
			"then 'Aprobada' when s.estado='R' then 'Rechazada' when s.estado='B' then 'Anulada' " +
			"else 'Finalizada' end as estadoDescr, tante.primer_nom || ' ' || tante.primer_ape as solicitante, " +
			"b.nombre as bodegaDes, s.cod_solicitante as codSolicitante, s.comentario_tecnico as comentarioTecnico, s.cod_bodega as codBodega " +
			"from activos.act_solicitud as s, seguridad.usuario as tante, inventario.inv_bodega as b " +
			"where  s.tipo_solicitud = 'D' " +
			"and tante.id_usuario=s.cod_solicitante "+
			//"and s.estado='A' "+
			"and s.cod_bodega=b.cod_bodega "+
			"and s.cod_bodega in (Select b.cod_bodega from activos.act_unidad as u, inventario.inv_bodega as b " +
			"where u.cod_unidad=b.cod_bodega and u.cod_responsable ='" + codUsuario + "') ";
			
			if(mostrarHistorico.equals("S"))
				queryString += "and (s.estado='F' or s.estado='B')";
			else
				queryString += "and s.estado='A' ";
			queryString += "order by fechaCreacion desc ";
			BeanListHandler handler = new BeanListHandler(RecibirDescargaActivoForm.class);
			//MapListHandler handler = new MapListHandler();
            //BeanListHandler handler = new BeanListHandler(OptionAppForm.class);       
            QueryRunner query = new QueryRunner();
            return (List) query.query(HibernateSessionFactory.getSession().connection(), queryString, handler);
           
			/*Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();*/
		
	}
	
	
}
