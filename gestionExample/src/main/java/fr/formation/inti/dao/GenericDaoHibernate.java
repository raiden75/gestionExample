package fr.formation.inti.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.formation.inti.utils.HibernateUtils;

public class GenericDaoHibernate<T,I extends Serializable> implements IGenericDao<T, I> {

	SessionFactory sf = HibernateUtils.getSessionFacory();
	Session session = sf.getCurrentSession();
	private Transaction tx = session.getTransaction();
	
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	
	
	public GenericDaoHibernate(Class<T> type) {
		this.type = type;
	}
	
	
	@SuppressWarnings("unchecked")
	public I save(T t) {
		return (I) session.save(t);
	}

	public void update(T t) {
		session.update(t);
	}


	public void delete(I i) {
		session.delete(i);
	}


	public T findById(I i) {
		return (T) session.get(this.type, i);
	}


	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "select e from "+this.type.getName()+" e";
		Query<T> query = session.createQuery(hql);
		List<T> datas = query.getResultList();
		return datas;
	}
	
	public void beginTransaction() {
		if(!session.isOpen())
			session = sf.openSession();
		if(!tx.isActive())
			tx = session.beginTransaction();
	}
	
	public void commitTransaction() {
		tx.commit();
		session.close();
	}
	
	public void rollBackTransaction() {
		tx.rollback();
	}

	public void close() {
		sf.close();
	}
}
