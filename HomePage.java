import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {

    Pattern regularEx;
    Random random;
    ConfigurationClass configData;
    WebElement theme;
    FileWriter writer;
    String homePageURL;
    String regularExString;
    String loginButtonXpath;
    String listOfPopularThemesSelector;
    String logOutButtonXpath;
    String weatherButtonXpath;

    public HomePage(WebDriver driver) throws IOException {
        homePageURL = "https://www.onliner.by/";
        regularExString = "<div class=\"b-opinions-main-2__text\">(.+?)</div>";
        loginButtonXpath = "//*[@id=\"userbar\"]/div[2]/div[1]";
        listOfPopularThemesSelector = ".project-navigation__sign";
        logOutButtonXpath = "//*[@id=\"userbar\"]/div[1]/ul/li[3]/a";
        weatherButtonXpath = "//*[@id=\"weather-informer\"]/a/span";
        configData = new ConfigurationClass();
        random = new Random();
        writer = new FileWriter(configData.getPathToCSV(), false);
        regularEx = Pattern.compile(regularExString);
    }

    public void goToOnliner(WebDriver driver) throws IOException {

        driver.get(configData.getHost());
        driver.manage().window().maximize();
        configData.implicitlyWait(10);
    }

    public Boolean checkHomePageName() {

        if(Singleton.getDriver().getCurrentUrl().contains(homePageURL))
            return true;
        return false;
    }

    public void loginButtonClick() {
        new WebDriverWait(Singleton.getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(loginButtonXpath)));
        Singleton.getDriver().findElement(By.xpath(loginButtonXpath)).click();
        configData.implicitlyWait(10);
    }


    public List<WebElement> getListOfPopularThemes() {

        configData.explicityWait(10);
        List<WebElement> elements = Singleton.getDriver().findElements(By.cssSelector(listOfPopularThemesSelector));
        configData.implicitlyWait(10);
        return elements;
    }

    public String goToRandomTheme(List<WebElement> elements, String randomTheme_name) {

        configData.implicitlyWait(10);
        theme = elements.get(random.nextInt(elements.size() - 4));//т.к. всего 15 элементов в списке, а на странице отображается 12, ([0]...[11])
        randomTheme_name = theme.getText();
        theme.click();
        configData.implicitlyWait(10);

        return randomTheme_name;
    }

    public void logoutClick() {

        configData.implicitlyWait(10);
        Singleton.getDriver().findElement(By.xpath(logOutButtonXpath)).click();
    }

    public  void getListOfTheOpinions() throws IOException {

        for (String opinion: getTags(Singleton.getDriver().getPageSource()))
            writer.write(opinion + "\n");

        writer.close();
    }


    public List<String> getTags(String str) {

        List<String> tags = new ArrayList<String>();
        Matcher matcher = regularEx.matcher(str);
        while (matcher.find())
            tags.add(matcher.group(1));
        return tags;
    }


    public void goToWeatherPage() {
        new WebDriverWait(Singleton.getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"weather-informer\"]/a/span")));
        Singleton.getDriver().findElement(By.xpath(weatherButtonXpath)).click();
        configData.implicitlyWait(10);
    }
}