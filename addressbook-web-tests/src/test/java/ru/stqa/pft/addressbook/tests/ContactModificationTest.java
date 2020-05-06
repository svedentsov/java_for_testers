package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Firstname new", "Lastname new", "Address new", "Email new", "Phone new"));
        app.getContactHelper().submitContactModification();
    }
}
