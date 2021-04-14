package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static String screenshotpath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\";
	public static String extent_reports_path = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\";
	public ExtentReports extent = ExtentManager.createInstance();
	public static ExtentTest test;

	public static void setUp() {
		if(driver==null) {
			//Initialized properties file
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Condition for launching browser//
			if(config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>(); //logic for disable browser popup box
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver =  new ChromeDriver(options);
				log.debug("Chrome Launch");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")), TimeUnit.SECONDS);
			log.debug("Navigated to: "+config.getProperty("testsiteurl"));
		}
	}
	public static void quit() throws InterruptedException {
		if(driver!=null) {
			Thread.sleep(8000);	
			driver.quit();
			log.debug("Browser Closed!!!");
		}
	}
	
	public void click(String locator) {
		if(locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		}else if(locator.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		}
		log.debug("Clicking on element:"+locator);
		test.log(Status.INFO, "Clicking on: '"+locator+"'");
	}
	
	public void typeatext(String locator, String value) {
		if(locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_NAME")) {
			System.out.println(locator+"=========="+value);
			driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		}
		log.debug("Typing in:"+locator+" and entered value is:"+value);
		test.log(Status.INFO, "Tying in: "+locator+" and entered value is:'"+value+"'");
	}
	
	public static void mouseHoverAndClick(String locator) {
		WebElement element = driver.findElement(By.xpath(or.getProperty(locator)));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
	
	public void dropdown(String locator, String selectlocator, String value) {
		String str = or.getProperty(selectlocator);
		String updatedlocator1=str.replace("value",value);
		driver.findElement(By.xpath(or.getProperty(locator))).click();
		driver.findElement(By.xpath(updatedlocator1)).click();
		test.log(Status.INFO, "Selecting from dropdown: "+locator+" and value is:'"+value+"'");
	}
	
	public static String screenshotName;
	public static String getcurrenttime() {
		Date d = new Date();
		String current_time = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		return current_time;
	}

	public static void captureScreenShots(String screenshotpath, String methodName) throws IOException {
		screenshotName = screenshotpath+methodName+"_Full_"+getcurrenttime();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(screenshotName));
	}
}
