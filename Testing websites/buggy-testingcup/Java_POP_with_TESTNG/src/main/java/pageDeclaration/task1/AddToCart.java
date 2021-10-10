package pageDeclaration.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

public class AddToCart extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_1
     */
    public AddToCart(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "main-reset")
    public WebElement resetTask;

    @FindBy(xpath = "//span[@class='summary-quantity']")
    public WebElement summaryQuantity;

    @FindBy(xpath = "//span[@class='summary-price']")
    public WebElement summaryPrice;

    @FindBys(@FindBy(xpath = "//div[@class='caption']/h4"))
    public List<WebElement> productH4;

    @FindBys(@FindBy(xpath = "//div[@class='caption']//p[1]"))
    public List<WebElement> productH4P;

    @FindBys(@FindBy(xpath = "//input[@type='number']"))
    public List<WebElement> productInput;

    @FindBys(@FindBy(xpath = "//button[@data-add-to-basket='']"))
    public List<WebElement> addButton;

    @FindBys(@FindBy(xpath = "//div[@class='col-md-9 text-on-button-level']"))
    public List<WebElement> nameAndPriceOfProduct;

    @FindBys(@FindBy(xpath = "//div[@class='col-md-9 text-on-button-level']/span"))
    public List<WebElement> quantityOfProduct;

    @FindBys(@FindBy(xpath = "//button[@data-remove-from-basket='']"))
    public List<WebElement> removeButton;
}