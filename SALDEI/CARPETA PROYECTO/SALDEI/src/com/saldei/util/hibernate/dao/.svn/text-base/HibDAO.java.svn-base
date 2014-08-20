/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface HibDAO {

	/**
	 * @return
	 */
	public Session getSession();
	
	/**	  
	 * @param obj Objeto a guardar manteniendo persistencia
	 */
	public void save(Object obj);
	
	/**	 
	 * @param obj Objeto a actualizar manteniendo persistencia
	 */
	public void update(Object obj);	
	
	/**	 
	 * @param hql Query
	 * @return Lista con resultados de la base
	 */
	public List find(String hql);
	
	/**	 
	 * @param obj Objeto a ser eliminado manteniendo persistencia
	 */
	public void delete(Object obj);
	
	/**
	 * @param obj
	 * @param session
	 * @throws Exception
	 */
	public void delete(Object obj, Session session)throws Exception;

	/**
	 * @param obj
	 * @param session
	 * @throws Exception
	 */
	public void update(Object obj, Session session)  throws Exception;
	
	/**
	 * @param obj
	 * @param session
	 * @throws Exception
	 */
	public void save(Object obj, Session session) throws Exception;

	/**	 
	 * @param xample Objeto que desea buscarse
	 * @param clazz Clase del objeto
	 * @return Busca un objeto especificado por una llave
	 */
	public List getByExample(Object obj,Class clazz);
	
	/**	 
	 * @param obj Objeto que se desea buscar
	 * @param properties propiedades del where
	 * @return Lista de registros de la base
	 */
	public List findByProps(String hql, Object[] params);
	
	
	/**	 
	 * @param obj Objeto que se desea buscar
	 * @param properties propiedades del where
	 * @return Lista de registros de la base
	 */
	public List findByPropsSession(String hql, Object[] params);
	
	/**	 
	 * @param obj
	 * @return
	 */
	public List findByFilter(Object obj);
	
	/**	 
	 * @param table a la que se desea saber el numero de registros
	 * @return sequencia
	 */
	public int getSequence(String table);
	
	/**	 
	 * @return devuelve todos los registros de un Objeto
	 */
	public List getAll();
	
	
	/**	 
	 * @param clazz
	 * @return registros de la clase
	 */
	public List getAll(Class clazz);
	
	/**	 
	 * @param hql
	 * @param params
	 */
	public void updateByProps(String hql, Object[] params);
	
	/**
	 * <b>Cierra la session de hibernate</b>	 
	 */
	public void closeSession();

	/**
	 * @param hql
	 * @return
	 */
	public int update(String hql);

	/**
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public int updateSinCommit(String hql)throws Exception;

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public List getAllSession(Class clazz);
	
	/**
	 * 
	 * @param hql
	 * @return
	 */
	public List findSession(String hql);
	
	/**
	 * @return
	 */
	public Transaction open();

	/**
	 *<b> Cierra Session instanciada </b>
	 */
	public void close();
	
	/**
	 * @param tx
	 */
	public void commit(Transaction tx);
	
	/**
	 * @param tx
	 */
	public void rollback(Transaction tx);
	
	/**
	 * @param hql
	 * @param session
	 * @return
	 */
	public List find(String hql, Session session) ;

}
