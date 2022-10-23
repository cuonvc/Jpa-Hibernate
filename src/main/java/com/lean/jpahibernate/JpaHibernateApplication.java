package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Person;
import com.lean.jpahibernate.helloWorld.entity.Shoes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

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

//		Person person = new Person();
//		person.setAge(18);
//		person.setFirstName("Cuong 1");

//		Person person = session.get(Person.class, 4);
//		Shoes shoes = new Shoes();
//		shoes.setHeight(11.2f);
//		shoes.setColor("red");
//		shoes.setPerson(person);
//
//		session.persist(shoes);  //save Person and Shoes to persistent status

		//case 2:
		Shoes shoes = session.get(Shoes.class, 7);
		session.delete(shoes);  //success if CascadeType.REMOVE apply for Shoes(many side) and orphanRemove apply for Person(one side)
		//logic SQL: deleted Shoes (id = 5) -> deleted all remaining Shoes (person_id = Shoes(id=5)) -> deleted Person

		transaction.commit();
		session.close();
	}
}
