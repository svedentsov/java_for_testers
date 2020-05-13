package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreateTest extends TestBase {

//    @Test(enabled = false)
//    public void contactCreateTest() {
//        app.goTo().homePage();
//        List<ContactData> before = app.contact().all();
//        ContactData contact = new ContactData("Ivan", "Ivanovich", "new_group", "Lenina, 15", "name@asd.com", "71234567890");
//        app.contact().createContact(contact, true);
//        List<ContactData> after = app.contact().getContactList();
//        Assert.assertEquals(after.size(), before.size() + 1);
//    }
}
