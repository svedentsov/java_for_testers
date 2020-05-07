package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactDate contactDate) {
        type(By.xpath("//*[@id=\"content\"]/form/input[3]"), contactDate.getFirstname());
        type(By.xpath("//*[@id=\"content\"]/form/input[5]"), contactDate.getLastname());
        type(By.xpath("//*[@id=\"content\"]/form/textarea[1]"), contactDate.getAddress());
        type(By.xpath("//*[@id=\"content\"]/form/input[14]"), contactDate.getEmail());
        type(By.xpath("//*[@id=\"content\"]/form/input[10]"), contactDate.getPhone());
    }

    public void submitContactCreation() {
        click(By.xpath("//*[@id=\"content\"]/form/input[21]"));
    }

    public void selectContact() {
        click(By.xpath("//*[@id=\"3\"]"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    public void confirmDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//*[@id=\"content\"]/form[1]/input[22]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
}
