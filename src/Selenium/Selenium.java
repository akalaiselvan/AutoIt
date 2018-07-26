package Selenium;


        import java.util.concurrent.TimeUnit;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {

    private static String property;


    public static void main(String[] args) {
        getProperty();
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver,30);

        //could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8482/tfa/");
// Maximize the window. ExpectedConditions.presenceOfElementLocated(By.id("password"))
        driver.manage().window().maximize();
// Enter UserName
        driver.findElement(By.id("identifierId")).sendKeys("akselvan44");
        driver.findElement(By.id("identifierNext")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("ksjr");
        WebElement pw = driver.findElement(By.xpath("//div[@id='password']"));
        wait.until(ExpectedConditions.elementToBeClickable(pw));
        pw.sendKeys("321654");
        //driver.findElement(By.xpath("//ksjr1028aksdiv[@id='passwordNext']")).sendKeys("kalai");
// Enter Password
//        driver.findElement(By.id("password")).sendKeys("ksjr1028");
// Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
// Click on 'Sign In' button
        driver.findElement(By.id("passwordNext")).click();
//Click on Compose Mail.
//        driver.findElement(By.xpath("//div[@class='z0']/div")).click();
// Click on the image icon present in the top right navigational Bar
  //      driver.findElement(By.xpath("//div[@class='gb_1 gb_3a gb_nc gb_e']/div/a")).click();
//Click on 'Logout' Button
    //    driver.findElement(By.xpath("//*[@id='gb_71']")).click();
//Close the browser.
        //driver.close();
    }
    public static String getProperty() {
        //Use this driver for chrome
        //System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\geckodriver-v0.19.1-win64\\chromedriver.exe");
        property = System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver-v0.19.1-win64\\geckodriver.exe");
        return property;
    }




}