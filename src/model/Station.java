package model;
/*
    *Station reperesent a railway station.
 */
public class Station {
    //private attribute demonstrate encapsulation.
    private int stationId;
    private String stationName;

    //constructors
    public Station(int stationId,String stationName){
        this.stationId = stationId;
        this.stationName = stationName;
    }

    //getters and setters
    public int getStationId(){return stationId;}
    public String getStationName(){return stationName;}

    public void setStationId(int stationId){
        this.stationId = stationId;
    }
    public void setStationName(String stationName){
        this.stationName = stationName;
    }

    //Display ststion details
    public void dispalyStationDetails(){
        System.out.println("-------Station Details------");
        System.out.println("Station Id: " +stationId);
        System.out.println("Station Name: " +stationName);

    }
}
