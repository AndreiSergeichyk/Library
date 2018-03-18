package dao;

import entity.Book;
import entity.Genre;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    private static String SAVE = "INSERT INTO library_storage.book (name, genre_id, page) " +
            "VALUES (?,?,?)";

    public void save(Book book) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getGenre().getId());
            preparedStatement.setInt(3, book.getPage());

            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                book.setId(generatedKey.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}
