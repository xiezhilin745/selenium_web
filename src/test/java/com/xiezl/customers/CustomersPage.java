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
		sleep(1000);
		sendKeys(getElement("客户名称输入框"), "客户名称自动化"+currentTime());
		clear(getElement("客户编号输入框"));
		sendKeys(getElement("客户编号输入框"), "客户编号自动化"+currentTime());
		click(getElement("客户类型按钮"));
		selectByVisibleText(getElement("客户类型选择框"), "一级代理商");
		click(getElement("客户类型按钮"));
		sendKeys(getElement("调价比例输入框"), "90");
		sendKeys(getElement("期初应收款金额输入框"), "10");
		sendKeys(getElement("地址输入框"), "客户名称自动化01");
		sendKeys(getElement("联系人名称输入框"), "康师傅");
		sendKeys(getElement("联系人手机输入框"), "13052371908");
		sendKeys(getElement("联系人电话输入框"), "021-666888");
		sendKeys(getElement("联系人邮箱输入框"), "123@qq.com");
		sendKeys(getElement("联系人地址输入框"), "超市零售");
		sendKeys(getElement("联系人备注输入框"), "来一桶");
		click(getElement("保存客户按钮"));	
	}
}
