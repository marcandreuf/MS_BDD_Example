/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mands.test.framework.pageObjects;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author marcandreuf
 */
public class BagPage extends PageObject {
    
    public static final String XPATH_BASKET_ITEM_DESCRIPTION = "//div[@class='product-info-wrap']/h3/a";

    public BagPage(WebDriver driver) {
        super(driver);
    }

    public void validateThereIsItemWithDescription(String selectedItemDescription) {
        String basketItemDescription = driver.findElement(By.xpath(XPATH_BASKET_ITEM_DESCRIPTION)).getText();
        assertTrue(basketItemDescription.equals(selectedItemDescription));
    }
    
}
