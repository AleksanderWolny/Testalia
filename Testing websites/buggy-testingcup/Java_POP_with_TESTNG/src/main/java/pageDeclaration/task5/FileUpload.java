package pageDeclaration.task5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

import static allFinals.TextOnPageFinal.INCORRECT_EMAIL_MESSAGE_TEXT_TASK4;
import static allFinals.TextOnPageFinal.INCORRECT_PHONE_MESSAGE_TEXT_TASK4;

public class FileUpload extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_5
     */
    public FileUpload(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='file']")
    public WebElement buttonBrowse;

    @FindBys(@FindBy(xpath = "//table/tbody//td"))
    public List<WebElement> tableTDs;
}