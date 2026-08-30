import GUI.LoginFrame;

import Service.PassengerService;
import Service.TrainService;
import Service.StationService;
import Service.RouteService;
import Service.SeatService;
import Service.ReservationService;
import Service.PaymentService;

import javax.swing.*;
import java.util.Scanner;


/*
 * =====================================================
 * MAIN CLASS
 * =====================================================
 *
 * This is the starting point of the
 * Train Ticket Reservation System.
 *
 * All service objects are created only ONCE here.
 * The same objects are shared between all GUI screens.
 */
public class Main {

    public static void main(String[] args) {

        /*
         * =================================================
         * CREATE ONE SCANNER
         * =================================================
         */
        Scanner scanner =
                new Scanner(System.in);


        /*
         * =================================================
         * CREATE SHARED SERVICES
         * =================================================
         */

        PassengerService passengerService =
                new PassengerService(scanner);


        TrainService trainService =
                new TrainService(scanner);


        StationService stationService =
                new StationService(scanner);


        RouteService routeService =
                new RouteService(scanner);


        SeatService seatService =
                new SeatService(scanner);


        ReservationService reservationService =
                new ReservationService(scanner);


        PaymentService paymentService =
                new PaymentService();


        /*
         * =================================================
         * START LOGIN WINDOW
         * =================================================
         */
        SwingUtilities.invokeLater(() -> {

            new LoginFrame(
                    passengerService,
                    trainService,
                    stationService,
                    routeService,
                    seatService,
                    reservationService,
                    paymentService
            );

        });
    }
}