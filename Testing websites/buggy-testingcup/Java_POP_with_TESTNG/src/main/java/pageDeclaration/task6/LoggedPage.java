package pageDeclaration.task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumSetup.PageFactorInitElement;

public class LoggedPage extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_6/logged
     */
    public LoggedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "logout")
    public WebElement logoutButton;

    @FindBy(xpath = "//a[@href='/task_6/download']")
    public WebElement downloadButton;

}