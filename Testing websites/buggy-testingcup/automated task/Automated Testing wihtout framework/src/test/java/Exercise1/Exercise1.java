package Exercise1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise1 {
    WebDriver driver;
    String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://buggy-testingcup.pgs-soft.com/task_1";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkEmptyCart() {
        driver.get(baseUrl);
        String expectedSummaryQuantityValue = "0";
        String expectedSummaryPriceValue = "0.00 zł";
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));
        WebElement summaryPrice = driver.findElement(By.xpath("//span[@class='summary-price']"));

        Assertions.assertAll(
                //assert quantity of cart
                () -> Assertions.assertEquals(expectedSummaryQuantityValue, summaryQuantity.getText()),
                //assert summary price of cart
                () -> Assertions.assertEquals(expectedSummaryPriceValue, summaryPrice.getText())
        );
    }

    @Test
    public void addOneItemToCart() {
        driver.get(baseUrl);
        String inputValue = "1";
        //first product WebElement
        //h4
        WebElement firstProductH4 = driver.findElement(By.xpath("//div[@class='caption']/h4"));
        String firstProductH4text = firstProductH4.getText();
        //h4:p
        WebElement firstProductH4P = driver.findElement(By.xpath("//div[@class='caption']/h4/following-sibling::p"));
        //regex to extract price from p tag
        Pattern regexH4p = Pattern.compile("\\d+.\\d+");
        Matcher matcher = regexH4p.matcher(firstProductH4P.getText());
        String firstProductH4PTagRegex = "";
        if (matcher.find()) {
            firstProductH4PTagRegex = matcher.group();
        }
        //input
        WebElement firstProductInput = driver.findElement(By.xpath("//input[@type='number']"));
        firstProductInput.clear();
        firstProductInput.sendKeys(inputValue);
        //button
        driver.findElement(By.xpath("//button[@role='button']")).click();

        //cart WebElement
        //description with name and price of product
        WebElement nameAndPriceOfProduct = driver.findElement(By.xpath("//div[@class='col-md-9 text-on-button-level']"));
        //quantity of one product
        WebElement quantityOfProduct = driver.findElement(By.xpath("//div[@class='col-md-9 text-on-button-level']/span"));
        //summary quantity
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));
        //summary price
        WebElement summaryPrice = driver.findElement(By.xpath("//span[@class='summary-price']"));

        String finalFirstProductH4PTagRegex = firstProductH4PTagRegex;
        Assertions.assertAll(
                //assert if name, value and quantity is correct, example: Okulary (15.54)
                () -> Assertions.assertEquals(
                        firstProductH4text + " (" + finalFirstProductH4PTagRegex + ")" + "\n" + inputValue
                        , nameAndPriceOfProduct.getText()),
                //assert quantity of value
                () -> Assertions.assertEquals(inputValue, summaryQuantity.getText()),
                //assert summary price of cart
                () -> Assertions.assertEquals(finalFirstProductH4PTagRegex + " zł", summaryPrice.getText())
        );
    }

    @Test
    public void removeOneItemFromCart() {
        driver.get(baseUrl);
        String inputValue = "10";
        //h4
        WebElement firstProductH4 = driver.findElement(By.xpath("//div[@class='caption']/h4"));
        //input
        WebElement firstProductInput = driver.findElement(By.xpath("//input[@type='number']"));
        firstProductInput.clear();
        firstProductInput.sendKeys(inputValue);
        //button add to cart
        driver.findElement(By.xpath("//button[@role='button']")).click();
        //button remove from cart
        WebElement removeOneItemFromCart = driver.findElement(By.xpath(
                "//button[contains(@data-product-name,'" + firstProductH4.getText() + "') and contains(text(),'Usuń')]"));

        //summary quantity
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));
        //assert if product was added to cart
        Assertions.assertEquals(summaryQuantity.getText(), inputValue);
        removeOneItemFromCart.click();
        //assert again, to verify that cart is empty
        Assertions.assertEquals(summaryQuantity.getText(), "0");

    }

    @Test
    public void changeValueAddToCartByArrowKey() {
        driver.get(baseUrl);
        //input
        WebElement firstProductInput = driver.findElement(By.xpath("//input[@type='number']"));
        firstProductInput.clear();
        //click up arrow key 20 times
        for (int i = 0; i < 20; i++) {
            firstProductInput.sendKeys(Keys.ARROW_UP);
        }
        //click down arrow key 10 times
        for (int i = 0; i < 10; i++) {
            firstProductInput.sendKeys(Keys.ARROW_DOWN);
        }
        //button add to cart
        driver.findElement(By.xpath("//button[@role='button']")).click();
    }

    @Test
    public void exceedMaxValueInCart() {
        driver.get(baseUrl);
        String inputValue = "100";
        String alertText = "Łączna ilość produktów w koszyku nie może przekroczyć 100.";
        //input
        WebElement firstProductInput = driver.findElement(By.xpath("//input[@type='number']"));
        firstProductInput.clear();
        //add 100 products to cart
        firstProductInput.sendKeys(inputValue);
        //button add to cart
        driver.findElement(By.xpath("//button[@role='button']")).click();
        //summary quantity
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));

        //validate it contains 100 products
        Assertions.assertEquals(summaryQuantity.getText(), inputValue);

        //add 1 product to cart
        firstProductInput.clear();
        firstProductInput.sendKeys("1");
        driver.findElement(By.xpath("//button[@role='button']")).click();
        //switch to alert
        Alert alert = driver.switchTo().alert();
        //asert text of alert
        Assertions.assertEquals(alert.getText(), alertText);
        //close alert by accepting
        alert.accept();

    }

    @Test
    public void addOneItemFromAllProducts() {
        driver.get(baseUrl);
        String inputValue = "1";
        String firstProductH4PTagRegex = "";
        double doubleSummaryPrice = 0;
        double sumPrice = 0;
        //regex to extract price from p tag
        Pattern regexH4p = Pattern.compile("\\d+.[0-9][0-9]");
        //input
        List<WebElement> productsInput = driver.findElements(By.xpath("//input[@type='number']"));
        //button
        List<WebElement> productsButton = driver.findElements(By.xpath("//button[@data-add-to-basket='']"));
        //h4:p
        List<WebElement> firstProductH4P = driver.findElements(By.xpath("//div[@class='caption']//p[1]"));

        for (int i = 0; i < productsInput.size(); i++) {
            productsInput.get(i).clear();
            productsInput.get(i).sendKeys(inputValue);
            productsButton.get(i).click();
            Matcher matcher = regexH4p.matcher(firstProductH4P.get(i).getText());
            if (matcher.find()) {
                firstProductH4PTagRegex = matcher.group();
                doubleSummaryPrice = Double.parseDouble(firstProductH4PTagRegex);
            }
            sumPrice += doubleSummaryPrice;
        }
        //summary quantity
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));
        //summary price
        WebElement summaryPrice = driver.findElement(By.xpath("//span[@class='summary-price']"));
        double finalSumPrice = sumPrice;
        Assertions.assertAll(
                //assert if all products were added to cart
                () -> Assertions.assertEquals(summaryQuantity.getText(), Integer.toString(productsInput.size())),
                //assert summary price
                () -> Assertions.assertEquals(summaryPrice.getText(), finalSumPrice + " zł")
        );
    }

    @Test
    public void deleteOneItemFromAllProducts() {

        driver.get(baseUrl);
        String inputValue = "1";
        //input
        List<WebElement> productsInput = driver.findElements(By.xpath("//input[@type='number']"));
        //button add
        List<WebElement> productsButton = driver.findElements(By.xpath("//button[@data-add-to-basket='']"));
        for (int i = 0; i < productsInput.size(); i++) {
            productsInput.get(i).clear();
            productsInput.get(i).sendKeys(inputValue);
            productsButton.get(i).click();
        }

        //summary quantity
        WebElement summaryQuantity = driver.findElement(By.xpath("//span[@class='summary-quantity']"));
        //assert if all products were added to cart
        Assertions.assertEquals(summaryQuantity.getText(), Integer.toString(productsInput.size()));

        //button delete
        List<WebElement> deleteButton = driver.findElements(By.xpath("//button[@data-remove-from-basket='']"));
        for (WebElement deleteList : deleteButton) {
            deleteList.click();
        }
        //assert if all products were deleted from cart
        Assertions.assertEquals(summaryQuantity.getText(), "0");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}