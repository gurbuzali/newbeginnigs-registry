package com.newbeginnings.registry.model;

import java.util.Date;

public class Participant {

    private String referenceNumber;
    private String name;
    private Date dateOfBirth;
    private String phoneNumber;
    private String address;

    public Participant(String name, Date dateOfBirth, String phoneNumber, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (referenceNumber != null ? !referenceNumber.equals(that.referenceNumber) : that.referenceNumber != null)
            return false;
        if (!name.equals(that.name)) return false;
        if (!dateOfBirth.equals(that.dateOfBirth)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = referenceNumber != null ? referenceNumber.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
