package com.example.codecare.model;

public class Profile {
    private String name;
    private int age;
    private String address;
    private String phone;
    private String bloodGroup;
    private String medicalDetails;
    private String emergencyContact;
    private String email;  // Added for login
    private String password;  // Added for login (hash in production)

    // Constructors
    public Profile() {}

    public Profile(String name, int age, String address, String phone, String bloodGroup,
                   String medicalDetails, String emergencyContact, String email, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.medicalDetails = medicalDetails;
        this.emergencyContact = emergencyContact;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters (all fields)
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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}