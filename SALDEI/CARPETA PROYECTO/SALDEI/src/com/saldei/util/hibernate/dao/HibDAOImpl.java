/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;


public class HibDAOImpl implements HibDAO {

	private Log log = LogFactory.getLog(HibDAOImpl.class);
	private Class clazz;
	private Session hSession;	
	
	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#getSession()
	 */
	public Session getSession(){
		Session session =  HibernateSessionFactory.currentSession();		
		return session;
	}

	private Session getHSession(){
		this.hSession = HibernateSessionFactory.currentSession();		
		return hSession;
	}

	public HibDAOImpl(){}
	
	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#findSession(java.lang.String)
	 */
	public List findSession(String hql) {
		try{
			List list = this.getHSession().createQuery(hql).list();			
			return list;
		}catch(Exception ex){
			this.getHSession().close();
			ex.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#getAllSession(java.lang.Class)
	 */
	public List getAllSession(Class clazz) {
		try{
			List list = this.getHSession().createCriteria(clazz).list();			
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public HibDAOImpl(Class clazz){
		log.debug("Inicio de HibDAOImpl con: " + clazz.getName());
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#find(java.lang.String)
	 */
	public List find(String hql) {
		try{
			List list = this.getHSession().createQuery(hql).list();
			this.getHSession().close();
			return list;
		}catch(Exception ex){
			this.getHSession().close();
			ex.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#getByExample(java.lang.Object, boolean)
	 */
	public List getByExample(Object obj,Class clazz) {
		try{
			Example example = Example.create(obj);
			example.excludeZeroes().ignoreCase();
			List list = this.getHSession().createCriteria(clazz).add(example).list();
			this.getHSession().close();
			return list;
		}catch(Exception ex){
			this.getHSession().close();
			ex.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#remove(java.lang.Object)
	 */
	public void delete(Object obj){
		try{
			Transaction tx = this.getHSession().beginTransaction();		
			this.getHSession().delete(obj);
			tx.commit();
			this.getHSession().close();
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();
		}
	}

	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#save(java.lang.Object)
	 */
	public void save(Object obj) {
		try{
			Transaction tx = this.getHSession().beginTransaction();		
			this.getHSession().save(obj);
			tx.commit();
			this.getHSession().close();
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();
		}
	}

	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#update(java.lang.Object)
	 */
	public void update(Object obj) {
		try{
			Transaction tx = this.getHSession().beginTransaction();
			this.getHSession().update(obj);
			tx.commit();
			this.getHSession().close();
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#update(java.lang.String)
	 */
	public int update(String hql){
		Query query  = this.getHSession().createQuery(hql);
        int rowCount = query.executeUpdate();
        return rowCount;
	}
	
	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#findByProps(java.lang.String,java.lang.Object[])
	 */	
	public List findByProps(String hql, Object[] params){		
		try{
			Query query = this.getHSession().createQuery(hql);		
			for(int i=0; i<params.length; i++){
				query.setParameter(i, params[i]);
			}
			List list = query.list();
			this.getHSession().close();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#findByPropsSession(java.lang.String,java.lang.Object[])
	 */	
	public List findByPropsSession(String hql, Object[] params){		
		try{
			Query query = this.getHSession().createQuery(hql);		
			for(int i=0; i<params.length; i++){
				query.setParameter(i, params[i]);
			}
			List list = query.list();			
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#findByFilter(java.lang.Object)
	 */	
	public List findByFilter(Object obj){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#getSequence(java.lang.String)
	 */
	public int getSequence(String table){		
		List list = this.find("from " + table);
		int sequence = list.size();
		return sequence + 1;
	}
	
	/* (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDao#getAll()
	 */	
	public List getAll(){
		try{
			List list = this.getHSession().createCriteria(this.clazz).list();
			this.getHSession().close();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDAO#getAll(java.lang.Class)
	 */
	public List getAll(Class clazz){
		try{
			List list = this.getHSession().createCriteria(clazz).list();
			this.getHSession().close();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDAO#updateByProps(java.lang.String, java.lang.Object[])
	 */
	public void updateByProps(String hql, Object[] params){		
		try{
			Query query = this.getHSession().createQuery(hql);		
			for(int i=0; i<params.length; i++){
				query.setParameter(i, params[i]);
			}
			query.executeUpdate();
			this.getHSession().close();			
		}catch(Exception ex){
			ex.printStackTrace();
			this.getHSession().close();			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.adminlab.util.hibernate.dao.HibDAO#closeSession()
	 */
	public void closeSession(){
		this.hSession.close();
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#close()
	 */
	public void close() {
		this.getHSession().beginTransaction();		
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#commit(org.hibernate.Transaction)
	 */
	public void commit(Transaction tx) {
		tx.commit();
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#delete(java.lang.Object, org.hibernate.Session)
	 */
	public void delete(Object obj, Session session) throws Exception {
		session.delete(obj);		
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#open()
	 */
	public Transaction open() {
		Transaction tx = this.getHSession().beginTransaction();
		return tx;
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#rollback(org.hibernate.Transaction)
	 */
	public void rollback(Transaction tx) {
		tx.rollback();		
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#save(java.lang.Object, org.hibernate.Session)
	 */
	public void save(Object obj, Session session) throws Exception {
		session.save(obj);		
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#update(java.lang.Object, org.hibernate.Session)
	 */
	public void update(Object obj, Session session) throws Exception {
		session.update(obj);		
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#updateSinCommit(java.lang.String)
	 */
	public int updateSinCommit(String hql) throws Exception {
		int rowCount = 0;
		Query query  = this.getHSession().createQuery(hql);
        rowCount = query.executeUpdate();
        return rowCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.saldei.util.hibernate.dao.HibDAO#find(java.lang.String, org.hibernate.Session)
	 */
	public List find(String hql, Session session) {
		try{
			List list = session.createQuery(hql).list();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}



}//clase
