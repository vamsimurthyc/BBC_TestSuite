import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BBC_Suite3 {
    private static WebDriver driver = null;
    static ArrayList<String> texts = new ArrayList<>();
    static HSSFWorkbook workbook = new HSSFWorkbook();
    static HSSFSheet sheet = workbook.createSheet();
    static FileOutputStream fileOutputStream;
    static Map<Integer, String> map = new TreeMap();
    static HSSFRow nRow;

    static {
        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\e007463\\Documents\\BBC_Output\\Output.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BBC_Suite3() throws FileNotFoundException {
    }


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;


        List<WebElement> links = driver.findElements(By.tagName("a"));

        Iterator<WebElement> it = links.iterator();
        int i =0;
        while(it.hasNext()){

            //new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(it.next()));
           // driver.findElement(By.id("checkoutLink")).click();

           // WebDriverWait wait = new WebDriverWait(driver, 10);
            //wait.until(ExpectedConditions.visibilityOf(it.next()));

            //for(int i=0;i<50;i++) {


                url = it.next().getAttribute("href");


                System.out.println(url);

                if (url == null || url.isEmpty()) {
                    //System.out.println("URL is either not configured for anchor tag or it is empty");
                    continue;
                }

                if (!url.startsWith("https://www.bbc.com/")) {
                    // System.out.println("URL belongs to another domain, skipping it.");
                    continue;
                }

                try {
                    huc = (HttpURLConnection) (new URL(url).openConnection());

                    huc.setRequestMethod("HEAD");

                    huc.connect();

                    respCode = huc.getResponseCode();

                    if (respCode >= 400) {
                        System.out.println(url + " is a broken link");
                    } else {
                        System.out.println(url + " is a valid link");
                      //  texts.add(url);
                        System.out.println(texts);

                        Object[ ] obj1 = new Object[ ]
                                { url, respCode};

                        System.out.println(url+"******************");
                        map.put(1,url);
                        System.out.println(map.get(1)
                        );
                        System.out.println(obj1);

                    }

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
           // }
            i++;
        }

        //HSSFRow row = sheet.createRow(i);
        //  row.createCell(0).setCellValue(url);

        //workbook.write(fileOutputStream);
        //row.createCell(0).setCellValue(texts.toString());
        //row.createCell(1).setCellValue(list1.get(i).toString());
        driver.quit();

        System.out.println(map.size());
        System.out.println(map.get(1));
    }



    }

