package com.example.StoreMonitor.Repositories;

import com.example.StoreMonitor.Entities.StoreObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface StoreRepository extends JpaRepository<StoreObservation, Long> {

    List<StoreObservation> findByStoreIdAndTimestampUtcBetween(Long storeId, Instant startTime, Instant endTime);

    List<StoreObservation> findByStoreId(Long storeId);

    // Add more custom queries as needed
}
