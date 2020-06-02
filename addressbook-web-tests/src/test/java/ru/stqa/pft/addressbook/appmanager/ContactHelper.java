package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Создание (редактирование) контакта.
     *
     * @param contact  данные контакта
     * @param creation frue - создание, false - редактирование
     */
    public void createContact(ContactData contact, boolean creation) {
        creation();
        fillContactForm(contact, creation);
        submitContactCreation();
        gotoHomePage();
    }

    /**
     * Создать контакт
     *
     * @param contact
     */
    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        gotoHomePage();
    }

    /**
     * Заполнить форму контакта.
     *
     * @param contactData
     * @param creation
     */
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("mobile"), contactData.getPhoneMobile());
        type(By.name("work"), contactData.getPhoneWork());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        //attach(By.name("photo"),contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    /**
     * Запустить создание контакта.
     */
    public void creation() {
        click(By.linkText("add new"));
    }

    /**
     * Обновление контакта.
     */
    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact);
        deleteSelectedContact();
        contactCache = null;
    }

    /**
     * Выбрать контакт.
     */
    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    /**
     * Выбрать контакт по идентификатору
     *
     * @param contact контакт
     */
    public void selectContactById(ContactData contact) {
        wd.findElement(By.xpath(".//input[@value='" + contact.getId() + "']")).click();
    }

    /**
     * Удалить выбранный контакт
     */
    public void deleteSelectedContact() {
        click(By.xpath(".//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    /**
     * Подтвердить удаление контакта
     */
    public void submitContactModification() {
        click(By.name("update"));
    }

    /**
     * Вернуться на главную страницу.
     */
    public void gotoHomePage() {
        click(By.linkText("home"));
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
     * Кэш множества контактов.
     */
    private Contacts contactCache = null;

    /**
     * Получить множество контактов.
     *
     * @return множество контактов
     */
    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
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
                .withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work);
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
