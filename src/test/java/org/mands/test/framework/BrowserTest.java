/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mands.test.framework;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mands.test.framework.pageObjects.HomePage;
import org.mands.test.framework.pageObjects.PageObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.openqa.selenium.WebDriver;


/**
 *
 * @author marcandreuf
 */
public class BrowserTest {
    
    private WebDriver mocked_webDriver;
    private Browser browser;
    private final String sampleUrl = "http://sampleUrl";
    
    @Before
    public void setUp(){
        mocked_webDriver = mock(WebDriver.class); 
        browser = new Browser(mocked_webDriver);        
    }
    
    @Test
    public void testCreatePageObjectByClassType() throws Exception, Throwable{        
        PageObject homePage = browser.onPage(HomePage.class);
        assertTrue(homePage instanceof HomePage);
    }
    
    @Test
    public void testOpenSite(){
        browser.open(sampleUrl);
        verify(mocked_webDriver).get(sampleUrl);
    }
    
    @Test
    public void testGetEnumConstantByName(){
        String browserName = "Firefox";
        Browser.BrowserName enumConst = Browser.BrowserName.valueOf(browserName);
        assertTrue(enumConst.name().equals(browserName));
    }
}
