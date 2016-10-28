package controllers;

import models.Article;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asta on 2016-10-17.
 */
public class ArticlesController {

    private BasicDataSource dataSource;

    public ArticlesController() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/blogdb"); // Nusirodome, IP, port'ą ir duombazės pavadinimą
        dataSource.setMaxIdle(1);
        dataSource.setInitialSize(1);
        dataSource.setValidationQuery("SELECT 1");
    }

    public void createTable(String name) {
        String query = "CREATE TABLE " + name
                + " (id INTEGER NOT NULL, title VARCHAR(255), body VARCHAR(10000), created_at DATE, updated_at DATA, PRIMARY KEY (id))"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String name) {
        String query = "DROP TABLE " + name; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getAllArticles() {
        String query = "SELECT id, title, body, created_at, updated_at, userID FROM articles";
        List<Article> list = new ArrayList<Article>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) { // ResultSet klasės objektas saugo duomenis iš duombazės

            while (resultSet.next()) {
                Article a = new Article();
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setUserId(resultSet.getInt("userId"));
                list.add(a);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int save(Article a) {
        int status = 0;
        String query = "INSERT INTO articles (title,body,created_at,updated_at,userId)" +
                " VALUES ('" + a.getTitle() + "', '" + a.getBody() + "', '" + a.getCreated_at() + "', '" + a.getUpdated_at() + "', '" + a.getUserId() + "')"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            status = statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public int delete(int id) {
        int status = 0;
        String query = "DELETE FROM articles WHERE id=" + id; // SQL užklausa
        try (Connection connection = dataSource.getConnection(); // Connection sukuria ryšį su duomenų baze
             Statement statement = connection.createStatement()) { // Statement objektas turi metodus kurie leidžia siųsti ir vykdyti užklausas

            status = statement.executeUpdate(query); // Statement klasės objekto pagalba vykdome užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Atspausdiną išsamų klaidos aprašymą.
        }
        return status;
    }

    public int update(Article a) {
        int status = 0;
        String query = "UPDATE articles SET title = '" + a.getTitle() + "', body = '" + a.getBody() + "', updated_at = '" +
                a.getUpdated_at() + "' WHERE ID = " + a.getId();
        // System.out.println(query);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            status = statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public Article getArticleById(int id) {
        Article a = new Article();
        String query = "SELECT * FROM articles WHERE ID = " + id; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setBody(resultSet.getString("body"));
                a.setCreated_at(resultSet.getString("created_at"));
                a.setUpdated_at(resultSet.getString("updated_at"));
                a.setUserId(resultSet.getInt("userId"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public List<Article> getAllArticlesIdByUserId(int userId) {
        String query = "SELECT id, title, body, created_at, updated_at FROM articles WHERE userID = '" + userId + "'";
        List<Article> list = new ArrayList<Article>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) { // ResultSet klasės objektas saugo duomenis iš duombazės

            while (resultSet.next()) {
                Article a = new Article();
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setBody(resultSet.getString("body"));
                list.add(a);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
