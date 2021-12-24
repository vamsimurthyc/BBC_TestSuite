import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class BBCSuite {
    public static final String LINKS_TAG = "a";
    public static void BBC(){

    System.setProperty("webdriver.chrome.driver", "C:\\Users\\e007463\\Downloads\\chromedriver_7\\chromedriver.exe");

//            ChromeOptions chromeOptions = new ChromeOptions();
//    chromeOptions.addArguments("--headless");
//
//    ChromeDriver driver = new ChromeDriver(chromeOptions);
//    driver.get("https://www.bbc.com/");
//    System.out.println(driver.getTitle());
        WebDriver driver = null;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.bbc.com/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

        List<WebElement> allLinks = driver.findElements(By.tagName(LINKS_TAG));
        System.out.println(allLinks);
        for(WebElement link:allLinks){
            System.out.println(link.getText() + " - " + link.getAttribute("href"));
        }

        driver.quit();
    }

    public static void main(String[] args){
        BBC();
    }
}
