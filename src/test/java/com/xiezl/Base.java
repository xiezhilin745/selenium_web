package com.xiezl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.xiezl.po.pojo.Locator;
import com.xiezl.util.UILibraryUtil;

/**
 * @author Administrator
 * 
 */
public class Base {

	private static Log logger = LogFactory.getLog(Base.class);
	public static WebDriver driver;

	@BeforeSuite
	@Parameters(value = { "browserType", "driverPath", "seleniumVersion" })
	public void setUp(String browserType, String driverPath,
			String seleniumVersion) {
		logger.info("******************开始测试******************");
		logger.info("测试开始，配置浏览器类型：" + browserType + "，驱动路径：" + driverPath
				+ "，selenium版本：" + seleniumVersion);
		if ("chrome".equals(browserType)) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches",
					Arrays.asList("--start-maximized"));
			options.addArguments("--test-type", "--start-maximized");
			driver = new ChromeDriver(options);
			logger.info("加载了chrome驱动");

		} else if ("firefox".equals(browserType)) {
			System.setProperty("webdriver.firefox.bin",
					"D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			if ("3.X".equals(seleniumVersion)) {
				System.setProperty("webdriver.gecko.driver", driverPath);
			}
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			logger.info("加载了firefox驱动");
		}
	}

	@AfterSuite
	public void tearDown() {
		sleep(1000);
		logger.info("******************套件测试结束，关闭浏览器******************");
		driver.quit();
	}

	/**
	 * 睡眠毫秒
	 * @param mis
	 */
	public void sleep(int mis) {
		try {
			Thread.sleep(mis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前时间，可用来拼接参数
	 * @param currenttime
	 * @return 
	 */
	public static String currentTime() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currenttime = time.format(date);
		return currenttime;
	}	

	/**
	 * 元素等待和元素解析
	 * 
	 * @param
	 * @return
	 */
	public WebElement getElement(String keyword) {
		// 根据传入的类名，获取locator解析的xml中的关键词
		final Locator locator = UILibraryUtil.pageLocatorsMap.get(
				this.getClass().getName()).get(keyword);
		// 记录日志
		String tips = "获取元素：{'by','" + locator.getBy() + "'},{'value','"
				+ locator.getValue() + "'}";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = null;
		try {
			element = wait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					String by = locator.getBy();
					String value = locator.getValue();
					By by2 = null;
					if ("id".equals(by)) {
						by2 = By.id(value);
					} else if ("xpath".equals(by)) {
						by2 = By.xpath(value);
					} else if ("linkText".equals(by)) {
						by2 = By.linkText(value);
					} else if ("className".equals(by)) {
						by2 = By.className(value);
					} else if ("tagName".equals(by)) {
						by2 = By.tagName(value);
					} else if ("cssSelector".equals(by)) {
						by2 = By.cssSelector(value);
					} else if ("partialLinkText".equals(by)) {
						by2 = By.partialLinkText(value);
					} else if ("name".equals(by)) {
						by2 = By.name(value);
					}
					if (by2 == null) {
						throw new RuntimeException("找不到" + by + "元素");
					}
					return driver.findElement(by2);
				}
			});
		} catch (Exception e) {
			tips += "【失败】";
			logger.error(tips, e);
			return null;
		}
		tips += "【成功】";
		logger.info(tips);
		return element;
	}

	public List<WebElement> getElements(String keyword) {
		final Locator locator = UILibraryUtil.pageLocatorsMap.get(
				this.getClass().getName()).get(keyword);
		String tips = "获取元素列表：{'by','" + locator.getBy() + "'},{'value','"
				+ locator.getValue() + "'}";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> elements = null;
		try {
			elements = wait.until(new ExpectedCondition<List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver) {
					String by = locator.getBy();
					String value = locator.getValue();
					By by2 = null;
					if ("xpath".equals(by)) {
						by2 = By.xpath(value);
					} else if ("linkText".equals(by)) {
						by2 = By.linkText(value);
					} else if ("className".equals(by)) {
						by2 = By.className(value);
					} else if ("tagName".equals(by)) {
						by2 = By.tagName(value);
					} else if ("cssSelector".equals(by)) {
						by2 = By.cssSelector(value);
					} else if ("partialLinkText".equals(by)) {
						by2 = By.partialLinkText(value);
					} else if ("name".equals(by)) {
						by2 = By.name(value);
					}
					if (by2 == null) {
						throw new RuntimeException("找不到" + by + "元素");
					}
					return driver.findElements(by2);
				}
			});
		} catch (Exception e) {
			tips += "【失败】";
			logger.error(tips, e);
			return null;
		}
		tips += "【成功】";
		logger.info(tips);
		return elements;
	}

	/**
	 * 需要前往的url
	 * 
	 * @param url
	 */
	public void to(String url) {
		driver.navigate().to(url);
	}

	/*
	 * com.xiezl.po.pojo.Locator解析xml public Locator getLocator(String keyword)
	 * { Locator locator = UILibraryUtil.pageLocatorsMap.
	 * get(this.getClass().getName()).get(keyword);
	 * 
	 * return locator; }
	 */

	/**
	 * 往元素写入内容
	 * 
	 * @param webElement
	 * @param value
	 */
	public static void sendKeys(WebElement webElement, String value) {
		logger.info("输入数据：【" + value + "】");
		webElement.sendKeys(value);
	}

	/**
	 * 按可见文本选择
	 * @param webElement
	 * @param value
	 */
	public static void selectByVisibleText(WebElement webElement, String value) {
		logger.info("输入数据：【" + value + "】");
		new Select(webElement).selectByVisibleText(value);
	}

	/**
	 * 触发元素的点击事件
	 * @param webElement
	 */
	public static void click(WebElement webElement) {
		logger.info("触发点击事件");
		webElement.click();
	}
	
	/**
	 * 清空输入框的默认值
	 * @param webElement
	 */
	public static void clear(WebElement webElement) {
		logger.info("触发点击事件");
		webElement.clear();
	}

	/**
	 * 获取元素的文本值
	 * @param webElement
	 */
	public static String getText(WebElement element) {
		String text = element.getText();
		logger.info("获取文本内容：【" + text + "】");
		return text;
	}

}
