package com.example.byblosmobile;

public class movingServices {
    String identifier;
    String price;
    String numberOfMovers;

    boolean needCustomerName;
    boolean needDateOfBirth;
    boolean needCurrentAddress;
    boolean needEmail;
    boolean needTimeDuration;
    boolean needStartAndEndLocation;
    boolean needExpectedNumberOfBoxes;

    public movingServices()
    {
        this.identifier = null;
        this.price = null;
        this.numberOfMovers = null;
        this.needCustomerName = false;
        this.needDateOfBirth = false;
        this.needCurrentAddress = false;
        this.needEmail = false;
        this.needTimeDuration = false;
        this.needStartAndEndLocation= false;
        this.needExpectedNumberOfBoxes = false;
    }

    public movingServices(String price, String numberOfMovers, boolean needCustomerName,
                      boolean needDateOfBirth, boolean needCurrentAddress, boolean needEmail,
                      boolean needTimeDuration, boolean needStartAndEndLocation,
                          boolean needExpectedNumberOfBoxes)
    {
        this.price = price;
        this.numberOfMovers = numberOfMovers;

        this.needCustomerName = needCustomerName;
        this.needDateOfBirth = needDateOfBirth;
        this.needCurrentAddress = needCurrentAddress;
        this.needEmail = needEmail;
        this.needTimeDuration = needTimeDuration;
        this.needStartAndEndLocation = needStartAndEndLocation;
        this.needExpectedNumberOfBoxes = needExpectedNumberOfBoxes;
    }

    public String getNumberOfMovers(){return numberOfMovers;}

    public String getIdentifier(){return identifier;}

    public String getPrice(){return price;}

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public String toString()
    {
        return "Car Service {" +
                "Unique ID='" + getIdentifier()+ '\'' +
                ", Price='" + getPrice() + '\'' +
                ", Number of Movers Make='" + getNumberOfMovers() + '\'' +
                '}';
    }
}
