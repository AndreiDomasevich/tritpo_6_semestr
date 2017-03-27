import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by a.domasevich on 3/23/17.
 */
public class ConfigurationClass {
    FileInputStream input_stream;
    Properties property;
    String host;
    String login;
    String password;
    String browser;
    String pathToCSV;

    public  ConfigurationClass() throws IOException {
        host = "db.host";
        login = "db.login";
        password = "db.password";
        browser = "db.browser";
        pathToCSV = "db.pathToCSV";
        property = new Properties();
        input_stream = new FileInputStream("src/main/resources/config.properties");
        property.load(input_stream);
    }

    public String getHost() {

        return property.getProperty(host);
    }

    public String getLogin() {

        return property.getProperty(login);
    }

    public String getPassword() {

        return property.getProperty(password);
    }

    public String getBrowser() {

        return property.getProperty(browser);
    }

    public String getPathToCSV() {

        return property.getProperty(pathToCSV);
    }

    public void implicitlyWait(int time_seconds) {

        Singleton.getDriver().manage().timeouts().implicitlyWait(time_seconds, TimeUnit.SECONDS);
    }

    public void explicityWait(int time_seconds) {

        new WebDriverWait(Singleton.getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".project-navigation__sign")));
    }

}
