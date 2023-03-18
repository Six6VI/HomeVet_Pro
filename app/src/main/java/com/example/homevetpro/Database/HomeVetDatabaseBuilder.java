package com.example.homevetpro.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.homevetpro.DAO.AnimalDAO;
import com.example.homevetpro.DAO.AppointmentDAO;
import com.example.homevetpro.DAO.CustomerDAO;
import com.example.homevetpro.DAO.ReportDAO;
import com.example.homevetpro.DAO.UserDAO;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.Entities.User;

@Database(entities = {User.class, Customer.class, Animal.class, Appointment.class, Report.class}, version=1, exportSchema = false)
public abstract class HomeVetDatabaseBuilder extends RoomDatabase {

    public abstract UserDAO userDAO();

    public abstract CustomerDAO customerDAO();

    public abstract AnimalDAO animalDAO();

    public abstract AppointmentDAO appointmentDAO();

    public abstract ReportDAO reportDAO();

    private static volatile HomeVetDatabaseBuilder INSTANCE;

    static HomeVetDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HomeVetDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HomeVetDatabaseBuilder.class, "HomeVetDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

