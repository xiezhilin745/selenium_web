package com.xiezl.listener;

import java.io.File;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.xiezl.util.DateUtil;
import com.xiezl.util.ScreenUtil;

public class TestListener extends TestListenerAdapter{
	@Override
	public void onTestFailure(ITestResult tr){
		String outputDir = tr.getTestContext().getOutputDirectory();
		String surefireDir = outputDir.substring(0, outputDir.lastIndexOf("\\"));
		//获取testng.xml测试name
		String testName= tr.getTestContext().getCurrentXmlTest().getName();
		String currentTimeStr = DateUtil.dateToYmdString(new Date());
		String sureenShotDir = surefireDir + File.separator +"screendshot"+ File.separator + testName + File.separator +currentTimeStr;
		//调用截图驱动ScreenUtil
		ScreenUtil.takeScreenShot(sureenShotDir);
	}
	
	public static void main(String[] args) {
		System.out.println(File.separator);
	}
}
