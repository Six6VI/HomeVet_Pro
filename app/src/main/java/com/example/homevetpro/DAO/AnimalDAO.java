package com.example.homevetpro.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.User;

import java.util.List;

@Dao
public interface AnimalDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Animal animal);
    @Update
    void update(Animal animal);
    @Delete
    void delete(Animal animal);
    @Query("SELECT * FROM animals")
    List<User> getAllAnimals();
}
