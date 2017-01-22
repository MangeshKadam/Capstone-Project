package com.udacity.capstone.data;

/**
 * Created by mangesh on 14/1/17.
 */
public class AartiModel {

    public String getName() {
        return name;
    }

    public String name;
    public String fileName;

    public AartiModel(String name) {
        this.name = name;
    }

    public AartiModel(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
