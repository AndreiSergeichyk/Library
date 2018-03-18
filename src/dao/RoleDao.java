package dao;

import entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();

    private static final String SAVE = "INSERT INTO library_storage.rols (name) VALUES (?)";

//    public void save(Role role) {
//        try (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, role.getName());
//
//            preparedStatement.executeUpdate();
//            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
//            if(generatedKey.next()){
//                role.setId(generatedKey.getInt("id"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
