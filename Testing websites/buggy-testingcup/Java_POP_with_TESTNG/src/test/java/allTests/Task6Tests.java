package allTests;

import allFinals.TextOnPageFinal;
import allFinals.UrlAddressFinal;
import allMethods.TestDataProvider;
import allMethods.TestMethods;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task6.LoggedPage;
import pageDeclaration.task6.LoginFormPage;
import seleniumSetup.InitialStateBeforeTest;

import java.io.File;

import static allFinals.DownloadableFilesFinal.DOWNLOAD_FILE_TASK_6;


public class Task6Tests extends InitialStateBeforeTest {
    private TestMethods tM;
    private LoginFormPage lFP;
    private LoggedPage lP;
    SoftAssert sA = new SoftAssert();

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_6);
        tM = new TestMethods();
        lFP = new LoginFormPage(driver);
        lP = new LoggedPage(driver);
    }

    @AfterSuite
    public void deleteFilesAfterTests() {
        tM.deleteFiles("Downloads/", DOWNLOAD_FILE_TASK_6);
    }

    @Test(dataProvider = "correctLoginData",
            dataProviderClass = TestDataProvider.class)
    public void loginIntoAppCorrectData(String loginVariable, String passwordVariable) {
        lFP.logIntoApp(loginVariable, passwordVariable);

        Assert.assertEquals(driver.getCurrentUrl(), UrlAddressFinal.URL_TASK_6_SUCCESS_LOGGED);

    }

    @Test(dataProvider = "incorrectLoginData", dataProviderClass = TestDataProvider.class)
    public void loginIntoAppIncorrectData(String loginVariable, String passwordVariable) {
        lFP.logIntoApp(loginVariable, passwordVariable);

        sA.assertTrue(lFP.incorrectDataAlert.isDisplayed());
        sA.assertEquals(lFP.incorrectDataAlert.getText(), TextOnPageFinal.ALERT_TEXT_TASK_6);
        sA.assertAll();
    }

    @Test(dataProvider = "correctLoginData", dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"loginIntoAppCorrectData"}, priority = 1)
    public void logOutButton(String loginVariable, String passwordVariable) {
        lFP.logIntoApp(loginVariable, passwordVariable);

        sA.assertTrue(lP.logoutButton.isDisplayed());

        lP.logoutButton.click();
        sA.assertEquals(driver.getCurrentUrl(), UrlAddressFinal.URL_TASK_6);
        sA.assertAll();
    }

    @Test(dataProvider = "correctLoginData",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"loginIntoAppCorrectData"}, priority = 2)
    public void downloadFile(String loginVariable, String passwordVariable) {
        lFP.logIntoApp(loginVariable, passwordVariable);

        sA.assertTrue(lP.downloadButton.isDisplayed());

        lP.downloadButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        TestMethods tM = new TestMethods();
        File file = tM.fileInFolder("Downloads/", DOWNLOAD_FILE_TASK_6);
        try {
            wait.until((WebDriver driver) -> file.exists());
        } catch (TimeoutException e) {
            sA.fail("Failed to download expected file.");
        }
        sA.assertNotNull(file);
        sA.assertAll();
    }
}
