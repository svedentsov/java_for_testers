package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {

    @Test
    public void contactDeleteTest() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "new_group", "Lenina, 15", "name@asd.com", "71234567890"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContact();
        app.getContactHelper().confirmDeleteContact();
    }
}
