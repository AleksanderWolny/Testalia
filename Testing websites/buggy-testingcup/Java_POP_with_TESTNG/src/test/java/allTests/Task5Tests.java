package allTests;

import allMethods.TestDataProvider;
import allMethods.TestMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task4.PopUpWindow;
import pageDeclaration.task5.FileUpload;
import seleniumSetup.InitialStateBeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static allFinals.DataFilesTask5.*;
import static allFinals.TextOnPageFinal.*;
import static allFinals.UrlAddressFinal.*;


public class Task5Tests extends InitialStateBeforeTest {

    private FileUpload fU;
    SoftAssert sa;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(URL_TASK_5);
        fU = new FileUpload(driver);
        sa = new SoftAssert();
    }

    @Test
    public void uploadCorrectTextFile() throws FileNotFoundException {
        File correctData = new File(CORRECT_DATA_FILE);
        Scanner stringCorrectData = new Scanner(correctData);
        fU.buttonBrowse.sendKeys(correctData.getAbsolutePath());
        List<String> dateFromTable = new ArrayList<>();
        for (WebElement tdList : fU.tableTDs) {
            dateFromTable.add(tdList.getText());
        }
        List<String> dateFromFile = new ArrayList<>();
        while (stringCorrectData.hasNextLine()) {
            String data = stringCorrectData.nextLine();
            dateFromFile.add(data);
        }
        Assert.assertEquals(dateFromFile.toString().replaceAll("\\s+", ""), dateFromTable.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void uploadEmptyTextFile() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File emptyDataFile = new File(EMPTY_DATA_FILE);
        fU.buttonBrowse.sendKeys(emptyDataFile.getAbsolutePath());
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(FILE_IS_EMPTY_TEXT_TASK5, alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Alert didn't show up.");
        }
    }

    @Test
    public void uploadIncorrectTextFile() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File incorrectData = new File(INCORRECT_DATA_FILE);
        fU.buttonBrowse.sendKeys(incorrectData.getAbsolutePath());
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(INCORRECT_DATA_FILE_TEXT_TASK5, alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Alert didn't show up.");
        }
    }

    @Test
    public void uploadExceededCSVFile() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File correctData = new File(SIZE_LIMIT_DATA_FILE);
        fU.buttonBrowse.sendKeys(correctData.getAbsolutePath());
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(SIZE_LIMIT_DATA_FILE_TEXT_TASK5, alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Alert didn't show up.");
        }
    }

    @Test(dataProvider = "wrongNumberDataFile",
            dataProviderClass = TestDataProvider.class)
    public void uploadWrongNumberFormatTextFiles(String pathToData) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        fU.buttonBrowse.sendKeys(pathToData);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(WRONG_NUMBER_DATA_FILE_TEXT_TASK5, alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Alert didn't show up.");
        }
    }
}