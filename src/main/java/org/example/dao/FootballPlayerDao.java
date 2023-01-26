package org.example.dao;

import org.example.config.AppConfig;
import org.example.model.FootballPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FootballPlayerDao {

    private final static String URL = "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC";
    private final static String USERNAME = "bestuser";
    private final static String PASSWORD = "bestuser";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public FootballPlayer getPlayerById(int id) {
        FootballPlayer player = null;
        try {
            String sql = "SELECT * FROM football_player WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            player = new FootballPlayer();
            player.setId(resultSet.getInt("id"));
            player.setName(resultSet.getString("name"));
            player.setSurname(resultSet.getString("surname"));
            player.setRole_id(resultSet.getInt("role_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    public List<FootballPlayer> getPlayers() {
        List<FootballPlayer> players = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM football_player";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                FootballPlayer player = new FootballPlayer();
                player.setId(resultSet.getInt("id"));
                player.setName(resultSet.getString("name"));
                player.setSurname(resultSet.getString("surname"));
                player.setRole_id(resultSet.getInt("role_id"));
                players.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    public void addPlayer(FootballPlayer footballPlayer) {
        try {
            String sql = "INSERT INTO football_player (name, surname, role_id) "
            + "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, footballPlayer.getName());
            preparedStatement.setString(2, footballPlayer.getSurname());
            preparedStatement.setInt(3, footballPlayer.getRole_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlayerById(int id) {
        try {
            String sql = "DELETE FROM football_player where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
