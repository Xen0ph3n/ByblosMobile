package com.example.byblosmobile;

// Vehicle class that stores generic common elements
public class vehicle {
    public String make;
    public String model;
    public String type;

    public vehicle()
    {
        this.make = null;
        this.model = null;
        this.type = null;
    }

    public vehicle(String make, String model, String type)
    {
        this.make = make;
        this.model = model;
        this.type = type;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public String getType() {
        return type;
    }
}
