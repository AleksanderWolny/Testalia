package Exercise5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Exercise5 {
    WebDriver driver;
    String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://buggy-testingcup.pgs-soft.com/task_5";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void importCorrectTextFile() throws FileNotFoundException {
        driver.get(baseUrl);
        File correctData = new File("src/test/java/Exercise5/testDataFiles/correctDataFile.txt");
        Scanner stringCorrectData = new Scanner(correctData);
        //button 'Browse'
        WebElement buttonBrowse = driver.findElement(By.xpath("//input[@type='file']"));
        buttonBrowse.sendKeys(correctData.getAbsolutePath());
        //data from table, after import file
        List<WebElement> tableTDs = driver.findElements(By.xpath("//table/tbody//td"));
        List<String> dateFromTable = new ArrayList<>();
        for (WebElement tdList : tableTDs) {
            dateFromTable.add(tdList.getText());
        }
        List<String> dateFromString = new ArrayList<>();
        while (stringCorrectData.hasNextLine()) {
            String data = stringCorrectData.nextLine();
            dateFromString.add(data);
        }
        Assertions.assertEquals(dateFromString.toString().replaceAll("\\s+", ""), dateFromTable.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void importEmptyTextFile() {
        driver.get(baseUrl);
        String alertMessage = "Plik jest pusty, lub źle sformatowany";
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File correctData = new File("src/test/java/Exercise5/testDataFiles/emptyDataFile.txt");
        //button 'Browse'
        WebElement buttonBrowse = driver.findElement(By.xpath("//input[@type='file']"));
        buttonBrowse.sendKeys(correctData.getAbsolutePath());
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals(alertMessage, alert.getText());
        alert.accept();
    }

    @Test
    public void importIncorrectTextFile() {
        driver.get(baseUrl);
        String alertMessage = "Źle sformatowany plik.";
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File correctData = new File("src/test/java/Exercise5/testDataFiles/wrongDataFile.txt");
        //button 'Browse'
        WebElement buttonBrowse = driver.findElement(By.xpath("//input[@type='file']"));
        buttonBrowse.sendKeys(correctData.getAbsolutePath());
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals(alertMessage, alert.getText());
        alert.accept();
    }

    @Test
    public void importExceededCSVFile() {
        driver.get(baseUrl);
        String alertMessage = "Maksymalnie wprowadzić można 20 wierszy.";
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File correctData = new File("src/test/java/Exercise5/testDataFiles/21rowsDataFile.csv");
        //button 'Browse'
        WebElement buttonBrowse = driver.findElement(By.xpath("//input[@type='file']"));
        buttonBrowse.sendKeys(correctData.getAbsolutePath());
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals(alertMessage, alert.getText());
        alert.accept();
    }

    @Test
    public void importWrongNumberFormatTextFiles() {
        driver.get(baseUrl);
        String alertMessage = "Numer telefonu może zawierać tylko znaki numeryczne, musi mieć 9 znaków.";
        WebDriverWait wait = new WebDriverWait(driver, 3);
        File pathToData = new File("src/test/java/Exercise5/testDataFiles/wrongNumberDataFile");
        String[] pathString;
        pathString = pathToData.list();
        //button 'Browse'
        WebElement buttonBrowse = driver.findElement(By.xpath("//input[@type='file']"));
        assert pathString != null;
        for (String pathNames : pathString) {
            buttonBrowse.sendKeys(pathToData.getAbsolutePath() + "/" + pathNames);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            Assertions.assertEquals(alertMessage, alert.getText());
            alert.accept();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}