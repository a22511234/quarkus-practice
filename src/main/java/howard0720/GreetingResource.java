package howard0720;

import howard0720.model.Film;
import howard0720.repository.FilmRepo;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.stream.Collectors;

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

    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String paged(long page, short minLength) {
        return filmRepo.paged(page, minLength)
                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining("\n"));
    }
    @GET
    @Path("/pagedFilms/v2/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pagedImprove(long page, short minLength) {
        return filmRepo.pagedImprove(page, minLength)
                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining("\n"));
    }
    //根據 URL 中的 {page} 和 {minLength} 參數做查詢。
    //從 filmRepo.paged(...) 取得分頁後的 Film 物件。
    //把每筆電影資料轉成文字格式 Title (Length min)。
    //把所有結果用換行符號 \n 串接成一段字串。｀
    //回傳為 text/plain 格式給使用者。

    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String actors(String startsWith, short minLength) {
        return filmRepo.actors(startsWith, minLength)
                .map(f -> String.format("%s (%d min): %s",
                        f.getTitle(),
                        f.getLength(),
                        f.getActors().stream()
                                .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                                //每部電影的演員列表，並轉換每個演員的名字為 firstName lastName 格式。
                                .collect(Collectors.joining(", "))))
                .collect(Collectors.joining("\n"));
    }

}
