package repositories;

import config.PostgresConnectionProvider;
import models.Comment;
import models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CommentsRepository {
    public void save(Comment comment) {
        // language=SQL
        String insert = "insert into comments_table (text, post_id, user_id) values (?,?,?)";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, comment.getText());
            statement.setLong(2, comment.getPostID());
            statement.setLong(3, comment.getUserID());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public List<Comment> findAll() {
        // language=SQL
        String findAll = "select * from comments_table";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);

            ResultSet set = statement.executeQuery();
            List<Comment> list = new LinkedList<>();

            while (set.next()) {
                Comment comment = Comment.builder()
                        .id(set.getLong("id"))
                        .text(set.getString("text"))
                        .postID(set.getLong("post_id"))
                        .userID(set.getLong("user_id"))
                        .build();

                list.add(comment);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        // language=SQL
        String delete = "delete from comments_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
