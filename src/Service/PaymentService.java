package Service;

import model.Payment;
import model.Reservation;

import java.util.ArrayList;


/*
 * =====================================================
 * PAYMENT SERVICE
 * =====================================================
 *
 * Handles all payment-related operations.
 */
public class PaymentService {

    /*
     * Stores payments during program execution.
     */
    private ArrayList<Payment> payments =
            new ArrayList<>();


    /*
     * =====================================================
     * GET ALL PAYMENTS
     * =====================================================
     */
    public ArrayList<Payment> getPayments() {

        return payments;
    }


    /*
     * =====================================================
     * FIND PAYMENT BY ID
     * =====================================================
     */
    public Payment findPaymentById(
            int paymentId) {

        for (Payment payment :
                payments) {

            if (payment.getPaymentId()
                    == paymentId) {

                return payment;
            }
        }

        return null;
    }


    /*
     * =====================================================
     * FIND PAYMENT BY RESERVATION
     * =====================================================
     */
    public Payment findPaymentByReservation(
            Reservation reservation) {

        if (reservation == null) {
            return null;
        }

        for (Payment payment :
                payments) {

            if (payment
                    .getReservation()
                    .getReservationId()
                    ==
                    reservation
                            .getReservationId()) {

                return payment;
            }
        }

        return null;
    }


    /*
     * =====================================================
     * CHECK WHETHER RESERVATION IS ALREADY PAID
     * =====================================================
     */
    public boolean isReservationAlreadyPaid(
            Reservation reservation) {

        if (reservation == null) {
            return false;
        }

        for (Payment payment :
                payments) {

            if (payment
                    .getReservation()
                    .getReservationId()
                    ==
                    reservation
                            .getReservationId()
                    &&
                    payment
                            .getPaymentStatus()
                            .equalsIgnoreCase(
                                    "PAID"
                            )) {

                return true;
            }
        }

        return false;
    }


    /*
     * =====================================================
     * MAKE PAYMENT
     * =====================================================
     */
    public boolean makePayment(
            int paymentId,
            Reservation reservation,
            double amount,
            String paymentMethod,
            String paymentDate) {

        /*
         * Payment ID must be unique.
         */
        if (findPaymentById(paymentId)
                != null) {

            return false;
        }


        /*
         * Reservation must exist.
         */
        if (reservation == null) {

            return false;
        }


        /*
         * Cannot pay cancelled reservation.
         */
        if (reservation
                .getStatus()
                .equalsIgnoreCase(
                        "CANCELLED"
                )) {

            return false;
        }


        /*
         * Prevent duplicate payment.
         */
        if (isReservationAlreadyPaid(
                reservation)) {

            return false;
        }


        /*
         * Amount must be greater than zero.
         */
        if (amount <= 0) {

            return false;
        }


        /*
         * Create payment.
         */
        Payment payment =
                new Payment(
                        paymentId,
                        reservation,
                        amount,
                        paymentMethod,
                        "PAID",
                        paymentDate
                );


        /*
         * Add payment.
         */
        payments.add(
                payment
        );


        return true;
    }


    /*
     * =====================================================
     * CANCEL PAYMENT
     * =====================================================
     */
    public boolean cancelPayment(
            int paymentId) {

        Payment payment =
                findPaymentById(
                        paymentId
                );


        if (payment == null) {

            return false;
        }


        /*
         * Prevent cancelling twice.
         */
        if (payment
                .getPaymentStatus()
                .equalsIgnoreCase(
                        "CANCELLED"
                )) {

            return false;
        }


        payment.setPaymentStatus(
                "CANCELLED"
        );


        return true;
    }
}