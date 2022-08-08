package moviebuddy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import moviebuddy.cache.CachingAdvice;
import moviebuddy.data.CachingMovieReader;
import moviebuddy.data.CsvMovieReader;
import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;


@Configuration
@PropertySource("/application.properties")
@ComponentScan(basePackages= {"moviebuddy"})
@Import({MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class})
public class MovieBuddyFactory {
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("moviebuddy");
		
		return marshaller;
	}
	
	
	@Bean
	public CacheManager caffeineCacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS));
		
		return cacheManager;
	}
	
//	@Bean
//	public MovieFinder movieFinder() {
//		return new MovieFinder(new CsvMovieReader());
//	}
//	

//	1. 메서드 호출
//	@Bean 
//	public MovieFinder movieFinder() {
//		return new MovieFinder(movieReader()); 
//	}
	
//	2. 메서드 파라미터 방식:
//	스프링 컨테이너는 이렇게 클래스로 자바코드로 작성되어 있는 빈 구성 정보를 읽어들여서 어떻게 빈을 생성할지 스스로 판단할 수 있는데
//	MovieFinder 빈을 등록하려고 method의 parameter로 보고 MovieReader 빈이 필요한 것을 보고 이것이 등록되어 있는 지 찾는다. 
//	그리고 이것을 파라미터로 넘겨준다. 

	@Configuration
	static class DomainModuleConfig {
		
//		@Bean 
////		@Scope("prototype")
////		@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//		public MovieFinder movieFinder(MovieReader movieReader) {
//			return new MovieFinder(movieReader); 
//		}
		
		
	}
	
	@Configuration
	static class DataSourceModuleConfig {
		
//		@Primary
//		@Bean 
//		public MovieReader cachingMovieReader(CacheManager cacheManager, MovieReader target) {
//			return new CachingMovieReader(cacheManager, target);
//		}
		
		@Primary
		@Bean
		public ProxyFactoryBean cachingMovieReaderFactory(ApplicationContext applicationContext) { 
			MovieReader target = applicationContext.getBean(MovieReader.class);
			CacheManager cacheManager = applicationContext.getBean(CacheManager.class);
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			proxyFactoryBean.setTarget(target);
			// 클래스 프락시 활성화(true)/ 비활성화(false, 기본값) 
//			proxyFactoryBean.setProxyTargetClass(true);
			proxyFactoryBean.addAdvice(new CachingAdvice(cacheManager));
			
			return proxyFactoryBean;
		}
		
//		@Bean
//		public CsvMovieReader csvMovieReader() {
//			CacheManager cacheManager = new CaffeineCacheManager();
//			return new CsvMovieReader(cacheManager);
//		}
	}
}
