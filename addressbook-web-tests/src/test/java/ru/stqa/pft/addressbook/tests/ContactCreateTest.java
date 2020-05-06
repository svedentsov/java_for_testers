package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactCreateTest extends TestBase {

    @Test
    public void contactCreateTest() {
        app.getNavigationHelper().gotoAddAddressPage();
        app.getContactHelper().fillContactForm(new ContactDate("Firstname", "Lastname", "Address", "Email", "Telephone home"));
        app.getContactHelper().submitContactCreation();
    }
}
