package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Вернуться на страницу списка групп.
     */
    public void returnToGroupPage() {
        click(By.xpath("//*[@id=\"content\"]/div/i/a"));
    }

    /**
     * Создать группу.
     */
    public void submitGroupCreation() {
        click(By.xpath("//*[@id=\"content\"]/form/input[2]"));
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
    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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
    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
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
     * Получить список групп.
     *
     * @return список групп
     */
    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (var element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
