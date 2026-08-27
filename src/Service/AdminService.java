package Service;

import model.Admin;
import java.util.Scanner;

/*
 * AdminService manages administrator authentication.
 * This class is responsible for:
 * 1. Displaying the login screen.
 * 2. Reading username and password.
 * 3. Checking login credentials.
 *
 * One Scanner object is passed from Main.java.
 * This avoids creating multiple Scanner objects for System.in.
 */

public class AdminService {
    // Scanner received from Main.java.
    private Scanner scanner;

    /*
     * Admin object containing the login credentials.
     * Default credentials:
     * Username: admin
     * Password: 1234
     */
    private Admin admin;

    /*
     * Constructor for AdminService.
     * The Scanner is received from Main.java.
     */
    public AdminService(Scanner scanner) {
        this.scanner = scanner;
        this.admin = new Admin(
                1, "System Administrator", "admin", "1234");
    }

    /*
     * ADMIN LOGIN
     * Returns true when login is successful.
     * Returns false when login fails.
     */
    public boolean login() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          ADMIN LOGIN");
        System.out.println("================================");

        //Ask the administrator for username.
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        //Check the entered credentials.
        if (admin.login(username, password)) {
            System.out.println();
            System.out.println("Login successful!");
            System.out.println("Welcome, " + admin.getUsername() + "!");
            return true;
        }
        else {
            System.out.println();
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    /*
     * =====================================================
     * GUI LOGIN
     * =====================================================
     *
     * This method receives username and password
     * from the Swing GUI.
     */
    public boolean login(String username, String password) {
        /*
         * Use the SAME username and password
         * used by your existing console login.
         */
        if (username.equals("admin") && password.equals("admin123")) {
            return true;
        }
        return false;
    }
}