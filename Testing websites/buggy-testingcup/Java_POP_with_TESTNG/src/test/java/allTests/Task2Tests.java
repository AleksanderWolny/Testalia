package allTests;

import allFinals.UrlAddressFinal;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task2.FilterProductByCategory;
import seleniumSetup.InitialStateBeforeTest;

import java.util.concurrent.ThreadLocalRandom;


public class Task2Tests extends InitialStateBeforeTest {

    private FilterProductByCategory fPBC;
    SoftAssert sa;

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_2);
        fPBC = new FilterProductByCategory(driver);
        sa = new SoftAssert();
    }

    @Test
    public void chooseRandomCategoryAndValidateProducts() {
        int randomCategoryNumber = ThreadLocalRandom.current().nextInt(1, fPBC.selectOptionList.size());
        fPBC.expandContainer.click();
        fPBC.resultsOption.get(randomCategoryNumber - 1).click();
        System.out.println(randomCategoryNumber - 1);

        for (WebElement filteredList : fPBC.categoryInProducts) {
            sa.assertEquals(fPBC.selectedOption.getText(), filteredList.getText());
        }
        sa.assertAll();
    }

    @Test
    public void chooseCategoryByAPartialWord() {
        //partial word 't', can match with 'Sport' and 'Elektronika'
        String partialTestWord = "t";
        fPBC.expandContainer.click();
        fPBC.searchCategoryInput.sendKeys(partialTestWord);
        for (WebElement listLi : fPBC.resultsOption) {
            sa.assertTrue(listLi.getText().contains(partialTestWord));
        }
    }

    @Test
    public void mappingPolishWord() {
        //word 'uslugi' shoud find category 'usługi'
        String partialTestWord = "uslugi";
        fPBC.expandContainer.click();
        fPBC.searchCategoryInput.sendKeys(partialTestWord);
        fPBC.resultsOption.get(0).click();
        for (WebElement listLi : fPBC.categoryInProducts) {
            sa.assertEquals(listLi.getText(), "Firma i usługi");
        }
        sa.assertAll();
    }
}