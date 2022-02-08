package fr.formation.inti.dao;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.inti.entity.Employee;

@Repository
public class EmployeeDao extends GenericDaoHibernate<Employee, Integer> implements IEmployeeDao {

	private final Log log = LogFactory.getLog(EmployeeDao.class);
	
	public static EmployeeDao createInstance() {
		return new EmployeeDao();
	}
	
	public EmployeeDao() {
		super();
		log.debug("Create new EmployeeDao()");
	}
	
}
