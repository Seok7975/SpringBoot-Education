package myjpa2c;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember2 {
	/*
	순수 JPA를 사용할때 이정도의 코드가 있다라는것만 확인하고 넘어가면된다. 
	차후 Spring-Data-JPA로 넘어가면 아래 부분은 모두 추상화되어 직접 작성할 일이 없기때문이다. 
	 */
	public static void main(String[] args) {
		
		//persistence.xml에 지정된 이름을 통해 객체 생성 
		//이와같이 영속성을 생성한다. 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			//트랜젝션 시작
			transaction.begin();
			/*
			인수생성자를 통해 객체를 생성한 후 persist() 메서드를 호출하면
			테이블 생성 후 레코드도 하나 insert 된다. 
			 */
			Member2 member2 = new Member2("홍길동2", "1234");
			//영속성 개체로 Member2을 전달하여 추가. 
			em.persist(member2);
			//JPA가 오라클에 테이블을 생성하면서 동기화.
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			em.close();
		}
		
		emf.close();
	}
}
