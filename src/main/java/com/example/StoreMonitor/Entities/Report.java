package com.example.StoreMonitor.Entities;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.geo.Metrics;

import java.time.Instant;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "randomid")
    private Long randomid;
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "uptime_last_hour")
    private Long uptimeLastHour; // in minutes

    @Column(name = "uptime_last_day")
    private Double uptimeLastDay; // in hours

    @Column(name = "uptime_last_week")
    private Double uptimeLastWeek; // in hours

    @Column(name = "downtime_last_hour")
    private Long downtimeLastHour; // in minutes

    @Column(name = "downtime_last_day")
    private Double downtimeLastDay; // in hours

    @Column(name = "downtime_last_week")
    private Double downtimeLastWeek; // in hours

    @Column(name = "report_timestamp")
    private Instant reportTimestamp;

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUptimeLastHour() {
        return uptimeLastHour;
    }

    public void setUptimeLastHour(Long uptimeLastHour) {
        this.uptimeLastHour = uptimeLastHour;
    }

    public Double getUptimeLastDay() {
        return uptimeLastDay;
    }

    public void setUptimeLastDay(Double uptimeLastDay) {
        this.uptimeLastDay = uptimeLastDay;
    }

    public Double getUptimeLastWeek() {
        return uptimeLastWeek;
    }

    public void setUptimeLastWeek(Double uptimeLastWeek) {
        this.uptimeLastWeek = uptimeLastWeek;
    }

    public Long getDowntimeLastHour() {
        return downtimeLastHour;
    }

    public void setDowntimeLastHour(Long downtimeLastHour) {
        this.downtimeLastHour = downtimeLastHour;
    }

    public Double getDowntimeLastDay() {
        return downtimeLastDay;
    }

    public void setDowntimeLastDay(Double downtimeLastDay) {
        this.downtimeLastDay = downtimeLastDay;
    }

    public Double getDowntimeLastWeek() {
        return downtimeLastWeek;
    }

    public void setDowntimeLastWeek(Double downtimeLastWeek) {
        this.downtimeLastWeek = downtimeLastWeek;
    }

    public Instant getReportTimestamp() {
        return reportTimestamp;
    }

    public void setReportTimestamp(Instant reportTimestamp) {
        this.reportTimestamp = reportTimestamp;
    }

    public Long getRandomid() {
        return randomid;
    }

    public void setRandomid(Long randomid) {
        this.randomid = randomid;
    }

    public Report(Long id, Long randomid, Long storeId, Long uptimeLastHour, Double uptimeLastDay, Double uptimeLastWeek, Long downtimeLastHour, Double downtimeLastDay, Double downtimeLastWeek, Instant reportTimestamp) {
        this.id = id;
        this.randomid=randomid;
        this.storeId = storeId;
        this.uptimeLastHour = uptimeLastHour;
        this.uptimeLastDay = uptimeLastDay;
        this.uptimeLastWeek = uptimeLastWeek;
        this.downtimeLastHour = downtimeLastHour;
        this.downtimeLastDay = downtimeLastDay;
        this.downtimeLastWeek = downtimeLastWeek;
        this.reportTimestamp = reportTimestamp;
    }

    public void addMetrics(int storeId, Metrics metrics) {
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", randomid=" + randomid +
                ", storeId=" + storeId +
                ", uptimeLastHour=" + uptimeLastHour +
                ", uptimeLastDay=" + uptimeLastDay +
                ", uptimeLastWeek=" + uptimeLastWeek +
                ", downtimeLastHour=" + downtimeLastHour +
                ", downtimeLastDay=" + downtimeLastDay +
                ", downtimeLastWeek=" + downtimeLastWeek +
                ", reportTimestamp=" + reportTimestamp +
                '}';
    }

    public void addStoreMetrics(StoreMetrics storeMetrics) {
    }
}
