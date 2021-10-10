package pageDeclaration.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

public class EditForm extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_3
     */
    public EditForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Menu']")
    public WebElement menuButton;

    @FindBy(xpath = "//a[text()='Formularz ']")
    public WebElement formButton;

    @FindBy(id = "start-edit")
    public WebElement startEditButton;

    @FindBy(xpath = "//a[contains(text(),'Zapisz')]")
    public WebElement saveToFileButton;

    @FindBy(id = "in-name")
    public WebElement nameLabel;

    @FindBy(id = "in-surname")
    public WebElement surnameLabel;

    @FindBy(id = "in-notes")
    public WebElement notesLabel;

    @FindBy(id = "in-phone")
    public WebElement phoneLabel;

    @FindBy(id = "in-file")
    public WebElement chooseFileButton;

    @FindBy(id = "save-btn")
    public WebElement saveFileButton;

    @FindBy(xpath = "//img[@class='preview-photo']")
    public WebElement photoPreview;

    @FindBy(xpath = "//span[@data-notify-text='']")
    public WebElement messageSpan;
}