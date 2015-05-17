package steps.site;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mands.test.framework.Browser;
import org.mands.test.framework.Browser.BrowserName;
import org.mands.test.framework.pageObjects.BagPage;
import org.mands.test.framework.pageObjects.HomePage;
import org.mands.test.framework.pageObjects.ItemPage;
import org.mands.test.framework.pageObjects.WomenPage;
import org.mands.test.framework.pageObjects.WomenSection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author marcandreuf on 15/05/2015.
 */
public class ViewBagContentsPriorCheckoutSteps {
      
    private Browser browser;    
    private String selectedItemDescription;

    @Given("^I am using (.+)$")
    public void i_am_using_ABrowser(final String browserName) throws Throwable {
        browser = new Browser(BrowserName.valueOf(browserName));
    }
    
    @Given("^I am on the site (.+)$")
    public void i_am_on_the_site_name(final String siteName) throws Throwable {
        browser.open(siteName);
    }

    @Given("^I have added a shirt to my bag$")
    public void i_have_added_a_shirt_to_my_bag() throws Throwable {
        browser.onPage(HomePage.class).click_Menu("Women");
        browser.onPage(WomenPage.class).click_LeftMenu("Shirts & Blouses");
        browser.onPage(WomenSection.class).filterBy("Shirt");
        selectedItemDescription = browser.onPage(WomenSection.class).selectFirstItemWithDescKeyWord("shirt");
        browser.onPage(ItemPage.class).selectFirstAvailableColor();
        browser.onPage(ItemPage.class).selectFirstAvailableSize();
        browser.onPage(ItemPage.class).addToBag();
    }

    @When("^I view the contents of my bag$")
    public void i_view_the_contents_of_my_bag() throws Throwable {
        browser.onPage(HomePage.class).viewBag();
    }

    @Then("^I can see the contents of the bag include a shirt$")
    public void i_can_see_the_contents_of_the_bag_include_a_shirt() throws Throwable {
        browser.onPage(BagPage.class).validateThereIsItemWithDescription(selectedItemDescription);
        browser.close();
    }

}
