package moviebuddy.data;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.data.XmlMovieReader;
import moviebuddy.domain.Movie;

@ActiveProfiles(MovieBuddyProfile.XML_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
public class XmlMovieReaderTest {
	
//	@Autowired
	XmlMovieReader movieReader;
	
	@Autowired
	XmlMovieReaderTest(XmlMovieReader movieReader) {
		this.movieReader = movieReader;
	}
	
	@Test
	void NotEMpty_LoadedMovies() {		
		List<Movie> movies = movieReader.loadMovies();
		Assertions.assertEquals(1375, movies.size());
		
	}
}
