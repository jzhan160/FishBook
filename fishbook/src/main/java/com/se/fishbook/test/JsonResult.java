package com.se.fishbook.test;

public class JsonResult {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
