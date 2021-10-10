package seleniumSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageFactorInitElement {
    protected WebDriver driver;

    public PageFactorInitElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}