package com.example.codecare.model;

public class profile {
    private String name;
    private int age;
    private String address;
    private String phone;
    private String bloodGroup;
    private String medicalDetails;
    private String emergencyContact;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getMedicalDetails() { return medicalDetails; }
    public void setMedicalDetails(String medicalDetails) { this.medicalDetails = medicalDetails; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
}
