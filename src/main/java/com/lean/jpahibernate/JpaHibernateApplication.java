package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Employee;
import com.lean.jpahibernate.helloWorld.entity.EmployeeStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void run(String... args) throws Exception {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Employee employee = new Employee();
		employee.setEmployeeId("1231mkmesda");
		employee.setName("Test");
		employee.setEmployeeStatus(EmployeeStatus.PART_TIME);

		Employee employee1 = new Employee();
		employee1.setEmployeeId("12njkn2242");
		employee1.setName("demo");
		employee1.setEmployeeStatus(EmployeeStatus.FULL_TIME);

		session.save(employee);
		session.save(employee1);

		transaction.commit();
		session.close();

	}
}
