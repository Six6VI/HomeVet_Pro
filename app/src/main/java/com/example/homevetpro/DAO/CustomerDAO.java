package com.example.homevetpro.DAO;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Update;

import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.User;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Customer customer);
    @Update
    void update(Customer customer);
    @Delete
    void delete(Customer customer);
    @Query("SELECT * FROM customers")
    List<Customer> getAllCustomers();
    @Query("SELECT customerID from customers where customerName=:name")
    int getIDByName(String name);
    @Query("SELECT customerName from customers where customerID=:id")
    String getNameByID(int id);

}
