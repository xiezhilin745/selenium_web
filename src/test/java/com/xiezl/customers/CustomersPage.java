package com.xiezl.customers;

import org.testng.annotations.Test;
import com.xiezl.Base;

public class CustomersPage extends Base{
	
	/**
	 * 新增客户
	 */
	@Test
	public void NewCustomers() {
		
		click(getElement("基础资料按钮"));
		click(getElement("客户按钮"));
		click(getElement("新增客户按钮"));		
//		getElement(By.xpath("//*[@id='sidebar']/nav/ul/li[7]/a/span")).click();
//		getElement(By.xpath("//*[@id='sidebar']/nav/ul/li[7]/div/div/div[2]/a[2]")).click();
//		getElement(By.xpath("//*[@id='main']/div[1]/div/div[1]/div[2]/a[2]")).click();
	}

}
