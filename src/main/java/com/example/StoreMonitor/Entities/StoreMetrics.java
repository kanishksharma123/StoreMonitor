package com.example.StoreMonitor.Entities;

public class StoreMetrics {
    private int uptimeLastHour;
    private double uptimeLastDay;
    private double uptimeLastWeek;
    private int downtimeLastHour;
    private double downtimeLastDay;
    private double downtimeLastWeek;

    // Getters and setters for each metric

    public long getUptimeLastHour() {
        return uptimeLastHour;
    }

    public void setUptimeLastHour(int uptimeLastHour) {
        this.uptimeLastHour = uptimeLastHour;
    }

    public double getUptimeLastDay() {
        return uptimeLastDay;
    }

    public void setUptimeLastDay(double uptimeLastDay) {
        this.uptimeLastDay = uptimeLastDay;
    }

    public double getUptimeLastWeek() {
        return uptimeLastWeek;
    }

    public void setUptimeLastWeek(double uptimeLastWeek) {
        this.uptimeLastWeek = uptimeLastWeek;
    }

    public int getDowntimeLastHour() {
        return downtimeLastHour;
    }

    public void setDowntimeLastHour(int downtimeLastHour) {
        this.downtimeLastHour = downtimeLastHour;
    }

    public double getDowntimeLastDay() {
        return downtimeLastDay;
    }

    public void setDowntimeLastDay(double downtimeLastDay) {
        this.downtimeLastDay = downtimeLastDay;
    }

    public double getDowntimeLastWeek() {
        return downtimeLastWeek;
    }

    public void setDowntimeLastWeek(double downtimeLastWeek) {
        this.downtimeLastWeek = downtimeLastWeek;
    }
}
