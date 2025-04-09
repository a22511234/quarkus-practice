package howard0720.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import howard0720.model.Film;
import howard0720.model.Film$;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class FilmRepo {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findFirst();
    }
}
