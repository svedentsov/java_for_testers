package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreateTest extends TestBase {

    @Test
    public void contactCreateTest() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "new_group", "Lenina, 15", "name@asd.com", "71234567890"), true);
    }
}
