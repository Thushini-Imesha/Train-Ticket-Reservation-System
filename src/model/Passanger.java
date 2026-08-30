package model;

/*
 * =====================================================
 * PASSANGER CLASS
 * =====================================================
 *
 * Represents a passenger/user in the
 * Train Ticket Reservation System.
 */
public class Passanger extends User {

    private String nic;

    /*
     * Login details for passenger.
     */
    private String loginUsername;
    private String loginPassword;


    /*
     * =====================================================
     * ORIGINAL CONSTRUCTOR
     * =====================================================
     *
     * Keep this because your existing code may use it.
     */
    public Passanger(
            int userid,
            String username,
            String email,
            String tele,
            String nic) {

        super(
                userid,
                username,
                email,
                tele
        );

        this.nic = nic;

        /*
         * Default login values.
         *
         * Username = passenger name
         * Password = NIC
         *
         * You can change this later.
         */
        this.loginUsername = username;
        this.loginPassword = nic;
    }


    /*
     * =====================================================
     * NEW CONSTRUCTOR WITH LOGIN DETAILS
     * =====================================================
     */
    public Passanger(
            int userid,
            String username,
            String email,
            String tele,
            String nic,
            String loginUsername,
            String loginPassword) {

        super(
                userid,
                username,
                email,
                tele
        );

        this.nic = nic;
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }


    /*
     * =====================================================
     * NIC
     * =====================================================
     */
    public String getNic() {

        return nic;
    }


    public void setNic(
            String nic) {

        this.nic = nic;
    }


    /*
     * =====================================================
     * LOGIN USERNAME
     * =====================================================
     */
    public String getLoginUsername() {

        return loginUsername;
    }


    public void setLoginUsername(
            String loginUsername) {

        this.loginUsername =
                loginUsername;
    }


    /*
     * =====================================================
     * LOGIN PASSWORD
     * =====================================================
     */
    public String getLoginPassword() {

        return loginPassword;
    }


    public void setLoginPassword(
            String loginPassword) {

        this.loginPassword =
                loginPassword;
    }


    /*
     * =====================================================
     * DISPLAY NAME
     * =====================================================
     *
     * Used by JComboBox.
     */
    @Override
    public String toString() {

        return getUsername();
    }
    /*
     * =====================================================
     * DISPLAY PASSENGER DETAILS
     * =====================================================
     */
    public void displayPassangerDetails() {

        System.out.println("Passenger ID : " + getUserid());
        System.out.println("Name         : " + getUsername());
        System.out.println("Email        : " + getEmail());
        System.out.println("Telephone    : " + getTele());
        System.out.println("NIC          : " + nic);
    }
}