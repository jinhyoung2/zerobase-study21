package zerobase.wifi.model;

import java.sql.Timestamp;

public class PosHistoryModel {
    private int id;
	private double latitude;
    private double longitude;
    private Timestamp visitTime;

    public PosHistoryModel(int id, double latitude, double longitude, Timestamp visitTime) {
    	this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitTime = visitTime;
    }
    
    public int getId() {
        return id;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }
}
