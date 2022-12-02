package listeners;

import config.PostgresConnectionProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import services.PostService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private static HttpSession session;


    public static HttpSession getSession() {
        return session;
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // language=SQL
        String createUser = "create table if not exists user_table (" +
                "id bigserial primary key ," +
                "name varchar(20)," +
                "password varchar(200)," +
                "role varchar(20) default 'user')";

        // language=SQL
        String createPost = "create table if not exists post_table (" +
                "id bigserial primary key ," +
                "title varchar(1000)," +
                "text varchar(2000)," +
                "user_id bigint references user_table(id))";

        // language=SQL
        String createComments = "create table if not exists comments_table (" +
                "id bigserial primary key ," +
                "text varchar(2000)," +
                "post_id bigint references post_table(id)," +
                "user_id bigint references user_table(id))";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createUser);
            statement.execute();

            statement = connection.prepareStatement(createPost);
            statement.execute();

            statement = connection.prepareStatement(createComments);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        session = se.getSession();

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
