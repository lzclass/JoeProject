package com.wiwj.maigcer.model;

public class MainModel {
    private static final MainModel ourInstance = new MainModel();

    static MainModel getInstance() {
        return ourInstance;
    }

    private MainModel() {
    }
}
