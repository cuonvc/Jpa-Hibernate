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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

		Collection<String> collection = new ArrayList<>();
		collection.add("first nickname");
		collection.add("second nickname");

		Person person = new Person();
		person.setName("Name test");
		person.setEmail("email test");
		person.setNicknames(collection);

		Address address = new Address();
		address.setZipcode("1001");
		address.setStreet("new street");
		address.setCity("new city");

		Address address1 = new Address();
		address1.setZipcode("1231");
		address1.setStreet("new Street");
		address1.setCity("city test");

		person.getAddresses().add(address);
		person.getAddresses().add(address1);

		session.persist(person);
		transaction.commit();
		session.close();
	}
}
