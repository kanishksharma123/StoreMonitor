package com.example.StoreMonitor.Repositories;

import com.example.StoreMonitor.Entities.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {

    List<BusinessHours> findByStoreId(Long storeId);


    // Add more custom queries as needed
}
