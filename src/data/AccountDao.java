package data;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private Connection c;

    public AccountDao() {
        c = null;
    }

    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            c = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM account";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String username = rs.getString("username");
                String cardId = rs.getString("cardId");
                String pin = rs.getString("pin");
                double currentBalaance = rs.getDouble("currentBalance");
                boolean isLocked = rs.getBoolean("isLocked");
                accounts.add(new Account(username, cardId, pin, currentBalaance, isLocked));
            }
            DatabaseConnection.closeConnection(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public Account getByCardId(String cardId) {
        Account account = null;
        try {
            c = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM account" +
                    " WHERE cardId = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, cardId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String username = rs.getString("username");
                String pin = rs.getString("pin");
                double currentBalaance = rs.getDouble("currentBalance");
                boolean isLocked = rs.getBoolean("isLocked");
                account = new Account(username, cardId, pin, currentBalaance, isLocked);
            }
            DatabaseConnection.closeConnection(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public int updateCurrentBalance(String cardId, double newBalance) {
        int result = 0;
        try {
            c = DatabaseConnection.getConnection();
            String sql = "UPDATE account" +
                    " SET currentBalance = ?" +
                    " WHERE cardId = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setDouble(1, newBalance);
            stmt.setString(2, cardId);
            result = stmt.executeUpdate();
            DatabaseConnection.closeConnection(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int lockCard(String cardId) {
        int result = 0;
        try {
            c = DatabaseConnection.getConnection();
            String sql = "UPDATE account" +
                    " SET isLocked = ?" +
                    " WHERE cardId = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, cardId);
            result = stmt.executeUpdate();
            DatabaseConnection.closeConnection(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
