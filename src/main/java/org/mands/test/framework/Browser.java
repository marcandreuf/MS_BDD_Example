package org.mands.test.framework;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.mands.test.framework.pageObjects.PageObject;
import org.mands.test.framework.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marcandreuf
 */
public class Browser {
    
    private WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(Browser.class);

    
    public enum BrowserName {Firefox, Chrome}
    
    public Browser (WebDriver driver){
        this.driver = driver;
    }
    
    public Browser (BrowserName browserName){
        switch (browserName){
            case Firefox:
                driver = new FirefoxDriver();
                break;
            case Chrome:
                driver = new ChromeDriver();
                break;
            default: 
                throw new ExceptionInInitializerError("Browser not recognised, please try with Firefox or Chrome.");
        }
        logger.debug("Using browser "+browserName);
    }
  
    public <T extends PageObject> T onPage(Class<T> type) throws Exception, Throwable{
        Constructor<T> constructor;
        try {
            constructor = type.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | 
          IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new Exception("Page "+type+"its not a valid page object type.");
        }
    }
    
    public void open(String siteName) {
        driver.get(siteName);
    }
}
