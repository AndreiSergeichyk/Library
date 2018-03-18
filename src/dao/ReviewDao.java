package dao;

import entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReviewDao {

    private static final ReviewDao INSTANCE = new ReviewDao();

    private static String SAVE = "INSERT INTO library_storage.review(book_id, user_id, text_coment) VALUES (?,?,?)";

    public void save(Review review) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setLong(1, review.getBooks().getId());
            preparedStatement.setLong(2, review.getUser().getId());
            preparedStatement.setString(3, review.getTextReviews());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
