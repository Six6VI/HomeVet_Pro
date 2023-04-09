package com.example.homevetpro.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    private int customerID;

    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhone;
    private String customerEnterDate;
    private String customerModifyDate;

    public Customer(int customerID, String customerName, String customerAddress, String customerZip, String customerPhone, String customerEnterDate, String customerModifyDate) {
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

    public String getCustomerEnterDate() {
        return customerEnterDate;
    }

    public void setCustomerEnterDate(String customerEnterDate) {
        this.customerEnterDate = customerEnterDate;
    }

    public String getCustomerModifyDate() {
        return customerModifyDate;
    }

    public void setCustomerModifyDate(String customerModifyDate) {
        this.customerModifyDate = customerModifyDate;
    }
}
