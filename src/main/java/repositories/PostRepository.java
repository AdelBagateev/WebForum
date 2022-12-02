package repositories;

import config.PostgresConnectionProvider;
import models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository {

    public void save(Post post) {
        // language=SQL
        String save = "insert into post_table (title, text, user_id) values (?, ?, ?)";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(save);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setLong(3, post.getUserId());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public List<Post> findAll() {

        // language=SQL
        String findAll = "select * from post_table";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<Post> list = new ArrayList<>();

            while (resultSet.next()) {
                Post post = Post.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .text(resultSet.getString("text"))
                        .userId(resultSet.getLong("user_id"))
                        .build();
                list.add(post);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public Optional<Post> findById(Long id) {
        // language=SQL
        String find = "select * from post_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(find);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Post post = Post.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .text(resultSet.getString("text"))
                        .userId(resultSet.getLong("user_id"))
                        .build();

                return Optional.of(post);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public void update(Post post) {
        // language=SQL
        String update = "update post_table set title = ?, text = ? where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setLong(3, post.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public void delete(Long id) {
        // language=SQL
        String delete = "delete from post_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
