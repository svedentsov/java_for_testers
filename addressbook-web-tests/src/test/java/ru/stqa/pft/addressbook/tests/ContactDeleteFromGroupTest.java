package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new_group"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            GroupData groups = app.db().groups().iterator().next();
            app.contact().create(new ContactData()
                    .withFirstname("Firstname")
                    .withMiddlename("Middlename")
                    .withLastname("Lastname")
                    .withAddress("Yekaterinburg, st. Khokhryakova, 10")
                    .withPhoneHome("+7(000) 000-00-00")
                    .withPhoneMobile("+7(000) 000-00-00")
                    .withPhoneWork("+7(000) 000-00-00")
                    .withEmail("1.email@gmail.com")
                    .withEmail2("2.email@gmail.com")
                    .withEmail3("3.email@gmail.com")
                    .inGroup(groups)
            );
        }
    }

    @Test
    public void testContactDeleteFromGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        if (!group.equals(contact.getGroups())) {
            app.goTo().homePage();
            app.contact().contactAddToGroup(contact, group);
        }
        groups.removeAll(contact.getGroups());
        app.goTo().homePage();
        app.contact().contactDeletionFromGroup(contact, group);
        app.db().refresh(contact);
        assertThat(contact.getGroups(), not(hasItem(group)));
    }
}
