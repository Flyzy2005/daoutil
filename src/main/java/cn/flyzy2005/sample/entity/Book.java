package cn.flyzy2005.sample.entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by Fly on 2017/4/21.
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;

    public Book() {
    }

    public Book(int id, String name, String author, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
