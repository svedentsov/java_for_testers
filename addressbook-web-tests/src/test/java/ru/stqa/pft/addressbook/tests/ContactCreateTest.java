package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class ContactCreateTest extends TestBase {

    @Test(enabled = true)
    public void contactCreateTest() {
        Groups groups = app.db().groups();
        File photo = new File("src/main/resources/stru.jpg");
        ContactData newContact = new ContactData()
                .withFirstname("test_name")
                .withLastname("test_surname")
                .withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.goTo().homePage();
        app.contact().initContactCreation();
        app.contact().fillContactForm(newContact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }
}
