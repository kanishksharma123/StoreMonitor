package com.example.StoreMonitor.Services;
import com.example.StoreMonitor.Entities.*;

import com.example.StoreMonitor.Repositories.BusinessHoursRepository;
import com.example.StoreMonitor.Repositories.StoreRepository;
import com.example.StoreMonitor.Repositories.StoreTimezoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.StoreMonitor.Repositories.ReportRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalTime;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {
@Autowired
    private final StoreRepository storeRepository;
@Autowired
    private final BusinessHoursRepository businessHoursRepository;
@Autowired
    private final StoreTimezoneRepository storeTimezoneRepository;
@Autowired
    private final ReportRepository reportRepository;

    public ReportGenerationService(
            StoreRepository storeRepository,
            BusinessHoursRepository businessHoursRepository,
            StoreTimezoneRepository storeTimezoneRepository,
            ReportRepository reportRepository
    ) {
        this.storeRepository = storeRepository;
        this.businessHoursRepository = businessHoursRepository;
        this.storeTimezoneRepository = storeTimezoneRepository;
        this.reportRepository = reportRepository;
    }

    public List<StoreObservation> generateReport() {
        // Generate a unique report ID


        List<StoreObservation> allObservations = storeRepository.findAll();
        List<BusinessHours> allBusinessHours=businessHoursRepository.findAll();
        List<Long> stoIds = allObservations.stream()
                .map(StoreObservation::getStoreId)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("-------------Store id's'--------");
        System.out.println(stoIds);
        System.out.println("--------------Store id's--------------");
        // Create and save reports for each store
        for (Long storeId : stoIds) {
            List<StoreObservation> storeObservations = filterObservationsForStore(allObservations, storeId);
            //List<StoreTimezone> storeTimezones=
            Long reportId = Long.valueOf(generateRandomReportId());
            List<BusinessHours> businesshoursObservations = filterbusinesshoursForStore(allBusinessHours, storeId);
            System.out.println("-------------Store Observations for Store ID: " + storeId + "---------");
            System.out.println(storeObservations);
            System.out.println("--------------Store Observations--------------");

            StoreMetrics metrics = calculateMetricsForStore(storeObservations,businesshoursObservations );
            System.out.println("---------Metrics  for Store ID: " + storeId + "-------------------");
            System.out.println(metrics.getUptimeLastHour());
            System.out.println(metrics.getUptimeLastDay());
            System.out.println(metrics.getUptimeLastWeek());
            System.out.println(metrics.getDowntimeLastHour());
            System.out.println(metrics.getDowntimeLastDay());
            System.out.println(metrics.getDowntimeLastWeek());

            System.out.println("-------------------------------------------------------------------");

            // Create a new Report for the store
            Report report = new Report(
                    33L,  // Assign the ID for the metrics (if metrics have an ID)
                    (reportId),  // Generate a random report ID
                    storeId,  // Use the current store ID
                    metrics.getUptimeLastHour(),  // Get uptime for the last hour from the metrics
                    metrics.getUptimeLastDay(),  // Get uptime for the last day from the metrics
                    metrics.getUptimeLastWeek(),  // Get uptime for the last week from the metrics
                    (long) metrics.getDowntimeLastHour(),  // Get downtime for the last hour from the metrics
                    metrics.getDowntimeLastDay(),  // Get downtime for the last day from the metrics
                    metrics.getDowntimeLastWeek(),  // Get downtime for the last week from the metrics
                    Instant.now()  // Current timestamp for the report
            );
            // Save the report to the database
            report = reportRepository.save(report);
        }

        return allObservations;

    }
    public List<StoreObservation> filterObservationsForStore(List<StoreObservation> allObservations, Long storeId) {
        List<StoreObservation> filteredObservations = new ArrayList<>();

        for (StoreObservation observation : allObservations) {
            if (observation.getStoreId().equals(storeId)) {
                filteredObservations.add(observation);
            }
        }

        return filteredObservations;
    }
    public List<BusinessHours> filterbusinesshoursForStore(List<BusinessHours> allBusinessHours, Long storeId) {
        List<BusinessHours> filteredObs = new ArrayList<>();

        for (BusinessHours observation : allBusinessHours) {
            if (observation.getStoreId().equals(storeId)) {
                filteredObs.add(observation);
            }
        }

        return filteredObs;
    }








    public long generateRandomReportId() {
        // Generate a unique random report ID (e.g., using UUID)
        // Generate a unique random report ID as a UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a long value
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        // Combine the two long values into a single long ID
        // This assumes that the UUID provides enough randomness to ensure uniqueness
        long randomReportId = mostSignificantBits ^ leastSignificantBits;

        // Ensure the ID is positive (non-negative)
        if (randomReportId < 0) {
            randomReportId = -randomReportId;
        }

        return randomReportId;
    }
    private StoreMetrics calculateMetricsForStore( List<StoreObservation> observations,List<BusinessHours> businessHoursObservations) {
        StoreMetrics metrics = new StoreMetrics();
        //Long storeId = store.getStoreId();

        // Implement your logic to calculate uptime for the last hour here

        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        LocalDateTime oneDayAgo=LocalDateTime.now().minusHours(24);
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);


        // Implement your logic to calculate uptime for the last day here

        List<StoreObservation> observationsWithinHour = filterObservationsWithinTimeFrame(observations, oneHourAgo, LocalDateTime.now());
        List<StoreObservation> observationsWithinLastDay = filterObservationsWithinTimeFrame(observations, oneDayAgo, LocalDateTime.now());
        List<StoreObservation> observationsWithinWeek = filterObservationsWithinTimeFrame(observations, oneWeekAgo, LocalDateTime.now());

        System.out.println("metrics -----------------UPtime last hour");
        metrics.setUptimeLastHour(calculateUptimeLastHour(observationsWithinHour,businessHoursObservations));
        System.out.println(metrics.getUptimeLastHour());
        System.out.println("metrics -----------------UPtime last hour");
        System.out.println("metrics -----------------UPtime last day");
        metrics.setUptimeLastDay(calculateUptimeLastDay(observationsWithinLastDay,businessHoursObservations));
       System.out.println(metrics.getUptimeLastDay());
       System.out.println("metrics -----------------UP time last day");


        System.out.println("metrics -----------------UPtime last week");
        metrics.setUptimeLastWeek(calculateUptimeLastWeek(observationsWithinWeek, businessHoursObservations));
        System.out.println(metrics.getUptimeLastWeek());
        System.out.println("metrics -----------------UP time last week");


        // Calculate and set downtime metrics
        metrics.setDowntimeLastHour((int) (60 - metrics.getUptimeLastHour()));
        metrics.setDowntimeLastDay(1440 - metrics.getUptimeLastDay());
        metrics.setDowntimeLastWeek(10080 - metrics.getUptimeLastWeek());

        return metrics;
    }

    private List<StoreObservation> filterObservationsWithinTimeFrame(List<StoreObservation> observations, LocalDateTime oneHourAgo, LocalDateTime now) {

            return observations.stream()
                    .filter(observation -> isWithinTimeFrame(observation.getTimestampUtc(), oneHourAgo, LocalDateTime.now()))
                    .collect(Collectors.toList());
        }

        private boolean isWithinTimeFrame(LocalDateTime timestamp, LocalDateTime startTime, LocalDateTime endTime) {
            return !timestamp.isBefore(startTime) && !timestamp.isAfter(endTime);
        }




    private int calculateUptimeLastHour(List<StoreObservation> observations,List<BusinessHours> businessHoursObservations) {
        // Get the current timestamp
        Instant currentTimestamp = Instant.now();

        // Calculate the timestamp for one hour ago
        Instant oneHourAgo = currentTimestamp.minus(Duration.ofHours(1));

        // Initialize variables to keep track of uptime
        long uptimeLastHour = 0;

        // Loop through each observation
        for (StoreObservation observation : observations) {
             // Check if the observation timestamp is within the last hour
            Instant observationTimestamp = observation.getTimestampUtc().atZone(ZoneOffset.UTC).toInstant();
            System.out.println("Observation Timestamp: " + observationTimestamp);  // Add this line for debugging
            System.out.println("Current Timestamp: " + currentTimestamp);  // Add this line for debugging
            System.out.println("One Hour Ago: " + oneHourAgo);  // Add this line for debugging
            if (observationTimestamp.isAfter(oneHourAgo) && observationTimestamp.isBefore(currentTimestamp)) {
                // This observation is within the last hour and considered as uptime
                uptimeLastHour++;
            }
        }

        return (int) uptimeLastHour;
    }

    private int calculateUptimeLastDay(List<StoreObservation> observations,List<BusinessHours> businessHoursObservations) {
        // Get the current timestamp
        Instant currentTimestamp = Instant.now();

        // Calculate the timestamp for one day ago
        Instant oneDayAgo = currentTimestamp.minus(Duration.ofDays(1));

        // Initialize variables to keep track of uptime
        long uptimeLastDay = 0;

        // Loop through each observation
        for (StoreObservation observation : observations) {
            // Check if the observation timestamp is within the last day
            Instant observationTimestamp = observation.getTimestampUtc().atZone(ZoneOffset.UTC).toInstant();
            System.out.println("Observation Timestamp: " + observationTimestamp);  // Add this line for debugging
            System.out.println("Current Timestamp: " + currentTimestamp);  // Add this line for debugging
            System.out.println("One Day Ago: " + oneDayAgo);  // Add this line for debugging
            if (observationTimestamp.isAfter(oneDayAgo) && observationTimestamp.isBefore(currentTimestamp)) {
                // This observation is within the last day and considered as uptime
                uptimeLastDay++;
            }
        }

        return (int) uptimeLastDay;
    }

    private int calculateUptimeLastWeek(List<StoreObservation> observations,List<BusinessHours> businessHoursObservations) {
        // Get the current timestamp
        Instant currentTimestamp = Instant.now();

        // Calculate the timestamp for one week ago
        Instant oneWeekAgo = currentTimestamp.minus(Duration.ofDays(7));

        // Initialize variables to keep track of uptime
        long uptimeLastWeek = 0;

        // Loop through each observation
        for (StoreObservation observation : observations) {
            // Check if the observation timestamp is within the last week
            Instant observationTimestamp = observation.getTimestampUtc().atZone(ZoneOffset.UTC).toInstant();
            System.out.println("Observation Timestamp: " + observationTimestamp);  // Add this line for debugging
            System.out.println("Current Timestamp: " + currentTimestamp);  // Add this line for debugging
            System.out.println("One Week Ago: " + oneWeekAgo);  // Add this line for debugging
            if (observationTimestamp.isAfter(oneWeekAgo) && observationTimestamp.isBefore(currentTimestamp)) {
                // This observation is within the last week and considered as uptime
                uptimeLastWeek++;
            }
        }

        return (int) uptimeLastWeek;
    }

    private boolean isWithinBusinessHours(BusinessHours businessHours, Instant timestamp) {
        int dayOfWeek = timestamp.atZone(ZoneId.systemDefault()).getDayOfWeek().getValue();
        LocalTime startTime = businessHours.getStartTimeLocal();
        LocalTime endTime = businessHours.getEndTimeLocal();

        // Check if the timestamp is within business hours
        return dayOfWeek == businessHours.getDayOfWeek() &&
                !timestamp.isBefore(Instant.from(startTime.atDate(LocalDate.now()))) &&
                !timestamp.isAfter(Instant.from(endTime.atDate(LocalDate.now())));
    }
}
