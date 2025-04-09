package howard0720;

import howard0720.model.Film;
import howard0720.repository.FilmRepo;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;

@Path("/")
public class GreetingResource {
    @Inject
    FilmRepo filmRepo;

    @GET
    @Path("/helloworld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(short filmId) {
        Optional<Film> film = filmRepo.getFilm(filmId);
        return film.isPresent() ? film.get().getTitle() : "No film was found!";
    }


}
