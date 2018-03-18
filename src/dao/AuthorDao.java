package dao;

import entity.Author;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthorDao {
    private static final AuthorDao INSTANCE = new AuthorDao();

    private static String SAVE = "INSERT INTO library_storage.author (name, country_id, date_of_birds) VALUES (?,?,?)";

    public void save(Author author) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setInt(2, author.getCountry().getId());
            //preparedStatement.setString(3, author.getData());

            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                author.setId(generatedKey.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AuthorDao getInstance() {
        return INSTANCE;
    }
}
