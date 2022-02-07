package fr.formation.inti.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.IEmployeeDao;
import fr.formation.inti.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	private final Log log = LogFactory.getLog(EmployeeService.class);

	@Autowired
	private IEmployeeDao dao;
	
	public EmployeeService() {
		super();
		log.debug("Create new EmployeeService()");
//		dao = new EmployeeDao();
	}

	@Override
	public Integer save(Employee employee) {
		// TODO Auto-generated method stub
		return dao.save(employee);
	}

	@Override
	public void update(Employee employee) {
		dao.update(employee);

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		dao.beginTransaction();
		List<Employee> liste = dao.findAll();
		dao.commitTransaction();
		return liste;
	}

}
