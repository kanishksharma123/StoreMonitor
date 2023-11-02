package com.example.StoreMonitor.Controllers;
import com.example.StoreMonitor.DTOs.ReportStatus;
import com.example.StoreMonitor.Entities.Report;
import com.example.StoreMonitor.Services.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {


    private final ReportGenerationService reportGenerationService;

    @Autowired
    public ReportController(ReportGenerationService reportGenerationService) {

        this.reportGenerationService = reportGenerationService;
    }

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerReportGeneration() {

        String reportId = String.valueOf(reportGenerationService.generateReport());

        return ResponseEntity.ok(reportId);

    }
//        @GetMapping("/report/{reportId}")
//        public ResponseEntity<?> getReportById(@PathVariable String reportId) {
//            ReportStatus reportStatus = reportGenerationService.getReportStatus(reportId);
//
//
//            //return reportStatus.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
//            if (reportStatus == null) {
//                return ResponseEntity.notFound().build(); // Report not found
//            } else if (reportStatus.isComplete()) {
//                return ResponseEntity.ok(reportStatus.getReport()); // Return the complete report
//            } else {
//                return ResponseEntity.ok("Running"); // Report is still running
//            }
//        }
//        @GetMapping("/{id}")
//        public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
//            Optional<User> user = userRepository.findById(id);
//
//            return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
//        }
}
