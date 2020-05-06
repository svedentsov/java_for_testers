package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase{

    @Test
    public void contactDeleteTest() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
