/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mands.test.framework.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author marcandreuf
 */
public class WomenPage extends PageObject {
    
    protected static final String XPATH_LEFTMENU = 
      "//div[@class='uniqueCtrl leftNavigation']//div[@class='simple-list']";

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    public void click_LeftMenu(String leftMenuItem) {
        waitAndClickByXpath(XPATH_LEFTMENU+"//*[contains(text(), '"+leftMenuItem+"')]");
    }
    
}
