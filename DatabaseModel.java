import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseModel {
	public DatabaseModel{
		 try {
	            // Establishing the database connection
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wasterecycledb?serverTimezone=UTC", "root", "root");
	            System.out.println("Connected to the database!");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	}
}
