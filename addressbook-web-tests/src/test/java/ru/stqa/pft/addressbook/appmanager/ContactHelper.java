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
     * Создать контакт
     *
     * @param contact
     */
    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    /**
     * Создание (редактирование) контакта
     *
     * @param contactData информация контакта
     * @param creation    frue - создание, false - редактирование
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
     * Запустить создание контакта
     */
    public void creation() {
        click(By.linkText("add new"));
    }

    /**
     * Обновление контакта
     */
    public void submitContactCreation() {
        click(By.name("submit"));
    }

    /**
     * Удалить контакт
     *
     * @param contact контакт
     */
    public void delete(ContactData contact) {
        selectContactById(contact);
        deleteSelectedContact();
        contactCache = null;
    }

    /**
     * Выбрать контакт
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

    /**
     * Подтвердить удаление контакта
     */
    public void submitContactModification() {
        click(By.name("update"));
    }

    /**
     * Выбрать вкладку "home"
     */
    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    /**
     * Нажать кнопку "home page"
     */
    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    /**
     * Проверить наличие контакта
     *
     * @return наличие контакта
     */
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    /**
     * Изменить контакт
     *
     * @param contact контакт
     */
    public void modify(ContactData contact) {
        selectContactById(contact);
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    /**
     * Кэш множества контактов
     */
    private Contacts contactCache = null;

    /**
     * Получить множество контактов
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
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withLastname(lastname)
                    .withFirstname(firstname)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones)
            );
        }
        return new Contacts(contactCache);
    }

    /**
     * Получить информацию на странице редактирования
     *
     * @param contact контакт
     */
    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withPhoneHome(home)
                .withPhoneMobile(mobile)
                .withPhoneWork(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    /**
     * Открыть страницу редактирования
     *
     * @param id идентификатор контакта
     */
    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    /**
     * Получить количество контактов
     *
     * @return количество контактов
     */
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
