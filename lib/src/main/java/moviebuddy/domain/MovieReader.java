package moviebuddy.domain;

import java.util.List;

import javax.cache.annotation.CacheResult;

import org.springframework.stereotype.Component;

public interface MovieReader {
	
    @CacheResult(cacheName="movies")
	List<Movie> loadMovies(); 
}
