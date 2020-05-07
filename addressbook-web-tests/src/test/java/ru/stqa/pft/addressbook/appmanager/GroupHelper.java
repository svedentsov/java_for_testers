package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.xpath("//*[@id=\"content\"]/div/i/a"));
    }

    public void submitGroupCreation() {
        click(By.xpath("//*[@id=\"content\"]/form/input[2]"));
    }

    public void fillGroupForm(GroupDate groupDate) {
        type(By.xpath("//*[@id=\"content\"]/form/input[1]"), groupDate.getName());
        type(By.xpath("//*[@id=\"content\"]/form/textarea[1]"), groupDate.getHeader());
        type(By.xpath("//*[@id=\"content\"]/form/textarea[2]"), groupDate.getFooter());
    }

    public void initGroupCreation() {
        click(By.xpath("(//input[@value='New group'])[1]"));
    }

    public void deleteSelectGroup() {
        click(By.xpath("//*[@id=\"content\"]/form/input[2]"));
    }

    public void selectGroup() {
        click(By.xpath("//*[@id=\"content\"]/form/span/input"));
    }

    public void initGroupModification() {
        click(By.xpath("//*[@id=\"content\"]/form/input[3]"));
    }

    public void submitGroupModification() {
        click(By.xpath("//*[@id=\"content\"]/form/input[3]"));
    }
}
