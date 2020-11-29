package com.example.proyekcapstone.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyekcapstone.News.ModelNews;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository mRepository;
    private LiveData<List<ModelNews>> mNewsModel;

    public NewsViewModel(Application application) {
        super(application);
        mRepository = new NewsRepository(application);
        mNewsModel = mRepository.getAllData();
    }

    public LiveData<List<ModelNews>> getAllData() { return mNewsModel; }

    public void insert(ModelNews modelNews) { mRepository.insert(modelNews); }

    public void deleteAll() {mRepository.deleteAll();}
}
