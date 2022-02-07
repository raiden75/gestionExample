package fr.formation.inti.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.inti.config.AppConfiguration;
import fr.formation.inti.entity.Employee;
import fr.formation.inti.service.EmployeeService;
import fr.formation.inti.service.IEmployeeService;

public class AppMain {

	private static final Log log = LogFactory.getLog(AppMain.class);

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//		IEmployeeService service = context.getBean("employeeService", EmployeeService.class);
//		
//		log.debug("--------- Bean Service" + service);
//
//		List<Employee> liste = service.findAll();
//
//		for (Employee e : liste) {
//			log.debug("---------" + e);
//		}
		
		context.close();
	}
}
