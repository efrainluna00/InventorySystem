/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
    public static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	public static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    public static Configuration configuration = new Configuration();
    public static org.hibernate.SessionFactory sessionFactory;
    public static String configFile = CONFIG_FILE_LOCATION;
    
    
	public HibernateSessionFactory() {
    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

        return session;
    }

	/**
     *  Rebuild hibernate session factory
     *
     */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     *
     */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
     *  return session factory
     *
     *	session factory will be rebuilded in the next call
     */
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	/**
     *  return hibernate configuration
     *
     */
	public static Configuration getConfiguration() {
		return configuration;
	}
	
	public static Session currentSession() throws HibernateException {
	    Session session = (Session) threadLocal.get();
	
	    if (session != null && !session.isOpen()) session = null;
	    if (session == null) {
	      if (sessionFactory == null) {
	        try {
	          configuration.configure(CONFIG_FILE_LOCATION);
	          sessionFactory = configuration.buildSessionFactory();
	        } catch (Exception e) {
	          System.err
	    .println("%%%% Error Creating HibernateSessionFactory %%%%");
	          e.printStackTrace();
	        }
	      }
	      session = sessionFactory.openSession();
	      threadLocal.set(session);
	    }
	    return session;
	  }
}
