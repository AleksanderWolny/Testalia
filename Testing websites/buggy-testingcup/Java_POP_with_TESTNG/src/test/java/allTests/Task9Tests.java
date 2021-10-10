package allTests;

import allFinals.UrlAddressFinal;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task9.TreeFolder;
import seleniumSetup.InitialStateBeforeTest;

import static allFinals.TextOnPageFinal.ALERT_MAX_CHAR_FOLDER_NAME_TASK_9;


public class Task9Tests extends InitialStateBeforeTest {

    private TreeFolder tF;
    SoftAssert sa = new SoftAssert();
    Actions actions;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_9);
        tF = new TreeFolder(driver);
        actions = new Actions(driver);
    }

    @Test
    public void expandFolders() {
        Assert.assertTrue(tF.expandAllFolders());
    }

    @Test(dependsOnMethods = {"expandFolders"}, priority = 1)
    public void contentVisibility() {
        if (tF.expandAllFolders()) {
            for (WebElement myList : tF.folderName) {
                myList.click();
                sa.assertEquals(tF.headerContents.getText(), myList.getText());
                for (WebElement myList_2 : tF.bodyContents) {
                    sa.assertTrue(myList_2.isDisplayed());
                }
            }
        }
        sa.assertAll();
    }

    @Test
    public void changeFolderNameAbility() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        actions.contextClick(tF.folderName.get(0)).perform();
        try {
            wait.until(ExpectedConditions.visibilityOf(tF.changeName));
        } catch (TimeoutException e) {
            Assert.fail("Right click on folder name, don't show option to change name of folder!");
        }
        Assert.assertTrue(tF.changeName.isDisplayed());
    }

    @Test(dependsOnMethods = {"changeFolderNameAbility"})
    public void maxCharFolderName() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        actions.contextClick(tF.folderName.get(0)).perform();
        tF.changeName.click();
        for (int i = 0; i < 21; i++) {
            tF.changeNameInput.sendKeys("a");
        }
        tF.changeNameInput.sendKeys(Keys.RETURN);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            Assert.fail("Alert didn't show up!");
        }
        Assert.assertEquals(driver.switchTo().alert().getText(), ALERT_MAX_CHAR_FOLDER_NAME_TASK_9);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(dependsOnMethods = {"changeFolderNameAbility"}, priority = 2)
    public void changeFolderNameUpdateHeader() {
        String testString = "testFolderName";
        actions.contextClick(tF.folderName.get(0)).perform();
        tF.changeName.click();
        tF.changeNameInput.sendKeys(testString);
        tF.changeNameInput.sendKeys(Keys.RETURN);
        sa.assertEquals(tF.folderName.get(0).getText(), testString, "Folder name didn't change!");
        sa.assertEquals(tF.headerContents.getText(), testString, "Header text didn't change!");
        sa.assertAll();
    }
}