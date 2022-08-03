package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import moviebuddy.MovieBuddyFactory;

public class BeanScopeTest {
	@Test
	void Equals_MovieFinderBean() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);
		MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class);
		
		Assertions.assertEquals(movieFinder, applicationContext.getBean(MovieFinder.class));
		// 결과: pass. 따로 설정하지 않으면 bean은 singleton scope로 설정된다. 
	}
}
