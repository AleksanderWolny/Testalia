package allMethods;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import static allFinals.DataFilesTask5.ALL_FILE_IN_FOLDER_LIST;
import static allFinals.DataFilesTask5.WRONG_NUMBER_DATA_FILE;

public class TestDataProvider {
    @DataProvider(name = "fillFormCorrectData")
    public static Iterator<Object[]> fillFormCorrectData() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task3/TestDataTask3/FillFormCorrectData.csv");
    }

    @DataProvider(name = "correctDataApplyForm")
    public static Iterator<Object[]> correctDataApplyForm() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task4/TestDataTask4/correctDataApplyForm.csv");
    }

    @DataProvider(name = "incorrectDataApplyForm")
    public static Iterator<Object[]> incorrectDataApplyForm() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task4/TestDataTask4/incorrectDataApplyForm.csv");
    }

    @DataProvider(name = "wrongNumberDataFile")
    public static Iterator<Object[]> wrongNumberDataFile() {
        TestMethods tM = new TestMethods();
        tM.cycleThroughFolderFiles(WRONG_NUMBER_DATA_FILE, ALL_FILE_IN_FOLDER_LIST);
        return tM.parseCsvData(ALL_FILE_IN_FOLDER_LIST);
    }

    @DataProvider(name = "correctLoginData")
    public static Iterator<Object[]> correctLoginData() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task6/TestDataTask6/CorrectLoginData.csv");
    }

    @DataProvider(name = "incorrectLoginData")
    public static Iterator<Object[]> incorrectLoginData() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task6/TestDataTask6/InCorrectLoginData.csv");
    }

    @DataProvider(name = "amountOfProducts")
    public static Iterator<Object[]> amountOfProducts() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task7/TestDataTask7/AmountOfProducts.csv");
    }

    @DataProvider(name = "amountOfAllProducts")
    public static Iterator<Object[]> amountOfAllProducts() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task7/TestDataTask7/amountOfAllProducts.csv");
    }

    @DataProvider(name = "amountOfProductsDragAndDrop")
    public static Iterator<Object[]> amountOfProductsDragAndDrop() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task7/TestDataTask7/AmountOfProductsDragAndDrop.csv");
    }

    @DataProvider(name = "exceededAmountOfProduct")
    public static Iterator<Object[]> exceededAmountOfProduct() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task7/TestDataTask7/exceededAmountOfProduct.csv");
    }

    @DataProvider(name = "cvvIncorrectFormatTest")
    public static Iterator<Object[]> cvvIncorrectFormatTest() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task8/TestDataTask8/CvvIncorrectFormatTest.csv");
    }
    @DataProvider(name = "expiryDateCardAlertTest")
    public static Iterator<Object[]> expiryDateCardAlertTest() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task8/TestDataTask8/ExpiryDateCardAlertTest.csv");
    }

    @DataProvider(name = "orderPaidPositiveTest")
    public static Iterator<Object[]> orderPaidPositiveTest() {
        TestMethods tM = new TestMethods();
        return tM.parseCsvData("src/main/resources/task8/TestDataTask8/OrderPaidPositiveTest.csv");
    }
}