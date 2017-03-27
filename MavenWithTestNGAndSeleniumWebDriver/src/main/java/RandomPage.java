import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by a.domasevich on 3/23/17.
 */
public class RandomPage {

    ConfigurationClass configData;
    String className;
    public RandomPage(WebDriver driver) throws IOException {

        configData = new ConfigurationClass();
        className = "schema-header__title";
    }

    public String getCategoryName() {
        configData.implicitlyWait(10);
        WebElement categoryName = Singleton.getDriver().findElement(By.className(className));
        return categoryName.getText();
    }
    public void goToTheHomePage() {
        Singleton.getDriver().get(configData.getHost() + "#login");
        configData.implicitlyWait(10);
    }
}
