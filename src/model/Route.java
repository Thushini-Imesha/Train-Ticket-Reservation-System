package model;

/*
    represent the journey between railway stations
    *route has;
        -route id
        -departure station
        -destinantion station
        -Distance
    class demonstrate encapsulation by keeping its attribute private

 */
public class Route {
    private int routeId;
    private Station departureStation;
    private Station destinationStation;
    private double distance;

    //constructors
    public Route(int routeId,Station departureStation,Station destinationStation,double distance){
        this.routeId = routeId;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.distance = distance;
    }

    //getters and setter
    public int getRouteId(){return routeId;}
    public Station getDepartureStation(){return departureStation;}
    public Station getDestinationStation(){return destinationStation;}
    public double getDistance(){return distance;}

    public void setRouteId(int routeId){
        this.routeId = routeId;
    }
    public void setDepartureStation(Station departureStation){
        this.departureStation = departureStation;
    }
    public void setDestinationStation(Station destinationStation){
        this.destinationStation = destinationStation;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }

    //display route information
    public void displayRouteDetails() {
        System.out.println("----- Route Details -----");
        System.out.println("Route ID : " + routeId);
        System.out.println("Departure : " + departureStation.getStationName());
        System.out.println("Destination : " + destinationStation.getStationName());
        System.out.println("Distance : " + distance + " km");
    }
}

