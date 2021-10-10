package allTests;

import allFinals.UrlAddressFinal;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageDeclaration.task10.DynamicTextLoad;
import seleniumSetup.InitialStateBeforeTest;


public class Task10Tests extends InitialStateBeforeTest {

    private DynamicTextLoad dTL;
    JavascriptExecutor js;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_10);
        dTL = new DynamicTextLoad(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void scrollPageDown() {
        Object windowSize = js.executeScript("return window.innerHeight;");
        Object pageHeight = js.executeScript("return document.body.scrollHeight");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Object scrollPosition = js.executeScript("return window.pageYOffset");
        long sPTooLong = Math.round((Double) scrollPosition);
        Assert.assertEquals((long) windowSize, ((long) pageHeight - sPTooLong));
    }

    @Test(dependsOnMethods = {"scrollPageDown"})
    public void scrollPageUp() {
        scrollPageDown();
        js.executeScript("window.scrollTo(0,0)");
        Object scrollPosition = js.executeScript("return window.pageYOffset");
        Assert.assertEquals((long) scrollPosition, 0);
    }

}