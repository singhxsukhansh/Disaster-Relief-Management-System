package edu.ucalgary.oop;

public class Inquirer {
    private int inquirerID; // ✅ New field for DB linkage
    private String firstName;
    private String lastName;
    private String servicesPhoneNum;
    private String info;
    private String name; // Optional, for compatibility with Inquiry.java

    // ✅ Constructor used by Inquiry.java
    public Inquirer(int inquirerID, String name) {
        this.inquirerID = inquirerID;
        this.name = name;
    }

    // Original constructor (still needed for flexibility)
    public Inquirer(String firstName, String lastName, String servicesPhoneNum, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.servicesPhoneNum = servicesPhoneNum;
        this.info = info;
    }

    // Getters
    public int getInquirerID() {
        return this.inquirerID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getServicesPhoneNum() {
        return this.servicesPhoneNum;
    }

    public String getInfo() {
        return this.info;
    }

    public String getName() {
        return this.name;
    }

    // Setters
    public void setInquirerID(int inquirerID) {
        this.inquirerID = inquirerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setServicesPhoneNum(String servicesPhoneNum) {
        this.servicesPhoneNum = servicesPhoneNum;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }
}
