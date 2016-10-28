package controllers;

import models.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Asta on 2016-10-20.
 */
public class UsersController {
    private BasicDataSource dataSource;

    public UsersController() {
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
        String query = "CREATE TABLE `users` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(20) NOT NULL , " +
                "`email` VARCHAR(20) NOT NULL , `password` VARCHAR(20) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET utf8"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        String query = "DROP TABLE users"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int save(User a) {
        int status = 0;
        String query = "INSERT INTO users (name,password,email)" +
                " VALUES ('" + a.getName() + "', '" + a.getPassword() + "', '" + a.getEmail() + "')"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            status = statement.executeUpdate(query); // Įvykdome SQL užklausą
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean userExists(String email, String password) {
        String query = "SELECT * FROM users WHERE email = '" + email + "'" + " AND password = '" + password + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next() && resultSet != null) {      // resultSet != null
                return true;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User u = new User();
        String query = "SELECT * FROM users WHERE email = '" + email + "'" + " AND password = '" + password + "'"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {      // resultSet != null
                u.setName(resultSet.getString("name"));
                u.setEmail(resultSet.getString("email"));
                u.setId(resultSet.getInt("id"));
                u.setPassword("empty");   // Ar reikia pasiimti slaptazodi?
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User getUserById(int id) {
        User u = new User();
        String query = "SELECT * FROM users WHERE id = '" + id + "'"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {      // resultSet != null
                u.setName(resultSet.getString("name"));
                u.setEmail(resultSet.getString("email"));
                u.setId(resultSet.getInt("id"));
                u.setPassword("empty");   // Ar reikia pasiimti slaptazodi?
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }


}
