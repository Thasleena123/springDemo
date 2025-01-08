package Bookingapp.bookingapp.DAO;

import Bookingapp.bookingapp.Entity.Theater;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TheaterRepo {
    static final String DB_URL = "jdbc:mysql://localhost:3306/springDemo";
    static final String USER = "root";
    static final String PASS = "password";
    Connection conn = null;

    public TheaterRepo() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public List<Theater> getAllTheaters() {
        String sql = "select * from theater";
        List<Theater> theaters = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Theater theater = new Theater();
                theater.setId(rs.getInt(1));
                theater.setName(rs.getString(2));
                theater.setLocation(rs.getString(3));
                theaters.add(theater);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return theaters;
    }

    public Theater getTheaterById(int id) {
        String sql = "select * from theater where id =?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Theater theater = new Theater();
                theater.setId(rs.getInt(1));
                theater.setName(rs.getString(2));
                theater.setLocation(rs.getString(3));
                return theater;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String deleteTheatreById(int id) {
        String sql = "delete from theater where id =?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                return "Successfully deleted";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed To Delete";
    }

    public String addTheater(Theater theater) {
        String sql = "insert into theater (Name, Location) values (?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, theater.getName());
            preparedStatement.setString(2, theater.getLocation());
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                return "Successfully added";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed To add";
    }

    public String updateTheater(Theater theater, int id) {
        String sql = "update theater set name= ?,location=? where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, theater.getName());
            preparedStatement.setString(2, theater.getLocation());
            preparedStatement.setInt(3, id);
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                return "Successfully updated";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Failed To updated";
    }
}


