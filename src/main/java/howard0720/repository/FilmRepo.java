package howard0720.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import howard0720.model.Film;
import howard0720.model.Film$;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepo {

    @Inject
    JPAStreamer jpaStreamer;

    private static final int PAGE_SIZE = 20;

    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findFirst();
    }
    public Stream<Film> paged(long page, short minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength)) //過濾條件： 只保留 length > minLength 的影片。
                .sorted(Film$.length) //根據 length 由小到大排序
                .skip(page * PAGE_SIZE) //跳過筆數： 根據第幾頁跳過前面 N 筆資料。
                .limit(PAGE_SIZE); //限制筆數： 只保留這一頁的資料（每頁固定大小）。
    }
    public Stream<Film> pagedImprove(long page, short minLength) {
        return jpaStreamer.stream(Projection.select(Film$.filmId,Film$.title,Film$.length)) //只查詢特定欄位
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }

    public Stream<Film> actors(String startsWith, short minLength) {
        final StreamConfiguration<Film> sc =
                StreamConfiguration.of(Film.class).joining(Film$.actors);
        //設定 JPAStreamer 的查詢行為，明確指定要加入 Film → Actor 的關聯關係（join）
        //joining(...) 確保在查詢時fetch 演員列表，避免日後使用 film.getActors() 時出現 LazyLoadingException

        return jpaStreamer.stream(sc)
                .filter(Film$.title.startsWith(startsWith)
                        .and(Film$.length.greaterThan(minLength)))
                .sorted(Film$.length.reversed());
    }

}
