package steps.site;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import static junit.framework.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author marcandreuf on 15/05/2015.
 */
public class ViewBagContentsPriorCheckoutSteps {
    private final Logger logger = LoggerFactory.getLogger(ViewBagContentsPriorCheckoutSteps.class);
    private WebDriver driver;
    
    public static final String XPATH_WOMANS_MENU_BTN = 
      "//div[@id='main-nav']//nav//li//span[contains(text(),'Women')]";
    public static final String XPATH_SHIRTSBLUSES = 
      "//div[@class='uniqueCtrl leftNavigation']//div[@class='simple-list']//*[contains(text(), 'Shirts & Blouses')]";
    public static final String XPATH_SHIRT_FILERT =
      "//div[@class='content filter-options generic-type  open webOpen  ']//div//ul//li//*[contains(text(),'Shirt')]";
    public static final String XPATH_PRODUCT_ELEMENTS_LIST = 
      "//div[@id='product-listing']//form//ol//li";
    public static final String XPATH_SIZE_LABEL = "./label";
    public static final String CLASS_OUTOFSTOCK = "out-of-stock";
    public static final String XPATH_SIZES = "//form//table[@class='sizes displayCell']/tbody/tr/td";
    public static final String XPATH_COLORS = "//form//li[@id='swatch']//ul//li";
    public static final String XPATH_PRODUCT_DESCRIPTION = "./div[@class='detail']//h3//a";
    public static final String XPATH_BASKET_LINK = "//ul[@class='basket']/li/a";
    public static final String XPATH_BASKET_PRODUCT_DESCRIPTION = "//div[@class='product-info-wrap']/h3/a";

    @Given("^I am using (.+)$")
    public void i_am_using_ABrowser(final String browserName) throws Throwable {

        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default: throw new ExceptionInInitializerError("Browser not recognised, please try with Firefox or Chrome.");
        }

        logger.debug("using browser "+browserName);
    }

    @Given("^I am on the site (.+)$")
    public void i_am_on_the_site_name(final String siteName) throws Throwable {
        driver.get(siteName);
        logger.debug("site "+siteName);
    }

    private String selectedProductDescription;

    @Given("^I have added a shirt to my bag$")
    public void i_have_added_a_shirt_to_my_bag() throws Throwable {
       
        driver.findElement(By.xpath(XPATH_WOMANS_MENU_BTN)).click();

        (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.elementToBeClickable(By
          .xpath(XPATH_SHIRTSBLUSES)))
          .click();
        
        (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.elementToBeClickable(By
          .xpath(XPATH_SHIRT_FILERT)))
          .click();
        
        // Select the first shirt product.
        List<WebElement> products = driver.findElements(By.xpath(XPATH_PRODUCT_ELEMENTS_LIST));
        WebElement description;
        for(WebElement product : products){
            description = product.findElement(By.xpath(XPATH_PRODUCT_DESCRIPTION));
            if(description.getText().toLowerCase().contains("shirt")){
                selectedProductDescription = description.getText();
                description.click();
                break;
            }
        }        
        
        //Select color
        List<WebElement> colors = driver.findElements(By.xpath(XPATH_COLORS));
        for(WebElement color : colors){
            if(!color.getAttribute("class").toLowerCase().contains(CLASS_OUTOFSTOCK)){
                color.click();
                break;
            }
        }
        
        //Select size
        List<WebElement> sizes = driver.findElements(By.xpath(XPATH_SIZES));
        for(WebElement size : sizes){
            if(!size.getAttribute("class").toLowerCase().contains(CLASS_OUTOFSTOCK)){
                size.findElement(By.xpath(XPATH_SIZE_LABEL)).click();
                break;
            }
        }
        
        driver.findElement(By.xpath("//form//input[@value='add to bag']")).click();

    }

    @When("^I view the contents of my bag$")
    public void i_view_the_contents_of_my_bag() throws Throwable {
        driver.findElement(By.xpath(XPATH_BASKET_LINK)).click();
    }

    @Then("^I can see the contents of the bag include a shirt$")
    public void i_can_see_the_contents_of_the_bag_include_a_shirt() throws Throwable {
        String basketDescription = driver.findElement(By.xpath(XPATH_BASKET_PRODUCT_DESCRIPTION)).getText();
        assertTrue(basketDescription.equals(selectedProductDescription));
        driver.quit();
    }

}
