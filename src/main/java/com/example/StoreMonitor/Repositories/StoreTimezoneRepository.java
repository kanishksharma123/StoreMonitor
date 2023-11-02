package com.example.StoreMonitor.Repositories;

import com.example.StoreMonitor.Entities.StoreTimezone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTimezoneRepository extends JpaRepository<StoreTimezone, Long> {

    String findTimezoneStrByStoreId(Long storeId);

    StoreTimezone findByStoreId(Long id);

    // Add more custom queries as needed
}
