package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reports")
public class Report {
    @PrimaryKey(autoGenerate = true)
    private int reportID;

    private String reportCustName;
    private String reportAnimalName;
    private String reportAppDate;
    private String reportAppNotes;
    private Double reportAppCost;
    private int reportAppID;

    public Report(int reportID, String reportCustName, String reportAnimalName, String reportAppDate, String reportAppNotes, Double reportAppCost, int reportAppID) {
        this.reportID = reportID;
        this.reportCustName = reportCustName;
        this.reportAnimalName = reportAnimalName;
        this.reportAppDate = reportAppDate;
        this.reportAppNotes = reportAppNotes;
        this.reportAppCost = reportAppCost;
        this.reportAppID = reportAppID;
    }

    public Report() {
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportCustName() {
        return reportCustName;
    }

    public void setReportCustName(String reportCustName) {
        this.reportCustName = reportCustName;
    }

    public String getReportAnimalName() {
        return reportAnimalName;
    }

    public void setReportAnimalName(String reportAnimalName) {
        this.reportAnimalName = reportAnimalName;
    }

    public String getReportAppDate() {
        return reportAppDate;
    }

    public void setReportAppDate(String reportAppDate) {
        this.reportAppDate = reportAppDate;
    }

    public String getReportAppNotes() {
        return reportAppNotes;
    }

    public void setReportAppNotes(String reportAppNotes) {
        this.reportAppNotes = reportAppNotes;
    }

    public Double getReportAppCost() {
        return reportAppCost;
    }

    public void setReportAppCost(Double reportAppCost) {
        this.reportAppCost = reportAppCost;
    }

    public int getReportAppID() {
        return reportAppID;
    }

    public void setReportAppID(int reportAppID) {
        this.reportAppID = reportAppID;
    }
}
