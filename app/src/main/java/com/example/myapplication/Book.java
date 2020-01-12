package com.example.myapplication;

import com.google.firebase.firestore.Exclude;

public class Book {

    private String documentId;
    private String title;
    private String author;
    private String summary;
    private int copies;
    private double price;

    public Book(){

    }

    public Book(String title, String author, String summary, int copies, double price) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copies = copies;
        this.price = price;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

