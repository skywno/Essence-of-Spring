package moviebuddy.data;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.data.JaxbMovieReader;
import moviebuddy.domain.Movie;

@ActiveProfiles(MovieBuddyProfile.XML_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
public class JaxbMovieReaderTest {
	
//	@Autowired
	JaxbMovieReader movieReader;
	
	@Autowired
	JaxbMovieReaderTest(JaxbMovieReader movieReader) {
		this.movieReader = movieReader;
	}
	
	@Test
	void NotEMpty_LoadedMovies() {		
		List<Movie> movies = movieReader.loadMovies();
		Assertions.assertEquals(1375, movies.size());
		
	}
}
