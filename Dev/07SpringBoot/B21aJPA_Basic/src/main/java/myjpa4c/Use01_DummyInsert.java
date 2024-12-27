package myjpa4c;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Use01_DummyInsert {
	
	public static void main(String[] args) {
		
		//영속성 인스턴스 생성 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			//insert 처리 
			Member4 member4;
			member4 = new Member4("test1@spring.com", "홍길동", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test2@spring.com", "이순신", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test3@spring.com", "세종대왕", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test4@spring.com", "강감찬", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test5@spring.com", "을지문덕", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test6@spring.com", "정조대왕", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test7@spring.com", "신사임당", LocalDate.now());
			em.persist(member4);
			member4 = new Member4("test8@spring.com", "선덕여왕", LocalDate.now());
			em.persist(member4);
			
			transaction.commit();
			System.out.println("입력이 완료되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			em.close();
		}
		
		//실행전 xml설정파일에서 create로 변경 		
		emf.close();
	}
}
