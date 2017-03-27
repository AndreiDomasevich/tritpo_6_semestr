import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;

/**
 * Created by a.domasevich on 3/23/17.
 */
public class Singleton {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (!driver.equals(null))
            return driver;
        return new ChromeDriver();
    }

    public static WebDriver setDriver(String browser) throws MalformedURLException {

        if(browser.equals("firefox")) {
         System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            driver = new FirefoxDriver();
        }

        else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
        }

        return driver;
    }
}
