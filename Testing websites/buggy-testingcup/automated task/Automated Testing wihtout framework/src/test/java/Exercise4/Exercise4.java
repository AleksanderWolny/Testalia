package Exercise4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Exercise4 {
    WebDriver driver;
    String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://buggy-testingcup.pgs-soft.com/task_4";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkIfANewWindowOpens() {
        driver.get(baseUrl);
        String iframeAddress = "https://buggy-testingcup.pgs-soft.com/task_4_frame";
        // Store the current window handle
        String parentHandle = driver.getWindowHandle();

        //button, that open new window
        WebElement buttonOpenNewWindow = driver.findElement(By.xpath("//button[text()='APLIKUJ']"));
        buttonOpenNewWindow.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        WebElement iframeSource = driver.findElement(By.xpath("//iframe"));
        Assertions.assertEquals(iframeAddress, iframeSource.getAttribute("src"));
        // Close the new window
        driver.close();

        // Switch back to original browser
        driver.switchTo().window(parentHandle);
        Assertions.assertEquals(baseUrl, driver.getCurrentUrl());
    }

    @Test
    public void enterSampleDataAndSendIt() {
        driver.get(baseUrl);
        //sample data
        String nameSurnameData = "Name Surname";
        String emailData = "a@b.pl";
        String phoneData = "600-100-200";
        // Store the current window handle
        String parentHandle = driver.getWindowHandle();

        //button, that open new window
        WebElement buttonOpenNewWindow = driver.findElement(By.xpath("//button[text()='APLIKUJ']"));
        buttonOpenNewWindow.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //switch to first iframe
        driver.switchTo().frame(0);
        //find inputs elements from new window
        WebElement inputNameSurname = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPhone = driver.findElement(By.name("phone"));
        WebElement buttonSave = driver.findElement(By.id("save-btn"));

        //fill inputs with provided data
        inputNameSurname.clear();
        inputNameSurname.sendKeys(nameSurnameData);
        inputEmail.clear();
        inputEmail.sendKeys(emailData);
        inputPhone.clear();
        inputPhone.sendKeys(phoneData);
        //assert that data is correct
        Assertions.assertAll(
                () -> Assertions.assertEquals(nameSurnameData, inputNameSurname.getAttribute("value")),
                () -> Assertions.assertEquals(emailData, inputEmail.getAttribute("value")),
                () -> Assertions.assertEquals(phoneData, inputPhone.getAttribute("value"))
        );

        buttonSave.click();
        // Close the new window
        driver.close();
        // Switch back to original browser
        driver.switchTo().window(parentHandle);
    }

    @Test
    public void enterIncorrectDataAndCheckValidation() {
        driver.get(baseUrl);
        //sample data
        String nameSurnameData = "aaa";
        String emailData = "aaa";
        String phoneData = "aaa";
        String incorrectEmailMessage = "Nieprawidłowy email";
        String incorretPhoneMessage = "Zły format telefonu - prawidłowy: 600-100-200";
        // Store the current window handle
        String parentHandle = driver.getWindowHandle();

        //button, that open new window
        WebElement buttonOpenNewWindow = driver.findElement(By.xpath("//button[text()='APLIKUJ']"));
        buttonOpenNewWindow.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //switch to first iframe
        driver.switchTo().frame(0);
        //find inputs elements from new window
        WebElement inputNameSurname = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPhone = driver.findElement(By.name("phone"));
        WebElement buttonSave = driver.findElement(By.id("save-btn"));

        //fill inputs with provided data
        inputNameSurname.clear();
        inputNameSurname.sendKeys(nameSurnameData);
        inputEmail.clear();
        inputEmail.sendKeys(emailData);
        inputPhone.clear();
        inputPhone.sendKeys(phoneData);
        //assert that data is correct
        Assertions.assertAll(
                () -> Assertions.assertEquals(nameSurnameData, inputNameSurname.getAttribute("value")),
                () -> Assertions.assertEquals(emailData, inputEmail.getAttribute("value")),
                () -> Assertions.assertEquals(phoneData, inputPhone.getAttribute("value"))
        );

        buttonSave.click();
        try {
            //span with message of incorrect value
            WebElement incorrectEmailSpan = driver.findElement(By.xpath("//span[text()='" + incorrectEmailMessage + "'"));
            WebElement incorrectPhoneSpan = driver.findElement(By.xpath("//span[text()='" + incorretPhoneMessage + "'"));
            //assert text
            Assertions.assertAll(
                    () -> Assertions.assertEquals(incorrectEmailMessage, incorrectEmailSpan.getText()),
                    () -> Assertions.assertEquals(incorretPhoneMessage, incorrectPhoneSpan.getText())
            );
        } catch (NoSuchElementException e) {
            throw new java.util.NoSuchElementException("Spans with messages, didn't show up, after clicking save button.");
        }
        // Close the new window
        driver.close();
        // Switch back to original browser
        driver.switchTo().window(parentHandle);
    }

    @Test
    public void checkMaxFiftyChars() {
        driver.get(baseUrl);
        //sample data
        String nameSurnameData = "a";
        String emailData = "a";
        // Store the current window handle
        String parentHandle = driver.getWindowHandle();

        //button, that open new window
        WebElement buttonOpenNewWindow = driver.findElement(By.xpath("//button[text()='APLIKUJ']"));
        buttonOpenNewWindow.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //switch to first iframe
        driver.switchTo().frame(0);
        //find inputs elements from new window
        WebElement inputNameSurname = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));

        //fill inputs with provided data
        inputNameSurname.clear();
        inputEmail.clear();
        for (int i = 0; i <= 51; i++) {
            inputNameSurname.sendKeys(nameSurnameData);
            inputEmail.sendKeys(emailData);
        }
        //assert that max chars in label is set to 50
        Assertions.assertAll(
                () -> Assertions.assertEquals(inputNameSurname.getAttribute("maxlength"), String.valueOf(inputNameSurname.getAttribute("value").length())),
                () -> Assertions.assertEquals(inputEmail.getAttribute("maxlength"), String.valueOf(inputEmail.getAttribute("value").length()))
        );

        // Close the new window
        driver.close();
        // Switch back to original browser
        driver.switchTo().window(parentHandle);
    }

    @Test
    public void messageAfterSendingTheForm() {
        driver.get(baseUrl);
        //sample data
        String nameSurnameData = "Name Surname";
        String emailData = "a@b.pl";
        String phoneData = "600-100-200";
        String confirmationMessage = "Wiadomość została wysłana";
        // Store the current window handle
        String parentHandle = driver.getWindowHandle();

        //button, that open new window
        WebElement buttonOpenNewWindow = driver.findElement(By.xpath("//button[text()='APLIKUJ']"));
        buttonOpenNewWindow.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //switch to first iframe
        driver.switchTo().frame(0);
        //find inputs elements from new window
        WebElement inputNameSurname = driver.findElement(By.name("name"));
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPhone = driver.findElement(By.name("phone"));
        WebElement buttonSave = driver.findElement(By.id("save-btn"));

        //fill inputs with provided data
        inputNameSurname.clear();
        inputNameSurname.sendKeys(nameSurnameData);
        inputEmail.clear();
        inputEmail.sendKeys(emailData);
        inputPhone.clear();
        inputPhone.sendKeys(phoneData);

        buttonSave.click();

        try {
            //message
            WebElement h1ConfirmationMessage = driver.findElement(By.xpath("//h1[text()='" + confirmationMessage + "']"));
            //assert correct message
            Assertions.assertEquals(confirmationMessage, h1ConfirmationMessage.getText());
        } catch (NoSuchWindowException e) {
            throw new NoSuchWindowException("Message didn't show up, after clicking save button.");
        }
        // Close the new window
        driver.close();
        // Switch back to original browser
        driver.switchTo().window(parentHandle);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}