package BDD;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    static WebDriver driver;
    String baseURL = "http://automationpractice.com";

    @Before
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Given("^User is on home page$")
    public void verifyUserIsOnHomePage() {
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("my store"));
        Assert.assertTrue(driver.findElement(By.cssSelector("#search_query_top")).isDisplayed());
    }

    @When("^User clicks sign-in link$")
    public void clickSignInLink() {
        driver.findElement(By.cssSelector(".login")).click();
    }

    @When("^User enters email address as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void enterLoginCredentials(String email, String password) {
        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
    }

    @Then("^User should see login form$")
    public void verifyLoginFormVisible() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#login_form")).isDisplayed());
    }

    @Then("^User should be logged in successfully$")
    public void isUserLoggedIn() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".info-account")).isDisplayed());
    }

    @And("^User clicks sign-in button$")
    public void selectSignIn() {
        driver.findElement(By.cssSelector("#SubmitLogin")).click();
    }

    @And("^User's full name \"([^\"]*)\" should be displayed in menu bar$")
    public void user_should_see_his_name_as_something(String userName) {
        Assert.assertEquals(userName, driver.findElement(By.cssSelector(".account")).getText());
    }

    @Then("^User should not be logged in successfully$")
    public void verifyLoginFailure(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed());
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("login - my store"));
    }
    @And("^User should see error message as \"([^\"]*)\"$")
    public void isErrorMessageDisplayed(String errMsg){
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[1]/ol/li")).getText().equalsIgnoreCase(errMsg));
    }
}