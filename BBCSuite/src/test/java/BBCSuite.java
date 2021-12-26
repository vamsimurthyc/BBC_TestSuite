import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BBCSuite {
    public static final String LINKS_TAG = "a";
    public static void BBC() throws IOException {

        //FileInputStream fileInputStream = new FileInputStream("C:\\Users\\e007463\\Documents\\BBC_Output")
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
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
        try {
            for(WebElement link:allLinks){
                //list = new ArrayList<>();
                //Thread.sleep(5000);

                System.out.println((link.getText().toString()));
                list.add(link.getText());
                list1.add(link.getAttribute("href"));

//
//                HSSFRow row = sheet.createRow();
//                row.createCell(0).setCellValue(list.get(i));

                   }
        } catch (Exception e) {
            e.printStackTrace();
        }
            System.out.println(list);
            System.out.println(list1);
            for(int i=0;i<list.size();i++) {
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(list.get(i).toString());
                row.createCell(1).setCellValue(list1.get(i).toString());
                //System.out.println(link.getText() + " - " + link.getAttribute("href"));}
            }



        driver.quit();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\e007463\\Documents\\BBC_Output\\Output.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        BBC();
    }
}
