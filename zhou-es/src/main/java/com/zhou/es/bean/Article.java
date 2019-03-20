package com.zhou.es.bean;

import io.searchbox.annotations.JestId;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "zhou",type = "article")
public class Article {

//    @JestId
    private Integer id;
    private String author;
    private String title;
    private String contents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
