package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "animals")
public class Animal {
    @PrimaryKey(autoGenerate = true)
    private int animalID;

    private String animalName;
    private String animalType;
    private String animalGender;
    private String animalBirthday;
    private String animalColor;
    private int animalWeight;
    private String animalNotes;
    private Timestamp animalEnterDate;
    private Timestamp animalModifyDate;
    private int animalCustID;

    public Animal(int animalID, String animalName, String animalType, String animalGender, String animalBirthday, String animalColor, int animalWeight, String animalNotes, Timestamp animalEnterDate, Timestamp animalModifyDate, int animalCustID) {
        this.animalID = animalID;
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalGender = animalGender;
        this.animalBirthday = animalBirthday;
        this.animalColor = animalColor;
        this.animalWeight = animalWeight;
        this.animalNotes = animalNotes;
        this.animalEnterDate = animalEnterDate;
        this.animalModifyDate = animalModifyDate;
        this.animalCustID = animalCustID;
    }

    public Animal() {
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalGender() {
        return animalGender;
    }

    public void setAnimalGender(String animalGender) {
        this.animalGender = animalGender;
    }

    public String getAnimalBirthday() {
        return animalBirthday;
    }

    public void setAnimalBirthday(String animalBirthday) {
        this.animalBirthday = animalBirthday;
    }

    public String getAnimalColor() {
        return animalColor;
    }

    public void setAnimalColor(String animalColor) {
        this.animalColor = animalColor;
    }

    public int getAnimalWeight() {
        return animalWeight;
    }

    public void setAnimalWeight(int animalWeight) {
        this.animalWeight = animalWeight;
    }

    public String getAnimalNotes() {
        return animalNotes;
    }

    public void setAnimalNotes(String animalNotes) {
        this.animalNotes = animalNotes;
    }

    public Timestamp getAnimalEnterDate() {
        return animalEnterDate;
    }

    public void setAnimalEnterDate(Timestamp animalEnterDate) {
        this.animalEnterDate = animalEnterDate;
    }

    public Timestamp getAnimalModifyDate() {
        return animalModifyDate;
    }

    public void setAnimalModifyDate(Timestamp animalModifyDate) {
        this.animalModifyDate = animalModifyDate;
    }

    public int getAnimalCustID() {
        return animalCustID;
    }

    public void setAnimalCustID(int animalCustID) {
        this.animalCustID = animalCustID;
    }
}
