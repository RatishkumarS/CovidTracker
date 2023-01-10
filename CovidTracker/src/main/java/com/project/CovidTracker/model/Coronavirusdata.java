package com.project.CovidTracker.model;

public class Coronavirusdata {
    private String state;
    private String Country;
    private int latestTotalCases;
    private int prevdaycasesDiff;

    public int getPrevdaycasesDiff() {
        return prevdaycasesDiff;
    }

    public void setPrevdaycasesDiff(int prevdaycasesDiff) {
        this.prevdaycasesDiff = prevdaycasesDiff;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "Coronavirusdata{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", prevdaycasesDiff=" + prevdaycasesDiff +
                '}';
    }
}
