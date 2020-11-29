package com.example.proyekcapstone;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.proyekcapstone.News.ModelNews;
import com.example.proyekcapstone.News.NewsAdapter;
import com.example.proyekcapstone.News.NewsApi;
import com.example.proyekcapstone.News.OpenNewsActivity;
import com.example.proyekcapstone.room.NewsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusinessActivity extends AppCompatActivity implements NewsAdapter.onSelectData {

    RecyclerView rvBusiness;
    NewsAdapter newsAdapter;
    List modelNews = new ArrayList<>();
    ProgressDialog progressDialog;

    private NewsViewModel mNewsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);


        rvBusiness = findViewById(R.id.rvNews);
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        rvBusiness.setHasFixedSize(true);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));

        if (haveNetwork()) {
            mNewsViewModel.deleteAll();
            loadJSON();
        } else if (!haveNetwork()) {
            newsAdapter = new NewsAdapter(BusinessActivity.this, modelNews, this);
            rvBusiness.setAdapter(newsAdapter);
            mNewsViewModel.getAllData().observe(this, new Observer<List<ModelNews>>() {
                @Override
                public void onChanged(@Nullable final List<ModelNews> data) {
                    // Update the cached copy of the words in the adapter.
                    newsAdapter.setData((ArrayList<ModelNews>) data);
                }
            });
        }

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbNews);
        toolbar.setTitle("Back");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadJSON() {
        progressDialog.show();
        AndroidNetworking.get(NewsApi.GET_CATEGORY_BUSINESS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("articles");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelNews dataApi = new ModelNews();
                                dataApi.setTitle(temp.getString("title"));
                                dataApi.setContent(temp.getString("content"));
                                dataApi.setAuthor(temp.getString("author"));
                                dataApi.setUrl(temp.getString("url"));
                                dataApi.setPublishedAt(temp.getString("publishedAt"));
                                dataApi.setUrlToImage(temp.getString("urlToImage"));

                                modelNews.add(dataApi);
                                mNewsViewModel.insert(dataApi);
                                showNews();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BusinessActivity.this, "Berita gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(BusinessActivity.this, "Internet data tidak ada!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showNews() {
        newsAdapter = new NewsAdapter(BusinessActivity.this, modelNews, this);
        rvBusiness.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(ModelNews mdlNews) {
//        startActivity(new Intent(BusinessActivity.this, OpenNewsActivity.class).putExtra("url", mdlNews.getUrl()));
        Intent detailIntent = new Intent(BusinessActivity.this, OpenNewsActivity.class);
        detailIntent.putExtra("title", mdlNews.getTitle());
        detailIntent.putExtra("content", mdlNews.getContent());
        detailIntent.putExtra("published", mdlNews.getPublishedAt());
        detailIntent.putExtra("author", mdlNews.getAuthor());
        detailIntent.putExtra("image", mdlNews.getUrlToImage());

        BusinessActivity.this.startActivity(detailIntent);
    }

    private boolean haveNetwork() {
        boolean have_WIFI = false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected()) have_WIFI = true;

            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected()) have_MobileData = true;
        }
        return have_WIFI || have_MobileData;
    }

}