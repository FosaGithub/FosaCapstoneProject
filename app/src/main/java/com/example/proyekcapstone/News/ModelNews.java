package com.example.proyekcapstone.News;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "api_table")
public class ModelNews {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    @SerializedName("author")
    @ColumnInfo(name = "author")
    private String author;

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    private String urlToImage;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

//    @SerializedName("source")
//    @ColumnInfo(name = "source")
//    private Source source;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    private String content;

    public void setPublishedAt(String publishedAt){
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt(){
        return publishedAt;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setUrlToImage(String urlToImage){
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage(){
        return urlToImage;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

//    public void setSource(Source source){
//        this.source = source;
//    }

//    public Source getSource(){
//        return source;
//    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}