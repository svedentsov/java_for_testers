package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
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
