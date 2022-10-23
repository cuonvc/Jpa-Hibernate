package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Address;
import com.lean.jpahibernate.helloWorld.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

//		transaction.begin();

		Address homeAddress = new Address();
		homeAddress.setStreet("new street 1");
		homeAddress.setCity("new city 1");

		Address billingAddress = new Address();
		billingAddress.setStreet("new billing street");
		billingAddress.setCity("new billing city");

		Person person = new Person();
		person.setAge(18);
		person.setFirstName("Cuong 1");
		person.setHomeAddress(homeAddress);
		person.setBillingAddress(billingAddress);

		session.save(person);
		transaction.commit();
		session.close();
	}
}
