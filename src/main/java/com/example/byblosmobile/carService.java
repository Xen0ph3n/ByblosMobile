package com.example.byblosmobile;
import androidx.annotation.NonNull;

// Car service class that extends from properties from vehicle

public class carService extends vehicle{
    String identifier;
    String numberPlate;
    String price;
    boolean needCustomerName;
    boolean needDateOfBirth;
    boolean needCurrentAddress;
    boolean needEmail;
    boolean needLicense;
    boolean needTimeDuration;

    public carService()
    {
        this.identifier = null;
        this.numberPlate = null;
        this.price = null;
        this.needCustomerName = false;
        this.needDateOfBirth = false;
        this.needCurrentAddress = false;
        this.needEmail = false;
        this.needLicense = false;
        this.needTimeDuration = false;
    }
    public carService(String price, String make, String model, String type, String numberPlate, boolean needCustomerName,
                      boolean needDateOfBirth, boolean needCurrentAddress, boolean needEmail,
                      boolean needLicense, boolean needTimeDuration)
    {
        super(make, model, type);
        this.price = price;
        this.numberPlate = numberPlate;

        this.needCustomerName = needCustomerName;
        this.needDateOfBirth = needDateOfBirth;
        this.needCurrentAddress = needCurrentAddress;
        this.needEmail = needEmail;
        this.needLicense = needLicense;
        this.needTimeDuration = needTimeDuration;
    }

    public String getNumberPlate()
    {
        return numberPlate;
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
        return "Car Service {" +
                "Unique ID='" + getIdentifier()+ '\'' +
                ", Price='" + getPrice() + '\'' +
                ", Make='" + getMake() + '\'' +
                ", Model='" + getModel() + '\'' +
                ", Type='" + getType() + '\'' +
                ", Number Plate'" + getNumberPlate() + '\'' +
                '}';
    }

}
