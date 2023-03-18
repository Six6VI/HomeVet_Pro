package com.example.homevetpro.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homevetpro.Entities.Appointment;

import java.util.List;

@Dao
public interface AppointmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Appointment appointment);
    @Update
    void update(Appointment appointment);
    @Delete
    void delete(Appointment appointment);
    @Query("SELECT * FROM appointments")
    List<Appointment> getAllAppointments();
}
