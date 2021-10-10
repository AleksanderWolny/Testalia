package pageDeclaration.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

public class FilterProductByCategory extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_2
     */
    public FilterProductByCategory(WebDriver driver) {
        super(driver);
    }

    @FindBys(@FindBy(xpath = "//select/option"))
    public List<WebElement> selectOptionList;

    @FindBy(xpath = "//span[@dir='ltr']")
    public WebElement expandContainer;

    @FindBys(@FindBy(xpath = "//li[@role='treeitem']"))
    public List<WebElement> resultsOption;

    @FindBy(xpath = "//span[@class='select2-selection__rendered']")
    public WebElement selectedOption;

    @FindBys(@FindBy(xpath = "//p/strong"))
    public List<WebElement> categoryInProducts;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement searchCategoryInput;
}