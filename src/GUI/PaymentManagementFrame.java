package GUI;

import Service.PaymentService;
import Service.ReservationService;

import model.Payment;
import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.time.LocalDate;


/*
 * =====================================================
 * PAYMENT MANAGEMENT FRAME
 * =====================================================
 *
 * Functions:
 *
 * - Make Payment
 * - View Payments
 * - Search Payment
 * - Cancel Payment
 * - Payment Summary
 */
public class PaymentManagementFrame
        extends JFrame {


    /*
     * =====================================================
     * GUI COMPONENTS
     * =====================================================
     */

    private JTextField paymentIdField;

    private JComboBox<Reservation>
            reservationComboBox;

    private JTextField passengerField;

    private JTextField trainField;

    private JTextField amountField;

    private JComboBox<String>
            paymentMethodComboBox;

    private JTextField paymentDateField;


    private JButton makePaymentButton;
    private JButton viewPaymentsButton;
    private JButton searchPaymentButton;
    private JButton cancelPaymentButton;
    private JButton summaryButton;
    private JButton backButton;


    private JTable paymentTable;

    private DefaultTableModel tableModel;


    /*
     * =====================================================
     * SERVICES
     * =====================================================
     */

    private PaymentService paymentService;

    private ReservationService reservationService;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public PaymentManagementFrame(
            PaymentService paymentService,
            ReservationService reservationService) {

        this.paymentService =
                paymentService;

        this.reservationService =
                reservationService;


        /*
         * =================================================
         * WINDOW SETTINGS
         * =================================================
         */

        setTitle(
                "Payment Management"
        );

        setSize(
                900,
                700
        );

        setLocationRelativeTo(
                null
        );

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
                        "PAYMENT MANAGEMENT"
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
         * PAYMENT ID
         * =================================================
         */

        JLabel paymentIdLabel =
                new JLabel(
                        "Payment ID:"
                );


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;


        panel.add(
                paymentIdLabel,
                gbc
        );


        paymentIdField =
                new JTextField(15);


        gbc.gridx = 1;
        gbc.gridy = 1;


        panel.add(
                paymentIdField,
                gbc
        );


        /*
         * =================================================
         * RESERVATION
         * =================================================
         */

        JLabel reservationLabel =
                new JLabel(
                        "Reservation:"
                );


        gbc.gridx = 0;
        gbc.gridy = 2;


        panel.add(
                reservationLabel,
                gbc
        );


        reservationComboBox =
                new JComboBox<>();


        gbc.gridx = 1;
        gbc.gridy = 2;


        panel.add(
                reservationComboBox,
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
        gbc.gridy = 3;


        panel.add(
                passengerLabel,
                gbc
        );


        passengerField =
                new JTextField(15);


        passengerField.setEditable(
                false
        );


        gbc.gridx = 1;
        gbc.gridy = 3;


        panel.add(
                passengerField,
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
        gbc.gridy = 4;


        panel.add(
                trainLabel,
                gbc
        );


        trainField =
                new JTextField(15);


        trainField.setEditable(
                false
        );


        gbc.gridx = 1;
        gbc.gridy = 4;


        panel.add(
                trainField,
                gbc
        );


        /*
         * =================================================
         * AMOUNT
         * =================================================
         */

        JLabel amountLabel =
                new JLabel(
                        "Amount:"
                );


        gbc.gridx = 0;
        gbc.gridy = 5;


        panel.add(
                amountLabel,
                gbc
        );


        amountField =
                new JTextField(15);


        amountField.setEditable(
                false
        );


        gbc.gridx = 1;
        gbc.gridy = 5;


        panel.add(
                amountField,
                gbc
        );


        /*
         * =================================================
         * PAYMENT METHOD
         * =================================================
         */

        JLabel paymentMethodLabel =
                new JLabel(
                        "Payment Method:"
                );


        gbc.gridx = 0;
        gbc.gridy = 6;


        panel.add(
                paymentMethodLabel,
                gbc
        );


        paymentMethodComboBox =
                new JComboBox<>();


        paymentMethodComboBox.addItem(
                "Cash"
        );

        paymentMethodComboBox.addItem(
                "Credit Card"
        );

        paymentMethodComboBox.addItem(
                "Debit Card"
        );


        gbc.gridx = 1;
        gbc.gridy = 6;


        panel.add(
                paymentMethodComboBox,
                gbc
        );


        /*
         * =================================================
         * PAYMENT DATE
         * =================================================
         */

        JLabel paymentDateLabel =
                new JLabel(
                        "Payment Date:"
                );


        gbc.gridx = 0;
        gbc.gridy = 7;


        panel.add(
                paymentDateLabel,
                gbc
        );


        paymentDateField =
                new JTextField(15);


        /*
         * Automatically use today's date.
         */
        paymentDateField.setText(
                LocalDate
                        .now()
                        .toString()
        );


        paymentDateField.setEditable(
                false
        );


        gbc.gridx = 1;
        gbc.gridy = 7;


        panel.add(
                paymentDateField,
                gbc
        );


        /*
         * =================================================
         * LOAD RESERVATIONS
         * =================================================
         */

        loadReservations();


        /*
         * =================================================
         * RESERVATION CHANGE EVENT
         * =================================================
         */

        reservationComboBox
                .addActionListener(e -> {

                    displaySelectedReservation();
                });


        /*
         * Display first reservation automatically.
         */
        displaySelectedReservation();


        /*
         * =================================================
         * BUTTONS
         * =================================================
         */

        makePaymentButton =
                new JButton(
                        "Make Payment"
                );


        viewPaymentsButton =
                new JButton(
                        "View Payments"
                );


        searchPaymentButton =
                new JButton(
                        "Search Payment"
                );


        cancelPaymentButton =
                new JButton(
                        "Cancel Payment"
                );


        summaryButton =
                new JButton(
                        "Payment Summary"
                );


        backButton =
                new JButton(
                        "Back"
                );


        /*
         * Row 8
         */
        gbc.gridx = 0;
        gbc.gridy = 8;


        panel.add(
                makePaymentButton,
                gbc
        );


        gbc.gridx = 1;


        panel.add(
                viewPaymentsButton,
                gbc
        );


        /*
         * Row 9
         */
        gbc.gridx = 0;
        gbc.gridy = 9;


        panel.add(
                searchPaymentButton,
                gbc
        );


        gbc.gridx = 1;


        panel.add(
                cancelPaymentButton,
                gbc
        );


        /*
         * Row 10
         */
        gbc.gridx = 0;
        gbc.gridy = 10;


        panel.add(
                summaryButton,
                gbc
        );


        gbc.gridx = 1;


        panel.add(
                backButton,
                gbc
        );


        /*
         * =================================================
         * TABLE
         * =================================================
         */

        String[] columnNames = {

                "Payment ID",
                "Reservation ID",
                "Passenger",
                "Train",
                "Amount",
                "Method",
                "Date",
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


        paymentTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        paymentTable
                );


        gbc.gridx = 0;
        gbc.gridy = 11;

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
         * MAKE PAYMENT EVENT
         * =================================================
         */

        makePaymentButton
                .addActionListener(e -> {

                    makePayment();
                });


        /*
         * =================================================
         * VIEW PAYMENTS EVENT
         * =================================================
         */

        viewPaymentsButton
                .addActionListener(e -> {

                    refreshPaymentTable();
                });


        /*
         * =================================================
         * SEARCH PAYMENT EVENT
         * =================================================
         */

        searchPaymentButton
                .addActionListener(e -> {

                    searchPayment();
                });


        /*
         * =================================================
         * CANCEL PAYMENT EVENT
         * =================================================
         */

        cancelPaymentButton
                .addActionListener(e -> {

                    cancelPayment();
                });


        /*
         * =================================================
         * SUMMARY EVENT
         * =================================================
         */

        summaryButton
                .addActionListener(e -> {

                    showPaymentSummary();
                });


        /*
         * =================================================
         * BACK BUTTON
         * =================================================
         */

        backButton
                .addActionListener(e -> {

                    dispose();
                });


        add(panel);

        setVisible(true);
    }


    /*
     * =====================================================
     * LOAD RESERVATIONS
     * =====================================================
     */
    private void loadReservations() {

        reservationComboBox
                .removeAllItems();


        for (Reservation reservation :
                reservationService
                        .getReservations()) {

            /*
             * Only show active reservations.
             */
            if (reservation
                    .getStatus()
                    .equalsIgnoreCase(
                            "ACTIVE"
                    )) {

                reservationComboBox
                        .addItem(
                                reservation
                        );
            }
        }
    }


    /*
     * =====================================================
     * DISPLAY SELECTED RESERVATION
     * =====================================================
     */
    private void displaySelectedReservation() {

        Reservation reservation =
                (Reservation)
                        reservationComboBox
                                .getSelectedItem();


        if (reservation == null) {

            passengerField.setText("");

            trainField.setText("");

            amountField.setText("");

            return;
        }


        /*
         * Passenger.
         */
        passengerField.setText(
                reservation
                        .getPassenger()
                        .toString()
        );


        /*
         * Train.
         */
        trainField.setText(
                reservation
                        .getTrain()
                        .toString()
        );


        /*
         * Ticket price automatically becomes
         * payment amount.
         */
        amountField.setText(
                String.valueOf(
                        reservation
                                .getTrain()
                                .getTicketPrice()
                )
        );
    }


    /*
     * =====================================================
     * MAKE PAYMENT
     * =====================================================
     */
    private void makePayment() {

        try {

            /*
             * Read Payment ID.
             */
            int paymentId =
                    Integer.parseInt(
                            paymentIdField
                                    .getText()
                                    .trim()
                    );


            /*
             * Selected Reservation.
             */
            Reservation reservation =
                    (Reservation)
                            reservationComboBox
                                    .getSelectedItem();


            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No active reservation selected.",
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Amount comes from ticket price.
             */
            double amount =
                    reservation
                            .getTrain()
                            .getTicketPrice();


            /*
             * Selected payment method.
             */
            String paymentMethod =
                    (String)
                            paymentMethodComboBox
                                    .getSelectedItem();


            /*
             * Payment date.
             */
            String paymentDate =
                    paymentDateField
                            .getText();


            /*
             * Try making payment.
             */
            boolean paid =
                    paymentService
                            .makePayment(
                                    paymentId,
                                    reservation,
                                    amount,
                                    paymentMethod,
                                    paymentDate
                            );


            if (paid) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment completed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                paymentIdField
                        .setText("");


                refreshPaymentTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,

                        "Payment could not be completed.\n"
                                +
                                "Possible reasons:\n"
                                +
                                "- Payment ID already exists\n"
                                +
                                "- Reservation already paid\n"
                                +
                                "- Reservation is cancelled",

                        "Payment Error",

                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment ID must be a whole number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * REFRESH PAYMENT TABLE
     * =====================================================
     */
    private void refreshPaymentTable() {

        tableModel.setRowCount(0);


        if (paymentService
                .getPayments()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No payments found.",
                    "Payment List",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        for (Payment payment :
                paymentService
                        .getPayments()) {

            addPaymentToTable(
                    payment
            );
        }
    }


    /*
     * =====================================================
     * ADD ONE PAYMENT TO TABLE
     * =====================================================
     */
    private void addPaymentToTable(
            Payment payment) {

        Reservation reservation =
                payment
                        .getReservation();


        Object[] row = {

                payment
                        .getPaymentId(),

                reservation
                        .getReservationId(),

                reservation
                        .getPassenger(),

                reservation
                        .getTrain(),

                payment
                        .getAmount(),

                payment
                        .getPaymentMethod(),

                payment
                        .getPaymentDate(),

                payment
                        .getPaymentStatus()
        };


        tableModel.addRow(
                row
        );
    }


    /*
     * =====================================================
     * SEARCH PAYMENT
     * =====================================================
     */
    private void searchPayment() {

        try {

            int paymentId =
                    Integer.parseInt(
                            paymentIdField
                                    .getText()
                                    .trim()
                    );


            Payment payment =
                    paymentService
                            .findPaymentById(
                                    paymentId
                            );


            if (payment == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment not found.",
                        "Search Payment",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            /*
             * Display only searched payment.
             */
            tableModel.setRowCount(0);


            addPaymentToTable(
                    payment
            );


            /*
             * Select reservation.
             */
            reservationComboBox
                    .setSelectedItem(
                            payment
                                    .getReservation()
                    );


            /*
             * Select payment method.
             */
            paymentMethodComboBox
                    .setSelectedItem(
                            payment
                                    .getPaymentMethod()
                    );


            /*
             * Payment date.
             */
            paymentDateField
                    .setText(
                            payment
                                    .getPaymentDate()
                    );


            JOptionPane.showMessageDialog(
                    this,
                    "Payment found!",
                    "Search Payment",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Payment ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * CANCEL PAYMENT
     * =====================================================
     */
    private void cancelPayment() {

        try {

            int paymentId =
                    Integer.parseInt(
                            paymentIdField
                                    .getText()
                                    .trim()
                    );


            Payment payment =
                    paymentService
                            .findPaymentById(
                                    paymentId
                            );


            if (payment == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment not found.",
                        "Cancel Payment",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            int answer =
                    JOptionPane
                            .showConfirmDialog(
                                    this,
                                    "Are you sure you want to cancel this payment?",
                                    "Confirm Cancellation",
                                    JOptionPane.YES_NO_OPTION
                            );


            if (answer !=
                    JOptionPane.YES_OPTION) {

                return;
            }


            boolean cancelled =
                    paymentService
                            .cancelPayment(
                                    paymentId
                            );


            if (cancelled) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment cancelled successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                refreshPaymentTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment is already cancelled.",
                        "Cancel Payment",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Payment ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
     * =====================================================
     * PAYMENT SUMMARY
     * =====================================================
     */
    private void showPaymentSummary() {

        int paidCount = 0;

        int cancelledCount = 0;

        double totalIncome = 0;


        for (Payment payment :
                paymentService
                        .getPayments()) {


            if (payment
                    .getPaymentStatus()
                    .equalsIgnoreCase(
                            "PAID"
                    )) {

                paidCount++;

                totalIncome +=
                        payment
                                .getAmount();

            } else if (payment
                    .getPaymentStatus()
                    .equalsIgnoreCase(
                            "CANCELLED"
                    )) {

                cancelledCount++;
            }
        }


        JOptionPane.showMessageDialog(
                this,

                "===== PAYMENT SUMMARY =====\n\n"
                        +
                        "Successful Payments: "
                        + paidCount
                        + "\n"
                        +
                        "Cancelled Payments: "
                        + cancelledCount
                        + "\n"
                        +
                        "Total Income: Rs. "
                        + totalIncome,

                "Payment Summary",

                JOptionPane.INFORMATION_MESSAGE
        );
    }
}