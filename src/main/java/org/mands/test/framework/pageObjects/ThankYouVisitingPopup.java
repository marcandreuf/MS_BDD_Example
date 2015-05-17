package org.mands.test.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author marcandreuf
 */
public class ThankYouVisitingPopup extends PageObject {

    protected static final String XPATH_NOTHANKS_BTN = 
      "//*//div[@class='fsrDialog']//div[@class='fsrB']" +
      "//div[@class='fsrDeclineButtonContainer']//a[@class='fsrDeclineButton']";
    
    public ThankYouVisitingPopup(WebDriver driver) {
        super(driver);
    }

    public void closePopupIfVisible(){
        
        clickByXpath(XPATH_NOTHANKS_BTN);
    }


    
}
