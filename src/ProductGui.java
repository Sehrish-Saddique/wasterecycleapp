import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductGui extends JFrame implements ActionListener {
    private JButton addButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
 
    public ProductGui() {
        setTitle("Product CRUD Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel bottomNavBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomNavBar.setBackground(Color.LIGHT_GRAY);

        addButton = new JButton("Add Product");
        addButton.addActionListener(this);

        viewButton = new JButton("View Products");
        viewButton.addActionListener(this);

        updateButton = new JButton("Update Product");
        updateButton.addActionListener(this);

        deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(this);

        bottomNavBar.add(addButton);
        bottomNavBar.add(viewButton);
        bottomNavBar.add(updateButton);
        bottomNavBar.add(deleteButton);

        add(bottomNavBar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            openAddProductScreen();
        } else if (e.getSource() == viewButton) {
            openViewProductsScreen();
        } else if (e.getSource() == updateButton) {
            openUpdateProductScreen();
        } else if (e.getSource() == deleteButton) {
            openDeleteProductScreen();
        }
    }

    private void openAddProductScreen() {
        String productName = JOptionPane.showInputDialog("Enter product name:");
        String priceStr = JOptionPane.showInputDialog("Enter product price:");
        String desc = JOptionPane.showInputDialog("Enter product desc:");

        try {
            double price = Double.parseDouble(priceStr);
            Product product = new Product(desc, productName, price);
            boolean added = product.addProduct();
            if (added) {
                JOptionPane.showMessageDialog(this, "Product added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price format");
        }
    }

    private void openViewProductsScreen() {
    	  // Simulating a user ID for testing
    	
        // Fetch products sold by the user from the database
        List<Product> productList = Product.getProductsByUserId(Product.userId());

        if (!productList.isEmpty()) {
            StringBuilder productsInfo = new StringBuilder("Products sold by the user:\n");
            for (Product product : productList) {
                productsInfo.append(product.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, productsInfo.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No products found for the user");
        }
    }

    private void openUpdateProductScreen() {
        String productName = JOptionPane.showInputDialog("Enter updated product name:");
        String productdesc = JOptionPane.showInputDialog("Enter updated product Description:");
        String priceStr = JOptionPane.showInputDialog("Enter updated product price:");
        try {
            double price = Double.parseDouble(priceStr);
            Product product = new Product(productdesc, productName, price);
            boolean updated = product.updateProduct();
            if (updated) {
                JOptionPane.showMessageDialog(this, "Product updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price format");
        }
    }

    private void openDeleteProductScreen() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter product ID to delete:"));
        boolean deleted = Product.deleteProduct(productId);
        if (deleted) {
            JOptionPane.showMessageDialog(this, "Product deleted successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete product");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductGui::new);
    }
}
