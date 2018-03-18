package dao;

import entity.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryDao {

    private static final CountryDao INSTANCE = new CountryDao();

    private static String SAVE = "INSERT INTO library_storage.country (name) VALUES (?)";

    public void save(Country country) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, country.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                country.setId(generatedKey.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CountryDao getInstance() {
        return INSTANCE;
    }
}
