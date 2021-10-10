package allTests;

import allMethods.TestDataProvider;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task4.PopUpWindow;
import seleniumSetup.InitialStateBeforeTest;

import static allFinals.TextOnPageFinal.*;
import static allFinals.UrlAddressFinal.URL_TASK_4;
import static allFinals.UrlAddressFinal.URL_TASK_4_IFRAME_ADDRESS;


public class Task4Tests extends InitialStateBeforeTest {

    private PopUpWindow pUW;
    SoftAssert sa;
    String parentHandle;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(URL_TASK_4);
        pUW = new PopUpWindow(driver);
        sa = new SoftAssert();
        parentHandle = driver.getWindowHandle();
    }

    @Test
    public void checkIfANewWindowOpens() {
        pUW.buttonOpenNewWindow.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        sa.assertEquals(URL_TASK_4_IFRAME_ADDRESS, pUW.iframeSource.getAttribute("src"));
        driver.close();

        driver.switchTo().window(parentHandle);
        sa.assertEquals(URL_TASK_4, driver.getCurrentUrl());
        sa.assertAll();
    }

    @Test(dataProvider = "correctDataApplyForm",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = "checkIfANewWindowOpens")
    public void enterSampleDataAndSendIt(String nameSurnameData, String emailData, String phoneData) {
        pUW.buttonOpenNewWindow.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().frame(0);

        pUW.inputNameSurname.clear();
        pUW.inputNameSurname.sendKeys(nameSurnameData);
        pUW.inputEmail.clear();
        pUW.inputEmail.sendKeys(emailData);
        pUW.inputPhone.clear();
        pUW.inputPhone.sendKeys(phoneData);
        sa.assertEquals(nameSurnameData, pUW.inputNameSurname.getAttribute("value"));
        sa.assertEquals(emailData, pUW.inputEmail.getAttribute("value"));
        sa.assertEquals(phoneData, pUW.inputPhone.getAttribute("value"));
        sa.assertAll();

        pUW.buttonSave.click();
        driver.close();
        driver.switchTo().window(parentHandle);
    }

    @Test(dataProvider = "incorrectDataApplyForm",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = "checkIfANewWindowOpens")
    public void enterIncorrectDataAndCheckValidation(String nameSurnameData, String emailData, String phoneData) {
        pUW.buttonOpenNewWindow.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().frame(0);
        pUW.inputNameSurname.clear();
        pUW.inputNameSurname.sendKeys(nameSurnameData);
        pUW.inputEmail.clear();
        pUW.inputEmail.sendKeys(emailData);
        pUW.inputPhone.clear();
        pUW.inputPhone.sendKeys(phoneData);

        pUW.buttonSave.click();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        try {
            wait.until(ExpectedConditions.visibilityOf(pUW.incorrectEmailSpan));
            sa.assertEquals(INCORRECT_EMAIL_MESSAGE_TEXT_TASK4, pUW.incorrectEmailSpan.getText());
            sa.assertEquals(INCORRECT_PHONE_MESSAGE_TEXT_TASK4, pUW.incorrectPhoneSpan.getText());
            sa.assertAll();
        } catch (NoSuchElementException | TimeoutException e) {
            driver.close();
            driver.switchTo().window(parentHandle);
            Assert.fail("Spans with messages, didn't show up, after clicking save button.");
        }
        driver.close();
        driver.switchTo().window(parentHandle);
    }

    @Test(dependsOnMethods = "checkIfANewWindowOpens")
    public void checkMaxFiftyChars() {
        String nameSurnameData = "a";
        String emailData = "a";
        pUW.buttonOpenNewWindow.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().frame(0);

        pUW.inputNameSurname.clear();
        pUW.inputEmail.clear();
        for (int i = 0; i <= 51; i++) {
            pUW.inputNameSurname.sendKeys(nameSurnameData);
            pUW.inputEmail.sendKeys(emailData);
        }
        sa.assertEquals(pUW.inputNameSurname.getAttribute("maxlength")
                , String.valueOf(pUW.inputNameSurname.getAttribute("value").length()));
        System.out.println(pUW.inputNameSurname.getAttribute("value").length());
        sa.assertEquals(pUW.inputEmail.getAttribute("maxlength")
                , String.valueOf(pUW.inputEmail.getAttribute("value").length()));
        sa.assertAll();
        driver.close();
        driver.switchTo().window(parentHandle);
    }

    @Test(dataProvider = "correctDataApplyForm",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = "enterSampleDataAndSendIt")
    public void messageAfterSendingTheForm(String nameSurnameData, String emailData, String phoneData) {
        pUW.buttonOpenNewWindow.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().frame(0);
        pUW.inputNameSurname.clear();
        pUW.inputNameSurname.sendKeys(nameSurnameData);
        pUW.inputEmail.clear();
        pUW.inputEmail.sendKeys(emailData);
        pUW.inputPhone.clear();
        pUW.inputPhone.sendKeys(phoneData);

        pUW.buttonSave.click();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        try {
            wait.until(ExpectedConditions.visibilityOf(pUW.confirmationMessage));
            Assert.assertEquals(CORRECT_INFORMATION_MESSAGE_TEXT_TASK4, pUW.confirmationMessage.getText());
        } catch (NoSuchWindowException | TimeoutException e) {
            driver.close();
            driver.switchTo().window(parentHandle);
            Assert.fail("Message didn't show up, after clicking save button.");
        }
        driver.close();
        driver.switchTo().window(parentHandle);
    }
}