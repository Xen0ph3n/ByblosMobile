package com.example.byblosmobile;

import androidx.annotation.NonNull;

public class truckService extends vehicle{
    String identifier;
    String numberPlate;
    String price;
    String distanceRestriction;
    boolean needCustomerName;
    boolean needDateOfBirth;
    boolean needCurrentAddress;
    boolean needEmail;
    boolean needLicense;
    boolean needTimeDuration;
    boolean needAreaOfOperation;

    public truckService()
    {
        this.identifier = null;
        this.numberPlate = null;
        this.price = null;
        this.distanceRestriction = null;
        this.needCustomerName = false;
        this.needDateOfBirth = false;
        this.needCurrentAddress = false;
        this.needEmail = false;
        this.needLicense = false;
        this.needTimeDuration = false;
        this.needAreaOfOperation = false;
    }

    public truckService(String price, String make, String model, String type, String numberPlate, String distanceRestriction, boolean needCustomerName,
                      boolean needDateOfBirth, boolean needCurrentAddress, boolean needEmail,
                      boolean needLicense, boolean needTimeDuration, boolean needAreaOfOperation)
    {
        super(make, model, type);
        this.price = price;
        this.numberPlate = numberPlate;
        this.distanceRestriction = distanceRestriction;

        this.needCustomerName = needCustomerName;
        this.needDateOfBirth = needDateOfBirth;
        this.needCurrentAddress = needCurrentAddress;
        this.needEmail = needEmail;
        this.needLicense = needLicense;
        this.needTimeDuration = needTimeDuration;
        this.needAreaOfOperation = needAreaOfOperation;
    }

    public String getNumberPlate()
    {
        return numberPlate;
    }

    public String getDistanceRestriction()
    {
        return distanceRestriction;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public String getPrice()
    {
        return price;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "Truck Service {" +
                "Unique ID='" + getIdentifier()+ '\'' +
                ", Price='" + getPrice() + '\'' +
                ", Make='" + getMake() + '\'' +
                ", Model='" + getModel() + '\'' +
                ", Type='" + getType() + '\'' +
                ", Number Plate'" + getNumberPlate() + '\'' +
                ", Distance Restriction'" + getDistanceRestriction() + '\''+
                '}';
    }


}
