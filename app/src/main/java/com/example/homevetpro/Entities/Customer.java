package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.sql.Timestamp;

@Entity (tableName = "customers")
public class Customer {
    @PrimaryKey (autoGenerate = true)
    private int customerID;

    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhone;
    private Timestamp customerEnterDate;
    private Timestamp customerModifyDate;

    public Customer(int customerID, String customerName, String customerAddress, String customerZip, String customerPhone, Timestamp customerEnterDate, Timestamp customerModifyDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.customerEnterDate = customerEnterDate;
        this.customerModifyDate = customerModifyDate;
    }

    public Customer() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Timestamp getCustomerEnterDate() {
        return customerEnterDate;
    }

    public void setCustomerEnterDate(Timestamp customerEnterDate) {
        this.customerEnterDate = customerEnterDate;
    }

    public Timestamp getCustomerModifyDate() {
        return customerModifyDate;
    }

    public void setCustomerModifyDate(Timestamp customerModifyDate) {
        this.customerModifyDate = customerModifyDate;
    }
}
