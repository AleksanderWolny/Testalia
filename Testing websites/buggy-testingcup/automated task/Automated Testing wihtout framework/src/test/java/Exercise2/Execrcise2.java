package Exercise2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Execrcise2 {
    WebDriver driver;
    String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://buggy-testingcup.pgs-soft.com/task_2";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void chooseRandomCategoryAndValidateProducts() {
        driver.get(baseUrl);
        //list of all category in select
        List<WebElement> selectOptionList = driver.findElements(By.xpath("//select/option"));
        //random category
        Random random = new Random();
        //random number from 1 to size of list
        int randomCategoryNumber = random.ints(1, selectOptionList.size()).findFirst().getAsInt();
        String nameOfRandomSelectedCategory = selectOptionList.get(randomCategoryNumber).getText();
        //click span element to show list of li
        driver.findElement(By.xpath("//span[@dir='ltr']")).click();
        //click chosen li element
        driver.findElement(By.xpath("//li[text()='" + nameOfRandomSelectedCategory + "']")).click();

        //list of all filtered product
        List<WebElement> filteredProductList = driver.findElements(By.xpath("//p/strong"));
        //assertion, checking that all filtered product are in same category
        for (WebElement filteredList : filteredProductList) {
            Assertions.assertEquals(nameOfRandomSelectedCategory, filteredList.getText());
        }
    }

    @Test
    public void chooseCategoryByAPartialWord() {
        driver.get(baseUrl);
        //partial word to test, 't' can match with 'Sport' and 'Elektronika'
        String partialTestWord = "t";
        //click span element to show list of li
        driver.findElement(By.xpath("//span[@dir='ltr']")).click();
        //finding and sending 'por' to input
        driver.findElement(By.xpath("//input")).sendKeys(partialTestWord);
        //get list of all category li
        List<WebElement> listOfAllMatchedCategory = driver.findElements(By.xpath("//li[@role='treeitem']"));
        for (WebElement listLi : listOfAllMatchedCategory) {
            Assertions.assertTrue(listLi.getText().contains(partialTestWord));
        }
    }

    @Test
    public void mappingPolishWord() {
        driver.get("https://buggy-testingcup.pgs-soft.com/task_2");
        //word 'uslugi' shoud find category 'usługi'
        String partialTestWord = "uslugi";
        //click span element to show list of li
        driver.findElement(By.xpath("//span[@dir='ltr']")).click();
        //finding and sending 'uslugi' to input
        driver.findElement(By.xpath("//input")).sendKeys(partialTestWord);
        //get list of all category li
        List<WebElement> listOfAllMatchedCategory = driver.findElements(By.xpath("//li[@role='treeitem']"));
        List<String> listToCompare = new ArrayList<>();
        for (WebElement listLi : listOfAllMatchedCategory) {
            String valueWithoutPolishChar = changeChar.changeToAscii(listLi.getText());
            if (valueWithoutPolishChar.contains(partialTestWord))
                listToCompare.add(valueWithoutPolishChar);
        }

        Assertions.assertEquals("[Firma i uslugi]", listToCompare.toString());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
