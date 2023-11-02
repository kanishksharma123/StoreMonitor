package com.example.StoreMonitor.Entities;

public class ReportStatus {
    private boolean isComplete;
    private Report report; // This can be null if isComplete is false

    public ReportStatus(boolean isComplete, Report report) {
        this.isComplete = isComplete;
        this.report = report;
    }

    public ReportStatus() {
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
