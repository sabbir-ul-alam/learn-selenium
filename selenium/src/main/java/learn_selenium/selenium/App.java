package learn_selenium.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App
{
    private static String user_name="10_i_am_groot_01";
    private static String password = "oncloud9";
    private static String url = "https://www.instagram.com/ishmam_oishi/";
    public static void main( String[] args ) throws IOException {
        WebDriver driver = new ChromeDriver();
        //Navigate to insta//
        driver.get(url);
        //login with user name and pass

    }
}
