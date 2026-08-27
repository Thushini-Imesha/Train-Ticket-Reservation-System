package GUI;

import Service.PassengerService;
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
 * RESERVATION MANAGEMENT FRAME
 * =====================================================
 *
 * This class creates the GUI for Reservation Management.
 *
 * Functions:
 *
 * - Make Reservation
 * - View Reservations
 * - Search Reservation
 * - Cancel Reservation
 * - View Available Seats
 * - Reservation Summary
 */
public class ReservationManagementFrame extends JFrame {

    /*
     * =====================================================
     * GUI COMPONENTS
     * =====================================================
     */

    private JTextField reservationIdField;

    private JComboBox<Passanger> passengerComboBox;
    private JComboBox<Train> trainComboBox;
    private JComboBox<Seat> seatComboBox;

    private JTextField travelDateField;

    private JButton makeReservationButton;
    private JButton viewReservationsButton;
    private JButton searchReservationButton;
    private JButton cancelReservationButton;
    private JButton availableSeatsButton;
    private JButton summaryButton;
    private JButton backButton;

    /*
     * JTable.
     */
    private JTable reservationTable;
    private DefaultTableModel tableModel;


    /*
     * =====================================================
     * SERVICES
     * =====================================================
     */

    private PassengerService passengerService;
    private TrainService trainService;
    private SeatService seatService;
    private ReservationService reservationService;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public ReservationManagementFrame(
            PassengerService passengerService,
            TrainService trainService,
            SeatService seatService,
            ReservationService reservationService) {

        /*
         * Store shared services.
         */
        this.passengerService = passengerService;
        this.trainService = trainService;
        this.seatService = seatService;
        this.reservationService = reservationService;


        /*
         * =================================================
         * WINDOW SETTINGS
         * =================================================
         */

        setTitle(
                "Reservation Management"
        );

        setSize(
                950,
                700
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        /*
         * =================================================
         * MAIN PANEL
         * =================================================
         */

        JPanel panel =
                new JPanel();

        panel.setLayout(
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
                        "RESERVATION MANAGEMENT"
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
         * RESERVATION ID
         * =================================================
         */

        JLabel reservationIdLabel =
                new JLabel(
                        "Reservation ID:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(
                reservationIdLabel,
                gbc
        );


        reservationIdField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                reservationIdField,
                gbc
        );


        /*
         * =================================================
         * PASSENGER
         * =================================================
         */

        JLabel passengerLabel =
                new JLabel(
                        "Passenger:"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                passengerLabel,
                gbc
        );


        passengerComboBox =
                new JComboBox<>();

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                passengerComboBox,
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
        gbc.gridy = 3;

        panel.add(
                trainComboBox,
                gbc
        );


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
        gbc.gridy = 4;

        panel.add(
                seatComboBox,
                gbc
        );


        /*
         * =================================================
         * TRAVEL DATE
         * =================================================
         */

        JLabel travelDateLabel =
                new JLabel(
                        "Travel Date (YYYY-MM-DD):"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                travelDateLabel,
                gbc
        );


        travelDateField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 5;

        panel.add(
                travelDateField,
                gbc
        );


        /*
         * =================================================
         * LOAD PASSENGERS
         * =================================================
         */

        for (Passanger passenger :
                passengerService.getPassangers()) {

            passengerComboBox.addItem(
                    passenger
            );
        }


        /*
         * =================================================
         * LOAD TRAINS
         * =================================================
         */

        for (Train train :
                trainService.getTrains()) {

            trainComboBox.addItem(
                    train
            );
        }


        /*
         * =================================================
         * LOAD SEATS FOR FIRST TRAIN
         * =================================================
         */

        loadSeatsForSelectedTrain();


        /*
         * =================================================
         * TRAIN CHANGE EVENT
         * =================================================
         *
         * When another train is selected,
         * display seats belonging to that train.
         */

        trainComboBox.addActionListener(e -> {

            loadSeatsForSelectedTrain();
        });


        /*
         * =================================================
         * BUTTONS
         * =================================================
         */

        makeReservationButton =
                new JButton(
                        "Make Reservation"
                );

        viewReservationsButton =
                new JButton(
                        "View Reservations"
                );

        searchReservationButton =
                new JButton(
                        "Search Reservation"
                );

        cancelReservationButton =
                new JButton(
                        "Cancel Reservation"
                );

        availableSeatsButton =
                new JButton(
                        "Available Seats"
                );

        summaryButton =
                new JButton(
                        "Reservation Summary"
                );

        backButton =
                new JButton(
                        "Back"
                );


        /*
         * Row 6
         */
        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                makeReservationButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                viewReservationsButton,
                gbc
        );


        /*
         * Row 7
         */
        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                searchReservationButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                cancelReservationButton,
                gbc
        );


        /*
         * Row 8
         */
        gbc.gridx = 0;
        gbc.gridy = 8;

        panel.add(
                availableSeatsButton,
                gbc
        );


        gbc.gridx = 1;

        panel.add(
                summaryButton,
                gbc
        );


        /*
         * Row 9
         */
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;

        panel.add(
                backButton,
                gbc
        );


        /*
         * =================================================
         * RESERVATION TABLE
         * =================================================
         */

        String[] columnNames = {

                "Reservation ID",
                "Passenger",
                "Train",
                "Seat Number",
                "Travel Date",
                "Status"
        };


        tableModel =
                new DefaultTableModel(
                        columnNames,
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
        gbc.gridy = 10;

        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        panel.add(
                scrollPane,
                gbc
        );


        /*
         * =================================================
         * MAKE RESERVATION BUTTON
         * =================================================
         */

        makeReservationButton.addActionListener(e -> {

            makeReservation();
        });


        /*
         * =================================================
         * VIEW RESERVATIONS BUTTON
         * =================================================
         */

        viewReservationsButton.addActionListener(e -> {

            refreshReservationTable();
        });


        /*
         * =================================================
         * SEARCH RESERVATION BUTTON
         * =================================================
         */

        searchReservationButton.addActionListener(e -> {

            searchReservation();
        });


        /*
         * =================================================
         * CANCEL RESERVATION BUTTON
         * =================================================
         */

        cancelReservationButton.addActionListener(e -> {

            cancelReservation();
        });


        /*
         * =================================================
         * AVAILABLE SEATS BUTTON
         * =================================================
         */

        availableSeatsButton.addActionListener(e -> {

            displayAvailableSeats();
        });


        /*
         * =================================================
         * RESERVATION SUMMARY BUTTON
         * =================================================
         */

        summaryButton.addActionListener(e -> {

            displayReservationSummary();
        });


        /*
         * =================================================
         * BACK BUTTON
         * =================================================
         */

        backButton.addActionListener(e -> {

            dispose();
        });


        /*
         * Add panel.
         */
        add(panel);


        /*
         * Show window.
         */
        setVisible(true);
    }


    /*
     * =====================================================
     * LOAD SEATS FOR SELECTED TRAIN
     * =====================================================
     */
    private void loadSeatsForSelectedTrain() {

        /*
         * Remove old seats.
         */
        seatComboBox.removeAllItems();


        Train selectedTrain =
                (Train)
                        trainComboBox
                                .getSelectedItem();


        /*
         * No train selected.
         */
        if (selectedTrain == null) {
            return;
        }


        /*
         * Load seats belonging to selected train.
         */
        for (Seat seat :
                selectedTrain.getSeats()) {

            seatComboBox.addItem(
                    seat
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

            /*
             * Read Reservation ID.
             */
            int reservationId =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );


            /*
             * Get selected Passenger.
             */
            Passanger passenger =
                    (Passanger)
                            passengerComboBox
                                    .getSelectedItem();


            /*
             * Get selected Train.
             */
            Train train =
                    (Train)
                            trainComboBox
                                    .getSelectedItem();


            /*
             * Get selected Seat.
             */
            Seat seat =
                    (Seat)
                            seatComboBox
                                    .getSelectedItem();


            /*
             * Read Travel Date.
             */
            String travelDate =
                    travelDateField
                            .getText()
                            .trim();


            /*
             * Validate basic information.
             */
            if (passenger == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please add/select a passenger.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            if (train == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please add/select a train.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            if (seat == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please add/select a seat.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Validate date format.
             */
            LocalDate.parse(
                    travelDate
            );


            /*
             * Call GUI ReservationService method.
             */
            boolean reserved =
                    reservationService.makeReservation(
                            reservationId,
                            passenger,
                            train,
                            seat,
                            travelDate
                    );


            if (reserved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                /*
                 * Clear fields.
                 */
                reservationIdField.setText("");
                travelDateField.setText("");


                /*
                 * Refresh table.
                 */
                refreshReservationTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation could not be created.\n"
                                + "Check Reservation ID or seat availability for this date.",
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
                    "Enter travel date using YYYY-MM-DD.\nExample: 2026-09-15",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * VIEW / REFRESH RESERVATIONS
     * =====================================================
     */
    private void refreshReservationTable() {

        /*
         * Clear current rows.
         */
        tableModel.setRowCount(0);


        ArrayList<Reservation> reservations =
                reservationService.getReservations();


        if (reservations.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No reservations found.",
                    "Reservation List",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        /*
         * Display all reservations.
         */
        for (Reservation reservation :
                reservations) {

            addReservationToTable(
                    reservation
            );
        }
    }


    /*
     * =====================================================
     * ADD ONE RESERVATION TO TABLE
     * =====================================================
     */
    private void addReservationToTable(
            Reservation reservation) {

        Object[] row = {

                reservation.getReservationId(),

                reservation.getPassenger(),

                reservation.getTrain(),

                reservation.getSeatNumber(),

                reservation.getTravelDate(),

                reservation.getStatus()
        };


        tableModel.addRow(
                row
        );
    }


    /*
     * =====================================================
     * SEARCH RESERVATION
     * =====================================================
     */
    private void searchReservation() {

        try {

            int reservationId =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );


            Reservation reservation =
                    reservationService.findReservationById(
                            reservationId
                    );


            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation not found.",
                        "Search Result",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Clear table and show only
             * searched reservation.
             */
            tableModel.setRowCount(0);

            addReservationToTable(
                    reservation
            );


            /*
             * Fill the fields.
             */
            passengerComboBox.setSelectedItem(
                    reservation.getPassenger()
            );

            trainComboBox.setSelectedItem(
                    reservation.getTrain()
            );


            /*
             * Changing Train automatically
             * reloads seat combo box.
             */
            for (int i = 0;
                 i < seatComboBox.getItemCount();
                 i++) {

                Seat seat =
                        seatComboBox.getItemAt(i);

                if (seat.getSeatNumber()
                        == reservation.getSeatNumber()) {

                    seatComboBox.setSelectedIndex(i);

                    break;
                }
            }


            travelDateField.setText(
                    reservation.getTravelDate()
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Reservation found!",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Reservation ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * CANCEL RESERVATION
     * =====================================================
     */
    private void cancelReservation() {

        try {

            int reservationId =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );


            Reservation reservation =
                    reservationService.findReservationById(
                            reservationId
                    );


            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation not found.",
                        "Cancel Reservation",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Prevent cancelling twice.
             */
            if (reservation
                    .getStatus()
                    .equals("CANCELLED")) {

                JOptionPane.showMessageDialog(
                        this,
                        "This reservation is already cancelled.",
                        "Cancel Reservation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            int answer =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to cancel this reservation?",
                            "Confirm Cancellation",
                            JOptionPane.YES_NO_OPTION
                    );


            if (answer != JOptionPane.YES_OPTION) {

                return;
            }


            /*
             * IMPORTANT:
             *
             * Cancellation changes reservation status.
             * We do NOT globally release the seat because
             * reservations are date-specific.
             */
            reservation.cancelReservation();


            JOptionPane.showMessageDialog(
                    this,
                    "Reservation cancelled successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            /*
             * Refresh table.
             */
            refreshReservationTable();

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Reservation ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * DISPLAY AVAILABLE SEATS
     * =====================================================
     */
    private void displayAvailableSeats() {

        try {

            Train train =
                    (Train)
                            trainComboBox
                                    .getSelectedItem();


            if (train == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a train.",
                        "Available Seats",
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


            ArrayList<Seat> availableSeats =
                    reservationService.getAvailableSeats(
                            train,
                            travelDate
                    );


            if (availableSeats.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No seats are available for this train on "
                                + travelDate + ".",
                        "Available Seats",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            /*
             * Create message containing available
             * seat numbers.
             */
            StringBuilder message =
                    new StringBuilder();

            message.append(
                    "Available Seats for "
            );

            message.append(
                    travelDate
            );

            message.append(
                    ":\n\n"
            );


            for (Seat seat :
                    availableSeats) {

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
                    "Enter travel date using YYYY-MM-DD.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * RESERVATION SUMMARY
     * =====================================================
     */
    private void displayReservationSummary() {

        int activeCount = 0;
        int cancelledCount = 0;


        for (Reservation reservation :
                reservationService.getReservations()) {

            if (reservation
                    .getStatus()
                    .equals("ACTIVE")) {

                activeCount++;

            } else if (reservation
                    .getStatus()
                    .equals("CANCELLED")) {

                cancelledCount++;
            }
        }


        int total =
                activeCount
                        + cancelledCount;


        JOptionPane.showMessageDialog(
                this,

                "===== RESERVATION SUMMARY =====\n\n"
                        + "Total Reservations: "
                        + total
                        + "\n"
                        + "Active Reservations: "
                        + activeCount
                        + "\n"
                        + "Cancelled Reservations: "
                        + cancelledCount,

                "Reservation Summary",

                JOptionPane.INFORMATION_MESSAGE
        );
    }
}