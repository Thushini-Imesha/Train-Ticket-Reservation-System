package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Service.RouteService;
import Service.StationService;
import model.Station;
import model.Route;

/*
 * =====================================================
 * ROUTE MANAGEMENT FRAME
 * =====================================================
 *
 * This class creates the GUI for Route Management.
 *
 * Functions:
 * - Add Route
 * - View Routes
 * - Search Route
 * - Update Route
 * - Delete Route
 */
public class RouteManagementFrame extends JFrame {

    private JTextField routeIdField;
    private JComboBox<Station> departureStationComboBox;
    private JComboBox<Station> destinationStationComboBox;
    private JTextField distanceField;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;

    private RouteService routeService;
    private StationService stationService;

    /*
     * Route table components.
     */
    private JTable routeTable;
    private DefaultTableModel tableModel;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public RouteManagementFrame(
            RouteService routeService,
            StationService stationService) {

        this.routeService = routeService;
        this.stationService = stationService;

        setTitle("Route Management");
        setSize(850, 650);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

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
                        "ROUTE MANAGEMENT"
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
         * ROUTE ID
         * =================================================
         */
        JLabel routeIdLabel =
                new JLabel(
                        "Route ID:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(
                routeIdLabel,
                gbc
        );

        routeIdField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                routeIdField,
                gbc
        );

        /*
         * =================================================
         * BUTTONS
         * =================================================
         */
        addButton =
                new JButton(
                        "Add Route"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                addButton,
                gbc
        );

        viewButton =
                new JButton(
                        "View Routes"
                );

        gbc.gridx = 1;
        gbc.gridy = 5;

        panel.add(
                viewButton,
                gbc
        );

        searchButton =
                new JButton(
                        "Search Route"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                searchButton,
                gbc
        );

        updateButton =
                new JButton(
                        "Update Route"
                );

        gbc.gridx = 1;
        gbc.gridy = 6;

        panel.add(
                updateButton,
                gbc
        );

        deleteButton =
                new JButton(
                        "Delete Route"
                );

        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                deleteButton,
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
         * =================================================
         * DEPARTURE STATION
         * =================================================
         */
        JLabel departureStationLabel =
                new JLabel("Departure Station:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                departureStationLabel,
                gbc
        );

        departureStationComboBox =
                new JComboBox<>();

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                departureStationComboBox,
                gbc
        );


        /*
         * =================================================
         * DESTINATION STATION
         * =================================================
         */
        JLabel destinationStationLabel =
                new JLabel("Destination Station:");

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                destinationStationLabel,
                gbc
        );

        destinationStationComboBox =
                new JComboBox<>();

        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(
                destinationStationComboBox,
                gbc
        );


        /*
         * =================================================
         * DISTANCE
         * =================================================
         */
        JLabel distanceLabel =
                new JLabel("Distance (km):");

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(
                distanceLabel,
                gbc
        );

        distanceField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(
                distanceField,
                gbc
        );
        /*
         * =====================================================
         * ROUTE TABLE
         * =====================================================
         */

        String[] columnNames = {
                "Route ID",
                "Departure Station",
                "Destination Station",
                "Distance (km)"
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
        routeTable =
                new JTable(
                        tableModel
                );


        /*
         * Put JTable inside a scroll pane.
         */
        JScrollPane scrollPane =
                new JScrollPane(
                        routeTable
                );


        /*
         * Place table below all buttons.
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
         * =====================================================
         * LOAD STATIONS INTO COMBO BOXES
         * =====================================================
         */
        for (Station station :
                stationService.getStations()) {

            departureStationComboBox.addItem(
                    station
            );

            destinationStationComboBox.addItem(
                    station
            );
        }

        /*
         * =====================================================
         * ADD ROUTE BUTTON EVENT
         * =====================================================
         */
        addButton.addActionListener(e -> {

            try {

                /*
                 * Read Route ID.
                 */
                int routeId =
                        Integer.parseInt(
                                routeIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Read selected stations.
                 */
                Station departureStation =
                        (Station)
                                departureStationComboBox
                                        .getSelectedItem();

                Station destinationStation =
                        (Station)
                                destinationStationComboBox
                                        .getSelectedItem();


                /*
                 * Read distance.
                 */
                double distance =
                        Double.parseDouble(
                                distanceField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Check whether stations are available.
                 */
                if (departureStation == null
                        || destinationStation == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please add stations before creating a route.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Departure and destination
                 * cannot be the same station.
                 */
                if (departureStation == destinationStation) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Departure and Destination stations cannot be the same.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Distance must be greater than zero.
                 */
                if (distance <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Distance must be greater than 0.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Add Route.
                 */
                boolean added =
                        routeService.addRoute(
                                routeId,
                                departureStation,
                                destinationStation,
                                distance
                        );


                if (added) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    routeIdField.setText("");
                    distanceField.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route could not be added. Check the Route ID and stations.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Route ID must be a whole number and Distance must be numeric.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * VIEW ROUTES BUTTON EVENT
         * =====================================================
         */
        viewButton.addActionListener(e -> {

            /*
             * Clear old table rows.
             */
            tableModel.setRowCount(0);


            /*
             * Check whether routes exist.
             */
            if (routeService.getRoutes().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No routes found.",
                        "Route List",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            /*
             * Display every route.
             */
            for (Route route :
                    routeService.getRoutes()) {

                Object[] row = {

                        route.getRouteId(),

                        route.getDepartureStation(),

                        route.getDestinationStation(),

                        route.getDistance()
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
         * SEARCH ROUTE BUTTON EVENT
         * =====================================================
         */
        searchButton.addActionListener(e -> {

            try {

                /*
                 * Read Route ID.
                 */
                int routeId =
                        Integer.parseInt(
                                routeIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Search for route.
                 */
                Route route =
                        routeService.findRouteById(
                                routeId
                        );


                /*
                 * Check whether route exists.
                 */
                if (route != null) {

                    /*
                     * Select the saved departure station.
                     */
                    departureStationComboBox.setSelectedItem(
                            route.getDepartureStation()
                    );


                    /*
                     * Select the saved destination station.
                     */
                    destinationStationComboBox.setSelectedItem(
                            route.getDestinationStation()
                    );


                    /*
                     * Display distance.
                     */
                    distanceField.setText(
                            String.valueOf(
                                    route.getDistance()
                            )
                    );


                    JOptionPane.showMessageDialog(
                            this,
                            "Route found!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    /*
                     * Clear old distance.
                     */
                    distanceField.setText("");


                    JOptionPane.showMessageDialog(
                            this,
                            "Route not found.",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Route ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * UPDATE ROUTE BUTTON EVENT
         * =====================================================
         */
        updateButton.addActionListener(e -> {

            try {

                /*
                 * Read Route ID.
                 */
                int routeId =
                        Integer.parseInt(
                                routeIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Get selected Departure Station.
                 */
                Station departureStation =
                        (Station)
                                departureStationComboBox
                                        .getSelectedItem();


                /*
                 * Get selected Destination Station.
                 */
                Station destinationStation =
                        (Station)
                                destinationStationComboBox
                                        .getSelectedItem();


                /*
                 * Read distance.
                 */
                double distance =
                        Double.parseDouble(
                                distanceField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Check whether stations exist.
                 */
                if (departureStation == null
                        || destinationStation == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please select both stations.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Check whether both stations
                 * are the same.
                 */
                if (departureStation == destinationStation) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Departure and Destination stations cannot be the same.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Distance must be greater than zero.
                 */
                if (distance <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Distance must be greater than 0.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                /*
                 * Update route through RouteService.
                 */
                boolean updated =
                        routeService.updateRoute(
                                routeId,
                                departureStation,
                                destinationStation,
                                distance
                        );


                /*
                 * Check update result.
                 */
                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Route route :
                            routeService.getRoutes()) {

                        Object[] row = {

                                route.getRouteId(),

                                route.getDepartureStation(),

                                route.getDestinationStation(),

                                route.getDistance()
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route not found or invalid route details.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Route ID must be a whole number and Distance must be numeric.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * DELETE ROUTE BUTTON EVENT
         * =====================================================
         */
        deleteButton.addActionListener(e -> {

            try {

                /*
                 * Read Route ID.
                 */
                int routeId =
                        Integer.parseInt(
                                routeIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Ask user to confirm deletion.
                 */
                int answer =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this route?",
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
                 * Delete route through RouteService.
                 */
                boolean deleted =
                        routeService.deleteRoute(
                                routeId
                        );


                /*
                 * Check result.
                 */
                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    /*
                     * Clear input fields.
                     */
                    routeIdField.setText("");
                    distanceField.setText("");


                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Route route :
                            routeService.getRoutes()) {

                        Object[] row = {

                                route.getRouteId(),
                                route.getDepartureStation(),
                                route.getDestinationStation(),
                                route.getDistance()
                        };

                        tableModel.addRow(
                                row
                        );
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Route not found.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Route ID.",
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