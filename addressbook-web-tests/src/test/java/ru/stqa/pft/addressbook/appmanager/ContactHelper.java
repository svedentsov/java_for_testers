package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @param contactData
     * @param creation
     */
    public void fillContactForm(ContactData contactData, boolean creation) {
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
//               Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
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
     * Получить множество контактов.
     *
     * @return множество контактов
     */
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withHomePhone(phones[0])
                    .withMobilePhone(phones[1])
                    .withWorkPhone(phones[2]));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
