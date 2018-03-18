package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:postgresql://localhost:5432/library");
        poolProperties.setUsername("postgres");
        poolProperties.setPassword("pass");
        poolProperties.setDriverClassName("org.postgresql.Driver");
        DataSource dataSource = new DataSource(poolProperties);

        return dataSource.getConnection();
    }
}
