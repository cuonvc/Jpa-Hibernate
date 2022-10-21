package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Person;
import org.hibernate.Session;
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

	@Override
	public void run(String... args) throws Exception {
		Session session = (Session) entityManager.getDelegate();

		session.beginTransaction();  //start transaction - connected to DB

		Person person = new Person("Test first", "Test last", 18, "test address");

		session.save(person);   // to persistent state

		session.getTransaction().commit();  //save to DB

		session.close();
	}
}
