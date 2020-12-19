package TestFlight;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestAutomation {
    WebDriver driver;

    @BeforeTest
    public void driverChrome()
    {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
        driver = new ChromeDriver();
        driver.get("https://www.skyscanner.com.tr");
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void searchDepartureCity()
    {
        driver.findElement(By.cssSelector("input#fsc-origin-search")).sendKeys("İstanbul");
        Actions pressEnterKey = new Actions(driver);
        pressEnterKey.moveToElement(driver.findElement(By.cssSelector("input#fsc-origin-search"))).sendKeys(Keys.ENTER).build().perform();
        pressEnterKey.sendKeys(Keys.TAB).build().perform();
    }

    @Test(priority = 2)
    public void searchArrivalCity()
    {
        driver.findElement(By.cssSelector("input#fsc-destination-search")).sendKeys("Ankara");
        Actions pressEnterKey = new Actions(driver);
        pressEnterKey.moveToElement(driver.findElement(By.cssSelector("input#fsc-destination-search"))).sendKeys(Keys.ENTER).build().perform();
    }

    @Test(priority = 3)
    public void chooseDepartureDate()
    {
        driver.findElement(By.id("depart-fsc-datepicker-button")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        List<WebElement> departureDate = driver.findElements(By.xpath("//div[contains(@class,'_content')] "));
        int countDepartureDate = driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).size();

        for (int i = 0; i < countDepartureDate; i++)
        {
            String text = driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).get(i).getText();
            if (text.equalsIgnoreCase("22"))
            {
                driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).get(i).click();
                break;
            }
        }
    }
    @Test(priority = 4)
    public void chooseArrivalDate()
    {
        driver.findElement(By.id("return-fsc-datepicker-button")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        List<WebElement> arrivalDate = driver.findElements(By.xpath("//div[contains(@class,'_content')]"));
        int countArrivalDate = driver.findElements(By.xpath("//div[contains(@class,'_content')]")).size();

        for (int i = 0; i < countArrivalDate; i++)
        {
            String text = driver.findElements(By.xpath("//div[contains(@class,'_content')]")).get(i).getText();
            if (text.equalsIgnoreCase("25"))
            {
                driver.findElements(By.xpath("//div[contains(@class,'_content')]")).get(i).click();
                break;
            }
        }
    }

    @Test(priority = 5)
    public void checkTransitFiter()
    {
        driver.findElement(By.name("directOnly")).click();
    }

    @Test(priority = 6)
    public void searchFlight()
    {
        driver.findElement(By.xpath("//*[text() = 'Ara']")).click();
    }

    @Test(priority = 7)
    public void waitForFlightSelection()
    {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test(priority = 8)
    public void chooseDepartureAndArrivalFlight()
    {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 400);");
        List<WebElement> departureFlight = driver.findElements(By.xpath("//button[@class='BpkButtonBase_bpk-button__1Sw0Y TicketStub_ctaButton__7Xfvg']"));
        int countDepartureFlight = driver.findElements(By.xpath("//button[@class='BpkButtonBase_bpk-button__1Sw0Y TicketStub_ctaButton__7Xfvg']")).size();

        for (int i = 0; i < countDepartureFlight; i++)
        {
            if(i == 3)
            {
                driver.findElements(By.xpath("//button[@class='BpkButtonBase_bpk-button__1Sw0Y TicketStub_ctaButton__7Xfvg']")).get(i).click();
            }
        }
    }
    @Test(priority = 9)
    public void waitForChoose()
    {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test(priority = 10)
    public void chooseSite()
    {
        driver.findElement(By.xpath("//*[text() = 'Seç']")).click();

    }
    @Test(priority = 11)
    public void closeBrowser()
    {
        driver.close();
    }
}






