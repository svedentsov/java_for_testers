package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

//    @Test
//    public void testContactPhone() {
//        app.goTo().homePage();
//        ContactData contact = app.contact().all().iterator().next();
//        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
//        assertThat(contact.getAllPhones(), equalTo(megrePhones(contactInfoFromEditForm)));
//    }

//    private String megrePhones(ContactData contact) {
//        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
//                .stream().filter(s -> !s.equals(""))
//                .map(ContactPhoneTest::cleaned)
//                .collect(Collectors.joining("\n"));
//    }

    public static String cleaned(String phone) {
        return phone.replace("\\s", "").replace("[-()]", "");
    }
}
