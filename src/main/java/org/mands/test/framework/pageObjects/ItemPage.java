/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mands.test.framework.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author marcandreuf
 */
public class ItemPage extends PageObject {

    protected static final String XPATH_COLORS = "//form//li[@id='swatch']//ul//li";
    protected static final String CLASS_OUTOFSTOCK = "out-of-stock";
    protected static final String XPATH_SIZE_LABEL = "./label";
    protected static final String XPATH_SIZES = "//form//table[@class='sizes displayCell']/tbody/tr/td";
    protected static final String XPATH_ADD_TO_BAG_BTN = "//form//input[@value='add to bag']";


    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstAvailableColor() {
        List<WebElement> colors = driver.findElements(By.xpath(XPATH_COLORS));
        for(WebElement color : colors){
            if(!color.getAttribute("class").toLowerCase().contains(CLASS_OUTOFSTOCK)){
                color.click();
                break;
            }
        }
    }

    public void selectFirstAvailableSize() {
        List<WebElement> sizes = driver.findElements(By.xpath(XPATH_SIZES));
        for(WebElement size : sizes){
            if(!size.getAttribute("class").toLowerCase().contains(CLASS_OUTOFSTOCK)){
                size.findElement(By.xpath(XPATH_SIZE_LABEL)).click();
                break;
            }
        }
    }

    public void addToBag() {
        driver.findElement(By.xpath(XPATH_ADD_TO_BAG_BTN)).click();
    }
    
}
