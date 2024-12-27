package myjpa4c;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Use04_Like {
	
	public static void main(String[] args) {
		
		//none으로 변경한 후 실행
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			String SQL = "SELECT m FROM Member4 m WHERE m.email like :email "
					+ " ORDER BY m.name";						
			TypedQuery<Member4> query = em.createQuery(SQL, Member4.class)
					.setParameter("email", "%spring.com%");
			List<Member4> result = query.getResultList();
			
			transaction.commit();

			if(result.isEmpty()) {
				System.out.println("레코드가 없습니다.");
			}
			else {

				result.forEach(user -> 
					System.out.printf("| %s | %s | %tY-%<tm-%<td |\n",
						user.getEmail(), user.getName(), user.getCreateDate()));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
				
		em.close();
		emf.close();
	}
}
