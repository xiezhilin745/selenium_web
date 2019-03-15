package com.xiezl.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.xiezl.Base;

public class ScreenUtil {
	public static void takeScreenShot(String dir) {
		Date date = new Date();
		long  time = date.getTime();
		if (Base.driver instanceof ChromeDriver) {
			ChromeDriver chromeDriver = (ChromeDriver) Base.driver;
			File from = chromeDriver.getScreenshotAs(OutputType.FILE);
			File to = new File(dir+File.separator+time+".jpg");
			try {
				FileUtils.copyFile(from, to);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (Base.driver instanceof FirefoxDriver) {
			FirefoxDriver firefoxDriver = (FirefoxDriver) Base.driver;
			File from = firefoxDriver.getScreenshotAs(OutputType.FILE);
			File to = new File(dir+File.separator+time+".jpg");
			try {
				FileUtils.copyFile(from, to);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
