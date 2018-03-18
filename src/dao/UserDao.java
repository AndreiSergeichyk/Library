package dao;

import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE = "INSERT INTO library_storage.uzer (name, password, addres_mailbox, rols_id)\n" +
            "VALUES (?, ?, ?, ?)";

    private static final String LOGIN = "SELECT u.id, u.name, u.password, u.addres_mailbox, r.id AS role_id, r.name AS role_name " +
            "FROM library_storage.uzer u " +
            "INNER JOIN library_storage.rols r ON u.rols_id = r.id " +
            "WHERE u.name=? AND u.password=?";

    public User save(User user) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAddresMailBox());
            preparedStatement.setInt(4, user.getRols().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public User login(String name, String password) {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .addresMailBox(resultSet.getString("addres_mailbox"))
                        .rols(Role.builder()
                                .id(resultSet.getInt("role_id"))
                                .name(resultSet.getString("role_name"))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
