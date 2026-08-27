package GUI;

import Service.PassengerService;
import model.Passanger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
 * =====================================================
 * PASSENGER MANAGEMENT FRAME
 * =====================================================
 */
public class PassengerManagementFrame extends JFrame {

    private JTextField passengerIdField;
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField telephoneField;
    private JTextField nicField;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;

    private PassengerService passengerService;

    private JTable passengerTable;
    private DefaultTableModel tableModel;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public PassengerManagementFrame(
            PassengerService passengerService) {

        /*
         * Store PassengerService.
         */
        this.passengerService = passengerService;

        /*
         * Window settings.
         */
        setTitle("Passenger Management");
        setSize(750, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        /*
         * =====================================================
         * CREATE PANEL AND LAYOUT FIRST
         * =====================================================
         */
        JPanel panel = new JPanel();

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
                        "PASSENGER MANAGEMENT"
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
         * PASSENGER ID
         * =================================================
         */
        JLabel idLabel =
                new JLabel(
                        "Passenger ID:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(
                idLabel,
                gbc
        );

        passengerIdField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                passengerIdField,
                gbc
        );


        /*
         * =================================================
         * USERNAME
         * =================================================
         */
        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                usernameLabel,
                gbc
        );

        usernameField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                usernameField,
                gbc
        );


        /*
         * =================================================
         * EMAIL
         * =================================================
         */
        JLabel emailLabel =
                new JLabel(
                        "Email:"
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                emailLabel,
                gbc
        );

        emailField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(
                emailField,
                gbc
        );


        /*
         * =================================================
         * TELEPHONE
         * =================================================
         */
        JLabel telephoneLabel =
                new JLabel(
                        "Telephone:"
                );

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(
                telephoneLabel,
                gbc
        );

        telephoneField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(
                telephoneField,
                gbc
        );


        /*
         * =================================================
         * NIC
         * =================================================
         */
        JLabel nicLabel =
                new JLabel(
                        "NIC:"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                nicLabel,
                gbc
        );

        nicField =
                new JTextField(15);

        gbc.gridx = 1;
        gbc.gridy = 5;

        panel.add(
                nicField,
                gbc
        );


        /*
         * =================================================
         * BUTTONS
         * =================================================
         */
        addButton =
                new JButton(
                        "Add Passenger"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                addButton,
                gbc
        );


        viewButton =
                new JButton(
                        "View Passengers"
                );

        gbc.gridx = 1;
        gbc.gridy = 6;

        panel.add(
                viewButton,
                gbc
        );


        searchButton =
                new JButton(
                        "Search Passenger"
                );

        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                searchButton,
                gbc
        );


        updateButton =
                new JButton(
                        "Update Passenger"
                );

        gbc.gridx = 1;
        gbc.gridy = 7;

        panel.add(
                updateButton,
                gbc
        );


        deleteButton =
                new JButton(
                        "Delete Passenger"
                );

        gbc.gridx = 0;
        gbc.gridy = 8;

        panel.add(
                deleteButton,
                gbc
        );


        backButton =
                new JButton(
                        "Back"
                );

        gbc.gridx = 1;
        gbc.gridy = 8;

        panel.add(
                backButton,
                gbc
        );


        /*
         * =====================================================
         * PASSENGER TABLE
         * =====================================================
         */

        String[] columnNames = {
                "Passenger ID",
                "Username",
                "Email",
                "Telephone",
                "NIC"
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

        passengerTable =
                new JTable(
                        tableModel
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        passengerTable
                );

        gbc.gridx = 0;
        gbc.gridy = 9;
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
         * ADD PASSENGER BUTTON EVENT
         * =====================================================
         */
        addButton.addActionListener(e -> {

            try {

                int passengerId =
                        Integer.parseInt(
                                passengerIdField
                                        .getText()
                                        .trim()
                        );

                String username =
                        usernameField
                                .getText()
                                .trim();

                String email =
                        emailField
                                .getText()
                                .trim();

                String telephone =
                        telephoneField
                                .getText()
                                .trim();

                String nic =
                        nicField
                                .getText()
                                .trim();


                /*
                 * Check empty fields.
                 */
                if (username.isEmpty()
                        || email.isEmpty()
                        || telephone.isEmpty()
                        || nic.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please complete all passenger details.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }


                boolean added =
                        passengerService.addPassenger(
                                passengerId,
                                username,
                                email,
                                telephone,
                                nic
                        );


                if (added) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    passengerIdField.setText("");
                    usernameField.setText("");
                    emailField.setText("");
                    telephoneField.setText("");
                    nicField.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger ID already exists!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Passenger ID must be a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * VIEW PASSENGERS BUTTON EVENT
         * =====================================================
         */
        viewButton.addActionListener(e -> {

            /*
             * Clear existing rows.
             */
            tableModel.setRowCount(0);


            /*
             * Check whether list is empty.
             */
            if (passengerService.getPassangers().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No passengers found.", "Passenger List", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            /*
             * Add passengers to JTable.
             */
            for (Passanger passenger :
                    passengerService.getPassangers()) {

                Object[] row = {
                        passenger.getUserid(),
                        passenger.getUsername(),
                        passenger.getEmail(),
                        passenger.getTele(),
                        passenger.getNic()
                };

                tableModel.addRow(row);
            }
        });


        /*
         * =====================================================
         * SEARCH PASSENGER BUTTON EVENT
         * =====================================================
         */
        searchButton.addActionListener(e -> {

            try {

                /*
                 * Read the Passenger ID from the GUI.
                 */
                int passengerId =
                        Integer.parseInt(
                                passengerIdField
                                        .getText()
                                        .trim()
                        );


                /*
                 * Search passenger through PassengerService.
                 */
                Passanger passenger =
                        passengerService.findPassengerById(
                                passengerId
                        );


                /*
                 * Check whether passenger was found.
                 */
                if (passenger != null) {

                    /*
                     * Display passenger information
                     * inside the GUI text fields.
                     */
                    usernameField.setText(
                            passenger.getUsername()
                    );

                    emailField.setText(
                            passenger.getEmail()
                    );

                    telephoneField.setText(
                            passenger.getTele()
                    );

                    nicField.setText(
                            passenger.getNic()
                    );


                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger found!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    /*
                     * Clear old information.
                     */
                    usernameField.setText("");
                    emailField.setText("");
                    telephoneField.setText("");
                    nicField.setText("");


                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger not found.",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Passenger ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * =====================================================
         * UPDATE PASSENGER BUTTON EVENT
         * =====================================================
         */
        updateButton.addActionListener(e -> {

            try {

                /*
                 * Read Passenger ID.
                 */
                int passengerId =
                        Integer.parseInt(
                                passengerIdField
                                        .getText()
                                        .trim()
                        );

                /*
                 * Read updated values.
                 */
                String username =
                        usernameField
                                .getText()
                                .trim();

                String email =
                        emailField
                                .getText()
                                .trim();

                String telephone =
                        telephoneField
                                .getText()
                                .trim();

                String nic =
                        nicField
                                .getText()
                                .trim();

                /*
                 * Check for empty fields.
                 */
                if (username.isEmpty()
                        || email.isEmpty()
                        || telephone.isEmpty()
                        || nic.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please complete all passenger details.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                /*
                 * Update passenger using PassengerService.
                 */
                boolean updated =
                        passengerService.updatePassenger(
                                passengerId,
                                username,
                                email,
                                telephone,
                                nic
                        );

                /*
                 * Check result.
                 */
                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    /*
                     * Refresh table if passenger records
                     * are currently displayed.
                     */
                    tableModel.setRowCount(0);

                    for (Passanger passenger :
                            passengerService.getPassangers()) {

                        Object[] row = {
                                passenger.getUserid(),
                                passenger.getUsername(),
                                passenger.getEmail(),
                                passenger.getTele(),
                                passenger.getNic()
                        };

                        tableModel.addRow(row);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger not found.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Passenger ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        /*
         * =====================================================
         * DELETE PASSENGER BUTTON EVENT
         * =====================================================
         */
        deleteButton.addActionListener(e -> {

            try {

                /*
                 * Read Passenger ID.
                 */
                int passengerId =
                        Integer.parseInt(
                                passengerIdField
                                        .getText()
                                        .trim()
                        );

                /*
                 * Confirm deletion.
                 */
                int answer =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this passenger?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                /*
                 * If user selects NO,
                 * stop the delete operation.
                 */
                if (answer != JOptionPane.YES_OPTION) {

                    return;
                }

                /*
                 * Delete passenger using PassengerService.
                 */
                boolean deleted =
                        passengerService.deletePassenger(
                                passengerId
                        );

                /*
                 * Check delete result.
                 */
                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    /*
                     * Clear text fields.
                     */
                    passengerIdField.setText("");
                    usernameField.setText("");
                    emailField.setText("");
                    telephoneField.setText("");
                    nicField.setText("");

                    /*
                     * Refresh JTable.
                     */
                    tableModel.setRowCount(0);

                    for (Passanger passenger :
                            passengerService.getPassangers()) {

                        Object[] row = {
                                passenger.getUserid(),
                                passenger.getUsername(),
                                passenger.getEmail(),
                                passenger.getTele(),
                                passenger.getNic()
                        };

                        tableModel.addRow(row);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Passenger not found.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Passenger ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        /*
         * Back button.
         */
        backButton.addActionListener(e -> {

            dispose();
        });


        /*
         * Add panel to frame.
         */
        add(panel);

        /*
         * Show window.
         */
        setVisible(true);
    }

}