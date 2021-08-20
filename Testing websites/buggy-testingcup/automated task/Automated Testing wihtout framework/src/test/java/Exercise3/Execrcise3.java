package Exercise3;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Execrcise3 {
    WebDriver driver;
    String baseUrl;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://buggy-testingcup.pgs-soft.com/task_3";
        //init js
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void showDropDownMenuWithMouseHover() {
        driver.get(baseUrl);
        //init action class
        Actions actions = new Actions(driver);
        //button 'Menu'
        WebElement menuButton = driver.findElement(By.xpath("//a[text()='Menu']"));
        //button 'Formularz'
        WebElement formButton = driver.findElement(By.xpath("//a[text()='Formularz ']"));
        //button 'Przejdz do trybu edycji'
        WebElement startEditButton = driver.findElement(By.id("start-edit"));
        //button 'Zapisz do pliku'
        WebElement saveToFileButton = driver.findElement(By.xpath("//a[contains(text(),'Zapisz')]"));

        Assertions.assertAll(
                //check if its not visible, before hover mouse
                () -> Assertions.assertFalse(formButton.isDisplayed()),
                () -> Assertions.assertFalse(startEditButton.isDisplayed()),
                () -> Assertions.assertFalse(saveToFileButton.isDisplayed()),
                //move mouse to menu
                () -> actions.moveToElement(menuButton).perform(),
                () -> Assertions.assertTrue(formButton.isDisplayed()),
                //move to 'Formularz'
                () -> actions.moveToElement(formButton).perform(),
                () -> Assertions.assertTrue(startEditButton.isDisplayed()),
                () -> Assertions.assertTrue(saveToFileButton.isDisplayed())
        );
    }

    @Test
    public void enableFormAfterButtonClick() {
        driver.get(baseUrl);
        //init action class
        Actions actions = new Actions(driver);
        //button 'Menu', 'Formularz', 'Przejdz do trybu deycji'
        WebElement menuButton = driver.findElement(By.xpath("//a[text()='Menu']"));
        WebElement formButton = driver.findElement(By.xpath("//a[text()='Formularz ']"));
        WebElement startEditButton = driver.findElement(By.id("start-edit"));
        //fields of form
        WebElement nameLabel = driver.findElement(By.id("in-name"));
        WebElement surnameLabel = driver.findElement(By.id("in-surname"));
        WebElement notesLabel = driver.findElement(By.id("in-notes"));
        WebElement phoneLabel = driver.findElement(By.id("in-phone"));
        WebElement chooseFileButton = driver.findElement(By.id("in-file"));
        WebElement saveFileButton = driver.findElement(By.id("save-btn"));

        //check if all fields are in default blocked from editing
        Assertions.assertAll(
                () -> Assertions.assertFalse(nameLabel.isEnabled()),
                () -> Assertions.assertFalse(surnameLabel.isEnabled()),
                () -> Assertions.assertFalse(notesLabel.isEnabled()),
                () -> Assertions.assertFalse(phoneLabel.isEnabled()),
                () -> Assertions.assertFalse(chooseFileButton.isEnabled()),
                () -> Assertions.assertFalse(saveFileButton.isDisplayed()),
                //move mouse to menu, form, edit form and click edit form
                () -> actions.moveToElement(menuButton).perform(),
                () -> actions.moveToElement(formButton).perform(),
                () -> actions.moveToElement(startEditButton).perform()
        );
        startEditButton.click();

        //check if all fields are enable, after clicking edit form button
        Assertions.assertAll(
                () -> Assertions.assertTrue(nameLabel.isEnabled()),
                () -> Assertions.assertTrue(surnameLabel.isEnabled()),
                () -> Assertions.assertTrue(notesLabel.isEnabled()),
                () -> Assertions.assertTrue(phoneLabel.isEnabled()),
                () -> Assertions.assertTrue(chooseFileButton.isEnabled()),
                () -> Assertions.assertTrue(saveFileButton.isDisplayed())
        );
    }

    @Test
    public void fillFormWithNewContent() {
        driver.get(baseUrl);
        String myName = "testName";
        String mySurname = "testSurname";
        String myNotes = "Testing notes, a bit longer. Testing notes, a bit longer. Testing notes, a bit longer. Testing notes, a bit longer.";
        String myPhone = "123456789";

        //init action class
        Actions actions = new Actions(driver);
        //button 'Menu', 'Formularz', 'Przejdz do trybu deycji'
        WebElement menuButton = driver.findElement(By.xpath("//a[text()='Menu']"));
        WebElement formButton = driver.findElement(By.xpath("//a[text()='Formularz ']"));
        WebElement startEditButton = driver.findElement(By.id("start-edit"));
        //fields of form
        WebElement nameLabel = driver.findElement(By.id("in-name"));
        WebElement surnameLabel = driver.findElement(By.id("in-surname"));
        WebElement notesLabel = driver.findElement(By.id("in-notes"));
        WebElement phoneLabel = driver.findElement(By.id("in-phone"));
        WebElement saveFileButton = driver.findElement(By.id("save-btn"));

        //move mouse to menu, form, edit form and click edit form
        actions.moveToElement(menuButton).perform();
        actions.moveToElement(formButton).perform();
        actions.moveToElement(startEditButton).perform();
        startEditButton.click();

        //check if all fields are enable, after clicking edit form button
        nameLabel.clear();
        nameLabel.sendKeys(myName);
        surnameLabel.clear();
        surnameLabel.sendKeys(mySurname);
        notesLabel.clear();
        notesLabel.sendKeys(myNotes);
        phoneLabel.clear();
        phoneLabel.sendKeys(myPhone);
        //click save form button
        saveFileButton.click();

        //check if all fields have provided data
        Assertions.assertAll(
                () -> Assertions.assertEquals(myName, js.executeScript("return arguments[0].value", nameLabel)),
                () -> Assertions.assertEquals(mySurname, js.executeScript("return arguments[0].value", surnameLabel)),
                () -> Assertions.assertEquals(myNotes, js.executeScript("return arguments[0].value", notesLabel)),
                () -> Assertions.assertEquals(myPhone, js.executeScript("return arguments[0].value", phoneLabel))
        );
    }

    @Test
    public void uploadPhotoFromHardDrive() throws IOException {
        driver.get(baseUrl);
        File myPhoto = new File("src/test/java/Exercise3/aw.png");
        byte[] myPhotoChangeToByte = FileUtils.readFileToByteArray(myPhoto);
        String encodedString = Base64.getEncoder().encodeToString(myPhotoChangeToByte);
        //init action class
        Actions actions = new Actions(driver);
        //button 'Menu', 'Formularz', 'Przejdz do trybu deycji'
        WebElement menuButton = driver.findElement(By.xpath("//a[text()='Menu']"));
        WebElement formButton = driver.findElement(By.xpath("//a[text()='Formularz ']"));
        WebElement startEditButton = driver.findElement(By.id("start-edit"));
        WebElement photoPreview = driver.findElement(By.xpath("//img[@class='preview-photo']"));
        //fields of form
        WebElement chooseFileButton = driver.findElement(By.id("in-file"));
        WebElement saveFileButton = driver.findElement(By.id("save-btn"));

        //move mouse to menu, form, edit form and click edit form
        actions.moveToElement(menuButton).perform();
        actions.moveToElement(formButton).perform();
        actions.moveToElement(startEditButton).perform();
        startEditButton.click();

        //click on 'Wybierz plik' and choose photo from hard drive
        chooseFileButton.sendKeys(myPhoto.getAbsolutePath());
        saveFileButton.click();
        Assertions.assertEquals("data:image/png;base64," + encodedString, js.executeScript("return arguments[0].src", photoPreview));
    }

    @Test
    public void visibilityOfSuccessSaveMessage() {
        driver.get(baseUrl);
        String messageSpanText = "Twoje dane zostały poprawnie zapisane";
        //init action class
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 3);

        //button 'Menu', 'Formularz', 'Przejdz do trybu deycji'
        WebElement menuButton = driver.findElement(By.xpath("//a[text()='Menu']"));
        WebElement formButton = driver.findElement(By.xpath("//a[text()='Formularz ']"));
        WebElement startEditButton = driver.findElement(By.id("start-edit"));
        //fields of form
        WebElement saveFileButton = driver.findElement(By.id("save-btn"));

        //move mouse to menu, form, edit form and click edit form
        actions.moveToElement(menuButton).perform();
        actions.moveToElement(formButton).perform();
        actions.moveToElement(startEditButton).perform();
        startEditButton.click();

        saveFileButton.click();
        try {
            //message span
            WebElement messageSpan = driver.findElement(By.xpath("//span[@data-notify-text='']"));
            //wait for element to appear
            wait.until(ExpectedConditions.visibilityOf(messageSpan));
            Assertions.assertAll(
                    () -> Assertions.assertTrue(messageSpan.isDisplayed()),
                    () -> Assertions.assertEquals(messageSpanText, messageSpan.getText())
            );
        } catch (NoSuchElementException e) {
            throw new java.util.NoSuchElementException("Span with message, didn't show up, after clicking save button.");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
