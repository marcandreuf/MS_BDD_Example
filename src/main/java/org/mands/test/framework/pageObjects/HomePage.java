package org.mands.test.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author marcandreuf
 */
public class HomePage extends PageObject {

    protected static final String XPATH_WOMANS_MENU_BTN = "//div[@id='main-nav']//nav//li//span";
    protected static final String XPATH_BASKET_LINK = "//ul[@class='basket']/li/a";
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void click_Menu(String menuItemText){
        String menuItemXpath = XPATH_WOMANS_MENU_BTN+"[contains(text(),'"+menuItemText+"')]";
        clickByXpath(menuItemXpath);
    }

    public void viewBag() {
        driver.findElement(By.xpath(XPATH_BASKET_LINK)).click();
    }
    
}
