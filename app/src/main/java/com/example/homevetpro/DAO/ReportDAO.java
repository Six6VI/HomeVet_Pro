package com.example.homevetpro.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homevetpro.Entities.Report;

import java.util.List;

@Dao
public interface ReportDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Report report);
    @Update
    void update(Report report);
    @Delete
    void delete(Report report);
    @Query("SELECT * FROM reports")
    List<Report> getAllReports();
}
