package com.example.StoreMonitor.Entities;

import jakarta.persistence.*;



@Entity
@Table(name = "store_timezone")
public class StoreTimezone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "timezone_str")
    private String timezoneStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId( Long storeId) {
        this.storeId = storeId;
    }

    public String getTimezoneStr() {
        return timezoneStr;
    }

    public void setTimezoneStr(String timezoneStr) {
        this.timezoneStr = timezoneStr;
    }

    public StoreTimezone(Long id, Long storeId, String timezoneStr) {
        this.id = id;
        this.storeId = storeId;
        this.timezoneStr = timezoneStr;
    }
}
