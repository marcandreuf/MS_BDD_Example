package org.mands.test.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author marcandreuf
 */
public abstract class PageObject {
    
    protected final WebDriver driver;
    
    public PageObject(WebDriver driver){
        this.driver = driver;
    }  
    
   
    public void clickByXpath(String menuItemXpath) {
        driver.findElement(By.xpath(menuItemXpath)).click();
    }    
        
    protected void waitAndClickByXpath(String xpath){
        (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
          .click();
    }
    
    
}
