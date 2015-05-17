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
public class WomenSection extends PageObject {
    
    protected static final String XPATH_STYLE_FILTER =
      "//div[@class='content filter-options generic-type  open webOpen  ']//div//ul//li";
    protected static final String XPATH_PRODUCT_ELEMENTS_LIST = 
      "//div[@id='product-listing']//form//ol//li";
    protected static final String XPATH_PRODUCT_DESCRIPTION = "./div[@class='detail']//h3//a";

    public WomenSection(WebDriver driver) {
        super(driver);
    }

    public WomenSection filterBy(String filterType) {
        waitAndClickByXpath(XPATH_STYLE_FILTER+"//*[contains(text(),'"+filterType+"')]");  
        return this;
    }

    public String selectFirstItemWithDescKeyWord(String keyWord) {
        WebElement description;
        String selectedProductDesc = "";
        List<WebElement> products = driver.findElements(By.xpath(XPATH_PRODUCT_ELEMENTS_LIST));
        for(WebElement product : products){
            description = product.findElement(By.xpath(XPATH_PRODUCT_DESCRIPTION));
            if(description.getText().toLowerCase().contains(keyWord)){
                selectedProductDesc = description.getText();
                description.click();
                break;
            }
        }
        return selectedProductDesc;
    }
    
}
