package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Вернуться на страницу списка групп.
     */
    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    /**
     * Создать группу.
     */
    public void submitGroupCreation() {
        click(By.name("submit"));
        ;
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
     * Выбрать группу.
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
     * Обновить группу.
     */
    public void submitGroupModification() {
        click(By.name("update"));
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

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectGroup();
        groupCache = null;
        returnToGroupPage();
    }

    /**
     * Проверка наличия группы.
     *
     * @return наличие группы
     */
    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    /**
     * Получить количество групп.
     *
     * @return количество групп
     */
    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    /**
     * Кэш для множества групп.
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
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
}
