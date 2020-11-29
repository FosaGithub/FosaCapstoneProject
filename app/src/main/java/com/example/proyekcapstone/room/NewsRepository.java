package com.example.proyekcapstone.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.proyekcapstone.News.ModelNews;

import java.util.List;

public class NewsRepository {
    private NewsDao mNewsDao;
    private LiveData<List<ModelNews>> mModelNews;

    NewsRepository(Application application){
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        mNewsDao = db.newsDao();
        mModelNews = mNewsDao.getAllData();
    }

    LiveData<List<ModelNews>> getAllData() {
        return mModelNews;
    }

    public void insert (ModelNews data) {
        new insertAsyncTask(mNewsDao).execute(data);
    }

    private static class insertAsyncTask extends AsyncTask<ModelNews, Void, Void> {

        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ModelNews... params) {
            mAsyncTaskDao.insertData(params[0]);
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mNewsDao).execute();
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
