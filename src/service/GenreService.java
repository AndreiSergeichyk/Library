package service;

import dao.GenreDao;
import entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GenreService {

    private static final GenreService INSTANCE = new GenreService();

    public List<Genre> allGenre(){
        return GenreDao.getInstance().allGenre();
    }

    public static GenreService getInstance() {
        return INSTANCE;
    }
}
