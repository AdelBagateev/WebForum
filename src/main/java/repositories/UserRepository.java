package repositories;

import config.PostgresConnectionProvider;
import models.Post;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public List<User> findAll() {
        // language=SQL
        String findAll = "select * from user_table";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<User> resultList = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .build();

                resultList.add(user);
            }

            return resultList;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(User user) {
        // language=SQL
        String save = "insert into user_table (name, password) values (?, ?)";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(save);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<User> findById(Long id) {
        // language=SQL
        String find = "select * from user_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(find);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            User user;
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .build();

                return Optional.of(user);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
