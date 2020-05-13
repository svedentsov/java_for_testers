package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

//    @Test(enabled = false)
//    public void contactDeleteTest() {
//        app.goTo().homePage();
//        if (!app.contact().isThereAContact()) {
//            app.contact().createContact(new ContactData("Ivan", "Ivanovich", "new_group", "Lenina, 15", "name@asd.com", "71234567890"), true);
//        }
//        List<ContactData> before = app.contact().getContactList();
//        app.contact().selectContact(before.size() - 1);
//        app.contact().deleteSelectContact();
//        app.contact().confirmDeleteContact();
//        app.contact().returnToHomePage();
//        List<ContactData> after = app.contact().getContactList();
//        Assert.assertEquals(after.size(), before.size() - 1);
//    }
}
