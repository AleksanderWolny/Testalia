package pageDeclaration.task9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import seleniumSetup.PageFactorInitElement;

import java.util.List;

import static allFinals.CssValueFinal.COLLAPSED_FOLDER;

public class TreeFolder extends PageFactorInitElement {
    /*
    webpage: https://buggy-testingcup.pgs-soft.com/task_9
     */
    public TreeFolder(WebDriver driver) {
        super(driver);
    }

    @FindBys(@FindBy(xpath = "//i[@class='jstree-icon jstree-ocl']"))
    public List<WebElement> treeIconNode;

    @FindBy(xpath = "//div[@class='col-md-9 content-container']/h1")
    public WebElement headerContents;

    @FindBys(@FindBy(xpath = "//div[@class='col-md-9 content-container']/p"))
    public List<WebElement> bodyContents;

    @FindBys(@FindBy(xpath = "//a[contains(@class,'jstree-anchor')]"))
    public List<WebElement> folderName;

    @FindBy(xpath = "//ul[contains(@class,'vakata-context')]/li/a")
    public WebElement changeName;

    @FindBy(xpath = "//input[@class='jstree-rename-input']")
    public WebElement changeNameInput;

    public boolean expandAllFolders() {
        boolean flag = false;
        while (!flag) {
            for (WebElement myList : treeIconNode) {
                if (myList.getCssValue("background-position").equals(COLLAPSED_FOLDER)) {
                    myList.click();
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        return true;
    }
}
