package org.mands.test.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marcandreuf
 */
public abstract class PageObject {
    
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final String XPATH_NOTHANKS_BTN = 
      "//*//div[@class='fsrDialog']//div[@class='fsrB']" +
      "//div[@class='fsrDeclineButtonContainer']//a[@class='fsrDeclineButton']";
    
    protected final WebDriver driver;
    
    public PageObject(WebDriver driver){
        this.driver = driver;
        closePopupIfVisible();
    }      
   
    public void clickByXpath(String menuItemXpath) {
        driver.findElement(By.xpath(menuItemXpath)).click();
    }    
        
    protected void waitAndClickByXpath(String xpath){
        (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
          .click();
    }
    
    protected void closePopupIfVisible(){
        try{
            (new WebDriverWait(driver, 2))
              .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_NOTHANKS_BTN)));        
        }catch(Exception e){
            logger.debug("Popup not present");
        }
    }
    
}
