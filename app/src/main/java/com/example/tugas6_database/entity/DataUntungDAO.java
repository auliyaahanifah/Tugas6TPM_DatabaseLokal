package com.example.tugas6_database.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataUntungDAO {
    @Insert
    Long insertData(DataUntung dataUntung);

    @Query("Select * from db_untung")
    List<DataUntung> getData();

    @Update
    int updateData(DataUntung item);

    @Delete
    void deleteData(DataUntung item);
}
