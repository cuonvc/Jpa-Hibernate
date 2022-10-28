package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Child;
import com.lean.jpahibernate.helloWorld.entity.Parent;
import com.lean.jpahibernate.helloWorld.entity.ParentPrimaryKey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
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
		Transaction transaction = session.getTransaction();
		transaction.begin();

		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey();
		parentPrimaryKey.setFirstname("Test firstname");
		parentPrimaryKey.setLastname("Test lastname");

		Parent parent = new Parent();
		parent.setParentPrimaryKey(parentPrimaryKey);

		Child child = new Child();
		child.setName("Test name");
		Child child1 = new Child();
		child1.setName("TRest 2");


		Set<Child> children = new HashSet<>();
		children.add(child);
		children.add(child1);

		parent.setChildren(children);

		session.persist(parent);
		transaction.commit();
		session.close();
	}
}
