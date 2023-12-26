 import java.util.ArrayList;
import java.util.List;

public class Product {
    private String desc;
    private String name;
    private double price;
    private static DatabaseModel database;

    private int  userId;
    // Constructor
    public Product(String desc, String name, double price) {
        this.desc = desc;
        this.name = name;
        this.price = price;
        Product.database = new DatabaseModel(); // Initialize DatabaseModel

    }
    private int product_id; // Assume product_id exists as a field

    // Other properties, constructor, and methods...

    // Setter method for product_id
    public void setId(int id) {
        this.product_id = id;
    }

    // Getter method for product_id
    public int getId() {
        return product_id;
    }
    // Getters and setters
    // (You can generate these using your IDE or write them manually)

    public String getdesc() {
        return desc;
    }

    public void setdesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method (for better printing of the Product object)
    @Override
    public String toString() {
        return "Product{" +
                "Desc=" + desc +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    

    public boolean addProduct() {
        return database.addProduct(this);
    }

    public boolean updateProduct() {
        return database.updateProduct(this);
    }

    public boolean deleteProduct() {
        return database.deleteProduct(1);
    }

	public static Product getProductById(int i) {
		// TODO Auto-generated method stub
		return database.getProductById(i);
	}

	public static boolean deleteProduct(int i) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static int userId() {
		return database.loggedInUserId("user1","password1");
	}
	 public static List<Product> getProductsByUserId(int userId) {
	        // Fetch the products sold by the user from the database using userId
	        // Construct and return a list of Product objects with fetched details
	        
	        // Placeholder code; replace it with your logic to fetch from the database
	        List<Product> productList = new ArrayList<>();
	        // Example: Fetching products sold by the user from the database
	        // Here, fetch products from the database based on userId and add them to the list
	        // This is a placeholder; replace it with your actual database retrieval logic
	        
	        // For example:
	        // productList = yourDatabaseHandler.fetchProductsByUserId(userId);
 	        // Returning a placeholder list of products
	     //   productList.add(new Product("desc", "Product 1", 20.0));
	     //   productList.add(new Product("", "Product 2", 30.0));
	        database.queryprocess(productList, userId);

	        // Add more products retrieved from the database
	        
	        return productList;
	    }

     

}
