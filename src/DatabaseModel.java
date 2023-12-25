import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseModel {
    private Connection connection;

    public DatabaseModel() {
        try {
            // Establishing the database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wasterecycledb?serverTimezone=UTC", "root", "root");
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyLogin(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Returns true if the user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean registerUser(String username, String password, String email) {
        try {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean buyProduct(int productId, int userId) {
        try {
            // Simplified logic to update user's purchased products
            String query = "INSERT INTO purchased_products (user_id, product_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sellProduct(Product product, int userId) {
        try {
            // Simplified logic to add product to the selling list
            String query = "INSERT INTO selling_products (user_id, product_id, price) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, product.getId());
            statement.setDouble(3, product.getPrice());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
