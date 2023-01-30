package mindpeer_welness.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Theropy {
    String url = "https://dashboard.mindpeers.co/login";
     
    // public static WebDriver createDriver(){
       
        WebDriver driver ;
       
        public Theropy(WebDriver driver){
            //    WebDriverManager.chromedriver().setup();
            //    driver = new ChromeDriver();
               this.driver = driver;
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.manage().window().maximize();
         }
    
      public void navigateToLoginPage(){
       if(!driver.getCurrentUrl().equals(this.url)){
        driver.get(url);
       }
    }

    public boolean enterEmailToBegin(String email){
        try{
            WebElement emailText = driver.findElement(By.xpath("//input[@type='email']"));
            emailText.sendKeys(email);
            WebElement btn_Next = driver.findElement(By.xpath(("//button[@type='submit']")));
            btn_Next.click();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean selectEmail(){
      try{
        WebElement email = driver.findElement(By.xpath("//div[@class='user-information']"));
        email.click();
        return true;
      }catch(Exception ex){
        return false;
      }
    }
 
    public boolean enterPassword(String password){
        try{
        WebElement passwordTxt = driver.findElement(By.xpath("//input[@type='password']"));
        passwordTxt.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.invisibilityOf(loginBtn));
        return true;
        }catch(Exception ex){
            return false;
        }
    }

    public boolean successLogin(String message){
        try{
            WebElement loginmessage = driver.findElement(By.xpath("//div[@class='MuiAlert-message']"));
            if(loginmessage.getText().equalsIgnoreCase(message)){
                loginmessage.isDisplayed();
                return true;
            }
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    public boolean ClickOnTherapy(){
        try{
          WebElement clickTherapy = driver.findElement(By.xpath("//span[text()='Therapy']"));
          clickTherapy.click();
          return true;
        }catch(Exception ex){
          return false;
        }
    }

    public boolean selectBookTherapyOption(){
        try{
            WebElement bookTherapyClick = driver.findElement(By.xpath("(//div[@class='space-box-title card-waves position-absolute left-0 bottom-0'])[2]"));
            bookTherapyClick.click();
            WebElement popupClose = driver.findElement(By.xpath("//div[@class='premium-popup-close-btn']"));
                WebElement popup = driver.findElement(By.xpath("//div[@class='modal-body']"));
                WebDriverWait wait =new WebDriverWait(driver,30);
                wait.until(ExpectedConditions.visibilityOf(popup));
                if(popup.isDisplayed()){
                popupClose.click();
                Thread.sleep(3000);
                }
                return true;

        }catch(Exception ex){
            return false;
        }
    }

    public boolean bookTherapist(String name){
        try{
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)", "");
            WebElement parantelement_bookTherapist = driver.findElement(By.xpath("//div[@class='infinite-scroll-component__outerdiv']"));
            List <WebElement> childElement = parantelement_bookTherapist.findElements(By.xpath("//div[@class='therapy-v2-expert-info-container']//div//div[@class='therapy-v2-expert-info-name']"));
             for(WebElement nameString : childElement){
                if(nameString.getText().equalsIgnoreCase(name)){
              Thread.sleep(3000);     
              driver.findElement(By.xpath("//button[text()='Book Now']")).click();
                    return true;
                }
             }
        }catch(Exception ex){
            return false;
        }
      return true;
    }

    public boolean enterBookingDetails(String therapistName,String problem,String guardianName,String guardianPhone,String email,String mode){
        try{
            WebElement problemDescription = driver.findElement(By.name("problemFacing"));
            problemDescription.sendKeys(problem);
            WebElement guardian_txt = driver.findElement(By.name("guardianName"));
            guardian_txt.sendKeys(guardianName);
            WebElement guardianPhone_txt = driver.findElement(By.name("guardianPhone"));
            guardianPhone_txt.sendKeys(guardianPhone);
            WebElement guardianEmail_txt = driver.findElement(By.name("guardianEmail"));
            guardianEmail_txt.sendKeys(email);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,200)", "");
            Thread.sleep(3000);
            List <WebElement> modeOfCommunication =driver.findElements(By.xpath("//div[@class='available-modes']//div "));
            for(WebElement modeOfComm : modeOfCommunication){
            if(modeOfComm.getText().equalsIgnoreCase(mode)){
                modeOfComm.click();
            }
            }
          WebElement time_click = driver.findElement(By.xpath("//div[@class='slots therapy-time-slot  '] "));
            time_click.click();
          WebElement validateTime = driver.findElement(By.xpath("//div[@class='therapy-date-time']//p"));
          validateTime.getText().equals(time_click.getText());
          WebElement next_btn = driver.findElement(By.xpath("//button[text()='Next']"));
          next_btn.click();
          return true;
        }catch(Exception ex){
            return false;
        }
       
    }

    public boolean verifyTherapistName(String name){
        try{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement summerry_lbl  =driver.findElement(By.xpath("//div[text()='Payment Summary']"));
        wait.until(ExpectedConditions.visibilityOf(summerry_lbl));
        WebElement therapist_lbl =driver.findElement(By.xpath("(//div[@class='pay-detail-content']//h3)[1]"));
        name.equals(therapist_lbl.getText());
        return true;
        }catch(Exception ex){
            return false;
        }
    }

}
