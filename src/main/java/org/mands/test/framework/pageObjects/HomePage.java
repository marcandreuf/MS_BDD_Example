package org.mands.test.framework.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author marcandreuf
 */
public class HomePage extends PageObject {

    protected static final String XPATH_WOMANS_MENU_BTN = "//div[@id='main-nav']//nav//li//span";
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void click_Menu(String menuItemText){
        String menuItemXpath = XPATH_WOMANS_MENU_BTN+"[contains(text(),'"+menuItemText+"')]";
        clickByXpath(menuItemXpath);
    }
    
}
