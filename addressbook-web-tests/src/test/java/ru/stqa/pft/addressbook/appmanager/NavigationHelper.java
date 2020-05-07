package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoAddAddressPage() {
        click(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
    }

    public void gotoHomePage() {
        click(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"));
    }
}
