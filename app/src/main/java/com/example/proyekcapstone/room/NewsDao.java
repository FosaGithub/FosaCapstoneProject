package com.example.proyekcapstone.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyekcapstone.News.ModelNews;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insertData(ModelNews data);

    @Query("SELECT * from api_table ORDER BY id ASC")
    LiveData<List<ModelNews>> getAllData();

    @Query("DELETE FROM api_table")
    void deleteAll();

}
