package GUI;

import javax.swing.*;
import java.awt.*;

import Service.TrainService;
import javax.swing.table.DefaultTableModel;
import model.Train;

/*
 * =====================================================
 * TRAIN MANAGEMENT FRAME
 * =====================================================
 *
 * This class creates the GUI for Train Management.
 *
 * Functions:
 * - Add Train
 * - View Trains
 * - Search Train
 * - Update Train
 * - Delete Train
 */
public class TrainManagementFrame extends JFrame {

    private JTable trainTable;
    private DefaultTableModel tableModel;

    private JTextField trainIdField;
    private JTextField trainNameField;
    private JTextField trainTypeField;
    private JTextField departureStationField;
    private JTextField destinationStationField;
    private JTextField departureTimeField;
    private JTextField ticketPriceField;
    private TrainService trainService;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public TrainManagementFrame(TrainService trainService) {

        this.trainService = trainService;


        setTitle("Train Management");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /*
         * =================================================
         * TITLE
         * =================================================
         */
        JLabel titleLabel = new JLabel("TRAIN MANAGEMENT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        /*
         * =================================================
         * TRAIN ID
         * =================================================
         */
        JLabel trainIdLabel =
                new JLabel("Train ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(trainIdLabel, gbc);

        trainIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(trainIdField, gbc);

        /*
         * =================================================
         * TRAIN NAME
         * =================================================
         */
        JLabel trainNameLabel = new JLabel("Train Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(trainNameLabel, gbc);
        trainNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        panel.add(trainNameField, gbc);

        /*
         * =================================================
         * TRAIN TYPE
         * =================================================
         */
        JLabel trainTypeLabel = new JLabel("Train Type:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panel.add(trainTypeLabel, gbc);

        trainTypeField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        panel.add(trainTypeField, gbc);

        /*
         * =================================================
         * DEPARTURE STATION
         * =================================================
         */
        JLabel departureStationLabel = new JLabel("Departure Station:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panel.add(departureStationLabel, gbc);

        departureStationField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        panel.add(departureStationField, gbc);

        /*
         * =================================================
         * DESTINATION STATION
         * =================================================
         */
        JLabel destinationStationLabel = new JLabel("Destination Station:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        panel.add(destinationStationLabel, gbc);

        destinationStationField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        panel.add(destinationStationField, gbc);

        /*
         * =================================================
         * DEPARTURE TIME
         * =================================================
         */
        JLabel departureTimeLabel = new JLabel("Departure Time:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        panel.add(departureTimeLabel, gbc);

        departureTimeField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        panel.add(departureTimeField, gbc);

        /*
         * =================================================
         * TICKET PRICE
         * =================================================
         */
        JLabel ticketPriceLabel = new JLabel("Ticket Price:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0;
        panel.add(ticketPriceLabel, gbc);

        ticketPriceField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        panel.add(ticketPriceField, gbc);

        /*
         * =====================================================
         * BUTTONS
         * =====================================================
         */

        addButton = new JButton("Add Train");
        viewButton = new JButton("View Trains");
        searchButton = new JButton("Search Train");
        updateButton = new JButton("Update Train");
        deleteButton = new JButton("Delete Train");
        backButton = new JButton("Back");


        /*
         * IMPORTANT:
         * Reset GridBag values before adding buttons.
         */
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        /*
         * =================================================
         * ADD BUTTON
         * =================================================
         */
        addButton = new JButton("Add Train");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(addButton, gbc);

        /*
         * =================================================
         * VIEW BUTTON
         * =================================================
         */
        viewButton = new JButton("View Trains");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(viewButton, gbc);

        /*
         * =================================================
         * SEARCH BUTTON
         * =================================================
         */
        searchButton = new JButton("Search Train");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(searchButton, gbc);

        /*
         * =================================================
         * UPDATE BUTTON
         * =================================================
         */
        updateButton = new JButton("Update Train");
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(updateButton, gbc);

        /*
         * =================================================
         * DELETE BUTTON
         * =================================================
         */
        deleteButton = new JButton("Delete Train");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(deleteButton, gbc);

        /*
         * =================================================
         * BACK BUTTON
         * =================================================
         */
        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(backButton, gbc);
        /*
         * =====================================================
         * TRAIN TABLE
         * =====================================================
         */

        String[] columnNames = {
                "Train ID",
                "Train Name",
                "Train Type",
                "Departure Station",
                "Destination Station",
                "Departure Time",
                "Ticket Price"
        };


        /*
         * Create table model.
         */
        tableModel =
                new DefaultTableModel(
                        columnNames,
                        0
                ) {

                    /*
                     * Prevent users from editing
                     * table cells directly.
                     */
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
        trainTable =
                new JTable(
                        tableModel
                );


        /*
         * Put JTable inside a scroll pane.
         */
        JScrollPane scrollPane =
                new JScrollPane(
                        trainTable
                );


        /*
         * Place table below buttons.
         */
        gbc.gridx = 0;
        gbc.gridy = 11;

        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.BOTH;

        //gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        panel.add(
                scrollPane,
                gbc
        );

        /*
         * =====================================================
         * ADD TRAIN BUTTON EVENT
         * =====================================================
         */
        addButton.addActionListener(e -> {

            try {

                /*
                 * Read Train ID.
                 */
                int trainId =
                        Integer.parseInt(
                                trainIdField
                                        .getText()
                                        .trim()
                        );

                /*
                 * Read Train Name.
                 */
                String trainName =
                        trainNameField
                                .getText()
                                .trim();

                /*
                 * Read Train Type.
                 */
                String trainType =
                        trainTypeField
                                .getText()
                                .trim();

                /*
                 * Read Departure Station.
                 */
                String departureStation =
                        departureStationField
                                .getText()
                                .trim();

                /*
                 * Read Destination Station.
                 */
                String destinationStation =
                        destinationStationField
                                .getText()
                                .trim();

                /*
                 * Read Departure Time.
                 */
                String departureTime =
                        departureTimeField
                                .getText()
                                .trim();

                /*
                 * Read Ticket Price.
                 */
                double ticketPrice =
                        Double.parseDouble(
                                ticketPriceField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Check empty fields.
                 */
                if (trainName.isEmpty()
                        || trainType.isEmpty()
                        || departureStation.isEmpty()
                        || destinationStation.isEmpty()
                        || departureTime.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please complete all train details.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Add train through TrainService.
                 */
                boolean added =
                        trainService.addTrain(
                                trainId,
                                trainName,
                                trainType,
                                departureStation,
                                destinationStation,
                                departureTime,
                                ticketPrice
                        );


                /*
                 * Check result.
                 */
                if (added) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    /*
                     * Clear fields.
                     */
                    trainIdField.setText("");
                    trainNameField.setText("");
                    trainTypeField.setText("");
                    departureStationField.setText("");
                    destinationStationField.setText("");
                    departureTimeField.setText("");
                    ticketPriceField.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train ID already exists!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Train ID must be a whole number and Ticket Price must be numeric.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        /*
         * =====================================================
         * VIEW TRAINS BUTTON EVENT
         * =====================================================
         */
        viewButton.addActionListener(e -> {

            /*
             * Remove old rows before displaying
             * the current train list.
             */
            tableModel.setRowCount(0);


            /*
             * Check whether trains exist.
             */
            if (trainService.getTrains().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No trains found.",
                        "Train List",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }
            /*
             * Read each Train object from TrainService.
             */
            for (Train train :
                    trainService.getTrains()) {

                /*
                 * Create one table row.
                 */
                Object[] row = {
                        train.getTrainId(),
                        train.getTrainName(),
                        train.getTrainType(),
                        train.getDepartureStation(),
                        train.getDestinationStation(),
                        train.getDepartureTime(),
                        train.getTicketPrice()
                };


                /*
                 * Add row to JTable.
                 */
                tableModel.addRow(
                        row
                );
            }
        });


        /*
         * =====================================================
         * SEARCH TRAIN BUTTON EVENT
         * =====================================================
         */
        searchButton.addActionListener(e -> {

            try {

                /*
                 * Read Train ID from the GUI.
                 */
                int trainId =
                        Integer.parseInt(
                                trainIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Search for the train.
                 */
                Train train =
                        trainService.findTrainById(
                                trainId
                        );


                /*
                 * Check whether train exists.
                 */
                if (train != null) {

                    /*
                     * Fill all GUI fields with
                     * the train information.
                     */
                    trainNameField.setText(
                            train.getTrainName()
                    );

                    trainTypeField.setText(
                            train.getTrainType()
                    );

                    departureStationField.setText(
                            train.getDepartureStation()
                    );

                    destinationStationField.setText(
                            train.getDestinationStation()
                    );

                    departureTimeField.setText(
                            train.getDepartureTime()
                    );

                    ticketPriceField.setText(
                            String.valueOf(
                                    train.getTicketPrice()
                            )
                    );


                    JOptionPane.showMessageDialog(
                            this,
                            "Train found!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    /*
                     * Clear old information.
                     */
                    trainNameField.setText("");
                    trainTypeField.setText("");
                    departureStationField.setText("");
                    destinationStationField.setText("");
                    departureTimeField.setText("");
                    ticketPriceField.setText("");


                    JOptionPane.showMessageDialog(
                            this,
                            "Train not found.",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Train ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * UPDATE TRAIN BUTTON EVENT
         * =====================================================
         */
        updateButton.addActionListener(e -> {

            try {

                /*
                 * Read Train ID.
                 */
                int trainId =
                        Integer.parseInt(
                                trainIdField
                                        .getText()
                                        .trim()
                        );

                /*
                 * Read updated values.
                 */
                String trainName =
                        trainNameField
                                .getText()
                                .trim();

                String trainType =
                        trainTypeField
                                .getText()
                                .trim();

                String departureStation =
                        departureStationField
                                .getText()
                                .trim();

                String destinationStation =
                        destinationStationField
                                .getText()
                                .trim();

                String departureTime =
                        departureTimeField
                                .getText()
                                .trim();

                double ticketPrice =
                        Double.parseDouble(
                                ticketPriceField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Check for empty fields.
                 */
                if (trainName.isEmpty()
                        || trainType.isEmpty()
                        || departureStation.isEmpty()
                        || destinationStation.isEmpty()
                        || departureTime.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please complete all train details.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Update train through TrainService.
                 */
                boolean updated =
                        trainService.updateTrain(
                                trainId,
                                trainName,
                                trainType,
                                departureStation,
                                destinationStation,
                                departureTime,
                                ticketPrice
                        );


                /*
                 * Check result.
                 */
                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Train train :
                            trainService.getTrains()) {

                        Object[] row = {

                                train.getTrainId(),
                                train.getTrainName(),
                                train.getTrainType(),
                                train.getDepartureStation(),
                                train.getDestinationStation(),
                                train.getDepartureTime(),
                                train.getTicketPrice()
                        };

                        tableModel.addRow(row);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train not found.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Train ID must be a whole number and Ticket Price must be numeric.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * DELETE TRAIN BUTTON EVENT
         * =====================================================
         */
        deleteButton.addActionListener(e -> {

            try {

                /*
                 * Read Train ID.
                 */
                int trainId =
                        Integer.parseInt(
                                trainIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Ask user to confirm deletion.
                 */
                int answer =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this train?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                /*
                 * Stop if user selects No.
                 */
                if (answer != JOptionPane.YES_OPTION) {

                    return;
                }


                /*
                 * Delete train through TrainService.
                 */
                boolean deleted =
                        trainService.deleteTrain(
                                trainId
                        );


                /*
                 * Check result.
                 */
                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Clear input fields.
                     */
                    trainIdField.setText("");
                    trainNameField.setText("");
                    trainTypeField.setText("");
                    departureStationField.setText("");
                    destinationStationField.setText("");
                    departureTimeField.setText("");
                    ticketPriceField.setText("");


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Train train :
                            trainService.getTrains()) {

                        Object[] row = {

                                train.getTrainId(),
                                train.getTrainName(),
                                train.getTrainType(),
                                train.getDepartureStation(),
                                train.getDestinationStation(),
                                train.getDepartureTime(),
                                train.getTicketPrice()
                        };

                        tableModel.addRow(row);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Train not found.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Train ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        backButton.addActionListener(e -> {
            dispose();
        });
        add(panel);
        setVisible(true);
    }
}