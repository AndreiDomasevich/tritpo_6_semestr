import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by a.domasevich on 3/27/17.
 */
public class WeatherPage {

    ConfigurationClass configData;
    FileWriter pog_writer;
    String pathToWetherCSV_File;
    String toDayElementXpath;

    public WeatherPage() throws IOException {
        toDayElementXpath = "//*[@id=\"container\"]/div/div[2]/div/div/div/div/div[1]";
        pathToWetherCSV_File = "src/main/resources/weather.csv";
        configData = new ConfigurationClass();
        pog_writer = new FileWriter(pathToWetherCSV_File, false);
    }

    public void checkToDayWeather() throws IOException {
        WebElement element = Singleton.getDriver().findElement(By.xpath(toDayElementXpath));
        pog_writer.write("Прогноз погоды на " + element.getText());
        pog_writer.close();
    }

    public String getPageURL() {
        return Singleton.getDriver().getCurrentUrl();
    }

}
