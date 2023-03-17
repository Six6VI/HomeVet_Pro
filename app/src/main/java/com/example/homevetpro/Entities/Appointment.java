package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int appointmentID;

    private String appointmentDate;
    private String appointmentNotes;
    private int appointmentDuration;
    private Double appointmentCost;
    private Timestamp appEnterDate;
    private Timestamp appModifyDate;
    private int appAnimalID;

    public Appointment(int appointmentID, String appointmentDate, String appointmentNotes, int appointmentDuration, Double appointmentCost, Timestamp appEnterDate, Timestamp appModifyDate, int appAnimalID) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentNotes = appointmentNotes;
        this.appointmentDuration = appointmentDuration;
        this.appointmentCost = appointmentCost;
        this.appEnterDate = appEnterDate;
        this.appModifyDate = appModifyDate;
        this.appAnimalID = appAnimalID;
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

    public Timestamp getAppEnterDate() {
        return appEnterDate;
    }

    public void setAppEnterDate(Timestamp appEnterDate) {
        this.appEnterDate = appEnterDate;
    }

    public Timestamp getAppModifyDate() {
        return appModifyDate;
    }

    public void setAppModifyDate(Timestamp appModifyDate) {
        this.appModifyDate = appModifyDate;
    }

    public int getAppAnimalID() {
        return appAnimalID;
    }

    public void setAppAnimalID(int appAnimalID) {
        this.appAnimalID = appAnimalID;
    }
}
