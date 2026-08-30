package model;

/*
 * =====================================================
 * PAYMENT MODEL
 * =====================================================
 *
 * This class stores payment details for a reservation.
 */
public class Payment {

    private int paymentId;
    private Reservation reservation;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentDate;


    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */
    public Payment(
            int paymentId,
            Reservation reservation,
            double amount,
            String paymentMethod,
            String paymentStatus,
            String paymentDate) {

        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }


    /*
     * =====================================================
     * GETTERS
     * =====================================================
     */

    public int getPaymentId() {
        return paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }


    /*
     * =====================================================
     * SETTERS
     * =====================================================
     */

    public void setPaymentMethod(
            String paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(
            String paymentStatus) {

        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDate(
            String paymentDate) {

        this.paymentDate = paymentDate;
    }


    /*
     * Display inside JComboBox if needed.
     */
    @Override
    public String toString() {

        return "Payment " + paymentId;
    }
}