package pageDeclaration.task7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

public class DragAndDrop extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_7
     */
    public DragAndDrop(WebDriver driver) {
        super(driver);
    }

    @FindBys(@FindBy(xpath = "//input[@type='number']"))
    public List<WebElement> firstProductInput;

    @FindBys(@FindBy(xpath = "//button[@data-add-to-basket='']"))
    public List<WebElement> addToCartButton;

    @FindBy(xpath = "//span[@class='summary-quantity']")
    public WebElement summaryQuantity;

    @FindBys(@FindBy(xpath = "//div[contains(@class,'draggable')]/img"))
    public List<WebElement> draggableElement;

    @FindBy(xpath = "//div[contains(@class,'ui-droppable')]")
    public WebElement droppableElement;

}
