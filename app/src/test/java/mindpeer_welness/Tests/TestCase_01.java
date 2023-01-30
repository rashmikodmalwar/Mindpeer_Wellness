package mindpeer_welness.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import mindpeer_welness.Pages.Theropy;

public class TestCase_01 {
    static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public static void createDriver(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        
    }

    @Test(description="Verify therapy booking flow")
    public void TestCase01() throws InterruptedException{
    boolean status;
    String name = "Janaki Nagaraja";
    Theropy therapy = new Theropy(driver);
     therapy.navigateToLoginPage();
     status = therapy.enterEmailToBegin("rashmi.kasetwar@gmail.com");
     assertTrue(status, "Failed to enter with email id");
     status = therapy.selectEmail();
     assertTrue(status,"Failed to select email id");
     status = therapy.enterPassword("Rashmi@1987");
     assertTrue(status,"password is wrong");
     status = therapy.successLogin("Logged In Successfully!");
     assertTrue(status,"Failed to login successfully");
     status = therapy.ClickOnTherapy();
     assertTrue(status,"Failed to click on Therapy");
     status = therapy.selectBookTherapyOption();
     assertTrue(status,"Failed to select therepy to book");
     status =therapy.bookTherapist(name);
     assertTrue(status,"Failed to click for book now");
     status =therapy.enterBookingDetails(name,"struggling with relationship and low confidence", "Rashi", "9970627356", "rashi@gmail.com", "Video");
     assertTrue(status,"Failed to book slot");
     status = therapy.verifyTherapistName(name);
     assertTrue(status, "failed to register");

    }

    @AfterTest
    public static void quiteDriver(){
        driver.quit();
    }
    
}
