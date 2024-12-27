package temp;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/* 
설정파일을 기본패키지가 아닌 다른 패키지에 생성하는 경우에는 자동으로
빈이 생성되지 않는다. 반드시 기본패키지 하위에 생성해야 한다. 
*/
@Configuration
public class PropertyConfig {
	
	/* name속성을 지정하였으므로 myprops라는 이름으로 빈이 생성된다. */
	@Bean(name="myprops")
	public PropertiesFactoryBean propertiesFactoryBean() {
		//PropertiesFactoryBean 객체를 반환하는 메서드로 정의 
		PropertiesFactoryBean propertiesFactoryBean = 
				new PropertiesFactoryBean();
		//사용자 정의 프로퍼티 파일의 위치를 지정 
		ClassPathResource classPathResource = 
			new ClassPathResource("my.properties");
		//경로를 등록한 후 인스턴스를 반환 
		propertiesFactoryBean.setLocation(classPathResource);
		return propertiesFactoryBean;
	}
}

