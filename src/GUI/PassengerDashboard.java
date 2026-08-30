package GUI;

import Service.PaymentService;
import Service.ReservationService;
import Service.SeatService;
import Service.TrainService;

import model.Passanger;
import model.Reservation;
import model.Seat;
import model.Train;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/*
 * =====================================================
 * PASSENGER DASHBOARD
 * =====================================================
 *
 * This dashboard is shown after
 * a normal passenger logs in.
 *
 * Passenger functions:
 *
 * - View Trains
 * - View Available Seats
 * - Make Reservation
 * - View My Reservations
 * - Make Payment
 * - Logout
 */
public class PassengerDashboard
        extends JFrame {


    /*
     * =====================================================
     * LOGGED-IN PASSENGER
     * =====================================================
     */

    private Passanger currentPassenger;


    /*
     * =====================================================
     * SERVICES
     * =====================================================
     */

    private TrainService trainService;
    private SeatService seatService;
    private ReservationService reservationService;
    private PaymentService paymentService;


    /*
     * =====================================================
     * GUI COMPONENTS
     * =====================================================
     */

    private JComboBox<Train> trainComboBox;

    private JComboBox<Seat> seatComboBox;

    private JTextField travelDateField;

    private JTextField reservationIdField;


    private JButton viewTrainsButton;
    private JButton availableSeatsButton;
    private JButton makeReservationButton;
    private JButton myReservationsButton;
    private JButton paymentButton;
    private JButton logoutButton;


    private JTable reservationTable;

    private DefaultTableModel tableModel;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public PassengerDashboard(
            Passanger currentPassenger,
            TrainService trainService,
            SeatService seatService,
            ReservationService reservationService,
            PaymentService paymentService) {

        /*
         * Store values.
         */
        this.currentPassenger =
                currentPassenger;

        this.trainService =
                trainService;

        this.seatService =
                seatService;

        this.reservationService =
                reservationService;

        this.paymentService =
                paymentService;


        /*
         * =================================================
         * WINDOW SETTINGS
         * =================================================
         */

        setTitle(
                "Passenger Dashboard"
        );

        setSize(
                900,
                650
        );

        setLocationRelativeTo(
                null
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );


        /*
         * =================================================
         * MAIN PANEL
         * =================================================
         */

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );


        GridBagConstraints gbc =
                new GridBagConstraints();


        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );


        gbc.fill =
                GridBagConstraints.HORIZONTAL;


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


        panel.add(
                titleLabel,
                gbc
        );


        /*
         * =================================================
         * WELCOME MESSAGE
         * =================================================
         */

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + currentPassenger
                                .getUsername()
                );


        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;


        panel.add(
                welcomeLabel,
                gbc
        );


        /*
         * =================================================
         * RESERVATION ID
         * =================================================
         */

        JLabel reservationIdLabel =
                new JLabel(
                        "Reservation ID:"
                );


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;


        panel.add(
                reservationIdLabel,
                gbc
        );


        reservationIdField =
                new JTextField(15);


        gbc.gridx = 1;


        panel.add(
                reservationIdField,
                gbc
        );


        /*
         * =================================================
         * TRAIN
         * =================================================
         */

        JLabel trainLabel =
                new JLabel(
                        "Train:"
                );


        gbc.gridx = 0;
        gbc.gridy = 3;


        panel.add(
                trainLabel,
                gbc
        );


        trainComboBox =
                new JComboBox<>();


        gbc.gridx = 1;


        panel.add(
                trainComboBox,
                gbc
        );


        /*
         * Load trains.
         */
        loadTrains();


        /*
         * =================================================
         * SEAT
         * =================================================
         */

        JLabel seatLabel =
                new JLabel(
                        "Seat:"
                );


        gbc.gridx = 0;
        gbc.gridy = 4;


        panel.add(
                seatLabel,
                gbc
        );


        seatComboBox =
                new JComboBox<>();


        gbc.gridx = 1;


        panel.add(
                seatComboBox,
                gbc
        );


        /*
         * Load first train seats.
         */
        loadSeats();


        /*
         * When train changes,
         * reload seats.
         */
        trainComboBox
                .addActionListener(e -> {

                    loadSeats();
                });


        /*
         * =================================================
         * TRAVEL DATE
         * =================================================
         */

        JLabel dateLabel =
                new JLabel(
                        "Travel Date (YYYY-MM-DD):"
                );


        gbc.gridx = 0;
        gbc.gridy = 5;


        panel.add(
                dateLabel,
                gbc
        );


        travelDateField =
                new JTextField(15);


        gbc.gridx = 1;


        panel.add(
                travelDateField,
                gbc
        );


        /*
         * =================================================
         * BUTTONS
         * =================================================
         */

        viewTrainsButton =
                new JButton(
                        "View Trains"
                );


        availableSeatsButton =
                new JButton(
                        "Available Seats"
                );


        makeReservationButton =
                new JButton(
                        "Book Ticket"
                );


        myReservationsButton =
                new JButton(
                        "My Reservations"
                );


        paymentButton =
                new JButton(
                        "Make Payment"
                );


        logoutButton =
                new JButton(
                        "Logout"
                );


        /*
         * Row 6.
         */
        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                viewTrainsButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                availableSeatsButton,
                gbc
        );


        /*
         * Row 7.
         */
        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                makeReservationButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                myReservationsButton,
                gbc
        );


        /*
         * Row 8.
         */
        gbc.gridx = 0;
        gbc.gridy = 8;

        panel.add(
                paymentButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                logoutButton,
                gbc
        );


        /*
         * =================================================
         * TABLE
         * =================================================
         */

        String[] columns = {

                "Reservation ID",
                "Train",
                "Seat",
                "Travel Date",
                "Status"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        reservationTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        reservationTable
                );


        gbc.gridx = 0;
        gbc.gridy = 9;

        gbc.gridwidth = 2;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.fill =
                GridBagConstraints.BOTH;


        panel.add(
                scrollPane,
                gbc
        );


        /*
         * =================================================
         * VIEW TRAINS
         * =================================================
         */

        viewTrainsButton
                .addActionListener(e -> {

                    viewTrains();
                });


        /*
         * =================================================
         * AVAILABLE SEATS
         * =================================================
         */

        availableSeatsButton
                .addActionListener(e -> {

                    showAvailableSeats();
                });


        /*
         * =================================================
         * BOOK TICKET
         * =================================================
         */

        makeReservationButton
                .addActionListener(e -> {

                    makeReservation();
                });


        /*
         * =================================================
         * MY RESERVATIONS
         * =================================================
         */

        myReservationsButton
                .addActionListener(e -> {

                    showMyReservations();
                });


        /*
         * =================================================
         * PAYMENT
         * =================================================
         */

        paymentButton
                .addActionListener(e -> {

                    new PaymentManagementFrame(
                            paymentService,
                            reservationService
                    );
                });


        /*
         * =================================================
         * LOGOUT
         * =================================================
         */

        logoutButton
                .addActionListener(e -> {

                    int answer =
                            JOptionPane
                                    .showConfirmDialog(
                                            this,
                                            "Do you want to logout?",
                                            "Logout",
                                            JOptionPane.YES_NO_OPTION
                                    );


                    if (answer
                            == JOptionPane.YES_OPTION) {

                        dispose();


                        /*
                         * LoginFrame should use
                         * the same service objects.
                         *
                         * We will connect this properly
                         * in the LoginFrame section below.
                         */
                    }
                });


        add(panel);

        setVisible(true);
    }


    /*
     * =====================================================
     * LOAD TRAINS
     * =====================================================
     */
    private void loadTrains() {

        trainComboBox
                .removeAllItems();


        for (Train train :
                trainService
                        .getTrains()) {

            trainComboBox
                    .addItem(
                            train
                    );
        }
    }


    /*
     * =====================================================
     * LOAD SEATS
     * =====================================================
     */
    private void loadSeats() {

        seatComboBox
                .removeAllItems();


        Train train =
                (Train)
                        trainComboBox
                                .getSelectedItem();


        if (train == null) {

            return;
        }


        for (Seat seat :
                train.getSeats()) {

            seatComboBox
                    .addItem(
                            seat
                    );
        }
    }


    /*
     * =====================================================
     * VIEW TRAINS
     * =====================================================
     */
    private void viewTrains() {

        if (trainService
                .getTrains()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No trains are available.",
                    "Train List",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        StringBuilder message =
                new StringBuilder();


        message.append(
                "AVAILABLE TRAINS\n\n"
        );


        for (Train train :
                trainService
                        .getTrains()) {

            message.append(
                    "Train ID: "
            );

            message.append(
                    train.getTrainId()
            );

            message.append(
                    "\n"
            );


            message.append(
                    "Train: "
            );

            message.append(
                    train
            );

            message.append(
                    "\n"
            );


            message.append(
                    "Departure: "
            );

            message.append(
                    train
                            .getDepartureStation()
            );

            message.append(
                    "\n"
            );


            message.append(
                    "Destination: "
            );

            message.append(
                    train
                            .getDestinationStation()
            );

            message.append(
                    "\n"
            );


            message.append(
                    "Departure Time: "
            );

            message.append(
                    train
                            .getDepartureTime()
            );

            message.append(
                    "\n"
            );


            message.append(
                    "Ticket Price: Rs. "
            );

            message.append(
                    train
                            .getTicketPrice()
            );

            message.append(
                    "\n\n"
            );
        }


        JOptionPane.showMessageDialog(
                this,
                message.toString(),
                "Train List",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    /*
     * =====================================================
     * VIEW AVAILABLE SEATS
     * =====================================================
     */
    private void showAvailableSeats() {

        try {

            Train train =
                    (Train)
                            trainComboBox
                                    .getSelectedItem();


            if (train == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a train.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            String travelDate =
                    travelDateField
                            .getText()
                            .trim();


            /*
             * Validate date.
             */
            LocalDate.parse(
                    travelDate
            );


            ArrayList<Seat> seats =
                    reservationService
                            .getAvailableSeats(
                                    train,
                                    travelDate
                            );


            if (seats.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No seats available.",
                        "Available Seats",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            StringBuilder message =
                    new StringBuilder();


            message.append(
                    "Available Seats:\n\n"
            );


            for (Seat seat :
                    seats) {

                message.append(
                        "Seat "
                );

                message.append(
                        seat.getSeatNumber()
                );

                message.append(
                        "\n"
                );
            }


            JOptionPane.showMessageDialog(
                    this,
                    message.toString(),
                    "Available Seats",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }
        catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter the date as YYYY-MM-DD.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * MAKE RESERVATION
     * =====================================================
     */
    private void makeReservation() {

        try {

            int reservationId =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );


            Train train =
                    (Train)
                            trainComboBox
                                    .getSelectedItem();


            Seat seat =
                    (Seat)
                            seatComboBox
                                    .getSelectedItem();


            String travelDate =
                    travelDateField
                            .getText()
                            .trim();


            /*
             * Validate.
             */
            if (train == null
                    || seat == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a train and seat.",
                        "Reservation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Validate date.
             */
            LocalDate.parse(
                    travelDate
            );


            boolean created =
                    reservationService
                            .makeReservation(
                                    reservationId,
                                    currentPassenger,
                                    train,
                                    seat,
                                    travelDate
                            );


            if (created) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ticket reserved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                reservationIdField
                        .setText("");


                showMyReservations();

            } else {

                JOptionPane.showMessageDialog(
                        this,

                        "Reservation could not be created.\n"
                                +
                                "The seat may already be booked\n"
                                +
                                "or Reservation ID may already exist.",

                        "Reservation Error",

                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation ID must be a whole number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter travel date as YYYY-MM-DD.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * MY RESERVATIONS
     * =====================================================
     */
    private void showMyReservations() {

        tableModel
                .setRowCount(0);


        boolean found =
                false;


        for (Reservation reservation :
                reservationService
                        .getReservations()) {

            /*
             * Only display reservations
             * belonging to logged-in passenger.
             */
            if (reservation
                    .getPassenger()
                    .getUserid()
                    ==
                    currentPassenger
                            .getUserid()) {

                Object[] row = {

                        reservation
                                .getReservationId(),

                        reservation
                                .getTrain(),

                        reservation
                                .getSeatNumber(),

                        reservation
                                .getTravelDate(),

                        reservation
                                .getStatus()
                };


                tableModel
                        .addRow(
                                row
                        );


                found =
                        true;
            }
        }


        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "You do not have any reservations.",
                    "My Reservations",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}