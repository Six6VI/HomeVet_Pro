package com.example.homevetpro.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.Entities.User;

@Database(entities = {User.class, Customer.class, Animal.class, Appointment.class, Report.class}, version=1, exportSchema = false)
public abstract class HomeVetDatabaseBuilder extends RoomDatabase {
}
