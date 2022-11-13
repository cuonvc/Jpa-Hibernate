package com.lean.jpahibernate;

import com.lean.jpahibernate.helloWorld.entity.Guide;
import com.lean.jpahibernate.helloWorld.entity.Guide_;
import com.lean.jpahibernate.helloWorld.entity.Student;
import com.lean.jpahibernate.helloWorld.entity.Student_;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Transactional
@Slf4j
public class JpaHibernateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Autowired
	private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
//
//		Student student = new Student();
//		student.setName("hahahah");
//		student.setEnrollmentId("idhheheheheh");
//
//		Student student1 = new Student();
//		student1.setEnrollmentId("idhhahahaha");
//		student1.setName("hehehehehe");
//
//		Set<Student> students = new HashSet<>();
//		students.add(student1);
//		students.add(student);
//		Guide guide = new Guide();
//		guide.setName("name thu hai");
//		guide.setSalary(6969696L);
//		guide.setStudents(students);
//
//		session.persist(guide);

		Student student =  entityManager.find(Student.class, 2);
		student.setName("New Name 1");
		Query query = entityManager.createQuery("select s.name from Student s where s.id = :id").setParameter("id", 2);
		entityManager.setFlushMode(FlushModeType.COMMIT);
		String name = (String) query.getSingleResult(); //result is old value (not "New Name 1")
		log.info("Student's name after changed: {}", name);

		transaction.commit();
		//after commited -> Student's name is "New Name 1";
		session.close();

	}
}
