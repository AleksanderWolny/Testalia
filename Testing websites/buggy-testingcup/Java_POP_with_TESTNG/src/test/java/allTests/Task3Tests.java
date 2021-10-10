package allTests;

import allFinals.UrlAddressFinal;
import allMethods.TestDataProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task3.EditForm;
import seleniumSetup.InitialStateBeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static allFinals.TextOnPageFinal.SUCCESSFUL_MESSAGE_TEXT_TASK_3;


public class Task3Tests extends InitialStateBeforeTest {

    private EditForm eF;
    SoftAssert sa;
    Actions actions;
    JavascriptExecutor js;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_3);
        eF = new EditForm(driver);
        sa = new SoftAssert();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void showDropDownMenuWithMouseHover() {
        sa.assertFalse(eF.formButton.isDisplayed());
        sa.assertFalse(eF.startEditButton.isDisplayed());
        sa.assertFalse(eF.saveToFileButton.isDisplayed());

        actions.moveToElement(eF.menuButton).perform();
        sa.assertTrue(eF.formButton.isDisplayed());

        actions.moveToElement(eF.formButton).perform();
        sa.assertTrue(eF.startEditButton.isDisplayed());
        sa.assertTrue(eF.saveToFileButton.isDisplayed());
        sa.assertAll();
    }

    @Test(dependsOnMethods = "showDropDownMenuWithMouseHover")
    public void enableFormAfterButtonClick() {
        sa.assertFalse(eF.nameLabel.isEnabled());
        sa.assertFalse(eF.surnameLabel.isEnabled());
        sa.assertFalse(eF.notesLabel.isEnabled());
        sa.assertFalse(eF.phoneLabel.isEnabled());
        sa.assertFalse(eF.chooseFileButton.isEnabled());
        sa.assertFalse(eF.saveFileButton.isDisplayed());
        actions.moveToElement(eF.menuButton).perform();
        actions.moveToElement(eF.formButton).perform();
        actions.moveToElement(eF.startEditButton).perform();
        eF.startEditButton.click();

        sa.assertTrue(eF.nameLabel.isEnabled());
        sa.assertTrue(eF.surnameLabel.isEnabled());
        sa.assertTrue(eF.notesLabel.isEnabled());
        sa.assertTrue(eF.phoneLabel.isEnabled());
        sa.assertTrue(eF.chooseFileButton.isEnabled());
        sa.assertTrue(eF.saveFileButton.isDisplayed());
        sa.assertAll();
    }

    @Test(dataProvider = "fillFormCorrectData",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = "enableFormAfterButtonClick")
    public void fillFormWithNewContent(String myName, String mySurname, String myNotes, String myPhone) {
        actions.moveToElement(eF.menuButton).perform();
        actions.moveToElement(eF.formButton).perform();
        actions.moveToElement(eF.startEditButton).perform();
        eF.startEditButton.click();

        eF.nameLabel.clear();
        eF.nameLabel.sendKeys(myName);
        eF.surnameLabel.clear();
        eF.surnameLabel.sendKeys(mySurname);
        eF.notesLabel.clear();
        eF.notesLabel.sendKeys(myNotes);
        eF.phoneLabel.clear();
        eF.phoneLabel.sendKeys(myPhone);
        eF.saveFileButton.click();

        sa.assertEquals(myName, js.executeScript("return arguments[0].value", eF.nameLabel));
        sa.assertEquals(mySurname, js.executeScript("return arguments[0].value", eF.surnameLabel));
        sa.assertEquals(myNotes, js.executeScript("return arguments[0].value", eF.notesLabel));
        sa.assertEquals(myPhone, js.executeScript("return arguments[0].value", eF.phoneLabel));
        sa.assertAll();
    }

    @Test(dependsOnMethods = "enableFormAfterButtonClick")
    public void uploadPhotoFromHardDrive() throws IOException {
        File myPhoto = new File("src/main/resources/task3/aw.png");
        byte[] myPhotoChangeToByte = FileUtils.readFileToByteArray(myPhoto);
        String encodedString = Base64.getEncoder().encodeToString(myPhotoChangeToByte);

        actions.moveToElement(eF.menuButton).perform();
        actions.moveToElement(eF.formButton).perform();
        actions.moveToElement(eF.startEditButton).perform();
        eF.startEditButton.click();

        eF.chooseFileButton.sendKeys(myPhoto.getAbsolutePath());
        eF.saveFileButton.click();
        Assert.assertEquals("data:image/png;base64," + encodedString, js.executeScript("return arguments[0].src", eF.photoPreview));
    }

    @Test(dependsOnMethods = {"fillFormWithNewContent", "uploadPhotoFromHardDrive"})
    public void visibilityOfSuccessSaveMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 3);

        actions.moveToElement(eF.menuButton).perform();
        actions.moveToElement(eF.formButton).perform();
        actions.moveToElement(eF.startEditButton).perform();
        eF.startEditButton.click();
        eF.saveFileButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOf(eF.messageSpan));
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Span with message, didn't show up, after clicking save button.");
        }
        sa.assertTrue(eF.messageSpan.isDisplayed());
        sa.assertEquals(SUCCESSFUL_MESSAGE_TEXT_TASK_3, eF.messageSpan.getText());
        sa.assertAll();
    }
}