package pageDeclaration.task8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

public class PaymentDropDownMenu extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_8
     */
    public PaymentDropDownMenu(WebDriver driver) {
        super(driver);
    }

    @FindBys(@FindBy(xpath = "//select[@id='task8_form_cardType']/option"))
    public List<WebElement> cardTypeDropdownMenu;

    @FindBy(id = "task8_form_name")
    public WebElement inputNameSurname;

    @FindBy(id = "task8_form_cardNumber")
    public WebElement inputCardNumber;

    @FindBy(id = "task8_form_cardCvv")
    public WebElement inputCvvNumber;

    @FindBy(xpath = "//i[contains(@class,'glyphicon-info-sign')]")
    public WebElement cvvNumberInfoTooltip;

    @FindBy(xpath = "//div[@class='tooltip-inner']")
    public WebElement cvvNumberInfoTooltipInner;

    @FindBy(xpath = "//span[@class='help-block']/ul/li")
    public WebElement cvvIncorrectFormat;

    @FindBys(@FindBy(xpath = "//select[@id='task8_form_cardInfo_month']/option"))
    public List<WebElement> monthDropdownMenu;

    @FindBys(@FindBy(xpath = "//select[@id='task8_form_cardInfo_year']/option"))
    public List<WebElement> yearDropdownMenu;

    @FindBy(name = "task8_form[save]")
    public WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]/ul/li")
    public WebElement alertExpiryDate;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]/ul/li")
    public WebElement alertSuccess;

}
