package moviebuddy.domain;

import java.util.List;

import org.springframework.stereotype.Component;

public interface MovieReader {
	
	List<Movie> loadMovies(); 
}
