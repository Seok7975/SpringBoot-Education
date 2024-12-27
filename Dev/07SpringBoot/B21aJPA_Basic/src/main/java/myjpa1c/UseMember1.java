package myjpa1c;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember1 {
	/*
	순수 JPA를 사용할때 이정도의 코드가 있다라는것만 확인하고 넘어가면된다. 
	차후 Spring-Data-JPA로 넘어가면 아래 부분은 모두 추상화되어 직접 작성할 일이 없기때문이다. 
	 */
	public static void main(String[] args) {
		
		//이와같이 영속성을 생성한다. 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			//트랜젝션 시작 
			transaction.begin();
			//데이터 입력을 위한 인스턴스 생성 
			//테이블 생성 및 레코드 입력
			/* 클래스를 통해 객체를 생성하면 테이블이 생성되고, 멤버변수를 초기화를 통해 레코드가
			삽입된다. */
			Member1 member1 = new Member1("홍길동1", LocalDate.now());
			//영속성 개체로 Member1을 전달하여 추가. 
			em.persist(member1);
			//JPA가 오라클에 테이블을 생성하면서 동기화.
			//트랜잭션을 통한 커밋(여기서 데이터베이스에 insert 된다.)
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			//트랜잭션을 통한 롤백
			transaction.rollback();
		}
		finally {
			//엔티티메니져 자원해제
			em.close();
		}
		
		//팩토리 자원해제 
		emf.close();
	}
}
