package GUI;

import Service.SeatService;
import Service.TrainService;

import model.Train;
import model.Seat;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;


/*
 * =====================================================
 * SEAT MANAGEMENT FRAME
 * =====================================================
 *
 * This class creates the GUI for Seat Management.
 *
 * Functions will include:
 * - Add Seat
 * - View Seats
 * - Search Seat
 * - Update Seat
 * - Delete Seat
 * - Reserve Seat
 * - Release Seat
 */
public class SeatManagementFrame extends JFrame {

    /*
     * =====================================================
     * GUI COMPONENTS
     * =====================================================
     */

    private JTextField seatIdField;
    private JTextField seatNumberField;

    private JComboBox<Train> trainComboBox;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton reserveButton;
    private JButton releaseButton;
    private JButton backButton;


    /*
     * =====================================================
     * SERVICES
     * =====================================================
     */

    private SeatService seatService;
    private TrainService trainService;
    /*
     * JTable components.
     */
    private JTable seatTable;
    private DefaultTableModel tableModel;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public SeatManagementFrame(
            SeatService seatService,
            TrainService trainService) {

        /*
         * Store the shared services.
         */
        this.seatService = seatService;
        this.trainService = trainService;


        /*
         * =================================================
         * WINDOW SETTINGS
         * =================================================
         */

        setTitle("Seat Management");

        setSize(800, 650);

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
                        10,
                        10,
                        10,
                        10
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
                        "SEAT MANAGEMENT"
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
         * SEAT ID
         * =================================================
         */

        JLabel seatIdLabel =
                new JLabel(
                        "Seat ID:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(
                seatIdLabel,
                gbc
        );


        seatIdField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                seatIdField,
                gbc
        );


        /*
         * =================================================
         * SEAT NUMBER
         * =================================================
         */

        JLabel seatNumberLabel =
                new JLabel(
                        "Seat Number:"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                seatNumberLabel,
                gbc
        );


        seatNumberField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                seatNumberField,
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
         * LOAD TRAINS INTO COMBO BOX
         * =================================================
         *
         * The same TrainService used by Train Management
         * is used here.
         */

        for (Train train :
                trainService.getTrains()) {

            trainComboBox.addItem(
                    train
            );
        }


        /*
         * =================================================
         * BUTTONS
         * =================================================
         */

        addButton =
                new JButton(
                        "Add Seat"
                );

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(
                addButton,
                gbc
        );


        viewButton =
                new JButton(
                        "View Seats"
                );

        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(
                viewButton,
                gbc
        );


        searchButton =
                new JButton(
                        "Search Seat"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                searchButton,
                gbc
        );


        updateButton =
                new JButton(
                        "Update Seat"
                );

        gbc.gridx = 1;
        gbc.gridy = 5;

        panel.add(
                updateButton,
                gbc
        );


        deleteButton =
                new JButton(
                        "Delete Seat"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                deleteButton,
                gbc
        );


        reserveButton =
                new JButton(
                        "Reserve Seat"
                );

        gbc.gridx = 1;
        gbc.gridy = 6;

        panel.add(
                reserveButton,
                gbc
        );


        releaseButton =
                new JButton(
                        "Release Seat"
                );

        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                releaseButton,
                gbc
        );


        backButton =
                new JButton(
                        "Back"
                );

        gbc.gridx = 1;
        gbc.gridy = 7;

        panel.add(
                backButton,
                gbc
        );
        /*
         * =====================================================
         * SEAT TABLE
         * =====================================================
         */

        String[] columnNames = {
                "Seat ID",
                "Seat Number",
                "Train",
                "Available"
        };


        /*
         * Create table model.
         */
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


        /*
         * Create JTable.
         */
        seatTable =
                new JTable(
                        tableModel
                );


        /*
         * Add JTable to ScrollPane.
         */
        JScrollPane scrollPane =
                new JScrollPane(
                        seatTable
                );


        /*
         * Place table below the buttons.
         */
        gbc.gridx = 0;
        gbc.gridy = 8;
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
         * TEMPORARY BUTTON EVENTS
         * =================================================
         *
         * We will connect each function one by one.
         */
        /*
         * =====================================================
         * ADD SEAT BUTTON EVENT
         * =====================================================
         */
        addButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Read Seat Number.
                 */
                int seatNumber =
                        Integer.parseInt(
                                seatNumberField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Get selected Train.
                 */
                Train selectedTrain =
                        (Train)
                                trainComboBox
                                        .getSelectedItem();


                /*
                 * Check whether a train exists.
                 */
                if (selectedTrain == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please add a train before adding seats.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Seat values must be positive.
                 */
                if (seatId <= 0 || seatNumber <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat ID and Seat Number must be greater than 0.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Add seat through SeatService.
                 */
                boolean added =
                        seatService.addSeat(
                                seatId,
                                seatNumber,
                                selectedTrain
                        );


                /*
                 * Check result.
                 */
                if (added) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Clear text fields.
                     */
                    seatIdField.setText("");
                    seatNumberField.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat could not be added. Check Seat ID and Seat Number.",
                            "Add Seat Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seat ID and Seat Number must be whole numbers.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * VIEW SEATS BUTTON EVENT
         * =====================================================
         */
        viewButton.addActionListener(e -> {

            /*
             * Clear old table rows.
             */
            tableModel.setRowCount(0);


            /*
             * Get every seat from every train.
             */
            java.util.ArrayList<Seat> seats =
                    seatService.getAllSeats(
                            trainService.getTrains()
                    );


            /*
             * Check whether seats exist.
             */
            if (seats.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No seats found.",
                        "Seat List",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            /*
             * Display every seat in JTable.
             */
            for (Seat seat : seats) {

                Object[] row = {

                        seat.getSeatId(),

                        seat.getSeatNumber(),

                        seat.getTrain(),

                        seat.isAvailable()
                                ? "Available"
                                : "Reserved"
                };


                tableModel.addRow(
                        row
                );
            }
        });


        /*
         * =====================================================
         * SEARCH SEAT BUTTON EVENT
         * =====================================================
         */
        searchButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Search for the seat.
                 */
                Seat seat =
                        seatService.findSeatById(
                                seatId,
                                trainService.getTrains()
                        );


                /*
                 * Check whether seat was found.
                 */
                if (seat != null) {

                    /*
                     * Display Seat Number.
                     */
                    seatNumberField.setText(
                            String.valueOf(
                                    seat.getSeatNumber()
                            )
                    );


                    /*
                     * Select the Train that belongs
                     * to this seat.
                     */
                    trainComboBox.setSelectedItem(
                            seat.getTrain()
                    );


                    /*
                     * Show result message.
                     */
                    JOptionPane.showMessageDialog(
                            this,
                            "Seat found!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    /*
                     * Clear old value.
                     */
                    seatNumberField.setText("");


                    JOptionPane.showMessageDialog(
                            this,
                            "Seat not found.",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Seat ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * UPDATE SEAT BUTTON EVENT
         * =====================================================
         */
        updateButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Read new Seat Number.
                 */
                int seatNumber =
                        Integer.parseInt(
                                seatNumberField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Get selected Train.
                 */
                Train selectedTrain =
                        (Train)
                                trainComboBox
                                        .getSelectedItem();


                /*
                 * Validate values.
                 */
                if (seatId <= 0 || seatNumber <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat ID and Seat Number must be greater than 0.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                if (selectedTrain == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please select a train.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Update seat through SeatService.
                 */
                boolean updated =
                        seatService.updateSeat(
                                seatId,
                                seatNumber,
                                selectedTrain,
                                trainService.getTrains()
                        );


                /*
                 * Check result.
                 */
                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Seat seat :
                            seatService.getAllSeats(
                                    trainService.getTrains()
                            )) {

                        Object[] row = {

                                seat.getSeatId(),
                                seat.getSeatNumber(),
                                seat.getTrain(),
                                seat.isAvailable()
                                        ? "Available"
                                        : "Reserved"
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat not found or Seat Number already exists for that train.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seat ID and Seat Number must be whole numbers.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * DELETE SEAT BUTTON EVENT
         * =====================================================
         */
        deleteButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Ask user for confirmation.
                 */
                int answer =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this seat?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                /*
                 * Stop if user chooses No.
                 */
                if (answer != JOptionPane.YES_OPTION) {
                    return;
                }


                /*
                 * Delete seat through SeatService.
                 */
                boolean deleted =
                        seatService.deleteSeat(
                                seatId,
                                trainService.getTrains()
                        );


                /*
                 * Check result.
                 */
                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Clear text fields.
                     */
                    seatIdField.setText("");
                    seatNumberField.setText("");


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Seat seat :
                            seatService.getAllSeats(
                                    trainService.getTrains()
                            )) {

                        Object[] row = {

                                seat.getSeatId(),
                                seat.getSeatNumber(),
                                seat.getTrain(),
                                seat.isAvailable()
                                        ? "Available"
                                        : "Reserved"
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat not found.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Seat ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * RESERVE SEAT BUTTON EVENT
         * =====================================================
         */
        reserveButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Find the seat.
                 */
                Seat seat =
                        seatService.findSeatById(
                                seatId,
                                trainService.getTrains()
                        );


                /*
                 * Check whether seat exists.
                 */
                if (seat == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat not found.",
                            "Reserve Seat",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Check whether seat is already reserved.
                 */
                if (!seat.isAvailable()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "This seat is already reserved.",
                            "Reserve Seat",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                /*
                 * Reserve the seat.
                 */
                seat.reserveSeat();


                JOptionPane.showMessageDialog(
                        this,
                        "Seat reserved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                /*
                 * Refresh JTable.
                 */
                tableModel.setRowCount(0);

                for (Seat currentSeat :
                        seatService.getAllSeats(
                                trainService.getTrains()
                        )) {

                    Object[] row = {

                            currentSeat.getSeatId(),
                            currentSeat.getSeatNumber(),
                            currentSeat.getTrain(),

                            currentSeat.isAvailable()
                                    ? "Available"
                                    : "Reserved"
                    };

                    tableModel.addRow(row);
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Seat ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * RELEASE SEAT BUTTON EVENT
         * =====================================================
         */
        releaseButton.addActionListener(e -> {

            try {

                /*
                 * Read Seat ID.
                 */
                int seatId =
                        Integer.parseInt(
                                seatIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Find the seat.
                 */
                Seat seat =
                        seatService.findSeatById(
                                seatId,
                                trainService.getTrains()
                        );


                /*
                 * Check whether seat exists.
                 */
                if (seat == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seat not found.",
                            "Release Seat",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Check whether seat is already available.
                 */
                if (seat.isAvailable()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "This seat is already available.",
                            "Release Seat",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                /*
                 * Release the seat.
                 */
                seat.releaseSeat();


                JOptionPane.showMessageDialog(
                        this,
                        "Seat released successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                /*
                 * Refresh JTable.
                 */
                tableModel.setRowCount(0);

                for (Seat currentSeat :
                        seatService.getAllSeats(
                                trainService.getTrains()
                        )) {

                    Object[] row = {

                            currentSeat.getSeatId(),
                            currentSeat.getSeatNumber(),
                            currentSeat.getTrain(),

                            currentSeat.isAvailable()
                                    ? "Available"
                                    : "Reserved"
                    };

                    tableModel.addRow(row);
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Seat ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * Back to dashboard.
         */
        backButton.addActionListener(e -> {

            dispose();
        });


        /*
         * Add panel to frame.
         */
        add(panel);


        /*
         * Display window.
         */
        setVisible(true);
    }
}