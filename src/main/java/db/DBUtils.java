package db;

import beans.Comment;
import beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MHSL on 14.03.2017.
 */
public class DBUtils {
    
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, user_name, user_pass, email FROM users;")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("user_pass"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }
    
    public static int addUser(String name, String password, String email) {
        int result = 0;
        try (Connection connection = DataBase.getConnection();
             PreparedStatement usersStatement = connection.prepareStatement("INSERT INTO users(user_name, user_pass, email) VALUES(?, ?, ?)");
             PreparedStatement rolesStatement = connection.prepareStatement("INSERT INTO user_roles(user_name, role_name) VALUES(?, 'USER')")) {
            usersStatement.setString(1, name);
            usersStatement.setString(2, password);
            usersStatement.setString(3, email);
            rolesStatement.setString(1, name);
            result = usersStatement.executeUpdate();
            rolesStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static List<Comment> getCommentsForItem(int itemId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT comments.id, user_id, user_name, user_pass, email, comment, date, item_id " +
                                                        "FROM comments INNER JOIN users WHERE user_id = users.id AND item_id=" + itemId)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("user_pass"));
                user.setEmail(resultSet.getString("email"));
                
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setUser(user);
                comment.setText(resultSet.getString("comment"));
                comment.setDate(resultSet.getDate("date"));
                comment.setItemId(resultSet.getInt("item_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return comments;
    }
    
    public static void addCommentForItem(Comment comment) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO comments(user_id, comment, date, item_id) VALUES(?, ?, ?, ?)")) {
            statement.setInt(1, comment.getUser().getId());
            statement.setString(2, comment.getText());
            statement.setDate(3, comment.getDate());
            statement.setInt(4, comment.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void changeCommentForItem(Comment comment) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE comments SET comment = ? WHERE id = ?")) {
            statement.setString(1, comment.getText());
            statement.setInt(2, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteCommentForItem(Comment comment) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE id = ?")) {
            statement.setInt(1, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
