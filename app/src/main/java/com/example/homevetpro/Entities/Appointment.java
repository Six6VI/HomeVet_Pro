package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int appointmentID;

    private String appointmentDate;
    private String appointmentNotes;
    private int appointmentDuration;
    private Double appointmentCost;
    private String appEnterDate;
    private String appModifyDate;
    private int appAnimalID;
    private int appCustID;

    public Appointment(int appointmentID, String appointmentDate, String appointmentNotes, int appointmentDuration, Double appointmentCost, String appEnterDate, String appModifyDate, int appAnimalID, int appCustID) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentNotes = appointmentNotes;
        this.appointmentDuration = appointmentDuration;
        this.appointmentCost = appointmentCost;
        this.appEnterDate = appEnterDate;
        this.appModifyDate = appModifyDate;
        this.appAnimalID = appAnimalID;
        this.appCustID = appCustID;
    }

    public Appointment() {
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentNotes() {
        return appointmentNotes;
    }

    public void setAppointmentNotes(String appointmentNotes) {
        this.appointmentNotes = appointmentNotes;
    }

    public int getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(int appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public Double getAppointmentCost() {
        return appointmentCost;
    }

    public void setAppointmentCost(Double appointmentCost) {
        this.appointmentCost = appointmentCost;
    }

    public String getAppEnterDate() {
        return appEnterDate;
    }

    public void setAppEnterDate(String appEnterDate) {
        this.appEnterDate = appEnterDate;
    }

    public String getAppModifyDate() {
        return appModifyDate;
    }

    public void setAppModifyDate(String appModifyDate) {
        this.appModifyDate = appModifyDate;
    }

    public int getAppAnimalID() {
        return appAnimalID;
    }

    public void setAppAnimalID(int appAnimalID) {
        this.appAnimalID = appAnimalID;
    }

    public int getAppCustID() {
        return appCustID;
    }

    public void setAppCustID(int appCustID) {
        this.appCustID = appCustID;
    }

}
