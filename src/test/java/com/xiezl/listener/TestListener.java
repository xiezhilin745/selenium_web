package com.xiezl.listener;

import java.io.File;
import java.util.Date;
import org.testng.ITestResult;
import org.testng.Reporter;
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
		File image = ScreenUtil.takeScreenShot(sureenShotDir);
		//获取绝对路径
		String path = image.getAbsolutePath();
		//截取绝对路径：screendshot\loginTest\2019-03-15\1552633854161.jpg
		String accessPath = path.replace(path.substring(0, path.lastIndexOf("screendshot")), "http://localhost:8080//").replace("\\", "/");
		//把截图文件传递到html报告中
		Reporter.log("<img src='"+accessPath+"' hight='100' width='100'><a href='"+accessPath+"' target='_blank'>点击查看大图</a></img>");
	}
	
	public static void main(String[] args) {
		System.out.println(File.separator);
	}
}
