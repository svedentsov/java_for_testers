package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData data, boolean creation) {
        type(By.name("firstname"), data.getFirstname());
        type(By.name("lastname"), data.getLastname());
        type(By.name("address"), data.getAddress());
        type(By.name("email"), data.getEmail());
        type(By.name("mobile"), data.getPhone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(data.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    public void confirmDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void createContact(ContactData data, boolean creation) {
        initContactCreation();
        fillContactForm(data, creation);
        submitContactCreation();
        returnToHomePage();
    }

    public void selectContact() {
        click(By.id("MassCB"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected"));
    }
}
