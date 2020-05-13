package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

public class ContactCreateTest extends TestBase {

    @Test(enabled = true)
    public void contactCreateTest() {
        app.goTo().homePage();
        app.contact().initContactCreation();
        File photo = new File("src/main/resources/stru.jpg");
        app.contact().fillContactForm(
                new ContactData().withFirstname("test_name").withLastname("test_surname").withGroup("test1").withPhoto(photo), true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }
}
