package GUI;

import javax.swing.*;
import java.awt.*;
import Service.AdminService;
/*
 * =====================================================
 * LOGIN FRAME
 * =====================================================
 *
 * This class creates the graphical login screen
 * for the Train Ticket Reservation System.
 *
 * At this stage, we are only creating the GUI.
 * The login functionality will be connected later.
 */
public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private java.util.Scanner scanner;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public LoginFrame() {
        scanner = new java.util.Scanner(System.in);
        AdminService adminService = new AdminService(scanner);

        // Set the title of the window.
        setTitle("Train Ticket Reservation System");

        // Set the window size.
        setSize(450, 300);

        // Place the window in the center of the screen.
        setLocationRelativeTo(null);

        // Close the application when the window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*
         * =================================================
         * MAIN PANEL
         * =================================================
         */

        JPanel panel = new JPanel();

        // Use GridBagLayout to arrange components.
        panel.setLayout(new GridBagLayout());

        // Create layout constraints.
        GridBagConstraints gbc = new GridBagConstraints();

        // Add space around components.
        gbc.insets = new Insets(10, 10, 10, 10);


        /*
         * =================================================
         * TITLE
         * =================================================
         */

        JLabel titleLabel = new JLabel("TRAIN TICKET RESERVATION SYSTEM");

        // Make the title bold and larger.
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Place the title at the top.
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(titleLabel, gbc);


        /*
         * =================================================
         * USERNAME
         * =================================================
         */

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(usernameLabel, gbc);


        // Username input field.
        usernameField = new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(usernameField, gbc);
        /*
         * =================================================
         * PASSWORD
         * =================================================
         */
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);
        // Password input field.
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);
        /*
         * =================================================
         * LOGIN BUTTON
         * =================================================
         */
        loginButton = new JButton("LOGIN");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);
        /*
         * =====================================================
         * LOGIN BUTTON EVENT
         * =====================================================
         * This code runs when the LOGIN button is clicked.
         */
        loginButton.addActionListener(e -> {
             // Get the username entered in the username field.
            String username = usernameField.getText();
            /*
             * Get the password entered in the password field.
             * getPassword() returns a char array,so we convert it into a String.
             */
            String password = new String(passwordField.getPassword());
            //Check username and password
            boolean loggedIn = adminService.login(username, password);
            if (loggedIn) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                 //Close the Login window
                dispose();
                new MainDashboard();
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        //Add the panel to the window
        add(panel);
        // Make the window visible.
        setVisible(true);
    }
    /*
     * =====================================================
     * MAIN METHOD
     * =====================================================
     *
     * This method is only used to test the GUI.
     */
    public static void main(String[] args) {
        /*
         * Start the GUI using the Swing Event
         * Dispatch Thread.
         */
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}