package com.lean.jpahibernate;

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

		Transaction transaction = session.getTransaction();  //initial transaction

		transaction.begin();  //start transaction - connected to DB

		Person person = session.get(Person.class, 10);  //get person with id=10
		person.setFirstName("test first update");
		person.setLastName("test last update");

//		session.delete(person);  //remove person from persistent state
		session.save(person);   // to persistent state (update person on persistent state)

		transaction.commit();  //commit to DB

		session.close();
	}
}
