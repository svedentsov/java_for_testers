package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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
    public void selectGroup() {
        click(By.xpath("//*[@id=\"content\"]/form/span/input"));
    }

    /**
     * Запустить модификацию группы.
     */
    public void initGroupModification() {
        click(By.xpath("//*[@id=\"content\"]/form/input[3]"));
    }

    /**
     * Изменить группу.
     */
    public void submitGroupModification() {
        click(By.xpath("//*[@id=\"content\"]/form/input[3]"));
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
        return isElementPresent(By.name("selected"));
    }
}
