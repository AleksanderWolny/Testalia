package pageDeclaration.task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumSetup.PageFactorInitElement;

public class LoginFormPage extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_6
     */
    public LoginFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "LoginForm__username")
    public WebElement loginFormUsername;

    @FindBy(id = "LoginForm__password")
    public WebElement loginFormPassword;

    @FindBy(id = "LoginForm_save")
    public WebElement loginFormSaveButton;

    @FindBy(xpath = "//div[contains(@class,'alert')]")
    public WebElement incorrectDataAlert;

    public void logIntoApp(String username, String password) {
        loginFormUsername.clear();
        loginFormUsername.sendKeys(username);

        loginFormPassword.clear();
        loginFormPassword.sendKeys(password);

        loginFormSaveButton.click();
    }
}
