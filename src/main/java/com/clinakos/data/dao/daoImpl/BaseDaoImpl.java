package com.clinakos.data.dao.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class BaseDaoImpl<T,ID extends Serializable> extends HibernateDaoSupport   {
	
	private static final Logger LOG = Logger.getLogger(BaseDaoImpl.class);
	
	public void save(Object object) {
		// TODO Auto-generated method stub
		
		Session session=getSessionFactory().openSession();
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction=session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error("saving value:::",e);
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		
	}


	public void update(Object object) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().openSession();
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction=session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error("updationg value:::",e);
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		
	}

	
	public void saveOrUpdate(Object object) {
		// TODO Auto-generated method stub
		
		Session session = getSessionFactory().openSession();
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction=session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error("save or update value:::",e);
		} finally {
			session.flush();
			session.clear();
			session.close();
		}

		
	}

	
	public void persist(Object object) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().openSession();
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction=session.beginTransaction();
			session.persist(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error("Persist value:::",e);
		} finally {
			session.flush();
			session.clear();
			session.close();
		}

		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseDAO#findById(java.lang.Class,
	 * java.io.Serializable)
	 */

	public Object findById(Class<?> clazz, Serializable id) {
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Session session=getSessionFactory().openSession();
		return session.get(clazz, id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseDAO#findByAll(java.lang.Class)
	 */

	public List<?> findAll(Class<T> clazz) {
		return (List<?>) getHibernateTemplate().loadAll(clazz);
	}


	
	
	public void delete(Object object) {
		Session session=getSessionFactory().openSession();
		//Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {

			transaction=session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error("Deleting value:::",e);
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		
	}

}
