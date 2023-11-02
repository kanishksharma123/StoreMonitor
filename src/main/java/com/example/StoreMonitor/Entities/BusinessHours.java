package com.example.StoreMonitor.Entities;

import jakarta.persistence.*;

import javax.security.auth.kerberos.KerberosTicket;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name="business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "day_of_week")
    private int dayOfWeek; // 0=Monday, 6=Sunday

    @Column(name = "start_time_local")
    private LocalTime startTimeLocal;

    @Column(name = "end_time_local")
    private LocalTime endTimeLocal;

    public BusinessHours() {
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTimeLocal() {
        return startTimeLocal;
    }

    public void setStartTimeLocal(LocalTime startTimeLocal) {
        this.startTimeLocal = startTimeLocal;
    }

    public LocalTime getEndTimeLocal() {
        return endTimeLocal;
    }

    public void setEndTimeLocal(LocalTime endTimeLocal) {
        this.endTimeLocal = endTimeLocal;
    }

    public BusinessHours(Long id, Long storeId, int dayOfWeek, LocalTime startTimeLocal, LocalTime endTimeLocal) {
        this.id = id;
        this.storeId = storeId;
        this.dayOfWeek = dayOfWeek;
        this.startTimeLocal = startTimeLocal;
        this.endTimeLocal = endTimeLocal;
    }

    @Override
    public String toString() {
        return "BusinessHours{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", dayOfWeek=" + dayOfWeek +
                ", startTimeLocal=" + startTimeLocal +
                ", endTimeLocal=" + endTimeLocal +
                '}';
    }
}
