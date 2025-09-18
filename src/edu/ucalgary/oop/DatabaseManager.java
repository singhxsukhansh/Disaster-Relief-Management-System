package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private Connection connection;

    public DatabaseManager(String dbUrl, String dbUser, String dbPassword) {
        if (dbUrl == null || dbUser == null || dbPassword == null ||
            dbUrl.trim().isEmpty() || dbUser.trim().isEmpty() || dbPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Database parameters cannot be null or empty.");
        }

        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            ErrorLogger.logError("Database connection failed: " + e.getMessage());
        }
    }

    public List<String> loadVictimNames() {
        List<String> names = new ArrayList<>();
        String query = "SELECT first_name, last_name FROM person";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                names.add(firstName + (lastName != null ? " " + lastName : ""));
            }
        } catch (SQLException e) {
            ErrorLogger.logError("Error loading victim names: " + e.getMessage());
        }

        return names;
    }

    public boolean updateVictimComment(int victimId, String comment) {
        String query = "UPDATE person SET comments = ? WHERE person_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, comment);
            pstmt.setInt(2, victimId);
            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            ErrorLogger.logError("Error updating victim comment: " + e.getMessage());
            return false;
        }
    }

    public void saveSupply(int supplyId, String type, int quantity) {
        String query = "INSERT INTO supplies (supply_id, type, quantity) VALUES (?, ?, ?) " +
                       "ON CONFLICT (supply_id) DO UPDATE SET type = EXCLUDED.type, quantity = EXCLUDED.quantity";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, supplyId);
            pstmt.setString(2, type);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ErrorLogger.logError("Error saving supply: " + e.getMessage());
        }
    }

    public boolean saveInquiry(int victimId, String infoProvided) {
        String checkPersonQuery = "SELECT person_id FROM person WHERE person_id = ?";
        String insertQuery = "INSERT INTO inquiry (inquirer_id, seeking_id, location_id, date_of_inquiry, comments) " +
                             "VALUES (?, ?, ?, ?, ?)";

        try (
            PreparedStatement checkPerson = connection.prepareStatement(checkPersonQuery);
            PreparedStatement insert = connection.prepareStatement(insertQuery)
        ) {
            // Check if victim exists
            checkPerson.setInt(1, victimId);
            ResultSet victimResult = checkPerson.executeQuery();
            if (!victimResult.next()) {
                ErrorLogger.logError("Victim ID " + victimId + " does not exist.");
                return false;
            }

            // Check if inquirer ID 1 exists
            checkPerson.setInt(1, 1);
            ResultSet inquirerResult = checkPerson.executeQuery();
            if (!inquirerResult.next()) {
                ErrorLogger.logError("Inquirer ID 1 does not exist.");
                return false;
            }

            // Insert inquiry
            insert.setInt(1, 1);  // inquirer_id (default)
            insert.setInt(2, victimId); // seeking_id
            insert.setInt(3, 1);  // dummy location_id
            insert.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // date_of_inquiry
            insert.setString(5, infoProvided); // comments

            int insertedRows = insert.executeUpdate();
            return insertedRows > 0;

        } catch (SQLException e) {
            ErrorLogger.logError("Error saving inquiry: " + e.getMessage());
            return false;
        }
    }

    public List<String> getAllSupplies() {
        List<String> supplies = new ArrayList<>();
        String query = "SELECT supply_id, type, quantity FROM supplies";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("supply_id");
                String type = rs.getString("type");
                int qty = rs.getInt("quantity");
                supplies.add("ID: " + id + " | Type: " + type + " | Qty: " + qty);
            }
        } catch (SQLException e) {
            ErrorLogger.logError("Error retrieving supplies: " + e.getMessage());
        }

        return supplies;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            ErrorLogger.logError("Error closing database connection: " + e.getMessage());
        }
    }
}
