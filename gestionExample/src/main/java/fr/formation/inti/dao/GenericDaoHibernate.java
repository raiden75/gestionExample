package fr.formation.inti.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;


//import fr.formation.inti.utils.HibernateUtils;


public class GenericDaoHibernate<T,I extends Serializable> implements IGenericDao<T, I> {

//	SessionFactory sf = HibernateUtils.getSessionFacory();
//	Session session = sf.getCurrentSession();
	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	
	
	public GenericDaoHibernate(Class<T> type) {
		this.type = type;
	}
	
	public Session getCurrentSession() {
		if(!sessionFactory.isOpen()) {
			sessionFactory.openSession();
		}
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public I save(T t) {
		return (I) getCurrentSession().save(t);
	}

	public void update(T t) {
		getCurrentSession().update(t);
	}


	public void delete(I i) {
		getCurrentSession().delete(i);
	}


	public T findById(I i) {
		return (T) getCurrentSession().get(this.type, i);
	}


	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "select e from "+this.type.getName()+" e";
		Query<T> query = getCurrentSession().createQuery(hql);
		List<T> datas = query.getResultList();
		return datas;
	}
	
	public void close() {
		getCurrentSession().close();
		sessionFactory.close();
	}
	
	public void beginTransaction() {
		if (!getCurrentSession().isOpen()) {
			sessionFactory.openSession();
		}
		if (!getCurrentSession().getTransaction().isActive()) {
			getCurrentSession().beginTransaction();
		}
	}
		
	@Override
	public void commitTransaction() {
		getCurrentSession().getTransaction().commit();
	}



	@Override
	public void rollBackTransaction() {
		getCurrentSession().getTransaction().rollback();
	}
	
//	public void beginTransaction() {
//		if(!getCurrentSession().isOpen())
//			sessionFactory.getCurrentSession() = sessionFactory.openSession();
//		if(!tx.isActive())
//			tx = session.beginTransaction();
//	}
//	
//	public void commitTransaction() {
//		tx.commit();
//		session.close();
//	}
//	
//	public void rollBackTransaction() {
//		tx.rollback();
//	}
//
//	public void close() {
//		sf.close();
//	}
}
