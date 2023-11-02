package com.example.StoreMonitor.Repositories;

import com.example.StoreMonitor.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {



    // Add custom queries or methods for report handling if necessary
}
