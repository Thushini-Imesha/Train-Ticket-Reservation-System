package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import Service.PassengerService;
import Service.TrainService;
import Service.StationService;
import Service.RouteService;
import Service.SeatService;
import Service.ReservationService;
import Service.PaymentService;
/*
 * =====================================================
 * MAIN DASHBOARD
 * =====================================================
 *
 * This class creates the main GUI menu for the
 * Train Ticket Reservation System.
 *
 * After the administrator logs in successfully,
 * this dashboard will be displayed.
 */
public class MainDashboard extends JFrame {

    private JButton passengerButton;
    private JButton trainButton;
    private JButton stationButton;
    private JButton routeButton;
    private JButton seatButton;
    private JButton reservationButton;
    private JButton paymentButton;
    private JButton exitButton;

    // PassengerService used by the GUI.
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
    public MainDashboard(
            PassengerService passengerService,
            TrainService trainService,
            StationService stationService,
            RouteService routeService,
            SeatService seatService,
            ReservationService reservationService,
            PaymentService paymentService) {

        /*
         * Use the shared services.
         */
        this.passengerService =
                passengerService;

        this.trainService =
                trainService;

        this.stationService =
                stationService;

        this.routeService =
                routeService;

        this.seatService =
                seatService;

        this.reservationService =
                reservationService;

        this.paymentService =
                paymentService;
        /*
         * Set window title.
         */
        setTitle("Train Ticket Reservation System - Dashboard");

        /*
         * Set window size.
         */
        setSize(800, 700);

        /*
         * Display window in the center of the screen.
         */
        setLocationRelativeTo(null);

        /*
         * Close application when window is closed.
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Create main panel.
         */
        JPanel panel = new JPanel();

        /*
         * Use GridBagLayout.
         */
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        /*
         * Space between components.
         */
        gbc.insets = new Insets(10, 10, 10, 10);

        /*
         * Make buttons wider.
         */
        gbc.fill = GridBagConstraints.HORIZONTAL;


        /*
         * =================================================
         * TITLE
         * =================================================
         */

        JLabel titleLabel =
                new JLabel(
                        "TRAIN TICKET RESERVATION SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(titleLabel, gbc);


        /*
         * =================================================
         * SUBTITLE
         * =================================================
         */

        JLabel dashboardLabel =
                new JLabel(
                        "MAIN DASHBOARD"
                );

        dashboardLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        panel.add(dashboardLabel, gbc);


        /*
         * =================================================
         * PASSENGER MANAGEMENT BUTTON
         * =================================================
         */

        passengerButton = new JButton("Passenger Management");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(passengerButton, gbc);


        /*
         * =================================================
         * TRAIN MANAGEMENT BUTTON
         * =================================================
         */

        trainButton =
                new JButton(
                        "Train Management"
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(trainButton, gbc);


        /*
         * =================================================
         * STATION MANAGEMENT BUTTON
         * =================================================
         */

        stationButton =
                new JButton(
                        "Station Management"
                );

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(stationButton, gbc);


        /*
         * =================================================
         * ROUTE MANAGEMENT BUTTON
         * =================================================
         */

        routeButton = new JButton("Route Management");

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(routeButton, gbc);


        /*
         * =================================================
         * SEAT MANAGEMENT BUTTON
         * =================================================
         */

        seatButton =
                new JButton(
                        "Seat Management"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(seatButton, gbc);


        /*
         * =================================================
         * RESERVATION MANAGEMENT BUTTON
         * =================================================
         */

        reservationButton =
                new JButton(
                        "Reservation Management"
                );

        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(reservationButton, gbc);

        paymentButton =
                new JButton(
                        "Payment Management"
                );
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;

        panel.add(
                paymentButton,
                gbc
        );


        /*
         * =================================================
         * EXIT BUTTON
         * =================================================
         */

        exitButton =
                new JButton(
                        "Exit"
                );

        gbc.gridx = 0;
        gbc.gridy = 10;

        panel.add(exitButton, gbc);


        /*
         * =================================================
         * TEMPORARY BUTTON EVENTS
         * =================================================
         *
         * These events will be replaced later
         * when we create each management screen.
         */

        passengerButton.addActionListener(e -> {
            new PassengerManagementFrame(passengerService);
        });

        trainButton.addActionListener(e -> {
            new TrainManagementFrame(trainService);
        });

        /*
         * =====================================================
         * STATION MANAGEMENT BUTTON
         * =====================================================
         */
        stationButton.addActionListener(e -> {

            /*
             * Open Station Management window
             * and pass the shared StationService.
             */
            new StationManagementFrame(stationService);
        });

        routeButton.addActionListener(e -> {
            new RouteManagementFrame( routeService, stationService);
        });

        /*
         * =====================================================
         * OPEN SEAT MANAGEMENT
         * =====================================================
         */
        seatButton.addActionListener(e -> {

            new SeatManagementFrame(
                    seatService,
                    trainService
            );
        });

        /*
         * =====================================================
         * OPEN RESERVATION MANAGEMENT
         * =====================================================
         */
        reservationButton.addActionListener(e -> {

            new ReservationManagementFrame(
                    passengerService,
                    trainService,
                    seatService,
                    reservationService
            );
        });
        /*
         * =====================================================
         * OPEN PAYMENT MANAGEMENT
         * =====================================================
         */
        paymentButton
                .addActionListener(e -> {

                    new PaymentManagementFrame(
                            paymentService,
                            reservationService
                    );
                });

        /*
         * Exit button event.
         */
        exitButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        /*
         * Add main panel to frame.
         */
        add(panel);

        /*
         * Make window visible.
         */
        setVisible(true);
    }


    /*
     * =====================================================
     * MAIN METHOD
     * =====================================================
     *
     * Used only for testing the dashboard directly.
     */
    // end of your last method

}