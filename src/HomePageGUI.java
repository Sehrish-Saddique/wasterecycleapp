import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HomePageGUI {

    private final String[] itemImageNames = {
            "src/images/1.png", "src/images/2.png", "src/images/3.png",
            "src/images/4.png", "src/images/5.png", "src/images/6.png"
    };

    private final JPanel content;

    public HomePageGUI() {
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 800);

        // Navbar
        JPanel navBar = createNavBar();
        frame.add(navBar, BorderLayout.NORTH);

        // Content
        content = createContent();
        JScrollPane scrollPane = new JScrollPane(content);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Bar
        JPanel bottomBar = createBottomBar();
        frame.add(bottomBar, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(34, 139, 34)); // Dark green background color

        // Logo icon
        ImageIcon logoIcon = new ImageIcon("src/images/logo.jpg");
        logoIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);

        // Title label
        JLabel titleLabel = new JLabel("Waste Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        // Search bar
        JTextField searchBar = new JTextField(15); // Increased the size of the input bar
        searchBar.setForeground(Color.BLACK);
        searchBar.setBackground(new Color(255, 255, 224)); // Light yellow background color

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0, 102, 0)); // Dark green button color
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle search button click
                String searchText = searchBar.getText();
                JOptionPane.showMessageDialog(null, "Search clicked with input: " + searchText);
            }
        });

        // Notification button
        JButton notificationButton = new JButton("Notifications");
        notificationButton.setForeground(Color.WHITE);
        notificationButton.setBackground(new Color(0, 102, 0)); // Dark green button color
        notificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle notification button click
                JOptionPane.showMessageDialog(null, "Notifications clicked!");
            }
        });

        // Panel for notification and search components
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(searchBar);
        rightPanel.add(searchButton);
        rightPanel.add(notificationButton);

        navBar.add(logoLabel, BorderLayout.WEST);
        navBar.add(titleLabel, BorderLayout.CENTER);
        navBar.add(rightPanel, BorderLayout.EAST);

        return navBar;
    }

    private JPanel createContent() {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0, 3, 20, 20)); // Added gaps between panels

        Random random = new Random();

        // Add multiple items (e.g., images or panels) to the content
        for (int i = 0; i < 18; i++) { // Display 18 items
            int randomIndex = random.nextInt(itemImageNames.length);
            String itemName = "Item " + (i + 1);
            JButton itemButton = createItemButton(itemImageNames[randomIndex], itemName);
            content.add(itemButton);
        }

        return content;
    }

    private JButton createItemButton(String imagePath, String buttonText) {
        ImageIcon itemIcon = new ImageIcon(imagePath);
        itemIcon = new ImageIcon(itemIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

        JButton itemButton = new JButton(buttonText, itemIcon);
        itemButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        itemButton.setHorizontalTextPosition(SwingConstants.CENTER);
        itemButton.setFont(new Font("Arial", Font.PLAIN, 16));
        itemButton.setForeground(Color.BLACK);
        itemButton.setBackground(Color.WHITE); // White background color for item box
        itemButton.setBorder(BorderFactory.createEmptyBorder()); // Set border to zero

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle item button click
                JOptionPane.showMessageDialog(null, buttonText + " clicked!");
            }
        });

        return itemButton;
    }

    private JPanel createBottomBar() {
        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(34, 139, 34)); // Dark green background color

        // Buy, Sell, Learn, Settings buttons
        JButton buyButton = createBottomBarButton("Buy");
        JButton sellButton = createBottomBarButton("Sell");
        JButton learnButton = createBottomBarButton("Learn");
        JButton settingsButton = createBottomBarButton("Settings");

        bottomBar.add(buyButton);
        bottomBar.add(sellButton);
        bottomBar.add(learnButton);
        bottomBar.add(settingsButton);

        return bottomBar;
    }

    private JButton createBottomBarButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle bottom bar button click
                JOptionPane.showMessageDialog(null, buttonText + " button clicked!");
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePageGUI());
    }
}