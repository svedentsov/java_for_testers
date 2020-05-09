package ru.stqa.pft.addressbook.model;

public class ContactData {

    private final String firstname;
    private final String lastname;
    private final String group;
    private final String address;
    private final String email;
    private final String phone;

    public ContactData(String firstname,
                       String lastname,
                       String group,
                       String address,
                       String email,
                       String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}
