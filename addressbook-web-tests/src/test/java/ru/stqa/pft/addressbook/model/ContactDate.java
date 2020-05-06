package ru.stqa.pft.addressbook.model;

public class ContactDate {

    private final String firstname;
    private final String lastname;
    private final String address;
    private final String email;
    private final String phone;

    public ContactDate(String firstname, String lastname, String address, String email, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phone = telephone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
