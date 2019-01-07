package com.google.codelabs.mdc.java.shrine;

public class DepartmentCategory {
    String name;
    String imagePath;

    public DepartmentCategory(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
