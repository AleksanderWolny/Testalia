package allTests;

import allFinals.UrlAddressFinal;
import allMethods.TestMethods;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task1.AddToCart;
import seleniumSetup.InitialStateBeforeTest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static allFinals.TextOnPageFinal.ALERT_TEXT_TASK_1;


public class Task1Tests extends InitialStateBeforeTest {

    private AddToCart aTC;
    SoftAssert sa;
    TestMethods tM;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_1);
        aTC = new AddToCart(driver);
        sa = new SoftAssert();
        aTC.resetTask.click();
        tM = new TestMethods();
    }

    @Test
    public void checkEmptyCart() {
        String expectedSummaryQuantityValue = "0";
        String expectedSummaryPriceValue = "0.00 zł";
        sa.assertEquals(expectedSummaryQuantityValue, aTC.summaryQuantity.getText());
        sa.assertEquals(expectedSummaryPriceValue, aTC.summaryPrice.getText());
        sa.assertAll();
    }

    @Test(dependsOnMethods = "checkEmptyCart")
    public void addOneItemToCart() {
        String inputValue = "1";
        Pattern regexH4p = Pattern.compile("\\d+.\\d+");
        Matcher matcher = regexH4p.matcher(aTC.productH4P.get(0).getText());
        String firstProductH4PTagRegex = "";
        if (matcher.find()) {
            firstProductH4PTagRegex = matcher.group();
        }

        aTC.productInput.get(0).clear();
        aTC.productInput.get(0).sendKeys(inputValue);
        aTC.addButton.get(0).click();

        sa.assertEquals(aTC.productH4.get(0).getText() + " (" + firstProductH4PTagRegex + ")" + "\n" + inputValue,
                aTC.nameAndPriceOfProduct.get(0).getText());
        sa.assertEquals(inputValue, aTC.summaryQuantity.getText());
        sa.assertEquals(firstProductH4PTagRegex + " zł", aTC.summaryPrice.getText());
        sa.assertAll();
    }

    @Test(dependsOnMethods = "addOneItemToCart")
    public void removeOneItemFromCart() {
        String inputValue = "10";
        aTC.productInput.get(0).clear();
        aTC.productInput.get(0).sendKeys(inputValue);
        aTC.addButton.get(0).click();
        sa.assertEquals(aTC.summaryQuantity.getText(), inputValue);
        aTC.removeButton.get(0).click();
        sa.assertEquals(aTC.summaryQuantity.getText(), "0");
        sa.assertAll();
    }

    @Test
    public void changeValueAddToCartByArrowKey() {
        aTC.productInput.get(0).clear();
        tM.clickElementMultipleTimes(20, aTC.productInput.get(0), Keys.ARROW_UP);
        tM.clickElementMultipleTimes(10, aTC.productInput.get(0), Keys.ARROW_DOWN);
        aTC.addButton.get(0).click();
        Assert.assertEquals(aTC.summaryQuantity.getText(), "10");
    }

    @Test(dependsOnMethods = "addOneItemToCart")
    public void exceedMaxValueInCart() {
        String inputValue = "100";
        aTC.productInput.get(0).clear();
        aTC.productInput.get(0).sendKeys(inputValue);
        aTC.addButton.get(0).click();

        sa.assertEquals(aTC.summaryQuantity.getText(), inputValue);

        aTC.productInput.get(0).clear();
        aTC.productInput.get(0).sendKeys("1");
        aTC.addButton.get(0).click();
        Alert alert = driver.switchTo().alert();
        sa.assertEquals(alert.getText(), ALERT_TEXT_TASK_1);
        sa.assertAll();
        alert.accept();
    }

    @Test(dependsOnMethods = "addOneItemToCart")
    public void addOneItemFromAllProducts() {
        String inputValue = "1";
        String firstProductH4PTagRegex = "";
        double doubleSummaryPrice = 0;
        double sumPrice = 0;
        //regex to extract price from p tag
        Pattern regexH4p = Pattern.compile("\\d+.[0-9][0-9]");

        for (int i = 0; i < aTC.productInput.size(); i++) {
            aTC.productInput.get(i).clear();
            aTC.productInput.get(i).sendKeys(inputValue);
            aTC.addButton.get(i).click();
            Matcher matcher = regexH4p.matcher(aTC.productH4P.get(i).getText());
            if (matcher.find()) {
                firstProductH4PTagRegex = matcher.group();
                doubleSummaryPrice = Double.parseDouble(firstProductH4PTagRegex);
                sumPrice += doubleSummaryPrice;
            }
        }
        sa.assertEquals(aTC.summaryQuantity.getText(), Integer.toString(aTC.productInput.size()));
        sa.assertEquals(aTC.summaryPrice.getText(), sumPrice + " zł");
        sa.assertAll();
    }

    @Test(dependsOnMethods = "addOneItemFromAllProducts")
    public void deleteOneItemFromAllProducts() {
        String inputValue = "1";
        for (int i = 0; i < aTC.productInput.size(); i++) {
            aTC.productInput.get(i).clear();
            aTC.productInput.get(i).sendKeys(inputValue);
            aTC.addButton.get(i).click();
        }
        sa.assertEquals(aTC.summaryQuantity.getText(), Integer.toString(aTC.productInput.size()));
        for (WebElement deleteList : aTC.removeButton) {
            deleteList.click();
        }
        sa.assertEquals(aTC.summaryQuantity.getText(), "0");
        sa.assertAll();
    }
}