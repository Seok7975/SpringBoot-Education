package myjpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember03_update {
	
	public static void main(String[] args) {
		
		//영속성 인스턴스 생성 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			//조건에 맞는 레코드를 검색한다. 
			Member3 member3 = em.find(Member3.class, "hong@spring.com");
			if(member3 == null) {
				System.out.println("존재하지 않습니다.");
				transaction.rollback();
				return;
			}
			//메서드를 통해 영속성에서 이름을 변경한 후 커밋해서 데이터베이스와 동기화한다. 
			member3.changeName("전우치");
			transaction.commit();
			System.out.println("이름을 변경했습니다.");
		} 
		catch (Exception e) {
			transaction.rollback();
			throw e;
		}
		
		emf.close();
		em.close();
	}
}
