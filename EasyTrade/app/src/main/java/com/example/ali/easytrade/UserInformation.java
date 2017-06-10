package com.example.ali.easytrade;

/**
 * Created by Ali on 3/7/2017.
 */

public class UserInformation {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    //public String emailAddress;

    public UserInformation() {

    }

    public UserInformation(String fname, String lname , String date, String number){
        this.firstName = fname;
        this.lastName = lname;
        this.dateOfBirth =date;
        this.phoneNumber = number;
       // this.emailAddress = emailAddress;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
