package seleniumSetup;

import allMethods.DurationOfTheTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class InitialStateBeforeTest {
    protected static WebDriver driver;

    @BeforeSuite
    public void suitSetup(ITestContext context) {
        System.out.println("Test name: " + context.getSuite().getName());
        System.out.println("Started at: ");
        System.out.println(context.getStartDate());
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void suitCleanUp(ITestContext context) {
        System.out.println("Ended at: ");
        System.out.println(context.getEndDate());
        System.out.println("The test took: ");
        long testDuration = context.getEndDate().getTime() - context.getStartDate().getTime();
        System.out.println(DurationOfTheTest.convertSecondToHours(testDuration) + "ms");
    }
}