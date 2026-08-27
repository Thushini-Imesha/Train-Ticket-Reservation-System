package GUI;

import Service.StationService;
import model.Station;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/*
 * =====================================================
 * STATION MANAGEMENT FRAME
 * =====================================================
 *
 * This class creates the GUI for Station Management.
 *
 * Functions:
 * - Add Station
 * - View Stations
 * - Search Station
 * - Update Station
 * - Delete Station
 */
public class StationManagementFrame extends JFrame {

    private JTextField stationIdField;
    private JTextField stationNameField;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;

    private StationService stationService;

    /*
     * Table used to display station records.
     */
    private JTable stationTable;
    private DefaultTableModel tableModel;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public StationManagementFrame(StationService stationService) {
        this.stationService = stationService;

        /*
         * Window settings.
         */
        setTitle("Station Management");
        setSize(700, 550);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        /*
         * Create main panel.
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
                        "STATION MANAGEMENT"
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
         * STATION ID
         * =================================================
         */
        JLabel stationIdLabel =
                new JLabel(
                        "Station ID:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(
                stationIdLabel,
                gbc
        );


        stationIdField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                stationIdField,
                gbc
        );


        /*
         * =================================================
         * STATION NAME
         * =================================================
         */
        JLabel stationNameLabel =
                new JLabel(
                        "Station Name:"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                stationNameLabel,
                gbc
        );


        stationNameField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                stationNameField,
                gbc
        );


        /*
         * =================================================
         * ADD BUTTON
         * =================================================
         */
        addButton =
                new JButton(
                        "Add Station"
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                addButton,
                gbc
        );


        /*
         * =================================================
         * VIEW BUTTON
         * =================================================
         */
        viewButton =
                new JButton(
                        "View Stations"
                );

        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(
                viewButton,
                gbc
        );


        /*
         * =================================================
         * SEARCH BUTTON
         * =================================================
         */
        searchButton =
                new JButton(
                        "Search Station"
                );

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(
                searchButton,
                gbc
        );


        /*
         * =================================================
         * UPDATE BUTTON
         * =================================================
         */
        updateButton =
                new JButton(
                        "Update Station"
                );

        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(
                updateButton,
                gbc
        );


        /*
         * =================================================
         * DELETE BUTTON
         * =================================================
         */
        deleteButton =
                new JButton(
                        "Delete Station"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                deleteButton,
                gbc
        );


        /*
         * =================================================
         * BACK BUTTON
         * =================================================
         */
        backButton =
                new JButton(
                        "Back"
                );

        gbc.gridx = 1;
        gbc.gridy = 5;

        panel.add(
                backButton,
                gbc
        );

        /*
         * =====================================================
         * STATION TABLE
         * =====================================================
         */

        String[] columnNames = {
                "Station ID",
                "Station Name"
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
        stationTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stationTable);

        /*
         * Place table below buttons.
         */
        gbc.gridx = 0;
        gbc.gridy = 6;

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
         */

        addButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Add Station will be connected next."
            );
        });


        /*
         * =====================================================
         * VIEW STATIONS BUTTON EVENT
         * =====================================================
         */
        viewButton.addActionListener(e -> {

            /*
             * Remove old rows from the table.
             */
            tableModel.setRowCount(0);


            /*
             * Check whether stations exist.
             */
            if (stationService.getStations().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No stations found.",
                        "Station List",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            /*
             * Display every station.
             */
            for (Station station :
                    stationService.getStations()) {

                Object[] row = {

                        station.getStationId(),
                        station.getStationName()
                };


                /*
                 * Add station to JTable.
                 */
                tableModel.addRow(
                        row
                );
            }
        });

        /*
         * =====================================================
         * SEARCH STATION BUTTON EVENT
         * =====================================================
         */
        searchButton.addActionListener(e -> {

            try {

                /*
                 * Read Station ID from GUI.
                 */
                int stationId =
                        Integer.parseInt(
                                stationIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Search station through StationService.
                 */
                Station station =
                        stationService.findStationById(
                                stationId
                        );


                /*
                 * Check whether station exists.
                 */
                if (station != null) {

                    /*
                     * Display Station Name.
                     */
                    stationNameField.setText(
                            station.getStationName()
                    );


                    JOptionPane.showMessageDialog(
                            this,
                            "Station found!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    /*
                     * Clear old station name.
                     */
                    stationNameField.setText("");


                    JOptionPane.showMessageDialog(
                            this,
                            "Station not found.",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Station ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * UPDATE STATION BUTTON EVENT
         * =====================================================
         */
        updateButton.addActionListener(e -> {

            try {

                /*
                 * Read Station ID.
                 */
                int stationId =
                        Integer.parseInt(
                                stationIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Read new Station Name.
                 */
                String stationName =
                        stationNameField
                                .getText()
                                .trim();


                /*
                 * Check whether Station Name is empty.
                 */
                if (stationName.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the Station Name.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Update station through StationService.
                 */
                boolean updated =
                        stationService.updateStation(
                                stationId,
                                stationName
                        );


                /*
                 * Check update result.
                 */
                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Station updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Station station :
                            stationService.getStations()) {

                        Object[] row = {
                                station.getStationId(),
                                station.getStationName()
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Station not found.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Station ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * DELETE STATION BUTTON EVENT
         * =====================================================
         */
        deleteButton.addActionListener(e -> {

            try {

                /*
                 * Read Station ID.
                 */
                int stationId =
                        Integer.parseInt(
                                stationIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Ask for confirmation.
                 */
                int answer =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this station?",
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
                 * Delete station through StationService.
                 */
                boolean deleted =
                        stationService.deleteStation(
                                stationId
                        );


                /*
                 * Check delete result.
                 */
                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Station deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Clear input fields.
                     */
                    stationIdField.setText("");
                    stationNameField.setText("");


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Station station :
                            stationService.getStations()) {

                        Object[] row = {

                                station.getStationId(),
                                station.getStationName()
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Station not found.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Station ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * Close only the Station window.
         */
        backButton.addActionListener(e -> {

            dispose();
        });


        /*
         * Add panel to frame.
         */
        add(panel);


        /*
         * Display frame.
         */
        setVisible(true);
    }
}