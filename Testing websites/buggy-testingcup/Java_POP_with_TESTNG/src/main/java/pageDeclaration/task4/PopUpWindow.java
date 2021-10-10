package pageDeclaration.task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumSetup.PageFactorInitElement;

import static allFinals.TextOnPageFinal.*;

public class PopUpWindow extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_4
     */
    public PopUpWindow(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='APLIKUJ']")
    public WebElement buttonOpenNewWindow;

    @FindBy(xpath = "//iframe")
    public WebElement iframeSource;

    @FindBy(name = "name")
    public WebElement inputNameSurname;

    @FindBy(name = "email")
    public WebElement inputEmail;

    @FindBy(name = "phone")
    public WebElement inputPhone;

    @FindBy(id = "save-btn")
    public WebElement buttonSave;

    @FindBy(xpath = "//span[text()='" + INCORRECT_EMAIL_MESSAGE_TEXT_TASK4 + "']")
    public WebElement incorrectEmailSpan;

    @FindBy(xpath = "//span[text()='" + INCORRECT_PHONE_MESSAGE_TEXT_TASK4 + "']")
    public WebElement incorrectPhoneSpan;

    @FindBy(xpath = "//div/h1")
    public WebElement confirmationMessage;
}