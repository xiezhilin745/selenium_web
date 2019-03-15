package com.xiezl.login;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xiezl.Base;
import com.xiezl.assertion.Assertion;
import com.xiezl.util.ExcelUtil;
import com.xiezl.util.PropertiesUtil;

public class LoginPage extends Base{
	
	@Test(dataProvider="loginDatas")
	public void initcase(String mobilephone, String password, String expected){
		//访问页面
		to(PropertiesUtil.urlProperties.getProperty("login.url"));
		//官网点击登录按钮
		click(getElement("跳转登录页面"));
		sendKeys(getElement("用户账号"),mobilephone);
		sendKeys(getElement("用户密码"),password);
		//判断登录页面URL是否包含以下断言
		Assertion.assertUrlPresent("https://e.ikjxc.com");
		click(getElement("登录按钮"));
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		String actual=getElement(By.xpath("/html/body/div[2]/div/div/div/form/div[2]/div[2]/div/div/div")).getText();
//		boolean flag = expected.equals(actual);
//		Assert.assertTrue(flag);
	}

	@Test
	public void inittest(){
		Object [][] datas = ExcelUtil.read("/Excel/ExcelTest.xls",0, 6, 6, 1, 3);
		String mobilephone = datas[0][0].toString();
		String password = datas[0][1].toString();
		String tips = datas[0][2].toString();
		//访问页面
		to(PropertiesUtil.urlProperties.getProperty("login.url"));
		//官网点击登录按钮
		click(getElement("跳转登录页面"));
		sendKeys(getElement("用户账号"),mobilephone);
		sendKeys(getElement("用户密码"),password);
		//判断登录页面URL是否包含以下断言
		Assertion.assertUrlPresent("https://e.ikjxc.com");
		click(getElement("登录按钮"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assertion.assertUrlPresent("ikjxc.com/dashboard");
	}

	@DataProvider
	public Object [][] loginDatas(){
		Object [][] datas = ExcelUtil.read("/Excel/ExcelTest.xls", 0,2, 6, 1, 3);
		return datas;
	}
	
	public static void main(String[] args) {
		Object [][] datas = ExcelUtil.read("/Excel/ExcelTest.xls",0, 6, 6, 1, 3);
		String mobilephone = datas[0][0].toString();
		String password = datas[0][1].toString();
		String config = datas[0][2].toString();
		System.out.println(config);

	}
	
}
