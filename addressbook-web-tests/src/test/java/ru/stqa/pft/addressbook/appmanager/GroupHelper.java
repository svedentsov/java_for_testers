package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Класс помощник для работы с группами.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Нажать на таб "groups".
     */
    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    /**
     * Нажать на кнопку "Enter information".
     */
    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    /**
     * Нажать на кнопку "Update".
     */
    public void submitGroupModification() {
        click(By.name("update"));
    }

    /**
     * Заполнить форму группы.
     *
     * @param group данные группы
     */
    public void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.getName());
        type(By.name("group_header"), group.getHeader());
        type(By.name("group_footer"), group.getFooter());
    }

    /**
     * Запустить создание группы.
     */
    public void initGroupCreation() {
        click(By.name("new"));
    }

    /**
     * Удалить выбранную группу.
     */
    public void deleteSelectGroup() {
        click(By.name("delete"));
    }

    /**
     * Выбрать группу по id.
     *
     * @param id id группы
     */
    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    /**
     * Запустить модификацию группы.
     */
    public void initGroupModification() {
        click(By.name("edit"));
    }

    /**
     * Создать группу.
     *
     * @param group данные группы
     */
    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    /**
     * Модифицировать группу.
     *
     * @param group группа
     */
    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    /**
     * Удалить группу
     *
     * @param group группа
     */
    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectGroup();
        groupCache = null;
        returnToGroupPage();
    }

    /**
     * Проверить наличие группы.
     *
     * @return признак наличия
     */
    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    /**
     * Получить количество групп.
     *
     * @return количество групп
     */
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    /**
     * Кэш множества групп.
     */
    private Groups groupCache = null;

    /**
     * Получить множество групп.
     *
     * @return множество групп
     */
    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groupCache.add(new GroupData()
                    .withId(id)
                    .withName(name));
        }
        return new Groups(groupCache);
    }
}
