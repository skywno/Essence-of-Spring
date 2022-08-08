package moviebuddy.data;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.core.io.DefaultResourceLoader;

public class CsvMovieReaderTest {

	@Test
	void Valid_Metadata() throws Exception {
		CsvMovieReader movieReader = new CsvMovieReader(new NoOpCacheManager());
		movieReader.setMetadata("movie_metadata.csv");
		movieReader.setResourceLoader(new DefaultResourceLoader());
		movieReader.afterPropertiesSet();
	}
	

	@Test
	void Invalid_Metadata() throws Exception {
		CsvMovieReader movieReader = new CsvMovieReader(new NoOpCacheManager());
		movieReader.setResourceLoader(new DefaultResourceLoader());

		Assertions.assertThrows(FileNotFoundException.class, () -> {
			movieReader.setMetadata("movie_metadata.txt");
			movieReader.afterPropertiesSet();
		}); 
	}
}
