package dao;

import entity.BlackList;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BlackListDao {

    private static final BlackListDao INSTANCE = new BlackListDao();

    private static String SAVE = "INSERT INTO library_storage.black_list(user_id) VALUES (?)";

    public void save(BlackList blackList, User user) {
        PreparedStatement blackListStatement = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setLong(1, user.getId());

            blackListStatement.executeUpdate();
            ResultSet generatedKeys = blackListStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                blackList.setId(generatedKeys.getLong("id"));
            }
            blackList.getUsers().add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BlackListDao getInstance() {
        return INSTANCE;
    }
}
