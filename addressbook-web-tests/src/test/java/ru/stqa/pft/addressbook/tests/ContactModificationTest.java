package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().addContactPage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new_group"));
        }
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().addContactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Firstname")
                    .withMiddlename("Middlename")
                    .withLastname("Lastname")
                    .withAddress("Yekaterinburg, st. Khokhryakova, 10")
                    .withPhoneHome("+7 343 000-00-00")
                    .withPhoneMobile("+7 900 000-00-00")
                    .withEmail("1.email@gmail.com")
                    .withEmail2("2.email@gmail.com")
                    .withEmail3("3.email@gmail.com")
                    .inGroup(groups.iterator().next())
            );
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Firstname (new)")
                .withMiddlename("Middlename (new)")
                .withLastname("Lastname (new)")
                .withAddress("Yekaterinburg, st. Khokhryakova, 10 (new)")
                .withPhoneHome("+7 343 000-00-00 (new)")
                .withPhoneMobile("+7 900 000-00-00 (new)")
                .withEmail("1.email@gmail.com (new)")
                .withEmail2("2.email@gmail.com (new)")
                .withEmail3("3.email@gmail.com (new)");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
