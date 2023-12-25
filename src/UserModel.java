public class UserModel {
    private DatabaseModel database;

    public UserModel() {
        this.database = new DatabaseModel();
    }

    public boolean loginUser(String username, String password) {
        return database.verifyLogin(username, password);
    }
    public boolean registerUser(String username, String password, String email) {
        return database.registerUser(username, password, email);
    }
    public boolean buyProduct(int productId, int userId) {
        return database.buyProduct(productId, userId);
    }

    public boolean sellProduct(Product product, int userId) {
        return database.sellProduct(product, userId);
    }
    public void closeDatabaseConnection() {
        database.closeConnection();
    }
}
