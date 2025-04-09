package repository;
import howard0720.model.Film;
import howard0720.repository.FilmRepo;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class FilmRepository {

    @Inject
    FilmRepo filmRepo;

    @Test
    public void getFilmTest() {
        Optional<Film> film = filmRepo.getFilm((short) 5);
        assertTrue(film.isPresent());
        assertEquals("AFRICAN EGG", film.get().getTitle());
    }
}
