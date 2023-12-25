import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginGUI extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel loginPanel;
    private JPanel registrationPanel;
    private JTextField usernameFieldReg;
    private JPasswordField passwordFieldReg;
    private JTextField emailFieldReg;
    private JButton registerButton;
    private JTextField usernameFieldLogin;
    private JPasswordField passwordFieldLogin;
    private JButton loginButton;
    private JButton registerLink;
    private JButton loginLink;
    private UserModel userModel;
    public static int userId;
    public LoginGUI() {
        userModel = new UserModel();

        setTitle("Recycle Waste System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPanel = createLoginPanel();
        registrationPanel = createRegistrationPanel();

        cardPanel.add(loginPanel, "LoginPanel");
        cardPanel.add(registrationPanel, "RegistrationPanel");

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "LoginPanel");

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        // Login Section
        JLabel loginLabel = new JLabel("Login:");
        loginPanel.add(loginLabel);

        usernameFieldLogin = new JTextField(20);
        passwordFieldLogin = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        registerLink = new JButton("Register");
        registerLink.setForeground(Color.BLUE);
        registerLink.setBorderPainted(false);
        registerLink.setContentAreaFilled(false);
        registerLink.addActionListener(e -> cardLayout.show(cardPanel, "RegistrationPanel"));

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameFieldLogin);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordFieldLogin);
        loginPanel.add(loginButton);
        loginPanel.add(registerLink);

        return loginPanel;
    }

    private JPanel createRegistrationPanel() {
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel, BoxLayout.Y_AXIS));

        // Registration Section
        JLabel registrationLabel = new JLabel("Registration:");
        registrationPanel.add(registrationLabel);

        usernameFieldReg = new JTextField(20);
        passwordFieldReg = new JPasswordField(20);
        emailFieldReg = new JTextField(20);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        loginLink = new JButton("Login");
        loginLink.setForeground(Color.BLUE);
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.addActionListener(e -> cardLayout.show(cardPanel, "LoginPanel"));

        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(usernameFieldReg);
        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(passwordFieldReg);
        registrationPanel.add(new JLabel("Email:"));
        registrationPanel.add(emailFieldReg);
        registrationPanel.add(registerButton);
        registrationPanel.add(loginLink);

        return registrationPanel;
    }
    private boolean isValidUsername(String username) {
        // Allow only alphanumeric characters and underscore
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        // Validate password (e.g., minimum length)
        return password.length() >= 6; // Minimum 6 characters
    }

    private boolean isValidEmail(String email) {
        // Simple email format validation using regex
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
        	   String username = usernameFieldReg.getText();
               String password = new String(passwordFieldReg.getPassword());
               String email = emailFieldReg.getText();
               
               if (!isValidUsername(username)) {
                   JOptionPane.showMessageDialog(this, "Invalid username format!");
                   return;
               }

               if (!isValidPassword(password)) {
                   JOptionPane.showMessageDialog(this, "Invalid password format!");
                   return;
               }

               if (!isValidEmail(email)) {
                   JOptionPane.showMessageDialog(this, "Invalid email format!");
                   return;
               }
               
            boolean registered = userModel.registerUser(username, password, email);
            
            if (registered) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                userId = userModel.LoggedInUserId(username,password);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed!");
            }
        }

        if (e.getSource() == loginButton) {
            String username = usernameFieldLogin.getText();
            String password = new String(passwordFieldLogin.getPassword());

            boolean loggedIn = userModel.loginUser(username, password);

            if (loggedIn) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Perform actions after successful login
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
