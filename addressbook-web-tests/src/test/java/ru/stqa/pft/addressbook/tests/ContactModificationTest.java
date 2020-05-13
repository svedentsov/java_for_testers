package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

//    @Test(enabled = false)
//    public void testContactModification() {
//        app.goTo().homePage();
//        if (!app.contact().isThereAContact()) {
//            app.contact().createContact(new ContactData().withFirstname("Ivan").withLastname("Ivanovich").withGroup("new_group").withAddress("Lenina, 15").withEmail("name@asd.com").withAllPhones("71234567890"), true);
//        }
//        app.contact().initContactModification();
//        app.contact().fillContactForm(
//                new ContactData().withFirstname("test_name").withFirstname("test_surname"), false);
//        app.contact().submitContactModification();
//        app.contact().returnToHomePage();
//    }
}
