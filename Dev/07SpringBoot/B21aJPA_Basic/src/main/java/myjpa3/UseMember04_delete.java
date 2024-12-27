package myjpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember04_delete {
	
	public static void main(String[] args) {
		
		//영속성 인스턴스 생성 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			//조건에 맞는 레코드 검색
			Member3 member3 = em.find(Member3.class, "hong@spring.com");
			if(member3 == null) {
				System.out.println("존재하지 않습니다.");
				transaction.rollback();
				return;
			}
			//레코드 삭제 및 동기화
			em.remove(member3);
			transaction.commit();
			System.out.println("삭제했습니다.");
		} 
		catch (Exception e) {
			transaction.rollback();
			throw e;
		}
		
		emf.close();
		em.close();
	}
}
