package com.example.StoreMonitor.Entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name ="store status")
public class StoreObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "timestamp_utc")
    private LocalDateTime timestampUtc;

    @Column(name = "status")
    private String status; // Active or Inactive

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

    public LocalDateTime getTimestampUtc() {
        return timestampUtc;
    }

    public void setTimestampUtc(LocalDateTime timestampUtc) {
        this.timestampUtc = timestampUtc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StoreObservation(Long id, Long storeId, LocalDateTime timestampUtc, String status) {
        this.id = id;
        this.storeId = storeId;
        this.timestampUtc = timestampUtc;
        this.status = status;
    }

    public StoreObservation() {
    }

    @Override
    public String toString() {
        return "StoreObservation{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", timestampUtc=" + timestampUtc +
                ", status='" + status + '\'' +
                '}';
    }
}
