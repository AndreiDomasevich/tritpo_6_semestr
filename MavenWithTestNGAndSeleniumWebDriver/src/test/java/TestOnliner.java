import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestOnliner {

    LogInPage loginPage;
    HomePage homePage;
    RandomPage randomPage;
    WeatherPage weatherPage;
    ConfigurationClass configData;
    String actualString;
    String expectedString;
    String parameterString;
    String weatherPageURL;

    @BeforeTest
    public void setup() throws IOException {
        configData = new ConfigurationClass();
        Singleton.setDriver(configData.getBrowser());
        homePage = new HomePage(Singleton.getDriver());
        loginPage = new LogInPage(Singleton.getDriver());
        randomPage = new RandomPage(Singleton.getDriver());
        weatherPage = new WeatherPage();
        homePage.goToOnliner(Singleton.getDriver());
        parameterString = "";
        weatherPageURL = "http://pogoda.onliner.by/";
    }


    @Test(priority = 0)
    public void logIn(){
        Assert.assertTrue(homePage.checkHomePageName());
        homePage.loginButtonClick();
    }

    @Test(priority = 1)
    public void UsernameAndPassword(){

        loginPage.authorization(configData.getLogin(), configData.getPassword());
    }

    @Test(priority = 2)
    public void goToRandomCategory() throws IOException {

        actualString = homePage.goToRandomTheme(homePage.getListOfPopularThemes(), parameterString);
        expectedString = randomPage.getCategoryName();
        Assert.assertEquals(actualString, expectedString);
    }

    @Test(priority = 3)
    public void goToTheHomePage() throws IOException {

        randomPage.goToTheHomePage();
        Assert.assertTrue(homePage.checkHomePageName());
        homePage.getListOfTheOpinions();
    }

    @Test(priority = 4)
    public void getWeatherToDay() throws IOException {

        homePage.goToWeatherPage();

        weatherPage.checkToDayWeather();
        Assert.assertEquals(weatherPageURL, weatherPage.getPageURL());
    }
    @Test(priority = 5)
    public void logOut(){

        homePage.logoutClick();
    }

    @AfterTest
    public void thearDown() throws IOException {

        Singleton.getDriver().quit();
    }
}
