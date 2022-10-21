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

		Person person = session.get(Person.class, 12);  //get person with id=10
		transaction.commit();  //commit to DB
		session.close();
		person.setAge(10);  //error vì session đã closed

		Session session1 = sessionFactory.openSession();
		Transaction transaction1 = session1.getTransaction();
		transaction1.begin();
		Person person1 = session1.get(Person.class, 12);
		person1.setFirstName("update 1");
		person1.setLastName("update 2");
		session1.merge(person);   //update lại dữ liệu từ session trước
		transaction1.commit();
		session1.close();

		//note: mặc dù person có id=12 được commit và close session ngay sau khi get lên persistent nhưng
		//trạng thái của chúng vẫn nằm trong persistent. Vậy nên từ lần mở sessiton tiếp theo, nếu muốn thay
		//đổi data thì chỉ cần dùng hàm merge thay cho update

//		session.delete(person);  //remove person from persistent state
//		session.save(person);   // to persistent state (update person on persistent state)
//
//		transaction.commit();  //commit to DB
//
//		session.close();
	}
}
