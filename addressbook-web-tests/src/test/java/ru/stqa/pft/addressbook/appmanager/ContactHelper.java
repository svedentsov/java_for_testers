package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Создание (редактирование) контакта.
     *
     * @param data     данные контакта
     * @param creation frue - создание, false - редактирование
     */
    public void createContact(ContactData data, boolean creation) {
        initContactCreation();
        fillContactForm(data, creation);
        submitContactCreation();
        returnToHomePage();
    }

    /**
     * Заполнить форму контакта.
     *
     * @param data     данные контакта
     * @param creation frue - создание, false - редактирование
     */
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

    /**
     * Запустить создание контакта.
     */
    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    /**
     * Обновление контакта.
     */
    public void submitContactCreation() {
        click(By.name("submit"));
    }

    /**
     * Удаление выбранного контакта.
     */
    public void deleteSelectContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    public void confirmDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    /**
     * Подтверждение удаления контакта.
     */
    public void submitContactModification() {
        click(By.name("update"));
    }

    /**
     * Вернуться на главную страницу.
     */
    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    /**
     * Выбрать контакт.
     */
    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    /**
     * Проверка наличия контакта.
     *
     * @return наличие контакта
     */
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    /**
     * Получить список контактов.
     *
     * @return список контактов
     */
    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            var cells = element.findElements(By.tagName("td"));
            var firstname = cells.get(2).getText();
            var lastname = cells.get(1).getText();
            var address = cells.get(3).getText();
            var email = cells.get(4).getText();
            var phones = cells.get(5).getText();
            ContactData contact = new ContactData(id, firstname, lastname, address, email, phones);
            contacts.add(contact);
        }
        return contacts;
    }
}
