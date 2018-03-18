package dao;

import entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GenreDao {

    private static final GenreDao INSTANCE = new GenreDao();

    private static String SAVE = "INSERT INTO library_storage.genre(name) VALUES (?)";

    private static String ALL_GENRE = "SELECT id, name FROM library_storage.genre";

    public void save(Genre genre) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, genre.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                genre.setId(generatedKey.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Genre> allGenre() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_GENRE)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Genre genre = Genre.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    public static GenreDao getInstance() {
        return INSTANCE;
    }
}
