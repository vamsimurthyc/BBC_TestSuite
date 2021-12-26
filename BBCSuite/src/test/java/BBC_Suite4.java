import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BBC_Suite4 {

    private static WebDriver driver = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Titles");
        HSSFSheet sheet1 = workbook.createSheet("Description");
        HSSFSheet sheet3 = workbook.createSheet("LinkTexts");

        List<String> texts = new ArrayList<>();
        List<String> Description_texts = new ArrayList<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.bbc.com/");
        driver.manage().window().maximize();
        List<String> hrefs = new ArrayList<String>();
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        List<String> links = new ArrayList<String>();


        //For getting the link texts
        String url="";
        List<WebElement> allURLs = driver.findElements(By.tagName("a"));
        System.out.println("Total links on the Wb Page: " + allURLs.size());

        //We will iterate through the list and will check the elements in the list.
        Iterator<WebElement> iterator = allURLs.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
            links.add(url.toString());
        }

        //For getting the title of the articles
        List<WebElement> titles = driver.findElements(By.xpath("(//*[@class=\"media__link\"])"));
        for (WebElement title:titles){
            String title_obtained = title.getText();
            System.out.println(title_obtained);
            texts.add(title_obtained.toString());
        }

        //For getting the descriptions
        List<WebElement> descriptions=driver.findElements(By.xpath("(//*[@class=\"media__summary\"])"));
        for (WebElement description:descriptions){
            String title_obtained = description.getText();
            System.out.println(title_obtained);
            Description_texts.add(title_obtained.toString());
        }
        for(int i=0;i<texts.size();i++) {
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(texts.get(i).toString());
        }
        for(int i=0;i<Description_texts.size();i++) {
            HSSFRow row1 = sheet1.createRow(i);
            row1.createCell(0).setCellValue(Description_texts.get(i).toString());
            //System.out.println(link.getText() + " - " + link.getAttribute("href"));}
        }
        for(int i=0;i<links.size();i++) {
            HSSFRow row1 = sheet3.createRow(i);
            row1.createCell(0).setCellValue(links.get(i).toString());
            //System.out.println(link.getText() + " - " + link.getAttribute("href"));}
        }

        driver.quit();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\e007463\\Documents\\BBC_Output\\Output.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();


    }


}