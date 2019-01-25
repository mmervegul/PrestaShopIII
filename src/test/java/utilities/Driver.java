package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    private static WebDriver driver;

    private Driver(){
        /*
         * to our Driver class we add a private Constructor that so one can make an object of this class whoever needs
         * to use te driver object has to call the getDriver() method
         */
    }

    /*
     * We have a class with a static variable, and a getter method
     *
     * How the getter works?
     *     1. it checks of driver object gas value/ initialized or noy
     *     2. if driver is noy initialized it will be initialized inside if clause
     *     3. it will return the driver object
     *     4. if the driver is already initialized, it will skip the if part and simple return it
     */

    public static WebDriver getDriver(){
        if (driver == null){
            String browser = ConfigurationReader.getProperty("browser");

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
