package GUI;

import javax.swing.*;
import java.awt.*;

import Service.PassengerService;
import Service.TrainService;
import Service.StationService;
import Service.RouteService;
import Service.SeatService;
import Service.ReservationService;
import Service.PaymentService;

import model.Passanger;

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
     * SHARED SERVICES
     * =====================================================
     */
    private PassengerService passengerService;
    private TrainService trainService;
    private StationService stationService;
    private RouteService routeService;
    private SeatService seatService;
    private ReservationService reservationService;
    private PaymentService paymentService;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public LoginFrame(
            PassengerService passengerService,
            TrainService trainService,
            StationService stationService,
            RouteService routeService,
            SeatService seatService,
            ReservationService reservationService,
            PaymentService paymentService) {

        /*
         * Store SAME shared services.
         */
        this.passengerService = passengerService;
        this.trainService = trainService;
        this.stationService = stationService;
        this.routeService = routeService;
        this.seatService = seatService;
        this.reservationService = reservationService;
        this.paymentService = paymentService;


        /*
         * KEEP YOUR EXISTING GUI CODE BELOW.
         */

        setTitle("Train Ticket Reservation System");
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
        /*
         * =====================================================
         * LOGIN BUTTON EVENT
         * =====================================================
         */
        loginButton.addActionListener(e -> {

            /*
             * Read login fields.
             */
            String username =
                    usernameField
                            .getText()
                            .trim();


            String password =
                    new String(
                            passwordField
                                    .getPassword()
                    );


            /*
             * Check empty fields.
             */
            if (username.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * =================================================
             * ADMIN LOGIN
             * =================================================
             *
             * Keep your current admin credentials here.
             *
             * Change these if your existing credentials
             * are different.
             */
            if (username.equals("admin")
                    &&
                    password.equals("admin123")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Admin login successful!",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE
                );


                dispose();


                /*
                 * Open Admin Dashboard.
                 */
                new MainDashboard(    passengerService,
                        trainService,
                        stationService,
                        routeService,
                        seatService,
                        reservationService,
                        paymentService);


                return;
            }


            /*
             * =================================================
             * PASSENGER LOGIN
             * =================================================
             */

            Passanger passenger =
                    passengerService
                            .loginPassenger(
                                    username,
                                    password
                            );


            if (passenger != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Passenger login successful!",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE
                );


                dispose();


                /*
                 * Open Passenger Dashboard.
                 */
                new PassengerDashboard(
                        passenger,
                        trainService,
                        seatService,
                        reservationService,
                        paymentService
                );


                return;
            }


            /*
             * =================================================
             * LOGIN FAILED
             * =================================================
             */

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        });
        //Add the panel to the window
        add(panel);
        // Make the window visible.
        setVisible(true);
    }
}