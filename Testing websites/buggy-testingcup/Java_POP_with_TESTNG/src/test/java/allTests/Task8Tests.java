package allTests;

import allFinals.PaymentCardLengthFinal;
import allFinals.UrlAddressFinal;
import allMethods.GenerateRandomTestData;
import allMethods.TestDataProvider;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageDeclaration.task8.PaymentDropDownMenu;
import seleniumSetup.InitialStateBeforeTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import static allFinals.TextOnPageFinal.*;


public class Task8Tests extends InitialStateBeforeTest {

    private PaymentDropDownMenu pDDM;
    Actions actions;
    SoftAssert sA = new SoftAssert();

    @BeforeMethod
    public void getPageAddress() {
        driver.get(UrlAddressFinal.URL_TASK_8);
        pDDM = new PaymentDropDownMenu(driver);
        actions = new Actions(driver);
    }

    @Test
    public void nameSurnameAttributeRequired() {
        Assert.assertNotNull(pDDM.inputNameSurname.getAttribute("required"));
    }

    @Test
    public void cardNumberAttributeRequired() {
        Assert.assertNotNull(pDDM.inputCardNumber.getAttribute("required"));
    }

    @Test
    public void cvvNumberAttributeRequired() {
        Assert.assertNotNull(pDDM.inputCvvNumber.getAttribute("required"));
    }

    @Test
    public void cvvHoverInfo() {
        actions.moveToElement(pDDM.cvvNumberInfoTooltip).perform();
        if (pDDM.cvvNumberInfoTooltip.getAttribute("data-original-title").equals(pDDM.cvvNumberInfoTooltipInner.getText())) {
            Assert.assertEquals(pDDM.cvvNumberInfoTooltip.getAttribute("data-original-title"), CVV_INFO_TEXT_TASK_8);
        } else {
            Assert.fail("Text on hover CVV code, is not equal to CVV_INFO_TEXT!");
        }
    }

    @Test(dataProvider = "cvvIncorrectFormatTest",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"nameSurnameAttributeRequired",
                    "cardNumberAttributeRequired",
                    "cvvNumberAttributeRequired"})
    public void cvvIncorrectFormat(String nameSurname, String cardNumber, String cvvNumber) {
        pDDM.inputNameSurname.clear();
        pDDM.inputNameSurname.sendKeys(nameSurname);
        pDDM.inputCardNumber.clear();
        pDDM.inputCardNumber.sendKeys(cardNumber);
        pDDM.inputCvvNumber.clear();
        pDDM.inputCvvNumber.sendKeys(cvvNumber);
        pDDM.submitButton.click();
        Assert.assertEquals(pDDM.cvvIncorrectFormat.getText(), CVV_INCORRECT_FORMAT_TASK_8);
    }

    @Test(dependsOnMethods = {"nameSurnameAttributeRequired",
            "cardNumberAttributeRequired",
            "cvvNumberAttributeRequired"})
    public void expiryDateCardAlert() {
        boolean flag = false;
        GenerateRandomTestData gRTD = new GenerateRandomTestData();
        String nameSurname = gRTD.generateRandomNameSurname();
        String cardNumber = gRTD.generateAnySequenceOfNumber(15);
        String cvvNumber = gRTD.generateCvv();
        Calendar cal = Calendar.getInstance();
        String getCurrentMonth = new SimpleDateFormat("MM").format(cal.getTime());
        for (int i = 0; i <= 11; i++) {
            if (i + 1 != Integer.parseInt(getCurrentMonth) + 1 || Integer.parseInt(getCurrentMonth) == 12) {
                pDDM.inputNameSurname.clear();
                pDDM.inputNameSurname.sendKeys(nameSurname);
                pDDM.inputCardNumber.clear();
                pDDM.inputCardNumber.sendKeys(cardNumber);
                pDDM.inputCvvNumber.clear();
                pDDM.inputCvvNumber.sendKeys(cvvNumber);
                pDDM.monthDropdownMenu.get(i).click();
                pDDM.submitButton.click();
            } else {
                break;
            }
            flag = pDDM.alertExpiryDate.getText().equals(ALERT_EXPIRY_DATE_TASK_8);
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void patternPaymentCard() {
        for (WebElement typeDropdownMenu : pDDM.cardTypeDropdownMenu) {
            typeDropdownMenu.click();
            Map<String, String> cL = PaymentCardLengthFinal.getCardLength();
            for (String key : cL.keySet()) {
                if (typeDropdownMenu.getText().equals(key)) {
                    sA.assertEquals(cL.get(typeDropdownMenu.getText()),
                            pDDM.inputCardNumber.getAttribute("pattern"),
                            typeDropdownMenu.getText() + " is not equal.");
                }
            }
        }
        sA.assertAll();
    }

    @Test(dataProvider = "orderPaidPositiveTest",
            dataProviderClass = TestDataProvider.class,
            dependsOnMethods = {"nameSurnameAttributeRequired", "cardNumberAttributeRequired",
                    "cvvNumberAttributeRequired", "patternPaymentCard"}
    )
    public void orderPaid(String optionIndex, String nameSurname, String cardNumber, String cvvNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        pDDM.cardTypeDropdownMenu.get(Integer.parseInt(optionIndex)).click();
        pDDM.inputNameSurname.clear();
        pDDM.inputNameSurname.sendKeys(nameSurname);
        pDDM.inputCardNumber.clear();
        pDDM.inputCardNumber.sendKeys(cardNumber);
        pDDM.inputCvvNumber.clear();
        pDDM.inputCvvNumber.sendKeys(cvvNumber);
        pDDM.yearDropdownMenu.get(2).click();
        pDDM.submitButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOf(pDDM.alertSuccess));
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Cant enter: "
                    + cardNumber.length()
                    + " characters, into field: "
                    + pDDM.cardTypeDropdownMenu.get(Integer.parseInt(optionIndex)).getText());
        }
        Assert.assertEquals(pDDM.alertSuccess.getText(), ALERT_SUCCESS_TEXT_TASK_8);
    }
}