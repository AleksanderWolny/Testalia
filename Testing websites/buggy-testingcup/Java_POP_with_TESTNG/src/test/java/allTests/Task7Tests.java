package allTests;

import allFinals.UrlAddressFinal;
import allMethods.TestDataProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageDeclaration.task7.DragAndDrop;
import seleniumSetup.InitialStateBeforeTest;

import static allFinals.TextOnPageFinal.ALERT_TEXT_TASK_7;


public class Task7Tests extends InitialStateBeforeTest {

    private DragAndDrop dAD;
    Actions actions;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_7);
        dAD = new DragAndDrop(driver);
        actions = new Actions(driver);
    }

    @Test
    public void inputProductIsEnable() {
        Assert.assertTrue(dAD.firstProductInput.get(0).isEnabled());
    }

    @Test(dataProvider = "amountOfProducts",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"inputProductIsEnable"})
    public void addProductWithButton(String numberOfProducts) {
        dAD.firstProductInput.get(0).clear();
        dAD.firstProductInput.get(0).sendKeys(numberOfProducts);
        dAD.addToCartButton.get(0).click();
        Assert.assertEquals(numberOfProducts, dAD.summaryQuantity.getText());
    }

    @Test(dataProvider = "amountOfAllProducts",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"addProductWithButton"})
    public void addAllProductWithButton(String numberOfProducts) {
        for (int i = 0; i < dAD.firstProductInput.size(); i++) {
            dAD.firstProductInput.get(i).clear();
            dAD.firstProductInput.get(i).sendKeys(numberOfProducts);
            dAD.addToCartButton.get(i).click();
        }
        int changeStringToInt = Integer.parseInt(numberOfProducts);
        int multiplySizeOfList = changeStringToInt * dAD.firstProductInput.size();
        Assert.assertEquals(
                Integer.toString(multiplySizeOfList),
                dAD.summaryQuantity.getText());
    }

    @Test(dataProvider = "exceededAmountOfProduct",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"inputProductIsEnable"})
    public void exceededAmountOfProductWithButton(String numberOfProducts) {
        dAD.firstProductInput.get(0).clear();
        dAD.firstProductInput.get(0).sendKeys(numberOfProducts);
        dAD.addToCartButton.get(0).click();
        Assert.assertEquals(ALERT_TEXT_TASK_7, driver.switchTo().alert().getText());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(dataProvider = "amountOfProductsDragAndDrop",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"inputProductIsEnable"})
    public void addProductWithDragAndDrop(String numberOfProducts) {
        dAD.firstProductInput.get(0).clear();
        dAD.firstProductInput.get(0).sendKeys(numberOfProducts);
        actions.clickAndHold(dAD.draggableElement.get(0))
                .moveByOffset(1, 1)
                .moveToElement(dAD.droppableElement)
                .release()
                .perform();
        Assert.assertEquals(numberOfProducts, dAD.summaryQuantity.getText());
    }

    @Test(dataProvider = "amountOfProductsDragAndDrop",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"addProductWithDragAndDrop"})
    public void addAllProductWithDragAndDrop(String numberOfProducts) {
        for (int i = 0; i < dAD.firstProductInput.size(); i++) {
            dAD.firstProductInput.get(i).clear();
            dAD.firstProductInput.get(i).sendKeys(numberOfProducts);
            actions.clickAndHold(dAD.draggableElement.get(i))
                    .moveByOffset(1, 1)
                    .moveToElement(dAD.droppableElement)
                    .release()
                    .perform();
        }
        int changeStringToInt = Integer.parseInt(numberOfProducts);
        int multiplySizeOfList = changeStringToInt * dAD.firstProductInput.size();
        Assert.assertEquals(
                Integer.toString(multiplySizeOfList),
                dAD.summaryQuantity.getText());
    }

    @Test(dataProvider = "exceededAmountOfProduct",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"inputProductIsEnable"})
    public void exceededAmountOfProductWithDragAndDrop(String numberOfProducts) {
        dAD.firstProductInput.get(0).clear();
        dAD.firstProductInput.get(0).sendKeys(numberOfProducts);
        actions.clickAndHold(dAD.draggableElement.get(0))
                .moveByOffset(1, 1)
                .moveToElement(dAD.droppableElement)
                .release()
                .perform();
        Assert.assertEquals(ALERT_TEXT_TASK_7, driver.switchTo().alert().getText());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}