package controllers;

import models.Comment;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asta on 2016-10-21.
 */
public class CommentsController {
    private BasicDataSource dataSource;

    public CommentsController() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/blogdb"); // Nusirodome, IP, port'ą ir duombazės pavadinimą
        dataSource.setMaxIdle(1);
        dataSource.setInitialSize(1);
        dataSource.setValidationQuery("SELECT 1");
    }

    public void createTable() {
        String query = "CREATE TABLE `comments` (`id` INT(5) NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) " +
                "CHARACTER SET latin1 NOT NULL, `body` VARCHAR(600) CHARACTER SET latin1 NOT NULL, `parentID` " +
                "INT(11) NOT NULL, PRIMARY KEY (`id`), KEY `parentID` (`parentID`), CONSTRAINT `comments_ibfk_1` " +
                "FOREIGN KEY (`parentID`) REFERENCES `articles` (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_c"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        String query = "DROP TABLE comments"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int save(Comment c) {
        int status = 0;
        String query = "INSERT INTO comments (name, body, parentID)" +
                " VALUES ('" + c.getName() + "', '" + c.getBody() + "', '" + c.getParentId() + "')"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            status = statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Comment> getAllCommentsByParentId(int articleId) {
        String query = "SELECT id, name, body FROM comments WHERE parentID = '" + articleId + "'";
        List<Comment> list = new ArrayList<Comment>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) { // ResultSet klasės objektas saugo duomenis iš duombazės

            while (resultSet.next()) {
                Comment c = new Comment();
                //c.setId(resultSet.getInt("ID"));
                c.setName(resultSet.getString("name"));
                c.setBody(resultSet.getString("body"));
                //c.setParentId(articleId);
                list.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
