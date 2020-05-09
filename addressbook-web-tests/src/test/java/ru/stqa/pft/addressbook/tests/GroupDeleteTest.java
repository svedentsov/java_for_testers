package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeleteTest extends TestBase {

    @Test
    public void groupDeleteTest() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("new_group", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
