import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


/**
 * Created by a.domasevich on 3/23/17.
 */

    public class LogInPage {
String userNameFieldXpath;
String passwordFieldXpath;
String loginButtonXpath;

    public LogInPage(WebDriver driver) {
        userNameFieldXpath = "//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[1]/input";
        passwordFieldXpath = "//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[2]/input";
        loginButtonXpath = "//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[4]/div/button";
    }

    public void setUserName(String strUserName){

            Singleton.getDriver().findElement(By.xpath(userNameFieldXpath)).sendKeys(strUserName);
        }

        public void setPassword(String strPassword){

            Singleton.getDriver().findElement(By.xpath(passwordFieldXpath)).sendKeys(strPassword);
        }

        public void clickLogin(){

            Singleton.getDriver().findElement(By.xpath(loginButtonXpath)).sendKeys(Keys.ENTER);
        }

        public void authorization(String strUserName,String strPasword){

            this.setUserName(strUserName);
            this.setPassword(strPasword);
            this.clickLogin();
        }
    }



