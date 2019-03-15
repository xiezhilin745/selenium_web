package com.xiezl.assertion;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.xiezl.Base;

public class Assertion {
	
	/**判断页面指定元素的文本值是否和期望值一致
	 * @param element
	 * @param expected
	 */
	public static void assertTextPresentPrecision(WebElement element, String expected) {
		String actual = Base.getText(element);
		boolean flag = element.equals(actual);
		Assert.assertTrue(flag);
	}
	
	/**判断页面指定元素的文本值是否包含了期望值
	 * @param element
	 * @param expected
	 */
	public static void assertTextPresent(WebElement element, String expected) {
		String actual = Base.getText(element);
		boolean flag = expected.contains(actual);
		Assert.assertTrue(flag);
	}
	
	/**断言一个页面元素可编辑
	 * @param webElement
	 */
	public static void assertElementEdittable(WebElement webelement) {
		boolean flag = webelement.isEnabled();
		Assert.assertTrue(flag);
	}
	
	/**断言一个页面元素不可编辑
	 * @param webElement
	 */
	public static void assertElementNotEdittable(WebElement webelement) {
		boolean flag = webelement.isEnabled();
		Assert.assertFalse(flag);
	}
	
	/**断言一个页面的Url
	 * @param webElement
	 */
	public static void assertUrlPresent(String containsUrl) {
		String url = Base.driver.getCurrentUrl();
		boolean flag = (url!=null&&url.contains(containsUrl));
		Assert.assertTrue(flag);
		System.out.println("登录的Url断言："+url);
	}
	
	public static void main(String[] args) {
		
	}

}
