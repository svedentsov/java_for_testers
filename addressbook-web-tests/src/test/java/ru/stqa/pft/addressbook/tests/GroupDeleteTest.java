package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void groupDeleteTest() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
