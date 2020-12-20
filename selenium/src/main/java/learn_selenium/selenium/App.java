package learn_selenium.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App
{
    private static String user_name="10_i_am_groot_01";
    private static String password = "oncloud9";
    private static String login_url = "https://www.instagram.com/";
    private static String profile_url = "https://www.instagram.com/ishmam_oishi/";
    private static WebDriver driver;
    public static void main( String[] args ) throws IOException, InterruptedException {
         driver = new ChromeDriver();
        //Navigate to insta//
        driver.get(login_url);
//        WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
//                .until(driver -> driver.findElement(By.name("q")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebDriverWait(driver,30).until(EC.presence_of_element_located((By.XPATH,"//input[@name='q']")))
        //WebElement use=((ChromeDriver) driver).findElementByName("username");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement login_button = driver.findElement(By.tagName("button"));
        inputs.get(0).sendKeys(user_name);
        inputs.get(1).sendKeys(password);
        login_button.click();
        getCookies();
        readCookies();
    //navigate to ishmams page
        driver.navigate().to(profile_url);



        //driver.quit();

        //login with user name and pass


    }
    public static void  getCookies(){
        File file = new File("Cookies.data");
        try
        {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);
            // loop for getting the cookie information

            // loop for getting the cookie information
            for(Cookie ck : driver.manage().getCookies())
            {
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                Bwrite.newLine();
            }
            Bwrite.close();
            fileWrite.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public static void readCookies(){
        try{

            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while((strline=Buffreader.readLine())!=null){
                StringTokenizer token = new StringTokenizer(strline,";");
                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if(!(val=token.nextToken()).equals("null"))
                    {
                        System.out.println(val);
                        expiry = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(val);
                        System.out.println(expiry);

                    }

                    Boolean isSecure = new Boolean(token.nextToken()).
                            booleanValue();
                    Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
                    System.out.println(ck);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session
                }
                Thread.sleep(500);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


    }

}
