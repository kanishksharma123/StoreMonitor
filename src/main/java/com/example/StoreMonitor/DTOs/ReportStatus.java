package com.example.StoreMonitor.DTOs;

import com.example.StoreMonitor.Entities.Report;

public class ReportStatus {
    private boolean isComplete;
    private Report report;

    // Constructors, getters, and setters

    public ReportStatus(boolean isComplete, Report report) {
        this.isComplete = isComplete;
        this.report = report;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
