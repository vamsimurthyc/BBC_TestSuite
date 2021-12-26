import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BBC_Suite {
    static WebDriver driver = null;

    public static void clickAllHyperLinksByTagName() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.bbc.com/");
        driver.manage().window().maximize();


        //Store all the "a" tagname WebElements to links variable.
        List<WebElement> links = driver.findElements(By.tagName("a"));

        //Iterate over all the "links" WebElements using java for-each loop
        for (WebElement link : links) {
            //print the text of each link variable using Selenium's getText() method.
            System.out.println(link.getText());

        }



    }
    public static void main(String[] args) throws IOException, InterruptedException {
        clickAllHyperLinksByTagName();
    }
}
