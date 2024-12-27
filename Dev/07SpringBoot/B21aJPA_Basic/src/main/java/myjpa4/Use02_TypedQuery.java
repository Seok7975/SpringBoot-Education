package myjpa4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Use02_TypedQuery {
	
	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			/*
			TypedQuery 작성. 오라클의 테이블명인 JpaMember4가 아닌 엔티티인 Member4를 
			사용해서 작성한다. 영속성 인스턴스를 사용하기 때문. 
			 */
			String SQL = "SELECT m FROM Member4 m ORDER BY m.name";						
			TypedQuery<Member4> query = em.createQuery(SQL, Member4.class);
			List<Member4> result = query.getResultList();
			
			transaction.commit();

			if(result.isEmpty()) {
				System.out.println("레코드가 없습니다.");
			}
			else {
				/*
				%tY : t는 Date, Calendar, Long (밀리초), TemporalAccessor와 같은 날짜/시간 
					관련 객체를 포맷할 때 사용됩니다.
					Y는 4자리 연도로 출력하라는 의미입니다. 예를 들어, 2024년이라면 2024가 출력됩니다.
				-%<tm : %<t는 직전에 사용된 날짜/시간 변수를 다시 사용하라는 의미입니다.
					m은 2자리 월을 출력하라는 의미입니다. 예를 들어, 9월이면 09가 출력됩니다.
				-%<td : %<t는 또다시 직전에 사용된 날짜/시간 변수를 사용하라는 의미입니다.
					d는 2자리 날짜(일)를 출력하라는 의미입니다. 예를 들어, 1일이면 01이 출력됩니다.
				*/				
				result.forEach(user -> 
					System.out.printf("| %s | %s | %tY-%<tm-%<td |\n",
						user.getEmail(), user.getName(), user.getCreateDate()));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
				
		//실행전 xml설정파일에서 none으로 변경 	
		em.close();
		emf.close();
	}
}
